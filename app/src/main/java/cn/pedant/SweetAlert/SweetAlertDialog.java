package cn.pedant.SweetAlert;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.util.List;

/* loaded from: classes.dex */
public class SweetAlertDialog extends Dialog implements View.OnClickListener {
    public static final int CUSTOM_IMAGE_TYPE = 4;
    public static final int ERROR_TYPE = 1;
    public static final int NORMAL_TYPE = 0;
    public static final int PROGRESS_TYPE = 5;
    public static final int SUCCESS_TYPE = 2;
    public static final int WARNING_TYPE = 3;
    private int mAlertType;
    private Button mCancelButton;
    private OnSweetClickListener mCancelClickListener;
    private String mCancelText;
    private boolean mCloseFromCancel;
    private Button mConfirmButton;
    private OnSweetClickListener mConfirmClickListener;
    private String mConfirmText;
    private String mContentText;
    private TextView mContentTextView;
    private ImageView mCustomImage;
    private Drawable mCustomImgDrawable;
    private View mDialogView;
    private FrameLayout mErrorFrame;
    private Animation mErrorInAnim;
    private ImageView mErrorX;
    private AnimationSet mErrorXInAnim;
    private AnimationSet mModalInAnim;
    private AnimationSet mModalOutAnim;
    private Animation mOverlayOutAnim;
    private FrameLayout mProgressFrame;
    private ProgressHelper mProgressHelper;
    private boolean mShowCancel;
    private boolean mShowContent;
    private Animation mSuccessBowAnim;
    private FrameLayout mSuccessFrame;
    private AnimationSet mSuccessLayoutAnimSet;
    private View mSuccessLeftMask;
    private View mSuccessRightMask;
    private SuccessTickView mSuccessTick;
    private String mTitleText;
    private TextView mTitleTextView;
    private FrameLayout mWarningFrame;

    public interface OnSweetClickListener {
        void onClick(SweetAlertDialog sweetAlertDialog);
    }

    public SweetAlertDialog(Context context) {
        this(context, 0);
    }

