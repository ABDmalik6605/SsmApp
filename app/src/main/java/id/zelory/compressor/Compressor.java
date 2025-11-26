package id.zelory.compressor;

import android.content.Context;
import id.zelory.compressor.constraint.Compression;
import id.zelory.compressor.constraint.Constraint;
import id.zelory.compressor.constraint.DefaultConstraintKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: Compressor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JF\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\t2\u0019\b\u0002\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lid/zelory/compressor/Compressor;", "", "()V", "compress", "Ljava/io/File;", "context", "Landroid/content/Context;", "imageFile", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "compressionPatch", "Lkotlin/Function1;", "Lid/zelory/compressor/constraint/Compression;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/content/Context;Ljava/io/File;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "compressor_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class Compressor {
    public static final Compressor INSTANCE = new Compressor();

    private Compressor() {
    }

    public static /* synthetic */ Object compress$default(Compressor compressor, Context context, File file, CoroutineContext coroutineContext, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            coroutineContext = Dispatchers.getIO();
        }
        CoroutineContext coroutineContext2 = coroutineContext;
        if ((i & 8) != 0) {
            function1 = new Function1<Compression, Unit>() { // from class: id.zelory.compressor.Compressor.compress.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Compression compression) {
                    invoke2(compression);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Compression receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    DefaultConstraintKt.default$default(receiver, 0, 0, null, 0, 15, null);
                }
            };
        }
        return compressor.compress(context, file, coroutineContext2, function1, continuation);
    }

    /* compiled from: Compressor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Ljava/io/File;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "id.zelory.compressor.Compressor$compress$3", f = "Compressor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.zelory.compressor.Compressor$compress$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super File>, Object> {
        final /* synthetic */ Function1 $compressionPatch;
        final /* synthetic */ Context $context;
        final /* synthetic */ File $imageFile;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Function1 function1, Context context, File file, Continuation continuation) {
            super(2, continuation);
            this.$compressionPatch = function1;
            this.$context = context;
            this.$imageFile = file;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$compressionPatch, this.$context, this.$imageFile, completion);
            anonymousClass3.p$ = (CoroutineScope) obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super File> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Compression compression = new Compression();
                this.$compressionPatch.invoke(compression);
                File fileCopyToCache = UtilKt.copyToCache(this.$context, this.$imageFile);
                for (Constraint constraint : compression.getConstraints$compressor_release()) {
                    while (!constraint.isSatisfied(fileCopyToCache)) {
                        fileCopyToCache = constraint.satisfy(fileCopyToCache);
                    }
                }
                return fileCopyToCache;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object compress(Context context, File file, CoroutineContext coroutineContext, Function1<? super Compression, Unit> function1, Continuation<? super File> continuation) {
        return BuildersKt.withContext(coroutineContext, new AnonymousClass3(function1, context, file, null), continuation);
    }
}
