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
public final class FragmentSmcBinding implements ViewBinding {
    public final Button cancelButton;
    public final EditText etAcNumber;
    public final EditText etAcTitle;
    public final EditText etBalanceAvailableFromPreviousYear;
    public final EditText etBankName;
    public final EditText etBranchCode;
    public final EditText etComments;
    public final EditText etFundsReceivedCurrentYear;
    public final EditText etFundsUtilizedExpenditureIneligibleItems;
    public final EditText etFundsUtilizedMaintenanceOfInfrastructure;
    public final EditText etFundsUtilizedPurchaseHealthDisinfectionItems;
    public final EditText etFundsUtilizedPurchaseLearningTeachingMaterial;
    public final EditText etNameOfBranch;
    public final EditText etTotalFundsUtilizedCurrentYear;
    public final RelativeLayout functionalLayout;
    public final RelativeLayout fundsLayout;
    public final RelativeLayout parentView;
    public final RadioButton rbFunctionalNo;
    public final RadioButton rbFunctionalYes;
    public final RadioButton rbFundsNo;
    public final RadioButton rbFundsYes;
    public final RelativeLayout remarksLayout;
    public final RadioGroup rgFunctional;
    public final RadioGroup rgFunds;
    private final RelativeLayout rootView;
    public final RelativeLayout rvBankName;
    public final RelativeLayout rvBranchCode;
    public final RelativeLayout rvNameOfBranch;
    public final RelativeLayout rvSmcACNumber;
    public final RelativeLayout rvSmcACTitle;
    public final Button saveButton;
    public final RelativeLayout smcBalancePreviousYearLayout;
    public final RelativeLayout smcFundsCurrentYearLayout;
    public final RelativeLayout smcFundsUtilizedMaintenanceLayout;
    public final RelativeLayout smcFundsUtilizedOtherLayout;
    public final RelativeLayout smcFundsUtilizedPurchaseLearningLayout;
    public final RelativeLayout smcFundsUtilizedPurchaseWashableLayout;
    public final RelativeLayout smcTotalFundsUtilizedLayout;
    public final TextView tvBankName;
    public final TextView tvBranchCode;
    public final TextView tvCensusWidget;
    public final TextView tvFunctional;
    public final TextView tvFunds;
    public final TextView tvNameOfBranch;
    public final TextView tvRemarks;
    public final TextView tvSmcACNumber;
    public final TextView tvSmcACTitle;
    public final TextView tvSmcBalancePreviousYear;
    public final TextView tvSmcFundsCurrentYear;
    public final TextView tvSmcFundsUtilizedMaintenance;
    public final TextView tvSmcFundsUtilizedOther;
    public final TextView tvSmcFundsUtilizedPurchaseLearning;
    public final TextView tvSmcFundsUtilizedPurchaseWashable;
    public final TextView tvSmcTotalFundsUtilized;

