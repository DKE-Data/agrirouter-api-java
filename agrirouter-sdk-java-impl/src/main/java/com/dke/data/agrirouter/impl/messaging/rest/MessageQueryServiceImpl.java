package com.dke.data.agrirouter.impl.messaging.rest;

import agrirouter.feed.response.FeedResponse;
import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse;
import com.dke.data.agrirouter.api.enums.SystemMessageType;
import com.dke.data.agrirouter.api.env.Environment;
import com.dke.data.agrirouter.api.messaging.HttpAsyncMessageSendingResult;
import com.dke.data.agrirouter.api.service.messaging.encoding.MessageDecoder;
import com.dke.data.agrirouter.api.service.messaging.http.MessageQueryService;
import com.dke.data.agrirouter.api.service.parameters.MessageQueryParameters;
import com.dke.data.agrirouter.impl.EnvironmentalService;
import com.dke.data.agrirouter.impl.common.UtcTimeService;
import com.dke.data.agrirouter.impl.messaging.encoding.EncodeMessageServiceImpl;
import com.dke.data.agrirouter.impl.messaging.helper.MessageQueryHelperService;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;

public class MessageQueryServiceImpl extends EnvironmentalService
    implements MessageQueryService,
        MessageSender,
        MessageDecoder<FeedResponse.MessageQueryResponse> {

  private final MessageQueryHelperService messageQueryHelperService;

  public MessageQueryServiceImpl(Environment environment) {
    super(environment);
    messageQueryHelperService =
        new MessageQueryHelperService(
            new EncodeMessageServiceImpl(), SystemMessageType.DKE_FEED_MESSAGE_QUERY);
  }

  @Override
  public String send(MessageQueryParameters parameters) {
    return messageQueryHelperService.send(parameters);
  }

  @Override
  public HttpAsyncMessageSendingResult sendAsync(MessageQueryParameters parameters) {
    return messageQueryHelperService.sendAsync(parameters);
  }

  @Override
  public FeedResponse.MessageQueryResponse unsafeDecode(ByteString message)
      throws InvalidProtocolBufferException {
    return FeedResponse.MessageQueryResponse.parseFrom(message);
  }

  @Override
  public String queryAll(OnboardingResponse onboardingResponse) {
    MessageQueryParameters messageQueryParameters =
        createMessageParametersToQueryAllMessages(onboardingResponse);
    return send(messageQueryParameters);
  }

  @Override
  public HttpAsyncMessageSendingResult queryAllAsync(OnboardingResponse onboardingResponse) {
    MessageQueryParameters messageQueryParameters =
        createMessageParametersToQueryAllMessages(onboardingResponse);
    return sendAsync(messageQueryParameters);
  }

  @NotNull
  private MessageQueryParameters createMessageParametersToQueryAllMessages(
      OnboardingResponse onboardingResponse) {
    MessageQueryParameters messageQueryParameters = new MessageQueryParameters();
    messageQueryParameters.setOnboardingResponse(onboardingResponse);
    messageQueryParameters.setMessageIds(Collections.emptyList());
    messageQueryParameters.setSenderIds(Collections.emptyList());
    messageQueryParameters.setSentFromInSeconds(
        UtcTimeService.inThePast(UtcTimeService.FOUR_WEEKS_AGO).toEpochSecond());
    messageQueryParameters.setSentToInSeconds(UtcTimeService.now().toEpochSecond());
    return messageQueryParameters;
  }
}
