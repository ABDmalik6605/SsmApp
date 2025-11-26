package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class OfferOrderLayoutBinding implements ViewBinding {
    public final LinearLayout offerOrderLayout;
    public final ImageView offerOrderPdf;
    public final ImageView offerOrderPicture;
    public final ImageView offerOrderPictureCameraIcon;
    private final LinearLayout rootView;
    public final TextView tvOfferOrder;

    private OfferOrderLayoutBinding(LinearLayout rootView, LinearLayout offerOrderLayout, ImageView offerOrderPdf, ImageView offerOrderPicture, ImageView offerOrderPictureCameraIcon, TextView tvOfferOrder) {
        this.rootView = rootView;
        this.offerOrderLayout = offerOrderLayout;
        this.offerOrderPdf = offerOrderPdf;
        this.offerOrderPicture = offerOrderPicture;
        this.offerOrderPictureCameraIcon = offerOrderPictureCameraIcon;
        this.tvOfferOrder = tvOfferOrder;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OfferOrderLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static OfferOrderLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.offer_order_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static OfferOrderLayoutBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.offer_order_pdf;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.offer_order_pdf);
        if (imageView != null) {
            i = R.id.offer_order_picture;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.offer_order_picture);
            if (imageView2 != null) {
                i = R.id.offer_order_picture_camera_icon;
                ImageView imageView3 = (ImageView) rootView.findViewById(R.id.offer_order_picture_camera_icon);
                if (imageView3 != null) {
                    i = R.id.tv_offer_order;
                    TextView textView = (TextView) rootView.findViewById(R.id.tv_offer_order);
                    if (textView != null) {
                        return new OfferOrderLayoutBinding(linearLayout, linearLayout, imageView, imageView2, imageView3, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
