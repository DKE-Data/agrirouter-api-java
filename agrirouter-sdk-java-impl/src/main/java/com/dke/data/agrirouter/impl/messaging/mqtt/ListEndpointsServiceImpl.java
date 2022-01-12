package com.dke.data.agrirouter.impl.messaging.mqtt;

import com.dke.data.agrirouter.api.dto.encoding.EncodedMessage;
import com.dke.data.agrirouter.api.exception.CouldNotSendMqttMessageException;
import com.dke.data.agrirouter.api.messaging.MqttAsyncMessageSendingResult;
import com.dke.data.agrirouter.api.service.messaging.encoding.EncodeMessageService;
import com.dke.data.agrirouter.api.service.messaging.mqtt.ListEndpointsService;
import com.dke.data.agrirouter.api.service.parameters.ListEndpointsParameters;
import com.dke.data.agrirouter.api.service.parameters.SendMessageParameters;
import com.dke.data.agrirouter.impl.messaging.MessageBodyCreator;
import com.dke.data.agrirouter.impl.messaging.MessageEncoder;
import com.dke.data.agrirouter.impl.messaging.MqttService;
import com.dke.data.agrirouter.impl.messaging.encoding.EncodeMessageServiceImpl;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ListEndpointsServiceImpl extends MqttService
    implements ListEndpointsService, MessageBodyCreator, MessageEncoder {

  private final EncodeMessageService encodeMessageService;

  public ListEndpointsServiceImpl(IMqttClient mqttClient) {
    super(mqttClient);
    this.encodeMessageService = new EncodeMessageServiceImpl();
  }

  @Override
  public String send(ListEndpointsParameters parameters) {
    parameters.validate();
    try {
      EncodedMessage encodedMessage = this.encode(parameters);
      SendMessageParameters sendMessageParameters = new SendMessageParameters();
      sendMessageParameters.setOnboardingResponse(parameters.getOnboardingResponse());
      sendMessageParameters.setEncodedMessages(
          Collections.singletonList(encodedMessage.getEncodedMessage()));
      String messageAsJson = this.createMessageBody(sendMessageParameters);
      byte[] payload = messageAsJson.getBytes();
      this.getMqttClient()
          .publish(
              Objects.requireNonNull(parameters.getOnboardingResponse())
                  .getConnectionCriteria()
                  .getMeasures(),
              new MqttMessage(payload));
      return encodedMessage.getApplicationMessageID();
    } catch (MqttException e) {
      throw new CouldNotSendMqttMessageException(e);
    }
  }

  @Override
  public MqttAsyncMessageSendingResult sendAsync(ListEndpointsParameters parameters) {
    return new MqttAsyncMessageSendingResult(
        CompletableFuture.supplyAsync(() -> this.send(parameters)));
  }

  @Override
  public EncodeMessageService getEncodeMessageService() {
    return this.encodeMessageService;
  }
}
