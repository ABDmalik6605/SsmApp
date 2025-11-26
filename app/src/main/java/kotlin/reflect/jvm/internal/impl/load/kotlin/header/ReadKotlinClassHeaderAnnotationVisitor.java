package kotlin.reflect.jvm.internal.impl.load.kotlin.header;

import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmBytecodeBinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* loaded from: classes2.dex */
public class ReadKotlinClassHeaderAnnotationVisitor implements KotlinJvmBinaryClass.AnnotationVisitor {
    private static final Map<ClassId, KotlinClassHeader.Kind> HEADER_KINDS;
    private static final boolean IGNORE_OLD_METADATA = "true".equals(System.getProperty("kotlin.ignore.old.metadata"));
    private JvmMetadataVersion metadataVersion = null;
    private JvmBytecodeBinaryVersion bytecodeVersion = null;
    private String extraString = null;
    private int extraInt = 0;
    private String packageName = null;
    private String[] data = null;
    private String[] strings = null;
    private String[] incompatibleData = null;
    private KotlinClassHeader.Kind headerKind = null;

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
    public void visitEnd() {
    }

    static {
        HashMap map = new HashMap();
        HEADER_KINDS = map;
        map.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinClass")), KotlinClassHeader.Kind.CLASS);
        map.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinFileFacade")), KotlinClassHeader.Kind.FILE_FACADE);
        map.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinMultifileClass")), KotlinClassHeader.Kind.MULTIFILE_CLASS);
        map.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinMultifileClassPart")), KotlinClassHeader.Kind.MULTIFILE_CLASS_PART);
        map.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinSyntheticClass")), KotlinClassHeader.Kind.SYNTHETIC_CLASS);
    }

    public KotlinClassHeader createHeader() {
        if (this.headerKind == null) {
            return null;
        }
        if (!this.metadataVersion.isCompatible()) {
            this.incompatibleData = this.data;
        }
        JvmMetadataVersion jvmMetadataVersion = this.metadataVersion;
        if (jvmMetadataVersion == null || !jvmMetadataVersion.isCompatible()) {
            this.data = null;
        } else if (shouldHaveData() && this.data == null) {
            return null;
        }
        KotlinClassHeader.Kind kind = this.headerKind;
        JvmMetadataVersion jvmMetadataVersion2 = this.metadataVersion;
        if (jvmMetadataVersion2 == null) {
            jvmMetadataVersion2 = JvmMetadataVersion.INVALID_VERSION;
        }
        JvmMetadataVersion jvmMetadataVersion3 = jvmMetadataVersion2;
        JvmBytecodeBinaryVersion jvmBytecodeBinaryVersion = this.bytecodeVersion;
        if (jvmBytecodeBinaryVersion == null) {
            jvmBytecodeBinaryVersion = JvmBytecodeBinaryVersion.INVALID_VERSION;
        }
        return new KotlinClassHeader(kind, jvmMetadataVersion3, jvmBytecodeBinaryVersion, this.data, this.incompatibleData, this.strings, this.extraString, this.extraInt, this.packageName);
    }

