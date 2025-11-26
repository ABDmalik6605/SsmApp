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
import android.widget.Spinner;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentCensusSchoolBuildingBinding implements ViewBinding {
    public final Button cancelButton;
    public final EditText editTextComments;
    public final EditText etAdoptionYear;
    public final EditText etIfSharedWithNoBuilding;
    public final EditText etNameOfAdopter;
    public final EditText etOtherGovSchool;
    public final EditText etRoomsAffected;
    public final RelativeLayout parentView;
    public final ImageView previewImage;
    public final RadioButton rbIsThisSchoolAdoptedNo;
    public final RadioButton rbIsThisSchoolAdoptedYes;
    public final RadioButton rbNo;
    public final RadioButton rbYes;
    public final RadioGroup rgAdoptedSchool;
    public final RadioGroup rgBuilidingIllegal;
    public final RelativeLayout rlComments;
    public final LinearLayout rlIllegalBuilding;
    public final RelativeLayout rlRamarks;
    private final RelativeLayout rootView;
    public final RelativeLayout rvAdoptionYear;
    public final RelativeLayout rvCondition;
    public final RelativeLayout rvIfSharedWithNoBuilding;
    public final RelativeLayout rvIsThisSchoolAdopted;
    public final RelativeLayout rvNameOfAdopter;
    public final RelativeLayout rvOtherGovSchool;
    public final RelativeLayout rvRadioGroup;
    public final RelativeLayout rvRoomsAffected;
    public final RelativeLayout rvSchoolIsPlaced;
    public final RelativeLayout rvTypeOfBuilding;
    public final Button saveButton;
    public final Spinner spCondition;
    public final Spinner spOwnership;
    public final Spinner spRemarks;
    public final Spinner spSchoolIsPlaced;
    public final Spinner spTypeOfBuilding;
    public final TextView tvAdoptionYear;
    public final TextView tvBuidlingUnderIllegalOccupation;
    public final TextView tvComments;
    public final TextView tvCondition;
    public final TextView tvIsThisSchoolAdopted;
    public final TextView tvNameOfAdopter;
    public final TextView tvOwnership;
    public final TextView tvRemarks;
    public final TextView tvRoomsAffected;
    public final TextView tvSchoolIsPlaced;
    public final TextView tvSchooloBuildingTitle;
    public final TextView tvTypeOfBuilding;
    public final ImageView uploadImage;

    private FragmentCensusSchoolBuildingBinding(RelativeLayout rootView, Button cancelButton, EditText editTextComments, EditText etAdoptionYear, EditText etIfSharedWithNoBuilding, EditText etNameOfAdopter, EditText etOtherGovSchool, EditText etRoomsAffected, RelativeLayout parentView, ImageView previewImage, RadioButton rbIsThisSchoolAdoptedNo, RadioButton rbIsThisSchoolAdoptedYes, RadioButton rbNo, RadioButton rbYes, RadioGroup rgAdoptedSchool, RadioGroup rgBuilidingIllegal, RelativeLayout rlComments, LinearLayout rlIllegalBuilding, RelativeLayout rlRamarks, RelativeLayout rvAdoptionYear, RelativeLayout rvCondition, RelativeLayout rvIfSharedWithNoBuilding, RelativeLayout rvIsThisSchoolAdopted, RelativeLayout rvNameOfAdopter, RelativeLayout rvOtherGovSchool, RelativeLayout rvRadioGroup, RelativeLayout rvRoomsAffected, RelativeLayout rvSchoolIsPlaced, RelativeLayout rvTypeOfBuilding, Button saveButton, Spinner spCondition, Spinner spOwnership, Spinner spRemarks, Spinner spSchoolIsPlaced, Spinner spTypeOfBuilding, TextView tvAdoptionYear, TextView tvBuidlingUnderIllegalOccupation, TextView tvComments, TextView tvCondition, TextView tvIsThisSchoolAdopted, TextView tvNameOfAdopter, TextView tvOwnership, TextView tvRemarks, TextView tvRoomsAffected, TextView tvSchoolIsPlaced, TextView tvSchooloBuildingTitle, TextView tvTypeOfBuilding, ImageView uploadImage) {
        this.rootView = rootView;
        this.cancelButton = cancelButton;
        this.editTextComments = editTextComments;
        this.etAdoptionYear = etAdoptionYear;
        this.etIfSharedWithNoBuilding = etIfSharedWithNoBuilding;
        this.etNameOfAdopter = etNameOfAdopter;
        this.etOtherGovSchool = etOtherGovSchool;
        this.etRoomsAffected = etRoomsAffected;
        this.parentView = parentView;
        this.previewImage = previewImage;
        this.rbIsThisSchoolAdoptedNo = rbIsThisSchoolAdoptedNo;
        this.rbIsThisSchoolAdoptedYes = rbIsThisSchoolAdoptedYes;
        this.rbNo = rbNo;
        this.rbYes = rbYes;
        this.rgAdoptedSchool = rgAdoptedSchool;
        this.rgBuilidingIllegal = rgBuilidingIllegal;
        this.rlComments = rlComments;
        this.rlIllegalBuilding = rlIllegalBuilding;
        this.rlRamarks = rlRamarks;
        this.rvAdoptionYear = rvAdoptionYear;
        this.rvCondition = rvCondition;
        this.rvIfSharedWithNoBuilding = rvIfSharedWithNoBuilding;
        this.rvIsThisSchoolAdopted = rvIsThisSchoolAdopted;
        this.rvNameOfAdopter = rvNameOfAdopter;
        this.rvOtherGovSchool = rvOtherGovSchool;
        this.rvRadioGroup = rvRadioGroup;
        this.rvRoomsAffected = rvRoomsAffected;
        this.rvSchoolIsPlaced = rvSchoolIsPlaced;
        this.rvTypeOfBuilding = rvTypeOfBuilding;
        this.saveButton = saveButton;
        this.spCondition = spCondition;
        this.spOwnership = spOwnership;
        this.spRemarks = spRemarks;
        this.spSchoolIsPlaced = spSchoolIsPlaced;
        this.spTypeOfBuilding = spTypeOfBuilding;
        this.tvAdoptionYear = tvAdoptionYear;
        this.tvBuidlingUnderIllegalOccupation = tvBuidlingUnderIllegalOccupation;
        this.tvComments = tvComments;
        this.tvCondition = tvCondition;
        this.tvIsThisSchoolAdopted = tvIsThisSchoolAdopted;
        this.tvNameOfAdopter = tvNameOfAdopter;
        this.tvOwnership = tvOwnership;
        this.tvRemarks = tvRemarks;
        this.tvRoomsAffected = tvRoomsAffected;
        this.tvSchoolIsPlaced = tvSchoolIsPlaced;
        this.tvSchooloBuildingTitle = tvSchooloBuildingTitle;
        this.tvTypeOfBuilding = tvTypeOfBuilding;
        this.uploadImage = uploadImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCensusSchoolBuildingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCensusSchoolBuildingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_school_building, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCensusSchoolBuildingBinding bind(View rootView) {
        int i = R.id.cancel_button;
        Button button = (Button) rootView.findViewById(R.id.cancel_button);
        if (button != null) {
            i = R.id.editText_comments;
            EditText editText = (EditText) rootView.findViewById(R.id.editText_comments);
            if (editText != null) {
                i = R.id.et_adoption_year;
                EditText editText2 = (EditText) rootView.findViewById(R.id.et_adoption_year);
                if (editText2 != null) {
                    i = R.id.et_if_shared_with_no_building;
                    EditText editText3 = (EditText) rootView.findViewById(R.id.et_if_shared_with_no_building);
                    if (editText3 != null) {
                        i = R.id.et_name_of_adopter;
                        EditText editText4 = (EditText) rootView.findViewById(R.id.et_name_of_adopter);
                        if (editText4 != null) {
                            i = R.id.et_other_gov_school;
                            EditText editText5 = (EditText) rootView.findViewById(R.id.et_other_gov_school);
                            if (editText5 != null) {
                                i = R.id.et_rooms_affected;
                                EditText editText6 = (EditText) rootView.findViewById(R.id.et_rooms_affected);
                                if (editText6 != null) {
                                    RelativeLayout relativeLayout = (RelativeLayout) rootView;
                                    i = R.id.previewImage;
                                    ImageView imageView = (ImageView) rootView.findViewById(R.id.previewImage);
                                    if (imageView != null) {
                                        i = R.id.rb_is_this_school_adopted_no;
                                        RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_is_this_school_adopted_no);
                                        if (radioButton != null) {
                                            i = R.id.rb_is_this_school_adopted_yes;
                                            RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_is_this_school_adopted_yes);
                                            if (radioButton2 != null) {
                                                i = R.id.rb_no;
                                                RadioButton radioButton3 = (RadioButton) rootView.findViewById(R.id.rb_no);
                                                if (radioButton3 != null) {
                                                    i = R.id.rb_yes;
                                                    RadioButton radioButton4 = (RadioButton) rootView.findViewById(R.id.rb_yes);
                                                    if (radioButton4 != null) {
                                                        i = R.id.rg_adopted_school;
                                                        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_adopted_school);
                                                        if (radioGroup != null) {
                                                            i = R.id.rg_builiding_illegal;
                                                            RadioGroup radioGroup2 = (RadioGroup) rootView.findViewById(R.id.rg_builiding_illegal);
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
                                                                            i = R.id.rv_adoption_year;
                                                                            RelativeLayout relativeLayout4 = (RelativeLayout) rootView.findViewById(R.id.rv_adoption_year);
                                                                            if (relativeLayout4 != null) {
                                                                                i = R.id.rv_condition;
                                                                                RelativeLayout relativeLayout5 = (RelativeLayout) rootView.findViewById(R.id.rv_condition);
                                                                                if (relativeLayout5 != null) {
                                                                                    i = R.id.rv_if_shared_with_no_building;
                                                                                    RelativeLayout relativeLayout6 = (RelativeLayout) rootView.findViewById(R.id.rv_if_shared_with_no_building);
                                                                                    if (relativeLayout6 != null) {
                                                                                        i = R.id.rv_is_this_school_adopted;
                                                                                        RelativeLayout relativeLayout7 = (RelativeLayout) rootView.findViewById(R.id.rv_is_this_school_adopted);
                                                                                        if (relativeLayout7 != null) {
                                                                                            i = R.id.rv_name_of_adopter;
                                                                                            RelativeLayout relativeLayout8 = (RelativeLayout) rootView.findViewById(R.id.rv_name_of_adopter);
                                                                                            if (relativeLayout8 != null) {
                                                                                                i = R.id.rv_other_gov_school;
                                                                                                RelativeLayout relativeLayout9 = (RelativeLayout) rootView.findViewById(R.id.rv_other_gov_school);
                                                                                                if (relativeLayout9 != null) {
                                                                                                    i = R.id.rv_radio_group;
                                                                                                    RelativeLayout relativeLayout10 = (RelativeLayout) rootView.findViewById(R.id.rv_radio_group);
                                                                                                    if (relativeLayout10 != null) {
                                                                                                        i = R.id.rv_rooms_affected;
                                                                                                        RelativeLayout relativeLayout11 = (RelativeLayout) rootView.findViewById(R.id.rv_rooms_affected);
                                                                                                        if (relativeLayout11 != null) {
                                                                                                            i = R.id.rv_school_is_placed;
                                                                                                            RelativeLayout relativeLayout12 = (RelativeLayout) rootView.findViewById(R.id.rv_school_is_placed);
                                                                                                            if (relativeLayout12 != null) {
                                                                                                                i = R.id.rv_type_of_building;
                                                                                                                RelativeLayout relativeLayout13 = (RelativeLayout) rootView.findViewById(R.id.rv_type_of_building);
                                                                                                                if (relativeLayout13 != null) {
                                                                                                                    i = R.id.save_button;
                                                                                                                    Button button2 = (Button) rootView.findViewById(R.id.save_button);
                                                                                                                    if (button2 != null) {
                                                                                                                        i = R.id.sp_condition;
                                                                                                                        Spinner spinner = (Spinner) rootView.findViewById(R.id.sp_condition);
                                                                                                                        if (spinner != null) {
                                                                                                                            i = R.id.sp_ownership;
                                                                                                                            Spinner spinner2 = (Spinner) rootView.findViewById(R.id.sp_ownership);
                                                                                                                            if (spinner2 != null) {
                                                                                                                                i = R.id.sp_remarks;
                                                                                                                                Spinner spinner3 = (Spinner) rootView.findViewById(R.id.sp_remarks);
                                                                                                                                if (spinner3 != null) {
                                                                                                                                    i = R.id.sp_school_is_placed;
                                                                                                                                    Spinner spinner4 = (Spinner) rootView.findViewById(R.id.sp_school_is_placed);
                                                                                                                                    if (spinner4 != null) {
                                                                                                                                        i = R.id.sp_type_of_building;
                                                                                                                                        Spinner spinner5 = (Spinner) rootView.findViewById(R.id.sp_type_of_building);
                                                                                                                                        if (spinner5 != null) {
                                                                                                                                            i = R.id.tv_adoption_year;
                                                                                                                                            TextView textView = (TextView) rootView.findViewById(R.id.tv_adoption_year);
                                                                                                                                            if (textView != null) {
                                                                                                                                                i = R.id.tv_buidling_under_illegal_occupation;
                                                                                                                                                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_buidling_under_illegal_occupation);
                                                                                                                                                if (textView2 != null) {
                                                                                                                                                    i = R.id.tv_comments;
                                                                                                                                                    TextView textView3 = (TextView) rootView.findViewById(R.id.tv_comments);
                                                                                                                                                    if (textView3 != null) {
                                                                                                                                                        i = R.id.tv_condition;
                                                                                                                                                        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_condition);
                                                                                                                                                        if (textView4 != null) {
                                                                                                                                                            i = R.id.tv_is_this_school_adopted;
                                                                                                                                                            TextView textView5 = (TextView) rootView.findViewById(R.id.tv_is_this_school_adopted);
                                                                                                                                                            if (textView5 != null) {
                                                                                                                                                                i = R.id.tv_name_of_adopter;
                                                                                                                                                                TextView textView6 = (TextView) rootView.findViewById(R.id.tv_name_of_adopter);
                                                                                                                                                                if (textView6 != null) {
                                                                                                                                                                    i = R.id.tv_ownership;
                                                                                                                                                                    TextView textView7 = (TextView) rootView.findViewById(R.id.tv_ownership);
                                                                                                                                                                    if (textView7 != null) {
                                                                                                                                                                        i = R.id.tv_remarks;
                                                                                                                                                                        TextView textView8 = (TextView) rootView.findViewById(R.id.tv_remarks);
                                                                                                                                                                        if (textView8 != null) {
                                                                                                                                                                            i = R.id.tv_rooms_affected;
                                                                                                                                                                            TextView textView9 = (TextView) rootView.findViewById(R.id.tv_rooms_affected);
                                                                                                                                                                            if (textView9 != null) {
                                                                                                                                                                                i = R.id.tv_school_is_placed;
                                                                                                                                                                                TextView textView10 = (TextView) rootView.findViewById(R.id.tv_school_is_placed);
                                                                                                                                                                                if (textView10 != null) {
                                                                                                                                                                                    i = R.id.tv_schoolo_building_title;
                                                                                                                                                                                    TextView textView11 = (TextView) rootView.findViewById(R.id.tv_schoolo_building_title);
                                                                                                                                                                                    if (textView11 != null) {
                                                                                                                                                                                        i = R.id.tv_type_of_building;
                                                                                                                                                                                        TextView textView12 = (TextView) rootView.findViewById(R.id.tv_type_of_building);
                                                                                                                                                                                        if (textView12 != null) {
                                                                                                                                                                                            i = R.id.uploadImage;
                                                                                                                                                                                            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.uploadImage);
                                                                                                                                                                                            if (imageView2 != null) {
                                                                                                                                                                                                return new FragmentCensusSchoolBuildingBinding(relativeLayout, button, editText, editText2, editText3, editText4, editText5, editText6, relativeLayout, imageView, radioButton, radioButton2, radioButton3, radioButton4, radioGroup, radioGroup2, relativeLayout2, linearLayout, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, relativeLayout10, relativeLayout11, relativeLayout12, relativeLayout13, button2, spinner, spinner2, spinner3, spinner4, spinner5, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, imageView2);
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
