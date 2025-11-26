package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
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
public final class FragSchoolstatusBinding implements ViewBinding {
    public final Button btnNext;
    public final Button btnStartLocation;
    public final LinearLayout closedTimeLayout;
    public final EditText editTextComments;
    public final EditText editTextReason;
    public final LinearLayout elementaryClassesLayout;
    public final ImageView gifImage;
    public final LinearLayout hiSecondaryClassesLayout;
    public final LinearLayout llComments;
    public final LinearLayout llSpinner;
    public final LinearLayout middleClassesLayout;
    public final LinearLayout parentView;
    public final ImageView previewImage;
    public final RadioButton rb610;
    public final RadioButton rb612;
    public final RadioButton rbCloseDuringVisit;
    public final RadioButton rbElementary68;
    public final RadioButton rbElementaryK8;
    public final RadioButton rbK10;
    public final RadioButton rbK12;
    public final RadioButton rbMiddle68;
    public final RadioButton rbMiddleK8;
    public final RadioButton rbNo;
    public final RadioButton rbOpen;
    public final RadioButton rbPclose;
    public final RadioButton rbTclose;
    public final RadioButton rbYes;
    public final RadioGroup rg;
    public final RadioGroup rgElementaryClasses;
    public final RadioGroup rgHiSecondaryClasses;
    public final RadioGroup rgMiddleClasses;
    public final RadioGroup rgSchoolGender;
    public final RadioGroup rgSecondaryClasses;
    public final RadioGroup rgSemisCode;
    public final LinearLayout rlSchoolLevel;
    public final RelativeLayout rlSpinner;
    private final LinearLayout rootView;
    public final FrameLayout saveProgress;
    public final LinearLayout schoolGenderLayout;
    public final ImageView schoolStatusImage;
    public final ImageView schoolStatusImageUpload;
    public final LinearLayout secondaryClassesLayout;
    public final Spinner spClosedTime;
    public final Spinner spReason;
    public final Spinner spSchoolLvl;
    public final TextView tvAccuracy;
    public final TextView tvComments;
    public final TextView tvCountDownTimer;
    public final TextView tvLatitude;
    public final TextView tvLocationMessage;
    public final TextView tvLongitude;
    public final TextView tvReason;
    public final TextView tvSchoolStatus;
    public final RelativeLayout tvSchoolStatusLayout;
    public final TextView tvSemisCodeDisplayed;
    public final RelativeLayout tvSemisCodeDisplayedLayout;
    public final ImageView uploadImage;

