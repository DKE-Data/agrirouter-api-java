package com.dke.data.agrirouter.impl.messaging.rest;

import com.dke.data.agrirouter.api.dto.encoding.EncodedMessage;
import com.dke.data.agrirouter.api.env.Environment;
import com.dke.data.agrirouter.api.messaging.HttpAsyncMessageSendingResult;
import com.dke.data.agrirouter.api.messaging.MessageSendingResponse;
import com.dke.data.agrirouter.api.service.messaging.encoding.EncodeMessageService;
import com.dke.data.agrirouter.api.service.messaging.http.ListEndpointsService;
import com.dke.data.agrirouter.api.service.parameters.ListEndpointsParameters;
import com.dke.data.agrirouter.api.service.parameters.SendMessageParameters;
import com.dke.data.agrirouter.impl.EnvironmentalService;
import com.dke.data.agrirouter.impl.messaging.MessageEncoder;
import com.dke.data.agrirouter.impl.messaging.encoding.EncodeMessageServiceImpl;
import com.dke.data.agrirouter.impl.validation.ResponseValidator;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class ListEndpointsServiceImpl extends EnvironmentalService
    implements ListEndpointsService, MessageSender, MessageEncoder, ResponseValidator {

  private final EncodeMessageService encodeMessageService;

  public ListEndpointsServiceImpl(Environment environment) {
    super(environment);
    this.encodeMessageService = new EncodeMessageServiceImpl();
  }

  @Override
  public String send(ListEndpointsParameters parameters) {
    parameters.validate();
    EncodedMessage encodedMessage = this.encode(parameters);
    SendMessageParameters sendMessageParameters = new SendMessageParameters();
    sendMessageParameters.setOnboardingResponse(parameters.getOnboardingResponse());
    sendMessageParameters.setEncodedMessages(
        Collections.singletonList(encodedMessage.getEncodedMessage()));
    this.sendMessage(sendMessageParameters);
    return encodedMessage.getApplicationMessageID();
  }

  @Override
  public HttpAsyncMessageSendingResult sendAsync(ListEndpointsParameters parameters) {
    parameters.validate();
    EncodedMessage encodedMessage = this.encode(parameters);
    SendMessageParameters sendMessageParameters = new SendMessageParameters();
    sendMessageParameters.setOnboardingResponse(parameters.getOnboardingResponse());
    sendMessageParameters.setEncodedMessages(
        Collections.singletonList(encodedMessage.getEncodedMessage()));
    final CompletableFuture<MessageSendingResponse> response =
        this.sendMessageAsync(sendMessageParameters);
    return new HttpAsyncMessageSendingResult(response, encodedMessage.getApplicationMessageID());
  }

  @Override
  public EncodeMessageService getEncodeMessageService() {
    return this.encodeMessageService;
  }
}
