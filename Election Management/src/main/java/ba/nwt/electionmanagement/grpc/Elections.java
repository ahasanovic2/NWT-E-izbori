// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: elections.proto

package ba.nwt.electionmanagement.grpc;

public final class Elections {
  private Elections() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LogRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LogRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_APIResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_APIResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017elections.proto\"Q\n\nLogRequest\022\021\n\ttimes" +
      "tamp\030\001 \001(\t\022\020\n\010resource\030\002 \001(\t\022\016\n\006action\030\003" +
      " \001(\t\022\016\n\006status\030\004 \001(\t\"4\n\013APIResponse\022\017\n\007m" +
      "essage\030\001 \001(\t\022\024\n\014responseCode\030\002 \001(\0052)\n\005ev" +
      "ent\022 \n\003log\022\013.LogRequest\032\014.APIResponseB\"\n" +
      "\036ba.nwt.electionmanagement.grpcP\001b\006proto" +
      "3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_LogRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_LogRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LogRequest_descriptor,
        new java.lang.String[] { "Timestamp", "Resource", "Action", "Status", });
    internal_static_APIResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_APIResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_APIResponse_descriptor,
        new java.lang.String[] { "Message", "ResponseCode", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}