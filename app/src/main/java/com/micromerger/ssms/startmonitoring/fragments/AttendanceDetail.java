package com.micromerger.ssms.startmonitoring.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.startmonitoring.SimpleMatchActivity;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.beans.employeeData.Employee;
import com.micromerger.ssms.user.beans.employeeData.KRAData;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.Constant;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.map.HashedMap;

/* loaded from: classes2.dex */
public class AttendanceDetail extends BaseFragment implements BaseFragment.CameraActivityCallBack, RadioGroup.OnCheckedChangeListener {
    List<ReferenceDataResponse.ReferenceData> REASON_LIST;
    List<ReferenceDataResponse.ReferenceData> REASON_LIST_TEMP;
    List<ReferenceDataResponse.ReferenceData> VERIFY_ABSCONDER_LIST;
    boolean alreadyVaccinated;
    HashedMap<Integer, KRAData> attendanceDetail;
    Button btn_mark_absent;
    Calendar calendar;
    int dutyTypeId;
    List<ReferenceDataResponse.ReferenceData> dutyTypeList;
    String eId;
    EditText editText_comments;
    String empRollInORG;
    Employee employee;
    EditText et_transferred_semis;
    RelativeLayout finger;
    TextView fromDate;
    String imageBase64;
    boolean isCapturing;
    boolean isPresent;
    boolean isReasonSelect;
    int leaveApprovalTypeId;
    List<ReferenceDataResponse.ReferenceData> leaveApprovalTypeList;
    int leaveTypeId;
    List<ReferenceDataResponse.ReferenceData> leaveTypeList;
    LinearLayout ll_duty_type;
    LinearLayout ll_from_to_date;
    LinearLayout ll_leave_approval_type;
    LinearLayout ll_leave_type;
    View mView;
    ImageView previewImage;
    private int reasonId;
    RadioGroup rg_msg_ilmi;
    RadioGroup rg_verify_absconder;
    int rollID;
    Spinner sp_duty_type;
    Spinner sp_leave_approval_type;
    Spinner sp_leave_type;
    Spinner sp_reasons;
    boolean thumbNotMatch;
    TextView toDate;
    RelativeLayout transferred_semis_layout;
    ImageView uploadImage;
    int verifyAbsconderId;
    LinearLayout verify_absconder_layout;

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    public AttendanceDetail() {
        this.REASON_LIST = new ArrayList();
        this.REASON_LIST_TEMP = new ArrayList();
        this.leaveTypeList = new ArrayList();
        this.leaveApprovalTypeList = new ArrayList();
        this.dutyTypeList = new ArrayList();
        this.reasonId = 0;
        this.verifyAbsconderId = 1;
        this.leaveTypeId = 0;
        this.leaveApprovalTypeId = 0;
        this.dutyTypeId = 0;
        this.imageBase64 = "";
        this.thumbNotMatch = false;
        this.alreadyVaccinated = false;
        this.attendanceDetail = new HashedMap<>();
        this.isCapturing = false;
        this.isReasonSelect = false;
        this.empRollInORG = "";
    }

