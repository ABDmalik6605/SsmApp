package kotlin.reflect.jvm.internal.impl.descriptors;

/* loaded from: classes2.dex */
public interface SourceElement {
    public static final SourceElement NO_SOURCE = new SourceElement() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.SourceElement.1
        public String toString() {
            return "NO_SOURCE";
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
        public SourceFile getContainingFile() {
            return SourceFile.NO_SOURCE_FILE;
        }
    };

    SourceFile getContainingFile();
}
