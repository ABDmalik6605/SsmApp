package com.micromerger.ssms.startmonitoring.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.map.HashedMap;

/* loaded from: classes2.dex */
public class TextbooksDetail extends BaseFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    Button cancel_button;
    EditText editText_comments;
    EditText et_textbooks_distributed;
    EditText et_textbooks_recieved;
    EditText et_textbooks_requested;
    EditText et_textbooks_shortfalls;
    EditText et_textbooks_students;
    EditText et_textbooks_surplus;
    View mView;
    RadioGroup rg_timely_distributed;
    Button save_button;
    Button skip_button;
    String title;
    TextView tv_textbooks_detail;
    String tbRequested = "";
    String tbReceived = "";
    String tbDistributed = "";
    String tbWithStudents = "";
    String tbSurplus = "";
    String tbShortfalls = "";
    String comments = "";
    int isTimelyDistributed = -1;
    String enrolledStudents = "";
    HashedMap<Integer, KRAData> classroom = new HashedMap<>();

    public TextbooksDetail(String s) {
        this.title = s;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_textbooks_detail, (ViewGroup) null);
        this.mView = viewInflate;
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.textbooks.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            KRAData value = entry.getValue();
            if (value.getKRAName().contains("_" + this.title)) {
                this.classroom.put(Integer.valueOf(iIntValue), value);
            }
        }
        Iterator<Map.Entry<Integer, KRAData>> it = CommonObjects.enrollment_fields.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<Integer, KRAData> next = it.next();
            next.getKey();
            KRAData value2 = next.getValue();
            if (value2.getDataValue() != null && !value2.getDataValue().equals("")) {
                if (value2.getKRAName().equals(Constant.Total_Students_Enrollment_ + this.title)) {
                    this.enrolledStudents = String.valueOf(value2.getDataValue());
                    break;
                }
            }
        }
        this.tv_textbooks_detail = (TextView) view.findViewById(R.id.tv_textbooks_detail);
        if (this.title.equals(Constant.ECE_Katchi)) {
            this.tv_textbooks_detail.setText("Class ECE/Katchi");
        } else {
            this.tv_textbooks_detail.setText("Class " + this.title);
        }
        EditText editText = (EditText) view.findViewById(R.id.et_textbooks_requested);
        this.et_textbooks_requested = editText;
        editText.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.TextbooksDetail.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    TextbooksDetail.this.et_textbooks_recieved.setEnabled(true);
                    TextbooksDetail.this.et_textbooks_recieved.setText(TextbooksDetail.this.tbReceived);
                } else {
                    TextbooksDetail.this.et_textbooks_recieved.setEnabled(false);
                }
            }
        });
        EditText editText2 = (EditText) view.findViewById(R.id.et_textbooks_recieved);
        this.et_textbooks_recieved = editText2;
        editText2.setEnabled(false);
        this.et_textbooks_recieved.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.startmonitoring.fragments.TextbooksDetail.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0 && TextbooksDetail.this.et_textbooks_requested.getText().toString().length() > 0) {
                    if (Integer.parseInt(TextbooksDetail.this.et_textbooks_requested.getText().toString()) == Integer.parseInt(editable.toString())) {
                        TextbooksDetail.this.et_textbooks_shortfalls.setText("");
                        TextbooksDetail.this.et_textbooks_shortfalls.setEnabled(false);
                        TextbooksDetail.this.et_textbooks_shortfalls.setBackgroundResource(R.color.light_gray);
                        TextbooksDetail.this.et_textbooks_surplus.setText("");
                        TextbooksDetail.this.et_textbooks_surplus.setEnabled(false);
                        TextbooksDetail.this.et_textbooks_surplus.setBackgroundResource(R.color.light_gray);
                        return;
                    }
                    if (Integer.parseInt(TextbooksDetail.this.et_textbooks_requested.getText().toString()) < Integer.parseInt(editable.toString())) {
                        TextbooksDetail.this.et_textbooks_shortfalls.setText("");
                        TextbooksDetail.this.et_textbooks_shortfalls.setEnabled(false);
                        TextbooksDetail.this.et_textbooks_shortfalls.setBackgroundResource(R.color.light_gray);
                        TextbooksDetail.this.et_textbooks_surplus.setText(String.valueOf(Integer.parseInt(editable.toString()) - Integer.parseInt(TextbooksDetail.this.et_textbooks_requested.getText().toString())));
                        TextbooksDetail.this.et_textbooks_surplus.setEnabled(false);
                        TextbooksDetail.this.et_textbooks_surplus.setBackground(null);
                        return;
                    }
                    TextbooksDetail.this.et_textbooks_shortfalls.setText(String.valueOf(Integer.parseInt(TextbooksDetail.this.et_textbooks_requested.getText().toString()) - Integer.parseInt(editable.toString())));
                    TextbooksDetail.this.et_textbooks_shortfalls.setEnabled(false);
                    TextbooksDetail.this.et_textbooks_shortfalls.setBackground(null);
                    TextbooksDetail.this.et_textbooks_surplus.setText("");
                    TextbooksDetail.this.et_textbooks_surplus.setEnabled(false);
                    TextbooksDetail.this.et_textbooks_surplus.setBackgroundResource(R.color.light_gray);
                    return;
                }
                TextbooksDetail.this.et_textbooks_shortfalls.setText("");
                TextbooksDetail.this.et_textbooks_shortfalls.setEnabled(false);
                TextbooksDetail.this.et_textbooks_shortfalls.setBackgroundResource(R.color.light_gray);
                TextbooksDetail.this.et_textbooks_surplus.setText("");
                TextbooksDetail.this.et_textbooks_surplus.setEnabled(false);
                TextbooksDetail.this.et_textbooks_surplus.setBackgroundResource(R.color.light_gray);
            }
        });
        this.et_textbooks_distributed = (EditText) view.findViewById(R.id.et_textbooks_distributed);
        this.et_textbooks_students = (EditText) view.findViewById(R.id.et_textbooks_students);
        EditText editText3 = (EditText) view.findViewById(R.id.et_textbooks_surplus);
        this.et_textbooks_surplus = editText3;
        editText3.setEnabled(false);
        EditText editText4 = (EditText) view.findViewById(R.id.et_textbooks_shortfalls);
        this.et_textbooks_shortfalls = editText4;
        editText4.setEnabled(false);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_timely_distributed);
        this.rg_timely_distributed = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        EditText editText5 = (EditText) view.findViewById(R.id.editText_comments);
        this.editText_comments = editText5;
        editText5.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.TextbooksDetail.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.editText_comments) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & 255) == 1) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        Button button = (Button) view.findViewById(R.id.save_button);
        this.save_button = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.skip_button);
        this.skip_button = button2;
        button2.setOnClickListener(this);
        Button button3 = (Button) view.findViewById(R.id.cancel_button);
        this.cancel_button = button3;
        button3.setOnClickListener(this);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        SSMS.setCurrentFragment(this);
        for (Map.Entry<Integer, KRAData> entry : this.classroom.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                Log.e(value.getKRAName(), value.getDataValue() + "_");
                if (value.getKRAName().equals(Constant.TextbooksRequested_ + this.title)) {
                    String str = (String) value.getDataValue();
                    this.tbRequested = str;
                    this.et_textbooks_requested.setText(str);
                } else {
                    if (value.getKRAName().equals(Constant.TextbooksReceived_ + this.title)) {
                        String str2 = (String) value.getDataValue();
                        this.tbReceived = str2;
                        this.et_textbooks_recieved.setText(str2);
                    } else {
                        if (value.getKRAName().equals(Constant.TextbooksDistributed_ + this.title)) {
                            String str3 = (String) value.getDataValue();
                            this.tbDistributed = str3;
                            this.et_textbooks_distributed.setText(str3);
                        } else {
                            if (value.getKRAName().equals(Constant.StudentsWithTextbooks_ + this.title)) {
                                String str4 = (String) value.getDataValue();
                                this.tbWithStudents = str4;
                                this.et_textbooks_students.setText(str4);
                            } else {
                                if (value.getKRAName().equals(Constant.SurplusTextbooks_ + this.title)) {
                                    String str5 = (String) value.getDataValue();
                                    this.tbSurplus = str5;
                                    this.et_textbooks_surplus.setText(str5);
                                } else {
                                    if (value.getKRAName().equals(Constant.ShortfallTextbooks_ + this.title)) {
                                        String str6 = (String) value.getDataValue();
                                        this.tbShortfalls = str6;
                                        this.et_textbooks_shortfalls.setText(str6);
                                    } else {
                                        if (value.getKRAName().equals(Constant.TimelyDistributed_ + this.title)) {
                                            int i = Integer.parseInt((String) value.getDataValue());
                                            this.isTimelyDistributed = i;
                                            ((RadioButton) this.rg_timely_distributed.getChildAt(i)).setChecked(true);
                                        } else {
                                            if (value.getKRAName().equals(Constant.Comments_ + this.title)) {
                                                String str7 = (String) value.getDataValue();
                                                this.comments = str7;
                                                this.editText_comments.setText(str7);
                                            } else {
                                                if (value.getKRAName().equals(Constant.Is_Completed_ + this.title)) {
                                                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
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
        if (CommonObjects.monitoring == null || CommonObjects.monitoring.getMonitoring() == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.rg_timely_distributed.setEnabled(false);
        this.rg_timely_distributed.setFocusable(false);
        for (int i2 = 0; i2 < this.rg_timely_distributed.getChildCount(); i2++) {
            ((RadioButton) this.rg_timely_distributed.getChildAt(i2)).setFocusable(false);
            ((RadioButton) this.rg_timely_distributed.getChildAt(i2)).setEnabled(false);
        }
        this.et_textbooks_requested.setEnabled(false);
        this.et_textbooks_requested.setFocusable(false);
        this.et_textbooks_recieved.setEnabled(false);
        this.et_textbooks_recieved.setFocusable(false);
        this.et_textbooks_distributed.setEnabled(false);
        this.et_textbooks_distributed.setFocusable(false);
        this.et_textbooks_students.setEnabled(false);
        this.et_textbooks_students.setFocusable(false);
        this.et_textbooks_surplus.setFocusable(false);
        this.et_textbooks_surplus.setEnabled(false);
        this.et_textbooks_shortfalls.setFocusable(false);
        this.et_textbooks_shortfalls.setEnabled(false);
        this.editText_comments.setFocusable(false);
        this.editText_comments.setEnabled(false);
        this.save_button.setEnabled(false);
        this.save_button.setAlpha(0.5f);
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

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_no) {
            this.isTimelyDistributed = 1;
        } else {
            if (i != R.id.rb_yes) {
                return;
            }
            this.isTimelyDistributed = 0;
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        checkData();
        return true;
    }

    private void checkValidation() {
        this.tbRequested = this.et_textbooks_requested.getText().toString();
        this.tbReceived = this.et_textbooks_recieved.getText().toString();
        this.tbDistributed = this.et_textbooks_distributed.getText().toString();
        this.tbWithStudents = this.et_textbooks_students.getText().toString();
        this.tbSurplus = this.et_textbooks_surplus.getText().toString();
        this.tbShortfalls = this.et_textbooks_shortfalls.getText().toString();
        this.comments = this.editText_comments.getText().toString();
        if (!this.tbRequested.equals("")) {
            if (!this.tbReceived.equals("")) {
                if (!this.tbDistributed.equals("")) {
                    if (Integer.parseInt(this.tbDistributed) <= Integer.parseInt(this.tbReceived)) {
                        if (!this.tbWithStudents.equals("")) {
                            if (this.enrolledStudents.length() > 0) {
                                if (Integer.parseInt(this.tbWithStudents) <= Integer.parseInt(this.enrolledStudents)) {
                                    if (this.isTimelyDistributed > -1) {
                                        saveFieldsData();
                                        return;
                                    }
                                    this.rg_timely_distributed.setFocusableInTouchMode(true);
                                    this.rg_timely_distributed.setFocusable(true);
                                    DialogCustom.showError(getActivityContext(), "Please select timely distributed.");
                                    ViewParent parent = this.rg_timely_distributed.getParent();
                                    RadioGroup radioGroup = this.rg_timely_distributed;
                                    parent.requestChildFocus(radioGroup, radioGroup);
                                    return;
                                }
                                DialogCustom.showError(getActivityContext(), "No. of students with textbooks should not be greater than no. of students enrolled for that class");
                                return;
                            }
                            if (this.isTimelyDistributed > -1) {
                                saveFieldsData();
                                return;
                            }
                            this.rg_timely_distributed.setFocusableInTouchMode(true);
                            this.rg_timely_distributed.setFocusable(true);
                            DialogCustom.showError(getActivityContext(), "Please select timely distributed.");
                            ViewParent parent2 = this.rg_timely_distributed.getParent();
                            RadioGroup radioGroup2 = this.rg_timely_distributed;
                            parent2.requestChildFocus(radioGroup2, radioGroup2);
                            return;
                        }
                        this.et_textbooks_students.setError("Please fill the field.");
                        this.et_textbooks_students.setFocusableInTouchMode(true);
                        this.et_textbooks_students.setFocusable(true);
                        DialogCustom.showError(getActivityContext(), "Please fill the all fields.");
                        ViewParent parent3 = this.et_textbooks_students.getParent();
                        EditText editText = this.et_textbooks_students;
                        parent3.requestChildFocus(editText, editText);
                        return;
                    }
                    this.et_textbooks_distributed.setFocusableInTouchMode(true);
                    this.et_textbooks_distributed.setFocusable(true);
                    DialogCustom.showError(getActivityContext(), "Books distributed cannot be greater than books received");
                    ViewParent parent4 = this.et_textbooks_distributed.getParent();
                    EditText editText2 = this.et_textbooks_distributed;
                    parent4.requestChildFocus(editText2, editText2);
                    return;
                }
                this.et_textbooks_distributed.setError("Please fill the field.");
                this.et_textbooks_distributed.setFocusableInTouchMode(true);
                this.et_textbooks_distributed.setFocusable(true);
                DialogCustom.showError(getActivityContext(), "Please fill the all fields.");
                ViewParent parent5 = this.et_textbooks_distributed.getParent();
                EditText editText3 = this.et_textbooks_distributed;
                parent5.requestChildFocus(editText3, editText3);
                return;
            }
            this.et_textbooks_recieved.setError("Please fill the field.");
            this.et_textbooks_recieved.setFocusableInTouchMode(true);
            this.et_textbooks_recieved.setFocusable(true);
            DialogCustom.showError(getActivityContext(), "Please fill the all fields.");
            ViewParent parent6 = this.et_textbooks_recieved.getParent();
            EditText editText4 = this.et_textbooks_recieved;
            parent6.requestChildFocus(editText4, editText4);
            return;
        }
        this.et_textbooks_requested.setError("Please fill the field.");
        this.et_textbooks_requested.setFocusableInTouchMode(true);
        this.et_textbooks_requested.setFocusable(true);
        ViewParent parent7 = this.et_textbooks_requested.getParent();
        EditText editText5 = this.et_textbooks_requested;
        parent7.requestChildFocus(editText5, editText5);
        DialogCustom.showError(getActivityContext(), "Please fill the all fields.");
    }

    private void checkData() {
        if (CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            this.fm.popBackStack();
            return;
        }
        if (this.et_textbooks_requested.getText().toString().length() > 0 || this.et_textbooks_recieved.getText().toString().length() > 0 || this.et_textbooks_distributed.getText().toString().length() > 0 || this.et_textbooks_students.getText().toString().length() > 0 || this.et_textbooks_surplus.getText().toString().length() > 0 || this.et_textbooks_shortfalls.getText().toString().length() > 0 || this.editText_comments.getText().toString().length() > 0 || this.isTimelyDistributed > -1) {
            DialogCustom.showCancelWarning(getActivityContext(), this.fm);
        } else {
            this.fm.popBackStack();
        }
    }

    private void skipForm() {
        this.tbRequested = Constant.ECE_Katchi;
        this.tbReceived = Constant.ECE_Katchi;
        this.tbDistributed = Constant.ECE_Katchi;
        this.tbWithStudents = Constant.ECE_Katchi;
        this.tbSurplus = "";
        this.tbShortfalls = "";
        this.isTimelyDistributed = 1;
        this.comments = "";
        saveFieldsData();
    }

    private void saveFieldsData() {
        new Thread(new Runnable() { // from class: com.micromerger.ssms.startmonitoring.fragments.TextbooksDetail.4
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry<Integer, KRAData> entry : TextbooksDetail.this.classroom.entrySet()) {
                    Integer key = entry.getKey();
                    KRAData value = entry.getValue();
                    if (value.getKRAName().equals(Constant.TextbooksRequested_ + TextbooksDetail.this.title)) {
                        value.setDataValue(TextbooksDetail.this.tbRequested);
                    } else {
                        if (value.getKRAName().equals(Constant.TextbooksReceived_ + TextbooksDetail.this.title)) {
                            value.setDataValue(TextbooksDetail.this.tbReceived);
                        } else {
                            if (value.getKRAName().equals(Constant.TextbooksDistributed_ + TextbooksDetail.this.title)) {
                                value.setDataValue(TextbooksDetail.this.tbDistributed);
                            } else {
                                if (value.getKRAName().equals(Constant.StudentsWithTextbooks_ + TextbooksDetail.this.title)) {
                                    value.setDataValue(TextbooksDetail.this.tbWithStudents);
                                } else {
                                    if (value.getKRAName().equals(Constant.SurplusTextbooks_ + TextbooksDetail.this.title)) {
                                        value.setDataValue(TextbooksDetail.this.tbSurplus);
                                    } else {
                                        if (value.getKRAName().equals(Constant.ShortfallTextbooks_ + TextbooksDetail.this.title)) {
                                            value.setDataValue(TextbooksDetail.this.tbShortfalls);
                                        } else {
                                            if (!value.getKRAName().equals(Constant.TimelyDistributed_ + TextbooksDetail.this.title)) {
                                                if (value.getKRAName().equals(Constant.Comments_ + TextbooksDetail.this.title)) {
                                                    value.setDataValue(TextbooksDetail.this.comments);
                                                } else {
                                                    if (value.getKRAName().equals(Constant.Is_Completed_ + TextbooksDetail.this.title)) {
                                                        value.setDataValue(true);
                                                    }
                                                }
                                            } else if (TextbooksDetail.this.isTimelyDistributed == -1) {
                                                value.setDataValue(null);
                                            } else {
                                                value.setDataValue(String.valueOf(TextbooksDetail.this.isTimelyDistributed));
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
                        CommonActions.getDbHandler(TextbooksDetail.this.getActivityContext()).updateSchoolData(CommonObjects.employeeData.get(i).getDbId().intValue(), CommonObjects.monitoring);
                        return;
                    }
                }
            }
        }).start();
        this.fm.popBackStack();
    }
}
