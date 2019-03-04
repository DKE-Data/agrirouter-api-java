// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MeasureRequestMessage.proto

package com.sap.iotservices.common.protobuf.gateway;

public final class MeasureRequestMessageProtos {
  private MeasureRequestMessageProtos() {}

  public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {}

  public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public interface MeasureRequestMessageOrBuilder
      extends
      // @@protoc_insertion_point(interface_extends:gateway.MeasureRequestMessage)
      com.google.protobuf.MessageOrBuilder {

    /** <code>bytes message = 1;</code> */
    com.google.protobuf.ByteString getMessage();
  }
  /** Protobuf type {@code gateway.MeasureRequestMessage} */
  public static final class MeasureRequestMessage extends com.google.protobuf.GeneratedMessageV3
      implements
      // @@protoc_insertion_point(message_implements:gateway.MeasureRequestMessage)
      MeasureRequestMessageOrBuilder {
    private static final long serialVersionUID = 0L;
    // Use MeasureRequestMessage.newBuilder() to construct.
    private MeasureRequestMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }

    private MeasureRequestMessage() {
      message_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }

    private MeasureRequestMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10:
              {
                message_ = input.readBytes();
                break;
              }
            default:
              {
                if (!parseUnknownFieldProto3(input, unknownFields, extensionRegistry, tag)) {
                  done = true;
                }
                break;
              }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
          .internal_static_gateway_MeasureRequestMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
          .internal_static_gateway_MeasureRequestMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                  .MeasureRequestMessage.class,
              com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                  .MeasureRequestMessage.Builder.class);
    }

    public static final int MESSAGE_FIELD_NUMBER = 1;
    private com.google.protobuf.ByteString message_;
    /** <code>bytes message = 1;</code> */
    public com.google.protobuf.ByteString getMessage() {
      return message_;
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
      if (!message_.isEmpty()) {
        output.writeBytes(1, message_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!message_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream.computeBytesSize(1, message_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
        return true;
      }
      if (!(obj
          instanceof
          com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
              .MeasureRequestMessage)) {
        return super.equals(obj);
      }
      com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos.MeasureRequestMessage
          other =
              (com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                      .MeasureRequestMessage)
                  obj;

      boolean result = true;
      result = result && getMessage().equals(other.getMessage());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
      hash = (53 * hash) + getMessage().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseFrom(java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseFrom(
            java.nio.ByteBuffer data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseFrom(com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseFrom(byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseFrom(byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseFrom(java.io.InputStream input) throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseFrom(
            java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
          PARSER, input, extensionRegistry);
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseDelimitedFrom(java.io.InputStream input) throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseDelimitedFrom(
            java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(
          PARSER, input, extensionRegistry);
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseFrom(com.google.protobuf.CodedInputStream input) throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
          PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() {
      return newBuilder();
    }

    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(
        com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                .MeasureRequestMessage
            prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /** Protobuf type {@code gateway.MeasureRequestMessage} */
    public static final class Builder
        extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
        implements
        // @@protoc_insertion_point(builder_implements:gateway.MeasureRequestMessage)
        com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .internal_static_gateway_MeasureRequestMessage_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .internal_static_gateway_MeasureRequestMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                    .MeasureRequestMessage.class,
                com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                    .MeasureRequestMessage.Builder.class);
      }

      // Construct using
      // com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos.MeasureRequestMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {}
      }

      @java.lang.Override
      public Builder clear() {
        super.clear();
        message_ = com.google.protobuf.ByteString.EMPTY;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
        return com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .internal_static_gateway_MeasureRequestMessage_descriptor;
      }

      @java.lang.Override
      public com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
              .MeasureRequestMessage
          getDefaultInstanceForType() {
        return com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage.getDefaultInstance();
      }

      @java.lang.Override
      public com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
              .MeasureRequestMessage
          build() {
        com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                .MeasureRequestMessage
            result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
              .MeasureRequestMessage
          buildPartial() {
        com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                .MeasureRequestMessage
            result =
                new com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                    .MeasureRequestMessage(this);
        result.message_ = message_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }

      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }

      @java.lang.Override
      public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }

      @java.lang.Override
      public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }

      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index,
          java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }

      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }

      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other
            instanceof
            com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                .MeasureRequestMessage) {
          return mergeFrom(
              (com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                      .MeasureRequestMessage)
                  other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(
          com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                  .MeasureRequestMessage
              other) {
        if (other
            == com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                .MeasureRequestMessage.getDefaultInstance()) return this;
        if (other.getMessage() != com.google.protobuf.ByteString.EMPTY) {
          setMessage(other.getMessage());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                .MeasureRequestMessage
            parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage =
              (com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
                      .MeasureRequestMessage)
                  e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private com.google.protobuf.ByteString message_ = com.google.protobuf.ByteString.EMPTY;
      /** <code>bytes message = 1;</code> */
      public com.google.protobuf.ByteString getMessage() {
        return message_;
      }
      /** <code>bytes message = 1;</code> */
      public Builder setMessage(com.google.protobuf.ByteString value) {
        if (value == null) {
          throw new NullPointerException();
        }

        message_ = value;
        onChanged();
        return this;
      }
      /** <code>bytes message = 1;</code> */
      public Builder clearMessage() {

        message_ = getDefaultInstance().getMessage();
        onChanged();
        return this;
      }

      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }

      // @@protoc_insertion_point(builder_scope:gateway.MeasureRequestMessage)
    }

    // @@protoc_insertion_point(class_scope:gateway.MeasureRequestMessage)
    private static final com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        DEFAULT_INSTANCE;

    static {
      DEFAULT_INSTANCE =
          new com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
              .MeasureRequestMessage();
    }

    public static com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MeasureRequestMessage> PARSER =
        new com.google.protobuf.AbstractParser<MeasureRequestMessage>() {
          @java.lang.Override
          public MeasureRequestMessage parsePartialFrom(
              com.google.protobuf.CodedInputStream input,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws com.google.protobuf.InvalidProtocolBufferException {
            return new MeasureRequestMessage(input, extensionRegistry);
          }
        };

    public static com.google.protobuf.Parser<MeasureRequestMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MeasureRequestMessage> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.sap.iotservices.common.protobuf.gateway.MeasureRequestMessageProtos
            .MeasureRequestMessage
        getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }

  private static final com.google.protobuf.Descriptors.Descriptor
      internal_static_gateway_MeasureRequestMessage_descriptor;
  private static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_gateway_MeasureRequestMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

  static {
    java.lang.String[] descriptorData = {
      "\n\033MeasureRequestMessage.proto\022\007gateway\"("
          + "\n\025MeasureRequestMessage\022\017\n\007message\030\001 \001(\014"
          + "BJ\n+com.sap.iotservices.common.protobuf."
          + "gatewayB\033MeasureRequestMessageProtosb\006pr"
          + "oto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
        descriptorData, new com.google.protobuf.Descriptors.FileDescriptor[] {}, assigner);
    internal_static_gateway_MeasureRequestMessage_descriptor =
        getDescriptor().getMessageTypes().get(0);
    internal_static_gateway_MeasureRequestMessage_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_gateway_MeasureRequestMessage_descriptor,
            new java.lang.String[] {
              "Message",
            });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
