package kotlin.reflect.jvm.internal.impl.metadata.jvm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractParser;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList;
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import kotlin.reflect.jvm.internal.impl.protobuf.ProtocolStringList;

/* loaded from: classes2.dex */
public final class JvmModuleProtoBuf {

    public interface ModuleOrBuilder extends MessageLiteOrBuilder {
    }

    public interface PackagePartsOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class Module extends GeneratedMessageLite implements ModuleOrBuilder {
        public static Parser<Module> PARSER = new AbstractParser<Module>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.Module.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public Module parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Module(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Module defaultInstance;
        private List<ProtoBuf.Annotation> annotation_;
        private int bitField0_;
        private LazyStringList jvmPackageName_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List<PackageParts> metadataParts_;
        private List<PackageParts> packageParts_;
        private ProtoBuf.QualifiedNameTable qualifiedNameTable_;
        private ProtoBuf.StringTable stringTable_;
        private final ByteString unknownFields;

        private Module(GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Module(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Module getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public Module getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Module(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            ByteString.Output outputNewOutput = ByteString.newOutput();
            CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
            boolean z = false;
            int i = 0;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                if ((i & 1) != 1) {
                                    this.packageParts_ = new ArrayList();
                                    i |= 1;
                                }
                                this.packageParts_.add(codedInputStream.readMessage(PackageParts.PARSER, extensionRegistryLite));
                            } else if (tag == 18) {
                                if ((i & 2) != 2) {
                                    this.metadataParts_ = new ArrayList();
                                    i |= 2;
                                }
                                this.metadataParts_.add(codedInputStream.readMessage(PackageParts.PARSER, extensionRegistryLite));
                            } else if (tag != 26) {
                                if (tag == 34) {
                                    ProtoBuf.StringTable.Builder builder = (this.bitField0_ & 1) == 1 ? this.stringTable_.toBuilder() : null;
                                    ProtoBuf.StringTable stringTable = (ProtoBuf.StringTable) codedInputStream.readMessage(ProtoBuf.StringTable.PARSER, extensionRegistryLite);
                                    this.stringTable_ = stringTable;
                                    if (builder != null) {
                                        builder.mergeFrom(stringTable);
                                        this.stringTable_ = builder.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                } else if (tag == 42) {
                                    ProtoBuf.QualifiedNameTable.Builder builder2 = (this.bitField0_ & 2) == 2 ? this.qualifiedNameTable_.toBuilder() : null;
                                    ProtoBuf.QualifiedNameTable qualifiedNameTable = (ProtoBuf.QualifiedNameTable) codedInputStream.readMessage(ProtoBuf.QualifiedNameTable.PARSER, extensionRegistryLite);
                                    this.qualifiedNameTable_ = qualifiedNameTable;
                                    if (builder2 != null) {
                                        builder2.mergeFrom(qualifiedNameTable);
                                        this.qualifiedNameTable_ = builder2.buildPartial();
                                    }
                                    this.bitField0_ |= 2;
                                } else if (tag != 50) {
                                    if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                                    }
                                } else {
                                    if ((i & 32) != 32) {
                                        this.annotation_ = new ArrayList();
                                        i |= 32;
                                    }
                                    this.annotation_.add(codedInputStream.readMessage(ProtoBuf.Annotation.PARSER, extensionRegistryLite));
                                }
                            } else {
                                ByteString bytes = codedInputStream.readBytes();
                                if ((i & 4) != 4) {
                                    this.jvmPackageName_ = new LazyStringArrayList();
                                    i |= 4;
                                }
                                this.jvmPackageName_.add(bytes);
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable th) {
                    if ((i & 1) == 1) {
                        this.packageParts_ = Collections.unmodifiableList(this.packageParts_);
                    }
                    if ((i & 2) == 2) {
                        this.metadataParts_ = Collections.unmodifiableList(this.metadataParts_);
                    }
                    if ((i & 4) == 4) {
                        this.jvmPackageName_ = this.jvmPackageName_.getUnmodifiableView();
                    }
                    if ((i & 32) == 32) {
                        this.annotation_ = Collections.unmodifiableList(this.annotation_);
                    }
                    try {
                        codedOutputStreamNewInstance.flush();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.unknownFields = outputNewOutput.toByteString();
                        throw th2;
                    }
                    this.unknownFields = outputNewOutput.toByteString();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if ((i & 1) == 1) {
                this.packageParts_ = Collections.unmodifiableList(this.packageParts_);
            }
            if ((i & 2) == 2) {
                this.metadataParts_ = Collections.unmodifiableList(this.metadataParts_);
            }
            if ((i & 4) == 4) {
                this.jvmPackageName_ = this.jvmPackageName_.getUnmodifiableView();
            }
            if ((i & 32) == 32) {
                this.annotation_ = Collections.unmodifiableList(this.annotation_);
            }
            try {
                codedOutputStreamNewInstance.flush();
            } catch (IOException unused2) {
            } catch (Throwable th3) {
                this.unknownFields = outputNewOutput.toByteString();
                throw th3;
            }
            this.unknownFields = outputNewOutput.toByteString();
            makeExtensionsImmutable();
        }

        static {
            Module module = new Module(true);
            defaultInstance = module;
            module.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<Module> getParserForType() {
            return PARSER;
        }

        public List<PackageParts> getPackagePartsList() {
            return this.packageParts_;
        }

        public int getPackagePartsCount() {
            return this.packageParts_.size();
        }

        public PackageParts getPackageParts(int i) {
            return this.packageParts_.get(i);
        }

        public List<PackageParts> getMetadataPartsList() {
            return this.metadataParts_;
        }

        public int getMetadataPartsCount() {
            return this.metadataParts_.size();
        }

        public PackageParts getMetadataParts(int i) {
            return this.metadataParts_.get(i);
        }

        public ProtocolStringList getJvmPackageNameList() {
            return this.jvmPackageName_;
        }

        public boolean hasStringTable() {
            return (this.bitField0_ & 1) == 1;
        }

        public ProtoBuf.StringTable getStringTable() {
            return this.stringTable_;
        }

        public boolean hasQualifiedNameTable() {
            return (this.bitField0_ & 2) == 2;
        }

        public ProtoBuf.QualifiedNameTable getQualifiedNameTable() {
            return this.qualifiedNameTable_;
        }

        public List<ProtoBuf.Annotation> getAnnotationList() {
            return this.annotation_;
        }

        public int getAnnotationCount() {
            return this.annotation_.size();
        }

        public ProtoBuf.Annotation getAnnotation(int i) {
            return this.annotation_.get(i);
        }

        private void initFields() {
            this.packageParts_ = Collections.emptyList();
            this.metadataParts_ = Collections.emptyList();
            this.jvmPackageName_ = LazyStringArrayList.EMPTY;
            this.stringTable_ = ProtoBuf.StringTable.getDefaultInstance();
            this.qualifiedNameTable_ = ProtoBuf.QualifiedNameTable.getDefaultInstance();
            this.annotation_ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getPackagePartsCount(); i++) {
                if (!getPackageParts(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getMetadataPartsCount(); i2++) {
                if (!getMetadataParts(i2).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasQualifiedNameTable() && !getQualifiedNameTable().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i3 = 0; i3 < getAnnotationCount(); i3++) {
                if (!getAnnotation(i3).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.packageParts_.size(); i++) {
                codedOutputStream.writeMessage(1, this.packageParts_.get(i));
            }
            for (int i2 = 0; i2 < this.metadataParts_.size(); i2++) {
                codedOutputStream.writeMessage(2, this.metadataParts_.get(i2));
            }
            for (int i3 = 0; i3 < this.jvmPackageName_.size(); i3++) {
                codedOutputStream.writeBytes(3, this.jvmPackageName_.getByteString(i3));
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeMessage(4, this.stringTable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(5, this.qualifiedNameTable_);
            }
            for (int i4 = 0; i4 < this.annotation_.size(); i4++) {
                codedOutputStream.writeMessage(6, this.annotation_.get(i4));
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = 0;
            for (int i2 = 0; i2 < this.packageParts_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.packageParts_.get(i2));
            }
            for (int i3 = 0; i3 < this.metadataParts_.size(); i3++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(2, this.metadataParts_.get(i3));
            }
            int iComputeBytesSizeNoTag = 0;
            for (int i4 = 0; i4 < this.jvmPackageName_.size(); i4++) {
                iComputeBytesSizeNoTag += CodedOutputStream.computeBytesSizeNoTag(this.jvmPackageName_.getByteString(i4));
            }
            int size = iComputeMessageSize + iComputeBytesSizeNoTag + (getJvmPackageNameList().size() * 1);
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeMessageSize(4, this.stringTable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeMessageSize(5, this.qualifiedNameTable_);
            }
            for (int i5 = 0; i5 < this.annotation_.size(); i5++) {
                size += CodedOutputStream.computeMessageSize(6, this.annotation_.get(i5));
            }
            int size2 = size + this.unknownFields.size();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static Module parseFrom(InputStream inputStream) throws IOException {
            return PARSER.parseFrom(inputStream);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Module module) {
            return newBuilder().mergeFrom(module);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Module, Builder> implements ModuleOrBuilder {
            private int bitField0_;
            private List<PackageParts> packageParts_ = Collections.emptyList();
            private List<PackageParts> metadataParts_ = Collections.emptyList();
            private LazyStringList jvmPackageName_ = LazyStringArrayList.EMPTY;
            private ProtoBuf.StringTable stringTable_ = ProtoBuf.StringTable.getDefaultInstance();
            private ProtoBuf.QualifiedNameTable qualifiedNameTable_ = ProtoBuf.QualifiedNameTable.getDefaultInstance();
            private List<ProtoBuf.Annotation> annotation_ = Collections.emptyList();

            private void maybeForceBuilderInitialization() {
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo1331clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Module getDefaultInstanceForType() {
                return Module.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Module build() {
                Module moduleBuildPartial = buildPartial();
                if (moduleBuildPartial.isInitialized()) {
                    return moduleBuildPartial;
                }
                throw newUninitializedMessageException(moduleBuildPartial);
            }

            public Module buildPartial() {
                Module module = new Module(this);
                int i = this.bitField0_;
                if ((i & 1) == 1) {
                    this.packageParts_ = Collections.unmodifiableList(this.packageParts_);
                    this.bitField0_ &= -2;
                }
                module.packageParts_ = this.packageParts_;
                if ((this.bitField0_ & 2) == 2) {
                    this.metadataParts_ = Collections.unmodifiableList(this.metadataParts_);
                    this.bitField0_ &= -3;
                }
                module.metadataParts_ = this.metadataParts_;
                if ((this.bitField0_ & 4) == 4) {
                    this.jvmPackageName_ = this.jvmPackageName_.getUnmodifiableView();
                    this.bitField0_ &= -5;
                }
                module.jvmPackageName_ = this.jvmPackageName_;
                int i2 = (i & 8) != 8 ? 0 : 1;
                module.stringTable_ = this.stringTable_;
                if ((i & 16) == 16) {
                    i2 |= 2;
                }
                module.qualifiedNameTable_ = this.qualifiedNameTable_;
                if ((this.bitField0_ & 32) == 32) {
                    this.annotation_ = Collections.unmodifiableList(this.annotation_);
                    this.bitField0_ &= -33;
                }
                module.annotation_ = this.annotation_;
                module.bitField0_ = i2;
                return module;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public Builder mergeFrom(Module module) {
                if (module == Module.getDefaultInstance()) {
                    return this;
                }
                if (!module.packageParts_.isEmpty()) {
                    if (this.packageParts_.isEmpty()) {
                        this.packageParts_ = module.packageParts_;
                        this.bitField0_ &= -2;
                    } else {
                        ensurePackagePartsIsMutable();
                        this.packageParts_.addAll(module.packageParts_);
                    }
                }
                if (!module.metadataParts_.isEmpty()) {
                    if (this.metadataParts_.isEmpty()) {
                        this.metadataParts_ = module.metadataParts_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureMetadataPartsIsMutable();
                        this.metadataParts_.addAll(module.metadataParts_);
                    }
                }
                if (!module.jvmPackageName_.isEmpty()) {
                    if (this.jvmPackageName_.isEmpty()) {
                        this.jvmPackageName_ = module.jvmPackageName_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureJvmPackageNameIsMutable();
                        this.jvmPackageName_.addAll(module.jvmPackageName_);
                    }
                }
                if (module.hasStringTable()) {
                    mergeStringTable(module.getStringTable());
                }
                if (module.hasQualifiedNameTable()) {
                    mergeQualifiedNameTable(module.getQualifiedNameTable());
                }
                if (!module.annotation_.isEmpty()) {
                    if (this.annotation_.isEmpty()) {
                        this.annotation_ = module.annotation_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureAnnotationIsMutable();
                        this.annotation_.addAll(module.annotation_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(module.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getPackagePartsCount(); i++) {
                    if (!getPackageParts(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getMetadataPartsCount(); i2++) {
                    if (!getMetadataParts(i2).isInitialized()) {
                        return false;
                    }
                }
                if (hasQualifiedNameTable() && !getQualifiedNameTable().isInitialized()) {
                    return false;
                }
                for (int i3 = 0; i3 < getAnnotationCount(); i3++) {
                    if (!getAnnotation(i3).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.Module.Builder mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r3, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$Module> r1 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.Module.PARSER     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                    kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$Module r3 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.Module) r3     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1b
                L11:
                    r3 = move-exception
                    kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$Module r4 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.Module) r4     // Catch: java.lang.Throwable -> Lf
                    throw r3     // Catch: java.lang.Throwable -> L19
                L19:
                    r3 = move-exception
                    r0 = r4
                L1b:
                    if (r0 == 0) goto L20
                    r2.mergeFrom(r0)
                L20:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.Module.Builder.mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$Module$Builder");
            }

            private void ensurePackagePartsIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.packageParts_ = new ArrayList(this.packageParts_);
                    this.bitField0_ |= 1;
                }
            }

            public int getPackagePartsCount() {
                return this.packageParts_.size();
            }

            public PackageParts getPackageParts(int i) {
                return this.packageParts_.get(i);
            }

            private void ensureMetadataPartsIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.metadataParts_ = new ArrayList(this.metadataParts_);
                    this.bitField0_ |= 2;
                }
            }

            public int getMetadataPartsCount() {
                return this.metadataParts_.size();
            }

            public PackageParts getMetadataParts(int i) {
                return this.metadataParts_.get(i);
            }

            private void ensureJvmPackageNameIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.jvmPackageName_ = new LazyStringArrayList(this.jvmPackageName_);
                    this.bitField0_ |= 4;
                }
            }

            public Builder mergeStringTable(ProtoBuf.StringTable stringTable) {
                if ((this.bitField0_ & 8) == 8 && this.stringTable_ != ProtoBuf.StringTable.getDefaultInstance()) {
                    this.stringTable_ = ProtoBuf.StringTable.newBuilder(this.stringTable_).mergeFrom(stringTable).buildPartial();
                } else {
                    this.stringTable_ = stringTable;
                }
                this.bitField0_ |= 8;
                return this;
            }

            public boolean hasQualifiedNameTable() {
                return (this.bitField0_ & 16) == 16;
            }

            public ProtoBuf.QualifiedNameTable getQualifiedNameTable() {
                return this.qualifiedNameTable_;
            }

            public Builder mergeQualifiedNameTable(ProtoBuf.QualifiedNameTable qualifiedNameTable) {
                if ((this.bitField0_ & 16) == 16 && this.qualifiedNameTable_ != ProtoBuf.QualifiedNameTable.getDefaultInstance()) {
                    this.qualifiedNameTable_ = ProtoBuf.QualifiedNameTable.newBuilder(this.qualifiedNameTable_).mergeFrom(qualifiedNameTable).buildPartial();
                } else {
                    this.qualifiedNameTable_ = qualifiedNameTable;
                }
                this.bitField0_ |= 16;
                return this;
            }

            private void ensureAnnotationIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.annotation_ = new ArrayList(this.annotation_);
                    this.bitField0_ |= 32;
                }
            }

            public int getAnnotationCount() {
                return this.annotation_.size();
            }

            public ProtoBuf.Annotation getAnnotation(int i) {
                return this.annotation_.get(i);
            }
        }
    }

    public static final class PackageParts extends GeneratedMessageLite implements PackagePartsOrBuilder {
        public static Parser<PackageParts> PARSER = new AbstractParser<PackageParts>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.PackageParts.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public PackageParts parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PackageParts(codedInputStream, extensionRegistryLite);
            }
        };
        private static final PackageParts defaultInstance;
        private int bitField0_;
        private int classWithJvmPackageNamePackageIdMemoizedSerializedSize;
        private List<Integer> classWithJvmPackageNamePackageId_;
        private LazyStringList classWithJvmPackageNameShortName_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int multifileFacadeShortNameIdMemoizedSerializedSize;
        private List<Integer> multifileFacadeShortNameId_;
        private LazyStringList multifileFacadeShortName_;
        private Object packageFqName_;
        private LazyStringList shortClassName_;
        private final ByteString unknownFields;

        private PackageParts(GeneratedMessageLite.Builder builder) {
            super(builder);
            this.multifileFacadeShortNameIdMemoizedSerializedSize = -1;
            this.classWithJvmPackageNamePackageIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PackageParts(boolean z) {
            this.multifileFacadeShortNameIdMemoizedSerializedSize = -1;
            this.classWithJvmPackageNamePackageIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static PackageParts getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public PackageParts getDefaultInstanceForType() {
            return defaultInstance;
        }

        private PackageParts(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.multifileFacadeShortNameIdMemoizedSerializedSize = -1;
            this.classWithJvmPackageNamePackageIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            ByteString.Output outputNewOutput = ByteString.newOutput();
            CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
            boolean z = false;
            int i = 0;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    ByteString bytes = codedInputStream.readBytes();
                                    this.bitField0_ |= 1;
                                    this.packageFqName_ = bytes;
                                } else if (tag == 18) {
                                    ByteString bytes2 = codedInputStream.readBytes();
                                    if ((i & 2) != 2) {
                                        this.shortClassName_ = new LazyStringArrayList();
                                        i |= 2;
                                    }
                                    this.shortClassName_.add(bytes2);
                                } else if (tag == 24) {
                                    if ((i & 4) != 4) {
                                        this.multifileFacadeShortNameId_ = new ArrayList();
                                        i |= 4;
                                    }
                                    this.multifileFacadeShortNameId_.add(Integer.valueOf(codedInputStream.readInt32()));
                                } else if (tag == 26) {
                                    int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if ((i & 4) != 4 && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.multifileFacadeShortNameId_ = new ArrayList();
                                        i |= 4;
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.multifileFacadeShortNameId_.add(Integer.valueOf(codedInputStream.readInt32()));
                                    }
                                    codedInputStream.popLimit(iPushLimit);
                                } else if (tag == 34) {
                                    ByteString bytes3 = codedInputStream.readBytes();
                                    if ((i & 8) != 8) {
                                        this.multifileFacadeShortName_ = new LazyStringArrayList();
                                        i |= 8;
                                    }
                                    this.multifileFacadeShortName_.add(bytes3);
                                } else if (tag == 42) {
                                    ByteString bytes4 = codedInputStream.readBytes();
                                    if ((i & 16) != 16) {
                                        this.classWithJvmPackageNameShortName_ = new LazyStringArrayList();
                                        i |= 16;
                                    }
                                    this.classWithJvmPackageNameShortName_.add(bytes4);
                                } else if (tag == 48) {
                                    if ((i & 32) != 32) {
                                        this.classWithJvmPackageNamePackageId_ = new ArrayList();
                                        i |= 32;
                                    }
                                    this.classWithJvmPackageNamePackageId_.add(Integer.valueOf(codedInputStream.readInt32()));
                                } else if (tag != 50) {
                                    if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                                    }
                                } else {
                                    int iPushLimit2 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if ((i & 32) != 32 && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.classWithJvmPackageNamePackageId_ = new ArrayList();
                                        i |= 32;
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.classWithJvmPackageNamePackageId_.add(Integer.valueOf(codedInputStream.readInt32()));
                                    }
                                    codedInputStream.popLimit(iPushLimit2);
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable th) {
                    if ((i & 2) == 2) {
                        this.shortClassName_ = this.shortClassName_.getUnmodifiableView();
                    }
                    if ((i & 4) == 4) {
                        this.multifileFacadeShortNameId_ = Collections.unmodifiableList(this.multifileFacadeShortNameId_);
                    }
                    if ((i & 8) == 8) {
                        this.multifileFacadeShortName_ = this.multifileFacadeShortName_.getUnmodifiableView();
                    }
                    if ((i & 16) == 16) {
                        this.classWithJvmPackageNameShortName_ = this.classWithJvmPackageNameShortName_.getUnmodifiableView();
                    }
                    if ((i & 32) == 32) {
                        this.classWithJvmPackageNamePackageId_ = Collections.unmodifiableList(this.classWithJvmPackageNamePackageId_);
                    }
                    try {
                        codedOutputStreamNewInstance.flush();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.unknownFields = outputNewOutput.toByteString();
                        throw th2;
                    }
                    this.unknownFields = outputNewOutput.toByteString();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if ((i & 2) == 2) {
                this.shortClassName_ = this.shortClassName_.getUnmodifiableView();
            }
            if ((i & 4) == 4) {
                this.multifileFacadeShortNameId_ = Collections.unmodifiableList(this.multifileFacadeShortNameId_);
            }
            if ((i & 8) == 8) {
                this.multifileFacadeShortName_ = this.multifileFacadeShortName_.getUnmodifiableView();
            }
            if ((i & 16) == 16) {
                this.classWithJvmPackageNameShortName_ = this.classWithJvmPackageNameShortName_.getUnmodifiableView();
            }
            if ((i & 32) == 32) {
                this.classWithJvmPackageNamePackageId_ = Collections.unmodifiableList(this.classWithJvmPackageNamePackageId_);
            }
            try {
                codedOutputStreamNewInstance.flush();
            } catch (IOException unused2) {
            } catch (Throwable th3) {
                this.unknownFields = outputNewOutput.toByteString();
                throw th3;
            }
            this.unknownFields = outputNewOutput.toByteString();
            makeExtensionsImmutable();
        }

        static {
            PackageParts packageParts = new PackageParts(true);
            defaultInstance = packageParts;
            packageParts.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<PackageParts> getParserForType() {
            return PARSER;
        }

        public boolean hasPackageFqName() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getPackageFqName() {
            Object obj = this.packageFqName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.packageFqName_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getPackageFqNameBytes() {
            Object obj = this.packageFqName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.packageFqName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public ProtocolStringList getShortClassNameList() {
            return this.shortClassName_;
        }

        public List<Integer> getMultifileFacadeShortNameIdList() {
            return this.multifileFacadeShortNameId_;
        }

        public ProtocolStringList getMultifileFacadeShortNameList() {
            return this.multifileFacadeShortName_;
        }

        public ProtocolStringList getClassWithJvmPackageNameShortNameList() {
            return this.classWithJvmPackageNameShortName_;
        }

        public List<Integer> getClassWithJvmPackageNamePackageIdList() {
            return this.classWithJvmPackageNamePackageId_;
        }

        private void initFields() {
            this.packageFqName_ = "";
            this.shortClassName_ = LazyStringArrayList.EMPTY;
            this.multifileFacadeShortNameId_ = Collections.emptyList();
            this.multifileFacadeShortName_ = LazyStringArrayList.EMPTY;
            this.classWithJvmPackageNameShortName_ = LazyStringArrayList.EMPTY;
            this.classWithJvmPackageNamePackageId_ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasPackageFqName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getPackageFqNameBytes());
            }
            for (int i = 0; i < this.shortClassName_.size(); i++) {
                codedOutputStream.writeBytes(2, this.shortClassName_.getByteString(i));
            }
            if (getMultifileFacadeShortNameIdList().size() > 0) {
                codedOutputStream.writeRawVarint32(26);
                codedOutputStream.writeRawVarint32(this.multifileFacadeShortNameIdMemoizedSerializedSize);
            }
            for (int i2 = 0; i2 < this.multifileFacadeShortNameId_.size(); i2++) {
                codedOutputStream.writeInt32NoTag(this.multifileFacadeShortNameId_.get(i2).intValue());
            }
            for (int i3 = 0; i3 < this.multifileFacadeShortName_.size(); i3++) {
                codedOutputStream.writeBytes(4, this.multifileFacadeShortName_.getByteString(i3));
            }
            for (int i4 = 0; i4 < this.classWithJvmPackageNameShortName_.size(); i4++) {
                codedOutputStream.writeBytes(5, this.classWithJvmPackageNameShortName_.getByteString(i4));
            }
            if (getClassWithJvmPackageNamePackageIdList().size() > 0) {
                codedOutputStream.writeRawVarint32(50);
                codedOutputStream.writeRawVarint32(this.classWithJvmPackageNamePackageIdMemoizedSerializedSize);
            }
            for (int i5 = 0; i5 < this.classWithJvmPackageNamePackageId_.size(); i5++) {
                codedOutputStream.writeInt32NoTag(this.classWithJvmPackageNamePackageId_.get(i5).intValue());
            }
            codedOutputStream.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int iComputeBytesSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getPackageFqNameBytes()) + 0 : 0;
            int iComputeBytesSizeNoTag = 0;
            for (int i2 = 0; i2 < this.shortClassName_.size(); i2++) {
                iComputeBytesSizeNoTag += CodedOutputStream.computeBytesSizeNoTag(this.shortClassName_.getByteString(i2));
            }
            int size = iComputeBytesSize + iComputeBytesSizeNoTag + (getShortClassNameList().size() * 1);
            int iComputeInt32SizeNoTag = 0;
            for (int i3 = 0; i3 < this.multifileFacadeShortNameId_.size(); i3++) {
                iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(this.multifileFacadeShortNameId_.get(i3).intValue());
            }
            int iComputeInt32SizeNoTag2 = size + iComputeInt32SizeNoTag;
            if (!getMultifileFacadeShortNameIdList().isEmpty()) {
                iComputeInt32SizeNoTag2 = iComputeInt32SizeNoTag2 + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag);
            }
            this.multifileFacadeShortNameIdMemoizedSerializedSize = iComputeInt32SizeNoTag;
            int iComputeBytesSizeNoTag2 = 0;
            for (int i4 = 0; i4 < this.multifileFacadeShortName_.size(); i4++) {
                iComputeBytesSizeNoTag2 += CodedOutputStream.computeBytesSizeNoTag(this.multifileFacadeShortName_.getByteString(i4));
            }
            int size2 = iComputeInt32SizeNoTag2 + iComputeBytesSizeNoTag2 + (getMultifileFacadeShortNameList().size() * 1);
            int iComputeBytesSizeNoTag3 = 0;
            for (int i5 = 0; i5 < this.classWithJvmPackageNameShortName_.size(); i5++) {
                iComputeBytesSizeNoTag3 += CodedOutputStream.computeBytesSizeNoTag(this.classWithJvmPackageNameShortName_.getByteString(i5));
            }
            int size3 = size2 + iComputeBytesSizeNoTag3 + (getClassWithJvmPackageNameShortNameList().size() * 1);
            int iComputeInt32SizeNoTag3 = 0;
            for (int i6 = 0; i6 < this.classWithJvmPackageNamePackageId_.size(); i6++) {
                iComputeInt32SizeNoTag3 += CodedOutputStream.computeInt32SizeNoTag(this.classWithJvmPackageNamePackageId_.get(i6).intValue());
            }
            int iComputeInt32SizeNoTag4 = size3 + iComputeInt32SizeNoTag3;
            if (!getClassWithJvmPackageNamePackageIdList().isEmpty()) {
                iComputeInt32SizeNoTag4 = iComputeInt32SizeNoTag4 + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag3);
            }
            this.classWithJvmPackageNamePackageIdMemoizedSerializedSize = iComputeInt32SizeNoTag3;
            int size4 = iComputeInt32SizeNoTag4 + this.unknownFields.size();
            this.memoizedSerializedSize = size4;
            return size4;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PackageParts packageParts) {
            return newBuilder().mergeFrom(packageParts);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PackageParts, Builder> implements PackagePartsOrBuilder {
            private int bitField0_;
            private Object packageFqName_ = "";
            private LazyStringList shortClassName_ = LazyStringArrayList.EMPTY;
            private List<Integer> multifileFacadeShortNameId_ = Collections.emptyList();
            private LazyStringList multifileFacadeShortName_ = LazyStringArrayList.EMPTY;
            private LazyStringList classWithJvmPackageNameShortName_ = LazyStringArrayList.EMPTY;
            private List<Integer> classWithJvmPackageNamePackageId_ = Collections.emptyList();

            private void maybeForceBuilderInitialization() {
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo1331clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public PackageParts getDefaultInstanceForType() {
                return PackageParts.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public PackageParts build() {
                PackageParts packagePartsBuildPartial = buildPartial();
                if (packagePartsBuildPartial.isInitialized()) {
                    return packagePartsBuildPartial;
                }
                throw newUninitializedMessageException(packagePartsBuildPartial);
            }

            public PackageParts buildPartial() {
                PackageParts packageParts = new PackageParts(this);
                int i = (this.bitField0_ & 1) != 1 ? 0 : 1;
                packageParts.packageFqName_ = this.packageFqName_;
                if ((this.bitField0_ & 2) == 2) {
                    this.shortClassName_ = this.shortClassName_.getUnmodifiableView();
                    this.bitField0_ &= -3;
                }
                packageParts.shortClassName_ = this.shortClassName_;
                if ((this.bitField0_ & 4) == 4) {
                    this.multifileFacadeShortNameId_ = Collections.unmodifiableList(this.multifileFacadeShortNameId_);
                    this.bitField0_ &= -5;
                }
                packageParts.multifileFacadeShortNameId_ = this.multifileFacadeShortNameId_;
                if ((this.bitField0_ & 8) == 8) {
                    this.multifileFacadeShortName_ = this.multifileFacadeShortName_.getUnmodifiableView();
                    this.bitField0_ &= -9;
                }
                packageParts.multifileFacadeShortName_ = this.multifileFacadeShortName_;
                if ((this.bitField0_ & 16) == 16) {
                    this.classWithJvmPackageNameShortName_ = this.classWithJvmPackageNameShortName_.getUnmodifiableView();
                    this.bitField0_ &= -17;
                }
                packageParts.classWithJvmPackageNameShortName_ = this.classWithJvmPackageNameShortName_;
                if ((this.bitField0_ & 32) == 32) {
                    this.classWithJvmPackageNamePackageId_ = Collections.unmodifiableList(this.classWithJvmPackageNamePackageId_);
                    this.bitField0_ &= -33;
                }
                packageParts.classWithJvmPackageNamePackageId_ = this.classWithJvmPackageNamePackageId_;
                packageParts.bitField0_ = i;
                return packageParts;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public Builder mergeFrom(PackageParts packageParts) {
                if (packageParts == PackageParts.getDefaultInstance()) {
                    return this;
                }
                if (packageParts.hasPackageFqName()) {
                    this.bitField0_ |= 1;
                    this.packageFqName_ = packageParts.packageFqName_;
                }
                if (!packageParts.shortClassName_.isEmpty()) {
                    if (this.shortClassName_.isEmpty()) {
                        this.shortClassName_ = packageParts.shortClassName_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureShortClassNameIsMutable();
                        this.shortClassName_.addAll(packageParts.shortClassName_);
                    }
                }
                if (!packageParts.multifileFacadeShortNameId_.isEmpty()) {
                    if (this.multifileFacadeShortNameId_.isEmpty()) {
                        this.multifileFacadeShortNameId_ = packageParts.multifileFacadeShortNameId_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureMultifileFacadeShortNameIdIsMutable();
                        this.multifileFacadeShortNameId_.addAll(packageParts.multifileFacadeShortNameId_);
                    }
                }
                if (!packageParts.multifileFacadeShortName_.isEmpty()) {
                    if (this.multifileFacadeShortName_.isEmpty()) {
                        this.multifileFacadeShortName_ = packageParts.multifileFacadeShortName_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureMultifileFacadeShortNameIsMutable();
                        this.multifileFacadeShortName_.addAll(packageParts.multifileFacadeShortName_);
                    }
                }
                if (!packageParts.classWithJvmPackageNameShortName_.isEmpty()) {
                    if (this.classWithJvmPackageNameShortName_.isEmpty()) {
                        this.classWithJvmPackageNameShortName_ = packageParts.classWithJvmPackageNameShortName_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureClassWithJvmPackageNameShortNameIsMutable();
                        this.classWithJvmPackageNameShortName_.addAll(packageParts.classWithJvmPackageNameShortName_);
                    }
                }
                if (!packageParts.classWithJvmPackageNamePackageId_.isEmpty()) {
                    if (this.classWithJvmPackageNamePackageId_.isEmpty()) {
                        this.classWithJvmPackageNamePackageId_ = packageParts.classWithJvmPackageNamePackageId_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureClassWithJvmPackageNamePackageIdIsMutable();
                        this.classWithJvmPackageNamePackageId_.addAll(packageParts.classWithJvmPackageNamePackageId_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(packageParts.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasPackageFqName();
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.PackageParts.Builder mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream r3, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts> r1 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.PackageParts.PARSER     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                    kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts r3 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.PackageParts) r3     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1b
                L11:
                    r3 = move-exception
                    kotlin.reflect.jvm.internal.impl.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts r4 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.PackageParts) r4     // Catch: java.lang.Throwable -> Lf
                    throw r3     // Catch: java.lang.Throwable -> L19
                L19:
                    r3 = move-exception
                    r0 = r4
                L1b:
                    if (r0 == 0) goto L20
                    r2.mergeFrom(r0)
                L20:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf.PackageParts.Builder.mergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream, kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite):kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmModuleProtoBuf$PackageParts$Builder");
            }

            public boolean hasPackageFqName() {
                return (this.bitField0_ & 1) == 1;
            }

            private void ensureShortClassNameIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.shortClassName_ = new LazyStringArrayList(this.shortClassName_);
                    this.bitField0_ |= 2;
                }
            }

            private void ensureMultifileFacadeShortNameIdIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.multifileFacadeShortNameId_ = new ArrayList(this.multifileFacadeShortNameId_);
                    this.bitField0_ |= 4;
                }
            }

            private void ensureMultifileFacadeShortNameIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.multifileFacadeShortName_ = new LazyStringArrayList(this.multifileFacadeShortName_);
                    this.bitField0_ |= 8;
                }
            }

            private void ensureClassWithJvmPackageNameShortNameIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.classWithJvmPackageNameShortName_ = new LazyStringArrayList(this.classWithJvmPackageNameShortName_);
                    this.bitField0_ |= 16;
                }
            }

            private void ensureClassWithJvmPackageNamePackageIdIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.classWithJvmPackageNamePackageId_ = new ArrayList(this.classWithJvmPackageNamePackageId_);
                    this.bitField0_ |= 32;
                }
            }
        }
    }
}
