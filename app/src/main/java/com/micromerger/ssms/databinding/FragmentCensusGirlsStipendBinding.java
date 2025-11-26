package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentCensusGirlsStipendBinding implements ViewBinding {
    public final Button cancelButton;
    public final EditText etTotalNumberOfEligible;
    public final EditText etTotalNumberOfEnrollment;
    public final EditText etTotalNumberOfReceivedCandidates;
    public final RelativeLayout parentView;
    public final RadioButton rbReceivedGirlsStipendNa;
    public final RadioButton rbReceivedGirlsStipendNo;
    public final RadioButton rbReceivedGirlsStipendYes;
    public final RadioGroup rgReceivedGirlsStipend;
    private final RelativeLayout rootView;
    public final RelativeLayout rvReceivedGirlsStipend;
    public final RelativeLayout rvTotalNumberOfEligible;
    public final RelativeLayout rvTotalNumberOfEnrollment;
    public final RelativeLayout rvTotalNumberOfReceivedCandidates;
    public final Button saveButton;
    public final TextView tvGirlsStipendTitle;
    public final TextView tvReceivedGirlsStipend;
    public final TextView tvTotalNumberOfEligible;
    public final TextView tvTotalNumberOfEnrollment;
    public final TextView tvTotalNumberOfReceivedCandidates;

    private FragmentCensusGirlsStipendBinding(RelativeLayout rootView, Button cancelButton, EditText etTotalNumberOfEligible, EditText etTotalNumberOfEnrollment, EditText etTotalNumberOfReceivedCandidates, RelativeLayout parentView, RadioButton rbReceivedGirlsStipendNa, RadioButton rbReceivedGirlsStipendNo, RadioButton rbReceivedGirlsStipendYes, RadioGroup rgReceivedGirlsStipend, RelativeLayout rvReceivedGirlsStipend, RelativeLayout rvTotalNumberOfEligible, RelativeLayout rvTotalNumberOfEnrollment, RelativeLayout rvTotalNumberOfReceivedCandidates, Button saveButton, TextView tvGirlsStipendTitle, TextView tvReceivedGirlsStipend, TextView tvTotalNumberOfEligible, TextView tvTotalNumberOfEnrollment, TextView tvTotalNumberOfReceivedCandidates) {
        this.rootView = rootView;
        this.cancelButton = cancelButton;
        this.etTotalNumberOfEligible = etTotalNumberOfEligible;
        this.etTotalNumberOfEnrollment = etTotalNumberOfEnrollment;
        this.etTotalNumberOfReceivedCandidates = etTotalNumberOfReceivedCandidates;
        this.parentView = parentView;
        this.rbReceivedGirlsStipendNa = rbReceivedGirlsStipendNa;
        this.rbReceivedGirlsStipendNo = rbReceivedGirlsStipendNo;
        this.rbReceivedGirlsStipendYes = rbReceivedGirlsStipendYes;
        this.rgReceivedGirlsStipend = rgReceivedGirlsStipend;
        this.rvReceivedGirlsStipend = rvReceivedGirlsStipend;
        this.rvTotalNumberOfEligible = rvTotalNumberOfEligible;
        this.rvTotalNumberOfEnrollment = rvTotalNumberOfEnrollment;
        this.rvTotalNumberOfReceivedCandidates = rvTotalNumberOfReceivedCandidates;
        this.saveButton = saveButton;
        this.tvGirlsStipendTitle = tvGirlsStipendTitle;
        this.tvReceivedGirlsStipend = tvReceivedGirlsStipend;
        this.tvTotalNumberOfEligible = tvTotalNumberOfEligible;
        this.tvTotalNumberOfEnrollment = tvTotalNumberOfEnrollment;
        this.tvTotalNumberOfReceivedCandidates = tvTotalNumberOfReceivedCandidates;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCensusGirlsStipendBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCensusGirlsStipendBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_girls_stipend, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCensusGirlsStipendBinding bind(View rootView) {
        int i = R.id.cancel_button;
        Button button = (Button) rootView.findViewById(R.id.cancel_button);
        if (button != null) {
            i = R.id.et_total_number_of_eligible;
            EditText editText = (EditText) rootView.findViewById(R.id.et_total_number_of_eligible);
            if (editText != null) {
                i = R.id.et_total_number_of_enrollment;
                EditText editText2 = (EditText) rootView.findViewById(R.id.et_total_number_of_enrollment);
                if (editText2 != null) {
                    i = R.id.et_total_number_of_received_candidates;
                    EditText editText3 = (EditText) rootView.findViewById(R.id.et_total_number_of_received_candidates);
                    if (editText3 != null) {
                        RelativeLayout relativeLayout = (RelativeLayout) rootView;
                        i = R.id.rb_received_girls_stipend_na;
                        RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_received_girls_stipend_na);
                        if (radioButton != null) {
                            i = R.id.rb_received_girls_stipend_no;
                            RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_received_girls_stipend_no);
                            if (radioButton2 != null) {
                                i = R.id.rb_received_girls_stipend_yes;
                                RadioButton radioButton3 = (RadioButton) rootView.findViewById(R.id.rb_received_girls_stipend_yes);
                                if (radioButton3 != null) {
                                    i = R.id.rg_received_girls_stipend;
                                    RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_received_girls_stipend);
                                    if (radioGroup != null) {
                                        i = R.id.rv_received_girls_stipend;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.rv_received_girls_stipend);
                                        if (relativeLayout2 != null) {
                                            i = R.id.rv_total_number_of_eligible;
                                            RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.rv_total_number_of_eligible);
                                            if (relativeLayout3 != null) {
                                                i = R.id.rv_total_number_of_enrollment;
                                                RelativeLayout relativeLayout4 = (RelativeLayout) rootView.findViewById(R.id.rv_total_number_of_enrollment);
                                                if (relativeLayout4 != null) {
                                                    i = R.id.rv_total_number_of_received_candidates;
                                                    RelativeLayout relativeLayout5 = (RelativeLayout) rootView.findViewById(R.id.rv_total_number_of_received_candidates);
                                                    if (relativeLayout5 != null) {
                                                        i = R.id.save_button;
                                                        Button button2 = (Button) rootView.findViewById(R.id.save_button);
                                                        if (button2 != null) {
                                                            i = R.id.tv_girls_stipend_title;
                                                            TextView textView = (TextView) rootView.findViewById(R.id.tv_girls_stipend_title);
                                                            if (textView != null) {
                                                                i = R.id.tv_received_girls_stipend;
                                                                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_received_girls_stipend);
                                                                if (textView2 != null) {
                                                                    i = R.id.tv_total_number_of_eligible;
                                                                    TextView textView3 = (TextView) rootView.findViewById(R.id.tv_total_number_of_eligible);
                                                                    if (textView3 != null) {
                                                                        i = R.id.tv_total_number_of_enrollment;
                                                                        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_total_number_of_enrollment);
                                                                        if (textView4 != null) {
                                                                            i = R.id.tv_total_number_of_received_candidates;
                                                                            TextView textView5 = (TextView) rootView.findViewById(R.id.tv_total_number_of_received_candidates);
                                                                            if (textView5 != null) {
                                                                                return new FragmentCensusGirlsStipendBinding(relativeLayout, button, editText, editText2, editText3, relativeLayout, radioButton, radioButton2, radioButton3, radioGroup, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, button2, textView, textView2, textView3, textView4, textView5);
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
