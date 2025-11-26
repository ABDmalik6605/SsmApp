package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentCensusBinding implements ViewBinding {
    public final CardView cvBoundaryWall;
    public final CardView cvClassrooms;
    public final CardView cvDrinkingWater;
    public final CardView cvEducationQuality;
    public final CardView cvElectricity;
    public final CardView cvFurniture;
    public final CardView cvGirlsStipend;
    public final CardView cvOtherFacilities;
    public final CardView cvSanctionedNewExpenditure;
    public final CardView cvSchoolBuilding;
    public final CardView cvTextbooks;
    public final CardView cvWashroom;
    private final LinearLayout rootView;

    private FragmentCensusBinding(LinearLayout rootView, CardView cvBoundaryWall, CardView cvClassrooms, CardView cvDrinkingWater, CardView cvEducationQuality, CardView cvElectricity, CardView cvFurniture, CardView cvGirlsStipend, CardView cvOtherFacilities, CardView cvSanctionedNewExpenditure, CardView cvSchoolBuilding, CardView cvTextbooks, CardView cvWashroom) {
        this.rootView = rootView;
        this.cvBoundaryWall = cvBoundaryWall;
        this.cvClassrooms = cvClassrooms;
        this.cvDrinkingWater = cvDrinkingWater;
        this.cvEducationQuality = cvEducationQuality;
        this.cvElectricity = cvElectricity;
        this.cvFurniture = cvFurniture;
        this.cvGirlsStipend = cvGirlsStipend;
        this.cvOtherFacilities = cvOtherFacilities;
        this.cvSanctionedNewExpenditure = cvSanctionedNewExpenditure;
        this.cvSchoolBuilding = cvSchoolBuilding;
        this.cvTextbooks = cvTextbooks;
        this.cvWashroom = cvWashroom;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCensusBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCensusBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_census, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCensusBinding bind(View rootView) {
        int i = R.id.cv_boundary_wall;
        CardView cardView = (CardView) rootView.findViewById(R.id.cv_boundary_wall);
        if (cardView != null) {
            i = R.id.cv_classrooms;
            CardView cardView2 = (CardView) rootView.findViewById(R.id.cv_classrooms);
            if (cardView2 != null) {
                i = R.id.cv_drinking_water;
                CardView cardView3 = (CardView) rootView.findViewById(R.id.cv_drinking_water);
                if (cardView3 != null) {
                    i = R.id.cv_education_quality;
                    CardView cardView4 = (CardView) rootView.findViewById(R.id.cv_education_quality);
                    if (cardView4 != null) {
                        i = R.id.cv_electricity;
                        CardView cardView5 = (CardView) rootView.findViewById(R.id.cv_electricity);
                        if (cardView5 != null) {
                            i = R.id.cv_furniture;
                            CardView cardView6 = (CardView) rootView.findViewById(R.id.cv_furniture);
                            if (cardView6 != null) {
                                i = R.id.cv_girls_stipend;
                                CardView cardView7 = (CardView) rootView.findViewById(R.id.cv_girls_stipend);
                                if (cardView7 != null) {
                                    i = R.id.cv_other_facilities;
                                    CardView cardView8 = (CardView) rootView.findViewById(R.id.cv_other_facilities);
                                    if (cardView8 != null) {
                                        i = R.id.cv_sanctioned_new_expenditure;
                                        CardView cardView9 = (CardView) rootView.findViewById(R.id.cv_sanctioned_new_expenditure);
                                        if (cardView9 != null) {
                                            i = R.id.cv_school_building;
                                            CardView cardView10 = (CardView) rootView.findViewById(R.id.cv_school_building);
                                            if (cardView10 != null) {
                                                i = R.id.cv_textbooks;
                                                CardView cardView11 = (CardView) rootView.findViewById(R.id.cv_textbooks);
                                                if (cardView11 != null) {
                                                    i = R.id.cv_washroom;
                                                    CardView cardView12 = (CardView) rootView.findViewById(R.id.cv_washroom);
                                                    if (cardView12 != null) {
                                                        return new FragmentCensusBinding((LinearLayout) rootView, cardView, cardView2, cardView3, cardView4, cardView5, cardView6, cardView7, cardView8, cardView9, cardView10, cardView11, cardView12);
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
