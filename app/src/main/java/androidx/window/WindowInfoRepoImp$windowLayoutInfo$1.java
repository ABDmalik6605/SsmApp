package androidx.window;

import androidx.core.util.Consumer;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: WindowInfoRepoImp.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u0010\u0012\f\u0012\n \u0004*\u0004\u0018\u00010\u00030\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Landroidx/window/WindowLayoutInfo;", "kotlin.jvm.PlatformType"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.window.WindowInfoRepoImp$windowLayoutInfo$1", f = "WindowInfoRepoImp.kt", i = {}, l = {105}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class WindowInfoRepoImp$windowLayoutInfo$1 extends SuspendLambda implements Function2<ProducerScope<? super WindowLayoutInfo>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WindowInfoRepoImp this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WindowInfoRepoImp$windowLayoutInfo$1(WindowInfoRepoImp windowInfoRepoImp, Continuation<? super WindowInfoRepoImp$windowLayoutInfo$1> continuation) {
        super(2, continuation);
        this.this$0 = windowInfoRepoImp;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        WindowInfoRepoImp$windowLayoutInfo$1 windowInfoRepoImp$windowLayoutInfo$1 = new WindowInfoRepoImp$windowLayoutInfo$1(this.this$0, continuation);
        windowInfoRepoImp$windowLayoutInfo$1.L$0 = obj;
        return windowInfoRepoImp$windowLayoutInfo$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super WindowLayoutInfo> producerScope, Continuation<? super Unit> continuation) {
        return ((WindowInfoRepoImp$windowLayoutInfo$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final Consumer<WindowLayoutInfo> consumer = new Consumer() { // from class: androidx.window.WindowInfoRepoImp$windowLayoutInfo$1$callback$1
                @Override // androidx.core.util.Consumer
                public final void accept(WindowLayoutInfo windowLayoutInfo) {
                    producerScope.mo1534trySendJP2dKIU(windowLayoutInfo);
                }
            };
            this.this$0.windowBackend.registerLayoutChangeCallback(this.this$0.activity, new Executor() { // from class: androidx.window.WindowInfoRepoImp$windowLayoutInfo$1.1
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable p0) {
                    Intrinsics.checkNotNullParameter(p0, "p0");
                    p0.run();
                }
            }, consumer);
            final WindowInfoRepoImp windowInfoRepoImp = this.this$0;
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() { // from class: androidx.window.WindowInfoRepoImp$windowLayoutInfo$1.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    windowInfoRepoImp.windowBackend.unregisterLayoutChangeCallback(consumer);
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
