package com.micromerger.ssms.startmonitoring.fragments.enrollment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.map.HashedMap;

/* loaded from: classes2.dex */
public class EnrollmentDetail extends BaseFragment implements View.OnClickListener {
    Button cancel_button;
    String category;
    EditText et_absent_female_two_months;
    EditText et_absent_male_two_months;
    EditText et_female_enroll;
    EditText et_female_present;
    EditText et_male_enroll;
    EditText et_male_present;
    EditText et_total_enroll;
    EditText et_total_present;
    EditText et_total_uniform;
    RelativeLayout female_students_absent_two_months;
    RelativeLayout female_students_enrollment;
    RelativeLayout female_students_present;
    View mView;
    RelativeLayout male_students_absent_two_months;
    RelativeLayout male_students_enrollment;
    RelativeLayout male_students_present;
    RadioGroup rg_daily_diary;
    RadioGroup rg_lesson_plan;
    RadioGroup rg_scheme_of_studies;
    RadioGroup rg_time_table;
    Button save_button;
    Button skip_button;
    String title;
    TextView tv_enrollment_detail;
    HashedMap<Integer, KRAData> enrollment = new HashedMap<>();
    int lessonPlanId = -1;
    int schemeOfStudiesId = -1;
    int dailyDiaryId = -1;
    int timeTableId = -1;

    public EnrollmentDetail(String title) {
        this.title = "";
        this.category = "";
        this.title = title;
        this.category = "";
    }

