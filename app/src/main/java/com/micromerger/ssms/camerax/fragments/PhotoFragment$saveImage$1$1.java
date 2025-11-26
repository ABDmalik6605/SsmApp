package com.micromerger.ssms.camerax.fragments;

import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PhotoFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.micromerger.ssms.camerax.fragments.PhotoFragment$saveImage$1$1", f = "PhotoFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class PhotoFragment$saveImage$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $bitmapFile;
    final /* synthetic */ File $compressImage;
    final /* synthetic */ File $it;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PhotoFragment$saveImage$1$1(File file, File file2, File file3, Continuation<? super PhotoFragment$saveImage$1$1> continuation) {
        super(2, continuation);
        this.$compressImage = file;
        this.$it = file2;
        this.$bitmapFile = file3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PhotoFragment$saveImage$1$1(this.$compressImage, this.$it, this.$bitmapFile, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PhotoFragment$saveImage$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            this.$compressImage.delete();
            this.$it.delete();
            this.$bitmapFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }
}
