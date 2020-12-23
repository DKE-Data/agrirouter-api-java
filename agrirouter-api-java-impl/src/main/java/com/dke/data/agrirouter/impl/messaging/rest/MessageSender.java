package com.dke.data.agrirouter.impl.messaging.rest;

import com.dke.data.agrirouter.api.enums.CertificationType;
import com.dke.data.agrirouter.api.messaging.MessageSendingResponse;
import com.dke.data.agrirouter.api.service.parameters.SendMessageParameters;
import com.dke.data.agrirouter.impl.RequestFactory;
import com.dke.data.agrirouter.impl.messaging.MessageBodyCreator;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

/**
 * Default message sending interface. Used by all REST implementations to send messages to the AR.
 * Provides methods to send messages synchronous and asynchronous.
 */
public interface MessageSender extends MessageBodyCreator {

  /**
   * Synchronous messages sending.
   *
   * @param parameters Parameters to send messages.
   * @return Response of the server.
   */
  default MessageSendingResponse sendMessage(SendMessageParameters parameters) {
    Response response =
        RequestFactory.securedRequest(
                Objects.requireNonNull(parameters.getOnboardingResponse())
                    .getConnectionCriteria()
                    .getMeasures(),
                parameters.getOnboardingResponse().getAuthentication().getCertificate(),
                parameters.getOnboardingResponse().getAuthentication().getSecret(),
                CertificationType.valueOf(
                    parameters.getOnboardingResponse().getAuthentication().getType()))
            .post(Entity.json(this.createSendMessageRequest(parameters)));
    return new MessageSendingResponse(response);
  }

  /**
   * Synchronous messages sending.
   *
   * @param parameters Parameters to send messages.
   * @return Response of the server, wrapped within a completable future.
   */
  default CompletableFuture<MessageSendingResponse> sendMessageAsync(
      SendMessageParameters parameters) {
    return CompletableFuture.supplyAsync(() -> this.sendMessage(parameters));
  }
}
