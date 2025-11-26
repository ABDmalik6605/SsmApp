package com.micromerger.ssms.camerax.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraState;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.ZoomState;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.Navigation;
import androidx.window.WindowManager;
import com.fourmob.datetimepicker.date.SimpleMonthView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.messaging.Constants;
import com.micromerger.ssms.R;
import com.micromerger.ssms.camerax.CameraActivity;
import com.micromerger.ssms.camerax.CameraActivityKt;
import com.micromerger.ssms.camerax.fragments.PermissionsFragment;
import com.micromerger.ssms.camerax.utils.ViewExtensionsKt;
import com.micromerger.ssms.databinding.CameraUiContainerBinding;
import com.micromerger.ssms.databinding.FragmentCameraBinding;
import com.micromerger.ssms.utils.util;
import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: CameraFragment.kt */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t*\u0002\u0012,\u0018\u0000 L2\u00020\u0001:\u0002LMB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u00100\u001a\u00020\u00102\u0006\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u0010H\u0002J\b\u00103\u001a\u000204H\u0002J\b\u00105\u001a\u00020\"H\u0002J\b\u00106\u001a\u00020\"H\u0002J\b\u00107\u001a\u00020\"H\u0002J\u0010\u00108\u001a\u0002042\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u0002042\u0006\u0010<\u001a\u00020=H\u0016J$\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010D\u001a\u0004\u0018\u00010EH\u0016J\b\u0010F\u001a\u000204H\u0016J\b\u0010G\u001a\u000204H\u0016J\u001a\u0010H\u001a\u0002042\u0006\u0010I\u001a\u00020?2\b\u0010D\u001a\u0004\u0018\u00010EH\u0017J\b\u0010J\u001a\u000204H\u0002J\b\u0010K\u001a\u000204H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0004\n\u0002\u0010-R\u000e\u0010.\u001a\u00020/X\u0082.¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lcom/micromerger/ssms/camerax/fragments/CameraFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_fragmentCameraBinding", "Lcom/micromerger/ssms/databinding/FragmentCameraBinding;", "broadcastManager", "Landroidx/localbroadcastmanager/content/LocalBroadcastManager;", "camera", "Landroidx/camera/core/Camera;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "cameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "cameraUiContainerBinding", "Lcom/micromerger/ssms/databinding/CameraUiContainerBinding;", "displayId", "", "displayListener", "com/micromerger/ssms/camerax/fragments/CameraFragment$displayListener$1", "Lcom/micromerger/ssms/camerax/fragments/CameraFragment$displayListener$1;", "displayManager", "Landroid/hardware/display/DisplayManager;", "getDisplayManager", "()Landroid/hardware/display/DisplayManager;", "displayManager$delegate", "Lkotlin/Lazy;", "fragmentCameraBinding", "getFragmentCameraBinding", "()Lcom/micromerger/ssms/databinding/FragmentCameraBinding;", "imageAnalyzer", "Landroidx/camera/core/ImageAnalysis;", "imageCapture", "Landroidx/camera/core/ImageCapture;", "isFlashOn", "", "lensFacing", "orientationEventListener", "Landroid/view/OrientationEventListener;", "outputDirectory", "Ljava/io/File;", "preview", "Landroidx/camera/core/Preview;", "rotationDegree", "volumeDownReceiver", "com/micromerger/ssms/camerax/fragments/CameraFragment$volumeDownReceiver$1", "Lcom/micromerger/ssms/camerax/fragments/CameraFragment$volumeDownReceiver$1;", "windowManager", "Landroidx/window/WindowManager;", "aspectRatio", "width", SimpleMonthView.VIEW_PARAMS_HEIGHT, "bindCameraUseCases", "", "hasBackCamera", "hasFlashUnit", "hasFrontCamera", "observeCameraState", "cameraInfo", "Landroidx/camera/core/CameraInfo;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "view", "setUpCamera", "updateCameraUi", "Companion", "LuminosityAnalyzer", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final double RATIO_16_9_VALUE = 1.7777777777777777d;
    private static final double RATIO_4_3_VALUE = 1.3333333333333333d;
    private static final String TAG = "CameraX";
    private FragmentCameraBinding _fragmentCameraBinding;
    private LocalBroadcastManager broadcastManager;
    private Camera camera;
    private ExecutorService cameraExecutor;
    private ProcessCameraProvider cameraProvider;
    private CameraUiContainerBinding cameraUiContainerBinding;
    private ImageAnalysis imageAnalyzer;
    private ImageCapture imageCapture;
    private boolean isFlashOn;
    private OrientationEventListener orientationEventListener;
    private File outputDirectory;
    private Preview preview;
    private int rotationDegree;
    private WindowManager windowManager;
    private int displayId = -1;
    private int lensFacing = 1;

    /* renamed from: displayManager$delegate, reason: from kotlin metadata */
    private final Lazy displayManager = LazyKt.lazy(new Function0<DisplayManager>() { // from class: com.micromerger.ssms.camerax.fragments.CameraFragment$displayManager$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DisplayManager invoke() {
            Object systemService = this.this$0.requireContext().getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
            return (DisplayManager) systemService;
        }
    });
    private final CameraFragment$volumeDownReceiver$1 volumeDownReceiver = new BroadcastReceiver() { // from class: com.micromerger.ssms.camerax.fragments.CameraFragment$volumeDownReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            CameraUiContainerBinding cameraUiContainerBinding;
            ImageButton imageButton;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            if (intent.getIntExtra(CameraActivityKt.KEY_EVENT_EXTRA, 0) != 25 || (cameraUiContainerBinding = this.this$0.cameraUiContainerBinding) == null || (imageButton = cameraUiContainerBinding.cameraCaptureButton) == null) {
                return;
            }
            ViewExtensionsKt.simulateClick$default(imageButton, 0L, 1, null);
        }
    };
    private final CameraFragment$displayListener$1 displayListener = new DisplayManager.DisplayListener() { // from class: com.micromerger.ssms.camerax.fragments.CameraFragment$displayListener$1
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int displayId) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int displayId) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int displayId) {
            View view = this.this$0.getView();
            if (view == null) {
                return;
            }
            CameraFragment cameraFragment = this.this$0;
            if (displayId == cameraFragment.displayId) {
                Log.d("CameraX", Intrinsics.stringPlus("Rotation changed: ", Integer.valueOf(view.getDisplay().getRotation())));
                ImageCapture imageCapture = cameraFragment.imageCapture;
                if (imageCapture != null) {
                    imageCapture.setTargetRotation(view.getDisplay().getRotation());
                }
                ImageAnalysis imageAnalysis = cameraFragment.imageAnalyzer;
                if (imageAnalysis != null) {
                    imageAnalysis.setTargetRotation(view.getDisplay().getRotation());
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    };

    /* compiled from: CameraFragment.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CameraState.Type.values().length];
            iArr[CameraState.Type.PENDING_OPEN.ordinal()] = 1;
            iArr[CameraState.Type.OPENING.ordinal()] = 2;
            iArr[CameraState.Type.OPEN.ordinal()] = 3;
            iArr[CameraState.Type.CLOSING.ordinal()] = 4;
            iArr[CameraState.Type.CLOSED.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentCameraBinding getFragmentCameraBinding() {
        FragmentCameraBinding fragmentCameraBinding = this._fragmentCameraBinding;
        Intrinsics.checkNotNull(fragmentCameraBinding);
        return fragmentCameraBinding;
    }

    private final DisplayManager getDisplayManager() {
        return (DisplayManager) this.displayManager.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        PermissionsFragment.Companion companion = PermissionsFragment.INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        if (companion.hasPermissions(contextRequireContext)) {
            return;
        }
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(CameraFragmentDirections.actionCameraToPermissions());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        OrientationEventListener orientationEventListener = null;
        this._fragmentCameraBinding = null;
        super.onDestroyView();
        ExecutorService executorService = this.cameraExecutor;
        if (executorService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraExecutor");
            executorService = null;
        }
        executorService.shutdown();
        LocalBroadcastManager localBroadcastManager = this.broadcastManager;
        if (localBroadcastManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("broadcastManager");
            localBroadcastManager = null;
        }
        localBroadcastManager.unregisterReceiver(this.volumeDownReceiver);
        getDisplayManager().unregisterDisplayListener(this.displayListener);
        OrientationEventListener orientationEventListener2 = this.orientationEventListener;
        if (orientationEventListener2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationEventListener");
        } else {
            orientationEventListener = orientationEventListener2;
        }
        orientationEventListener.disable();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._fragmentCameraBinding = FragmentCameraBinding.inflate(inflater, container, false);
        RelativeLayout root = getFragmentCameraBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "fragmentCameraBinding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        this.cameraExecutor = executorServiceNewSingleThreadExecutor;
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(view.getContext());
        Intrinsics.checkNotNullExpressionValue(localBroadcastManager, "getInstance(view.context)");
        this.broadcastManager = localBroadcastManager;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CameraActivityKt.KEY_EVENT_ACTION);
        LocalBroadcastManager localBroadcastManager2 = this.broadcastManager;
        if (localBroadcastManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("broadcastManager");
            localBroadcastManager2 = null;
        }
        localBroadcastManager2.registerReceiver(this.volumeDownReceiver, intentFilter);
        getDisplayManager().registerDisplayListener(this.displayListener, null);
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "view.context");
        this.windowManager = new WindowManager(context, null, 2, null);
        CameraActivity.Companion companion = CameraActivity.INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        this.outputDirectory = CameraActivity.Companion.getOutputDirectory$default(companion, contextRequireContext, null, 2, null);
        getFragmentCameraBinding().viewFinder.post(new Runnable() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$CameraFragment$QCG7Va6y5iNyDxdo_M_C8ctU6Cc
            @Override // java.lang.Runnable
            public final void run() {
                CameraFragment.m32onViewCreated$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-3, reason: not valid java name */
    public static final void m32onViewCreated$lambda3(final CameraFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(this$0.getContext(), new ScaleGestureDetector.OnScaleGestureListener() { // from class: com.micromerger.ssms.camerax.fragments.CameraFragment$onViewCreated$1$scaleGestureDetector$1
            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector p0) {
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector p0) {
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector p0) {
                CameraInfo cameraInfo;
                LiveData<ZoomState> zoomState;
                ZoomState value;
                Camera camera = this.this$0.camera;
                Float fValueOf = null;
                if (camera != null && (cameraInfo = camera.getCameraInfo()) != null && (zoomState = cameraInfo.getZoomState()) != null && (value = zoomState.getValue()) != null) {
                    fValueOf = Float.valueOf(value.getZoomRatio());
                }
                if (fValueOf == null || p0 == null) {
                    return true;
                }
                Camera camera2 = this.this$0.camera;
                Intrinsics.checkNotNull(camera2);
                ZoomState value2 = camera2.getCameraInfo().getZoomState().getValue();
                Intrinsics.checkNotNull(value2);
                float zoomRatio = value2.getZoomRatio() * p0.getScaleFactor();
                Camera camera3 = this.this$0.camera;
                Intrinsics.checkNotNull(camera3);
                camera3.getCameraControl().setZoomRatio(zoomRatio);
                return true;
            }
        });
        PreviewView previewView = this$0.getFragmentCameraBinding().viewFinder;
        Intrinsics.checkNotNullExpressionValue(previewView, "fragmentCameraBinding.viewFinder");
        final PreviewView previewView2 = previewView;
        if (previewView2.getMeasuredWidth() > 0 && previewView2.getMeasuredHeight() > 0) {
            this$0.getFragmentCameraBinding().viewFinder.setOnTouchListener(new CameraFragment$onViewCreated$1$1$1(this$0));
        } else {
            previewView2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.micromerger.ssms.camerax.fragments.CameraFragment$onViewCreated$lambda-3$$inlined$afterMeasured$1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (previewView2.getMeasuredWidth() <= 0 || previewView2.getMeasuredHeight() <= 0) {
                        return;
                    }
                    previewView2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    this$0.getFragmentCameraBinding().viewFinder.setOnTouchListener(new CameraFragment$onViewCreated$1$1$1(this$0));
                }
            });
        }
        this$0.displayId = this$0.getFragmentCameraBinding().viewFinder.getDisplay().getDisplayId();
        this$0.updateCameraUi();
        this$0.setUpCamera();
        final Context context = view.getContext();
        OrientationEventListener orientationEventListener = new OrientationEventListener(context) { // from class: com.micromerger.ssms.camerax.fragments.CameraFragment$onViewCreated$1$2
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int p0) {
                CameraFragment cameraFragment = this.this$0;
                int i = 1;
                if (46 <= p0 && p0 <= 135) {
                    i = 3;
                } else {
                    if (136 <= p0 && p0 <= 225) {
                        i = 2;
                    } else {
                        if (!(226 <= p0 && p0 <= 315)) {
                            i = 0;
                        }
                    }
                }
                cameraFragment.rotationDegree = i;
            }
        };
        this$0.orientationEventListener = orientationEventListener;
        OrientationEventListener orientationEventListener2 = null;
        if (orientationEventListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationEventListener");
            orientationEventListener = null;
        }
        if (orientationEventListener.canDetectOrientation()) {
            OrientationEventListener orientationEventListener3 = this$0.orientationEventListener;
            if (orientationEventListener3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationEventListener");
            } else {
                orientationEventListener2 = orientationEventListener3;
            }
            orientationEventListener2.enable();
        }
        PreviewView previewView3 = this$0.getFragmentCameraBinding().viewFinder;
        Intrinsics.checkNotNullExpressionValue(previewView3, "fragmentCameraBinding.viewFinder");
        final PreviewView previewView4 = previewView3;
        if (previewView4.getMeasuredWidth() > 0 && previewView4.getMeasuredHeight() > 0) {
            this$0.getFragmentCameraBinding().viewFinder.setOnTouchListener(new CameraFragment$onViewCreated$1$3$1(scaleGestureDetector));
        } else {
            previewView4.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.micromerger.ssms.camerax.fragments.CameraFragment$onViewCreated$lambda-3$$inlined$afterMeasured$2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (previewView4.getMeasuredWidth() <= 0 || previewView4.getMeasuredHeight() <= 0) {
                        return;
                    }
                    previewView4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    this$0.getFragmentCameraBinding().viewFinder.setOnTouchListener(new CameraFragment$onViewCreated$1$3$1(scaleGestureDetector));
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        bindCameraUseCases();
    }

    private final void setUpCamera() {
        final ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(requireContext());
        Intrinsics.checkNotNullExpressionValue(processCameraProvider, "getInstance(requireContext())");
        processCameraProvider.addListener(new Runnable() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$CameraFragment$4BHM5bcLtO9wdTo7DCKI4_ElD7Q
            @Override // java.lang.Runnable
            public final void run() {
                CameraFragment.m33setUpCamera$lambda4(this.f$0, processCameraProvider);
            }
        }, ContextCompat.getMainExecutor(requireContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: setUpCamera$lambda-4, reason: not valid java name */
    public static final void m33setUpCamera$lambda4(CameraFragment this$0, ListenableFuture cameraProviderFuture) {
        int i;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cameraProviderFuture, "$cameraProviderFuture");
        this$0.cameraProvider = (ProcessCameraProvider) cameraProviderFuture.get();
        if (this$0.hasBackCamera()) {
            i = 1;
        } else {
            if (!this$0.hasFrontCamera()) {
                throw new IllegalStateException("Back and front camera are unavailable");
            }
            i = 0;
        }
        this$0.lensFacing = i;
        this$0.bindCameraUseCases();
        CameraUiContainerBinding cameraUiContainerBinding = this$0.cameraUiContainerBinding;
        ImageButton imageButton = cameraUiContainerBinding == null ? null : cameraUiContainerBinding.cameraFlashButton;
        if (imageButton != null) {
            imageButton.setEnabled(this$0.hasFlashUnit());
        }
        if (this$0.hasFlashUnit()) {
            return;
        }
        CameraUiContainerBinding cameraUiContainerBinding2 = this$0.cameraUiContainerBinding;
        ImageButton imageButton2 = cameraUiContainerBinding2 != null ? cameraUiContainerBinding2.cameraFlashButton : null;
        if (imageButton2 == null) {
            return;
        }
        imageButton2.setVisibility(8);
    }

    private final void bindCameraUseCases() {
        WindowManager windowManager = this.windowManager;
        CameraInfo cameraInfo = null;
        if (windowManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("windowManager");
            windowManager = null;
        }
        Rect bounds = windowManager.getCurrentWindowMetrics().getBounds();
        Log.d(TAG, "Screen metrics: " + bounds.width() + " x " + bounds.height());
        int iAspectRatio = aspectRatio(bounds.width(), bounds.height());
        Log.d(TAG, Intrinsics.stringPlus("Preview aspect ratio: ", Integer.valueOf(iAspectRatio)));
        int rotation = getFragmentCameraBinding().viewFinder.getDisplay().getRotation();
        ProcessCameraProvider processCameraProvider = this.cameraProvider;
        if (processCameraProvider == null) {
            throw new IllegalStateException("Camera initialization failed.");
        }
        CameraSelector cameraSelectorBuild = new CameraSelector.Builder().requireLensFacing(this.lensFacing).build();
        Intrinsics.checkNotNullExpressionValue(cameraSelectorBuild, "Builder().requireLensFacing(lensFacing).build()");
        this.preview = new Preview.Builder().setTargetAspectRatio(iAspectRatio).setTargetRotation(rotation).build();
        this.imageCapture = new ImageCapture.Builder().setCaptureMode(1).setTargetAspectRatio(iAspectRatio).build();
        ImageAnalysis imageAnalysisBuild = new ImageAnalysis.Builder().setTargetAspectRatio(iAspectRatio).build();
        ExecutorService executorService = this.cameraExecutor;
        if (executorService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraExecutor");
            executorService = null;
        }
        imageAnalysisBuild.setAnalyzer(executorService, new LuminosityAnalyzer(new Function1<Double, Unit>() { // from class: com.micromerger.ssms.camerax.fragments.CameraFragment$bindCameraUseCases$1$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Double d) {
                invoke(d.doubleValue());
                return Unit.INSTANCE;
            }

            public final void invoke(double d) {
                Log.d("CameraX", Intrinsics.stringPlus("Average luminosity: ", Double.valueOf(d)));
            }
        }));
        Unit unit = Unit.INSTANCE;
        this.imageAnalyzer = imageAnalysisBuild;
        processCameraProvider.unbindAll();
        try {
            this.camera = processCameraProvider.bindToLifecycle(this, cameraSelectorBuild, this.preview, this.imageCapture, this.imageAnalyzer);
            Preview preview = this.preview;
            if (preview != null) {
                preview.setSurfaceProvider(getFragmentCameraBinding().viewFinder.getSurfaceProvider());
            }
            Camera camera = this.camera;
            if (camera != null) {
                cameraInfo = camera.getCameraInfo();
            }
            Intrinsics.checkNotNull(cameraInfo);
            Intrinsics.checkNotNullExpressionValue(cameraInfo, "camera?.cameraInfo!!");
            observeCameraState(cameraInfo);
        } catch (Exception e) {
            Log.e(TAG, "Use case binding failed", e);
        }
    }

    private final void observeCameraState(CameraInfo cameraInfo) {
        cameraInfo.getCameraState().observe(getViewLifecycleOwner(), new Observer() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$CameraFragment$HkoGdtPoKzF-ZPKCah1czx9CnCU
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CameraFragment.m31observeCameraState$lambda8(this.f$0, (CameraState) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: observeCameraState$lambda-8, reason: not valid java name */
    public static final void m31observeCameraState$lambda8(CameraFragment this$0, CameraState cameraState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = WhenMappings.$EnumSwitchMapping$0[cameraState.getType().ordinal()];
        if (i == 1) {
            Log.w(TAG, "observeCameraState: CameraState: Pending Open");
        } else if (i == 2) {
            Log.d(TAG, "observeCameraState: CameraState: Opening");
        } else if (i == 3) {
            Log.d(TAG, "observeCameraState: CameraState: Open");
        } else if (i == 4) {
            Log.d(TAG, "observeCameraState: CameraState: Closing");
        } else {
            if (i != 5) {
                throw new NoWhenBranchMatchedException();
            }
            Log.d(TAG, "observeCameraState: CameraState: Closed");
        }
        CameraState.StateError error = cameraState.getError();
        if (error == null) {
            return;
        }
        switch (error.getCode()) {
            case 1:
                Toast.makeText(this$0.getContext(), "Max cameras in use", 0).show();
                util.logException(new Exception("Max cameras in use"));
                return;
            case 2:
                Toast.makeText(this$0.getContext(), "Camera in use", 0).show();
                util.logException(new Exception("Camera in use"));
                return;
            case 3:
                Toast.makeText(this$0.getContext(), "Other recoverable error", 0).show();
                util.logException(new Exception("Other recoverable error"));
                return;
            case 4:
                Toast.makeText(this$0.getContext(), "Stream config error", 0).show();
                util.logException(new Exception("Stream config error"));
                return;
            case 5:
                Toast.makeText(this$0.getContext(), "Camera disabled", 0).show();
                util.logException(new Exception("Camera disabled"));
                return;
            case 6:
                Toast.makeText(this$0.getContext(), "Fatal error", 0).show();
                util.logException(new Exception("Fatal error"));
                return;
            case 7:
                Toast.makeText(this$0.getContext(), "Do not disturb mode enabled", 0).show();
                util.logException(new Exception("Do not disturb mode enabled"));
                return;
            default:
                return;
        }
    }

    private final int aspectRatio(int width, int height) {
        if (CameraActivity.INSTANCE.getSquareImage()) {
            return 0;
        }
        double dMax = Math.max(width, height) / Math.min(width, height);
        return Math.abs(dMax - RATIO_4_3_VALUE) <= Math.abs(dMax - RATIO_16_9_VALUE) ? 0 : 1;
    }

    private final void updateCameraUi() {
        ImageButton imageButton;
        ImageButton imageButton2;
        ImageButton imageButton3;
        RelativeLayout root;
        CameraUiContainerBinding cameraUiContainerBinding = this.cameraUiContainerBinding;
        if (cameraUiContainerBinding != null && (root = cameraUiContainerBinding.getRoot()) != null) {
            getFragmentCameraBinding().getRoot().removeView(root);
        }
        CameraUiContainerBinding cameraUiContainerBindingInflate = CameraUiContainerBinding.inflate(LayoutInflater.from(requireContext()), getFragmentCameraBinding().getRoot(), true);
        this.cameraUiContainerBinding = cameraUiContainerBindingInflate;
        if (cameraUiContainerBindingInflate != null && (imageButton3 = cameraUiContainerBindingInflate.cameraCaptureButton) != null) {
            imageButton3.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$CameraFragment$tKodmJJBBdgXMX-Zg9E6YjYrQLg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CameraFragment.m34updateCameraUi$lambda12(this.f$0, view);
                }
            });
        }
        CameraUiContainerBinding cameraUiContainerBinding2 = this.cameraUiContainerBinding;
        if (cameraUiContainerBinding2 != null && (imageButton2 = cameraUiContainerBinding2.cameraFlashButton) != null) {
            imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$CameraFragment$X4pamt0vwdfOG2O-bMzqkC1QYic
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CameraFragment.m35updateCameraUi$lambda13(this.f$0, view);
                }
            });
        }
        CameraUiContainerBinding cameraUiContainerBinding3 = this.cameraUiContainerBinding;
        if (cameraUiContainerBinding3 == null || (imageButton = cameraUiContainerBinding3.cameraCloseButton) == null) {
            return;
        }
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.camerax.fragments.-$$Lambda$CameraFragment$i2D-ZITNHcOS2HsAYh3e9d0WU_c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraFragment.m36updateCameraUi$lambda14(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateCameraUi$lambda-12, reason: not valid java name */
    public static final void m34updateCameraUi$lambda12(CameraFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CameraUiContainerBinding cameraUiContainerBinding = this$0.cameraUiContainerBinding;
        ExecutorService executorService = null;
        ImageButton imageButton = cameraUiContainerBinding == null ? null : cameraUiContainerBinding.cameraCaptureButton;
        if (imageButton != null) {
            imageButton.setEnabled(false);
        }
        ImageCapture imageCapture = this$0.imageCapture;
        if (imageCapture == null) {
            return;
        }
        Companion companion = INSTANCE;
        File file = this$0.outputDirectory;
        if (file == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outputDirectory");
            file = null;
        }
        File fileCreateFile = companion.createFile(file, CameraActivity.FILENAME, CameraActivity.PHOTO_EXTENSION);
        ImageCapture.Metadata metadata = new ImageCapture.Metadata();
        metadata.setReversedHorizontal(this$0.lensFacing == 0);
        ImageCapture.OutputFileOptions outputFileOptionsBuild = new ImageCapture.OutputFileOptions.Builder(fileCreateFile).setMetadata(metadata).build();
        Intrinsics.checkNotNullExpressionValue(outputFileOptionsBuild, "Builder(photoFile)\n     …                 .build()");
        imageCapture.setTargetRotation(this$0.rotationDegree);
        ExecutorService executorService2 = this$0.cameraExecutor;
        if (executorService2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraExecutor");
        } else {
            executorService = executorService2;
        }
        imageCapture.lambda$takePicture$5$ImageCapture(outputFileOptionsBuild, executorService, new CameraFragment$updateCameraUi$2$1$1(this$0, fileCreateFile));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateCameraUi$lambda-13, reason: not valid java name */
    public static final void m35updateCameraUi$lambda13(CameraFragment this$0, View view) {
        ImageButton imageButton;
        CameraControl cameraControl;
        ImageButton imageButton2;
        CameraControl cameraControl2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = !this$0.isFlashOn;
        this$0.isFlashOn = z;
        if (z) {
            Camera camera = this$0.camera;
            if (camera != null && (cameraControl2 = camera.getCameraControl()) != null) {
                cameraControl2.enableTorch(true);
            }
            CameraUiContainerBinding cameraUiContainerBinding = this$0.cameraUiContainerBinding;
            if (cameraUiContainerBinding == null || (imageButton2 = cameraUiContainerBinding.cameraFlashButton) == null) {
                return;
            }
            imageButton2.setImageResource(R.drawable.ic_flash_on);
            return;
        }
        Camera camera2 = this$0.camera;
        if (camera2 != null && (cameraControl = camera2.getCameraControl()) != null) {
            cameraControl.enableTorch(false);
        }
        CameraUiContainerBinding cameraUiContainerBinding2 = this$0.cameraUiContainerBinding;
        if (cameraUiContainerBinding2 == null || (imageButton = cameraUiContainerBinding2.cameraFlashButton) == null) {
            return;
        }
        imageButton.setImageResource(R.drawable.ic_flash_off);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateCameraUi$lambda-14, reason: not valid java name */
    public static final void m36updateCameraUi$lambda14(CameraFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            ProcessCameraProvider processCameraProvider = this$0.cameraProvider;
            if (processCameraProvider != null) {
                processCameraProvider.unbindAll();
            }
        } finally {
            FragmentActivity activity = this$0.getActivity();
            if (activity != null) {
                activity.onBackPressed();
            }
        }
    }

    private final boolean hasBackCamera() {
        ProcessCameraProvider processCameraProvider = this.cameraProvider;
        if (processCameraProvider == null) {
            return false;
        }
        return processCameraProvider.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA);
    }

    private final boolean hasFrontCamera() {
        ProcessCameraProvider processCameraProvider = this.cameraProvider;
        if (processCameraProvider == null) {
            return false;
        }
        return processCameraProvider.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA);
    }

    private final boolean hasFlashUnit() {
        CameraInfo cameraInfo;
        Camera camera = this.camera;
        if (camera == null || (cameraInfo = camera.getCameraInfo()) == null) {
            return false;
        }
        return cameraInfo.hasFlashUnit();
    }

    /* compiled from: CameraFragment.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B2\u0012+\b\u0002\u0010\u0002\u001a%\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003j\u0004\u0018\u0001`\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J-\u0010\u001b\u001a\u00020\u001c2%\u0010\u0002\u001a!\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\tJ\f\u0010\u001d\u001a\u00020\u001e*\u00020\u001fH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\\\u0010\u0015\u001aP\u0012#\u0012!\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t0\u0016j'\u0012#\u0012!\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t`\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/micromerger/ssms/camerax/fragments/CameraFragment$LuminosityAnalyzer;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "luma", "", "Lcom/micromerger/ssms/camerax/fragments/LumaListener;", "(Lkotlin/jvm/functions/Function1;)V", "frameRateWindow", "", "frameTimestamps", "Ljava/util/ArrayDeque;", "", "<set-?>", "framesPerSecond", "getFramesPerSecond", "()D", "lastAnalyzedTimestamp", "listeners", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "analyze", "image", "Landroidx/camera/core/ImageProxy;", "onFrameAnalyzed", "", "toByteArray", "", "Ljava/nio/ByteBuffer;", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final class LuminosityAnalyzer implements ImageAnalysis.Analyzer {
        private final int frameRateWindow;
        private final ArrayDeque<Long> frameTimestamps;
        private double framesPerSecond;
        private long lastAnalyzedTimestamp;
        private final ArrayList<Function1<Double, Unit>> listeners;

        public LuminosityAnalyzer() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public LuminosityAnalyzer(Function1<? super Double, Unit> function1) {
            this.frameRateWindow = 8;
            this.frameTimestamps = new ArrayDeque<>(5);
            ArrayList<Function1<Double, Unit>> arrayList = new ArrayList<>();
            if (function1 != null) {
                arrayList.add(function1);
            }
            Unit unit = Unit.INSTANCE;
            this.listeners = arrayList;
            this.framesPerSecond = -1.0d;
        }

        public /* synthetic */ LuminosityAnalyzer(Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : function1);
        }

        public final double getFramesPerSecond() {
            return this.framesPerSecond;
        }

        public final boolean onFrameAnalyzed(Function1<? super Double, Unit> listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            return this.listeners.add(listener);
        }

        private final byte[] toByteArray(ByteBuffer byteBuffer) {
            byteBuffer.rewind();
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            return bArr;
        }

        @Override // androidx.camera.core.ImageAnalysis.Analyzer
        public void analyze(ImageProxy image) {
            Intrinsics.checkNotNullParameter(image, "image");
            if (this.listeners.isEmpty()) {
                image.close();
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.frameTimestamps.push(Long.valueOf(jCurrentTimeMillis));
            while (this.frameTimestamps.size() >= this.frameRateWindow) {
                this.frameTimestamps.removeLast();
            }
            Long lPeekFirst = this.frameTimestamps.peekFirst();
            long jLongValue = lPeekFirst == null ? jCurrentTimeMillis : lPeekFirst.longValue();
            Long lPeekLast = this.frameTimestamps.peekLast();
            if (lPeekLast != null) {
                jCurrentTimeMillis = lPeekLast.longValue();
            }
            this.framesPerSecond = (1.0d / ((jLongValue - jCurrentTimeMillis) / RangesKt.coerceAtLeast(this.frameTimestamps.size(), 1))) * 1000.0d;
            Long first = this.frameTimestamps.getFirst();
            Intrinsics.checkNotNullExpressionValue(first, "frameTimestamps.first");
            this.lastAnalyzedTimestamp = first.longValue();
            int i = 0;
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
            Intrinsics.checkNotNullExpressionValue(buffer, "image.planes[0].buffer");
            byte[] byteArray = toByteArray(buffer);
            ArrayList arrayList = new ArrayList(byteArray.length);
            int length = byteArray.length;
            while (i < length) {
                byte b = byteArray[i];
                i++;
                arrayList.add(Integer.valueOf(b & 255));
            }
            double dAverageOfInt = CollectionsKt.averageOfInt(arrayList);
            Iterator<T> it = this.listeners.iterator();
            while (it.hasNext()) {
                ((Function1) it.next()).invoke(Double.valueOf(dAverageOfInt));
            }
            image.close();
        }
    }

    /* compiled from: CameraFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/micromerger/ssms/camerax/fragments/CameraFragment$Companion;", "", "()V", "RATIO_16_9_VALUE", "", "RATIO_4_3_VALUE", "TAG", "", "createFile", "Ljava/io/File;", "baseFolder", "format", "extension", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final File createFile(File baseFolder, String format, String extension) {
            Intrinsics.checkNotNullParameter(baseFolder, "baseFolder");
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(extension, "extension");
            return new File(baseFolder, Intrinsics.stringPlus(new SimpleDateFormat(format, Locale.US).format(Long.valueOf(System.currentTimeMillis())), extension));
        }
    }
}
