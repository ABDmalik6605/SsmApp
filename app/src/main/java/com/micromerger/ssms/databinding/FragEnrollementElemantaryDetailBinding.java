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
public final class FragEnrollementElemantaryDetailBinding implements ViewBinding {
    public final Button cancelButton;
    public final EditText etAbsentFemaleTwoMonths;
    public final EditText etAbsentMaleTwoMonths;
    public final EditText etFemaleEnroll;
    public final EditText etFemalePresent;
    public final EditText etMaleEnroll;
    public final EditText etMalePresent;
    public final EditText etTotalEnroll;
    public final EditText etTotalPresent;
    public final EditText etTotalUniform;
    public final RelativeLayout femaleStudentsAbsentTwoMonths;
    public final RelativeLayout femaleStudentsEnrollment;
    public final RelativeLayout femaleStudentsPresent;
    public final RelativeLayout maleStudentsAbsentTwoMonths;
    public final RelativeLayout maleStudentsEnrollment;
    public final RelativeLayout maleStudentsPresent;
    public final RadioButton rbDailyDiaryNo;
    public final RadioButton rbDailyDiaryYes;
    public final RadioButton rbLessonPlanNo;
    public final RadioButton rbLessonPlanYes;
    public final RadioButton rbSchemeOfStudiesNo;
    public final RadioButton rbSchemeOfStudiesYes;
    public final RadioButton rbTimeTableNo;
    public final RadioButton rbTimeTableYes;
    public final RadioGroup rgDailyDiary;
    public final RadioGroup rgLessonPlan;
    public final RadioGroup rgSchemeOfStudies;
    public final RadioGroup rgTimeTable;
    private final LinearLayout rootView;
    public final Button saveButton;
    public final Button skipButton;
    public final TextView tvEnrollmentDetail;
    public final TextView tvFemaleTwoMonths;
    public final TextView tvMaleTwoMonths;
    public final TextView tvTotalNoOfClassrooms;
    public final TextView tvTotalNoOfClassrooms1;
    public final TextView tvTotalNoOfClassrooms3;
    public final TextView tvTotalNoOfClassrooms4;
    public final TextView tvTotalNoOfClassrooms5;
    public final TextView tvTotalNoOfClassrooms6;
    public final TextView tvTotalNoOfClassrooms7;

