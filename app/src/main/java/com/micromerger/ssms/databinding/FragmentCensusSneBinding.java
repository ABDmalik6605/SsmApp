package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentCensusSneBinding implements ViewBinding {
    public final Button cancelButton;
    public final EditText etFilled;
    public final EditText etNonTeachingFilled;
    public final EditText etNonTeachingSanctioned;
    public final EditText etNonTeachingVacant;
    public final EditText etSanctioned;
    public final EditText etVacant;
    public final RelativeLayout parentView;
    public final RadioButton rbSchoolHasApprovedSneNo;
    public final RadioButton rbSchoolHasApprovedSneYes;
    public final RadioGroup rgSneApproved;
    private final RelativeLayout rootView;
    public final RelativeLayout rvFilled;
    public final RelativeLayout rvNonTeachingFilled;
    public final RelativeLayout rvNonTeachingSanctioned;
    public final RelativeLayout rvNonTeachingVacant;
    public final RelativeLayout rvSanctioned;
    public final RelativeLayout rvSchoolHasApprovedSne;
    public final RelativeLayout rvVacant;
    public final Button saveButton;
    public final LinearLayout schoolHasApprovedSneYesLayout;
    public final TextView tvFilled;
    public final TextView tvNonTeachingFilled;
    public final TextView tvNonTeachingSanctioned;
    public final TextView tvNonTeachingVacant;
    public final TextView tvSanctioned;
    public final TextView tvSanctionedNewExpenditureTitle;
    public final TextView tvSchoolHasApprovedSne;
    public final TextView tvVacant;

    private FragmentCensusSneBinding(RelativeLayout rootView, Button cancelButton, EditText etFilled, EditText etNonTeachingFilled, EditText etNonTeachingSanctioned, EditText etNonTeachingVacant, EditText etSanctioned, EditText etVacant, RelativeLayout parentView, RadioButton rbSchoolHasApprovedSneNo, RadioButton rbSchoolHasApprovedSneYes, RadioGroup rgSneApproved, RelativeLayout rvFilled, RelativeLayout rvNonTeachingFilled, RelativeLayout rvNonTeachingSanctioned, RelativeLayout rvNonTeachingVacant, RelativeLayout rvSanctioned, RelativeLayout rvSchoolHasApprovedSne, RelativeLayout rvVacant, Button saveButton, LinearLayout schoolHasApprovedSneYesLayout, TextView tvFilled, TextView tvNonTeachingFilled, TextView tvNonTeachingSanctioned, TextView tvNonTeachingVacant, TextView tvSanctioned, TextView tvSanctionedNewExpenditureTitle, TextView tvSchoolHasApprovedSne, TextView tvVacant) {
        this.rootView = rootView;
        this.cancelButton = cancelButton;
        this.etFilled = etFilled;
        this.etNonTeachingFilled = etNonTeachingFilled;
        this.etNonTeachingSanctioned = etNonTeachingSanctioned;
        this.etNonTeachingVacant = etNonTeachingVacant;
        this.etSanctioned = etSanctioned;
        this.etVacant = etVacant;
        this.parentView = parentView;
        this.rbSchoolHasApprovedSneNo = rbSchoolHasApprovedSneNo;
        this.rbSchoolHasApprovedSneYes = rbSchoolHasApprovedSneYes;
        this.rgSneApproved = rgSneApproved;
        this.rvFilled = rvFilled;
        this.rvNonTeachingFilled = rvNonTeachingFilled;
        this.rvNonTeachingSanctioned = rvNonTeachingSanctioned;
        this.rvNonTeachingVacant = rvNonTeachingVacant;
        this.rvSanctioned = rvSanctioned;
        this.rvSchoolHasApprovedSne = rvSchoolHasApprovedSne;
        this.rvVacant = rvVacant;
        this.saveButton = saveButton;
        this.schoolHasApprovedSneYesLayout = schoolHasApprovedSneYesLayout;
        this.tvFilled = tvFilled;
        this.tvNonTeachingFilled = tvNonTeachingFilled;
        this.tvNonTeachingSanctioned = tvNonTeachingSanctioned;
        this.tvNonTeachingVacant = tvNonTeachingVacant;
        this.tvSanctioned = tvSanctioned;
        this.tvSanctionedNewExpenditureTitle = tvSanctionedNewExpenditureTitle;
        this.tvSchoolHasApprovedSne = tvSchoolHasApprovedSne;
        this.tvVacant = tvVacant;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCensusSneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCensusSneBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_sne, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCensusSneBinding bind(View rootView) {
        int i = R.id.cancel_button;
        Button button = (Button) rootView.findViewById(R.id.cancel_button);
        if (button != null) {
            i = R.id.et_filled;
            EditText editText = (EditText) rootView.findViewById(R.id.et_filled);
            if (editText != null) {
                i = R.id.et_non_teaching_filled;
                EditText editText2 = (EditText) rootView.findViewById(R.id.et_non_teaching_filled);
                if (editText2 != null) {
                    i = R.id.et_non_teaching_sanctioned;
                    EditText editText3 = (EditText) rootView.findViewById(R.id.et_non_teaching_sanctioned);
                    if (editText3 != null) {
                        i = R.id.et_non_teaching_vacant;
                        EditText editText4 = (EditText) rootView.findViewById(R.id.et_non_teaching_vacant);
                        if (editText4 != null) {
                            i = R.id.et_sanctioned;
                            EditText editText5 = (EditText) rootView.findViewById(R.id.et_sanctioned);
                            if (editText5 != null) {
                                i = R.id.et_vacant;
                                EditText editText6 = (EditText) rootView.findViewById(R.id.et_vacant);
                                if (editText6 != null) {
                                    RelativeLayout relativeLayout = (RelativeLayout) rootView;
                                    i = R.id.rb_school_has_approved_sne_no;
                                    RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_school_has_approved_sne_no);
                                    if (radioButton != null) {
                                        i = R.id.rb_school_has_approved_sne_yes;
                                        RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_school_has_approved_sne_yes);
                                        if (radioButton2 != null) {
                                            i = R.id.rg_sne_approved;
                                            RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_sne_approved);
                                            if (radioGroup != null) {
                                                i = R.id.rv_filled;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.rv_filled);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.rv_non_teaching_filled;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.rv_non_teaching_filled);
                                                    if (relativeLayout3 != null) {
                                                        i = R.id.rv_non_teaching_sanctioned;
                                                        RelativeLayout relativeLayout4 = (RelativeLayout) rootView.findViewById(R.id.rv_non_teaching_sanctioned);
                                                        if (relativeLayout4 != null) {
                                                            i = R.id.rv_non_teaching_vacant;
                                                            RelativeLayout relativeLayout5 = (RelativeLayout) rootView.findViewById(R.id.rv_non_teaching_vacant);
                                                            if (relativeLayout5 != null) {
                                                                i = R.id.rv_sanctioned;
                                                                RelativeLayout relativeLayout6 = (RelativeLayout) rootView.findViewById(R.id.rv_sanctioned);
                                                                if (relativeLayout6 != null) {
                                                                    i = R.id.rv_school_has_approved_sne;
                                                                    RelativeLayout relativeLayout7 = (RelativeLayout) rootView.findViewById(R.id.rv_school_has_approved_sne);
                                                                    if (relativeLayout7 != null) {
                                                                        i = R.id.rv_vacant;
                                                                        RelativeLayout relativeLayout8 = (RelativeLayout) rootView.findViewById(R.id.rv_vacant);
                                                                        if (relativeLayout8 != null) {
                                                                            i = R.id.save_button;
                                                                            Button button2 = (Button) rootView.findViewById(R.id.save_button);
                                                                            if (button2 != null) {
                                                                                i = R.id.school_has_approved_sne_yes_layout;
                                                                                LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.school_has_approved_sne_yes_layout);
                                                                                if (linearLayout != null) {
                                                                                    i = R.id.tv_filled;
                                                                                    TextView textView = (TextView) rootView.findViewById(R.id.tv_filled);
                                                                                    if (textView != null) {
                                                                                        i = R.id.tv_non_teaching_filled;
                                                                                        TextView textView2 = (TextView) rootView.findViewById(R.id.tv_non_teaching_filled);
                                                                                        if (textView2 != null) {
                                                                                            i = R.id.tv_non_teaching_sanctioned;
                                                                                            TextView textView3 = (TextView) rootView.findViewById(R.id.tv_non_teaching_sanctioned);
                                                                                            if (textView3 != null) {
                                                                                                i = R.id.tv_non_teaching_vacant;
                                                                                                TextView textView4 = (TextView) rootView.findViewById(R.id.tv_non_teaching_vacant);
                                                                                                if (textView4 != null) {
                                                                                                    i = R.id.tv_sanctioned;
                                                                                                    TextView textView5 = (TextView) rootView.findViewById(R.id.tv_sanctioned);
                                                                                                    if (textView5 != null) {
                                                                                                        i = R.id.tv_sanctioned_new_expenditure_title;
                                                                                                        TextView textView6 = (TextView) rootView.findViewById(R.id.tv_sanctioned_new_expenditure_title);
                                                                                                        if (textView6 != null) {
                                                                                                            i = R.id.tv_school_has_approved_sne;
                                                                                                            TextView textView7 = (TextView) rootView.findViewById(R.id.tv_school_has_approved_sne);
                                                                                                            if (textView7 != null) {
                                                                                                                i = R.id.tv_vacant;
                                                                                                                TextView textView8 = (TextView) rootView.findViewById(R.id.tv_vacant);
                                                                                                                if (textView8 != null) {
                                                                                                                    return new FragmentCensusSneBinding(relativeLayout, button, editText, editText2, editText3, editText4, editText5, editText6, relativeLayout, radioButton, radioButton2, radioGroup, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, button2, linearLayout, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