    public SweetAlertDialog(Context context, int alertType) {
        super(context, R.style.alert_dialog);
        setCancelable(true);
        int i = 0;
        setCanceledOnTouchOutside(false);
        this.mProgressHelper = new ProgressHelper(context);
        this.mAlertType = alertType;
        this.mErrorInAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.error_frame_in);
        this.mErrorXInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.error_x_in);
        if (Build.VERSION.SDK_INT <= 10) {
            List<Animation> animations = this.mErrorXInAnim.getAnimations();
            while (i < animations.size() && !(animations.get(i) instanceof AlphaAnimation)) {
                i++;
            }
            if (i < animations.size()) {
                animations.remove(i);
            }
        }
        this.mSuccessBowAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.success_bow_roate);
        this.mSuccessLayoutAnimSet = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.success_mask_layout);
        this.mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
        AnimationSet animationSet = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_out);
        this.mModalOutAnim = animationSet;
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: cn.pedant.SweetAlert.SweetAlertDialog.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                SweetAlertDialog.this.mDialogView.setVisibility(8);
                SweetAlertDialog.this.mDialogView.post(new Runnable() { // from class: cn.pedant.SweetAlert.SweetAlertDialog.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (SweetAlertDialog.this.mCloseFromCancel) {
                            SweetAlertDialog.super.cancel();
                        } else {
                            SweetAlertDialog.super.dismiss();
                        }
                    }
                });
            }
        });
        Animation animation = new Animation() { // from class: cn.pedant.SweetAlert.SweetAlertDialog.2
            @Override // android.view.animation.Animation
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                WindowManager.LayoutParams attributes = SweetAlertDialog.this.getWindow().getAttributes();
                attributes.alpha = 1.0f - interpolatedTime;
                SweetAlertDialog.this.getWindow().setAttributes(attributes);
            }
        };
        this.mOverlayOutAnim = animation;
        animation.setDuration(120L);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);
        this.mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        this.mTitleTextView = (TextView) findViewById(R.id.title_text);
        this.mContentTextView = (TextView) findViewById(R.id.content_text);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.error_frame);
        this.mErrorFrame = frameLayout;
        this.mErrorX = (ImageView) frameLayout.findViewById(R.id.error_x);
        this.mSuccessFrame = (FrameLayout) findViewById(R.id.success_frame);
        this.mProgressFrame = (FrameLayout) findViewById(R.id.progress_dialog);
        this.mSuccessTick = (SuccessTickView) this.mSuccessFrame.findViewById(R.id.success_tick);
        this.mSuccessLeftMask = this.mSuccessFrame.findViewById(R.id.mask_left);
        this.mSuccessRightMask = this.mSuccessFrame.findViewById(R.id.mask_right);
        this.mCustomImage = (ImageView) findViewById(R.id.custom_image);
        this.mWarningFrame = (FrameLayout) findViewById(R.id.warning_frame);
        this.mConfirmButton = (Button) findViewById(R.id.confirm_button);
        this.mCancelButton = (Button) findViewById(R.id.cancel_button);
        this.mProgressHelper.setProgressWheel((ProgressWheel) findViewById(R.id.progressWheel));
        this.mConfirmButton.setOnClickListener(this);
        this.mCancelButton.setOnClickListener(this);
        setTitleText(this.mTitleText);
        setContentText(this.mContentText);
        setCancelText(this.mCancelText);
        setConfirmText(this.mConfirmText);
        changeAlertType(this.mAlertType, true);
    }

    private void restore() {
        this.mCustomImage.setVisibility(8);
        this.mErrorFrame.setVisibility(8);
        this.mSuccessFrame.setVisibility(8);
        this.mWarningFrame.setVisibility(8);
        this.mProgressFrame.setVisibility(8);
        this.mConfirmButton.setVisibility(0);
        this.mConfirmButton.setBackgroundResource(R.drawable.blue_button_background);
        this.mErrorFrame.clearAnimation();
        this.mErrorX.clearAnimation();
        this.mSuccessTick.clearAnimation();
        this.mSuccessLeftMask.clearAnimation();
        this.mSuccessRightMask.clearAnimation();
    }

    private void playAnimation() {
        int i = this.mAlertType;
        if (i == 1) {
            this.mErrorFrame.startAnimation(this.mErrorInAnim);
            this.mErrorX.startAnimation(this.mErrorXInAnim);
        } else if (i == 2) {
            this.mSuccessTick.startTickAnim();
            this.mSuccessRightMask.startAnimation(this.mSuccessBowAnim);
        }
    }

    private void changeAlertType(int alertType, boolean fromCreate) {
        this.mAlertType = alertType;
        if (this.mDialogView != null) {
            if (!fromCreate) {
                restore();
            }
            int i = this.mAlertType;
            if (i == 1) {
                this.mErrorFrame.setVisibility(0);
            } else if (i == 2) {
                this.mSuccessFrame.setVisibility(0);
                this.mSuccessLeftMask.startAnimation(this.mSuccessLayoutAnimSet.getAnimations().get(0));
                this.mSuccessRightMask.startAnimation(this.mSuccessLayoutAnimSet.getAnimations().get(1));
            } else if (i == 3) {
                this.mConfirmButton.setBackgroundResource(R.drawable.red_button_background);
                this.mWarningFrame.setVisibility(0);
            } else if (i == 4) {
                setCustomImage(this.mCustomImgDrawable);
            } else if (i == 5) {
                this.mProgressFrame.setVisibility(0);
                this.mConfirmButton.setVisibility(8);
            }
            if (fromCreate) {
                return;
            }
            playAnimation();
        }
    }

    public int getAlerType() {
        return this.mAlertType;
    }

    public void changeAlertType(int alertType) {
        changeAlertType(alertType, false);
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    public SweetAlertDialog setTitleText(String text) {
        this.mTitleText = text;
        TextView textView = this.mTitleTextView;
        if (textView != null && text != null) {
            textView.setText(text);
        }
        return this;
    }

    public SweetAlertDialog setCustomImage(Drawable drawable) {
        this.mCustomImgDrawable = drawable;
        ImageView imageView = this.mCustomImage;
        if (imageView != null && drawable != null) {
            imageView.setVisibility(0);
            this.mCustomImage.setImageDrawable(this.mCustomImgDrawable);
        }
        return this;
    }

    public SweetAlertDialog setCustomImage(int resourceId) {
        return setCustomImage(getContext().getResources().getDrawable(resourceId));
    }

    public String getContentText() {
        return this.mContentText;
    }

    public SweetAlertDialog setContentText(String text) {
        this.mContentText = text;
        if (this.mContentTextView != null && text != null) {
            showContentText(true);
            this.mContentTextView.setText(this.mContentText);
        }
        return this;
    }

    public boolean isShowCancelButton() {
        return this.mShowCancel;
    }

    public SweetAlertDialog showCancelButton(boolean isShow) {
        this.mShowCancel = isShow;
        Button button = this.mCancelButton;
        if (button != null) {
            button.setVisibility(isShow ? 0 : 8);
        }
        return this;
    }

    public boolean isShowContentText() {
        return this.mShowContent;
    }

    public SweetAlertDialog showContentText(boolean isShow) {
        this.mShowContent = isShow;
        TextView textView = this.mContentTextView;
        if (textView != null) {
            textView.setVisibility(isShow ? 0 : 8);
        }
        return this;
    }

    public String getCancelText() {
        return this.mCancelText;
    }

    public SweetAlertDialog setCancelText(String text) {
        this.mCancelText = text;
        if (this.mCancelButton != null && text != null) {
            showCancelButton(true);
            this.mCancelButton.setText(this.mCancelText);
        }
        return this;
    }

    public String getConfirmText() {
        return this.mConfirmText;
    }

    public SweetAlertDialog setConfirmText(String text) {
        this.mConfirmText = text;
        Button button = this.mConfirmButton;
        if (button != null && text != null) {
            button.setText(text);
        }
        return this;
    }

    public SweetAlertDialog setCancelClickListener(OnSweetClickListener listener) {
        this.mCancelClickListener = listener;
        return this;
    }

    public SweetAlertDialog setConfirmClickListener(OnSweetClickListener listener) {
        this.mConfirmClickListener = listener;
        return this;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        this.mDialogView.startAnimation(this.mModalInAnim);
        playAnimation();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        dismissWithAnimation(true);
    }

    public void dismissWithAnimation() {
        dismissWithAnimation(false);
    }

    private void dismissWithAnimation(boolean fromCancel) {
        this.mCloseFromCancel = fromCancel;
        this.mConfirmButton.startAnimation(this.mOverlayOutAnim);
        this.mDialogView.startAnimation(this.mModalOutAnim);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.cancel_button) {
            OnSweetClickListener onSweetClickListener = this.mCancelClickListener;
            if (onSweetClickListener != null) {
                onSweetClickListener.onClick(this);
                return;
            } else {
                dismissWithAnimation();
                return;
            }
        }
        if (v.getId() == R.id.confirm_button) {
            OnSweetClickListener onSweetClickListener2 = this.mConfirmClickListener;
            if (onSweetClickListener2 != null) {
                onSweetClickListener2.onClick(this);
            } else {
                dismissWithAnimation();
            }
        }
    }

    public ProgressHelper getProgressHelper() {
        return this.mProgressHelper;
    }
}
