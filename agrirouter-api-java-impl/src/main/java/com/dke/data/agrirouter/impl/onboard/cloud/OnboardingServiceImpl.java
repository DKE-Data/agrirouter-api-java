package com.dke.data.agrirouter.impl.onboard.cloud;

import agrirouter.cloud.registration.CloudVirtualizedAppRegistration;
import agrirouter.commons.MessageOuterClass;
import agrirouter.request.Request;
import agrirouter.response.Response;
import com.dke.data.agrirouter.api.dto.encoding.DecodeMessageResponse;
import com.dke.data.agrirouter.api.dto.encoding.EncodeMessageResponse;
import com.dke.data.agrirouter.api.dto.messaging.FetchMessageResponse;
import com.dke.data.agrirouter.api.dto.messaging.inner.MessageRequest;
import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse;
import com.dke.data.agrirouter.api.enums.TechnicalMessageType;
import com.dke.data.agrirouter.api.exception.CouldNotOnboardVirtualCommunicationUnitException;
import com.dke.data.agrirouter.api.factories.impl.CloudEndpointOffboardingMessageContentFactory;
import com.dke.data.agrirouter.api.factories.impl.CloudEndpointOnboardingMessageContentFactory;
import com.dke.data.agrirouter.api.factories.impl.parameters.CloudEndpointOffboardingMessageParameters;
import com.dke.data.agrirouter.api.factories.impl.parameters.CloudEndpointOnboardingMessageParameters;
import com.dke.data.agrirouter.api.service.messaging.FetchMessageService;
import com.dke.data.agrirouter.api.service.messaging.encoding.DecodeMessageService;
import com.dke.data.agrirouter.api.service.messaging.encoding.EncodeMessageService;
import com.dke.data.agrirouter.api.service.onboard.cloud.OnboardingService;
import com.dke.data.agrirouter.api.service.parameters.*;
import com.dke.data.agrirouter.impl.common.MessageIdService;
import com.dke.data.agrirouter.impl.messaging.encoding.json.DecodeMessageServiceJSONImpl;
import com.dke.data.agrirouter.impl.messaging.encoding.json.EncodeMessageServiceJSONImpl;
import com.dke.data.agrirouter.impl.messaging.rest.FetchMessageServiceImpl;
import com.dke.data.agrirouter.impl.messaging.rest.MessageSender;
import com.dke.data.agrirouter.impl.messaging.rest.json.MessageSenderJSONImpl;
import com.dke.data.agrirouter.impl.validation.ResponseValidator;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OnboardingServiceImpl implements OnboardingService, MessageSender, ResponseValidator {

  private final EncodeMessageService encodeMessageService;
  private final MessageSender messageSender;
  private final FetchMessageService fetchMessageService;
  private final DecodeMessageService decodeMessageService;

  /**
   * @param
   * @deprecated As the interface offers JSON and Protobuf, the used format has to be defined Use
   *     OnboardingServiceJSONImpl or OnboardingServiceProtobufImpl instead
   */
  @Deprecated
  public OnboardingServiceImpl() {
    this(
        new EncodeMessageServiceJSONImpl(),
        new MessageSenderJSONImpl(),
        new FetchMessageServiceImpl(),
        new DecodeMessageServiceJSONImpl());
  }

  public OnboardingServiceImpl(
      EncodeMessageService encodeMessageService,
      MessageSender messageSender,
      FetchMessageService fetchMessageService,
      DecodeMessageService decodeMessageService) {
    this.encodeMessageService = encodeMessageService;
    this.fetchMessageService = fetchMessageService;
    this.decodeMessageService = decodeMessageService;
    this.messageSender = messageSender;
  }

  /**
   * Onboarding a virtual CU for an existing cloud application (incl. several checks).
   *
   * @param parameters Parameters for the onboarding.
   * @return -
   */
  @Override
  public List<OnboardingResponse> onboard(CloudOnboardingParameters parameters) {
    parameters.validate();
    EncodeMessageResponse encodedMessageResponse = this.encodeOnboardingMessage(parameters);
    SendMessageParameters sendMessageParameters =
        createSendMessageParameters(encodedMessageResponse, parameters.getOnboardingResponse());
    Optional<List<FetchMessageResponse>> fetchMessageResponses =
        sendMessageAndFetchResponses(sendMessageParameters, parameters.getOnboardingResponse());

    List<OnboardingResponse> responses = new ArrayList<>();
    if (fetchMessageResponses.isPresent()) {
      DecodeMessageResponse decodedMessageQueryResponse =
          this.decodeMessageService.decode(
              fetchMessageResponses.get().get(0).getCommand().getMessage());
      try {
        this.assertStatusCodeIsValid(
            decodedMessageQueryResponse.getResponseEnvelope().getResponseCode());
      } catch (Exception e) {
        MessageOuterClass.Message message =
            this.decodeMessageService.decode(
                decodedMessageQueryResponse.getResponsePayloadWrapper().getDetails().getValue());
        throw new CouldNotOnboardVirtualCommunicationUnitException(message.getMessage());
      }

      if (decodedMessageQueryResponse.getResponseEnvelope().getType()
              == Response.ResponseEnvelope.ResponseBodyType.CLOUD_REGISTRATIONS
          && this.assertStatusCodeIsCreated(
              decodedMessageQueryResponse.getResponseEnvelope().getResponseCode())) {
        CloudVirtualizedAppRegistration.OnboardingResponse onboardingResponse =
            this.decode(
                decodedMessageQueryResponse.getResponsePayloadWrapper().getDetails().getValue());
        onboardingResponse
            .getOnboardedEndpointsList()
            .forEach(
                endpointRegistrationDetails -> {
                  OnboardingResponse internalOnboardingResponse = new OnboardingResponse();
                  internalOnboardingResponse.setSensorAlternateId(
                      endpointRegistrationDetails.getSensorAlternateId());
                  internalOnboardingResponse.setCapabilityAlternateId(
                      endpointRegistrationDetails.getCapabilityAlternateId());
                  internalOnboardingResponse.setDeviceAlternateId(
                      endpointRegistrationDetails.getDeviceAlternateId());
                  internalOnboardingResponse.setAuthentication(
                      parameters.getOnboardingResponse().getAuthentication());
                  internalOnboardingResponse.setConnectionCriteria(
                      parameters.getOnboardingResponse().getConnectionCriteria());
                  responses.add(internalOnboardingResponse);
                });
      }
    }
    return responses;
  }

  private Optional<List<FetchMessageResponse>> sendMessageAndFetchResponses(
      SendMessageParameters sendMessageParameters, OnboardingResponse onboardingResponse) {
    MessageSenderResponse response = this.sendMessage(sendMessageParameters);
    this.assertStatusCodeIsValid(response.getNativeResponse().getStatus());
    return this.fetchMessageService.fetch(
        onboardingResponse, MAX_TRIES_BEFORE_FAILURE, DEFAULT_INTERVAL);
  }

  private EncodeMessageResponse encodeOnboardingMessage(CloudOnboardingParameters parameters) {
    final String applicationMessageID = MessageIdService.generateMessageId();

    List<CloudEndpointOnboardingMessageParameters> onboardCloudEndpointMessageParameters =
        new ArrayList<>();
    parameters
        .getEndpointDetails()
        .forEach(
            endpointDetailsParameters -> {
              CloudEndpointOnboardingMessageParameters onboardCloudEndpointMessageParameter =
                  new CloudEndpointOnboardingMessageParameters();
              onboardCloudEndpointMessageParameter.setEndpointId(
                  endpointDetailsParameters.getEndpointId());
              onboardCloudEndpointMessageParameter.setEndpointName(
                  endpointDetailsParameters.getEndpointName());
              onboardCloudEndpointMessageParameters.add(onboardCloudEndpointMessageParameter);
            });

    PayloadParameters payloadParameters = new PayloadParameters();
    payloadParameters.setTypeUrl(
        CloudVirtualizedAppRegistration.OnboardingRequest.getDescriptor().getFullName());
    payloadParameters.setValue(
        new CloudEndpointOnboardingMessageContentFactory()
            .message(
                onboardCloudEndpointMessageParameters.toArray(
                    new CloudEndpointOnboardingMessageParameters
                        [onboardCloudEndpointMessageParameters.size()])));

    return this.encodeMessageService.encode(
        this.createMessageHeaderParameters(applicationMessageID), payloadParameters);
  }

  private EncodeMessageResponse encodeOffboardingMessage(CloudOffboardingParameters parameters) {
    final String applicationMessageID = MessageIdService.generateMessageId();

    CloudEndpointOffboardingMessageParameters cloudOffboardingParameters =
        new CloudEndpointOffboardingMessageParameters();
    cloudOffboardingParameters.setEndpointIds(new ArrayList<>());
    parameters
        .getEndpointIds()
        .forEach(
            endpointId -> {
              cloudOffboardingParameters.getEndpointIds().add(endpointId);
            });

    PayloadParameters payloadParameters = new PayloadParameters();
    payloadParameters.setTypeUrl(
        CloudVirtualizedAppRegistration.OffboardingRequest.getDescriptor().getFullName());

    payloadParameters.setValue(
        new CloudEndpointOffboardingMessageContentFactory().message(cloudOffboardingParameters));

    return this.encodeMessageService.encode(
        this.createMessageHeaderParameters(applicationMessageID), payloadParameters);
  }

  private MessageHeaderParameters createMessageHeaderParameters(String applicationMessageID) {
    MessageHeaderParameters messageHeaderParameters = new MessageHeaderParameters();
    messageHeaderParameters.setApplicationMessageId(applicationMessageID);
    messageHeaderParameters.setTechnicalMessageType(
        TechnicalMessageType.DKE_CLOUD_ONBOARD_ENDPOINTS);
    messageHeaderParameters.setMode(Request.RequestEnvelope.Mode.DIRECT);
    return messageHeaderParameters;
  }

  private SendMessageParameters createSendMessageParameters(
      EncodeMessageResponse encodedMessageResponse, OnboardingResponse onboardingResponse) {
    SendMessageParameters sendMessageParameters = new SendMessageParameters();
    sendMessageParameters.setOnboardingResponse(onboardingResponse);
    sendMessageParameters.setEncodeMessageResponse(encodedMessageResponse);
    return sendMessageParameters;
  }

  @Override
  public CloudVirtualizedAppRegistration.OnboardingResponse unsafeDecode(ByteString message)
      throws InvalidProtocolBufferException {
    return CloudVirtualizedAppRegistration.OnboardingResponse.parseFrom(message);
  }

  @Override
  public MessageRequest createSendMessageRequest(SendMessageParameters parameters) {
    return this.messageSender.createSendMessageRequest(parameters);
  }

  @Override
  public MessageSenderResponse sendMessage(SendMessageParameters parameters) {
    return this.messageSender.sendMessage(parameters);
  }
}
