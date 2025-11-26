package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentBoundaryWallBinding implements ViewBinding {
    public final RelativeLayout availabilityLayout;
    public final RelativeLayout availabilityNoLayout;
    public final Switch availabilityToggleNo;
    public final Switch availabilityToggleYes;
    public final RelativeLayout availabilityYesLayout;
    public final ImageView cancelButton;
    public final RelativeLayout commentsLayout;
    public final EditText editTextComments;
    public final RelativeLayout functionalityLayout;
    public final RelativeLayout functionalityNoLayout;
    public final Switch functionalityToggleNo;
    public final Switch functionalityToggleYes;
    public final RelativeLayout functionalityYesLayout;
    public final RelativeLayout ifFunctionalLayout;
    public final RelativeLayout ifFunctionalPartiallyLayout;
    public final Switch ifFunctionalTogglePartially;
    public final Switch ifFunctionalToggleWholly;
    public final RelativeLayout ifFunctionalWhollyLayout;
    public final RelativeLayout parentView;
    private final RelativeLayout rootView;
    public final ImageView saveButton;
    public final MaterialSpinner spinner;
    public final RelativeLayout statusLayout;
    public final TextView tvAvailability;
    public final TextView tvAvailabilityNo;
    public final TextView tvAvailabilityYes;
    public final TextView tvBoundaryWall;
    public final TextView tvComments;
    public final TextView tvFunctionality;
    public final TextView tvFunctionalityNo;
    public final TextView tvFunctionalityYes;
    public final TextView tvIfFunctional;
    public final TextView tvIfFunctionalPartially;
    public final TextView tvIfFunctionalWholly;
    public final TextView tvStatus;
    public final ImageView uploadImage;

    private FragmentBoundaryWallBinding(RelativeLayout rootView, RelativeLayout availabilityLayout, RelativeLayout availabilityNoLayout, Switch availabilityToggleNo, Switch availabilityToggleYes, RelativeLayout availabilityYesLayout, ImageView cancelButton, RelativeLayout commentsLayout, EditText editTextComments, RelativeLayout functionalityLayout, RelativeLayout functionalityNoLayout, Switch functionalityToggleNo, Switch functionalityToggleYes, RelativeLayout functionalityYesLayout, RelativeLayout ifFunctionalLayout, RelativeLayout ifFunctionalPartiallyLayout, Switch ifFunctionalTogglePartially, Switch ifFunctionalToggleWholly, RelativeLayout ifFunctionalWhollyLayout, RelativeLayout parentView, ImageView saveButton, MaterialSpinner spinner, RelativeLayout statusLayout, TextView tvAvailability, TextView tvAvailabilityNo, TextView tvAvailabilityYes, TextView tvBoundaryWall, TextView tvComments, TextView tvFunctionality, TextView tvFunctionalityNo, TextView tvFunctionalityYes, TextView tvIfFunctional, TextView tvIfFunctionalPartially, TextView tvIfFunctionalWholly, TextView tvStatus, ImageView uploadImage) {
        this.rootView = rootView;
        this.availabilityLayout = availabilityLayout;
        this.availabilityNoLayout = availabilityNoLayout;
        this.availabilityToggleNo = availabilityToggleNo;
        this.availabilityToggleYes = availabilityToggleYes;
        this.availabilityYesLayout = availabilityYesLayout;
        this.cancelButton = cancelButton;
        this.commentsLayout = commentsLayout;
        this.editTextComments = editTextComments;
        this.functionalityLayout = functionalityLayout;
        this.functionalityNoLayout = functionalityNoLayout;
        this.functionalityToggleNo = functionalityToggleNo;
        this.functionalityToggleYes = functionalityToggleYes;
        this.functionalityYesLayout = functionalityYesLayout;
        this.ifFunctionalLayout = ifFunctionalLayout;
        this.ifFunctionalPartiallyLayout = ifFunctionalPartiallyLayout;
        this.ifFunctionalTogglePartially = ifFunctionalTogglePartially;
        this.ifFunctionalToggleWholly = ifFunctionalToggleWholly;
        this.ifFunctionalWhollyLayout = ifFunctionalWhollyLayout;
        this.parentView = parentView;
        this.saveButton = saveButton;
        this.spinner = spinner;
        this.statusLayout = statusLayout;
        this.tvAvailability = tvAvailability;
        this.tvAvailabilityNo = tvAvailabilityNo;
        this.tvAvailabilityYes = tvAvailabilityYes;
        this.tvBoundaryWall = tvBoundaryWall;
        this.tvComments = tvComments;
        this.tvFunctionality = tvFunctionality;
        this.tvFunctionalityNo = tvFunctionalityNo;
        this.tvFunctionalityYes = tvFunctionalityYes;
        this.tvIfFunctional = tvIfFunctional;
        this.tvIfFunctionalPartially = tvIfFunctionalPartially;
        this.tvIfFunctionalWholly = tvIfFunctionalWholly;
        this.tvStatus = tvStatus;
        this.uploadImage = uploadImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentBoundaryWallBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentBoundaryWallBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_boundary_wall, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentBoundaryWallBinding bind(View rootView) {
        int i = R.id.availabilityLayout;
        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.availabilityLayout);
        if (relativeLayout != null) {
            i = R.id.availability_noLayout;
            RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.availability_noLayout);
            if (relativeLayout2 != null) {
                i = R.id.availability_toggleNo;
                Switch r7 = (Switch) rootView.findViewById(R.id.availability_toggleNo);
                if (r7 != null) {
                    i = R.id.availability_toggleYes;
                    Switch r8 = (Switch) rootView.findViewById(R.id.availability_toggleYes);
                    if (r8 != null) {
                        i = R.id.availability_yesLayout;
                        RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.availability_yesLayout);
                        if (relativeLayout3 != null) {
                            i = R.id.cancel_button;
                            ImageView imageView = (ImageView) rootView.findViewById(R.id.cancel_button);
                            if (imageView != null) {
                                i = R.id.commentsLayout;
                                RelativeLayout relativeLayout4 = (RelativeLayout) rootView.findViewById(R.id.commentsLayout);
                                if (relativeLayout4 != null) {
                                    i = R.id.editText_comments;
                                    EditText editText = (EditText) rootView.findViewById(R.id.editText_comments);
                                    if (editText != null) {
                                        i = R.id.functionalityLayout;
                                        RelativeLayout relativeLayout5 = (RelativeLayout) rootView.findViewById(R.id.functionalityLayout);
                                        if (relativeLayout5 != null) {
                                            i = R.id.functionality_noLayout;
                                            RelativeLayout relativeLayout6 = (RelativeLayout) rootView.findViewById(R.id.functionality_noLayout);
                                            if (relativeLayout6 != null) {
                                                i = R.id.functionality_toggleNo;
                                                Switch r15 = (Switch) rootView.findViewById(R.id.functionality_toggleNo);
                                                if (r15 != null) {
                                                    i = R.id.functionality_toggleYes;
                                                    Switch r16 = (Switch) rootView.findViewById(R.id.functionality_toggleYes);
                                                    if (r16 != null) {
                                                        i = R.id.functionality_yesLayout;
                                                        RelativeLayout relativeLayout7 = (RelativeLayout) rootView.findViewById(R.id.functionality_yesLayout);
                                                        if (relativeLayout7 != null) {
                                                            i = R.id.ifFunctionalLayout;
                                                            RelativeLayout relativeLayout8 = (RelativeLayout) rootView.findViewById(R.id.ifFunctionalLayout);
                                                            if (relativeLayout8 != null) {
                                                                i = R.id.ifFunctional_partiallyLayout;
                                                                RelativeLayout relativeLayout9 = (RelativeLayout) rootView.findViewById(R.id.ifFunctional_partiallyLayout);
                                                                if (relativeLayout9 != null) {
                                                                    i = R.id.ifFunctional_togglePartially;
                                                                    Switch r20 = (Switch) rootView.findViewById(R.id.ifFunctional_togglePartially);
                                                                    if (r20 != null) {
                                                                        i = R.id.ifFunctional_toggleWholly;
                                                                        Switch r21 = (Switch) rootView.findViewById(R.id.ifFunctional_toggleWholly);
                                                                        if (r21 != null) {
                                                                            i = R.id.ifFunctional_whollyLayout;
                                                                            RelativeLayout relativeLayout10 = (RelativeLayout) rootView.findViewById(R.id.ifFunctional_whollyLayout);
                                                                            if (relativeLayout10 != null) {
                                                                                RelativeLayout relativeLayout11 = (RelativeLayout) rootView;
                                                                                i = R.id.save_button;
                                                                                ImageView imageView2 = (ImageView) rootView.findViewById(R.id.save_button);
                                                                                if (imageView2 != null) {
                                                                                    i = R.id.spinner;
                                                                                    MaterialSpinner materialSpinner = (MaterialSpinner) rootView.findViewById(R.id.spinner);
                                                                                    if (materialSpinner != null) {
                                                                                        i = R.id.statusLayout;
                                                                                        RelativeLayout relativeLayout12 = (RelativeLayout) rootView.findViewById(R.id.statusLayout);
                                                                                        if (relativeLayout12 != null) {
                                                                                            i = R.id.tv_availability;
                                                                                            TextView textView = (TextView) rootView.findViewById(R.id.tv_availability);
                                                                                            if (textView != null) {
                                                                                                i = R.id.tv_availabilityNo;
                                                                                                TextView textView2 = (TextView) rootView.findViewById(R.id.tv_availabilityNo);
                                                                                                if (textView2 != null) {
                                                                                                    i = R.id.tv_availabilityYes;
                                                                                                    TextView textView3 = (TextView) rootView.findViewById(R.id.tv_availabilityYes);
                                                                                                    if (textView3 != null) {
                                                                                                        i = R.id.tv_boundaryWall;
                                                                                                        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_boundaryWall);
                                                                                                        if (textView4 != null) {
                                                                                                            i = R.id.tv_comments;
                                                                                                            TextView textView5 = (TextView) rootView.findViewById(R.id.tv_comments);
                                                                                                            if (textView5 != null) {
                                                                                                                i = R.id.tv_functionality;
                                                                                                                TextView textView6 = (TextView) rootView.findViewById(R.id.tv_functionality);
                                                                                                                if (textView6 != null) {
                                                                                                                    i = R.id.tv_functionalityNo;
                                                                                                                    TextView textView7 = (TextView) rootView.findViewById(R.id.tv_functionalityNo);
                                                                                                                    if (textView7 != null) {
                                                                                                                        i = R.id.tv_functionalityYes;
                                                                                                                        TextView textView8 = (TextView) rootView.findViewById(R.id.tv_functionalityYes);
                                                                                                                        if (textView8 != null) {
                                                                                                                            i = R.id.tv_ifFunctional;
                                                                                                                            TextView textView9 = (TextView) rootView.findViewById(R.id.tv_ifFunctional);
                                                                                                                            if (textView9 != null) {
                                                                                                                                i = R.id.tv_ifFunctionalPartially;
                                                                                                                                TextView textView10 = (TextView) rootView.findViewById(R.id.tv_ifFunctionalPartially);
                                                                                                                                if (textView10 != null) {
                                                                                                                                    i = R.id.tv_ifFunctionalWholly;
                                                                                                                                    TextView textView11 = (TextView) rootView.findViewById(R.id.tv_ifFunctionalWholly);
                                                                                                                                    if (textView11 != null) {
                                                                                                                                        i = R.id.tv_status;
                                                                                                                                        TextView textView12 = (TextView) rootView.findViewById(R.id.tv_status);
                                                                                                                                        if (textView12 != null) {
                                                                                                                                            i = R.id.uploadImage;
                                                                                                                                            ImageView imageView3 = (ImageView) rootView.findViewById(R.id.uploadImage);
                                                                                                                                            if (imageView3 != null) {
                                                                                                                                                return new FragmentBoundaryWallBinding(relativeLayout11, relativeLayout, relativeLayout2, r7, r8, relativeLayout3, imageView, relativeLayout4, editText, relativeLayout5, relativeLayout6, r15, r16, relativeLayout7, relativeLayout8, relativeLayout9, r20, r21, relativeLayout10, relativeLayout11, imageView2, materialSpinner, relativeLayout12, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, imageView3);
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
