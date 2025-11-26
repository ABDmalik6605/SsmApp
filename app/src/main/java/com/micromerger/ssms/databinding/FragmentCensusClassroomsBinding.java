package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentCensusClassroomsBinding implements ViewBinding {
    public final Button cancelButton;
    public final EditText etCommunityCentre;
    public final EditText etDistrictAdministrationOffices;
    public final EditText etNgo;
    public final EditText etNoOfClassroomsStoringOldFurnitureFtbsEtc;
    public final EditText etNoOfClassroomsUsedForTeaching;
    public final EditText etOccupiedByVillager;
    public final EditText etOtherSpecify;
    public final EditText etTotalNoOfClassrooms;
    public final EditText etVocationalTrainingCentre;
    public final RelativeLayout parentView;
    public final ImageView previewImage;
    private final RelativeLayout rootView;
    public final Button saveButton;
    public final Button skipButton;
    public final TextView tvCommunityCentre;
    public final TextView tvDistrictAdministrationOffices;
    public final TextView tvNgo;
    public final TextView tvNoOfClassroomsStoringOldFurnitureFtbsEtc;
    public final TextView tvNoOfClassroomsUsedForTeaching;
    public final TextView tvOccupiedByVillager;
    public final TextView tvOtherSpecify;
    public final TextView tvTextbooksDetail;
    public final TextView tvTotalNoOfClassrooms;
    public final TextView tvVocationalTrainingCentre;
    public final ImageView uploadImage;

    private FragmentCensusClassroomsBinding(RelativeLayout rootView, Button cancelButton, EditText etCommunityCentre, EditText etDistrictAdministrationOffices, EditText etNgo, EditText etNoOfClassroomsStoringOldFurnitureFtbsEtc, EditText etNoOfClassroomsUsedForTeaching, EditText etOccupiedByVillager, EditText etOtherSpecify, EditText etTotalNoOfClassrooms, EditText etVocationalTrainingCentre, RelativeLayout parentView, ImageView previewImage, Button saveButton, Button skipButton, TextView tvCommunityCentre, TextView tvDistrictAdministrationOffices, TextView tvNgo, TextView tvNoOfClassroomsStoringOldFurnitureFtbsEtc, TextView tvNoOfClassroomsUsedForTeaching, TextView tvOccupiedByVillager, TextView tvOtherSpecify, TextView tvTextbooksDetail, TextView tvTotalNoOfClassrooms, TextView tvVocationalTrainingCentre, ImageView uploadImage) {
        this.rootView = rootView;
        this.cancelButton = cancelButton;
        this.etCommunityCentre = etCommunityCentre;
        this.etDistrictAdministrationOffices = etDistrictAdministrationOffices;
        this.etNgo = etNgo;
        this.etNoOfClassroomsStoringOldFurnitureFtbsEtc = etNoOfClassroomsStoringOldFurnitureFtbsEtc;
        this.etNoOfClassroomsUsedForTeaching = etNoOfClassroomsUsedForTeaching;
        this.etOccupiedByVillager = etOccupiedByVillager;
        this.etOtherSpecify = etOtherSpecify;
        this.etTotalNoOfClassrooms = etTotalNoOfClassrooms;
        this.etVocationalTrainingCentre = etVocationalTrainingCentre;
        this.parentView = parentView;
        this.previewImage = previewImage;
        this.saveButton = saveButton;
        this.skipButton = skipButton;
        this.tvCommunityCentre = tvCommunityCentre;
        this.tvDistrictAdministrationOffices = tvDistrictAdministrationOffices;
        this.tvNgo = tvNgo;
        this.tvNoOfClassroomsStoringOldFurnitureFtbsEtc = tvNoOfClassroomsStoringOldFurnitureFtbsEtc;
        this.tvNoOfClassroomsUsedForTeaching = tvNoOfClassroomsUsedForTeaching;
        this.tvOccupiedByVillager = tvOccupiedByVillager;
        this.tvOtherSpecify = tvOtherSpecify;
        this.tvTextbooksDetail = tvTextbooksDetail;
        this.tvTotalNoOfClassrooms = tvTotalNoOfClassrooms;
        this.tvVocationalTrainingCentre = tvVocationalTrainingCentre;
        this.uploadImage = uploadImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCensusClassroomsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCensusClassroomsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_classrooms, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCensusClassroomsBinding bind(View rootView) {
        int i = R.id.cancel_button;
        Button button = (Button) rootView.findViewById(R.id.cancel_button);
        if (button != null) {
            i = R.id.et_community_centre;
            EditText editText = (EditText) rootView.findViewById(R.id.et_community_centre);
            if (editText != null) {
                i = R.id.et_district_administration_offices;
                EditText editText2 = (EditText) rootView.findViewById(R.id.et_district_administration_offices);
                if (editText2 != null) {
                    i = R.id.et_ngo;
                    EditText editText3 = (EditText) rootView.findViewById(R.id.et_ngo);
                    if (editText3 != null) {
                        i = R.id.et_no_of_classrooms_storing_old_furniture_ftbs_etc;
                        EditText editText4 = (EditText) rootView.findViewById(R.id.et_no_of_classrooms_storing_old_furniture_ftbs_etc);
                        if (editText4 != null) {
                            i = R.id.et_no_of_classrooms_used_for_teaching;
                            EditText editText5 = (EditText) rootView.findViewById(R.id.et_no_of_classrooms_used_for_teaching);
                            if (editText5 != null) {
                                i = R.id.et_occupied_by_villager;
                                EditText editText6 = (EditText) rootView.findViewById(R.id.et_occupied_by_villager);
                                if (editText6 != null) {
                                    i = R.id.et_other_specify;
                                    EditText editText7 = (EditText) rootView.findViewById(R.id.et_other_specify);
                                    if (editText7 != null) {
                                        i = R.id.et_total_no_of_classrooms;
                                        EditText editText8 = (EditText) rootView.findViewById(R.id.et_total_no_of_classrooms);
                                        if (editText8 != null) {
                                            i = R.id.et_vocational_training_centre;
                                            EditText editText9 = (EditText) rootView.findViewById(R.id.et_vocational_training_centre);
                                            if (editText9 != null) {
                                                RelativeLayout relativeLayout = (RelativeLayout) rootView;
                                                i = R.id.previewImage;
                                                ImageView imageView = (ImageView) rootView.findViewById(R.id.previewImage);
                                                if (imageView != null) {
                                                    i = R.id.save_button;
                                                    Button button2 = (Button) rootView.findViewById(R.id.save_button);
                                                    if (button2 != null) {
                                                        i = R.id.skip_button;
                                                        Button button3 = (Button) rootView.findViewById(R.id.skip_button);
                                                        if (button3 != null) {
                                                            i = R.id.tv_community_centre;
                                                            TextView textView = (TextView) rootView.findViewById(R.id.tv_community_centre);
                                                            if (textView != null) {
                                                                i = R.id.tv_district_administration_offices;
                                                                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_district_administration_offices);
                                                                if (textView2 != null) {
                                                                    i = R.id.tv_ngo;
                                                                    TextView textView3 = (TextView) rootView.findViewById(R.id.tv_ngo);
                                                                    if (textView3 != null) {
                                                                        i = R.id.tv_no_of_classrooms_storing_old_furniture_ftbs_etc;
                                                                        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_no_of_classrooms_storing_old_furniture_ftbs_etc);
                                                                        if (textView4 != null) {
                                                                            i = R.id.tv_no_of_classrooms_used_for_teaching;
                                                                            TextView textView5 = (TextView) rootView.findViewById(R.id.tv_no_of_classrooms_used_for_teaching);
                                                                            if (textView5 != null) {
                                                                                i = R.id.tv_occupied_by_villager;
                                                                                TextView textView6 = (TextView) rootView.findViewById(R.id.tv_occupied_by_villager);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.tv_other_specify;
                                                                                    TextView textView7 = (TextView) rootView.findViewById(R.id.tv_other_specify);
                                                                                    if (textView7 != null) {
                                                                                        i = R.id.tv_textbooks_detail;
                                                                                        TextView textView8 = (TextView) rootView.findViewById(R.id.tv_textbooks_detail);
                                                                                        if (textView8 != null) {
                                                                                            i = R.id.tv_total_no_of_classrooms;
                                                                                            TextView textView9 = (TextView) rootView.findViewById(R.id.tv_total_no_of_classrooms);
                                                                                            if (textView9 != null) {
                                                                                                i = R.id.tv_vocational_training_centre;
                                                                                                TextView textView10 = (TextView) rootView.findViewById(R.id.tv_vocational_training_centre);
                                                                                                if (textView10 != null) {
                                                                                                    i = R.id.uploadImage;
                                                                                                    ImageView imageView2 = (ImageView) rootView.findViewById(R.id.uploadImage);
                                                                                                    if (imageView2 != null) {
                                                                                                        return new FragmentCensusClassroomsBinding(relativeLayout, button, editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, relativeLayout, imageView, button2, button3, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, imageView2);
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