    private FragEnrollementElemantaryDetailBinding(LinearLayout rootView, Button cancelButton, EditText etAbsentFemaleTwoMonths, EditText etAbsentMaleTwoMonths, EditText etFemaleEnroll, EditText etFemalePresent, EditText etMaleEnroll, EditText etMalePresent, EditText etTotalEnroll, EditText etTotalPresent, EditText etTotalUniform, RelativeLayout femaleStudentsAbsentTwoMonths, RelativeLayout femaleStudentsEnrollment, RelativeLayout femaleStudentsPresent, RelativeLayout maleStudentsAbsentTwoMonths, RelativeLayout maleStudentsEnrollment, RelativeLayout maleStudentsPresent, RadioButton rbDailyDiaryNo, RadioButton rbDailyDiaryYes, RadioButton rbLessonPlanNo, RadioButton rbLessonPlanYes, RadioButton rbSchemeOfStudiesNo, RadioButton rbSchemeOfStudiesYes, RadioButton rbTimeTableNo, RadioButton rbTimeTableYes, RadioGroup rgDailyDiary, RadioGroup rgLessonPlan, RadioGroup rgSchemeOfStudies, RadioGroup rgTimeTable, Button saveButton, Button skipButton, TextView tvEnrollmentDetail, TextView tvFemaleTwoMonths, TextView tvMaleTwoMonths, TextView tvTotalNoOfClassrooms, TextView tvTotalNoOfClassrooms1, TextView tvTotalNoOfClassrooms3, TextView tvTotalNoOfClassrooms4, TextView tvTotalNoOfClassrooms5, TextView tvTotalNoOfClassrooms6, TextView tvTotalNoOfClassrooms7) {
        this.rootView = rootView;
        this.cancelButton = cancelButton;
        this.etAbsentFemaleTwoMonths = etAbsentFemaleTwoMonths;
        this.etAbsentMaleTwoMonths = etAbsentMaleTwoMonths;
        this.etFemaleEnroll = etFemaleEnroll;
        this.etFemalePresent = etFemalePresent;
        this.etMaleEnroll = etMaleEnroll;
        this.etMalePresent = etMalePresent;
        this.etTotalEnroll = etTotalEnroll;
        this.etTotalPresent = etTotalPresent;
        this.etTotalUniform = etTotalUniform;
        this.femaleStudentsAbsentTwoMonths = femaleStudentsAbsentTwoMonths;
        this.femaleStudentsEnrollment = femaleStudentsEnrollment;
        this.femaleStudentsPresent = femaleStudentsPresent;
        this.maleStudentsAbsentTwoMonths = maleStudentsAbsentTwoMonths;
        this.maleStudentsEnrollment = maleStudentsEnrollment;
        this.maleStudentsPresent = maleStudentsPresent;
        this.rbDailyDiaryNo = rbDailyDiaryNo;
        this.rbDailyDiaryYes = rbDailyDiaryYes;
        this.rbLessonPlanNo = rbLessonPlanNo;
        this.rbLessonPlanYes = rbLessonPlanYes;
        this.rbSchemeOfStudiesNo = rbSchemeOfStudiesNo;
        this.rbSchemeOfStudiesYes = rbSchemeOfStudiesYes;
        this.rbTimeTableNo = rbTimeTableNo;
        this.rbTimeTableYes = rbTimeTableYes;
        this.rgDailyDiary = rgDailyDiary;
        this.rgLessonPlan = rgLessonPlan;
        this.rgSchemeOfStudies = rgSchemeOfStudies;
        this.rgTimeTable = rgTimeTable;
        this.saveButton = saveButton;
        this.skipButton = skipButton;
        this.tvEnrollmentDetail = tvEnrollmentDetail;
        this.tvFemaleTwoMonths = tvFemaleTwoMonths;
        this.tvMaleTwoMonths = tvMaleTwoMonths;
        this.tvTotalNoOfClassrooms = tvTotalNoOfClassrooms;
        this.tvTotalNoOfClassrooms1 = tvTotalNoOfClassrooms1;
        this.tvTotalNoOfClassrooms3 = tvTotalNoOfClassrooms3;
        this.tvTotalNoOfClassrooms4 = tvTotalNoOfClassrooms4;
        this.tvTotalNoOfClassrooms5 = tvTotalNoOfClassrooms5;
        this.tvTotalNoOfClassrooms6 = tvTotalNoOfClassrooms6;
        this.tvTotalNoOfClassrooms7 = tvTotalNoOfClassrooms7;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragEnrollementElemantaryDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragEnrollementElemantaryDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollement_elemantary_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragEnrollementElemantaryDetailBinding bind(View rootView) {
        int i = R.id.cancel_button;
        Button button = (Button) rootView.findViewById(R.id.cancel_button);
        if (button != null) {
            i = R.id.et_absent_female_two_months;
            EditText editText = (EditText) rootView.findViewById(R.id.et_absent_female_two_months);
            if (editText != null) {
                i = R.id.et_absent_male_two_months;
                EditText editText2 = (EditText) rootView.findViewById(R.id.et_absent_male_two_months);
                if (editText2 != null) {
                    i = R.id.et_female_enroll;
                    EditText editText3 = (EditText) rootView.findViewById(R.id.et_female_enroll);
                    if (editText3 != null) {
                        i = R.id.et_female_present;
                        EditText editText4 = (EditText) rootView.findViewById(R.id.et_female_present);
                        if (editText4 != null) {
                            i = R.id.et_male_enroll;
                            EditText editText5 = (EditText) rootView.findViewById(R.id.et_male_enroll);
                            if (editText5 != null) {
                                i = R.id.et_male_present;
                                EditText editText6 = (EditText) rootView.findViewById(R.id.et_male_present);
                                if (editText6 != null) {
                                    i = R.id.et_total_enroll;
                                    EditText editText7 = (EditText) rootView.findViewById(R.id.et_total_enroll);
                                    if (editText7 != null) {
                                        i = R.id.et_total_present;
                                        EditText editText8 = (EditText) rootView.findViewById(R.id.et_total_present);
                                        if (editText8 != null) {
                                            i = R.id.et_total_uniform;
                                            EditText editText9 = (EditText) rootView.findViewById(R.id.et_total_uniform);
                                            if (editText9 != null) {
                                                i = R.id.female_students_absent_two_months;
                                                RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.female_students_absent_two_months);
                                                if (relativeLayout != null) {
                                                    i = R.id.female_students_enrollment;
                                                    RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.female_students_enrollment);
                                                    if (relativeLayout2 != null) {
                                                        i = R.id.female_students_present;
                                                        RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.female_students_present);
                                                        if (relativeLayout3 != null) {
                                                            i = R.id.male_students_absent_two_months;
                                                            RelativeLayout relativeLayout4 = (RelativeLayout) rootView.findViewById(R.id.male_students_absent_two_months);
                                                            if (relativeLayout4 != null) {
                                                                i = R.id.male_students_enrollment;
                                                                RelativeLayout relativeLayout5 = (RelativeLayout) rootView.findViewById(R.id.male_students_enrollment);
                                                                if (relativeLayout5 != null) {
                                                                    i = R.id.male_students_present;
                                                                    RelativeLayout relativeLayout6 = (RelativeLayout) rootView.findViewById(R.id.male_students_present);
                                                                    if (relativeLayout6 != null) {
                                                                        i = R.id.rb_daily_diary_no;
                                                                        RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_daily_diary_no);
                                                                        if (radioButton != null) {
                                                                            i = R.id.rb_daily_diary_yes;
                                                                            RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_daily_diary_yes);
                                                                            if (radioButton2 != null) {
                                                                                i = R.id.rb_lesson_plan_no;
                                                                                RadioButton radioButton3 = (RadioButton) rootView.findViewById(R.id.rb_lesson_plan_no);
                                                                                if (radioButton3 != null) {
                                                                                    i = R.id.rb_lesson_plan_yes;
                                                                                    RadioButton radioButton4 = (RadioButton) rootView.findViewById(R.id.rb_lesson_plan_yes);
                                                                                    if (radioButton4 != null) {
                                                                                        i = R.id.rb_scheme_of_studies_no;
                                                                                        RadioButton radioButton5 = (RadioButton) rootView.findViewById(R.id.rb_scheme_of_studies_no);
                                                                                        if (radioButton5 != null) {
                                                                                            i = R.id.rb_scheme_of_studies_yes;
                                                                                            RadioButton radioButton6 = (RadioButton) rootView.findViewById(R.id.rb_scheme_of_studies_yes);
                                                                                            if (radioButton6 != null) {
                                                                                                i = R.id.rb_time_table_no;
                                                                                                RadioButton radioButton7 = (RadioButton) rootView.findViewById(R.id.rb_time_table_no);
                                                                                                if (radioButton7 != null) {
                                                                                                    i = R.id.rb_time_table_yes;
                                                                                                    RadioButton radioButton8 = (RadioButton) rootView.findViewById(R.id.rb_time_table_yes);
                                                                                                    if (radioButton8 != null) {
                                                                                                        i = R.id.rg_daily_diary;
                                                                                                        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_daily_diary);
                                                                                                        if (radioGroup != null) {
                                                                                                            i = R.id.rg_lesson_plan;
                                                                                                            RadioGroup radioGroup2 = (RadioGroup) rootView.findViewById(R.id.rg_lesson_plan);
                                                                                                            if (radioGroup2 != null) {
                                                                                                                i = R.id.rg_scheme_of_studies;
                                                                                                                RadioGroup radioGroup3 = (RadioGroup) rootView.findViewById(R.id.rg_scheme_of_studies);
                                                                                                                if (radioGroup3 != null) {
                                                                                                                    i = R.id.rg_time_table;
                                                                                                                    RadioGroup radioGroup4 = (RadioGroup) rootView.findViewById(R.id.rg_time_table);
                                                                                                                    if (radioGroup4 != null) {
                                                                                                                        i = R.id.save_button;
                                                                                                                        Button button2 = (Button) rootView.findViewById(R.id.save_button);
                                                                                                                        if (button2 != null) {
                                                                                                                            i = R.id.skip_button;
                                                                                                                            Button button3 = (Button) rootView.findViewById(R.id.skip_button);
                                                                                                                            if (button3 != null) {
                                                                                                                                i = R.id.tv_enrollment_detail;
                                                                                                                                TextView textView = (TextView) rootView.findViewById(R.id.tv_enrollment_detail);
                                                                                                                                if (textView != null) {
                                                                                                                                    i = R.id.tv_female_two_months;
                                                                                                                                    TextView textView2 = (TextView) rootView.findViewById(R.id.tv_female_two_months);
                                                                                                                                    if (textView2 != null) {
                                                                                                                                        i = R.id.tv_male_two_months;
                                                                                                                                        TextView textView3 = (TextView) rootView.findViewById(R.id.tv_male_two_months);
                                                                                                                                        if (textView3 != null) {
                                                                                                                                            i = R.id.tv_total_no_of_classrooms;
                                                                                                                                            TextView textView4 = (TextView) rootView.findViewById(R.id.tv_total_no_of_classrooms);
                                                                                                                                            if (textView4 != null) {
                                                                                                                                                i = R.id.tv_total_no_of_classrooms1;
                                                                                                                                                TextView textView5 = (TextView) rootView.findViewById(R.id.tv_total_no_of_classrooms1);
                                                                                                                                                if (textView5 != null) {
                                                                                                                                                    i = R.id.tv_total_no_of_classrooms3;
                                                                                                                                                    TextView textView6 = (TextView) rootView.findViewById(R.id.tv_total_no_of_classrooms3);
                                                                                                                                                    if (textView6 != null) {
                                                                                                                                                        i = R.id.tv_total_no_of_classrooms4;
                                                                                                                                                        TextView textView7 = (TextView) rootView.findViewById(R.id.tv_total_no_of_classrooms4);
                                                                                                                                                        if (textView7 != null) {
                                                                                                                                                            i = R.id.tv_total_no_of_classrooms5;
                                                                                                                                                            TextView textView8 = (TextView) rootView.findViewById(R.id.tv_total_no_of_classrooms5);
                                                                                                                                                            if (textView8 != null) {
                                                                                                                                                                i = R.id.tv_total_no_of_classrooms6;
                                                                                                                                                                TextView textView9 = (TextView) rootView.findViewById(R.id.tv_total_no_of_classrooms6);
                                                                                                                                                                if (textView9 != null) {
                                                                                                                                                                    i = R.id.tv_total_no_of_classrooms7;
                                                                                                                                                                    TextView textView10 = (TextView) rootView.findViewById(R.id.tv_total_no_of_classrooms7);
                                                                                                                                                                    if (textView10 != null) {
                                                                                                                                                                        return new FragEnrollementElemantaryDetailBinding((LinearLayout) rootView, button, editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, radioButton, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7, radioButton8, radioGroup, radioGroup2, radioGroup3, radioGroup4, button2, button3, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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
