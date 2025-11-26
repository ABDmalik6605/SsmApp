package com.micromerger.ssms.camerax.fragments;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;
import android.view.OrientationEventListener;
import android.webkit.MimeTypeMap;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.core.net.UriKt;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.FragmentKt;
import com.micromerger.ssms.R;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraFragment.kt */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/micromerger/ssms/camerax/fragments/CameraFragment$updateCameraUi$2$1$1", "Landroidx/camera/core/ImageCapture$OnImageSavedCallback;", "onError", "", "exc", "Landroidx/camera/core/ImageCaptureException;", "onImageSaved", "output", "Landroidx/camera/core/ImageCapture$OutputFileResults;", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraFragment$updateCameraUi$2$1$1 implements ImageCapture.OnImageSavedCallback {
    final /* synthetic */ File $photoFile;
    final /* synthetic */ CameraFragment this$0;

    CameraFragment$updateCameraUi$2$1$1(CameraFragment cameraFragment, File file) {
        this.this$0 = cameraFragment;
        this.$photoFile = file;
    }

    @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
    public void onError(ImageCaptureException exc) {
        Intrinsics.checkNotNullParameter(exc, "exc");
        Log.e("CameraX", Intrinsics.stringPlus("Photo capture failed: ", exc.getMessage()), exc);
        OrientationEventListener orientationEventListener = this.this$0.orientationEventListener;
        if (orientationEventListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationEventListener");
            orientationEventListener = null;
        }
        orientationEventListener.disable();
    }

    @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
    public void onImageSaved(ImageCapture.OutputFileResults output) {
        FragmentActivity activity;
        Intrinsics.checkNotNullParameter(output, "output");
        final Uri savedUri = output.getSavedUri();
        if (savedUri == null) {
            savedUri = Uri.fromFile(this.$photoFile);
        }
        Log.d("CameraX", Intrinsics.stringPlus("Photo capture succeeded: ", savedUri));
        OrientationEventListener orientationEventListener = this.this$0.orientationEventListener;
        if (orientationEventListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationEventListener");
            orientationEventListener = null;
        }
        orientationEventListener.disable();
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        Intrinsics.checkNotNullExpressionValue(savedUri, "savedUri");
        MediaScannerConnection.scanFile(this.this$0.getContext(), new String[]{UriKt.toFile(savedUri).getAbsolutePath()}, new String[]{singleton.getMimeTypeFromExtension(FilesKt.getExtension(UriKt.toFile(savedUri)))}, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$CameraFragment$updateCameraUi$2$1$1$AHsR8rhqtzFZ3Nz-NnbikTAw4Hg
            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public final void onScanCompleted(String str, Uri uri) {
                CameraFragment$updateCameraUi$2$1$1.m38onImageSaved$lambda0(str, uri);
            }
        });
        NavDestination currentDestination = FragmentKt.findNavController(this.this$0).getCurrentDestination();
        if (!(currentDestination != null && currentDestination.getId() == R.id.cameraFragment) || (activity = this.this$0.getActivity()) == null) {
            return;
        }
        final CameraFragment cameraFragment = this.this$0;
        activity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$CameraFragment$updateCameraUi$2$1$1$Lxggm5H1QdAKqtdoOnGmrInRSzs
            @Override // java.lang.Runnable
            public final void run() {
                CameraFragment$updateCameraUi$2$1$1.m39onImageSaved$lambda1(cameraFragment, savedUri);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onImageSaved$lambda-0, reason: not valid java name */
    public static final void m38onImageSaved$lambda0(String str, Uri uri) {
        Log.d("CameraX", Intrinsics.stringPlus("Image capture scanned into media store: ", uri));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onImageSaved$lambda-1, reason: not valid java name */
    public static final void m39onImageSaved$lambda1(CameraFragment this$0, Uri savedUri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NavController navControllerFindNavController = FragmentKt.findNavController(this$0);
        Intrinsics.checkNotNullExpressionValue(savedUri, "savedUri");
        navControllerFindNavController.navigate(CameraFragmentDirections.actionCameraToPhoto(UriKt.toFile(savedUri).getPath()));
    }
}
