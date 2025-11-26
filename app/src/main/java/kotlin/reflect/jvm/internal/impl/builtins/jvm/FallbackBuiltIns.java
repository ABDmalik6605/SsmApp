package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsInitializer;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;

/* compiled from: JvmBuiltInsSettings.kt */
/* loaded from: classes2.dex */
final class FallbackBuiltIns extends KotlinBuiltIns {
    public static final Companion Companion = new Companion(null);
    private static final BuiltInsInitializer<FallbackBuiltIns> initializer = new BuiltInsInitializer<>(new Function0<FallbackBuiltIns>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.FallbackBuiltIns$Companion$initializer$1
        @Override // kotlin.jvm.functions.Function0
        public final FallbackBuiltIns invoke() {
            return new FallbackBuiltIns(null);
        }
    });

    private FallbackBuiltIns() {
        super(new LockBasedStorageManager());
        createBuiltInsModule();
    }

    public /* synthetic */ FallbackBuiltIns(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: JvmBuiltInsSettings.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KotlinBuiltIns getInstance() {
            return FallbackBuiltIns.initializer.get();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns
    public PlatformDependentDeclarationFilter.All getPlatformDependentDeclarationFilter() {
        return PlatformDependentDeclarationFilter.All.INSTANCE;
    }
}
