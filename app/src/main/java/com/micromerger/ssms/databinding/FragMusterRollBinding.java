package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragMusterRollBinding implements ViewBinding {
    public final Button cancelButton;
    public final ImageView capturedImage;
    public final RelativeLayout content;
    public final RelativeLayout footer;
    public final RelativeLayout header;
    private final RelativeLayout rootView;
    public final RecyclerView rvCapturedImages;
    public final Button saveButton;
    public final TextView tvNoImages;
    public final ImageView uploadImage;

    private FragMusterRollBinding(RelativeLayout rootView, Button cancelButton, ImageView capturedImage, RelativeLayout content, RelativeLayout footer, RelativeLayout header, RecyclerView rvCapturedImages, Button saveButton, TextView tvNoImages, ImageView uploadImage) {
        this.rootView = rootView;
        this.cancelButton = cancelButton;
        this.capturedImage = capturedImage;
        this.content = content;
        this.footer = footer;
        this.header = header;
        this.rvCapturedImages = rvCapturedImages;
        this.saveButton = saveButton;
        this.tvNoImages = tvNoImages;
        this.uploadImage = uploadImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragMusterRollBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragMusterRollBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_muster_roll, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragMusterRollBinding bind(View rootView) {
        int i = R.id.cancel_button;
        Button button = (Button) rootView.findViewById(R.id.cancel_button);
        if (button != null) {
            i = R.id.capturedImage;
            ImageView imageView = (ImageView) rootView.findViewById(R.id.capturedImage);
            if (imageView != null) {
                i = R.id.content;
                RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.content);
                if (relativeLayout != null) {
                    i = R.id.footer;
                    RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.footer);
                    if (relativeLayout2 != null) {
                        i = R.id.header;
                        RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.header);
                        if (relativeLayout3 != null) {
                            i = R.id.rv_capturedImages;
                            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_capturedImages);
                            if (recyclerView != null) {
                                i = R.id.save_button;
                                Button button2 = (Button) rootView.findViewById(R.id.save_button);
                                if (button2 != null) {
                                    i = R.id.tv_noImages;
                                    TextView textView = (TextView) rootView.findViewById(R.id.tv_noImages);
                                    if (textView != null) {
                                        i = R.id.uploadImage;
                                        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.uploadImage);
                                        if (imageView2 != null) {
                                            return new FragMusterRollBinding((RelativeLayout) rootView, button, imageView, relativeLayout, relativeLayout2, relativeLayout3, recyclerView, button2, textView, imageView2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