    private FragSchoolstatusBinding(LinearLayout rootView, Button btnNext, Button btnStartLocation, LinearLayout closedTimeLayout, EditText editTextComments, EditText editTextReason, LinearLayout elementaryClassesLayout, ImageView gifImage, LinearLayout hiSecondaryClassesLayout, LinearLayout llComments, LinearLayout llSpinner, LinearLayout middleClassesLayout, LinearLayout parentView, ImageView previewImage, RadioButton rb610, RadioButton rb612, RadioButton rbCloseDuringVisit, RadioButton rbElementary68, RadioButton rbElementaryK8, RadioButton rbK10, RadioButton rbK12, RadioButton rbMiddle68, RadioButton rbMiddleK8, RadioButton rbNo, RadioButton rbOpen, RadioButton rbPclose, RadioButton rbTclose, RadioButton rbYes, RadioGroup rg, RadioGroup rgElementaryClasses, RadioGroup rgHiSecondaryClasses, RadioGroup rgMiddleClasses, RadioGroup rgSchoolGender, RadioGroup rgSecondaryClasses, RadioGroup rgSemisCode, LinearLayout rlSchoolLevel, RelativeLayout rlSpinner, FrameLayout saveProgress, LinearLayout schoolGenderLayout, ImageView schoolStatusImage, ImageView schoolStatusImageUpload, LinearLayout secondaryClassesLayout, Spinner spClosedTime, Spinner spReason, Spinner spSchoolLvl, TextView tvAccuracy, TextView tvComments, TextView tvCountDownTimer, TextView tvLatitude, TextView tvLocationMessage, TextView tvLongitude, TextView tvReason, TextView tvSchoolStatus, RelativeLayout tvSchoolStatusLayout, TextView tvSemisCodeDisplayed, RelativeLayout tvSemisCodeDisplayedLayout, ImageView uploadImage) {
        this.rootView = rootView;
        this.btnNext = btnNext;
        this.btnStartLocation = btnStartLocation;
        this.closedTimeLayout = closedTimeLayout;
        this.editTextComments = editTextComments;
        this.editTextReason = editTextReason;
        this.elementaryClassesLayout = elementaryClassesLayout;
        this.gifImage = gifImage;
        this.hiSecondaryClassesLayout = hiSecondaryClassesLayout;
        this.llComments = llComments;
        this.llSpinner = llSpinner;
        this.middleClassesLayout = middleClassesLayout;
        this.parentView = parentView;
        this.previewImage = previewImage;
        this.rb610 = rb610;
        this.rb612 = rb612;
        this.rbCloseDuringVisit = rbCloseDuringVisit;
        this.rbElementary68 = rbElementary68;
        this.rbElementaryK8 = rbElementaryK8;
        this.rbK10 = rbK10;
        this.rbK12 = rbK12;
        this.rbMiddle68 = rbMiddle68;
        this.rbMiddleK8 = rbMiddleK8;
        this.rbNo = rbNo;
        this.rbOpen = rbOpen;
        this.rbPclose = rbPclose;
        this.rbTclose = rbTclose;
        this.rbYes = rbYes;
        this.rg = rg;
        this.rgElementaryClasses = rgElementaryClasses;
        this.rgHiSecondaryClasses = rgHiSecondaryClasses;
        this.rgMiddleClasses = rgMiddleClasses;
        this.rgSchoolGender = rgSchoolGender;
        this.rgSecondaryClasses = rgSecondaryClasses;
        this.rgSemisCode = rgSemisCode;
        this.rlSchoolLevel = rlSchoolLevel;
        this.rlSpinner = rlSpinner;
        this.saveProgress = saveProgress;
        this.schoolGenderLayout = schoolGenderLayout;
        this.schoolStatusImage = schoolStatusImage;
        this.schoolStatusImageUpload = schoolStatusImageUpload;
        this.secondaryClassesLayout = secondaryClassesLayout;
        this.spClosedTime = spClosedTime;
        this.spReason = spReason;
        this.spSchoolLvl = spSchoolLvl;
        this.tvAccuracy = tvAccuracy;
        this.tvComments = tvComments;
        this.tvCountDownTimer = tvCountDownTimer;
        this.tvLatitude = tvLatitude;
        this.tvLocationMessage = tvLocationMessage;
        this.tvLongitude = tvLongitude;
        this.tvReason = tvReason;
        this.tvSchoolStatus = tvSchoolStatus;
        this.tvSchoolStatusLayout = tvSchoolStatusLayout;
        this.tvSemisCodeDisplayed = tvSemisCodeDisplayed;
        this.tvSemisCodeDisplayedLayout = tvSemisCodeDisplayedLayout;
        this.uploadImage = uploadImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragSchoolstatusBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragSchoolstatusBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_schoolstatus, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragSchoolstatusBinding bind(View rootView) {
        int i = R.id.btn_next;
        Button button = (Button) rootView.findViewById(R.id.btn_next);
        if (button != null) {
            i = R.id.btn_start_location;
            Button button2 = (Button) rootView.findViewById(R.id.btn_start_location);
            if (button2 != null) {
                i = R.id.closed_time_layout;
                LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.closed_time_layout);
                if (linearLayout != null) {
                    i = R.id.editText_comments;
                    EditText editText = (EditText) rootView.findViewById(R.id.editText_comments);
                    if (editText != null) {
                        i = R.id.editText_reason;
                        EditText editText2 = (EditText) rootView.findViewById(R.id.editText_reason);
                        if (editText2 != null) {
                            i = R.id.elementary_classes_layout;
                            LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.elementary_classes_layout);
                            if (linearLayout2 != null) {
                                i = R.id.gif_image;
                                ImageView imageView = (ImageView) rootView.findViewById(R.id.gif_image);
                                if (imageView != null) {
                                    i = R.id.hi_secondary_classes_layout;
                                    LinearLayout linearLayout3 = (LinearLayout) rootView.findViewById(R.id.hi_secondary_classes_layout);
                                    if (linearLayout3 != null) {
                                        i = R.id.ll_comments;
                                        LinearLayout linearLayout4 = (LinearLayout) rootView.findViewById(R.id.ll_comments);
                                        if (linearLayout4 != null) {
                                            i = R.id.ll_spinner;
                                            LinearLayout linearLayout5 = (LinearLayout) rootView.findViewById(R.id.ll_spinner);
                                            if (linearLayout5 != null) {
                                                i = R.id.middle_classes_layout;
                                                LinearLayout linearLayout6 = (LinearLayout) rootView.findViewById(R.id.middle_classes_layout);
                                                if (linearLayout6 != null) {
                                                    LinearLayout linearLayout7 = (LinearLayout) rootView;
                                                    i = R.id.previewImage;
                                                    ImageView imageView2 = (ImageView) rootView.findViewById(R.id.previewImage);
                                                    if (imageView2 != null) {
                                                        i = R.id.rb_6_10;
                                                        RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_6_10);
                                                        if (radioButton != null) {
                                                            i = R.id.rb_6_12;
                                                            RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_6_12);
                                                            if (radioButton2 != null) {
                                                                i = R.id.rb_close_during_visit;
                                                                RadioButton radioButton3 = (RadioButton) rootView.findViewById(R.id.rb_close_during_visit);
                                                                if (radioButton3 != null) {
                                                                    i = R.id.rb_elementary_6_8;
                                                                    RadioButton radioButton4 = (RadioButton) rootView.findViewById(R.id.rb_elementary_6_8);
                                                                    if (radioButton4 != null) {
                                                                        i = R.id.rb_elementary_k_8;
                                                                        RadioButton radioButton5 = (RadioButton) rootView.findViewById(R.id.rb_elementary_k_8);
                                                                        if (radioButton5 != null) {
                                                                            i = R.id.rb_k_10;
                                                                            RadioButton radioButton6 = (RadioButton) rootView.findViewById(R.id.rb_k_10);
                                                                            if (radioButton6 != null) {
                                                                                i = R.id.rb_k_12;
                                                                                RadioButton radioButton7 = (RadioButton) rootView.findViewById(R.id.rb_k_12);
                                                                                if (radioButton7 != null) {
                                                                                    i = R.id.rb_middle_6_8;
                                                                                    RadioButton radioButton8 = (RadioButton) rootView.findViewById(R.id.rb_middle_6_8);
                                                                                    if (radioButton8 != null) {
                                                                                        i = R.id.rb_middle_k_8;
                                                                                        RadioButton radioButton9 = (RadioButton) rootView.findViewById(R.id.rb_middle_k_8);
                                                                                        if (radioButton9 != null) {
                                                                                            i = R.id.rb_no;
                                                                                            RadioButton radioButton10 = (RadioButton) rootView.findViewById(R.id.rb_no);
                                                                                            if (radioButton10 != null) {
                                                                                                i = R.id.rb_open;
                                                                                                RadioButton radioButton11 = (RadioButton) rootView.findViewById(R.id.rb_open);
                                                                                                if (radioButton11 != null) {
                                                                                                    i = R.id.rb_pclose;
                                                                                                    RadioButton radioButton12 = (RadioButton) rootView.findViewById(R.id.rb_pclose);
                                                                                                    if (radioButton12 != null) {
                                                                                                        i = R.id.rb_tclose;
                                                                                                        RadioButton radioButton13 = (RadioButton) rootView.findViewById(R.id.rb_tclose);
                                                                                                        if (radioButton13 != null) {
                                                                                                            i = R.id.rb_yes;
                                                                                                            RadioButton radioButton14 = (RadioButton) rootView.findViewById(R.id.rb_yes);
                                                                                                            if (radioButton14 != null) {
                                                                                                                i = R.id.rg;
                                                                                                                RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg);
                                                                                                                if (radioGroup != null) {
                                                                                                                    i = R.id.rg_elementary_classes;
                                                                                                                    RadioGroup radioGroup2 = (RadioGroup) rootView.findViewById(R.id.rg_elementary_classes);
                                                                                                                    if (radioGroup2 != null) {
                                                                                                                        i = R.id.rg_hi_secondary_classes;
                                                                                                                        RadioGroup radioGroup3 = (RadioGroup) rootView.findViewById(R.id.rg_hi_secondary_classes);
                                                                                                                        if (radioGroup3 != null) {
                                                                                                                            i = R.id.rg_middle_classes;
                                                                                                                            RadioGroup radioGroup4 = (RadioGroup) rootView.findViewById(R.id.rg_middle_classes);
                                                                                                                            if (radioGroup4 != null) {
                                                                                                                                i = R.id.rg_school_gender;
                                                                                                                                RadioGroup radioGroup5 = (RadioGroup) rootView.findViewById(R.id.rg_school_gender);
                                                                                                                                if (radioGroup5 != null) {
                                                                                                                                    i = R.id.rg_secondary_classes;
                                                                                                                                    RadioGroup radioGroup6 = (RadioGroup) rootView.findViewById(R.id.rg_secondary_classes);
                                                                                                                                    if (radioGroup6 != null) {
                                                                                                                                        i = R.id.rg_semis_code;
                                                                                                                                        RadioGroup radioGroup7 = (RadioGroup) rootView.findViewById(R.id.rg_semis_code);
                                                                                                                                        if (radioGroup7 != null) {
                                                                                                                                            i = R.id.rl_school_level;
                                                                                                                                            LinearLayout linearLayout8 = (LinearLayout) rootView.findViewById(R.id.rl_school_level);
                                                                                                                                            if (linearLayout8 != null) {
                                                                                                                                                i = R.id.rl_spinner;
                                                                                                                                                RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.rl_spinner);
                                                                                                                                                if (relativeLayout != null) {
                                                                                                                                                    i = R.id.save_progress;
                                                                                                                                                    FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.save_progress);
                                                                                                                                                    if (frameLayout != null) {
                                                                                                                                                        i = R.id.school_gender_layout;
                                                                                                                                                        LinearLayout linearLayout9 = (LinearLayout) rootView.findViewById(R.id.school_gender_layout);
                                                                                                                                                        if (linearLayout9 != null) {
                                                                                                                                                            i = R.id.school_status_image;
                                                                                                                                                            ImageView imageView3 = (ImageView) rootView.findViewById(R.id.school_status_image);
                                                                                                                                                            if (imageView3 != null) {
                                                                                                                                                                i = R.id.school_status_image_upload;
                                                                                                                                                                ImageView imageView4 = (ImageView) rootView.findViewById(R.id.school_status_image_upload);
                                                                                                                                                                if (imageView4 != null) {
                                                                                                                                                                    i = R.id.secondary_classes_layout;
                                                                                                                                                                    LinearLayout linearLayout10 = (LinearLayout) rootView.findViewById(R.id.secondary_classes_layout);
                                                                                                                                                                    if (linearLayout10 != null) {
                                                                                                                                                                        i = R.id.sp_closed_time;
                                                                                                                                                                        Spinner spinner = (Spinner) rootView.findViewById(R.id.sp_closed_time);
                                                                                                                                                                        if (spinner != null) {
                                                                                                                                                                            i = R.id.sp_reason;
                                                                                                                                                                            Spinner spinner2 = (Spinner) rootView.findViewById(R.id.sp_reason);
                                                                                                                                                                            if (spinner2 != null) {
                                                                                                                                                                                i = R.id.sp_school_lvl;
                                                                                                                                                                                Spinner spinner3 = (Spinner) rootView.findViewById(R.id.sp_school_lvl);
                                                                                                                                                                                if (spinner3 != null) {
                                                                                                                                                                                    i = R.id.tv_accuracy;
                                                                                                                                                                                    TextView textView = (TextView) rootView.findViewById(R.id.tv_accuracy);
                                                                                                                                                                                    if (textView != null) {
                                                                                                                                                                                        i = R.id.tv_comments;
                                                                                                                                                                                        TextView textView2 = (TextView) rootView.findViewById(R.id.tv_comments);
                                                                                                                                                                                        if (textView2 != null) {
                                                                                                                                                                                            i = R.id.tv_count_down_timer;
                                                                                                                                                                                            TextView textView3 = (TextView) rootView.findViewById(R.id.tv_count_down_timer);
                                                                                                                                                                                            if (textView3 != null) {
                                                                                                                                                                                                i = R.id.tv_latitude;
                                                                                                                                                                                                TextView textView4 = (TextView) rootView.findViewById(R.id.tv_latitude);
                                                                                                                                                                                                if (textView4 != null) {
                                                                                                                                                                                                    i = R.id.tv_location_message;
                                                                                                                                                                                                    TextView textView5 = (TextView) rootView.findViewById(R.id.tv_location_message);
                                                                                                                                                                                                    if (textView5 != null) {
                                                                                                                                                                                                        i = R.id.tv_longitude;
                                                                                                                                                                                                        TextView textView6 = (TextView) rootView.findViewById(R.id.tv_longitude);
                                                                                                                                                                                                        if (textView6 != null) {
                                                                                                                                                                                                            i = R.id.tv_reason;
                                                                                                                                                                                                            TextView textView7 = (TextView) rootView.findViewById(R.id.tv_reason);
                                                                                                                                                                                                            if (textView7 != null) {
                                                                                                                                                                                                                i = R.id.tv_school_status;
                                                                                                                                                                                                                TextView textView8 = (TextView) rootView.findViewById(R.id.tv_school_status);
                                                                                                                                                                                                                if (textView8 != null) {
                                                                                                                                                                                                                    i = R.id.tv_school_status_Layout;
                                                                                                                                                                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.tv_school_status_Layout);
                                                                                                                                                                                                                    if (relativeLayout2 != null) {
                                                                                                                                                                                                                        i = R.id.tv_semis_code_displayed;
                                                                                                                                                                                                                        TextView textView9 = (TextView) rootView.findViewById(R.id.tv_semis_code_displayed);
                                                                                                                                                                                                                        if (textView9 != null) {
                                                                                                                                                                                                                            i = R.id.tv_semis_code_displayed_Layout;
                                                                                                                                                                                                                            RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.tv_semis_code_displayed_Layout);
                                                                                                                                                                                                                            if (relativeLayout3 != null) {
                                                                                                                                                                                                                                i = R.id.uploadImage;
                                                                                                                                                                                                                                ImageView imageView5 = (ImageView) rootView.findViewById(R.id.uploadImage);
                                                                                                                                                                                                                                if (imageView5 != null) {
                                                                                                                                                                                                                                    return new FragSchoolstatusBinding(linearLayout7, button, button2, linearLayout, editText, editText2, linearLayout2, imageView, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, imageView2, radioButton, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7, radioButton8, radioButton9, radioButton10, radioButton11, radioButton12, radioButton13, radioButton14, radioGroup, radioGroup2, radioGroup3, radioGroup4, radioGroup5, radioGroup6, radioGroup7, linearLayout8, relativeLayout, frameLayout, linearLayout9, imageView3, imageView4, linearLayout10, spinner, spinner2, spinner3, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, relativeLayout2, textView9, relativeLayout3, imageView5);
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
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
