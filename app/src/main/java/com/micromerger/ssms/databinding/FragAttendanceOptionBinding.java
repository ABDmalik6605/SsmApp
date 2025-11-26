package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragAttendanceOptionBinding implements ViewBinding {
    public final ImageView attendanceIcon;
    public final ImageView imagesIcon;
    public final CardView musterRollImages;
    private final LinearLayout rootView;
    public final CardView takeAttendance;

    private FragAttendanceOptionBinding(LinearLayout rootView, ImageView attendanceIcon, ImageView imagesIcon, CardView musterRollImages, CardView takeAttendance) {
        this.rootView = rootView;
        this.attendanceIcon = attendanceIcon;
        this.imagesIcon = imagesIcon;
        this.musterRollImages = musterRollImages;
        this.takeAttendance = takeAttendance;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragAttendanceOptionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragAttendanceOptionBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_attendance_option, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragAttendanceOptionBinding bind(View rootView) {
        int i = R.id.attendanceIcon;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.attendanceIcon);
        if (imageView != null) {
            i = R.id.imagesIcon;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.imagesIcon);
            if (imageView2 != null) {
                i = R.id.musterRollImages;
                CardView cardView = (CardView) rootView.findViewById(R.id.musterRollImages);
                if (cardView != null) {
                    i = R.id.takeAttendance;
                    CardView cardView2 = (CardView) rootView.findViewById(R.id.takeAttendance);
                    if (cardView2 != null) {
                        return new FragAttendanceOptionBinding((LinearLayout) rootView, imageView, imageView2, cardView, cardView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