    public EnrollmentDetail(String title, String category) {
        this.title = "";
        this.category = "";
        this.title = title;
        this.category = category;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_enrollement_elemantary_detail, (ViewGroup) null);
        this.mView = viewInflate;
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.enrollment_fields.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            KRAData value = entry.getValue();
            if (value.getKRAName().contains("_" + this.title + this.category)) {
                Log.e("enrollment", entry.getKey() + entry.getValue().getKRAName());
                this.enrollment.put(Integer.valueOf(iIntValue), value);
            }
        }
        this.tv_enrollment_detail = (TextView) view.findViewById(R.id.tv_enrollment_detail);
        if (this.title.equals(Constant.ECE_Katchi)) {
            this.tv_enrollment_detail.setText("Class ECE/Katchi details");
        } else {
            this.tv_enrollment_detail.setText("Class " + this.title + " details");
        }
        EditText editText = (EditText) view.findViewById(R.id.et_male_enroll);
        this.et_male_enroll = editText;
        editText.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.EnrollmentDetail.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                EnrollmentDetail.this.setTotalEnrollment();
            }
        });
        EditText editText2 = (EditText) view.findViewById(R.id.et_female_enroll);
        this.et_female_enroll = editText2;
        editText2.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.EnrollmentDetail.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                EnrollmentDetail.this.setTotalEnrollment();
            }
        });
        this.et_total_enroll = (EditText) view.findViewById(R.id.et_total_enroll);
        EditText editText3 = (EditText) view.findViewById(R.id.et_male_present);
        this.et_male_present = editText3;
        editText3.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.EnrollmentDetail.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                EnrollmentDetail.this.setTotalPresent();
            }
        });
        EditText editText4 = (EditText) view.findViewById(R.id.et_female_present);
        this.et_female_present = editText4;
        editText4.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.EnrollmentDetail.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                EnrollmentDetail.this.setTotalPresent();
            }
        });
        this.et_total_present = (EditText) view.findViewById(R.id.et_total_present);
        this.et_total_uniform = (EditText) view.findViewById(R.id.et_total_uniform);
        this.et_absent_male_two_months = (EditText) view.findViewById(R.id.et_absent_male_two_months);
        this.et_absent_female_two_months = (EditText) view.findViewById(R.id.et_absent_female_two_months);
        Button button = (Button) view.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.skip_button);
        this.skip_button = button2;
        button2.setOnClickListener(this);
        Button button3 = (Button) view.findViewById(R.id.cancel_button);
        this.cancel_button = button3;
        button3.setOnClickListener(this);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_lesson_plan);
        this.rg_lesson_plan = radioGroup;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.-$$Lambda$EnrollmentDetail$YCbHCNLn1Wv8Ewuo21eTNuKDDUc
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i) {
                this.f$0.lambda$onViewCreated$0$EnrollmentDetail(radioGroup2, i);
            }
        });
        RadioGroup radioGroup2 = (RadioGroup) view.findViewById(R.id.rg_scheme_of_studies);
        this.rg_scheme_of_studies = radioGroup2;
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.-$$Lambda$EnrollmentDetail$zQo6FrFAxj_NVsPXvNaFBzj190U
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup3, int i) {
                this.f$0.lambda$onViewCreated$1$EnrollmentDetail(radioGroup3, i);
            }
        });
        RadioGroup radioGroup3 = (RadioGroup) view.findViewById(R.id.rg_daily_diary);
        this.rg_daily_diary = radioGroup3;
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.-$$Lambda$EnrollmentDetail$J7kLshbFEog5cxRDKQxVd6yEJbo
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup4, int i) {
                this.f$0.lambda$onViewCreated$2$EnrollmentDetail(radioGroup4, i);
            }
        });
        RadioGroup radioGroup4 = (RadioGroup) view.findViewById(R.id.rg_time_table);
        this.rg_time_table = radioGroup4;
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.-$$Lambda$EnrollmentDetail$TqPrsmLDKe1ldxHfsRm_AmuOvSA
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup5, int i) {
                this.f$0.lambda$onViewCreated$3$EnrollmentDetail(radioGroup5, i);
            }
        });
        this.male_students_enrollment = (RelativeLayout) view.findViewById(R.id.male_students_enrollment);
        this.female_students_enrollment = (RelativeLayout) view.findViewById(R.id.female_students_enrollment);
        this.male_students_present = (RelativeLayout) view.findViewById(R.id.male_students_present);
        this.female_students_present = (RelativeLayout) view.findViewById(R.id.female_students_present);
        this.male_students_absent_two_months = (RelativeLayout) view.findViewById(R.id.male_students_absent_two_months);
        this.female_students_absent_two_months = (RelativeLayout) view.findViewById(R.id.female_students_absent_two_months);
        renderUIWithSchoolGender();
    }

    public /* synthetic */ void lambda$onViewCreated$0$EnrollmentDetail(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_lesson_plan_yes) {
            this.lessonPlanId = 0;
        } else if (i == R.id.rb_lesson_plan_no) {
            this.lessonPlanId = 1;
        }
    }

    public /* synthetic */ void lambda$onViewCreated$1$EnrollmentDetail(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_scheme_of_studies_yes) {
            this.schemeOfStudiesId = 0;
        } else if (i == R.id.rb_scheme_of_studies_no) {
            this.schemeOfStudiesId = 1;
        }
    }

    public /* synthetic */ void lambda$onViewCreated$2$EnrollmentDetail(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_daily_diary_yes) {
            this.dailyDiaryId = 0;
        } else if (i == R.id.rb_daily_diary_no) {
            this.dailyDiaryId = 1;
        }
    }

    public /* synthetic */ void lambda$onViewCreated$3$EnrollmentDetail(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_time_table_yes) {
            this.timeTableId = 0;
        } else if (i == R.id.rb_time_table_no) {
            this.timeTableId = 1;
        }
    }

    private void renderUIWithSchoolGender() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (CommonObjects.monitoring != null) {
            int iIntValue = CommonObjects.monitoring.getSchoolGenderTypeId().intValue();
            if (iIntValue == 1) {
                arrayList2.add(this.et_female_enroll);
                arrayList2.add(this.et_female_present);
                arrayList2.add(this.et_absent_female_two_months);
                arrayList.add(this.female_students_enrollment);
                arrayList.add(this.female_students_present);
                arrayList.add(this.female_students_absent_two_months);
            } else if (iIntValue == 2) {
                arrayList2.add(this.et_male_enroll);
                arrayList2.add(this.et_male_present);
                arrayList2.add(this.et_absent_male_two_months);
                arrayList.add(this.male_students_enrollment);
                arrayList.add(this.male_students_present);
                arrayList.add(this.male_students_absent_two_months);
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                TextView textView = (TextView) it.next();
                textView.setText(Constant.ECE_Katchi);
                textView.setFocusable(false);
                textView.setEnabled(false);
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                RelativeLayout relativeLayout = (RelativeLayout) it2.next();
                relativeLayout.getLayoutParams().height = 0;
                relativeLayout.getLayoutParams().width = 0;
                ((LinearLayout.LayoutParams) relativeLayout.getLayoutParams()).setMargins(0, 0, 0, 0);
            }
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        SSMS.setCurrentFragment(this);
        String str = "";
        String str2 = str;
        String str3 = str2;
        String str4 = str3;
        String str5 = str4;
        String str6 = str5;
        for (Map.Entry<Integer, KRAData> entry : this.enrollment.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Total_Male_Students_ + this.title + this.category)) {
                    str4 = (String) value.getDataValue();
                    if (CommonObjects.monitoring.getSchoolGenderTypeId().intValue() == 2) {
                        this.et_male_enroll.setText(Constant.ECE_Katchi);
                    } else {
                        this.et_male_enroll.setText(str4);
                    }
                } else {
                    if (value.getKRAName().equals(Constant.Total_Female_Students_ + this.title + this.category)) {
                        str3 = (String) value.getDataValue();
                        if (CommonObjects.monitoring.getSchoolGenderTypeId().intValue() == 1) {
                            this.et_female_enroll.setText(Constant.ECE_Katchi);
                        } else {
                            this.et_female_enroll.setText(str3);
                        }
                    } else {
                        if (value.getKRAName().equals(Constant.Total_Students_Enrollment_ + this.title + this.category)) {
                            str = (String) value.getDataValue();
                            this.et_total_enroll.setText((String) value.getDataValue());
                        } else {
                            if (value.getKRAName().equals(Constant.Total_Male_Students_Present_ + this.title + this.category)) {
                                str6 = (String) value.getDataValue();
                                if (CommonObjects.monitoring.getSchoolGenderTypeId().intValue() == 2) {
                                    this.et_male_present.setText(Constant.ECE_Katchi);
                                } else {
                                    this.et_male_present.setText(str6);
                                }
                            } else {
                                if (value.getKRAName().equals(Constant.Total_Female_Students_Present_ + this.title + this.category)) {
                                    str5 = (String) value.getDataValue();
                                    if (CommonObjects.monitoring.getSchoolGenderTypeId().intValue() == 1) {
                                        this.et_female_present.setText(Constant.ECE_Katchi);
                                    } else {
                                        this.et_female_present.setText(str5);
                                    }
                                } else {
                                    if (value.getKRAName().equals(Constant.Total_Students_Present_ + this.title + this.category)) {
                                        str2 = (String) value.getDataValue();
                                        this.et_total_present.setText((String) value.getDataValue());
                                    } else {
                                        if (value.getKRAName().equals(Constant.Students_In_Uniform_ + this.title + this.category)) {
                                            this.et_total_uniform.setText((String) value.getDataValue());
                                        } else {
                                            if (value.getKRAName().equals(Constant.Is_Completed_ + this.title + this.category)) {
                                                Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                                            } else {
                                                if (value.getKRAName().equalsIgnoreCase(Constant.Lesson_Plan_Available_ + this.title + this.category)) {
                                                    ((RadioButton) this.rg_lesson_plan.getChildAt(Integer.parseInt((String) value.getDataValue()))).setChecked(true);
                                                } else {
                                                    if (value.getKRAName().equalsIgnoreCase(Constant.Scheme_of_Studies_ + this.title + this.category)) {
                                                        ((RadioButton) this.rg_scheme_of_studies.getChildAt(Integer.parseInt((String) value.getDataValue()))).setChecked(true);
                                                    } else {
                                                        if (value.getKRAName().equalsIgnoreCase(Constant.Daily_Diary_ + this.title + this.category)) {
                                                            ((RadioButton) this.rg_daily_diary.getChildAt(Integer.parseInt((String) value.getDataValue()))).setChecked(true);
                                                        } else {
                                                            if (value.getKRAName().equalsIgnoreCase(Constant.Time_Table_ + this.title + this.category)) {
                                                                ((RadioButton) this.rg_time_table.getChildAt(Integer.parseInt((String) value.getDataValue()))).setChecked(true);
                                                            } else {
                                                                if (value.getKRAName().equalsIgnoreCase("Absent_Male_For_Last_Two_Month_" + this.title + this.category)) {
                                                                    this.et_absent_male_two_months.setText((String) value.getDataValue());
                                                                } else {
                                                                    if (value.getKRAName().equalsIgnoreCase("Absent_Female_For_Last_Two_Month_" + this.title + this.category)) {
                                                                        this.et_absent_female_two_months.setText((String) value.getDataValue());
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
        if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty()) {
            int i = Integer.parseInt(str);
            int i2 = Integer.parseInt(str2);
            if (CommonObjects.monitoring.getSchoolGenderTypeId().intValue() == 1) {
                if (str3 != null && !str3.isEmpty()) {
                    this.et_total_enroll.setText(String.valueOf(i - Integer.parseInt(str3)));
                }
                if (str5 != null && !str5.isEmpty()) {
                    this.et_total_present.setText(String.valueOf(i2 - Integer.parseInt(str5)));
                }
            } else if (CommonObjects.monitoring.getSchoolGenderTypeId().intValue() == 2) {
                if (str4 != null && !str4.isEmpty()) {
                    this.et_total_enroll.setText(String.valueOf(i - Integer.parseInt(str4)));
                }
                if (str6 != null && !str6.isEmpty()) {
                    this.et_total_present.setText(String.valueOf(i2 - Integer.parseInt(str6)));
                }
            }
        }
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.et_male_enroll.setEnabled(false);
            this.et_male_enroll.setFocusable(false);
            this.et_female_enroll.setEnabled(false);
            this.et_female_enroll.setFocusable(false);
            this.et_total_enroll.setEnabled(false);
            this.et_total_enroll.setFocusable(false);
            this.et_male_present.setEnabled(false);
            this.et_male_present.setFocusable(false);
            this.et_female_present.setFocusable(false);
            this.et_female_present.setEnabled(false);
            this.et_total_present.setFocusable(false);
            this.et_total_present.setEnabled(false);
            this.et_total_uniform.setFocusable(false);
            this.et_total_uniform.setEnabled(false);
            this.et_absent_male_two_months.setEnabled(false);
            this.et_absent_male_two_months.setFocusable(false);
            this.et_absent_female_two_months.setEnabled(false);
            this.et_absent_female_two_months.setFocusable(false);
            this.rg_lesson_plan.setEnabled(false);
            this.rg_lesson_plan.setFocusable(false);
            for (int i3 = 0; i3 < this.rg_lesson_plan.getChildCount(); i3++) {
                ((RadioButton) this.rg_lesson_plan.getChildAt(i3)).setFocusable(false);
                ((RadioButton) this.rg_lesson_plan.getChildAt(i3)).setEnabled(false);
            }
            this.rg_scheme_of_studies.setEnabled(false);
            this.rg_scheme_of_studies.setFocusable(false);
            for (int i4 = 0; i4 < this.rg_scheme_of_studies.getChildCount(); i4++) {
                ((RadioButton) this.rg_scheme_of_studies.getChildAt(i4)).setFocusable(false);
                ((RadioButton) this.rg_scheme_of_studies.getChildAt(i4)).setEnabled(false);
            }
            this.rg_daily_diary.setEnabled(false);
            this.rg_daily_diary.setFocusable(false);
            for (int i5 = 0; i5 < this.rg_daily_diary.getChildCount(); i5++) {
                ((RadioButton) this.rg_daily_diary.getChildAt(i5)).setFocusable(false);
                ((RadioButton) this.rg_daily_diary.getChildAt(i5)).setEnabled(false);
            }
            this.rg_time_table.setEnabled(false);
            this.rg_time_table.setFocusable(false);
            for (int i6 = 0; i6 < this.rg_time_table.getChildCount(); i6++) {
                ((RadioButton) this.rg_time_table.getChildAt(i6)).setFocusable(false);
                ((RadioButton) this.rg_time_table.getChildAt(i6)).setEnabled(false);
            }
            this.save_button.setEnabled(false);
            this.save_button.setAlpha(0.5f);
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id2 = view.getId();
        if (id2 == R.id.cancel_button) {
            checkData();
        } else if (id2 == R.id.save_button) {
            checkValidation();
        } else {
            if (id2 != R.id.skip_button) {
                return;
            }
            skipForm();
        }
    }

    private void checkValidation() {
        if (this.et_male_enroll.getText().toString().length() > 0 && this.et_female_enroll.getText().toString().length() > 0 && this.et_total_enroll.getText().toString().length() > 0 && this.et_male_present.getText().toString().length() > 0 && this.et_female_present.getText().toString().length() > 0 && this.et_total_present.getText().toString().length() > 0 && this.et_total_uniform.getText().toString().length() > 0 && this.et_absent_male_two_months.getText().toString().length() > 0 && this.et_absent_female_two_months.getText().toString().length() > 0) {
            if (this.lessonPlanId == -1) {
                DialogCustom.showError(getActivityContext(), "Please select lesson plan.");
                return;
            }
            if (this.schemeOfStudiesId == -1) {
                DialogCustom.showError(getActivityContext(), "Please select scheme of studies.");
                return;
            }
            if (this.dailyDiaryId == -1) {
                DialogCustom.showError(getActivityContext(), "Please select daily diary.");
                return;
            }
            if (this.timeTableId == -1) {
                DialogCustom.showError(getActivityContext(), "Please select time table.");
                return;
            }
            int i = this.et_male_present.getText().toString().length() > 0 ? Integer.parseInt(this.et_male_present.getText().toString()) : 0;
            int i2 = this.et_female_present.getText().toString().length() > 0 ? Integer.parseInt(this.et_female_present.getText().toString()) : 0;
            if (Integer.parseInt(this.et_male_present.getText().toString()) <= Integer.parseInt(this.et_male_enroll.getText().toString())) {
                if (Integer.parseInt(this.et_female_present.getText().toString()) <= Integer.parseInt(this.et_female_enroll.getText().toString())) {
                    if (Integer.parseInt(this.et_total_uniform.getText().toString()) <= i + i2) {
                        if (Integer.parseInt(this.et_male_enroll.getText().toString()) >= Integer.parseInt(this.et_male_present.getText().toString()) + Integer.parseInt(this.et_absent_male_two_months.getText().toString())) {
                            if (Integer.parseInt(this.et_female_enroll.getText().toString()) >= Integer.parseInt(this.et_female_present.getText().toString()) + Integer.parseInt(this.et_absent_female_two_months.getText().toString())) {
                                saveFieldsData();
                                return;
                            } else {
                                DialogCustom.showError(getActivityContext(), "Sum of Female students present and Female students absent for last two months should not be greater than total female students.");
                                return;
                            }
                        }
                        DialogCustom.showError(getActivityContext(), "Sum of Male students present and Male students absent for last two months should not be greater than total male students.");
                        return;
                    }
                    DialogCustom.showError(getActivityContext(), "Sum of male and female students in uniform should not be greater than sum of male and female attendance.");
                    return;
                }
                DialogCustom.showError(getActivityContext(), "Present female students should not be greater than present female enrollments.");
                return;
            }
            DialogCustom.showError(getActivityContext(), "Present male students should not be greater than present male enrollments.");
            return;
        }
        DialogCustom.showError(getActivityContext(), "Please fill the all fields");
        if (this.et_male_enroll.getText().toString().equals("")) {
            showError(this.et_male_enroll);
        } else if (this.et_female_enroll.getText().toString().equals("")) {
            showError(this.et_female_enroll);
        } else if (this.et_total_enroll.getText().toString().equals("")) {
            showError(this.et_total_enroll);
        } else if (this.et_male_present.getText().toString().equals("")) {
            showError(this.et_male_present);
        } else if (this.et_female_present.getText().toString().equals("")) {
            showError(this.et_female_present);
        } else if (this.et_total_present.getText().toString().equals("")) {
            showError(this.et_total_present);
        }
        if (this.et_absent_male_two_months.getText().toString().equals("")) {
            showError(this.et_absent_male_two_months);
        }
        if (this.et_absent_female_two_months.getText().toString().equals("")) {
            showError(this.et_absent_female_two_months);
        }
    }

    private void showError(EditText et) {
        et.setError("Please fill the field.");
        et.setFocusableInTouchMode(true);
        et.setFocusable(true);
        et.getParent().requestChildFocus(et, et);
    }

    private void skipForm() {
        this.et_male_enroll.setText(Constant.ECE_Katchi);
        this.et_female_enroll.setText(Constant.ECE_Katchi);
        this.et_male_present.setText(Constant.ECE_Katchi);
        this.et_female_present.setText(Constant.ECE_Katchi);
        this.et_total_uniform.setText(Constant.ECE_Katchi);
        this.et_absent_male_two_months.setText(Constant.ECE_Katchi);
        this.et_absent_female_two_months.setText(Constant.ECE_Katchi);
        this.lessonPlanId = 1;
        this.schemeOfStudiesId = 1;
        this.dailyDiaryId = 1;
        this.timeTableId = 1;
        saveFieldsData();
    }

    private void checkData() {
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.fm.popBackStack();
            return;
        }
        if (this.et_male_enroll.getText().toString().length() > 0 || this.et_female_enroll.getText().toString().length() > 0 || this.et_male_present.getText().toString().length() > 0 || this.et_female_present.getText().toString().length() > 0 || this.et_total_uniform.getText().toString().length() > 0 || this.et_absent_male_two_months.getText().toString().length() > 0 || this.et_absent_female_two_months.getText().toString().length() > 0 || this.lessonPlanId != -1 || this.schemeOfStudiesId != -1 || this.dailyDiaryId != -1 || this.timeTableId != -1) {
            DialogCustom.showCancelWarning(getActivityContext(), this.fm);
        } else {
            this.fm.popBackStack();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalEnrollment() {
        int i = this.et_male_enroll.getText().toString().length() > 0 ? Integer.parseInt(this.et_male_enroll.getText().toString()) : 0;
        int i2 = this.et_female_enroll.getText().toString().length() > 0 ? Integer.parseInt(this.et_female_enroll.getText().toString()) : 0;
        if (i == 0 && i2 == 0) {
            this.et_total_enroll.setText(Constant.ECE_Katchi);
        } else {
            this.et_total_enroll.setText(String.valueOf(i + i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalPresent() {
        int i = this.et_male_present.getText().toString().length() > 0 ? Integer.parseInt(this.et_male_present.getText().toString()) : 0;
        int i2 = this.et_female_present.getText().toString().length() > 0 ? Integer.parseInt(this.et_female_present.getText().toString()) : 0;
        if (i == 0 && i2 == 0) {
            this.et_total_present.setText(Constant.ECE_Katchi);
        } else {
            this.et_total_present.setText(String.valueOf(i + i2));
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        checkData();
        return true;
    }

    private void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.enrollment.EnrollmentDetail.5
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : EnrollmentDetail.this.enrollment.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.Total_Male_Students_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                        value.setDataValue(EnrollmentDetail.this.et_male_enroll.getText().toString());
                        Log.d("KRADATASAVE : ", value.getKRAName() + " = " + value.getDataValue());
                    } else {
                        if (value.getKRAName().equals(Constant.Total_Female_Students_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                            value.setDataValue(EnrollmentDetail.this.et_female_enroll.getText().toString());
                            Log.d("KRADATASAVE : ", value.getKRAName() + " = " + value.getDataValue());
                        } else {
                            if (value.getKRAName().equals(Constant.Total_Students_Enrollment_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                value.setDataValue(EnrollmentDetail.this.et_total_enroll.getText().toString());
                                Log.d("KRADATASAVE : ", value.getKRAName() + " = " + value.getDataValue());
                            } else {
                                if (value.getKRAName().equals(Constant.Total_Male_Students_Present_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                    value.setDataValue(EnrollmentDetail.this.et_male_present.getText().toString());
                                    Log.d("KRADATASAVE : ", value.getKRAName() + " = " + value.getDataValue());
                                } else {
                                    if (value.getKRAName().equals(Constant.Total_Female_Students_Present_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                        value.setDataValue(EnrollmentDetail.this.et_female_present.getText().toString());
                                        Log.d("KRADATASAVE : ", value.getKRAName() + " = " + value.getDataValue());
                                    } else {
                                        if (value.getKRAName().equals(Constant.Total_Students_Present_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                            value.setDataValue(EnrollmentDetail.this.et_total_present.getText().toString());
                                            Log.d("KRADATASAVE : ", value.getKRAName() + " = " + value.getDataValue());
                                        } else {
                                            if (value.getKRAName().equals(Constant.Students_In_Uniform_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                                value.setDataValue(EnrollmentDetail.this.et_total_uniform.getText().toString());
                                                Log.d("KRADATASAVE : ", value.getKRAName() + " = " + value.getDataValue());
                                            } else {
                                                if (value.getKRAName().equals(Constant.Is_Completed_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                                    value.setDataValue(true);
                                                    Log.d("KRADATASAVE : ", value.getKRAName() + " = " + value.getDataValue());
                                                } else {
                                                    if (!value.getKRAName().equalsIgnoreCase(Constant.Lesson_Plan_Available_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                                        if (!value.getKRAName().equalsIgnoreCase(Constant.Scheme_of_Studies_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                                            if (!value.getKRAName().equalsIgnoreCase(Constant.Daily_Diary_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                                                if (!value.getKRAName().equalsIgnoreCase(Constant.Time_Table_ + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                                                    if (value.getKRAName().equalsIgnoreCase("Absent_Male_For_Last_Two_Month_" + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                                                        value.setDataValue(EnrollmentDetail.this.et_absent_male_two_months.getText().toString());
                                                                    } else {
                                                                        if (value.getKRAName().equalsIgnoreCase("Absent_Female_For_Last_Two_Month_" + EnrollmentDetail.this.title + EnrollmentDetail.this.category)) {
                                                                            value.setDataValue(EnrollmentDetail.this.et_absent_female_two_months.getText().toString());
                                                                        }
                                                                    }
                                                                } else if (EnrollmentDetail.this.timeTableId != -1) {
                                                                    value.setDataValue(String.valueOf(EnrollmentDetail.this.timeTableId));
                                                                }
                                                            } else if (EnrollmentDetail.this.dailyDiaryId != -1) {
                                                                value.setDataValue(String.valueOf(EnrollmentDetail.this.dailyDiaryId));
                                                            }
                                                        } else if (EnrollmentDetail.this.schemeOfStudiesId != -1) {
                                                            value.setDataValue(String.valueOf(EnrollmentDetail.this.schemeOfStudiesId));
                                                        }
                                                    } else if (EnrollmentDetail.this.lessonPlanId != -1) {
                                                        value.setDataValue(String.valueOf(EnrollmentDetail.this.lessonPlanId));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    CommonObjects.monitoring.getMonitoring().get(0).getKRAData().set(key.intValue(), value);
                }
                for (int i = 0; i < CommonObjects.employeeData.size(); i++) {
                    if (CommonObjects.employeeData.get(i).getMonitoring().get(0).getMonitoringID() == CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID()) {
                        CommonObjects.employeeData.set(i, CommonObjects.monitoring);
                        CommonActions.getDbHandler(EnrollmentDetail.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        return;
                    }
                }
            }
        }).start();
        if (getActivity() != null) {
            this.fm.popBackStack();
        }
    }
}