    public AttendanceDetail(String s, String empRoll) {
        this.REASON_LIST = new ArrayList();
        this.REASON_LIST_TEMP = new ArrayList();
        this.leaveTypeList = new ArrayList();
        this.leaveApprovalTypeList = new ArrayList();
        this.dutyTypeList = new ArrayList();
        this.reasonId = 0;
        this.verifyAbsconderId = 1;
        this.leaveTypeId = 0;
        this.leaveApprovalTypeId = 0;
        this.dutyTypeId = 0;
        this.imageBase64 = "";
        this.thumbNotMatch = false;
        this.alreadyVaccinated = false;
        this.attendanceDetail = new HashedMap<>();
        this.isCapturing = false;
        this.isReasonSelect = false;
        this.empRollInORG = "";
        this.eId = s;
        this.empRollInORG = empRoll;
        for (Map.Entry<Integer, KRAData> entry : CommonObjects.attendance_fields.entrySet()) {
            if (entry.getValue().getKRAName().contains(this.eId)) {
                Log.e("attendance_fields", entry.getValue().getKRAName() + " " + entry.getValue().getDataValue());
                this.attendanceDetail.put(entry.getKey(), entry.getValue());
            }
        }
        String str = this.empRollInORG;
        if (str == null || str.equals(Constant.CHOWKIDAR_VALUE)) {
            return;
        }
        this.REASON_LIST.remove("Night Duty");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_attendancedetail, (ViewGroup) null);
        this.mView = viewInflate;
        this.parentView = viewInflate.findViewById(R.id.parentView);
        setCameraActivityCallBack(this);
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.1
            @Override // org.apache.commons.collections4.Predicate
            public boolean evaluate(Object o) {
                return ((ReferenceDataResponse.ReferenceData) o).getReferencecode().equals(Constant.ABSENT_REASON);
            }
        });
        if (collectionSelect instanceof List) {
            this.REASON_LIST = (List) collectionSelect;
        } else {
            this.REASON_LIST = new ArrayList(collectionSelect);
        }
        Collection collectionSelect2 = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.2
            @Override // org.apache.commons.collections4.Predicate
            public boolean evaluate(Object o) {
                return ((ReferenceDataResponse.ReferenceData) o).getReferencecode().equals(Constant.LEAVE_TYPES);
            }
        });
        if (collectionSelect2 instanceof List) {
            this.leaveTypeList = (List) collectionSelect2;
        } else {
            this.leaveTypeList = new ArrayList(collectionSelect2);
        }
        Collection collectionSelect3 = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.3
            @Override // org.apache.commons.collections4.Predicate
            public boolean evaluate(Object o) {
                return ((ReferenceDataResponse.ReferenceData) o).getReferencecode().equals("LEAVE_APPROVAL_TYPE");
            }
        });
        if (!collectionSelect3.isEmpty()) {
            if (collectionSelect3 instanceof List) {
                this.leaveApprovalTypeList = (List) collectionSelect3;
            } else {
                this.leaveApprovalTypeList = new ArrayList(collectionSelect3);
            }
        }
        Collection collectionSelect4 = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.4
            @Override // org.apache.commons.collections4.Predicate
            public boolean evaluate(Object o) {
                return ((ReferenceDataResponse.ReferenceData) o).getReferencecode().equals(Constant.OFFICIAL_DUTY_TYPES);
            }
        });
        if (collectionSelect4 instanceof List) {
            this.dutyTypeList = (List) collectionSelect4;
        } else {
            this.dutyTypeList = new ArrayList(collectionSelect4);
        }
        RelativeLayout relativeLayout = (RelativeLayout) this.mView.findViewById(R.id.finger);
        this.finger = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AttendanceDetail.this.startActivity(new Intent(AttendanceDetail.this.getActivity(), (Class<?>) SimpleMatchActivity.class).putExtra(Constant.Take_Attendance, AttendanceDetail.this.eId));
            }
        });
        this.transferred_semis_layout = (RelativeLayout) this.mView.findViewById(R.id.transferred_semis_layout);
        this.calendar = Calendar.getInstance();
        this.fromDate = (TextView) this.mView.findViewById(R.id.from_date);
        this.toDate = (TextView) this.mView.findViewById(R.id.to_date);
        this.fromDate.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$AttendanceDetail$fEFf5pJeGTiejpJeW-BeG4PYvXk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$0$AttendanceDetail(view);
            }
        });
        this.toDate.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$AttendanceDetail$BntYRU5mw4NyBaD1EQZK99cA4hU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$1$AttendanceDetail(view);
            }
        });
        return this.mView;
    }

    public /* synthetic */ void lambda$onCreateView$0$AttendanceDetail(View view) {
        new DatePickerDialog(getContext(), dateSetListener(this.fromDate), this.calendar.get(1), this.calendar.get(2), this.calendar.get(5)).show();
    }

    public /* synthetic */ void lambda$onCreateView$1$AttendanceDetail(View view) {
        new DatePickerDialog(getContext(), dateSetListener(this.toDate), this.calendar.get(1), this.calendar.get(2), this.calendar.get(5)).show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener(final TextView textView) {
        return new DatePickerDialog.OnDateSetListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$AttendanceDetail$Lw6aL-UmukzReq-L-RxCYYl7CBk
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                this.f$0.lambda$dateSetListener$2$AttendanceDetail(textView, datePicker, i, i2, i3);
            }
        };
    }

    public /* synthetic */ void lambda$dateSetListener$2$AttendanceDetail(TextView textView, DatePicker datePicker, int i, int i2, int i3) {
        this.calendar.set(1, i);
        this.calendar.set(2, i2);
        this.calendar.set(5, i3);
        setDate(textView);
    }

    private String getCurrentDate(Calendar calendar) {
        String strValueOf;
        String strValueOf2;
        int i = calendar.get(5);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(1);
        if (String.valueOf(i).length() > 1) {
            strValueOf = String.valueOf(i);
        } else {
            strValueOf = Constant.ECE_Katchi + i;
        }
        if (String.valueOf(i2).length() > 1) {
            strValueOf2 = String.valueOf(i2);
        } else {
            strValueOf2 = Constant.ECE_Katchi + i2;
        }
        return i3 + "-" + strValueOf2 + "-" + strValueOf;
    }

    private void setDate(TextView textView) {
        textView.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(this.calendar.getTime()));
    }

    private List<ReferenceDataResponse.ReferenceData> removeRollID() {
        if (this.REASON_LIST != null) {
            for (int i = 0; i < this.REASON_LIST.size(); i++) {
                if (!this.REASON_LIST.get(i).getReferencekey().equals(11)) {
                    this.REASON_LIST_TEMP.add(this.REASON_LIST.get(i));
                    this.rollID = i + 1;
                }
            }
        }
        return this.REASON_LIST_TEMP;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.ll_leave_type = (LinearLayout) view.findViewById(R.id.ll_leave_type);
        this.ll_leave_approval_type = (LinearLayout) view.findViewById(R.id.ll_leave_approval_type);
        this.ll_from_to_date = (LinearLayout) view.findViewById(R.id.ll_from_to_date);
        this.verify_absconder_layout = (LinearLayout) view.findViewById(R.id.verify_absconder_layout);
        this.ll_duty_type = (LinearLayout) view.findViewById(R.id.ll_duty_type);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_msg_ilmi);
        this.rg_msg_ilmi = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        RadioGroup radioGroup2 = (RadioGroup) view.findViewById(R.id.rg_verify_absconder);
        this.rg_verify_absconder = radioGroup2;
        radioGroup2.setOnCheckedChangeListener(this);
        this.fromDate.setText(getCurrentDate(this.calendar));
        this.toDate.setText(getCurrentDate(this.calendar));
        Spinner spinner = (Spinner) view.findViewById(R.id.sp_reasons);
        this.sp_reasons = spinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.6
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view2, int position, long l) {
                AttendanceDetail attendanceDetail = AttendanceDetail.this;
                attendanceDetail.reasonId = attendanceDetail.REASON_LIST.get(position).getReferencekey().intValue();
                if (AttendanceDetail.this.REASON_LIST.get(position).getReferencevalue().equals(Constant.ON_LEAVE)) {
                    AttendanceDetail.this.ll_leave_type.setVisibility(0);
                    AttendanceDetail.this.ll_from_to_date.setVisibility(0);
                    AttendanceDetail.this.verify_absconder_layout.setVisibility(8);
                    AttendanceDetail.this.verifyAbsconderId = 1;
                    AttendanceDetail.this.transferred_semis_layout.setVisibility(8);
                    AttendanceDetail.this.ll_duty_type.setVisibility(8);
                    AttendanceDetail.this.dutyTypeId = 0;
                    return;
                }
                if (AttendanceDetail.this.REASON_LIST.get(position).getReferencevalue().equals(Constant.ON_OFFICIAL_DUTY)) {
                    AttendanceDetail.this.rg_msg_ilmi.clearCheck();
                    AttendanceDetail.this.ll_leave_type.setVisibility(8);
                    AttendanceDetail.this.ll_leave_approval_type.setVisibility(8);
                    AttendanceDetail.this.verify_absconder_layout.setVisibility(8);
                    AttendanceDetail.this.verifyAbsconderId = 1;
                    AttendanceDetail.this.transferred_semis_layout.setVisibility(8);
                    AttendanceDetail.this.ll_from_to_date.setVisibility(8);
                    AttendanceDetail.this.leaveTypeId = 0;
                    AttendanceDetail.this.ll_duty_type.setVisibility(0);
                    return;
                }
                if (AttendanceDetail.this.REASON_LIST.get(position).getReferencevalue().equals(Constant.OFFICIAL_WORK) || AttendanceDetail.this.REASON_LIST.get(position).getReferencevalue().equals(Constant.ABSCONDER) || AttendanceDetail.this.REASON_LIST.get(position).getReferencevalue().equals(Constant.ABSENT) || AttendanceDetail.this.REASON_LIST.get(position).getReferencevalue().equals(Constant.HABITUAL_ABSENTEE)) {
                    if (AttendanceDetail.this.REASON_LIST.get(position).getReferencevalue().equals(Constant.ABSCONDER) && CommonObjects.userObj.role.name().equals(CommonObjects.Roles.CMO.name())) {
                        AttendanceDetail.this.verify_absconder_layout.setVisibility(0);
                    } else {
                        AttendanceDetail.this.verify_absconder_layout.setVisibility(8);
                        AttendanceDetail.this.verifyAbsconderId = 1;
                    }
                    AttendanceDetail.this.rg_msg_ilmi.clearCheck();
                    AttendanceDetail.this.ll_leave_type.setVisibility(8);
                    AttendanceDetail.this.ll_leave_approval_type.setVisibility(8);
                    AttendanceDetail.this.transferred_semis_layout.setVisibility(8);
                    AttendanceDetail.this.ll_from_to_date.setVisibility(0);
                    AttendanceDetail.this.ll_duty_type.setVisibility(8);
                    AttendanceDetail.this.leaveTypeId = 0;
                    return;
                }
                if (AttendanceDetail.this.REASON_LIST.get(position).getReferencevalue().equals("Transferred")) {
                    AttendanceDetail.this.rg_msg_ilmi.clearCheck();
                    AttendanceDetail.this.ll_leave_type.setVisibility(8);
                    AttendanceDetail.this.ll_leave_approval_type.setVisibility(8);
                    AttendanceDetail.this.verify_absconder_layout.setVisibility(8);
                    AttendanceDetail.this.verifyAbsconderId = 1;
                    AttendanceDetail.this.ll_from_to_date.setVisibility(8);
                    AttendanceDetail.this.leaveTypeId = 0;
                    AttendanceDetail.this.ll_duty_type.setVisibility(8);
                    AttendanceDetail.this.dutyTypeId = 0;
                    AttendanceDetail.this.transferred_semis_layout.setVisibility(0);
                    return;
                }
                AttendanceDetail.this.rg_msg_ilmi.clearCheck();
                AttendanceDetail.this.ll_leave_type.setVisibility(8);
                AttendanceDetail.this.ll_leave_approval_type.setVisibility(8);
                AttendanceDetail.this.verify_absconder_layout.setVisibility(8);
                AttendanceDetail.this.verifyAbsconderId = 1;
                AttendanceDetail.this.transferred_semis_layout.setVisibility(8);
                AttendanceDetail.this.ll_from_to_date.setVisibility(8);
                AttendanceDetail.this.leaveTypeId = 0;
                AttendanceDetail.this.ll_duty_type.setVisibility(8);
                AttendanceDetail.this.dutyTypeId = 0;
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                CommonActions.hideSoftKeyboard(AttendanceDetail.this.getActivityContext(), AttendanceDetail.this.getActivity().getCurrentFocus());
            }
        });
        if (this.empRollInORG.equals(Constant.CHOWKIDAR_VALUE)) {
            this.sp_reasons.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.REASON_LIST));
        } else if (this.REASON_LIST != null) {
            List<ReferenceDataResponse.ReferenceData> listRemoveRollID = removeRollID();
            this.REASON_LIST = listRemoveRollID;
            if (listRemoveRollID != null) {
                this.sp_reasons.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.REASON_LIST));
            }
        }
        Spinner spinner2 = (Spinner) view.findViewById(R.id.sp_leave_type);
        this.sp_leave_type = spinner2;
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.7
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view2, int position, long l) {
                AttendanceDetail attendanceDetail = AttendanceDetail.this;
                attendanceDetail.leaveTypeId = attendanceDetail.leaveTypeList.get(position).getReferencekey().intValue();
                if (AttendanceDetail.this.leaveTypeId > 0) {
                    AttendanceDetail.this.ll_leave_approval_type.setVisibility(0);
                } else {
                    AttendanceDetail.this.ll_leave_approval_type.setVisibility(8);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                CommonActions.hideSoftKeyboard(AttendanceDetail.this.getActivityContext(), AttendanceDetail.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_leave_type.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.leaveTypeList));
        Spinner spinner3 = (Spinner) view.findViewById(R.id.sp_leave_approval_type);
        this.sp_leave_approval_type = spinner3;
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.8
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view2, int position, long id2) {
                AttendanceDetail attendanceDetail = AttendanceDetail.this;
                attendanceDetail.leaveApprovalTypeId = attendanceDetail.leaveApprovalTypeList.get(position).getReferencekey().intValue();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
                CommonActions.hideSoftKeyboard(AttendanceDetail.this.getActivityContext(), AttendanceDetail.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_leave_approval_type.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.leaveApprovalTypeList));
        Spinner spinner4 = (Spinner) view.findViewById(R.id.sp_duty_type);
        this.sp_duty_type = spinner4;
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.9
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view2, int position, long l) {
                AttendanceDetail.this.dutyTypeId = position;
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                CommonActions.hideSoftKeyboard(AttendanceDetail.this.getActivityContext(), AttendanceDetail.this.getActivity().getCurrentFocus());
            }
        });
        this.sp_duty_type.setAdapter((SpinnerAdapter) new com.micromerger.ssms.utils.widgets.SpinnerAdapter(getActivityContext(), this.dutyTypeList));
        EditText editText = (EditText) view.findViewById(R.id.editText_comments);
        this.editText_comments = editText;
        editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.10
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
        this.et_transferred_semis = (EditText) view.findViewById(R.id.et_transferred_semis);
        ImageView imageView = (ImageView) view.findViewById(R.id.previewImage);
        this.previewImage = imageView;
        imageView.setVisibility(8);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.uploadImage);
        this.uploadImage = imageView2;
        imageView2.setVisibility(0);
        this.uploadImage.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AttendanceDetail.this.isCapturing = true;
                try {
                    int iIntValue = CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID().intValue();
                    String str = Constant.Monitoring_Image_ + AttendanceDetail.this.eId;
                    AttendanceDetail.this.getPhotoFromCamera(iIntValue + "_41_" + str);
                } catch (Exception unused) {
                    AttendanceDetail.this.isCapturing = false;
                    Toast.makeText(AttendanceDetail.this.getContext(), "Error getting Photo From Camera", 0).show();
                }
            }
        });
        Button button = (Button) view.findViewById(R.id.btn_mark_absent);
        this.btn_mark_absent = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AttendanceDetail.this.checkValidation();
            }
        });
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$AttendanceDetail$POFCjXp0ZzoN9XHMveNRGv8TggY
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("INDICATOR");
            }
        });
        if (collectionSelect instanceof List) {
            this.VERIFY_ABSCONDER_LIST = (List) collectionSelect;
        } else {
            this.VERIFY_ABSCONDER_LIST = new ArrayList(collectionSelect);
        }
        if (this.VERIFY_ABSCONDER_LIST.isEmpty()) {
            return;
        }
        this.rg_verify_absconder.setOrientation(1);
        for (int i = 0; i < this.VERIFY_ABSCONDER_LIST.size(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(this.VERIFY_ABSCONDER_LIST.get(i).getReferencedataid().intValue());
            radioButton.setText(this.VERIFY_ABSCONDER_LIST.get(i).getReferencevalue());
            radioButton.setTextColor(getResources().getColor(R.color.boundary_wall_text_bg_color));
            radioButton.setTextSize(16.0f);
            radioButton.setButtonDrawable(R.drawable.radiobuttonstate);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.micromerger.ssms.startmonitoring.fragments.-$$Lambda$AttendanceDetail$LU6K47rfV48vsAHivzMmAXvtnu4
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    this.f$0.lambda$onViewCreated$4$AttendanceDetail(compoundButton, z);
                }
            });
            this.rg_verify_absconder.addView(radioButton);
        }
        ((RadioButton) this.rg_verify_absconder.getChildAt(1)).setChecked(true);
        this.verifyAbsconderId = 1;
    }

    public /* synthetic */ void lambda$onViewCreated$4$AttendanceDetail(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isChecked()) {
            for (ReferenceDataResponse.ReferenceData referenceData : this.VERIFY_ABSCONDER_LIST) {
                if (referenceData.getReferencedataid().intValue() == compoundButton.getId()) {
                    this.verifyAbsconderId = referenceData.getReferencekey().intValue();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkValidation() {
        Iterator<Employee> it = CommonObjects.monitoring.getEmployees().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Employee next = it.next();
            if (String.valueOf(next.getEmployeeId()).equals(this.eId)) {
                this.employee = next;
                break;
            }
        }
        Employee employee = this.employee;
        if (employee != null && employee.getDonotAskForVaccination() != null && this.employee.getDonotAskForVaccination().booleanValue()) {
            this.alreadyVaccinated = true;
        }
        Iterator<ReferenceDataResponse.ReferenceData> it2 = this.REASON_LIST.iterator();
        int i = 0;
        while (it2.hasNext()) {
            if (it2.next().getReferencekey().intValue() == this.reasonId) {
                if (!this.REASON_LIST.get(i).getReferencevalue().equals(Constant.Select)) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    Date date2 = new Date();
                    try {
                        date = simpleDateFormat.parse(this.fromDate.getText().toString());
                        date2 = simpleDateFormat.parse(this.toDate.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if ((this.REASON_LIST.get(i).getReferencevalue().equals(Constant.ON_LEAVE) || this.REASON_LIST.get(i).getReferencevalue().equals(Constant.OFFICIAL_WORK) || this.REASON_LIST.get(i).getReferencevalue().equals(Constant.ABSCONDER) || this.REASON_LIST.get(i).getReferencevalue().equals(Constant.HABITUAL_ABSENTEE) || this.REASON_LIST.get(i).getReferencevalue().equals(Constant.ABSENT)) && date.after(date2)) {
                        DialogCustom.showError(getActivityContext(), "From Date can not be greater than To Date.");
                        return;
                    }
                    if (this.REASON_LIST.get(i).getReferencevalue().equals(Constant.ON_LEAVE)) {
                        Iterator<ReferenceDataResponse.ReferenceData> it3 = this.leaveTypeList.iterator();
                        int i2 = 0;
                        while (it3.hasNext()) {
                            if (it3.next().getReferencekey().intValue() == this.leaveTypeId) {
                                if (!this.leaveTypeList.get(i2).getReferencevalue().equals(Constant.Select)) {
                                    Iterator<ReferenceDataResponse.ReferenceData> it4 = this.leaveApprovalTypeList.iterator();
                                    int i3 = 0;
                                    while (it4.hasNext()) {
                                        if (it4.next().getReferencekey().intValue() == this.leaveApprovalTypeId) {
                                            if (!this.leaveApprovalTypeList.get(i3).getReferencevalue().equals(Constant.Select)) {
                                                if (!this.imageBase64.equals("")) {
                                                    this.isPresent = false;
                                                    saveFieldsData();
                                                } else {
                                                    DialogCustom.showError(getActivityContext(), "Please upload image.");
                                                }
                                            } else {
                                                DialogCustom.showError(getActivityContext(), "Please select leave approval type.");
                                            }
                                        }
                                        i3++;
                                    }
                                    return;
                                }
                                DialogCustom.showError(getActivityContext(), "Please select leave type.");
                                return;
                            }
                            i2++;
                        }
                        return;
                    }
                    if (this.REASON_LIST.get(i).getReferencevalue().equals(Constant.ON_OFFICIAL_DUTY)) {
                        if (!this.dutyTypeList.get(this.dutyTypeId).getReferencevalue().equals(Constant.Select)) {
                            if (!this.imageBase64.equals("")) {
                                this.isPresent = false;
                                saveFieldsData();
                                return;
                            } else {
                                DialogCustom.showError(getActivityContext(), "Please upload image.");
                                return;
                            }
                        }
                        DialogCustom.showError(getActivityContext(), "Please select official duty type.");
                        return;
                    }
                    if (this.REASON_LIST.get(i).getReferencevalue().equals(Constant.THUMB_NOT_MATCH)) {
                        if (!this.imageBase64.equals("")) {
                            this.isPresent = false;
                            this.thumbNotMatch = true;
                            if (!this.alreadyVaccinated) {
                                this.fm.beginTransaction().add(R.id.container, new SuccessAttendanceFragment(this.eId, "Reason marked successfully", Boolean.valueOf(this.thumbNotMatch))).addToBackStack(getTag()).commit();
                            }
                            saveFieldsData();
                            return;
                        }
                        DialogCustom.showError(getActivityContext(), "Please Take image.");
                        return;
                    }
                    if (!this.imageBase64.equals("")) {
                        this.isPresent = false;
                        saveFieldsData();
                        return;
                    } else {
                        DialogCustom.showError(getActivityContext(), "Please upload image.");
                        return;
                    }
                }
                DialogCustom.showError(getActivityContext(), "Please select absent reason.");
                return;
            }
            i++;
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() throws NumberFormatException {
        super.onResume();
        SSMS.setCurrentFragment(this);
        if (CommonObjects.schoolThumbCheck) {
            CommonObjects.schoolThumbCheck = false;
            if (CommonObjects.searchedEmployeeId.equals("")) {
                DialogCustom.showError(getActivity(), "No employee exist.");
                return;
            } else {
                this.isPresent = true;
                saveFieldsData();
                return;
            }
        }
        if (this.isCapturing) {
            this.isCapturing = false;
            return;
        }
        for (Map.Entry<Integer, KRAData> entry : this.attendanceDetail.entrySet()) {
            entry.getKey();
            KRAData value = entry.getValue();
            Log.e("kraData : ", value.getKRAName());
            if (value.getDataValue() != null && !value.getDataValue().equals("")) {
                if (value.getKRAName().equals(Constant.Absent_Reason_id_ + this.eId)) {
                    this.isReasonSelect = true;
                    Iterator<ReferenceDataResponse.ReferenceData> it = this.REASON_LIST.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        if (it.next().getReferencekey().toString().equals((String) value.getDataValue())) {
                            this.reasonId = this.REASON_LIST.get(i).getReferencekey().intValue();
                            this.sp_reasons.setSelection(i);
                        }
                        i++;
                    }
                } else {
                    if (!value.getKRAName().equals(Constant.Is_MsgSent_ILMI_ + this.eId)) {
                        if (value.getKRAName().equals(Constant.Leave_Type_ + this.eId)) {
                            Iterator<ReferenceDataResponse.ReferenceData> it2 = this.leaveTypeList.iterator();
                            int i2 = 0;
                            while (it2.hasNext()) {
                                if (it2.next().getReferencekey().toString().equals((String) value.getDataValue())) {
                                    this.leaveTypeId = this.leaveTypeList.get(i2).getReferencekey().intValue();
                                    this.sp_leave_type.setSelection(i2);
                                }
                                i2++;
                            }
                        } else {
                            if (value.getKRAName().equals(Constant.Leave_Approval_Type_ + this.eId)) {
                                Iterator<ReferenceDataResponse.ReferenceData> it3 = this.leaveApprovalTypeList.iterator();
                                int i3 = 0;
                                while (it3.hasNext()) {
                                    if (it3.next().getReferencekey().toString().equals((String) value.getDataValue())) {
                                        this.leaveApprovalTypeId = this.leaveApprovalTypeList.get(i3).getReferencekey().intValue();
                                        this.sp_leave_approval_type.setSelection(i3);
                                    }
                                    i3++;
                                }
                            } else {
                                if (value.getKRAName().equals(Constant.Office_Duty_Type_ + this.eId)) {
                                    int i4 = Integer.parseInt((String) value.getDataValue());
                                    this.dutyTypeId = i4;
                                    this.sp_duty_type.setSelection(i4);
                                } else {
                                    if (value.getKRAName().equals(Constant.Comments_ + this.eId)) {
                                        this.editText_comments.setText((String) value.getDataValue());
                                    } else {
                                        if (value.getKRAName().equals(Constant.Monitoring_Image_ + this.eId)) {
                                            this.imageBase64 = (String) value.getDataValue();
                                            Bitmap bitmapDecodeFile = CommonActions.decodeFile(new File(this.imageBase64));
                                            if (bitmapDecodeFile != null) {
                                                this.previewImage.setImageBitmap(bitmapDecodeFile);
                                            } else {
                                                String str = this.imageBase64;
                                                if (str != null) {
                                                    byte[] bArrDecode = Base64.decode(str, 0);
                                                    try {
                                                        this.previewImage.setImageBitmap(BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length));
                                                        this.previewImage.setVisibility(0);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                            if (this.imageBase64.length() > 0) {
                                                this.previewImage.setVisibility(0);
                                            }
                                        } else {
                                            if (value.getKRAName().equals(Constant.Attendance_Date_ + this.eId)) {
                                                Log.e(Constant.Attendance_Date_, String.valueOf(value.getDataValue()));
                                            } else {
                                                if (value.getKRAName().equals(Constant.Is_Completed_ + this.eId)) {
                                                    Log.e(Constant.Is_Completed, String.valueOf(value.getDataValue()));
                                                } else {
                                                    if (value.getKRAName().equals("Image_Date_" + this.eId)) {
                                                        Log.e(Constant.Image_Date, String.valueOf(value.getDataValue()));
                                                    } else {
                                                        if (!value.getKRAName().equals(Constant.Absent_Leave_From + this.eId)) {
                                                            if (!value.getKRAName().equals(Constant.Absent_Leave_To + this.eId)) {
                                                                if (value.getKRAName().equals(Constant.Transferred_To_School_SEMIS_ + this.eId)) {
                                                                    if (value.getDataValue() != null && !value.getDataValue().toString().isEmpty()) {
                                                                        this.et_transferred_semis.setText(String.valueOf(value.getDataValue()));
                                                                    }
                                                                } else {
                                                                    if (value.getKRAName().equals(Constant.Verified_Absconder_ + this.eId) && value.getDataValue() != null && !value.getDataValue().toString().isEmpty() && CommonObjects.userObj.role.name().equals(CommonObjects.Roles.CMO.name())) {
                                                                        int i5 = Integer.parseInt((String) value.getDataValue());
                                                                        this.verifyAbsconderId = i5;
                                                                        ((RadioButton) this.rg_verify_absconder.getChildAt(i5)).setChecked(true);
                                                                    }
                                                                }
                                                            } else if (value.getDataValue() != null && !value.getDataValue().toString().isEmpty()) {
                                                                String strValueOf = String.valueOf(value.getDataValue());
                                                                if (strValueOf.contains("T00:00:00")) {
                                                                    this.toDate.setText(strValueOf.split(ExifInterface.GPS_DIRECTION_TRUE)[0]);
                                                                } else {
                                                                    this.toDate.setText(strValueOf);
                                                                }
                                                            }
                                                        } else if (value.getDataValue() != null && !value.getDataValue().toString().isEmpty()) {
                                                            String strValueOf2 = String.valueOf(value.getDataValue());
                                                            if (strValueOf2.contains("T00:00:00")) {
                                                                this.fromDate.setText(strValueOf2.split(ExifInterface.GPS_DIRECTION_TRUE)[0]);
                                                            } else {
                                                                this.fromDate.setText(strValueOf2);
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
        if (CommonObjects.monitoring == null || CommonObjects.monitoring.getMonitoring() == null || !CommonObjects.monitoring.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            return;
        }
        this.rg_msg_ilmi.setEnabled(false);
        this.rg_msg_ilmi.setFocusable(false);
        for (int i6 = 0; i6 < this.rg_msg_ilmi.getChildCount(); i6++) {
            ((RadioButton) this.rg_msg_ilmi.getChildAt(i6)).setFocusable(false);
            ((RadioButton) this.rg_msg_ilmi.getChildAt(i6)).setEnabled(false);
        }
        this.rg_verify_absconder.setEnabled(false);
        this.rg_verify_absconder.setFocusable(false);
        for (int i7 = 0; i7 < this.rg_verify_absconder.getChildCount(); i7++) {
            ((RadioButton) this.rg_verify_absconder.getChildAt(i7)).setFocusable(false);
            ((RadioButton) this.rg_verify_absconder.getChildAt(i7)).setEnabled(false);
        }
        this.finger.setEnabled(false);
        this.finger.setFocusable(false);
        this.sp_reasons.setEnabled(false);
        this.sp_reasons.setFocusable(false);
        this.sp_leave_type.setEnabled(false);
        this.sp_leave_type.setFocusable(false);
        this.sp_leave_approval_type.setEnabled(false);
        this.sp_leave_approval_type.setFocusable(false);
        this.sp_duty_type.setEnabled(false);
        this.sp_duty_type.setFocusable(false);
        this.btn_mark_absent.setEnabled(false);
        this.btn_mark_absent.setFocusable(false);
        this.btn_mark_absent.setAlpha(0.5f);
        this.uploadImage.setEnabled(false);
        this.uploadImage.setFocusable(false);
        this.uploadImage.setAlpha(0.5f);
        this.editText_comments.setEnabled(false);
        this.editText_comments.setFocusable(false);
        this.et_transferred_semis.setEnabled(false);
        this.et_transferred_semis.setFocusable(false);
        this.fromDate.setEnabled(false);
        this.fromDate.setFocusable(false);
        this.toDate.setEnabled(false);
        this.toDate.setFocusable(false);
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        SSMS.setCurrentFragment(null);
        super.onPause();
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment.CameraActivityCallBack
    public void onCameraActivityResult(Bitmap bitmap, String bitmapPath) {
        this.location = CommonObjects.tracker.getLocation(getActivityContext());
        if (CommonObjects.isMock) {
            return;
        }
        if (bitmap != null) {
            this.previewImage.setVisibility(0);
            this.previewImage.setImageBitmap(bitmap);
        }
        if (bitmapPath != null) {
            this.imageBase64 = bitmapPath;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x002b, code lost:
    
        com.micromerger.ssms.utils.util.logException(new java.lang.Exception("Absent Reason marked: Absent Type = " + r1.getReferencevalue() + " Monitoring Id = " + com.micromerger.ssms.utils.CommonObjects.monitoring.getMonitoring().get(0).getMonitoringID() + " School Id = " + com.micromerger.ssms.utils.CommonObjects.monitoring.getSchoolId() + " Employee Id = " + r4.eId));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void saveFieldsData() {
        /*
            r4 = this;
            java.lang.Thread r0 = new java.lang.Thread
            com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail$13 r1 = new com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail$13
            r1.<init>()
            r0.<init>(r1)
            r0.start()
            java.util.List<com.micromerger.ssms.main.beans.ReferenceDataResponse$ReferenceData> r0 = r4.REASON_LIST     // Catch: java.lang.Exception -> L7a
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> L7a
        L13:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Exception -> L7a
            if (r1 == 0) goto L94
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Exception -> L7a
            com.micromerger.ssms.main.beans.ReferenceDataResponse$ReferenceData r1 = (com.micromerger.ssms.main.beans.ReferenceDataResponse.ReferenceData) r1     // Catch: java.lang.Exception -> L7a
            java.lang.Integer r2 = r1.getReferencekey()     // Catch: java.lang.Exception -> L7a
            int r2 = r2.intValue()     // Catch: java.lang.Exception -> L7a
            int r3 = r4.reasonId     // Catch: java.lang.Exception -> L7a
            if (r2 != r3) goto L13
            java.lang.String r0 = r1.getReferencevalue()     // Catch: java.lang.Exception -> L7a
            java.lang.Exception r1 = new java.lang.Exception     // Catch: java.lang.Exception -> L7a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L7a
            r2.<init>()     // Catch: java.lang.Exception -> L7a
            java.lang.String r3 = "Absent Reason marked: Absent Type = "
            r2.append(r3)     // Catch: java.lang.Exception -> L7a
            r2.append(r0)     // Catch: java.lang.Exception -> L7a
            java.lang.String r0 = " Monitoring Id = "
            r2.append(r0)     // Catch: java.lang.Exception -> L7a
            com.micromerger.ssms.user.beans.employeeData.EmployeeData r0 = com.micromerger.ssms.utils.CommonObjects.monitoring     // Catch: java.lang.Exception -> L7a
            java.util.List r0 = r0.getMonitoring()     // Catch: java.lang.Exception -> L7a
            r3 = 0
            java.lang.Object r0 = r0.get(r3)     // Catch: java.lang.Exception -> L7a
            com.micromerger.ssms.user.beans.employeeData.Monitoring r0 = (com.micromerger.ssms.user.beans.employeeData.Monitoring) r0     // Catch: java.lang.Exception -> L7a
            java.lang.Integer r0 = r0.getMonitoringID()     // Catch: java.lang.Exception -> L7a
            r2.append(r0)     // Catch: java.lang.Exception -> L7a
            java.lang.String r0 = " School Id = "
            r2.append(r0)     // Catch: java.lang.Exception -> L7a
            com.micromerger.ssms.user.beans.employeeData.EmployeeData r0 = com.micromerger.ssms.utils.CommonObjects.monitoring     // Catch: java.lang.Exception -> L7a
            java.lang.Integer r0 = r0.getSchoolId()     // Catch: java.lang.Exception -> L7a
            r2.append(r0)     // Catch: java.lang.Exception -> L7a
            java.lang.String r0 = " Employee Id = "
            r2.append(r0)     // Catch: java.lang.Exception -> L7a
            java.lang.String r0 = r4.eId     // Catch: java.lang.Exception -> L7a
            r2.append(r0)     // Catch: java.lang.Exception -> L7a
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Exception -> L7a
            r1.<init>(r0)     // Catch: java.lang.Exception -> L7a
            com.micromerger.ssms.utils.util.logException(r1)     // Catch: java.lang.Exception -> L7a
            goto L94
        L7a:
            r0 = move-exception
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unable to get Absent Reason marked non-fetal "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            com.micromerger.ssms.utils.util.logException(r1)
        L94:
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            if (r0 == 0) goto La7
            boolean r0 = r4.thumbNotMatch
            if (r0 == 0) goto La2
            boolean r0 = r4.alreadyVaccinated
            if (r0 == 0) goto La7
        La2:
            androidx.fragment.app.FragmentManager r0 = r4.fm
            r0.popBackStack()
        La7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.micromerger.ssms.startmonitoring.fragments.AttendanceDetail.saveFieldsData():void");
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i != R.id.rb_no) {
            if (i != R.id.rb_yes) {
                return;
            }
            this.ll_leave_type.setVisibility(0);
        } else {
            this.leaveTypeId = 0;
            this.ll_leave_type.setVisibility(8);
            this.ll_leave_approval_type.setVisibility(8);
        }
    }
}
