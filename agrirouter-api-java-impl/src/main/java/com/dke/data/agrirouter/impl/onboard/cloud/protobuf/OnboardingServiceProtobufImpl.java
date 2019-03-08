package com.dke.data.agrirouter.impl.onboard.cloud.protobuf;

import com.dke.data.agrirouter.impl.messaging.encoding.protobuf.DecodeMessageServiceProtobufImpl;
import com.dke.data.agrirouter.impl.messaging.encoding.protobuf.EncodeMessageServiceProtobufImpl;
import com.dke.data.agrirouter.impl.messaging.rest.protobuf.FetchMessageServiceProtobufImpl;
import com.dke.data.agrirouter.impl.messaging.rest.protobuf.MessageSenderProtobufImpl;
import com.dke.data.agrirouter.impl.onboard.cloud.OnboardingServiceImpl;

public class OnboardingServiceProtobufImpl extends OnboardingServiceImpl {

  public OnboardingServiceProtobufImpl() {
    super(
        new EncodeMessageServiceProtobufImpl(),
        new MessageSenderProtobufImpl(),
        new FetchMessageServiceProtobufImpl(),
        new DecodeMessageServiceProtobufImpl());
  }
}
