package cn.pedant.SweetAlert;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: classes.dex */
public class Rotate3dAnimation extends Animation {
    public static final int ROLL_BY_X = 0;
    public static final int ROLL_BY_Y = 1;
    public static final int ROLL_BY_Z = 2;
    private Camera mCamera;
    private float mFromDegrees;
    private float mPivotX;
    private int mPivotXType;
    private float mPivotXValue;
    private float mPivotY;
    private int mPivotYType;
    private float mPivotYValue;
    private int mRollType;
    private float mToDegrees;

    protected static class Description {
        public int type;
        public float value;

        protected Description() {
        }
    }

    Description parseValue(TypedValue value) {
        Description description = new Description();
        if (value == null) {
            description.type = 0;
            description.value = 0.0f;
        } else {
            if (value.type == 6) {
                description.type = (value.data & 15) == 1 ? 2 : 1;
                description.value = TypedValue.complexToFloat(value.data);
                return description;
            }
            if (value.type == 4) {
                description.type = 0;
                description.value = value.getFloat();
                return description;
            }
            if (value.type >= 16 && value.type <= 31) {
                description.type = 0;
                description.value = value.data;
                return description;
            }
        }
        description.type = 0;
        description.value = 0.0f;
        return description;
    }

    public Rotate3dAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPivotXType = 0;
        this.mPivotYType = 0;
        this.mPivotXValue = 0.0f;
        this.mPivotYValue = 0.0f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.Rotate3dAnimation);
        this.mFromDegrees = typedArrayObtainStyledAttributes.getFloat(R.styleable.Rotate3dAnimation_fromDeg, 0.0f);
        this.mToDegrees = typedArrayObtainStyledAttributes.getFloat(R.styleable.Rotate3dAnimation_toDeg, 0.0f);
        this.mRollType = typedArrayObtainStyledAttributes.getInt(R.styleable.Rotate3dAnimation_rollType, 0);
        Description value = parseValue(typedArrayObtainStyledAttributes.peekValue(R.styleable.Rotate3dAnimation_pivotX));
        this.mPivotXType = value.type;
        this.mPivotXValue = value.value;
        Description value2 = parseValue(typedArrayObtainStyledAttributes.peekValue(R.styleable.Rotate3dAnimation_pivotY));
        this.mPivotYType = value2.type;
        this.mPivotYValue = value2.value;
        typedArrayObtainStyledAttributes.recycle();
        initializePivotPoint();
    }

    public Rotate3dAnimation(int rollType, float fromDegrees, float toDegrees) {
        this.mPivotXType = 0;
        this.mPivotYType = 0;
        this.mPivotXValue = 0.0f;
        this.mPivotYValue = 0.0f;
        this.mRollType = rollType;
        this.mFromDegrees = fromDegrees;
        this.mToDegrees = toDegrees;
        this.mPivotX = 0.0f;
        this.mPivotY = 0.0f;
    }

    public Rotate3dAnimation(int rollType, float fromDegrees, float toDegrees, float pivotX, float pivotY) {
        this.mPivotXType = 0;
        this.mPivotYType = 0;
        this.mPivotXValue = 0.0f;
        this.mPivotYValue = 0.0f;
        this.mRollType = rollType;
        this.mFromDegrees = fromDegrees;
        this.mToDegrees = toDegrees;
        this.mPivotXType = 0;
        this.mPivotYType = 0;
        this.mPivotXValue = pivotX;
        this.mPivotYValue = pivotY;
        initializePivotPoint();
    }

    public Rotate3dAnimation(int rollType, float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        this.mPivotXType = 0;
        this.mPivotYType = 0;
        this.mPivotXValue = 0.0f;
        this.mPivotYValue = 0.0f;
        this.mRollType = rollType;
        this.mFromDegrees = fromDegrees;
        this.mToDegrees = toDegrees;
        this.mPivotXValue = pivotXValue;
        this.mPivotXType = pivotXType;
        this.mPivotYValue = pivotYValue;
        this.mPivotYType = pivotYType;
        initializePivotPoint();
    }

    private void initializePivotPoint() {
        if (this.mPivotXType == 0) {
            this.mPivotX = this.mPivotXValue;
        }
        if (this.mPivotYType == 0) {
            this.mPivotY = this.mPivotYValue;
        }
    }

    @Override // android.view.animation.Animation
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.mCamera = new Camera();
        this.mPivotX = resolveSize(this.mPivotXType, this.mPivotXValue, width, parentWidth);
        this.mPivotY = resolveSize(this.mPivotYType, this.mPivotYValue, height, parentHeight);
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float f = this.mFromDegrees;
        float f2 = f + ((this.mToDegrees - f) * interpolatedTime);
        Matrix matrix = t.getMatrix();
        this.mCamera.save();
        int i = this.mRollType;
        if (i == 0) {
            this.mCamera.rotateX(f2);
        } else if (i == 1) {
            this.mCamera.rotateY(f2);
        } else if (i == 2) {
            this.mCamera.rotateZ(f2);
        }
        this.mCamera.getMatrix(matrix);
        this.mCamera.restore();
        matrix.preTranslate(-this.mPivotX, -this.mPivotY);
        matrix.postTranslate(this.mPivotX, this.mPivotY);
    }
}
