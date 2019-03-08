package com.dke.data.agrirouter.impl.messaging.rest.protobuf;

import com.dke.data.agrirouter.api.env.Environment;
import com.dke.data.agrirouter.impl.messaging.encoding.protobuf.EncodeMessageServiceProtobufImpl;
import com.dke.data.agrirouter.impl.messaging.rest.MessageQueryServiceImpl;

public class MessageQueryServiceProtobufImpl extends MessageQueryServiceImpl {
  public MessageQueryServiceProtobufImpl(Environment environment) {
    super(environment, new MessageSenderProtobufImpl(), new EncodeMessageServiceProtobufImpl());
  }
}