    private FragmentSmcBinding(RelativeLayout rootView, Button cancelButton, EditText etAcNumber, EditText etAcTitle, EditText etBalanceAvailableFromPreviousYear, EditText etBankName, EditText etBranchCode, EditText etComments, EditText etFundsReceivedCurrentYear, EditText etFundsUtilizedExpenditureIneligibleItems, EditText etFundsUtilizedMaintenanceOfInfrastructure, EditText etFundsUtilizedPurchaseHealthDisinfectionItems, EditText etFundsUtilizedPurchaseLearningTeachingMaterial, EditText etNameOfBranch, EditText etTotalFundsUtilizedCurrentYear, RelativeLayout functionalLayout, RelativeLayout fundsLayout, RelativeLayout parentView, RadioButton rbFunctionalNo, RadioButton rbFunctionalYes, RadioButton rbFundsNo, RadioButton rbFundsYes, RelativeLayout remarksLayout, RadioGroup rgFunctional, RadioGroup rgFunds, RelativeLayout rvBankName, RelativeLayout rvBranchCode, RelativeLayout rvNameOfBranch, RelativeLayout rvSmcACNumber, RelativeLayout rvSmcACTitle, Button saveButton, RelativeLayout smcBalancePreviousYearLayout, RelativeLayout smcFundsCurrentYearLayout, RelativeLayout smcFundsUtilizedMaintenanceLayout, RelativeLayout smcFundsUtilizedOtherLayout, RelativeLayout smcFundsUtilizedPurchaseLearningLayout, RelativeLayout smcFundsUtilizedPurchaseWashableLayout, RelativeLayout smcTotalFundsUtilizedLayout, TextView tvBankName, TextView tvBranchCode, TextView tvCensusWidget, TextView tvFunctional, TextView tvFunds, TextView tvNameOfBranch, TextView tvRemarks, TextView tvSmcACNumber, TextView tvSmcACTitle, TextView tvSmcBalancePreviousYear, TextView tvSmcFundsCurrentYear, TextView tvSmcFundsUtilizedMaintenance, TextView tvSmcFundsUtilizedOther, TextView tvSmcFundsUtilizedPurchaseLearning, TextView tvSmcFundsUtilizedPurchaseWashable, TextView tvSmcTotalFundsUtilized) {
        this.rootView = rootView;
        this.cancelButton = cancelButton;
        this.etAcNumber = etAcNumber;
        this.etAcTitle = etAcTitle;
        this.etBalanceAvailableFromPreviousYear = etBalanceAvailableFromPreviousYear;
        this.etBankName = etBankName;
        this.etBranchCode = etBranchCode;
        this.etComments = etComments;
        this.etFundsReceivedCurrentYear = etFundsReceivedCurrentYear;
        this.etFundsUtilizedExpenditureIneligibleItems = etFundsUtilizedExpenditureIneligibleItems;
        this.etFundsUtilizedMaintenanceOfInfrastructure = etFundsUtilizedMaintenanceOfInfrastructure;
        this.etFundsUtilizedPurchaseHealthDisinfectionItems = etFundsUtilizedPurchaseHealthDisinfectionItems;
        this.etFundsUtilizedPurchaseLearningTeachingMaterial = etFundsUtilizedPurchaseLearningTeachingMaterial;
        this.etNameOfBranch = etNameOfBranch;
        this.etTotalFundsUtilizedCurrentYear = etTotalFundsUtilizedCurrentYear;
        this.functionalLayout = functionalLayout;
        this.fundsLayout = fundsLayout;
        this.parentView = parentView;
        this.rbFunctionalNo = rbFunctionalNo;
        this.rbFunctionalYes = rbFunctionalYes;
        this.rbFundsNo = rbFundsNo;
        this.rbFundsYes = rbFundsYes;
        this.remarksLayout = remarksLayout;
        this.rgFunctional = rgFunctional;
        this.rgFunds = rgFunds;
        this.rvBankName = rvBankName;
        this.rvBranchCode = rvBranchCode;
        this.rvNameOfBranch = rvNameOfBranch;
        this.rvSmcACNumber = rvSmcACNumber;
        this.rvSmcACTitle = rvSmcACTitle;
        this.saveButton = saveButton;
        this.smcBalancePreviousYearLayout = smcBalancePreviousYearLayout;
        this.smcFundsCurrentYearLayout = smcFundsCurrentYearLayout;
        this.smcFundsUtilizedMaintenanceLayout = smcFundsUtilizedMaintenanceLayout;
        this.smcFundsUtilizedOtherLayout = smcFundsUtilizedOtherLayout;
        this.smcFundsUtilizedPurchaseLearningLayout = smcFundsUtilizedPurchaseLearningLayout;
        this.smcFundsUtilizedPurchaseWashableLayout = smcFundsUtilizedPurchaseWashableLayout;
        this.smcTotalFundsUtilizedLayout = smcTotalFundsUtilizedLayout;
        this.tvBankName = tvBankName;
        this.tvBranchCode = tvBranchCode;
        this.tvCensusWidget = tvCensusWidget;
        this.tvFunctional = tvFunctional;
        this.tvFunds = tvFunds;
        this.tvNameOfBranch = tvNameOfBranch;
        this.tvRemarks = tvRemarks;
        this.tvSmcACNumber = tvSmcACNumber;
        this.tvSmcACTitle = tvSmcACTitle;
        this.tvSmcBalancePreviousYear = tvSmcBalancePreviousYear;
        this.tvSmcFundsCurrentYear = tvSmcFundsCurrentYear;
        this.tvSmcFundsUtilizedMaintenance = tvSmcFundsUtilizedMaintenance;
        this.tvSmcFundsUtilizedOther = tvSmcFundsUtilizedOther;
        this.tvSmcFundsUtilizedPurchaseLearning = tvSmcFundsUtilizedPurchaseLearning;
        this.tvSmcFundsUtilizedPurchaseWashable = tvSmcFundsUtilizedPurchaseWashable;
        this.tvSmcTotalFundsUtilized = tvSmcTotalFundsUtilized;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSmcBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSmcBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_smc, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSmcBinding bind(View rootView) {
        int i = R.id.cancel_button;
        Button button = (Button) rootView.findViewById(R.id.cancel_button);
        if (button != null) {
            i = R.id.et_ac_number;
            EditText editText = (EditText) rootView.findViewById(R.id.et_ac_number);
            if (editText != null) {
                i = R.id.et_ac_title;
                EditText editText2 = (EditText) rootView.findViewById(R.id.et_ac_title);
                if (editText2 != null) {
                    i = R.id.et_balance_available_from_previous_year;
                    EditText editText3 = (EditText) rootView.findViewById(R.id.et_balance_available_from_previous_year);
                    if (editText3 != null) {
                        i = R.id.et_bank_name;
                        EditText editText4 = (EditText) rootView.findViewById(R.id.et_bank_name);
                        if (editText4 != null) {
                            i = R.id.et_branch_code;
                            EditText editText5 = (EditText) rootView.findViewById(R.id.et_branch_code);
                            if (editText5 != null) {
                                i = R.id.et_comments;
                                EditText editText6 = (EditText) rootView.findViewById(R.id.et_comments);
                                if (editText6 != null) {
                                    i = R.id.et_funds_received_current_year;
                                    EditText editText7 = (EditText) rootView.findViewById(R.id.et_funds_received_current_year);
                                    if (editText7 != null) {
                                        i = R.id.et_funds_utilized_expenditure_ineligible_items;
                                        EditText editText8 = (EditText) rootView.findViewById(R.id.et_funds_utilized_expenditure_ineligible_items);
                                        if (editText8 != null) {
                                            i = R.id.et_funds_utilized_maintenance_of_infrastructure;
                                            EditText editText9 = (EditText) rootView.findViewById(R.id.et_funds_utilized_maintenance_of_infrastructure);
                                            if (editText9 != null) {
                                                i = R.id.et_funds_utilized_purchase_health_disinfection_items;
                                                EditText editText10 = (EditText) rootView.findViewById(R.id.et_funds_utilized_purchase_health_disinfection_items);
                                                if (editText10 != null) {
                                                    i = R.id.et_funds_utilized_purchase_learning_teaching_material;
                                                    EditText editText11 = (EditText) rootView.findViewById(R.id.et_funds_utilized_purchase_learning_teaching_material);
                                                    if (editText11 != null) {
                                                        i = R.id.et_name_of_branch;
                                                        EditText editText12 = (EditText) rootView.findViewById(R.id.et_name_of_branch);
                                                        if (editText12 != null) {
                                                            i = R.id.et_total_funds_utilized_current_year;
                                                            EditText editText13 = (EditText) rootView.findViewById(R.id.et_total_funds_utilized_current_year);
                                                            if (editText13 != null) {
                                                                i = R.id.functionalLayout;
                                                                RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.functionalLayout);
                                                                if (relativeLayout != null) {
                                                                    i = R.id.fundsLayout;
                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.fundsLayout);
                                                                    if (relativeLayout2 != null) {
                                                                        RelativeLayout relativeLayout3 = (RelativeLayout) rootView;
                                                                        i = R.id.rb_functional_no;
                                                                        RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_functional_no);
                                                                        if (radioButton != null) {
                                                                            i = R.id.rb_functional_yes;
                                                                            RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_functional_yes);
                                                                            if (radioButton2 != null) {
                                                                                i = R.id.rb_funds_no;
                                                                                RadioButton radioButton3 = (RadioButton) rootView.findViewById(R.id.rb_funds_no);
                                                                                if (radioButton3 != null) {
                                                                                    i = R.id.rb_funds_yes;
                                                                                    RadioButton radioButton4 = (RadioButton) rootView.findViewById(R.id.rb_funds_yes);
                                                                                    if (radioButton4 != null) {
                                                                                        i = R.id.remarks_layout;
                                                                                        RelativeLayout relativeLayout4 = (RelativeLayout) rootView.findViewById(R.id.remarks_layout);
                                                                                        if (relativeLayout4 != null) {
                                                                                            i = R.id.rg_functional;
                                                                                            RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_functional);
                                                                                            if (radioGroup != null) {
                                                                                                i = R.id.rg_funds;
                                                                                                RadioGroup radioGroup2 = (RadioGroup) rootView.findViewById(R.id.rg_funds);
                                                                                                if (radioGroup2 != null) {
                                                                                                    i = R.id.rv_bank_name;
                                                                                                    RelativeLayout relativeLayout5 = (RelativeLayout) rootView.findViewById(R.id.rv_bank_name);
                                                                                                    if (relativeLayout5 != null) {
                                                                                                        i = R.id.rv_branch_code;
                                                                                                        RelativeLayout relativeLayout6 = (RelativeLayout) rootView.findViewById(R.id.rv_branch_code);
                                                                                                        if (relativeLayout6 != null) {
                                                                                                            i = R.id.rv_name_of_branch;
                                                                                                            RelativeLayout relativeLayout7 = (RelativeLayout) rootView.findViewById(R.id.rv_name_of_branch);
                                                                                                            if (relativeLayout7 != null) {
                                                                                                                i = R.id.rv_smc_a_c_number;
                                                                                                                RelativeLayout relativeLayout8 = (RelativeLayout) rootView.findViewById(R.id.rv_smc_a_c_number);
                                                                                                                if (relativeLayout8 != null) {
                                                                                                                    i = R.id.rv_smc_a_c_title;
                                                                                                                    RelativeLayout relativeLayout9 = (RelativeLayout) rootView.findViewById(R.id.rv_smc_a_c_title);
                                                                                                                    if (relativeLayout9 != null) {
                                                                                                                        i = R.id.save_button;
                                                                                                                        Button button2 = (Button) rootView.findViewById(R.id.save_button);
                                                                                                                        if (button2 != null) {
                                                                                                                            i = R.id.smc_balance_previous_year_layout;
                                                                                                                            RelativeLayout relativeLayout10 = (RelativeLayout) rootView.findViewById(R.id.smc_balance_previous_year_layout);
                                                                                                                            if (relativeLayout10 != null) {
                                                                                                                                i = R.id.smc_funds_current_year_layout;
                                                                                                                                RelativeLayout relativeLayout11 = (RelativeLayout) rootView.findViewById(R.id.smc_funds_current_year_layout);
                                                                                                                                if (relativeLayout11 != null) {
                                                                                                                                    i = R.id.smc_funds_utilized_maintenance_layout;
                                                                                                                                    RelativeLayout relativeLayout12 = (RelativeLayout) rootView.findViewById(R.id.smc_funds_utilized_maintenance_layout);
                                                                                                                                    if (relativeLayout12 != null) {
                                                                                                                                        i = R.id.smc_funds_utilized_other_layout;
                                                                                                                                        RelativeLayout relativeLayout13 = (RelativeLayout) rootView.findViewById(R.id.smc_funds_utilized_other_layout);
                                                                                                                                        if (relativeLayout13 != null) {
                                                                                                                                            i = R.id.smc_funds_utilized_purchase_learning_layout;
                                                                                                                                            RelativeLayout relativeLayout14 = (RelativeLayout) rootView.findViewById(R.id.smc_funds_utilized_purchase_learning_layout);
                                                                                                                                            if (relativeLayout14 != null) {
                                                                                                                                                i = R.id.smc_funds_utilized_purchase_washable_layout;
                                                                                                                                                RelativeLayout relativeLayout15 = (RelativeLayout) rootView.findViewById(R.id.smc_funds_utilized_purchase_washable_layout);
                                                                                                                                                if (relativeLayout15 != null) {
                                                                                                                                                    i = R.id.smc_total_funds_utilized_layout;
                                                                                                                                                    RelativeLayout relativeLayout16 = (RelativeLayout) rootView.findViewById(R.id.smc_total_funds_utilized_layout);
                                                                                                                                                    if (relativeLayout16 != null) {
                                                                                                                                                        i = R.id.tv_bank_name;
                                                                                                                                                        TextView textView = (TextView) rootView.findViewById(R.id.tv_bank_name);
                                                                                                                                                        if (textView != null) {
                                                                                                                                                            i = R.id.tv_branch_code;
                                                                                                                                                            TextView textView2 = (TextView) rootView.findViewById(R.id.tv_branch_code);
                                                                                                                                                            if (textView2 != null) {
                                                                                                                                                                i = R.id.tv_census_widget;
                                                                                                                                                                TextView textView3 = (TextView) rootView.findViewById(R.id.tv_census_widget);
                                                                                                                                                                if (textView3 != null) {
                                                                                                                                                                    i = R.id.tv_functional;
                                                                                                                                                                    TextView textView4 = (TextView) rootView.findViewById(R.id.tv_functional);
                                                                                                                                                                    if (textView4 != null) {
                                                                                                                                                                        i = R.id.tv_funds;
                                                                                                                                                                        TextView textView5 = (TextView) rootView.findViewById(R.id.tv_funds);
                                                                                                                                                                        if (textView5 != null) {
                                                                                                                                                                            i = R.id.tv_name_of_branch;
                                                                                                                                                                            TextView textView6 = (TextView) rootView.findViewById(R.id.tv_name_of_branch);
                                                                                                                                                                            if (textView6 != null) {
                                                                                                                                                                                i = R.id.tv_remarks;
                                                                                                                                                                                TextView textView7 = (TextView) rootView.findViewById(R.id.tv_remarks);
                                                                                                                                                                                if (textView7 != null) {
                                                                                                                                                                                    i = R.id.tv_smc_a_c_number;
                                                                                                                                                                                    TextView textView8 = (TextView) rootView.findViewById(R.id.tv_smc_a_c_number);
                                                                                                                                                                                    if (textView8 != null) {
                                                                                                                                                                                        i = R.id.tv_smc_a_c_title;
                                                                                                                                                                                        TextView textView9 = (TextView) rootView.findViewById(R.id.tv_smc_a_c_title);
                                                                                                                                                                                        if (textView9 != null) {
                                                                                                                                                                                            i = R.id.tv_smc_balance_previous_year;
                                                                                                                                                                                            TextView textView10 = (TextView) rootView.findViewById(R.id.tv_smc_balance_previous_year);
                                                                                                                                                                                            if (textView10 != null) {
                                                                                                                                                                                                i = R.id.tv_smc_funds_current_year;
                                                                                                                                                                                                TextView textView11 = (TextView) rootView.findViewById(R.id.tv_smc_funds_current_year);
                                                                                                                                                                                                if (textView11 != null) {
                                                                                                                                                                                                    i = R.id.tv_smc_funds_utilized_maintenance;
                                                                                                                                                                                                    TextView textView12 = (TextView) rootView.findViewById(R.id.tv_smc_funds_utilized_maintenance);
                                                                                                                                                                                                    if (textView12 != null) {
                                                                                                                                                                                                        i = R.id.tv_smc_funds_utilized_other;
                                                                                                                                                                                                        TextView textView13 = (TextView) rootView.findViewById(R.id.tv_smc_funds_utilized_other);
                                                                                                                                                                                                        if (textView13 != null) {
                                                                                                                                                                                                            i = R.id.tv_smc_funds_utilized_purchase_learning;
                                                                                                                                                                                                            TextView textView14 = (TextView) rootView.findViewById(R.id.tv_smc_funds_utilized_purchase_learning);
                                                                                                                                                                                                            if (textView14 != null) {
                                                                                                                                                                                                                i = R.id.tv_smc_funds_utilized_purchase_washable;
                                                                                                                                                                                                                TextView textView15 = (TextView) rootView.findViewById(R.id.tv_smc_funds_utilized_purchase_washable);
                                                                                                                                                                                                                if (textView15 != null) {
                                                                                                                                                                                                                    i = R.id.tv_smc_total_funds_utilized;
                                                                                                                                                                                                                    TextView textView16 = (TextView) rootView.findViewById(R.id.tv_smc_total_funds_utilized);
                                                                                                                                                                                                                    if (textView16 != null) {
                                                                                                                                                                                                                        return new FragmentSmcBinding(relativeLayout3, button, editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, editText10, editText11, editText12, editText13, relativeLayout, relativeLayout2, relativeLayout3, radioButton, radioButton2, radioButton3, radioButton4, relativeLayout4, radioGroup, radioGroup2, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, button2, relativeLayout10, relativeLayout11, relativeLayout12, relativeLayout13, relativeLayout14, relativeLayout15, relativeLayout16, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16);
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
