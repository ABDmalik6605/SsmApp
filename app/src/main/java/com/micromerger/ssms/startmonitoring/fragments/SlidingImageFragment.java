package com.micromerger.ssms.startmonitoring.fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public class SlidingImageFragment extends Fragment {
    public static int[] dashboardTutorialArray = {R.drawable.abc1, R.drawable.abc2, R.drawable.abc1, R.drawable.abc2};
    ImageView iv_tutorial;
    View rootview;

    public static SlidingImageFragment newInstance(Bundle bundle) {
        SlidingImageFragment slidingImageFragment = new SlidingImageFragment();
        slidingImageFragment.setArguments(bundle);
        return slidingImageFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_sliding_image, container, false);
        this.rootview = viewInflate;
        this.iv_tutorial = (ImageView) viewInflate.findViewById(R.id.iv_tutorial);
        return this.rootview;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            int i = getArguments().getInt("KEY_INDEX");
            this.iv_tutorial.setOnClickListener(null);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), dashboardTutorialArray[i], options);
            int i2 = options.outHeight;
            int i3 = options.outWidth;
            String str = options.outMimeType;
            Display defaultDisplay = ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            int i4 = point.x;
            int i5 = point.y;
            try {
                this.iv_tutorial.setImageResource(dashboardTutorialArray[i]);
            } catch (OutOfMemoryError unused) {
                Bitmap bitmapDecodeSampledBitmapFromResource = decodeSampledBitmapFromResource(getResources(), dashboardTutorialArray[i], i4, i5);
                if (bitmapDecodeSampledBitmapFromResource != null) {
                    this.iv_tutorial.setImageBitmap(bitmapDecodeSampledBitmapFromResource);
                }
            }
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
    }

    private void setScaledImage(final ImageView imageView, final int resId) {
        imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.SlidingImageFragment.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                int measuredHeight = imageView.getMeasuredHeight();
                imageView.setImageBitmap(SlidingImageFragment.decodeSampledBitmapFromResource(SlidingImageFragment.this.getResources(), resId, imageView.getMeasuredWidth(), measuredHeight));
                return true;
            }
        });
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, resId, options);
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeResource(res, resId, options);
        } catch (Exception | OutOfMemoryError unused) {
            return null;
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int i = options.outHeight;
        int i2 = options.outWidth;
        int i3 = 1;
        if (i > reqHeight || i2 > reqWidth) {
            int i4 = i / 2;
            int i5 = i2 / 2;
            while (i4 / i3 > reqHeight && i5 / i3 > reqWidth) {
                i3 *= 2;
            }
        }
        return i3;
    }
}
