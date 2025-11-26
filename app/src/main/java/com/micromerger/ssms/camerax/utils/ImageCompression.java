package com.micromerger.ssms.camerax.utils;

import android.content.Context;
import com.fourmob.datetimepicker.date.SimpleMonthView;
import id.zelory.compressor.Compressor;
import id.zelory.compressor.constraint.Compression;
import id.zelory.compressor.constraint.DefaultConstraintKt;
import id.zelory.compressor.constraint.DestinationConstraintKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ImageCompression.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nJ*\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n¨\u0006\r"}, d2 = {"Lcom/micromerger/ssms/camerax/utils/ImageCompression;", "", "()V", "compressAndSaveFile", "Ljava/io/File;", "context", "Landroid/content/Context;", "inputFile", "outputFile", "quality", "", SimpleMonthView.VIEW_PARAMS_HEIGHT, "compressImage", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageCompression {
    public static final ImageCompression INSTANCE = new ImageCompression();

    private ImageCompression() {
    }

    public static /* synthetic */ File compressImage$default(ImageCompression imageCompression, Context context, File file, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i = 80;
        }
        if ((i3 & 8) != 0) {
            i2 = 480;
        }
        return imageCompression.compressImage(context, file, i, i2);
    }

    /* compiled from: ImageCompression.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.micromerger.ssms.camerax.utils.ImageCompression$compressImage$1", f = "ImageCompression.kt", i = {}, l = {19}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.micromerger.ssms.camerax.utils.ImageCompression$compressImage$1, reason: invalid class name and case insensitive filesystem */
    static final class C01521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<File> $compressedImage;
        final /* synthetic */ Context $context;
        final /* synthetic */ int $height;
        final /* synthetic */ File $inputFile;
        final /* synthetic */ int $quality;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01521(Ref.ObjectRef<File> objectRef, Context context, File file, int i, int i2, Continuation<? super C01521> continuation) {
            super(2, continuation);
            this.$compressedImage = objectRef;
            this.$context = context;
            this.$inputFile = file;
            this.$height = i;
            this.$quality = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01521(this.$compressedImage, this.$context, this.$inputFile, this.$height, this.$quality, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Ref.ObjectRef<File> objectRef;
            T t;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<File> objectRef2 = this.$compressedImage;
                Compressor compressor = Compressor.INSTANCE;
                Context context = this.$context;
                File file = this.$inputFile;
                final int i2 = this.$height;
                final int i3 = this.$quality;
                this.L$0 = objectRef2;
                this.label = 1;
                Object objCompress$default = Compressor.compress$default(compressor, context, file, null, new Function1<Compression, Unit>() { // from class: com.micromerger.ssms.camerax.utils.ImageCompression.compressImage.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Compression compression) {
                        invoke2(compression);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Compression compress) {
                        Intrinsics.checkNotNullParameter(compress, "$this$compress");
                        DefaultConstraintKt.default$default(compress, 0, i2, null, i3, 5, null);
                    }
                }, this, 4, null);
                if (objCompress$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                t = objCompress$default;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                t = obj;
            }
            objectRef.element = t;
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final File compressImage(Context context, File inputFile, int quality, int height) throws InterruptedException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(inputFile, "inputFile");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BuildersKt__BuildersKt.runBlocking$default(null, new C01521(objectRef, context, inputFile, height, quality, null), 1, null);
        return (File) objectRef.element;
    }

    /* compiled from: ImageCompression.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.micromerger.ssms.camerax.utils.ImageCompression$compressAndSaveFile$1", f = "ImageCompression.kt", i = {}, l = {38}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.micromerger.ssms.camerax.utils.ImageCompression$compressAndSaveFile$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<File> $compressedImage;
        final /* synthetic */ Context $context;
        final /* synthetic */ int $height;
        final /* synthetic */ File $inputFile;
        final /* synthetic */ File $outputFile;
        final /* synthetic */ int $quality;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.ObjectRef<File> objectRef, Context context, File file, int i, int i2, File file2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$compressedImage = objectRef;
            this.$context = context;
            this.$inputFile = file;
            this.$height = i;
            this.$quality = i2;
            this.$outputFile = file2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$compressedImage, this.$context, this.$inputFile, this.$height, this.$quality, this.$outputFile, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Ref.ObjectRef<File> objectRef;
            T t;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<File> objectRef2 = this.$compressedImage;
                Compressor compressor = Compressor.INSTANCE;
                Context context = this.$context;
                File file = this.$inputFile;
                final int i2 = this.$height;
                final int i3 = this.$quality;
                final File file2 = this.$outputFile;
                this.L$0 = objectRef2;
                this.label = 1;
                Object objCompress$default = Compressor.compress$default(compressor, context, file, null, new Function1<Compression, Unit>() { // from class: com.micromerger.ssms.camerax.utils.ImageCompression.compressAndSaveFile.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Compression compression) {
                        invoke2(compression);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Compression compress) {
                        Intrinsics.checkNotNullParameter(compress, "$this$compress");
                        DefaultConstraintKt.default$default(compress, 0, i2, null, i3, 5, null);
                        DestinationConstraintKt.destination(compress, file2);
                    }
                }, this, 4, null);
                if (objCompress$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                t = objCompress$default;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                t = obj;
            }
            objectRef.element = t;
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final File compressAndSaveFile(Context context, File inputFile, File outputFile, int quality, int height) throws InterruptedException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(inputFile, "inputFile");
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(objectRef, context, inputFile, height, quality, outputFile, null), 1, null);
        return (File) objectRef.element;
    }
}