    private boolean shouldHaveData() {
        return this.headerKind == KotlinClassHeader.Kind.CLASS || this.headerKind == KotlinClassHeader.Kind.FILE_FACADE || this.headerKind == KotlinClassHeader.Kind.MULTIFILE_CLASS_PART;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
    public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(ClassId classId, SourceElement sourceElement) {
        KotlinClassHeader.Kind kind;
        if (classId.asSingleFqName().equals(JvmAnnotationNames.METADATA_FQ_NAME)) {
            return new KotlinMetadataArgumentVisitor();
        }
        if (IGNORE_OLD_METADATA || this.headerKind != null || (kind = HEADER_KINDS.get(classId)) == null) {
            return null;
        }
        this.headerKind = kind;
        return new OldDeprecatedAnnotationArgumentVisitor();
    }

    private class KotlinMetadataArgumentVisitor implements KotlinJvmBinaryClass.AnnotationArgumentVisitor {
        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(Name name, ClassId classId) {
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visitEnd() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visitEnum(Name name, ClassId classId, Name name2) {
        }

        private KotlinMetadataArgumentVisitor() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visit(Name name, Object obj) {
            if (name == null) {
                return;
            }
            String strAsString = name.asString();
            if ("k".equals(strAsString)) {
                if (obj instanceof Integer) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.headerKind = KotlinClassHeader.Kind.getById(((Integer) obj).intValue());
                    return;
                }
                return;
            }
            if ("mv".equals(strAsString)) {
                if (obj instanceof int[]) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.metadataVersion = new JvmMetadataVersion((int[]) obj);
                    return;
                }
                return;
            }
            if ("bv".equals(strAsString)) {
                if (obj instanceof int[]) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.bytecodeVersion = new JvmBytecodeBinaryVersion((int[]) obj);
                    return;
                }
                return;
            }
            if ("xs".equals(strAsString)) {
                if (obj instanceof String) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.extraString = (String) obj;
                    return;
                }
                return;
            }
            if ("xi".equals(strAsString)) {
                if (obj instanceof Integer) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.extraInt = ((Integer) obj).intValue();
                    return;
                }
                return;
            }
            if ("pn".equals(strAsString) && (obj instanceof String)) {
                ReadKotlinClassHeaderAnnotationVisitor.this.packageName = (String) obj;
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(Name name) {
            String strAsString = name.asString();
            if ("d1".equals(strAsString)) {
                return dataArrayVisitor();
            }
            if ("d2".equals(strAsString)) {
                return stringsArrayVisitor();
            }
            return null;
        }

        private KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor dataArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.KotlinMetadataArgumentVisitor.1
                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.CollectStringArrayAnnotationVisitor
                protected void visitEnd(String[] strArr) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.data = strArr;
                }
            };
        }

        private KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor stringsArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.KotlinMetadataArgumentVisitor.2
                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.CollectStringArrayAnnotationVisitor
                protected void visitEnd(String[] strArr) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.strings = strArr;
                }
            };
        }
    }

    private class OldDeprecatedAnnotationArgumentVisitor implements KotlinJvmBinaryClass.AnnotationArgumentVisitor {
        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(Name name, ClassId classId) {
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visitEnd() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visitEnum(Name name, ClassId classId, Name name2) {
        }

        private OldDeprecatedAnnotationArgumentVisitor() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visit(Name name, Object obj) {
            if (name == null) {
                return;
            }
            String strAsString = name.asString();
            if ("version".equals(strAsString)) {
                if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    ReadKotlinClassHeaderAnnotationVisitor.this.metadataVersion = new JvmMetadataVersion(iArr);
                    if (ReadKotlinClassHeaderAnnotationVisitor.this.bytecodeVersion == null) {
                        ReadKotlinClassHeaderAnnotationVisitor.this.bytecodeVersion = new JvmBytecodeBinaryVersion(iArr);
                        return;
                    }
                    return;
                }
                return;
            }
            if ("multifileClassName".equals(strAsString)) {
                ReadKotlinClassHeaderAnnotationVisitor.this.extraString = obj instanceof String ? (String) obj : null;
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(Name name) {
            String strAsString = name.asString();
            if (Constants.ScionAnalytics.MessageType.DATA_MESSAGE.equals(strAsString) || "filePartClassNames".equals(strAsString)) {
                return dataArrayVisitor();
            }
            if ("strings".equals(strAsString)) {
                return stringsArrayVisitor();
            }
            return null;
        }

        private KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor dataArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.OldDeprecatedAnnotationArgumentVisitor.1
                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.CollectStringArrayAnnotationVisitor
                protected void visitEnd(String[] strArr) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.data = strArr;
                }
            };
        }

        private KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor stringsArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.OldDeprecatedAnnotationArgumentVisitor.2
                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.CollectStringArrayAnnotationVisitor
                protected void visitEnd(String[] strArr) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.strings = strArr;
                }
            };
        }
    }

    private static abstract class CollectStringArrayAnnotationVisitor implements KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor {
        private final List<String> strings = new ArrayList();

        protected abstract void visitEnd(String[] strArr);

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
        public void visitEnum(ClassId classId, Name name) {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
        public void visit(Object obj) {
            if (obj instanceof String) {
                this.strings.add((String) obj);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
        public void visitEnd() {
            List<String> list = this.strings;
            visitEnd((String[]) list.toArray(new String[list.size()]));
        }
    }
}
