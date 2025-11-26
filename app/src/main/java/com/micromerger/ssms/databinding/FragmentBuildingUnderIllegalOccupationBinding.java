package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentBuildingUnderIllegalOccupationBinding implements ViewBinding {
    public final Button cancelButton;
    public final EditText editTextComments;
    public final RelativeLayout parentView;
    public final ImageView previewImage;
    public final RadioButton rbDoesTheSchoolHaveABuildingNo;
    public final RadioButton rbDoesTheSchoolHaveABuildingYes;
    public final RadioButton rbNo;
    public final RadioButton rbYes;
    public final RadioGroup rgBuilidingIllegal;
    public final RadioGroup rgSchoolBuilding;
    public final RelativeLayout rlComments;
    public final LinearLayout rlIllegalBuilding;
    public final RelativeLayout rlRamarks;
    private final RelativeLayout rootView;
    public final RelativeLayout rvRadioGroup;
    public final Button saveButton;
    public final MaterialSpinner spRemarks;
    public final TextView tvBuicTitle;
    public final TextView tvBuidlingUnderIllegalOccupation;
    public final TextView tvComments;
    public final TextView tvDoesTheSchoolHaveABuilding;
    public final TextView tvRemarks;
    public final ImageView uploadImage;

    private FragmentBuildingUnderIllegalOccupationBinding(RelativeLayout rootView, Button cancelButton, EditText editTextComments, RelativeLayout parentView, ImageView previewImage, RadioButton rbDoesTheSchoolHaveABuildingNo, RadioButton rbDoesTheSchoolHaveABuildingYes, RadioButton rbNo, RadioButton rbYes, RadioGroup rgBuilidingIllegal, RadioGroup rgSchoolBuilding, RelativeLayout rlComments, LinearLayout rlIllegalBuilding, RelativeLayout rlRamarks, RelativeLayout rvRadioGroup, Button saveButton, MaterialSpinner spRemarks, TextView tvBuicTitle, TextView tvBuidlingUnderIllegalOccupation, TextView tvComments, TextView tvDoesTheSchoolHaveABuilding, TextView tvRemarks, ImageView uploadImage) {
        this.rootView = rootView;
        this.cancelButton = cancelButton;
        this.editTextComments = editTextComments;
        this.parentView = parentView;
        this.previewImage = previewImage;
        this.rbDoesTheSchoolHaveABuildingNo = rbDoesTheSchoolHaveABuildingNo;
        this.rbDoesTheSchoolHaveABuildingYes = rbDoesTheSchoolHaveABuildingYes;
        this.rbNo = rbNo;
        this.rbYes = rbYes;
        this.rgBuilidingIllegal = rgBuilidingIllegal;
        this.rgSchoolBuilding = rgSchoolBuilding;
        this.rlComments = rlComments;
        this.rlIllegalBuilding = rlIllegalBuilding;
        this.rlRamarks = rlRamarks;
        this.rvRadioGroup = rvRadioGroup;
        this.saveButton = saveButton;
        this.spRemarks = spRemarks;
        this.tvBuicTitle = tvBuicTitle;
        this.tvBuidlingUnderIllegalOccupation = tvBuidlingUnderIllegalOccupation;
        this.tvComments = tvComments;
        this.tvDoesTheSchoolHaveABuilding = tvDoesTheSchoolHaveABuilding;
        this.tvRemarks = tvRemarks;
        this.uploadImage = uploadImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentBuildingUnderIllegalOccupationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentBuildingUnderIllegalOccupationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_building_under_illegal_occupation, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentBuildingUnderIllegalOccupationBinding bind(View rootView) {
        int i = R.id.cancel_button;
        Button button = (Button) rootView.findViewById(R.id.cancel_button);
        if (button != null) {
            i = R.id.editText_comments;
            EditText editText = (EditText) rootView.findViewById(R.id.editText_comments);
            if (editText != null) {
                RelativeLayout relativeLayout = (RelativeLayout) rootView;
                i = R.id.previewImage;
                ImageView imageView = (ImageView) rootView.findViewById(R.id.previewImage);
                if (imageView != null) {
                    i = R.id.rb_does_the_school_have_a_building_no;
                    RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_does_the_school_have_a_building_no);
                    if (radioButton != null) {
                        i = R.id.rb_does_the_school_have_a_building_yes;
                        RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_does_the_school_have_a_building_yes);
                        if (radioButton2 != null) {
                            i = R.id.rb_no;
                            RadioButton radioButton3 = (RadioButton) rootView.findViewById(R.id.rb_no);
                            if (radioButton3 != null) {
                                i = R.id.rb_yes;
                                RadioButton radioButton4 = (RadioButton) rootView.findViewById(R.id.rb_yes);
                                if (radioButton4 != null) {
                                    i = R.id.rg_builiding_illegal;
                                    RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_builiding_illegal);
                                    if (radioGroup != null) {
                                        i = R.id.rg_school_building;
                                        RadioGroup radioGroup2 = (RadioGroup) rootView.findViewById(R.id.rg_school_building);
                                        if (radioGroup2 != null) {
                                            i = R.id.rl_comments;
                                            RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.rl_comments);
                                            if (relativeLayout2 != null) {
                                                i = R.id.rl_illegal_building;
                                                LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.rl_illegal_building);
                                                if (linearLayout != null) {
                                                    i = R.id.rl_ramarks;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.rl_ramarks);
                                                    if (relativeLayout3 != null) {
                                                        i = R.id.rv_radio_group;
                                                        RelativeLayout relativeLayout4 = (RelativeLayout) rootView.findViewById(R.id.rv_radio_group);
                                                        if (relativeLayout4 != null) {
                                                            i = R.id.save_button;
                                                            Button button2 = (Button) rootView.findViewById(R.id.save_button);
                                                            if (button2 != null) {
                                                                i = R.id.sp_remarks;
                                                                MaterialSpinner materialSpinner = (MaterialSpinner) rootView.findViewById(R.id.sp_remarks);
                                                                if (materialSpinner != null) {
                                                                    i = R.id.tv_buic_title;
                                                                    TextView textView = (TextView) rootView.findViewById(R.id.tv_buic_title);
                                                                    if (textView != null) {
                                                                        i = R.id.tv_buidling_under_illegal_occupation;
                                                                        TextView textView2 = (TextView) rootView.findViewById(R.id.tv_buidling_under_illegal_occupation);
                                                                        if (textView2 != null) {
                                                                            i = R.id.tv_comments;
                                                                            TextView textView3 = (TextView) rootView.findViewById(R.id.tv_comments);
                                                                            if (textView3 != null) {
                                                                                i = R.id.tv_does_the_school_have_a_building;
                                                                                TextView textView4 = (TextView) rootView.findViewById(R.id.tv_does_the_school_have_a_building);
                                                                                if (textView4 != null) {
                                                                                    i = R.id.tv_remarks;
                                                                                    TextView textView5 = (TextView) rootView.findViewById(R.id.tv_remarks);
                                                                                    if (textView5 != null) {
                                                                                        i = R.id.uploadImage;
                                                                                        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.uploadImage);
                                                                                        if (imageView2 != null) {
                                                                                            return new FragmentBuildingUnderIllegalOccupationBinding(relativeLayout, button, editText, relativeLayout, imageView, radioButton, radioButton2, radioButton3, radioButton4, radioGroup, radioGroup2, relativeLayout2, linearLayout, relativeLayout3, relativeLayout4, button2, materialSpinner, textView, textView2, textView3, textView4, textView5, imageView2);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
