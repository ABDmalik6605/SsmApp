package androidx.window;

import android.graphics.Rect;
import androidx.exifinterface.media.ExifInterface;
import com.micromerger.ssms.utils.biometric.IBMatcherDatabase;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FoldingFeature.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 $2\u00020\u0001:\u0005$%&'(B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001f\b\u0000\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\u0013\u0010\u001d\u001a\u00020\u000f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0016R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006)"}, d2 = {"Landroidx/window/FoldingFeature;", "Landroidx/window/DisplayFeature;", "bounds", "Landroid/graphics/Rect;", "type", "Landroidx/window/FoldingFeature$Type;", "state", "Landroidx/window/FoldingFeature$State;", "(Landroid/graphics/Rect;Landroidx/window/FoldingFeature$Type;Landroidx/window/FoldingFeature$State;)V", "featureBounds", "Landroidx/window/Bounds;", "(Landroidx/window/Bounds;Landroidx/window/FoldingFeature$Type;Landroidx/window/FoldingFeature$State;)V", "getBounds", "()Landroid/graphics/Rect;", "isSeparating", "", "()Z", "occlusionType", "Landroidx/window/FoldingFeature$OcclusionType;", "getOcclusionType", "()Landroidx/window/FoldingFeature$OcclusionType;", "orientation", "Landroidx/window/FoldingFeature$Orientation;", "getOrientation", "()Landroidx/window/FoldingFeature$Orientation;", "getState", "()Landroidx/window/FoldingFeature$State;", "getType$window_release", "()Landroidx/window/FoldingFeature$Type;", "equals", "other", "", "hashCode", "", "toString", "", "Companion", "OcclusionType", ExifInterface.TAG_ORIENTATION, "State", "Type", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class FoldingFeature implements DisplayFeature {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int OCCLUSION_FULL = 1;
    public static final int OCCLUSION_NONE = 0;
    public static final int ORIENTATION_HORIZONTAL = 1;
    public static final int ORIENTATION_VERTICAL = 0;
    public static final int STATE_FLAT = 1;
    public static final int STATE_HALF_OPENED = 2;
    public static final int TYPE_FOLD = 1;
    public static final int TYPE_HINGE = 2;
    private final Bounds featureBounds;
    private final State state;
    private final Type type;

    public FoldingFeature(Bounds featureBounds, Type type, State state) {
        Intrinsics.checkNotNullParameter(featureBounds, "featureBounds");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(state, "state");
        this.featureBounds = featureBounds;
        this.type = type;
        this.state = state;
        INSTANCE.validateFeatureBounds$window_release(featureBounds);
    }

    /* renamed from: getType$window_release, reason: from getter */
    public final Type getType() {
        return this.type;
    }

    public final State getState() {
        return this.state;
    }

    /* compiled from: FoldingFeature.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/window/FoldingFeature$Type;", "", IBMatcherDatabase.COLUMN_NAME_DESCRIPTION, "", "(Ljava/lang/String;)V", "toString", "Companion", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Type {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final Type FOLD = new Type("FOLD");
        public static final Type HINGE = new Type("HINGE");
        private final String description;

        private Type(String str) {
            this.description = str;
        }

        /* renamed from: toString, reason: from getter */
        public String getDescription() {
            return this.description;
        }

        /* compiled from: FoldingFeature.kt */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/window/FoldingFeature$Type$Companion;", "", "()V", "FOLD", "Landroidx/window/FoldingFeature$Type;", "HINGE", "from", "value", "", "from$window_release", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Type from$window_release(int value) {
                if (value == 1) {
                    return Type.FOLD;
                }
                if (value == 2) {
                    return Type.HINGE;
                }
                throw new IllegalArgumentException(Intrinsics.stringPlus(FoldingFeature.class.getSimpleName(), " incorrect type value"));
            }
        }
    }

    /* compiled from: FoldingFeature.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/window/FoldingFeature$OcclusionType;", "", IBMatcherDatabase.COLUMN_NAME_DESCRIPTION, "", "(Ljava/lang/String;)V", "toString", "Companion", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class OcclusionType {
        private final String description;
        public static final OcclusionType NONE = new OcclusionType("NONE");
        public static final OcclusionType FULL = new OcclusionType("FULL");

        private OcclusionType(String str) {
            this.description = str;
        }

        /* renamed from: toString, reason: from getter */
        public String getDescription() {
            return this.description;
        }
    }

    /* compiled from: FoldingFeature.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/window/FoldingFeature$Orientation;", "", IBMatcherDatabase.COLUMN_NAME_DESCRIPTION, "", "(Ljava/lang/String;)V", "toString", "Companion", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Orientation {
        private final String description;
        public static final Orientation VERTICAL = new Orientation("VERTICAL");
        public static final Orientation HORIZONTAL = new Orientation("HORIZONTAL");

        private Orientation(String str) {
            this.description = str;
        }

        /* renamed from: toString, reason: from getter */
        public String getDescription() {
            return this.description;
        }
    }

    /* compiled from: FoldingFeature.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/window/FoldingFeature$State;", "", IBMatcherDatabase.COLUMN_NAME_DESCRIPTION, "", "(Ljava/lang/String;)V", "toString", "Companion", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class State {
        public static final State FLAT = new State("FLAT");
        public static final State HALF_OPENED = new State("HALF_OPENED");
        private final String description;

        private State(String str) {
            this.description = str;
        }

        /* renamed from: toString, reason: from getter */
        public String getDescription() {
            return this.description;
        }
    }

    @Override // androidx.window.DisplayFeature
    public Rect getBounds() {
        return this.featureBounds.toRect();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FoldingFeature(Rect bounds, Type type, State state) {
        this(new Bounds(bounds), type, state);
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(state, "state");
    }

    public final boolean isSeparating() {
        if (Intrinsics.areEqual(this.type, Type.HINGE)) {
            return true;
        }
        return Intrinsics.areEqual(this.type, Type.FOLD) && Intrinsics.areEqual(this.state, State.HALF_OPENED);
    }

    public final OcclusionType getOcclusionType() {
        if (this.featureBounds.getWidth() == 0 || this.featureBounds.getHeight() == 0) {
            return OcclusionType.NONE;
        }
        return OcclusionType.FULL;
    }

    public final Orientation getOrientation() {
        if (this.featureBounds.getWidth() > this.featureBounds.getHeight()) {
            return Orientation.HORIZONTAL;
        }
        return Orientation.VERTICAL;
    }

    public String toString() {
        return ((Object) FoldingFeature.class.getSimpleName()) + " { " + this.featureBounds + ", type=" + this.type + ", state=" + this.state + " }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other == null ? null : other.getClass())) {
            return false;
        }
        Objects.requireNonNull(other, "null cannot be cast to non-null type androidx.window.FoldingFeature");
        FoldingFeature foldingFeature = (FoldingFeature) other;
        return Intrinsics.areEqual(this.featureBounds, foldingFeature.featureBounds) && Intrinsics.areEqual(this.type, foldingFeature.type) && Intrinsics.areEqual(this.state, foldingFeature.state);
    }

    public int hashCode() {
        return (((this.featureBounds.hashCode() * 31) + this.type.hashCode()) * 31) + this.state.hashCode();
    }

    /* compiled from: FoldingFeature.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/FoldingFeature$Companion;", "", "()V", "OCCLUSION_FULL", "", "OCCLUSION_NONE", "ORIENTATION_HORIZONTAL", "ORIENTATION_VERTICAL", "STATE_FLAT", "STATE_HALF_OPENED", "TYPE_FOLD", "TYPE_HINGE", "validateFeatureBounds", "", "bounds", "Landroidx/window/Bounds;", "validateFeatureBounds$window_release", "window_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void validateFeatureBounds$window_release(Bounds bounds) {
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            if (!((bounds.getWidth() == 0 && bounds.getHeight() == 0) ? false : true)) {
                throw new IllegalArgumentException("Bounds must be non zero".toString());
            }
            if (!(bounds.getLeft() == 0 || bounds.getTop() == 0)) {
                throw new IllegalArgumentException("Bounding rectangle must start at the top or left window edge for folding features".toString());
            }
        }
    }
}
