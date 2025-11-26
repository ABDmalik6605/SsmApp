package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentSuccessAttendanceBinding implements ViewBinding {
    public final Button btnCancel;
    public final Button btnSave;
    public final LinearLayout firstDoseLayout;
    public final LinearLayout imageLayout;
    public final ImageView previewImage;
    public final RadioButton rbFirstYes;
    public final RadioButton rbSecondNo;
    public final RadioButton rbSecondYes;
    public final RadioGroup rgFirstDose;
    public final RadioGroup rgSecondDose;
    private final RelativeLayout rootView;
    public final ScrollView scrollView;
    public final LinearLayout secondDoseLayout;
    public final LinearLayout spinnerLayout;
    public final RelativeLayout successFragment;
    public final TextView successText;
    public final ImageView uploadImage;
    public final Spinner vaccineSpinner;

    private FragmentSuccessAttendanceBinding(RelativeLayout rootView, Button btnCancel, Button btnSave, LinearLayout firstDoseLayout, LinearLayout imageLayout, ImageView previewImage, RadioButton rbFirstYes, RadioButton rbSecondNo, RadioButton rbSecondYes, RadioGroup rgFirstDose, RadioGroup rgSecondDose, ScrollView scrollView, LinearLayout secondDoseLayout, LinearLayout spinnerLayout, RelativeLayout successFragment, TextView successText, ImageView uploadImage, Spinner vaccineSpinner) {
        this.rootView = rootView;
        this.btnCancel = btnCancel;
        this.btnSave = btnSave;
        this.firstDoseLayout = firstDoseLayout;
        this.imageLayout = imageLayout;
        this.previewImage = previewImage;
        this.rbFirstYes = rbFirstYes;
        this.rbSecondNo = rbSecondNo;
        this.rbSecondYes = rbSecondYes;
        this.rgFirstDose = rgFirstDose;
        this.rgSecondDose = rgSecondDose;
        this.scrollView = scrollView;
        this.secondDoseLayout = secondDoseLayout;
        this.spinnerLayout = spinnerLayout;
        this.successFragment = successFragment;
        this.successText = successText;
        this.uploadImage = uploadImage;
        this.vaccineSpinner = vaccineSpinner;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSuccessAttendanceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSuccessAttendanceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_success_attendance, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSuccessAttendanceBinding bind(View rootView) {
        int i = R.id.btn_cancel;
        Button button = (Button) rootView.findViewById(R.id.btn_cancel);
        if (button != null) {
            i = R.id.btn_save;
            Button button2 = (Button) rootView.findViewById(R.id.btn_save);
            if (button2 != null) {
                i = R.id.first_dose_layout;
                LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.first_dose_layout);
                if (linearLayout != null) {
                    i = R.id.image_layout;
                    LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.image_layout);
                    if (linearLayout2 != null) {
                        i = R.id.previewImage;
                        ImageView imageView = (ImageView) rootView.findViewById(R.id.previewImage);
                        if (imageView != null) {
                            i = R.id.rb_first_yes;
                            RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_first_yes);
                            if (radioButton != null) {
                                i = R.id.rb_second_no;
                                RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_second_no);
                                if (radioButton2 != null) {
                                    i = R.id.rb_second_yes;
                                    RadioButton radioButton3 = (RadioButton) rootView.findViewById(R.id.rb_second_yes);
                                    if (radioButton3 != null) {
                                        i = R.id.rg_first_dose;
                                        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_first_dose);
                                        if (radioGroup != null) {
                                            i = R.id.rg_second_dose;
                                            RadioGroup radioGroup2 = (RadioGroup) rootView.findViewById(R.id.rg_second_dose);
                                            if (radioGroup2 != null) {
                                                i = R.id.scroll_view;
                                                ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.scroll_view);
                                                if (scrollView != null) {
                                                    i = R.id.second_dose_layout;
                                                    LinearLayout linearLayout3 = (LinearLayout) rootView.findViewById(R.id.second_dose_layout);
                                                    if (linearLayout3 != null) {
                                                        i = R.id.spinner_layout;
                                                        LinearLayout linearLayout4 = (LinearLayout) rootView.findViewById(R.id.spinner_layout);
                                                        if (linearLayout4 != null) {
                                                            RelativeLayout relativeLayout = (RelativeLayout) rootView;
                                                            i = R.id.success_text;
                                                            TextView textView = (TextView) rootView.findViewById(R.id.success_text);
                                                            if (textView != null) {
                                                                i = R.id.uploadImage;
                                                                ImageView imageView2 = (ImageView) rootView.findViewById(R.id.uploadImage);
                                                                if (imageView2 != null) {
                                                                    i = R.id.vaccine_spinner;
                                                                    Spinner spinner = (Spinner) rootView.findViewById(R.id.vaccine_spinner);
                                                                    if (spinner != null) {
                                                                        return new FragmentSuccessAttendanceBinding(relativeLayout, button, button2, linearLayout, linearLayout2, imageView, radioButton, radioButton2, radioButton3, radioGroup, radioGroup2, scrollView, linearLayout3, linearLayout4, relativeLayout, textView, imageView2, spinner);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
