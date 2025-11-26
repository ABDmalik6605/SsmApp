package com.micromerger.ssms.addemployee;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.micromerger.ssms.R;
import com.micromerger.ssms.addemployee.bean.AddStaffTicketing;
import com.micromerger.ssms.addemployee.bean.EmployeeRecordUpdateDatum;
import com.micromerger.ssms.addemployee.bean.EmployeeUpdateRecord;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import com.micromerger.ssms.staffpresence.StaffPresenceModel;
import com.micromerger.ssms.startmonitoring.bean.School;
import com.micromerger.ssms.startmonitoring.fragments.BaseFragment;
import com.micromerger.ssms.user.UserBean;
import com.micromerger.ssms.utils.BasePreferenceHelper;
import com.micromerger.ssms.utils.CommonActions;
import com.micromerger.ssms.utils.CommonObjects;
import com.micromerger.ssms.utils.FileUtil;
import com.micromerger.ssms.utils.MyFilesProvider;
import com.micromerger.ssms.utils.WebConstant;
import com.micromerger.ssms.utils.util;
import com.micromerger.ssms.utils.widgets.DialogCustom;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import javax.annotation.Nullable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.map.HashedMap;

/* loaded from: classes2.dex */
public class AddEmployeeFragment extends BaseFragment implements BaseFragment.CameraActivityCallBackMultiImages {
    public static final int APPOINTMENT_ORDER_FILE_REQ_CODE = 101;
    public static final int APPOINTMENT_ORDER_PICTURE_REQ_CODE = 15;
    public static final int ATTENDANCE_REG_PICTURE_REQ_CODE = 21;
    public static final int CNIC_PICTURE_REQ_CODE = 13;
    public static final int EMPLOYEE_PICTURE_REQ_CODE = 12;
    public static final int EMP_PAYSLIP_PICTURE_REQ_CODE = 18;
    public static final int GOVT_LETTER_PICTURE_REQ_CODE = 14;
    public static final int INFORMATION_LETTER_REQ_CODE = 22;
    public static final int JOINING_REPORT_PICTURE_REQ_CODE = 20;
    public static final int OFFER_ORDER_FILE_REQ_CODE = 102;
    public static final int OFFER_ORDER_PICTURE_REQ_CODE = 19;
    public static final int OTHER_PICTURE_REQ_CODE = 17;
    public static final int POSTING_ORDER_PICTURE_REQ_CODE = 16;
    public static final int allowToWork = 6;
    public static final int biometricMistake = 5;
    public static final int employeeProfilePicture = 9;
    public static final int expired = 7;
    public static final int newAppointee = 1;
    public static final int promoted = 4;
    public static final int retired = 8;
    public static final int transferIn = 2;
    List<ReferenceDataResponse.ReferenceData> ATTENDANCE_LIST;
    List<ReferenceDataResponse.ReferenceData> GENDER_LIST;
    List<ReferenceDataResponse.ReferenceData> REASONS_LIST;
    List<ReferenceDataResponse.ReferenceData> REOPEN_LIST;
    List<ReferenceDataResponse.ReferenceData> VERBAL_LIST;
    AddEmployeeOperations addEmployeeOperations;
    LinearLayout admin_response_layout;
    ImageView appointment_order_pdf;
    ImageView appointment_order_picture;
    ImageView appointment_order_picture_camera_icon;
    LinearLayout appointment_order_picture_layout;
    int attendanceId;
    LinearLayout attendance_layout;
    ImageView attendance_register_picture;
    File cnicPictureFile;
    LinearLayout cnic_layout;
    ImageView cnic_picture;
    ImageView cnic_picture_camera_icon;
    LinearLayout cnic_picture_layout;
    LinearLayout contact_no_layout;
    TextView date_of_birth;
    LinearLayout designation_layout;
    LinearLayout dob_layout;
    boolean editMode;
    File employeePayslipFile;
    File employeePictureFile;
    EmployeeRecordUpdateDatum employeeRecordUpdateDatum;
    EmployeeUpdateRecord employeeUpdateRecord;
    LinearLayout employee_id_layout;
    LinearLayout employee_payslip_layout;
    ImageView employee_payslip_layout_camera_icon;
    ImageView employee_payslip_picture;
    ImageView employee_picture;
    ImageView employee_picture_camera_icon;
    LinearLayout employee_picture_layout;
    EditText et_cnic;
    EditText et_comments;
    EditText et_contact_no;
    EditText et_designation;
    EditText et_name;
    EditText et_person_id;
    EditText et_referred_letter_no;
    int genderId;
    LinearLayout gender_layout;
    LinearLayout images_layout;
    LinearLayout images_linear_layout;
    File informationLetterFile;
    ImageView information_letter_camera_icon;
    LinearLayout information_letter_layout;
    ImageView information_letter_picture;
    File joiningReportFile;
    TextView joining_date;
    LinearLayout joining_date_layout;
    LinearLayout joining_report_layout;
    ImageView joining_report_layout_camera_icon;
    ImageView joining_report_picture;
    boolean letterFieldsRequired;
    LinearLayout letter_order_layout;
    boolean localSyncedRecordFound;
    LinearLayout name_layout;
    boolean newRecordFound;
    File offerOrderFile;
    LinearLayout offer_order_layout;
    ImageView offer_order_pdf;
    ImageView offer_order_picture;
    ImageView offer_order_picture_camera_icon;
    TextView order_effecting_date;
    File pictureAttendanceRegisterFile;
    LinearLayout picture_attendance_register_layout;
    ImageView picture_attendance_register_layout_camera_icon;
    String pleaseFillAllFields;
    ImageView posting_order_picture;
    ImageView posting_order_picture_camera_icon;
    BasePreferenceHelper preferenceHelper;
    int reOpenId;
    LinearLayout re_open_layout;
    int reasonId;
    File reasonOrderFile;
    LinearLayout reason_layout;
    HashedMap<Integer, String> reasonsMap;
    boolean recordFound;
    TextView referred_letter_date;
    RelativeLayout remarks_layout;
    LinearLayout response_picture_layout;
    RelativeLayout response_picture_tap;
    RadioGroup rg_attendance;
    RadioGroup rg_gender;
    RadioGroup rg_re_open;
    RadioGroup rg_reason;
    Button save_btn;
    LinearLayout school_layout;
    List<School> schools;
    SearchableSpinner schools_spinner;
    School selectedSchool;
    StaffPresenceModel staffPresenceModel;
    CardView status_card;
    String thisFieldIsRequired;
    TextView tv_admin_response;
    TextView tv_appointment_order;
    TextView tv_attendance;
    TextView tv_cnic_image;
    TextView tv_designation;
    TextView tv_employee_image;
    TextView tv_employee_payslip;
    TextView tv_information_letter;
    TextView tv_joining_report;
    TextView tv_offer_order;
    TextView tv_order_date;
    TextView tv_order_no;
    TextView tv_person_id;
    TextView tv_picture_attendance_register;
    TextView tv_retired_expired_date;
    TextView tv_tap_image_view;
    TextView tv_ticket_status;
    boolean typing;
    Integer updateRecordId;
    UserBean userBean;

    private String getValue(@Nullable String value) {
        return value != null ? value : "";
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment
    public boolean onBackPressed() {
        return false;
    }

    public AddEmployeeFragment() {
        this.reasonId = -1;
        this.genderId = -1;
        this.attendanceId = -1;
        this.reOpenId = -1;
        this.thisFieldIsRequired = "This field is required";
        this.pleaseFillAllFields = "Please fill all fields";
        this.typing = true;
        this.recordFound = false;
        this.newRecordFound = false;
        this.localSyncedRecordFound = false;
        this.reasonsMap = new HashedMap<>();
        this.updateRecordId = null;
        this.letterFieldsRequired = false;
    }

    public AddEmployeeFragment(StaffPresenceModel staffPresenceModel) {
        this.reasonId = -1;
        this.genderId = -1;
        this.attendanceId = -1;
        this.reOpenId = -1;
        this.thisFieldIsRequired = "This field is required";
        this.pleaseFillAllFields = "Please fill all fields";
        this.typing = true;
        this.recordFound = false;
        this.newRecordFound = false;
        this.localSyncedRecordFound = false;
        this.reasonsMap = new HashedMap<>();
        this.updateRecordId = null;
        this.letterFieldsRequired = false;
        this.staffPresenceModel = staffPresenceModel;
        this.editMode = true;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_employee, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCameraActivityCallBackMultiImages(this);
        this.preferenceHelper = new BasePreferenceHelper(view.getContext());
        this.addEmployeeOperations = new AddEmployeeOperations(view.getContext());
        this.userBean = this.preferenceHelper.getUser();
        initUIFields(view);
        renderUI(view);
        this.date_of_birth.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$Kqr5diRjo8UsNQnGbko7EzbxFvQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$0$AddEmployeeFragment(view2);
            }
        });
        this.joining_date.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$Xi7GMxtFzTSeMai24KC1uTcMvfo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$1$AddEmployeeFragment(view2);
            }
        });
        this.referred_letter_date.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$2UnlXPMWJpXo1EAzdwbdl9WZtZg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$2$AddEmployeeFragment(view2);
            }
        });
        this.order_effecting_date.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$WNt-d6rX-utDmSZicof40hRr67I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$3$AddEmployeeFragment(view2);
            }
        });
        this.employee_picture_camera_icon.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$PCrMvVbJmvMFtY6WV5g_KkuHAR4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$4$AddEmployeeFragment(view2);
            }
        });
        this.cnic_picture_camera_icon.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$ob3jVhILnDOF3M8F3ePY39dcyhU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$5$AddEmployeeFragment(view2);
            }
        });
        this.appointment_order_picture_camera_icon.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$T3Or9EbwkG4-y-R78a5rtY2rEvU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$9$AddEmployeeFragment(view2);
            }
        });
        this.posting_order_picture_camera_icon.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$lx-1AW78jw-Qkdw9VGBKtWTp3hM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$10$AddEmployeeFragment(view2);
            }
        });
        this.employee_payslip_layout_camera_icon.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$E9TqRw_aqbBg--56PsMeLFG3hBo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$11$AddEmployeeFragment(view2);
            }
        });
        this.joining_report_layout_camera_icon.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$x1eF8tnTESyvdZyN0T1Yiy6MUuU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$12$AddEmployeeFragment(view2);
            }
        });
        this.offer_order_picture_camera_icon.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$P8qGlhisl2trpeIGQe_qT18qnUs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$16$AddEmployeeFragment(view2);
            }
        });
        this.picture_attendance_register_layout_camera_icon.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$xd-kVU0pN7ZUmmuLRmXM902QeCg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$17$AddEmployeeFragment(view2);
            }
        });
        this.information_letter_camera_icon.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$Qs0k8sq6Ld6w6CsNdMBLDBQficE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$18$AddEmployeeFragment(view2);
            }
        });
        this.save_btn.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$GIU0B3RfwPtqw1DbOQ3YBxh5W8g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$19$AddEmployeeFragment(view2);
            }
        });
        if (getActivity() != null && this.editMode) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$vckCuFfL_PC2oT4HwRjgLmBSKOQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.loadingStarted();
                }
            });
        }
        new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$69M6x5rDadK8x3kBFW1dWCKtY08
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onViewCreated$21$AddEmployeeFragment();
            }
        }, 500L);
        this.appointment_order_pdf.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$rLHVIiXZSsmHpCzUmcQKAYSIpUc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$22$AddEmployeeFragment(view2);
            }
        });
        this.offer_order_pdf.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$CAH1XmZfZGPpOfjCqNRBzG-_rC4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$23$AddEmployeeFragment(view2);
            }
        });
    }

    public /* synthetic */ void lambda$onViewCreated$0$AddEmployeeFragment(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener(this.date_of_birth, calendar), calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        if (!TextUtils.isEmpty(this.date_of_birth.getText().toString().trim())) {
            String strTrim = this.date_of_birth.getText().toString().trim();
            datePickerDialog.updateDate(Integer.parseInt(strTrim.split("-")[0]), Integer.parseInt(strTrim.split("-")[1]) - 1, Integer.parseInt(strTrim.split("-")[2]));
        }
        datePickerDialog.show();
    }

    public /* synthetic */ void lambda$onViewCreated$1$AddEmployeeFragment(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener(this.joining_date, calendar), calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        if (!TextUtils.isEmpty(this.joining_date.getText().toString().trim())) {
            String strTrim = this.joining_date.getText().toString().trim();
            datePickerDialog.updateDate(Integer.parseInt(strTrim.split("-")[0]), Integer.parseInt(strTrim.split("-")[1]) - 1, Integer.parseInt(strTrim.split("-")[2]));
        }
        datePickerDialog.show();
    }

    public /* synthetic */ void lambda$onViewCreated$2$AddEmployeeFragment(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener(this.referred_letter_date, calendar), calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        if (!TextUtils.isEmpty(this.referred_letter_date.getText().toString().trim())) {
            String strTrim = this.referred_letter_date.getText().toString().trim();
            datePickerDialog.updateDate(Integer.parseInt(strTrim.split("-")[0]), Integer.parseInt(strTrim.split("-")[1]) - 1, Integer.parseInt(strTrim.split("-")[2]));
        }
        datePickerDialog.show();
    }

    public /* synthetic */ void lambda$onViewCreated$3$AddEmployeeFragment(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener(this.order_effecting_date, calendar), calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        if (!TextUtils.isEmpty(this.order_effecting_date.getText().toString().trim())) {
            String strTrim = this.order_effecting_date.getText().toString().trim();
            datePickerDialog.updateDate(Integer.parseInt(strTrim.split("-")[0]), Integer.parseInt(strTrim.split("-")[1]) - 1, Integer.parseInt(strTrim.split("-")[2]));
        }
        datePickerDialog.show();
    }

    public /* synthetic */ void lambda$onViewCreated$4$AddEmployeeFragment(View view) {
        try {
            getPhotoFromCamera(12, "employee_" + System.currentTimeMillis());
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error getting Photo From Camera", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$5$AddEmployeeFragment(View view) {
        try {
            getPhotoFromCamera(13, "cnic_" + System.currentTimeMillis());
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error getting Photo From Camera", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$9$AddEmployeeFragment(View view) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.camera_pdf_option_sheet);
        dialog.findViewById(R.id.option_pdf).setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$T_m7aAClc7eYILTtztqrG60k2ec
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$6$AddEmployeeFragment(dialog, view2);
            }
        });
        dialog.findViewById(R.id.option_camera).setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$acNOUYaMwa1UelovLD07-YrZd_s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$7$AddEmployeeFragment(dialog, view2);
            }
        });
        dialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$xSV66UoRhskKCstj4jfZBrcyR7w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public /* synthetic */ void lambda$onViewCreated$6$AddEmployeeFragment(Dialog dialog, View view) {
        dialog.dismiss();
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.setType("application/pdf");
            startActivityForResult(intent, 101);
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error getting PDF file", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$7$AddEmployeeFragment(Dialog dialog, View view) {
        dialog.dismiss();
        try {
            getPhotoFromCamera(15, "appointment_order_" + System.currentTimeMillis());
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error getting Photo From Camera", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$10$AddEmployeeFragment(View view) {
        try {
            getPhotoFromCamera(16, "posting_order_" + System.currentTimeMillis());
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error getting Photo From Camera", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$11$AddEmployeeFragment(View view) {
        try {
            getPhotoFromCamera(18, "employee_payslip_" + System.currentTimeMillis());
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error getting Photo From Camera", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$12$AddEmployeeFragment(View view) {
        try {
            getPhotoFromCamera(20, "joining_report_" + System.currentTimeMillis());
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error getting Photo From Camera", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$16$AddEmployeeFragment(View view) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.camera_pdf_option_sheet);
        dialog.findViewById(R.id.option_pdf).setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$WwtgIWBLUabEM1IHaYYyz1eE77o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$13$AddEmployeeFragment(dialog, view2);
            }
        });
        dialog.findViewById(R.id.option_camera).setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$bdZ9jjxWWE3WX0eCSncHangj608
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$14$AddEmployeeFragment(dialog, view2);
            }
        });
        dialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$yioqDbj4QReWbYzrEOlozyMZooE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public /* synthetic */ void lambda$onViewCreated$13$AddEmployeeFragment(Dialog dialog, View view) {
        dialog.dismiss();
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.setType("application/pdf");
            startActivityForResult(intent, 102);
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error getting PDF file", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$14$AddEmployeeFragment(Dialog dialog, View view) {
        dialog.dismiss();
        try {
            getPhotoFromCamera(19, "offer_order_" + System.currentTimeMillis());
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error getting Photo From Camera", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$17$AddEmployeeFragment(View view) {
        try {
            getPhotoFromCamera(21, "attendance_register_" + System.currentTimeMillis());
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error getting Photo From Camera", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$18$AddEmployeeFragment(View view) {
        try {
            getPhotoFromCamera(22, "information_letter_" + System.currentTimeMillis());
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error getting Photo From Camera", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$19$AddEmployeeFragment(View view) {
        if (this.recordFound) {
            int i = this.reasonId;
            if (i != 7 && i != 8 && i != 9 && this.attendanceId == -1) {
                DialogCustom.showError(getActivityContext(), "Please Select Attendance");
                return;
            } else {
                saveDataWhenFound();
                return;
            }
        }
        if (this.localSyncedRecordFound) {
            int i2 = this.reasonId;
            if (i2 != 7 && i2 != 8 && i2 != 9 && this.attendanceId == -1) {
                DialogCustom.showError(getActivityContext(), "Please Select Attendance");
                return;
            } else {
                saveDataWhenLocalSyncedRecordFound();
                return;
            }
        }
        if (this.newRecordFound) {
            if (this.selectedSchool == null) {
                DialogCustom.showError(getActivityContext(), "Please Select a School");
                return;
            } else if (this.reasonId == -1) {
                DialogCustom.showError(getActivityContext(), "Please Select Reason");
                return;
            } else {
                saveDataWhenNewRecordFound();
                return;
            }
        }
        if (this.selectedSchool == null) {
            DialogCustom.showError(getActivityContext(), "Please Select a School");
        } else if (this.reasonId == -1) {
            DialogCustom.showError(getActivityContext(), "Please Select Reason");
        } else {
            saveFieldsData();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$21$AddEmployeeFragment() {
        List<School> list;
        StaffPresenceModel staffPresenceModel = this.staffPresenceModel;
        if (staffPresenceModel != null) {
            final EmployeeDbRecord employeeDbRecord = this.addEmployeeOperations.getEmployeeDbRecord(staffPresenceModel.getRecordId());
            if (!isReasonValid(employeeDbRecord.getAddEmployeeRecord().getReasonForUpdate(), this.REASONS_LIST)) {
                getActivity().runOnUiThread(new $$Lambda$AddEmployeeFragment$7vC5oF1mihqZLmcy4NEm1D2O8(this));
                return;
            }
            EmployeeUpdateRecord addEmployeeRecord = employeeDbRecord.getAddEmployeeRecord();
            this.employeeUpdateRecord = addEmployeeRecord;
            if (addEmployeeRecord.getSchoolIDVisit() != null && (list = this.schools) != null) {
                int i = 0;
                Iterator<School> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    School next = it.next();
                    if (next.getSchoolId().equals(this.employeeUpdateRecord.getSchoolIDVisit())) {
                        this.selectedSchool = next;
                        this.schools_spinner.setSelection(i);
                        break;
                    }
                    i++;
                }
            }
            new Handler().postDelayed(new Runnable() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$M-V9zav_2MXP92zI-lOlGZ0H-FQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onViewCreated$20$AddEmployeeFragment(employeeDbRecord);
                }
            }, 500L);
        }
    }

    public /* synthetic */ void lambda$onViewCreated$20$AddEmployeeFragment(EmployeeDbRecord employeeDbRecord) {
        boolean z;
        switch (employeeDbRecord.getAddEmployeeRecord().getReasonForUpdate().intValue()) {
            case 1:
                setReasonValue(1, this.REASONS_LIST);
                break;
            case 2:
                setReasonValue(2, this.REASONS_LIST);
                break;
            case 4:
                setReasonValue(4, this.REASONS_LIST);
                break;
            case 5:
                setReasonValue(5, this.REASONS_LIST);
                break;
            case 6:
                setReasonValue(6, this.REASONS_LIST);
                break;
            case 7:
                setReasonValue(7, this.REASONS_LIST);
                this.letter_order_layout.setVisibility(0);
                break;
            case 8:
                setReasonValue(8, this.REASONS_LIST);
                this.letter_order_layout.setVisibility(0);
                break;
            case 9:
                setReasonValue(9, this.REASONS_LIST);
                break;
        }
        this.letter_order_layout.setVisibility(0);
        editForm(employeeDbRecord);
        boolean z2 = this.recordFound;
        if ((z2 && this.reOpenId == 0) || (((z = this.localSyncedRecordFound) && this.reOpenId == 0) || !z2 || !z)) {
            showImages(employeeDbRecord);
        }
        getActivity().runOnUiThread(new $$Lambda$AddEmployeeFragment$7vC5oF1mihqZLmcy4NEm1D2O8(this));
    }

    public /* synthetic */ void lambda$onViewCreated$22$AddEmployeeFragment(View view) {
        try {
            File file = this.reasonOrderFile;
            if (file != null && file.exists()) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(MyFilesProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", this.reasonOrderFile), "application/pdf");
                intent.addFlags(1);
                startActivity(intent);
            } else {
                Toast.makeText(view.getContext(), "File does not exists", 0).show();
            }
        } catch (Exception unused) {
            Toast.makeText(view.getContext(), "Unable to open file", 0).show();
        }
    }

    public /* synthetic */ void lambda$onViewCreated$23$AddEmployeeFragment(View view) {
        try {
            File file = this.offerOrderFile;
            if (file != null && file.exists()) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(MyFilesProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", this.offerOrderFile), "application/pdf");
                intent.addFlags(1);
                startActivity(intent);
            } else {
                Toast.makeText(view.getContext(), "File does not exists", 0).show();
            }
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(view.getContext(), "Please install PDF reader application to read PDF file", 0).show();
        } catch (Exception unused2) {
            Toast.makeText(view.getContext(), "Unable to open file", 0).show();
        }
    }

    private void initUIFields(View view) {
        this.school_layout = (LinearLayout) view.findViewById(R.id.school_layout);
        this.reason_layout = (LinearLayout) view.findViewById(R.id.reason_layout);
        this.employee_id_layout = (LinearLayout) view.findViewById(R.id.employee_id_layout);
        this.name_layout = (LinearLayout) view.findViewById(R.id.name_layout);
        this.cnic_layout = (LinearLayout) view.findViewById(R.id.cnic_layout);
        this.contact_no_layout = (LinearLayout) view.findViewById(R.id.contact_no_layout);
        this.dob_layout = (LinearLayout) view.findViewById(R.id.dob_layout);
        this.designation_layout = (LinearLayout) view.findViewById(R.id.designation_layout);
        this.joining_date_layout = (LinearLayout) view.findViewById(R.id.joining_date_layout);
        this.gender_layout = (LinearLayout) view.findViewById(R.id.gender_layout);
        this.images_layout = (LinearLayout) view.findViewById(R.id.images_layout);
        this.admin_response_layout = (LinearLayout) view.findViewById(R.id.admin_response_layout);
        this.re_open_layout = (LinearLayout) view.findViewById(R.id.re_open_layout);
        this.attendance_layout = (LinearLayout) view.findViewById(R.id.attendance_layout);
        this.remarks_layout = (RelativeLayout) view.findViewById(R.id.remarks_layout);
        this.employee_picture_layout = (LinearLayout) view.findViewById(R.id.employee_picture_layout);
        this.cnic_picture_layout = (LinearLayout) view.findViewById(R.id.cnic_picture_layout);
        this.appointment_order_picture_layout = (LinearLayout) view.findViewById(R.id.appointment_order_picture_layout);
        this.employee_payslip_layout = (LinearLayout) view.findViewById(R.id.employee_payslip_layout);
        this.offer_order_layout = (LinearLayout) view.findViewById(R.id.offer_order_layout);
        this.joining_report_layout = (LinearLayout) view.findViewById(R.id.joining_report_layout);
        this.picture_attendance_register_layout = (LinearLayout) view.findViewById(R.id.picture_attendance_register_layout);
        this.information_letter_layout = (LinearLayout) view.findViewById(R.id.information_letter_layout);
        this.letter_order_layout = (LinearLayout) view.findViewById(R.id.letter_order_layout);
        this.tv_employee_image = (TextView) view.findViewById(R.id.tv_employee_image);
        this.tv_cnic_image = (TextView) view.findViewById(R.id.tv_cnic_image);
        this.tv_employee_payslip = (TextView) view.findViewById(R.id.tv_employee_payslip);
        this.tv_offer_order = (TextView) view.findViewById(R.id.tv_offer_order);
        this.tv_joining_report = (TextView) view.findViewById(R.id.tv_joining_report);
        this.tv_picture_attendance_register = (TextView) view.findViewById(R.id.tv_picture_attendance_register);
        this.tv_information_letter = (TextView) view.findViewById(R.id.tv_information_letter);
        this.schools_spinner = (SearchableSpinner) view.findViewById(R.id.schools_spinner);
        this.rg_reason = (RadioGroup) view.findViewById(R.id.rg_reason);
        this.rg_gender = (RadioGroup) view.findViewById(R.id.rg_gender);
        this.rg_attendance = (RadioGroup) view.findViewById(R.id.rg_attendance);
        this.rg_re_open = (RadioGroup) view.findViewById(R.id.rg_re_open);
        this.status_card = (CardView) view.findViewById(R.id.status_card);
        this.date_of_birth = (TextView) view.findViewById(R.id.date_of_birth);
        this.joining_date = (TextView) view.findViewById(R.id.joining_date);
        this.referred_letter_date = (TextView) view.findViewById(R.id.order_date);
        this.tv_order_no = (TextView) view.findViewById(R.id.tv_order_no);
        this.tv_order_date = (TextView) view.findViewById(R.id.tv_order_date);
        this.tv_retired_expired_date = (TextView) view.findViewById(R.id.tv_retired_expired_date);
        this.order_effecting_date = (TextView) view.findViewById(R.id.retired_expired_date);
        this.tv_person_id = (TextView) view.findViewById(R.id.tv_person_id);
        this.tv_designation = (TextView) view.findViewById(R.id.tv_designation);
        this.tv_appointment_order = (TextView) view.findViewById(R.id.tv_appointment_order);
        this.et_person_id = (EditText) view.findViewById(R.id.et_person_id);
        this.et_name = (EditText) view.findViewById(R.id.et_name);
        this.et_cnic = (EditText) view.findViewById(R.id.et_cnic);
        this.et_contact_no = (EditText) view.findViewById(R.id.et_contact_no);
        this.et_designation = (EditText) view.findViewById(R.id.et_designation);
        this.et_comments = (EditText) view.findViewById(R.id.et_comments);
        this.et_referred_letter_no = (EditText) view.findViewById(R.id.et_order_no);
        this.employee_picture = (ImageView) view.findViewById(R.id.employee_picture);
        this.employee_picture_camera_icon = (ImageView) view.findViewById(R.id.employee_picture_camera_icon);
        this.cnic_picture = (ImageView) view.findViewById(R.id.cnic_picture);
        this.cnic_picture_camera_icon = (ImageView) view.findViewById(R.id.cnic_picture_camera_icon);
        this.appointment_order_picture = (ImageView) view.findViewById(R.id.appointment_order_picture);
        this.appointment_order_picture_camera_icon = (ImageView) view.findViewById(R.id.appointment_order_picture_camera_icon);
        this.appointment_order_pdf = (ImageView) view.findViewById(R.id.appointment_order_pdf);
        this.posting_order_picture = (ImageView) view.findViewById(R.id.posting_order_picture);
        this.posting_order_picture_camera_icon = (ImageView) view.findViewById(R.id.posting_order_picture_camera_icon);
        this.employee_payslip_picture = (ImageView) view.findViewById(R.id.employee_payslip_picture);
        this.employee_payslip_layout_camera_icon = (ImageView) view.findViewById(R.id.employee_payslip_layout_camera_icon);
        this.joining_report_picture = (ImageView) view.findViewById(R.id.joining_report_picture);
        this.joining_report_layout_camera_icon = (ImageView) view.findViewById(R.id.joining_report_layout_camera_icon);
        this.offer_order_picture = (ImageView) view.findViewById(R.id.offer_order_picture);
        this.offer_order_picture_camera_icon = (ImageView) view.findViewById(R.id.offer_order_picture_camera_icon);
        this.offer_order_pdf = (ImageView) view.findViewById(R.id.offer_order_pdf);
        this.attendance_register_picture = (ImageView) view.findViewById(R.id.attendance_register_picture);
        this.picture_attendance_register_layout_camera_icon = (ImageView) view.findViewById(R.id.picture_attendance_register_layout_camera_icon);
        this.information_letter_picture = (ImageView) view.findViewById(R.id.information_letter_picture);
        this.information_letter_camera_icon = (ImageView) view.findViewById(R.id.information_letter_camera_icon);
        this.tv_admin_response = (TextView) view.findViewById(R.id.tv_admin_response);
        this.tv_ticket_status = (TextView) view.findViewById(R.id.tv_ticket_status);
        this.response_picture_layout = (LinearLayout) view.findViewById(R.id.response_picture_layout);
        this.response_picture_tap = (RelativeLayout) view.findViewById(R.id.response_picture_tap);
        this.tv_tap_image_view = (TextView) view.findViewById(R.id.tv_tap_image_view);
        this.tv_attendance = (TextView) view.findViewById(R.id.tv_attendance);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.images_linear_layout);
        this.images_linear_layout = linearLayout;
        linearLayout.removeAllViews();
        this.save_btn = (Button) view.findViewById(R.id.save_btn);
    }

    private void renderUI(final View view) {
        List<School> schools = getSchools();
        this.schools = schools;
        if (schools != null && !schools.isEmpty()) {
            try {
                if (Build.VERSION.SDK_INT >= 24) {
                    this.schools.sort(Comparator.comparing(new Function() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$NoaJcV37MMVFcZuiCAn6suK7hK4
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ((School) obj).getSchoolName();
                        }
                    }));
                } else {
                    Collections.sort(this.schools, new Comparator<School>() { // from class: com.micromerger.ssms.addemployee.AddEmployeeFragment.1
                        @Override // java.util.Comparator
                        public int compare(School school1, School school2) {
                            return school1.getSchoolName().compareTo(school2.getSchoolName());
                        }
                    });
                }
            } catch (Exception e) {
                util.logException(new Exception("Unable to sort schools " + e));
            }
            ArrayList arrayList = new ArrayList();
            for (School school : this.schools) {
                arrayList.add(view.getContext().getResources().getString(R.string.school_dropdown_title, getValue(school.getSchoolPrefix()), getValue(school.getSchoolName()), getValue(school.getSchoolSemisCode())));
            }
            this.schools_spinner.setTitle(view.getContext().getResources().getString(R.string.select_school));
            this.schools_spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(view.getContext(), R.layout.school_list_dropdown, arrayList));
            this.schools_spinner.setPositiveButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$Y5IOgcV1dG7tJz8zAsTc5bPnt1I
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            this.schools_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.micromerger.ssms.addemployee.AddEmployeeFragment.2
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> parent) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> parent, View view2, int position, long id2) {
                    AddEmployeeFragment addEmployeeFragment = AddEmployeeFragment.this;
                    addEmployeeFragment.selectedSchool = addEmployeeFragment.schools.get(position);
                }
            });
            renderReasons(view);
            renderGender();
            renderAttendanceOptions();
            renderReOpenTickerOptions();
        }
        triggerFormVisibility();
        this.et_cnic.addTextChangedListener(new TextWatcher() { // from class: com.micromerger.ssms.addemployee.AddEmployeeFragment.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int i = start + before;
                if (i == 13 && s.length() < i) {
                    AddEmployeeFragment.this.clearForm();
                }
                if (s.length() == 13) {
                    AddEmployeeFragment.this.typing = false;
                    if (AddEmployeeFragment.this.reasonId != -1) {
                        AddEmployeeFragment.this.fetchRecord(s.toString().trim(), AddEmployeeFragment.this.reasonId, view);
                    }
                } else {
                    AddEmployeeFragment.this.typing = true;
                }
                AddEmployeeFragment.this.triggerFormVisibility();
            }
        });
    }

    private void renderReasons(final View view) {
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$FRqE9qEDpribtkvJCcJmil20NcA
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("EMPLOYEE_RECORD_UPDATE_REASON");
            }
        });
        if (collectionSelect instanceof List) {
            this.REASONS_LIST = (List) collectionSelect;
        } else {
            this.REASONS_LIST = new ArrayList(collectionSelect);
        }
        if (this.REASONS_LIST.isEmpty()) {
            return;
        }
        this.rg_reason.setOrientation(1);
        for (int i = 0; i < this.REASONS_LIST.size(); i++) {
            this.reasonsMap.put(this.REASONS_LIST.get(i).getReferencekey(), this.REASONS_LIST.get(i).getReferencevalue());
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(this.REASONS_LIST.get(i).getReferencedataid().intValue());
            radioButton.setText(this.REASONS_LIST.get(i).getReferencevalue());
            radioButton.setTextColor(getResources().getColor(R.color.boundary_wall_text_bg_color));
            radioButton.setTextSize(16.0f);
            radioButton.setButtonDrawable(R.drawable.radiobuttonstate);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$9IMXRfeEAZo0KVqpcawlamhkC3M
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    this.f$0.lambda$renderReasons$26$AddEmployeeFragment(view, compoundButton, z);
                }
            });
            this.rg_reason.addView(radioButton);
        }
    }

    public /* synthetic */ void lambda$renderReasons$26$AddEmployeeFragment(View view, CompoundButton compoundButton, boolean z) {
        if (compoundButton.isChecked()) {
            this.images_layout.setVisibility(0);
            this.reasonOrderFile = null;
            letterFieldsOptional();
            this.appointment_order_pdf.setVisibility(8);
            this.appointment_order_picture.setVisibility(8);
            for (ReferenceDataResponse.ReferenceData referenceData : this.REASONS_LIST) {
                if (referenceData.getReferencedataid().intValue() == compoundButton.getId()) {
                    this.reasonId = referenceData.getReferencekey().intValue();
                    String strTrim = this.et_cnic.getText().toString().trim();
                    if (strTrim.length() == 13) {
                        fetchRecord(strTrim, this.reasonId, view);
                    }
                    if (!this.recordFound) {
                        this.tv_person_id.setText(R.string.person_id);
                        this.tv_designation.setText(R.string.designation_);
                    }
                    int i = this.reasonId;
                    if (i == 1) {
                        showNewAppointeeFields();
                    } else if (i == 2) {
                        showTransferInFields();
                    } else if (i == 4) {
                        showPromotedFields();
                    } else if (i == 5) {
                        showBiometricIssueFields();
                    } else if (i == 6) {
                        showAllowToWorkFields();
                    } else if (i == 7) {
                        showExpiredFields();
                    } else if (i == 8) {
                        showRetiredFields();
                    } else if (i == 9) {
                        showEmployeeProfilePictureFields();
                    }
                }
            }
        }
        triggerFormVisibility();
        imageTitlesRequiredHandling();
        setOrderTitle();
    }

    private boolean isReasonValid(Integer reasonId, List<ReferenceDataResponse.ReferenceData> REASONS_LIST) {
        for (int i = 0; i < REASONS_LIST.size(); i++) {
            if (REASONS_LIST.get(i).getReferencekey().equals(reasonId)) {
                return true;
            }
        }
        return false;
    }

    private void setReasonValue(Integer reasonId, List<ReferenceDataResponse.ReferenceData> REASONS_LIST) {
        for (int i = 0; i < REASONS_LIST.size(); i++) {
            if (REASONS_LIST.get(i).getReferencekey().equals(reasonId)) {
                this.rg_reason.check(REASONS_LIST.get(i).getReferencedataid().intValue());
                return;
            }
        }
    }

    private void showNewAppointeeFields() {
        this.tv_person_id.setText(R.string.person_id);
        this.images_linear_layout.removeAllViews();
        this.images_linear_layout.addView(this.employee_picture_layout);
        this.images_linear_layout.addView(this.cnic_picture_layout);
        this.images_linear_layout.addView(this.employee_payslip_layout);
        this.images_linear_layout.addView(this.offer_order_layout);
        this.images_linear_layout.addView(this.appointment_order_picture_layout);
        this.images_linear_layout.addView(this.joining_report_layout);
        this.images_linear_layout.addView(this.picture_attendance_register_layout);
    }

    private void showTransferInFields() {
        this.tv_person_id.setText(R.string.person_id_);
        this.images_linear_layout.removeAllViews();
        this.images_linear_layout.addView(this.employee_picture_layout);
        this.images_linear_layout.addView(this.cnic_picture_layout);
        this.images_linear_layout.addView(this.employee_payslip_layout);
        this.images_linear_layout.addView(this.appointment_order_picture_layout);
        this.images_linear_layout.addView(this.joining_report_layout);
        this.images_linear_layout.addView(this.picture_attendance_register_layout);
    }

    private void showPromotedFields() {
        this.tv_person_id.setText(R.string.person_id_);
        this.images_linear_layout.removeAllViews();
        this.images_linear_layout.addView(this.employee_picture_layout);
        this.images_linear_layout.addView(this.cnic_picture_layout);
        this.images_linear_layout.addView(this.employee_payslip_layout);
        this.images_linear_layout.addView(this.appointment_order_picture_layout);
        this.images_linear_layout.addView(this.joining_report_layout);
        this.images_linear_layout.addView(this.picture_attendance_register_layout);
    }

    private void showBiometricIssueFields() {
        this.tv_person_id.setText(R.string.person_id_);
        this.images_linear_layout.removeAllViews();
        this.images_linear_layout.addView(this.employee_picture_layout);
        this.images_linear_layout.addView(this.cnic_picture_layout);
        this.images_linear_layout.addView(this.employee_payslip_layout);
    }

    private void showAllowToWorkFields() {
        this.tv_person_id.setText(R.string.person_id);
        this.images_linear_layout.removeAllViews();
        this.images_linear_layout.addView(this.employee_picture_layout);
        this.images_linear_layout.addView(this.cnic_picture_layout);
        this.images_linear_layout.addView(this.employee_payslip_layout);
        this.images_linear_layout.addView(this.appointment_order_picture_layout);
        this.images_linear_layout.addView(this.joining_report_layout);
        this.images_linear_layout.addView(this.picture_attendance_register_layout);
    }

    private void showExpiredFields() {
        this.tv_person_id.setText(R.string.person_id_);
        this.tv_retired_expired_date.setText(R.string.order_effecting_date);
        this.order_effecting_date.setHint(R.string.order_effecting_date);
        clearAttendance();
        this.images_linear_layout.removeAllViews();
        this.images_linear_layout.addView(this.employee_picture_layout);
        this.images_linear_layout.addView(this.cnic_picture_layout);
        this.images_linear_layout.addView(this.employee_payslip_layout);
        this.images_linear_layout.addView(this.appointment_order_picture_layout);
        this.images_linear_layout.addView(this.information_letter_layout);
    }

    private void showRetiredFields() {
        this.tv_person_id.setText(R.string.person_id_);
        this.tv_retired_expired_date.setText(R.string.order_effecting_date);
        this.order_effecting_date.setHint(R.string.order_effecting_date);
        clearAttendance();
        this.images_linear_layout.removeAllViews();
        this.images_linear_layout.addView(this.employee_picture_layout);
        this.images_linear_layout.addView(this.cnic_picture_layout);
        this.images_linear_layout.addView(this.employee_payslip_layout);
        this.images_linear_layout.addView(this.appointment_order_picture_layout);
        this.images_linear_layout.addView(this.information_letter_layout);
    }

    private void showEmployeeProfilePictureFields() {
        clearAttendance();
        letterFieldsNotRequired();
        this.images_linear_layout.removeAllViews();
        this.images_linear_layout.addView(this.employee_picture_layout);
    }

    private void clearAttendance() {
        this.rg_attendance.clearCheck();
        this.attendanceId = -1;
        this.attendance_layout.setVisibility(8);
    }

    private void letterFieldsRequired() {
        this.letter_order_layout.setVisibility(0);
        this.tv_order_no.setText(R.string.letter_order_no_);
        this.tv_order_date.setText(R.string.letter_order_date_);
        this.tv_retired_expired_date.setText(R.string.order_effecting_date_);
        this.letterFieldsRequired = true;
    }

    private void letterFieldsOptional() {
        this.letter_order_layout.setVisibility(0);
        this.tv_order_no.setText(R.string.letter_order_no);
        this.tv_order_date.setText(R.string.letter_order_date);
        this.tv_retired_expired_date.setText(R.string.order_effecting_date);
        this.letterFieldsRequired = false;
    }

    private void letterFieldsNotRequired() {
        this.letter_order_layout.setVisibility(8);
        this.tv_order_no.setText(R.string.letter_order_no);
        this.tv_order_date.setText(R.string.letter_order_date);
        this.tv_retired_expired_date.setText(R.string.order_effecting_date);
        this.letterFieldsRequired = false;
    }

    private void setOrderTitle() {
        if (this.attendanceId == 0) {
            int i = this.reasonId;
            if (i == 1) {
                this.tv_appointment_order.setText(R.string.appointment_order_);
                return;
            }
            if (i == 2) {
                this.tv_appointment_order.setText(R.string.transfer_order_);
                return;
            }
            if (i == 4) {
                this.tv_appointment_order.setText(R.string.promotion_order_);
                return;
            }
            if (i == 6) {
                this.tv_appointment_order.setText(R.string.authority_order_);
                return;
            } else if (i == 7) {
                this.tv_appointment_order.setText(R.string.obituary_order_death_certificate_);
                return;
            } else {
                if (i != 8) {
                    return;
                }
                this.tv_appointment_order.setText(R.string.retirement_order_);
                return;
            }
        }
        int i2 = this.reasonId;
        if (i2 == 1) {
            this.tv_appointment_order.setText(R.string.appointment_order);
            return;
        }
        if (i2 == 2) {
            this.tv_appointment_order.setText(R.string.transfer_order);
            return;
        }
        if (i2 == 4) {
            this.tv_appointment_order.setText(R.string.promotion_order);
            return;
        }
        if (i2 == 6) {
            this.tv_appointment_order.setText(R.string.authority_order);
        } else if (i2 == 7) {
            this.tv_appointment_order.setText(R.string.obituary_order_death_certificate);
        } else {
            if (i2 != 8) {
                return;
            }
            this.tv_appointment_order.setText(R.string.retirement_order);
        }
    }

    private void renderGender() {
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$aU3LT67clP6EL5MJcntF9QObpu8
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("EMPLOYEE_RECORD_UPDATE_GENDER");
            }
        });
        if (collectionSelect instanceof List) {
            this.GENDER_LIST = (List) collectionSelect;
        } else {
            this.GENDER_LIST = new ArrayList(collectionSelect);
        }
        if (this.GENDER_LIST.isEmpty()) {
            return;
        }
        this.rg_gender.setOrientation(1);
        for (ReferenceDataResponse.ReferenceData referenceData : this.GENDER_LIST) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(referenceData.getReferencedataid().intValue());
            radioButton.setText(referenceData.getReferencevalue());
            radioButton.setTextColor(getResources().getColor(R.color.boundary_wall_text_bg_color));
            radioButton.setTextSize(16.0f);
            radioButton.setButtonDrawable(R.drawable.radiobuttonstate);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$JGe--dApPRx44BBy_ok5U13nWn8
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    this.f$0.lambda$renderGender$28$AddEmployeeFragment(compoundButton, z);
                }
            });
            this.rg_gender.addView(radioButton);
        }
    }

    public /* synthetic */ void lambda$renderGender$28$AddEmployeeFragment(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isChecked()) {
            for (ReferenceDataResponse.ReferenceData referenceData : this.GENDER_LIST) {
                if (referenceData.getReferencedataid().intValue() == compoundButton.getId()) {
                    this.genderId = referenceData.getReferencekey().intValue();
                }
            }
        }
    }

    private void renderAttendanceOptions() {
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$s0bukLxC54-vCPbH_h0igHjFpfQ
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("EMPLOYEE_ATTENDANCE");
            }
        });
        if (collectionSelect instanceof List) {
            this.ATTENDANCE_LIST = (List) collectionSelect;
        } else {
            this.ATTENDANCE_LIST = new ArrayList(collectionSelect);
        }
        if (this.ATTENDANCE_LIST.isEmpty()) {
            return;
        }
        this.rg_attendance.setOrientation(1);
        for (ReferenceDataResponse.ReferenceData referenceData : this.ATTENDANCE_LIST) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(referenceData.getReferencedataid().intValue());
            radioButton.setText(referenceData.getReferencevalue());
            radioButton.setTextColor(getResources().getColor(R.color.boundary_wall_text_bg_color));
            radioButton.setTextSize(16.0f);
            radioButton.setButtonDrawable(R.drawable.radiobuttonstate);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$qN9psDiElYEOMYDRqw9m2JX5VUo
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    this.f$0.lambda$renderAttendanceOptions$30$AddEmployeeFragment(compoundButton, z);
                }
            });
            this.rg_attendance.addView(radioButton);
        }
    }

    public /* synthetic */ void lambda$renderAttendanceOptions$30$AddEmployeeFragment(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isChecked()) {
            for (ReferenceDataResponse.ReferenceData referenceData : this.ATTENDANCE_LIST) {
                if (referenceData.getReferencedataid().intValue() == compoundButton.getId()) {
                    this.attendanceId = referenceData.getReferencekey().intValue();
                }
            }
            imageTitlesRequiredHandling();
        }
    }

    private boolean imagesRequired() {
        boolean z;
        boolean z2;
        return this.attendanceId == 0 && (((z = this.recordFound) && this.reOpenId == 0) || (((z2 = this.localSyncedRecordFound) && this.reOpenId == 0) || !(z || z2)));
    }

    private void imageTitlesRequiredHandling() {
        boolean zImagesRequired = imagesRequired();
        switch (this.reasonId) {
            case 1:
                this.employee_picture_layout.setVisibility(0);
                this.cnic_picture_layout.setVisibility(0);
                this.employee_payslip_layout.setVisibility(0);
                this.offer_order_layout.setVisibility(0);
                this.appointment_order_picture_layout.setVisibility(0);
                this.joining_report_layout.setVisibility(0);
                this.picture_attendance_register_layout.setVisibility(0);
                this.information_letter_layout.setVisibility(8);
                if (zImagesRequired) {
                    this.tv_employee_image.setText(R.string.employee_picture_);
                    this.tv_cnic_image.setText(R.string.employee_cnic_);
                    this.tv_employee_payslip.setText(R.string.employee_payslip);
                    this.tv_offer_order.setText(R.string.offer_order_);
                    this.tv_appointment_order.setText(R.string.appointment_order_);
                    this.tv_joining_report.setText(R.string.joining_report_);
                    this.tv_picture_attendance_register.setText(R.string.picture_of_attendance_register_current_visit_);
                    break;
                } else {
                    this.tv_employee_image.setText(R.string.employee_picture);
                    this.tv_cnic_image.setText(R.string.employee_cnic);
                    this.tv_employee_payslip.setText(R.string.employee_payslip);
                    this.tv_offer_order.setText(R.string.offer_order);
                    this.tv_appointment_order.setText(R.string.appointment_order);
                    this.tv_joining_report.setText(R.string.joining_report);
                    this.tv_picture_attendance_register.setText(R.string.picture_of_attendance_register_current_visit);
                    break;
                }
            case 2:
                this.employee_picture_layout.setVisibility(0);
                this.cnic_picture_layout.setVisibility(0);
                this.employee_payslip_layout.setVisibility(0);
                this.offer_order_layout.setVisibility(8);
                this.appointment_order_picture_layout.setVisibility(0);
                this.joining_report_layout.setVisibility(0);
                this.picture_attendance_register_layout.setVisibility(0);
                this.information_letter_layout.setVisibility(8);
                if (zImagesRequired) {
                    this.tv_employee_image.setText(R.string.employee_picture_);
                    this.tv_cnic_image.setText(R.string.employee_cnic);
                    this.tv_employee_payslip.setText(R.string.employee_payslip_);
                    this.tv_appointment_order.setText(R.string.transfer_order_);
                    this.tv_joining_report.setText(R.string.joining_report_);
                    this.tv_picture_attendance_register.setText(R.string.picture_of_attendance_register_current_visit_);
                    break;
                } else {
                    this.tv_employee_image.setText(R.string.employee_picture);
                    this.tv_cnic_image.setText(R.string.employee_cnic);
                    this.tv_employee_payslip.setText(R.string.employee_payslip);
                    this.tv_appointment_order.setText(R.string.transfer_order);
                    this.tv_joining_report.setText(R.string.joining_report);
                    this.tv_picture_attendance_register.setText(R.string.picture_of_attendance_register_current_visit);
                    break;
                }
            case 4:
                this.employee_picture_layout.setVisibility(0);
                this.cnic_picture_layout.setVisibility(0);
                this.employee_payslip_layout.setVisibility(0);
                this.offer_order_layout.setVisibility(8);
                this.appointment_order_picture_layout.setVisibility(0);
                this.joining_report_layout.setVisibility(0);
                this.picture_attendance_register_layout.setVisibility(0);
                this.information_letter_layout.setVisibility(8);
                if (zImagesRequired) {
                    this.tv_employee_image.setText(R.string.employee_picture_);
                    this.tv_cnic_image.setText(R.string.employee_cnic);
                    this.tv_employee_payslip.setText(R.string.employee_payslip_);
                    this.tv_appointment_order.setText(R.string.promotion_order_);
                    this.tv_joining_report.setText(R.string.joining_report_);
                    this.tv_picture_attendance_register.setText(R.string.picture_of_attendance_register_current_visit_);
                    break;
                } else {
                    this.tv_employee_image.setText(R.string.employee_picture);
                    this.tv_cnic_image.setText(R.string.employee_cnic);
                    this.tv_employee_payslip.setText(R.string.employee_payslip);
                    this.tv_appointment_order.setText(R.string.promotion_order);
                    this.tv_joining_report.setText(R.string.joining_report);
                    this.tv_picture_attendance_register.setText(R.string.picture_of_attendance_register_current_visit);
                    break;
                }
            case 5:
                this.employee_picture_layout.setVisibility(0);
                this.cnic_picture_layout.setVisibility(0);
                this.employee_payslip_layout.setVisibility(0);
                this.offer_order_layout.setVisibility(8);
                this.appointment_order_picture_layout.setVisibility(8);
                this.joining_report_layout.setVisibility(8);
                this.picture_attendance_register_layout.setVisibility(8);
                this.information_letter_layout.setVisibility(8);
                if (zImagesRequired) {
                    this.tv_employee_image.setText(R.string.employee_picture_);
                    this.tv_cnic_image.setText(R.string.employee_cnic_);
                    this.tv_employee_payslip.setText(R.string.employee_payslip_);
                    break;
                } else {
                    this.tv_employee_image.setText(R.string.employee_picture);
                    this.tv_cnic_image.setText(R.string.employee_cnic);
                    this.tv_employee_payslip.setText(R.string.employee_payslip);
                    break;
                }
            case 6:
                this.employee_picture_layout.setVisibility(0);
                this.cnic_picture_layout.setVisibility(0);
                this.employee_payslip_layout.setVisibility(0);
                this.offer_order_layout.setVisibility(8);
                this.appointment_order_picture_layout.setVisibility(0);
                this.joining_report_layout.setVisibility(0);
                this.picture_attendance_register_layout.setVisibility(0);
                this.information_letter_layout.setVisibility(8);
                if (zImagesRequired) {
                    this.tv_employee_image.setText(R.string.employee_picture_);
                    this.tv_cnic_image.setText(R.string.employee_cnic_);
                    this.tv_employee_payslip.setText(R.string.employee_payslip_);
                    this.tv_appointment_order.setText(R.string.authority_order_);
                    this.tv_joining_report.setText(R.string.joining_report_);
                    this.tv_picture_attendance_register.setText(R.string.picture_of_attendance_register_current_visit_);
                    break;
                } else {
                    this.tv_employee_image.setText(R.string.employee_picture);
                    this.tv_cnic_image.setText(R.string.employee_cnic);
                    this.tv_employee_payslip.setText(R.string.employee_payslip);
                    this.tv_appointment_order.setText(R.string.authority_order);
                    this.tv_joining_report.setText(R.string.joining_report);
                    this.tv_picture_attendance_register.setText(R.string.picture_of_attendance_register_current_visit);
                    break;
                }
            case 7:
                this.employee_picture_layout.setVisibility(0);
                this.cnic_picture_layout.setVisibility(0);
                this.employee_payslip_layout.setVisibility(0);
                this.offer_order_layout.setVisibility(8);
                this.appointment_order_picture_layout.setVisibility(0);
                this.joining_report_layout.setVisibility(8);
                this.picture_attendance_register_layout.setVisibility(8);
                this.information_letter_layout.setVisibility(0);
                this.tv_employee_image.setText(R.string.employee_picture);
                this.tv_cnic_image.setText(R.string.employee_cnic);
                this.tv_employee_payslip.setText(R.string.employee_payslip);
                this.tv_appointment_order.setText(R.string.obituary_order_death_certificate);
                this.tv_information_letter.setText(R.string.information_letter_expired);
                break;
            case 8:
                this.employee_picture_layout.setVisibility(0);
                this.cnic_picture_layout.setVisibility(0);
                this.employee_payslip_layout.setVisibility(0);
                this.offer_order_layout.setVisibility(8);
                this.appointment_order_picture_layout.setVisibility(0);
                this.joining_report_layout.setVisibility(8);
                this.picture_attendance_register_layout.setVisibility(8);
                this.information_letter_layout.setVisibility(0);
                this.tv_employee_image.setText(R.string.employee_picture);
                this.tv_cnic_image.setText(R.string.employee_cnic);
                this.tv_employee_payslip.setText(R.string.employee_payslip);
                this.tv_appointment_order.setText(R.string.retirement_order);
                this.tv_information_letter.setText(R.string.information_letter_retired);
                break;
            case 9:
                this.employee_picture_layout.setVisibility(0);
                this.tv_employee_image.setText(R.string.employee_picture_);
                break;
        }
    }

    private void letterFieldsRequiredHandling() {
        if (this.reasonId != 9) {
            return;
        }
        letterFieldsNotRequired();
    }

    private List<School> getSchools() {
        return this.preferenceHelper.getAllSchools().getData();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener(final TextView textView, final Calendar calendar) {
        return new DatePickerDialog.OnDateSetListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$vfBRNYaOKpCwU66-03iFF41O5GY
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                this.f$0.lambda$dateSetListener$31$AddEmployeeFragment(calendar, textView, datePicker, i, i2, i3);
            }
        };
    }

    public /* synthetic */ void lambda$dateSetListener$31$AddEmployeeFragment(Calendar calendar, TextView textView, DatePicker datePicker, int i, int i2, int i3) {
        calendar.set(1, i);
        calendar.set(2, i2);
        calendar.set(5, i3);
        setDate(textView, calendar);
    }

    private void setDate(TextView textView, Calendar calendar) {
        textView.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.getTime()));
        textView.setError(null);
    }

    private void saveFieldsData() {
        try {
            if (validateFields() && validateImages()) {
                loadingStarted();
                this.addEmployeeOperations.saveEmployeeRecord(buildEmployeeDbObject(), this.editMode);
                loadingFinished();
                this.fm.popBackStack();
            }
        } catch (Exception unused) {
            Toast.makeText(getContext(), "Error occurred while saving", 0).show();
            loadingFinished();
        }
    }

    private boolean validateLetterFieldsFields() {
        if (this.letterFieldsRequired && TextUtils.isEmpty(this.et_referred_letter_no.getText().toString().trim())) {
            DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
            this.et_referred_letter_no.setError(this.thisFieldIsRequired);
        } else if (this.letterFieldsRequired && TextUtils.isEmpty(this.referred_letter_date.getText().toString().trim())) {
            DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
            this.referred_letter_date.setError(this.thisFieldIsRequired);
        } else {
            if (!this.letterFieldsRequired || !TextUtils.isEmpty(this.order_effecting_date.getText().toString().trim())) {
                return true;
            }
            DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
            this.order_effecting_date.setError(this.thisFieldIsRequired);
        }
        return false;
    }

    private boolean validateFields() {
        if (this.reasonId == 1 && TextUtils.isEmpty(this.et_name.getText().toString().trim())) {
            DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
            this.et_name.setError(this.thisFieldIsRequired);
        } else if (this.reasonId == 1 && TextUtils.isEmpty(this.et_cnic.getText().toString().trim())) {
            DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
            this.et_cnic.setError(this.thisFieldIsRequired);
        } else if (this.reasonId == 1 && TextUtils.isEmpty(this.et_contact_no.getText().toString().trim())) {
            DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
            this.et_contact_no.setError(this.thisFieldIsRequired);
        } else if (this.reasonId == 1 && TextUtils.isEmpty(this.date_of_birth.getText().toString().trim())) {
            DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
            this.date_of_birth.setError(this.thisFieldIsRequired);
        } else if (TextUtils.isEmpty(this.et_designation.getText().toString().trim())) {
            DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
            this.et_designation.setError(this.thisFieldIsRequired);
        } else if (this.reasonId == 1 && TextUtils.isEmpty(this.joining_date.getText().toString().trim())) {
            DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
            this.joining_date.setError(this.thisFieldIsRequired);
        } else {
            int i = this.reasonId;
            if (i == 1 && this.genderId == -1) {
                DialogCustom.showError(getActivityContext(), "Please Select Gender");
            } else if (i != 7 && i != 8 && i != 9 && this.attendanceId == -1) {
                DialogCustom.showError(getActivityContext(), "Please Select Attendance");
            } else if (this.letterFieldsRequired && TextUtils.isEmpty(this.et_referred_letter_no.getText().toString().trim())) {
                DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
                this.et_referred_letter_no.setError(this.thisFieldIsRequired);
            } else if (this.letterFieldsRequired && TextUtils.isEmpty(this.referred_letter_date.getText().toString().trim())) {
                DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
                this.referred_letter_date.setError(this.thisFieldIsRequired);
            } else {
                if (!this.letterFieldsRequired || !TextUtils.isEmpty(this.order_effecting_date.getText().toString().trim())) {
                    return true;
                }
                DialogCustom.showError(getActivityContext(), this.pleaseFillAllFields);
                this.order_effecting_date.setError(this.thisFieldIsRequired);
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean validateImages() {
        boolean z;
        boolean zImagesRequired = imagesRequired();
        int i = this.reasonId;
        switch (i) {
            case 1:
                if (!zImagesRequired) {
                    return true;
                }
                if (this.employeePictureFile == null) {
                    showImageErrorDialog("Employee Picture");
                } else if (this.cnicPictureFile == null) {
                    showImageErrorDialog("Employee CNIC");
                } else if (this.offerOrderFile == null) {
                    showImageErrorDialog("Offer Order");
                } else if (this.reasonOrderFile == null) {
                    showImageErrorDialog("Appointment Order");
                } else if (this.joiningReportFile == null) {
                    showImageErrorDialog("Joining Report");
                } else {
                    if (this.pictureAttendanceRegisterFile != null) {
                        return true;
                    }
                    showImageErrorDialog("Picture of Attendance Register (Current Visit)");
                }
                return false;
            case 2:
            case 4:
                if (!zImagesRequired) {
                    return true;
                }
                if (this.employeePictureFile == null) {
                    showImageErrorDialog("Employee Picture");
                } else if (this.employeePayslipFile == null) {
                    showImageErrorDialog("Employee Payslip (Not before 06 months old)");
                } else if (this.reasonOrderFile == null) {
                    if (i == 2) {
                        showImageErrorDialog("Transfer Order");
                    } else {
                        showImageErrorDialog("Promotion Order");
                    }
                } else if (this.joiningReportFile == null) {
                    showImageErrorDialog("Joining Report");
                } else {
                    if (this.pictureAttendanceRegisterFile != null) {
                        return true;
                    }
                    showImageErrorDialog("Picture of Attendance Register (Current Visit)");
                }
                return false;
            case 3:
            default:
                return false;
            case 5:
                if (!zImagesRequired) {
                    return true;
                }
                if (this.employeePictureFile == null) {
                    showImageErrorDialog("Employee Picture");
                } else if (this.cnicPictureFile == null) {
                    showImageErrorDialog("Employee CNIC");
                } else {
                    if (this.employeePayslipFile != null) {
                        return true;
                    }
                    showImageErrorDialog("Employee Payslip (Not before 06 months old)");
                }
                return false;
            case 6:
                if (!zImagesRequired) {
                    return true;
                }
                if (this.employeePictureFile == null) {
                    showImageErrorDialog("Employee Picture");
                } else if (this.cnicPictureFile == null) {
                    showImageErrorDialog("Employee CNIC");
                } else if (this.employeePayslipFile == null) {
                    showImageErrorDialog("Employee Payslip (Not before 06 months old)");
                } else if (this.reasonOrderFile == null) {
                    showImageErrorDialog("Authority Order");
                } else if (this.joiningReportFile == null) {
                    showImageErrorDialog("Joining Report");
                } else {
                    if (this.pictureAttendanceRegisterFile != null) {
                        return true;
                    }
                    showImageErrorDialog("Picture of Attendance Register (Current Visit)");
                }
                return false;
            case 7:
            case 8:
                boolean z2 = this.recordFound;
                boolean z3 = (z2 && this.reOpenId == 0) || !((!(z = this.localSyncedRecordFound) || this.reOpenId != 0) && z2 && z);
                if (this.localSyncedRecordFound || z2 || !z3 || this.reasonOrderFile != null || this.informationLetterFile != null) {
                    return true;
                }
                if (i == 7) {
                    showImageErrorDialog("Obituary Order or Information Letter");
                } else {
                    showImageErrorDialog("Retirement Order or Information Letter");
                }
                return false;
            case 9:
                if (this.employeePictureFile != null) {
                    return true;
                }
                showImageErrorDialog("Employee Picture");
                return false;
        }
    }

    private void showImageErrorDialog(String imageName) {
        DialogCustom.showError(getContext(), "Please upload " + imageName);
    }

    private EmployeeDbRecord buildEmployeeDbObject() {
        if (this.employeeUpdateRecord == null) {
            this.employeeUpdateRecord = new EmployeeUpdateRecord();
        }
        int iCurrentTimeMillis = (int) System.currentTimeMillis();
        if (!this.editMode) {
            this.employeeUpdateRecord.setRecordId(iCurrentTimeMillis);
        } else {
            EmployeeUpdateRecord employeeUpdateRecord = this.employeeUpdateRecord;
            employeeUpdateRecord.setRecordId(employeeUpdateRecord.getRecordId());
        }
        this.employeeUpdateRecord.setUserID(Integer.valueOf(Integer.parseInt(this.userBean.userId)));
        this.employeeUpdateRecord.setSchoolIDVisit(this.selectedSchool.getSchoolId());
        this.employeeUpdateRecord.setSchoolSEMISCodeVisit(this.selectedSchool.getSchoolSemisCode());
        this.employeeUpdateRecord.setEmployeeID(null);
        this.employeeUpdateRecord.setEmployeeCode(this.et_person_id.getText().toString());
        this.employeeUpdateRecord.setEmployeeName(this.et_name.getText().toString().trim());
        this.employeeUpdateRecord.setCnic(this.et_cnic.getText().toString().trim());
        this.employeeUpdateRecord.setGender(Integer.valueOf(this.genderId));
        this.employeeUpdateRecord.setContactNo(this.et_contact_no.getText().toString().trim());
        this.employeeUpdateRecord.setDateOfBirth(this.date_of_birth.getText().toString().trim());
        this.employeeUpdateRecord.setJoiningDate(this.joining_date.getText().toString().trim());
        this.employeeUpdateRecord.setDesignation(this.et_designation.getText().toString().trim());
        this.employeeUpdateRecord.setReasonForUpdate(Integer.valueOf(this.reasonId));
        int i = this.reasonId;
        if (i == 7 || i == 8 || i == 9) {
            this.employeeUpdateRecord.setEmployeeAttendance(null);
        } else {
            this.employeeUpdateRecord.setEmployeeAttendance(Integer.valueOf(this.attendanceId));
        }
        this.employeeUpdateRecord.setReferredLetterNumber(this.et_referred_letter_no.getText().toString().trim());
        this.employeeUpdateRecord.setReferredLetterDated(this.referred_letter_date.getText().toString().trim());
        this.employeeUpdateRecord.setOrderEffectingDate(this.order_effecting_date.getText().toString().trim());
        this.employeeUpdateRecord.setComments(this.et_comments.getText().toString().trim());
        if (this.editMode) {
            EmployeeUpdateRecord employeeUpdateRecord2 = this.employeeUpdateRecord;
            employeeUpdateRecord2.setDateTime(employeeUpdateRecord2.getDateTime());
        } else {
            this.employeeUpdateRecord.setDateTime(String.valueOf(System.currentTimeMillis()));
        }
        this.employeeUpdateRecord.setStatus(0);
        this.employeeUpdateRecord.setReopenTicket(false);
        this.employeeUpdateRecord.setParentTicketID(null);
        addImages(this.employeeUpdateRecord);
        removeImagesForRespectiveReason();
        EmployeeDbRecord employeeDbRecord = new EmployeeDbRecord();
        employeeDbRecord.setRecordId(this.employeeUpdateRecord.getRecordId());
        employeeDbRecord.setAddEmployeeRecord(this.employeeUpdateRecord);
        employeeDbRecord.setName(this.employeeUpdateRecord.getEmployeeName());
        employeeDbRecord.setCnic(this.employeeUpdateRecord.getCnic());
        employeeDbRecord.setEmployeeId(String.valueOf(this.employeeUpdateRecord.getEmployeeCode()));
        employeeDbRecord.setReason(this.reasonsMap.get(this.employeeUpdateRecord.getReasonForUpdate()));
        employeeDbRecord.setEmployeePicturePath(this.employeeUpdateRecord.getEmployeePicture());
        employeeDbRecord.setCnicPicturePath(this.employeeUpdateRecord.getcNICPicture());
        employeeDbRecord.setGovtLetterPicturePath(this.employeeUpdateRecord.getGovtLetterPicture());
        employeeDbRecord.setOtherPicturePath(this.employeeUpdateRecord.getOtherPicture());
        employeeDbRecord.setPostingOrderPicturePath(this.employeeUpdateRecord.getPostingPicture());
        int i2 = this.reasonId;
        if (i2 == 1) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getAppointmentOrderPicture());
        } else if (i2 == 2) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getTransferOrderPicture());
        } else if (i2 == 4) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getPromotionOrderPicture());
        } else if (i2 == 5) {
            employeeDbRecord.setReasonOrderPicturePath(null);
        } else if (i2 == 6) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getAuthorityOrderPicture());
        } else if (i2 == 7) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getObituaryOrderPicture());
        } else if (i2 == 8) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getRetirementOrderPicture());
        }
        employeeDbRecord.setEmployeePayslipPicturePath(this.employeeUpdateRecord.getEmployeePayslipPicture());
        employeeDbRecord.setOfferOrderPicturePath(this.employeeUpdateRecord.getOfferOrderPicture());
        employeeDbRecord.setJoiningReportPicturePath(this.employeeUpdateRecord.getJoiningReportPicture());
        employeeDbRecord.setAttendanceRegisterPicturePath(this.employeeUpdateRecord.getAttendanceRegisterPicture());
        int i3 = this.reasonId;
        if (i3 == 7) {
            employeeDbRecord.setInformationLetterPicturePath(this.employeeUpdateRecord.getExpiredInformationLetterPicture());
        } else if (i3 == 8) {
            employeeDbRecord.setInformationLetterPicturePath(this.employeeUpdateRecord.getRetirementInformationLetterPicture());
        }
        if (this.editMode) {
            employeeDbRecord.setDateTime(this.employeeUpdateRecord.getDateTime());
        } else {
            employeeDbRecord.setDateTime(String.valueOf(System.currentTimeMillis()));
        }
        employeeDbRecord.setSyncStatus(0);
        return employeeDbRecord;
    }

    private EmployeeDbRecord buildEmployeeDbObjectWhenNewRecordFound() {
        EmployeeDbRecord employeeDbRecord = CommonActions.getDbHandler(getContext()).getEmployeeDbRecord(this.updateRecordId.intValue());
        if (this.employeeUpdateRecord == null) {
            this.employeeUpdateRecord = new EmployeeUpdateRecord();
        }
        this.employeeUpdateRecord.setRecordId(employeeDbRecord.getRecordId());
        this.employeeUpdateRecord.setUserID(employeeDbRecord.getAddEmployeeRecord().getUserID());
        this.employeeUpdateRecord.setSchoolIDVisit(this.selectedSchool.getSchoolId());
        this.employeeUpdateRecord.setSchoolSEMISCodeVisit(this.selectedSchool.getSchoolSemisCode());
        this.employeeUpdateRecord.setEmployeeID(null);
        this.employeeUpdateRecord.setEmployeeCode(this.et_person_id.getText().toString());
        this.employeeUpdateRecord.setEmployeeName(this.et_name.getText().toString().trim());
        this.employeeUpdateRecord.setCnic(this.et_cnic.getText().toString().trim());
        this.employeeUpdateRecord.setGender(Integer.valueOf(this.genderId));
        this.employeeUpdateRecord.setContactNo(this.et_contact_no.getText().toString().trim());
        this.employeeUpdateRecord.setDateOfBirth(this.date_of_birth.getText().toString().trim());
        this.employeeUpdateRecord.setJoiningDate(this.joining_date.getText().toString().trim());
        this.employeeUpdateRecord.setDesignation(this.et_designation.getText().toString().trim());
        this.employeeUpdateRecord.setReasonForUpdate(Integer.valueOf(this.reasonId));
        int i = this.reasonId;
        if (i == 7 || i == 8 || i == 9) {
            this.employeeUpdateRecord.setEmployeeAttendance(null);
        } else {
            this.employeeUpdateRecord.setEmployeeAttendance(Integer.valueOf(this.attendanceId));
        }
        this.employeeUpdateRecord.setReferredLetterNumber(this.et_referred_letter_no.getText().toString().trim());
        this.employeeUpdateRecord.setReferredLetterDated(this.referred_letter_date.getText().toString().trim());
        this.employeeUpdateRecord.setOrderEffectingDate(this.order_effecting_date.getText().toString().trim());
        this.employeeUpdateRecord.setComments(this.et_comments.getText().toString().trim());
        this.employeeUpdateRecord.setDateTime(employeeDbRecord.getAddEmployeeRecord().getDateTime());
        this.employeeUpdateRecord.setStatus(0);
        this.employeeUpdateRecord.setReopenTicket(false);
        this.employeeUpdateRecord.setParentTicketID(null);
        addImages(this.employeeUpdateRecord);
        removeImagesForRespectiveReason();
        employeeDbRecord.setRecordId(this.employeeUpdateRecord.getRecordId());
        employeeDbRecord.setAddEmployeeRecord(this.employeeUpdateRecord);
        employeeDbRecord.setName(this.employeeUpdateRecord.getEmployeeName());
        employeeDbRecord.setCnic(this.employeeUpdateRecord.getCnic());
        employeeDbRecord.setEmployeeId(String.valueOf(this.employeeUpdateRecord.getEmployeeCode()));
        employeeDbRecord.setReason(this.reasonsMap.get(this.employeeUpdateRecord.getReasonForUpdate()));
        employeeDbRecord.setEmployeePicturePath(this.employeeUpdateRecord.getEmployeePicture());
        employeeDbRecord.setCnicPicturePath(this.employeeUpdateRecord.getcNICPicture());
        employeeDbRecord.setGovtLetterPicturePath(this.employeeUpdateRecord.getGovtLetterPicture());
        employeeDbRecord.setOtherPicturePath(this.employeeUpdateRecord.getOtherPicture());
        employeeDbRecord.setPostingOrderPicturePath(this.employeeUpdateRecord.getPostingPicture());
        int i2 = this.reasonId;
        if (i2 == 1) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getAppointmentOrderPicture());
        } else if (i2 == 2) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getTransferOrderPicture());
        } else if (i2 == 4) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getPromotionOrderPicture());
        } else if (i2 == 5) {
            employeeDbRecord.setReasonOrderPicturePath(null);
        } else if (i2 == 6) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getAuthorityOrderPicture());
        } else if (i2 == 7) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getObituaryOrderPicture());
        } else if (i2 == 8) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getRetirementOrderPicture());
        }
        employeeDbRecord.setEmployeePayslipPicturePath(this.employeeUpdateRecord.getEmployeePayslipPicture());
        employeeDbRecord.setOfferOrderPicturePath(this.employeeUpdateRecord.getOfferOrderPicture());
        employeeDbRecord.setJoiningReportPicturePath(this.employeeUpdateRecord.getJoiningReportPicture());
        employeeDbRecord.setAttendanceRegisterPicturePath(this.employeeUpdateRecord.getAttendanceRegisterPicture());
        int i3 = this.reasonId;
        if (i3 == 7) {
            employeeDbRecord.setInformationLetterPicturePath(this.employeeUpdateRecord.getExpiredInformationLetterPicture());
        } else if (i3 == 8) {
            employeeDbRecord.setInformationLetterPicturePath(this.employeeUpdateRecord.getRetirementInformationLetterPicture());
        }
        employeeDbRecord.setDateTime(this.employeeUpdateRecord.getDateTime());
        employeeDbRecord.setSyncStatus(0);
        return employeeDbRecord;
    }

    private EmployeeDbRecord buildEmployeeDbObjectWhenRecordFound() {
        EmployeeDbRecord employeeDbRecord;
        if (this.employeeUpdateRecord == null) {
            this.employeeUpdateRecord = new EmployeeUpdateRecord();
        }
        int iCurrentTimeMillis = (int) System.currentTimeMillis();
        if (this.updateRecordId != null && (employeeDbRecord = CommonActions.getDbHandler(getContext()).getEmployeeDbRecord(this.updateRecordId.intValue())) != null) {
            this.employeeUpdateRecord.setRecordId(employeeDbRecord.getRecordId());
            this.editMode = true;
            this.employeeUpdateRecord.setDateTime(employeeDbRecord.getDateTime());
        } else {
            this.employeeUpdateRecord.setRecordId(iCurrentTimeMillis);
            this.employeeUpdateRecord.setDateTime(String.valueOf(System.currentTimeMillis()));
        }
        this.employeeUpdateRecord.setUserID(Integer.valueOf(Integer.parseInt(this.userBean.userId)));
        this.employeeUpdateRecord.setSchoolSEMISCodeVisit(null);
        this.employeeUpdateRecord.setEmployeeID(null);
        this.employeeUpdateRecord.setEmployeeCode(null);
        this.employeeUpdateRecord.setEmployeeName(null);
        this.employeeUpdateRecord.setCnic(this.et_cnic.getText().toString().trim());
        this.employeeUpdateRecord.setGender(null);
        this.employeeUpdateRecord.setContactNo(null);
        this.employeeUpdateRecord.setDateOfBirth(null);
        this.employeeUpdateRecord.setJoiningDate(null);
        this.employeeUpdateRecord.setDesignation(null);
        EmployeeRecordUpdateDatum employeeRecordUpdateDatum = this.employeeRecordUpdateDatum;
        if (employeeRecordUpdateDatum != null) {
            this.employeeUpdateRecord.setEmployeeName(employeeRecordUpdateDatum.getEmployeeName());
            this.employeeUpdateRecord.setStatus(this.employeeRecordUpdateDatum.getStatus());
            if (this.employeeRecordUpdateDatum.getStatus().intValue() == 1) {
                this.employeeUpdateRecord.setReopenTicket(Boolean.valueOf(this.reOpenId == 0));
            } else {
                this.employeeUpdateRecord.setReopenTicket(false);
            }
            if (this.employeeRecordUpdateDatum.getParentTicketId() != null) {
                this.employeeUpdateRecord.setParentTicketID(this.employeeRecordUpdateDatum.getParentTicketId());
            }
            if (this.employeeRecordUpdateDatum.getSchoolIdVisit() != null) {
                this.employeeUpdateRecord.setSchoolIDVisit(this.employeeRecordUpdateDatum.getSchoolIdVisit());
            }
        }
        this.employeeUpdateRecord.setReasonForUpdate(Integer.valueOf(this.reasonId));
        int i = this.reasonId;
        if (i == 7 || i == 8 || i == 9) {
            this.employeeUpdateRecord.setEmployeeAttendance(null);
        } else {
            this.employeeUpdateRecord.setEmployeeAttendance(Integer.valueOf(this.attendanceId));
        }
        this.employeeUpdateRecord.setReferredLetterNumber(this.et_referred_letter_no.getText().toString().trim());
        this.employeeUpdateRecord.setReferredLetterDated(this.referred_letter_date.getText().toString().trim());
        this.employeeUpdateRecord.setOrderEffectingDate(this.order_effecting_date.getText().toString().trim());
        this.employeeUpdateRecord.setComments(this.et_comments.getText().toString().trim());
        addImages(this.employeeUpdateRecord);
        if (this.reOpenId == 1) {
            this.employeeUpdateRecord.setEmployeePicture(null);
            this.employeeUpdateRecord.setcNICPicture(null);
            this.employeeUpdateRecord.setGovtLetterPicture(null);
            this.employeeUpdateRecord.setOtherPicture(null);
            this.employeeUpdateRecord.setPostingPicture(null);
            this.employeeUpdateRecord.setAppointmentOrderPicture(null);
            this.employeeUpdateRecord.setEmployeePayslipPicture(null);
            this.employeeUpdateRecord.setOfferOrderPicture(null);
            this.employeeUpdateRecord.setJoiningReportPicture(null);
            this.employeeUpdateRecord.setAttendanceRegisterPicture(null);
            this.employeeUpdateRecord.setTransferOrderPicture(null);
            this.employeeUpdateRecord.setPromotionOrderPicture(null);
            this.employeeUpdateRecord.setObituaryOrderPicture(null);
            this.employeeUpdateRecord.setExpiredInformationLetterPicture(null);
            this.employeeUpdateRecord.setAuthorityOrderPicture(null);
            this.employeeUpdateRecord.setRetirementOrderPicture(null);
            this.employeeUpdateRecord.setRetirementInformationLetterPicture(null);
        } else {
            removeImagesForRespectiveReason();
        }
        EmployeeDbRecord employeeDbRecord2 = new EmployeeDbRecord();
        employeeDbRecord2.setRecordId(this.employeeUpdateRecord.getRecordId());
        employeeDbRecord2.setAddEmployeeRecord(this.employeeUpdateRecord);
        employeeDbRecord2.setName(this.employeeUpdateRecord.getEmployeeName());
        employeeDbRecord2.setCnic(this.employeeUpdateRecord.getCnic());
        employeeDbRecord2.setEmployeeId(String.valueOf(this.employeeUpdateRecord.getEmployeeCode()));
        employeeDbRecord2.setReason(this.reasonsMap.get(this.employeeUpdateRecord.getReasonForUpdate()));
        employeeDbRecord2.setEmployeePicturePath(this.employeeUpdateRecord.getEmployeePicture());
        employeeDbRecord2.setCnicPicturePath(this.employeeUpdateRecord.getcNICPicture());
        employeeDbRecord2.setGovtLetterPicturePath(this.employeeUpdateRecord.getGovtLetterPicture());
        employeeDbRecord2.setOtherPicturePath(this.employeeUpdateRecord.getOtherPicture());
        employeeDbRecord2.setPostingOrderPicturePath(this.employeeUpdateRecord.getPostingPicture());
        int i2 = this.reasonId;
        if (i2 == 1) {
            employeeDbRecord2.setReasonOrderPicturePath(this.employeeUpdateRecord.getAppointmentOrderPicture());
        } else if (i2 == 2) {
            employeeDbRecord2.setReasonOrderPicturePath(this.employeeUpdateRecord.getTransferOrderPicture());
        } else if (i2 == 4) {
            employeeDbRecord2.setReasonOrderPicturePath(this.employeeUpdateRecord.getPromotionOrderPicture());
        } else if (i2 == 5) {
            employeeDbRecord2.setReasonOrderPicturePath(null);
        } else if (i2 == 6) {
            employeeDbRecord2.setReasonOrderPicturePath(this.employeeUpdateRecord.getAuthorityOrderPicture());
        } else if (i2 == 7) {
            employeeDbRecord2.setReasonOrderPicturePath(this.employeeUpdateRecord.getObituaryOrderPicture());
        } else if (i2 == 8) {
            employeeDbRecord2.setReasonOrderPicturePath(this.employeeUpdateRecord.getRetirementOrderPicture());
        }
        employeeDbRecord2.setEmployeePayslipPicturePath(this.employeeUpdateRecord.getEmployeePayslipPicture());
        employeeDbRecord2.setOfferOrderPicturePath(this.employeeUpdateRecord.getOfferOrderPicture());
        employeeDbRecord2.setJoiningReportPicturePath(this.employeeUpdateRecord.getJoiningReportPicture());
        employeeDbRecord2.setAttendanceRegisterPicturePath(this.employeeUpdateRecord.getAttendanceRegisterPicture());
        int i3 = this.reasonId;
        if (i3 == 7) {
            employeeDbRecord2.setInformationLetterPicturePath(this.employeeUpdateRecord.getExpiredInformationLetterPicture());
        } else if (i3 == 8) {
            employeeDbRecord2.setInformationLetterPicturePath(this.employeeUpdateRecord.getRetirementInformationLetterPicture());
        }
        if (this.editMode) {
            employeeDbRecord2.setDateTime(this.employeeUpdateRecord.getDateTime());
        } else {
            employeeDbRecord2.setDateTime(String.valueOf(System.currentTimeMillis()));
        }
        employeeDbRecord2.setSyncStatus(0);
        return employeeDbRecord2;
    }

    private EmployeeDbRecord buildEmployeeDbObjectWhenLocalSyncRecordFound() {
        EmployeeDbRecord employeeDbRecord = CommonActions.getDbHandler(getContext()).getEmployeeDbRecord(this.updateRecordId.intValue());
        if (this.employeeUpdateRecord == null) {
            this.employeeUpdateRecord = new EmployeeUpdateRecord();
        }
        this.employeeUpdateRecord.setRecordId(employeeDbRecord.getRecordId());
        this.employeeUpdateRecord.setUserID(employeeDbRecord.getAddEmployeeRecord().getUserID());
        this.employeeUpdateRecord.setSchoolIDVisit(employeeDbRecord.getAddEmployeeRecord().getSchoolIDVisit());
        this.employeeUpdateRecord.setSchoolSEMISCodeVisit(employeeDbRecord.getAddEmployeeRecord().getSchoolSEMISCodeVisit());
        this.employeeUpdateRecord.setEmployeeID(employeeDbRecord.getAddEmployeeRecord().getEmployeeID());
        this.employeeUpdateRecord.setEmployeeCode(employeeDbRecord.getAddEmployeeRecord().getEmployeeCode());
        this.employeeUpdateRecord.setEmployeeName(employeeDbRecord.getAddEmployeeRecord().getEmployeeName());
        this.employeeUpdateRecord.setCnic(this.et_cnic.getText().toString().trim());
        this.employeeUpdateRecord.setGender(employeeDbRecord.getAddEmployeeRecord().getGender());
        this.employeeUpdateRecord.setContactNo(employeeDbRecord.getAddEmployeeRecord().getContactNo());
        this.employeeUpdateRecord.setDateOfBirth(employeeDbRecord.getAddEmployeeRecord().getDateOfBirth());
        this.employeeUpdateRecord.setJoiningDate(employeeDbRecord.getAddEmployeeRecord().getJoiningDate());
        this.employeeUpdateRecord.setDesignation(employeeDbRecord.getAddEmployeeRecord().getDesignation());
        this.employeeUpdateRecord.setReasonForUpdate(Integer.valueOf(this.reasonId));
        int i = this.reasonId;
        if (i == 7 || i == 8 || i == 9) {
            this.employeeUpdateRecord.setEmployeeAttendance(null);
        } else {
            this.employeeUpdateRecord.setEmployeeAttendance(Integer.valueOf(this.attendanceId));
        }
        this.employeeUpdateRecord.setReferredLetterNumber(this.et_referred_letter_no.getText().toString().trim());
        this.employeeUpdateRecord.setReferredLetterDated(this.referred_letter_date.getText().toString().trim());
        this.employeeUpdateRecord.setOrderEffectingDate(this.order_effecting_date.getText().toString().trim());
        this.employeeUpdateRecord.setComments(this.et_comments.getText().toString().trim());
        this.employeeUpdateRecord.setDateTime(employeeDbRecord.getAddEmployeeRecord().getDateTime());
        this.employeeUpdateRecord.setStatus(0);
        this.employeeUpdateRecord.setReopenTicket(false);
        this.employeeUpdateRecord.setParentTicketID(null);
        addImages(this.employeeUpdateRecord);
        removeImagesForRespectiveReason();
        employeeDbRecord.setRecordId(this.employeeUpdateRecord.getRecordId());
        employeeDbRecord.setAddEmployeeRecord(this.employeeUpdateRecord);
        employeeDbRecord.setName(this.employeeUpdateRecord.getEmployeeName());
        employeeDbRecord.setCnic(this.employeeUpdateRecord.getCnic());
        employeeDbRecord.setEmployeeId(String.valueOf(this.employeeUpdateRecord.getEmployeeCode()));
        employeeDbRecord.setReason(this.reasonsMap.get(this.employeeUpdateRecord.getReasonForUpdate()));
        employeeDbRecord.setEmployeePicturePath(this.employeeUpdateRecord.getEmployeePicture());
        employeeDbRecord.setCnicPicturePath(this.employeeUpdateRecord.getcNICPicture());
        employeeDbRecord.setGovtLetterPicturePath(this.employeeUpdateRecord.getGovtLetterPicture());
        employeeDbRecord.setOtherPicturePath(this.employeeUpdateRecord.getOtherPicture());
        employeeDbRecord.setPostingOrderPicturePath(this.employeeUpdateRecord.getPostingPicture());
        int i2 = this.reasonId;
        if (i2 == 1) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getAppointmentOrderPicture());
        } else if (i2 == 2) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getTransferOrderPicture());
        } else if (i2 == 4) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getPromotionOrderPicture());
        } else if (i2 == 5) {
            employeeDbRecord.setReasonOrderPicturePath(null);
        } else if (i2 == 6) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getAuthorityOrderPicture());
        } else if (i2 == 7) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getObituaryOrderPicture());
        } else if (i2 == 8) {
            employeeDbRecord.setReasonOrderPicturePath(this.employeeUpdateRecord.getRetirementOrderPicture());
        }
        employeeDbRecord.setEmployeePayslipPicturePath(this.employeeUpdateRecord.getEmployeePayslipPicture());
        employeeDbRecord.setOfferOrderPicturePath(this.employeeUpdateRecord.getOfferOrderPicture());
        employeeDbRecord.setJoiningReportPicturePath(this.employeeUpdateRecord.getJoiningReportPicture());
        employeeDbRecord.setAttendanceRegisterPicturePath(this.employeeUpdateRecord.getAttendanceRegisterPicture());
        int i3 = this.reasonId;
        if (i3 == 7) {
            employeeDbRecord.setInformationLetterPicturePath(this.employeeUpdateRecord.getExpiredInformationLetterPicture());
        } else if (i3 == 8) {
            employeeDbRecord.setInformationLetterPicturePath(this.employeeUpdateRecord.getRetirementInformationLetterPicture());
        }
        employeeDbRecord.setDateTime(this.employeeUpdateRecord.getDateTime());
        employeeDbRecord.setSyncStatus(0);
        employeeDbRecord.setAlreadySynced(employeeDbRecord.getAlreadySynced());
        return employeeDbRecord;
    }

    private void removeImagesForRespectiveReason() {
        int i = this.reasonId;
        if (i == 1) {
            this.employeeUpdateRecord.setTransferOrderPicture(null);
            this.employeeUpdateRecord.setPromotionOrderPicture(null);
            this.employeeUpdateRecord.setAuthorityOrderPicture(null);
            this.employeeUpdateRecord.setObituaryOrderPicture(null);
            this.employeeUpdateRecord.setRetirementOrderPicture(null);
            this.employeeUpdateRecord.setExpiredInformationLetterPicture(null);
            this.employeeUpdateRecord.setRetirementInformationLetterPicture(null);
            return;
        }
        if (i == 2) {
            this.employeeUpdateRecord.setOfferOrderPicture(null);
            this.employeeUpdateRecord.setAppointmentOrderPicture(null);
            this.employeeUpdateRecord.setPromotionOrderPicture(null);
            this.employeeUpdateRecord.setAuthorityOrderPicture(null);
            this.employeeUpdateRecord.setObituaryOrderPicture(null);
            this.employeeUpdateRecord.setRetirementOrderPicture(null);
            this.employeeUpdateRecord.setExpiredInformationLetterPicture(null);
            this.employeeUpdateRecord.setRetirementInformationLetterPicture(null);
            return;
        }
        if (i == 4) {
            this.employeeUpdateRecord.setOfferOrderPicture(null);
            this.employeeUpdateRecord.setAppointmentOrderPicture(null);
            this.employeeUpdateRecord.setTransferOrderPicture(null);
            this.employeeUpdateRecord.setAuthorityOrderPicture(null);
            this.employeeUpdateRecord.setObituaryOrderPicture(null);
            this.employeeUpdateRecord.setRetirementOrderPicture(null);
            this.employeeUpdateRecord.setExpiredInformationLetterPicture(null);
            this.employeeUpdateRecord.setRetirementInformationLetterPicture(null);
            return;
        }
        if (i == 5) {
            this.employeeUpdateRecord.setOfferOrderPicture(null);
            this.employeeUpdateRecord.setAppointmentOrderPicture(null);
            this.employeeUpdateRecord.setTransferOrderPicture(null);
            this.employeeUpdateRecord.setPromotionOrderPicture(null);
            this.employeeUpdateRecord.setAuthorityOrderPicture(null);
            this.employeeUpdateRecord.setObituaryOrderPicture(null);
            this.employeeUpdateRecord.setRetirementOrderPicture(null);
            this.employeeUpdateRecord.setJoiningReportPicture(null);
            this.employeeUpdateRecord.setAttendanceRegisterPicture(null);
            this.employeeUpdateRecord.setExpiredInformationLetterPicture(null);
            this.employeeUpdateRecord.setRetirementInformationLetterPicture(null);
            return;
        }
        if (i == 6) {
            this.employeeUpdateRecord.setOfferOrderPicture(null);
            this.employeeUpdateRecord.setAppointmentOrderPicture(null);
            this.employeeUpdateRecord.setTransferOrderPicture(null);
            this.employeeUpdateRecord.setPromotionOrderPicture(null);
            this.employeeUpdateRecord.setObituaryOrderPicture(null);
            this.employeeUpdateRecord.setRetirementOrderPicture(null);
            this.employeeUpdateRecord.setExpiredInformationLetterPicture(null);
            this.employeeUpdateRecord.setRetirementInformationLetterPicture(null);
            return;
        }
        if (i == 7) {
            this.employeeUpdateRecord.setOfferOrderPicture(null);
            this.employeeUpdateRecord.setAppointmentOrderPicture(null);
            this.employeeUpdateRecord.setTransferOrderPicture(null);
            this.employeeUpdateRecord.setPromotionOrderPicture(null);
            this.employeeUpdateRecord.setAuthorityOrderPicture(null);
            this.employeeUpdateRecord.setRetirementOrderPicture(null);
            this.employeeUpdateRecord.setJoiningReportPicture(null);
            this.employeeUpdateRecord.setAttendanceRegisterPicture(null);
            this.employeeUpdateRecord.setAttendanceRegisterPicture(null);
            return;
        }
        if (i == 8) {
            this.employeeUpdateRecord.setOfferOrderPicture(null);
            this.employeeUpdateRecord.setAppointmentOrderPicture(null);
            this.employeeUpdateRecord.setTransferOrderPicture(null);
            this.employeeUpdateRecord.setPromotionOrderPicture(null);
            this.employeeUpdateRecord.setAuthorityOrderPicture(null);
            this.employeeUpdateRecord.setObituaryOrderPicture(null);
            this.employeeUpdateRecord.setJoiningReportPicture(null);
            this.employeeUpdateRecord.setAttendanceRegisterPicture(null);
            this.employeeUpdateRecord.setAttendanceRegisterPicture(null);
            return;
        }
        if (i == 9) {
            this.employeeUpdateRecord.setcNICPicture(null);
            this.employeeUpdateRecord.setGovtLetterPicture(null);
            this.employeeUpdateRecord.setOtherPicture(null);
            this.employeeUpdateRecord.setPostingPicture(null);
            this.employeeUpdateRecord.setAppointmentOrderPicture(null);
            this.employeeUpdateRecord.setEmployeePayslipPicture(null);
            this.employeeUpdateRecord.setOfferOrderPicture(null);
            this.employeeUpdateRecord.setJoiningReportPicture(null);
            this.employeeUpdateRecord.setAttendanceRegisterPicture(null);
            this.employeeUpdateRecord.setTransferOrderPicture(null);
            this.employeeUpdateRecord.setPromotionOrderPicture(null);
            this.employeeUpdateRecord.setObituaryOrderPicture(null);
            this.employeeUpdateRecord.setExpiredInformationLetterPicture(null);
            this.employeeUpdateRecord.setAuthorityOrderPicture(null);
            this.employeeUpdateRecord.setRetirementOrderPicture(null);
            this.employeeUpdateRecord.setRetirementInformationLetterPicture(null);
        }
    }

    private void saveDataWhenFound() {
        if (validateLetterFieldsFields() && validateImages()) {
            this.addEmployeeOperations.saveEmployeeRecord(buildEmployeeDbObjectWhenRecordFound(), this.editMode);
            this.fm.popBackStack();
        }
    }

    private void saveDataWhenNewRecordFound() {
        try {
            if (this.updateRecordId != null && validateFields() && validateImages()) {
                loadingStarted();
                this.addEmployeeOperations.saveEmployeeRecord(buildEmployeeDbObjectWhenNewRecordFound(), true);
                loadingFinished();
                this.fm.popBackStack();
            }
        } catch (Exception e) {
            e.printStackTrace();
            util.logException(e);
        }
    }

    private void saveDataWhenLocalSyncedRecordFound() {
        try {
            if (this.updateRecordId != null && validateLetterFieldsFields() && validateImages()) {
                loadingStarted();
                this.addEmployeeOperations.saveEmployeeRecord(buildEmployeeDbObjectWhenLocalSyncRecordFound(), true);
                loadingFinished();
                this.fm.popBackStack();
            }
        } catch (Exception e) {
            e.printStackTrace();
            util.logException(e);
        }
    }

    private void addImages(EmployeeUpdateRecord employeeUpdateRecord) {
        File file = this.employeePictureFile;
        if (file != null && file.exists()) {
            employeeUpdateRecord.setEmployeePicture(this.employeePictureFile.getPath());
        }
        File file2 = this.cnicPictureFile;
        if (file2 != null && file2.exists()) {
            employeeUpdateRecord.setcNICPicture(this.cnicPictureFile.getPath());
        }
        File file3 = this.employeePayslipFile;
        if (file3 != null && file3.exists()) {
            employeeUpdateRecord.setEmployeePayslipPicture(this.employeePayslipFile.getPath());
        }
        File file4 = this.offerOrderFile;
        if (file4 != null && file4.exists()) {
            employeeUpdateRecord.setOfferOrderPicture(this.offerOrderFile.getPath());
        }
        File file5 = this.reasonOrderFile;
        if (file5 != null && file5.exists()) {
            int i = this.reasonId;
            if (i == 1) {
                employeeUpdateRecord.setAppointmentOrderPicture(this.reasonOrderFile.getPath());
            } else if (i == 2) {
                employeeUpdateRecord.setTransferOrderPicture(this.reasonOrderFile.getPath());
            } else if (i == 4) {
                employeeUpdateRecord.setPromotionOrderPicture(this.reasonOrderFile.getPath());
            } else if (i != 5) {
                if (i == 6) {
                    employeeUpdateRecord.setAuthorityOrderPicture(this.reasonOrderFile.getPath());
                } else if (i == 7) {
                    employeeUpdateRecord.setObituaryOrderPicture(this.reasonOrderFile.getPath());
                } else if (i == 8) {
                    employeeUpdateRecord.setRetirementOrderPicture(this.reasonOrderFile.getPath());
                }
            }
        }
        File file6 = this.joiningReportFile;
        if (file6 != null && file6.exists()) {
            employeeUpdateRecord.setJoiningReportPicture(this.joiningReportFile.getPath());
        }
        File file7 = this.pictureAttendanceRegisterFile;
        if (file7 != null && file7.exists()) {
            employeeUpdateRecord.setAttendanceRegisterPicture(this.pictureAttendanceRegisterFile.getPath());
        }
        File file8 = this.informationLetterFile;
        if (file8 == null || !file8.exists()) {
            return;
        }
        int i2 = this.reasonId;
        if (i2 == 7) {
            employeeUpdateRecord.setExpiredInformationLetterPicture(this.informationLetterFile.getPath());
        } else if (i2 == 8) {
            employeeUpdateRecord.setRetirementInformationLetterPicture(this.informationLetterFile.getPath());
        }
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment.CameraActivityCallBackMultiImages
    public void onCameraActivityResult(Bitmap bitmap, String bitmapPath, int requestCode) {
        this.location = CommonObjects.tracker.getLocation(getActivityContext());
        if (CommonObjects.isMock || bitmap == null || bitmapPath == null) {
            return;
        }
        if (requestCode == 12) {
            this.employee_picture.setVisibility(0);
            this.employee_picture.setImageBitmap(bitmap);
            this.employeePictureFile = new File(bitmapPath);
        }
        if (requestCode == 13) {
            this.cnic_picture.setVisibility(0);
            this.cnic_picture.setImageBitmap(bitmap);
            this.cnicPictureFile = new File(bitmapPath);
        }
        if (requestCode == 18) {
            this.employee_payslip_picture.setVisibility(0);
            this.employee_payslip_picture.setImageBitmap(bitmap);
            this.employeePayslipFile = new File(bitmapPath);
        }
        if (requestCode == 19) {
            this.offer_order_picture.setVisibility(0);
            this.offer_order_pdf.setVisibility(8);
            this.offer_order_picture.setImageBitmap(bitmap);
            this.offerOrderFile = new File(bitmapPath);
        }
        if (requestCode == 15) {
            this.appointment_order_picture.setVisibility(0);
            this.appointment_order_pdf.setVisibility(8);
            this.appointment_order_picture.setImageBitmap(bitmap);
            this.reasonOrderFile = new File(bitmapPath);
            letterFieldsRequired();
        }
        if (requestCode == 20) {
            this.joining_report_picture.setVisibility(0);
            this.joining_report_picture.setImageBitmap(bitmap);
            this.joiningReportFile = new File(bitmapPath);
        }
        if (requestCode == 21) {
            this.attendance_register_picture.setVisibility(0);
            this.attendance_register_picture.setImageBitmap(bitmap);
            this.pictureAttendanceRegisterFile = new File(bitmapPath);
        }
        if (requestCode == 22) {
            this.information_letter_picture.setVisibility(0);
            this.information_letter_picture.setImageBitmap(bitmap);
            this.informationLetterFile = new File(bitmapPath);
            letterFieldsRequired();
        }
    }

    private void showImages(EmployeeDbRecord employeeDbRecord) {
        this.images_layout.setVisibility(0);
        imageTitlesRequiredHandling();
        if (employeeDbRecord.getEmployeePicturePath() != null && !employeeDbRecord.getEmployeePicturePath().isEmpty()) {
            File file = new File(employeeDbRecord.getEmployeePicturePath());
            this.employeePictureFile = file;
            if (file.exists()) {
                this.employee_picture.setVisibility(0);
                this.employee_picture.setImageURI(Uri.fromFile(this.employeePictureFile));
            }
        }
        if (employeeDbRecord.getCnicPicturePath() != null && !employeeDbRecord.getCnicPicturePath().isEmpty()) {
            File file2 = new File(employeeDbRecord.getCnicPicturePath());
            this.cnicPictureFile = file2;
            if (file2.exists()) {
                this.cnic_picture.setVisibility(0);
                this.cnic_picture.setImageURI(Uri.fromFile(this.cnicPictureFile));
            }
        }
        if (employeeDbRecord.getEmployeePayslipPicturePath() != null && !employeeDbRecord.getEmployeePayslipPicturePath().isEmpty()) {
            File file3 = new File(employeeDbRecord.getEmployeePayslipPicturePath());
            this.employeePayslipFile = file3;
            if (file3.exists()) {
                this.employee_payslip_picture.setVisibility(0);
                this.employee_payslip_picture.setImageURI(Uri.fromFile(this.employeePayslipFile));
            }
        }
        if (employeeDbRecord.getOfferOrderPicturePath() != null && !employeeDbRecord.getOfferOrderPicturePath().isEmpty()) {
            File file4 = new File(employeeDbRecord.getOfferOrderPicturePath());
            this.offerOrderFile = file4;
            if (file4.exists()) {
                if (this.offerOrderFile.getPath().endsWith(".pdf")) {
                    this.offer_order_pdf.setVisibility(0);
                    this.offer_order_pdf.setImageResource(R.drawable.pdf);
                } else {
                    this.offer_order_picture.setVisibility(0);
                    this.offer_order_picture.setImageURI(Uri.fromFile(this.offerOrderFile));
                }
            }
        }
        if (employeeDbRecord.getReasonOrderPicturePath() != null && !employeeDbRecord.getReasonOrderPicturePath().isEmpty()) {
            File file5 = new File(employeeDbRecord.getReasonOrderPicturePath());
            this.reasonOrderFile = file5;
            if (file5.exists()) {
                if (this.reasonOrderFile.getPath().endsWith(".pdf")) {
                    this.appointment_order_pdf.setVisibility(0);
                    this.appointment_order_pdf.setImageResource(R.drawable.pdf);
                } else {
                    this.appointment_order_picture.setVisibility(0);
                    this.appointment_order_picture.setImageURI(Uri.fromFile(this.reasonOrderFile));
                }
                letterFieldsRequired();
            }
        }
        if (employeeDbRecord.getJoiningReportPicturePath() != null && !employeeDbRecord.getJoiningReportPicturePath().isEmpty()) {
            File file6 = new File(employeeDbRecord.getJoiningReportPicturePath());
            this.joiningReportFile = file6;
            if (file6.exists()) {
                this.joining_report_picture.setVisibility(0);
                this.joining_report_picture.setImageURI(Uri.fromFile(this.joiningReportFile));
            }
        }
        if (employeeDbRecord.getAttendanceRegisterPicturePath() != null && !employeeDbRecord.getAttendanceRegisterPicturePath().isEmpty()) {
            File file7 = new File(employeeDbRecord.getAttendanceRegisterPicturePath());
            this.pictureAttendanceRegisterFile = file7;
            if (file7.exists()) {
                this.attendance_register_picture.setVisibility(0);
                this.attendance_register_picture.setImageURI(Uri.fromFile(this.pictureAttendanceRegisterFile));
            }
        }
        if (employeeDbRecord.getInformationLetterPicturePath() == null || employeeDbRecord.getInformationLetterPicturePath().isEmpty()) {
            return;
        }
        File file8 = new File(employeeDbRecord.getInformationLetterPicturePath());
        this.informationLetterFile = file8;
        if (file8.exists()) {
            this.information_letter_picture.setVisibility(0);
            this.information_letter_picture.setImageURI(Uri.fromFile(this.informationLetterFile));
            letterFieldsRequired();
        }
    }

    private void editForm(EmployeeDbRecord employeeDbRecord) {
        EmployeeUpdateRecord addEmployeeRecord = employeeDbRecord.getAddEmployeeRecord();
        if (this.reasonId == 1 && addEmployeeRecord.getEmployeeCode() != null) {
            this.et_person_id.setText(employeeDbRecord.getAddEmployeeRecord().getEmployeeCode());
        }
        if (this.reasonId == 1 && addEmployeeRecord.getEmployeeName() != null) {
            this.et_name.setText(employeeDbRecord.getAddEmployeeRecord().getEmployeeName());
        }
        if (addEmployeeRecord.getCnic() != null) {
            this.et_cnic.setText(employeeDbRecord.getAddEmployeeRecord().getCnic());
        }
        if (this.reasonId == 1 && addEmployeeRecord.getContactNo() != null) {
            this.et_contact_no.setText(employeeDbRecord.getAddEmployeeRecord().getContactNo());
        }
        if (this.reasonId == 1 && addEmployeeRecord.getDateOfBirth() != null) {
            this.date_of_birth.setText(employeeDbRecord.getAddEmployeeRecord().getDateOfBirth());
        }
        if (addEmployeeRecord.getDesignation() != null) {
            this.et_designation.setText(employeeDbRecord.getAddEmployeeRecord().getDesignation());
        }
        if (this.reasonId == 1 && addEmployeeRecord.getJoiningDate() != null) {
            this.joining_date.setText(employeeDbRecord.getAddEmployeeRecord().getJoiningDate());
        }
        if (this.reasonId == 1 && addEmployeeRecord.getGender() != null) {
            ((RadioButton) this.rg_gender.getChildAt(employeeDbRecord.getAddEmployeeRecord().getGender().intValue() - 1)).setChecked(true);
        }
        if (addEmployeeRecord.getComments() != null) {
            this.et_comments.setText(employeeDbRecord.getAddEmployeeRecord().getComments());
        }
        int i = this.reasonId;
        if (i == 7 || i == 8 || i == 9) {
            this.attendance_layout.setVisibility(8);
        }
        if (employeeDbRecord.getAddEmployeeRecord().getReferredLetterNumber() != null) {
            this.et_referred_letter_no.setText(employeeDbRecord.getAddEmployeeRecord().getReferredLetterNumber());
        }
        if (employeeDbRecord.getAddEmployeeRecord().getReferredLetterDated() != null) {
            this.referred_letter_date.setText(employeeDbRecord.getAddEmployeeRecord().getReferredLetterDated());
        }
        if (employeeDbRecord.getAddEmployeeRecord().getOrderEffectingDate() != null) {
            this.order_effecting_date.setText(employeeDbRecord.getAddEmployeeRecord().getOrderEffectingDate());
        }
        if (this.reasonId != 9 && employeeDbRecord.getAddEmployeeRecord().getEmployeeAttendance() != null) {
            ((RadioButton) this.rg_attendance.getChildAt(employeeDbRecord.getAddEmployeeRecord().getEmployeeAttendance().intValue())).setChecked(true);
        }
        if (addEmployeeRecord.getReopenTicket() != null) {
            if (addEmployeeRecord.getReopenTicket().booleanValue()) {
                ((RadioButton) this.rg_re_open.getChildAt(0)).setChecked(true);
            } else {
                ((RadioButton) this.rg_re_open.getChildAt(1)).setChecked(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerFormVisibility() {
        int i;
        if (!this.typing && (i = this.reasonId) != -1) {
            if (i != 1) {
                if (this.employee_id_layout.getVisibility() != 8) {
                    this.employee_id_layout.setVisibility(8);
                    this.et_person_id.setText("");
                }
                if (this.name_layout.getVisibility() != 8) {
                    this.name_layout.setVisibility(8);
                    this.et_name.setText("");
                }
                if (this.contact_no_layout.getVisibility() != 8) {
                    this.contact_no_layout.setVisibility(8);
                    this.et_contact_no.setText("");
                }
                if (this.dob_layout.getVisibility() != 8) {
                    this.dob_layout.setVisibility(8);
                    this.date_of_birth.setText("");
                }
                if (this.joining_date_layout.getVisibility() != 8) {
                    this.joining_date_layout.setVisibility(8);
                    this.joining_date.setText("");
                }
                if (this.gender_layout.getVisibility() != 8) {
                    this.gender_layout.setVisibility(8);
                    this.genderId = -1;
                    ((RadioButton) this.rg_gender.getChildAt(0)).setChecked(false);
                    ((RadioButton) this.rg_gender.getChildAt(1)).setChecked(false);
                    return;
                }
                return;
            }
            return;
        }
        if (this.school_layout.getVisibility() != 8) {
            this.school_layout.setVisibility(8);
        }
        if (this.employee_id_layout.getVisibility() != 8) {
            this.employee_id_layout.setVisibility(8);
            this.et_person_id.setText("");
        }
        if (this.name_layout.getVisibility() != 8) {
            this.name_layout.setVisibility(8);
            this.et_name.setText("");
        }
        if (this.contact_no_layout.getVisibility() != 8) {
            this.contact_no_layout.setVisibility(8);
            this.et_contact_no.setText("");
        }
        if (this.dob_layout.getVisibility() != 8) {
            this.dob_layout.setVisibility(8);
            this.date_of_birth.setText("");
        }
        if (this.designation_layout.getVisibility() != 8) {
            this.designation_layout.setVisibility(8);
            this.et_designation.setText("");
        }
        if (this.joining_date_layout.getVisibility() != 8) {
            this.joining_date_layout.setVisibility(8);
            this.joining_date.setText("");
        }
        if (this.gender_layout.getVisibility() != 8) {
            this.gender_layout.setVisibility(8);
            this.genderId = -1;
            ((RadioButton) this.rg_gender.getChildAt(0)).setChecked(false);
            ((RadioButton) this.rg_gender.getChildAt(1)).setChecked(false);
        }
        if (this.admin_response_layout.getVisibility() != 8) {
            this.admin_response_layout.setVisibility(8);
        }
        if (this.re_open_layout.getVisibility() != 8) {
            this.re_open_layout.setVisibility(8);
        }
        if (this.attendance_layout.getVisibility() != 8) {
            this.attendance_layout.setVisibility(8);
            this.attendanceId = -1;
            this.rg_attendance.clearCheck();
        }
        if (this.images_layout.getVisibility() != 8) {
            this.images_layout.setVisibility(8);
        }
        if (this.letter_order_layout.getVisibility() != 8) {
            this.letter_order_layout.setVisibility(8);
        }
        if (this.remarks_layout.getVisibility() != 8) {
            this.remarks_layout.setVisibility(8);
            this.et_comments.setText("");
        }
        if (this.save_btn.getVisibility() != 8) {
            this.save_btn.setVisibility(8);
        }
    }

    private void recordFound() {
        this.school_layout.setVisibility(8);
        this.employee_id_layout.setVisibility(8);
        this.name_layout.setVisibility(8);
        this.contact_no_layout.setVisibility(8);
        this.dob_layout.setVisibility(8);
        this.designation_layout.setVisibility(8);
        this.joining_date_layout.setVisibility(8);
        this.gender_layout.setVisibility(8);
        this.admin_response_layout.setVisibility(0);
        this.attendance_layout.setVisibility(0);
        this.images_layout.setVisibility(0);
        this.remarks_layout.setVisibility(0);
        this.save_btn.setVisibility(0);
    }

    private void recordNotFound() {
        this.school_layout.setVisibility(0);
        this.employee_id_layout.setVisibility(0);
        this.name_layout.setVisibility(0);
        this.contact_no_layout.setVisibility(0);
        this.dob_layout.setVisibility(0);
        this.designation_layout.setVisibility(0);
        this.joining_date_layout.setVisibility(0);
        this.gender_layout.setVisibility(0);
        this.admin_response_layout.setVisibility(8);
        this.re_open_layout.setVisibility(8);
        this.attendance_layout.setVisibility(0);
        this.images_layout.setVisibility(0);
        this.remarks_layout.setVisibility(0);
        this.save_btn.setVisibility(0);
        clearForm();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearForm() {
        this.schools_spinner.setSelection(0);
        this.et_person_id.setText("");
        this.et_name.setText("");
        this.et_contact_no.setText("");
        this.date_of_birth.setText("");
        this.et_designation.setText("");
        this.joining_date.setText("");
        this.rg_gender.clearCheck();
        this.rg_attendance.clearCheck();
        this.rg_re_open.clearCheck();
        this.et_referred_letter_no.setText("");
        this.referred_letter_date.setText("");
        this.order_effecting_date.setText("");
        this.employeePictureFile = null;
        this.employee_picture.setImageDrawable(null);
        this.employee_picture.setVisibility(8);
        this.cnicPictureFile = null;
        this.cnic_picture.setImageDrawable(null);
        this.cnic_picture.setVisibility(8);
        this.reasonOrderFile = null;
        this.appointment_order_picture.setImageDrawable(null);
        this.appointment_order_picture.setVisibility(8);
        this.appointment_order_pdf.setImageDrawable(null);
        this.appointment_order_pdf.setVisibility(8);
        this.offerOrderFile = null;
        this.offer_order_picture.setImageDrawable(null);
        this.offer_order_picture.setVisibility(8);
        this.offer_order_pdf.setImageDrawable(null);
        this.offer_order_pdf.setVisibility(8);
        this.employeePayslipFile = null;
        this.employee_payslip_picture.setImageDrawable(null);
        this.employee_payslip_picture.setVisibility(8);
        this.joiningReportFile = null;
        this.joining_report_picture.setImageDrawable(null);
        this.joining_report_picture.setVisibility(8);
        this.pictureAttendanceRegisterFile = null;
        this.attendance_register_picture.setImageDrawable(null);
        this.attendance_register_picture.setVisibility(8);
        this.informationLetterFile = null;
        this.information_letter_picture.setImageDrawable(null);
        this.information_letter_picture.setVisibility(8);
        this.genderId = -1;
        this.attendanceId = -1;
        this.reOpenId = -1;
        this.et_comments.setText("");
        letterFieldsOptional();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchRecord(String cnic, int reason, View view) {
        try {
            AddStaffTicketing addStaffTicketingFetchStaffTicketingData = CommonActions.getDbHandler(getContext()).fetchStaffTicketingData(cnic, String.valueOf(reason));
            if (addStaffTicketingFetchStaffTicketingData != null) {
                this.recordFound = true;
                this.newRecordFound = false;
                this.localSyncedRecordFound = false;
                recordFound();
                this.employeeRecordUpdateDatum = (EmployeeRecordUpdateDatum) new Gson().fromJson(addStaffTicketingFetchStaffTicketingData.getTicketData(), EmployeeRecordUpdateDatum.class);
                List<EmployeeDbRecord> employeeRecordsFromCNIC = CommonActions.getDbHandler(getContext()).getEmployeeRecordsFromCNIC(cnic, getReasonName(reason));
                if (employeeRecordsFromCNIC != null && !employeeRecordsFromCNIC.isEmpty() && employeeRecordsFromCNIC.get(0) != null) {
                    EmployeeDbRecord employeeDbRecord = employeeRecordsFromCNIC.get(0);
                    this.updateRecordId = Integer.valueOf(employeeDbRecord.getRecordId());
                    if (employeeDbRecord.getAddEmployeeRecord().getEmployeeAttendance() != null) {
                        int iIntValue = employeeDbRecord.getAddEmployeeRecord().getEmployeeAttendance().intValue();
                        this.attendanceId = iIntValue;
                        ((RadioButton) this.rg_attendance.getChildAt(iIntValue)).setChecked(true);
                    }
                    if (employeeDbRecord.getAddEmployeeRecord().getReopenTicket().booleanValue()) {
                        this.reOpenId = 0;
                        ((RadioButton) this.rg_re_open.getChildAt(0)).setChecked(true);
                        this.letter_order_layout.setVisibility(0);
                        this.images_layout.setVisibility(0);
                    } else {
                        this.reOpenId = 1;
                        ((RadioButton) this.rg_re_open.getChildAt(1)).setChecked(true);
                        this.letter_order_layout.setVisibility(8);
                        this.images_layout.setVisibility(8);
                    }
                    if (this.employeeRecordUpdateDatum.getStatus().intValue() != 1 || this.reOpenId != 1) {
                        showImages(employeeDbRecord);
                    }
                    this.et_referred_letter_no.setText(employeeDbRecord.getAddEmployeeRecord().getReferredLetterNumber());
                    this.referred_letter_date.setText(employeeDbRecord.getAddEmployeeRecord().getReferredLetterDated());
                    this.order_effecting_date.setText(employeeDbRecord.getAddEmployeeRecord().getOrderEffectingDate());
                    this.et_comments.setText(employeeDbRecord.getAddEmployeeRecord().getComments());
                    if (employeeDbRecord.getSyncStatus() == 0) {
                        this.editMode = true;
                    }
                } else {
                    this.reOpenId = 1;
                    ((RadioButton) this.rg_re_open.getChildAt(1)).setChecked(true);
                    if (this.employeeRecordUpdateDatum.getStatus().intValue() == 1) {
                        this.letter_order_layout.setVisibility(8);
                        this.images_layout.setVisibility(8);
                    } else {
                        this.letter_order_layout.setVisibility(0);
                        this.images_layout.setVisibility(0);
                    }
                }
                if (this.employeeRecordUpdateDatum.getComments() == null || this.employeeRecordUpdateDatum.getComments().isEmpty()) {
                    this.tv_admin_response.setText(R.string.no_response_from_administration);
                } else {
                    this.tv_admin_response.setText(this.employeeRecordUpdateDatum.getComments());
                }
                if (this.employeeRecordUpdateDatum.getStatus().intValue() == 1) {
                    this.status_card.setCardBackgroundColor(ContextCompat.getColor(getActivityContext(), R.color.colorPrimaryDark));
                    this.tv_ticket_status.setText(R.string.closed);
                    this.re_open_layout.setVisibility(0);
                } else {
                    this.status_card.setCardBackgroundColor(ContextCompat.getColor(getActivityContext(), R.color.yellow));
                    this.reOpenId = -1;
                    this.tv_ticket_status.setText(R.string.open);
                    this.re_open_layout.setVisibility(8);
                }
                if (this.employeeRecordUpdateDatum.getOtherPicture() != null && !this.employeeRecordUpdateDatum.getOtherPicture().isEmpty()) {
                    this.response_picture_layout.setVisibility(0);
                    this.tv_tap_image_view.setVisibility(0);
                    this.response_picture_tap.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$iXKoY_gUUiSUp1Gdcmz4k4bJYvA
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            this.f$0.lambda$fetchRecord$33$AddEmployeeFragment(view2);
                        }
                    });
                    return;
                } else {
                    this.response_picture_layout.setVisibility(8);
                    this.tv_tap_image_view.setVisibility(8);
                    return;
                }
            }
            this.recordFound = false;
            this.rg_re_open.clearCheck();
            this.reOpenId = -1;
            recordNotFound();
            List<EmployeeDbRecord> employeeRecordsFromCNIC2 = CommonActions.getDbHandler(getContext()).getEmployeeRecordsFromCNIC(cnic, getReasonName(reason));
            if (employeeRecordsFromCNIC2 != null && !employeeRecordsFromCNIC2.isEmpty()) {
                EmployeeDbRecord employeeDbRecord2 = employeeRecordsFromCNIC2.get(0);
                this.updateRecordId = Integer.valueOf(employeeDbRecord2.getRecordId());
                this.newRecordFound = true;
                this.editMode = true;
                newRecordFound(employeeDbRecord2, view);
                return;
            }
            this.localSyncedRecordFound = false;
            this.newRecordFound = false;
            this.editMode = false;
            recordNotFound();
        } catch (Exception e) {
            this.localSyncedRecordFound = false;
            this.recordFound = false;
            recordNotFound();
            util.logException(e);
        }
    }

    public /* synthetic */ void lambda$fetchRecord$33$AddEmployeeFragment(View view) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(WebConstant.fileBaseUrl + this.employeeRecordUpdateDatum.getOtherPicture())));
        } catch (Exception unused) {
            getMainActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$9ygxCVE8_hc7ly_7LO-b3lyTS24
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$fetchRecord$32$AddEmployeeFragment();
                }
            });
        }
    }

    public /* synthetic */ void lambda$fetchRecord$32$AddEmployeeFragment() {
        Toast.makeText(getActivityContext(), "Unable to view image", 0).show();
    }

    private void newRecordFound(EmployeeDbRecord employeeDbRecord, View view) {
        String str;
        List<School> list;
        if (employeeDbRecord.getSyncStatus() == 1 || employeeDbRecord.getAlreadySynced() == 1) {
            recordFound();
            this.localSyncedRecordFound = true;
            this.editMode = true;
            this.status_card.setCardBackgroundColor(ContextCompat.getColor(getActivityContext(), R.color.yellow));
            this.response_picture_layout.setVisibility(8);
            this.tv_tap_image_view.setVisibility(8);
            if (employeeDbRecord.getSyncStatus() == 0) {
                Integer employeeAttendance = employeeDbRecord.getAddEmployeeRecord().getEmployeeAttendance();
                String comments = employeeDbRecord.getAddEmployeeRecord().getComments();
                String referredLetterNumber = employeeDbRecord.getAddEmployeeRecord().getReferredLetterNumber();
                String referredLetterDated = employeeDbRecord.getAddEmployeeRecord().getReferredLetterDated();
                String orderEffectingDate = employeeDbRecord.getAddEmployeeRecord().getOrderEffectingDate();
                if (employeeAttendance != null) {
                    this.attendanceId = employeeAttendance.intValue();
                    ((RadioButton) this.rg_attendance.getChildAt(employeeDbRecord.getAddEmployeeRecord().getEmployeeAttendance().intValue())).setChecked(true);
                }
                if (referredLetterNumber != null) {
                    this.et_referred_letter_no.setText(referredLetterNumber);
                }
                if (referredLetterDated != null) {
                    this.referred_letter_date.setText(referredLetterDated);
                }
                if (orderEffectingDate != null) {
                    this.order_effecting_date.setText(orderEffectingDate);
                }
                showImages(employeeDbRecord);
                if (comments == null || comments.isEmpty()) {
                    return;
                }
                this.et_comments.setText(comments);
                return;
            }
            return;
        }
        this.localSyncedRecordFound = false;
        this.letter_order_layout.setVisibility(0);
        try {
            String schoolSEMISCodeVisit = employeeDbRecord.getAddEmployeeRecord().getSchoolSEMISCodeVisit();
            String employeeCode = employeeDbRecord.getAddEmployeeRecord().getEmployeeCode();
            String employeeName = employeeDbRecord.getAddEmployeeRecord().getEmployeeName();
            String contactNo = employeeDbRecord.getAddEmployeeRecord().getContactNo();
            String dateOfBirth = employeeDbRecord.getAddEmployeeRecord().getDateOfBirth();
            String designation = employeeDbRecord.getAddEmployeeRecord().getDesignation();
            String joiningDate = employeeDbRecord.getAddEmployeeRecord().getJoiningDate();
            Integer gender = employeeDbRecord.getAddEmployeeRecord().getGender();
            Integer employeeAttendance2 = employeeDbRecord.getAddEmployeeRecord().getEmployeeAttendance();
            String comments2 = employeeDbRecord.getAddEmployeeRecord().getComments();
            String referredLetterNumber2 = employeeDbRecord.getAddEmployeeRecord().getReferredLetterNumber();
            String referredLetterDated2 = employeeDbRecord.getAddEmployeeRecord().getReferredLetterDated();
            String orderEffectingDate2 = employeeDbRecord.getAddEmployeeRecord().getOrderEffectingDate();
            if (schoolSEMISCodeVisit == null || schoolSEMISCodeVisit.isEmpty() || (list = this.schools) == null) {
                str = comments2;
            } else {
                Iterator<School> it = list.iterator();
                int i = 0;
                while (it.hasNext()) {
                    Iterator<School> it2 = it;
                    School next = it.next();
                    str = comments2;
                    if (next.getSchoolSemisCode().equals(schoolSEMISCodeVisit)) {
                        this.selectedSchool = next;
                        this.schools_spinner.setSelection(i);
                        break;
                    } else {
                        i++;
                        it = it2;
                        comments2 = str;
                    }
                }
                str = comments2;
            }
            if (this.reasonId == 1 && employeeCode != null && !employeeCode.isEmpty()) {
                this.et_person_id.setText(employeeCode);
            }
            if (this.reasonId == 1 && employeeName != null && !employeeName.isEmpty()) {
                this.et_name.setText(employeeName);
            }
            if (this.reasonId == 1 && contactNo != null && !contactNo.isEmpty()) {
                this.et_contact_no.setText(contactNo);
            }
            if (this.reasonId == 1 && dateOfBirth != null && !dateOfBirth.isEmpty()) {
                this.date_of_birth.setText(dateOfBirth);
            }
            if (designation != null && !designation.isEmpty()) {
                this.et_designation.setText(designation);
            }
            if (this.reasonId == 1 && joiningDate != null && !joiningDate.isEmpty()) {
                this.joining_date.setText(joiningDate);
            }
            if (this.reasonId == 1 && gender != null) {
                this.genderId = gender.intValue();
                ((RadioButton) this.rg_gender.getChildAt(gender.intValue() - 1)).setChecked(true);
            }
            if (this.reasonId != 9 && employeeAttendance2 != null) {
                this.attendanceId = employeeAttendance2.intValue();
                ((RadioButton) this.rg_attendance.getChildAt(employeeDbRecord.getAddEmployeeRecord().getEmployeeAttendance().intValue())).setChecked(true);
            }
            if (referredLetterNumber2 != null) {
                this.et_referred_letter_no.setText(referredLetterNumber2);
            }
            if (referredLetterDated2 != null) {
                this.referred_letter_date.setText(referredLetterDated2);
            }
            if (orderEffectingDate2 != null) {
                this.order_effecting_date.setText(orderEffectingDate2);
            }
            showImages(employeeDbRecord);
            if (str == null || str.isEmpty()) {
                return;
            }
            this.et_comments.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
            util.logException(e);
        }
    }

    private String getReasonName(int reasonId) {
        List<ReferenceDataResponse.ReferenceData> list = this.REASONS_LIST;
        if (list != null && !list.isEmpty()) {
            for (ReferenceDataResponse.ReferenceData referenceData : this.REASONS_LIST) {
                if (referenceData.getReferencekey().intValue() == reasonId) {
                    return referenceData.getReferencevalue();
                }
            }
        }
        return null;
    }

    private void renderReOpenTickerOptions() {
        Collection collectionSelect = CollectionUtils.select(this.preferenceHelper.getReferenceData().getData(), new Predicate() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$SsiUeo8xV_ommsm8-MFMrTblu8Y
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return ((ReferenceDataResponse.ReferenceData) obj).getReferencecode().equals("INDICATOR");
            }
        });
        if (collectionSelect instanceof List) {
            this.REOPEN_LIST = (List) collectionSelect;
        } else {
            this.REOPEN_LIST = new ArrayList(collectionSelect);
        }
        if (this.REOPEN_LIST.isEmpty()) {
            return;
        }
        this.rg_re_open.setOrientation(1);
        for (ReferenceDataResponse.ReferenceData referenceData : this.REOPEN_LIST) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(referenceData.getReferencedataid().intValue());
            radioButton.setText(referenceData.getReferencevalue());
            radioButton.setTextColor(getResources().getColor(R.color.boundary_wall_text_bg_color));
            radioButton.setTextSize(16.0f);
            radioButton.setButtonDrawable(R.drawable.radiobuttonstate);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$lkGzGGy24OdstFcoERF19AkmCGg
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    this.f$0.lambda$renderReOpenTickerOptions$35$AddEmployeeFragment(compoundButton, z);
                }
            });
            this.rg_re_open.addView(radioButton);
        }
        ((RadioButton) this.rg_re_open.getChildAt(1)).setChecked(true);
    }

    public /* synthetic */ void lambda$renderReOpenTickerOptions$35$AddEmployeeFragment(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isChecked()) {
            for (ReferenceDataResponse.ReferenceData referenceData : this.REOPEN_LIST) {
                if (referenceData.getReferencedataid().intValue() == compoundButton.getId()) {
                    int iIntValue = referenceData.getReferencekey().intValue();
                    this.reOpenId = iIntValue;
                    if (this.recordFound || this.localSyncedRecordFound || this.newRecordFound) {
                        if (iIntValue == 0) {
                            this.images_layout.setVisibility(0);
                            this.letter_order_layout.setVisibility(0);
                            imageTitlesRequiredHandling();
                            letterFieldsRequiredHandling();
                        } else {
                            this.images_layout.setVisibility(8);
                            this.letter_order_layout.setVisibility(8);
                        }
                    }
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.employeeUpdateRecord = null;
    }

    @Override // com.micromerger.ssms.startmonitoring.fragments.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri data2;
        Uri data3;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == 101 && (data3 = data.getData()) != null) {
                try {
                    File fileFrom = FileUtil.from(getContext(), data3);
                    if (fileFrom.exists()) {
                        long length = fileFrom.length();
                        if (length < 10485760) {
                            File file = new File(getContext().getFilesDir().getPath() + File.separator + "pdf");
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            File file2 = new File(file.getPath() + File.separator + ("appointment_order_" + System.currentTimeMillis() + ".pdf"));
                            if (FileUtil.saveFileInInternalStorage(fileFrom, file2)) {
                                this.reasonOrderFile = file2;
                            } else {
                                this.reasonOrderFile = fileFrom;
                            }
                            letterFieldsRequired();
                            this.appointment_order_picture.setVisibility(8);
                            this.appointment_order_pdf.setVisibility(0);
                            this.appointment_order_pdf.setImageResource(R.drawable.pdf);
                        } else {
                            getMainActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$0qJVQEmic6CpA191dyimgv6xE2g
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f$0.lambda$onActivityResult$36$AddEmployeeFragment();
                                }
                            });
                        }
                        Log.d("Appointment Order", "fileSize: " + length);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    util.logException(e);
                }
            }
            if (requestCode != 102 || (data2 = data.getData()) == null) {
                return;
            }
            try {
                File fileFrom2 = FileUtil.from(getContext(), data2);
                if (fileFrom2.exists()) {
                    long length2 = fileFrom2.length();
                    if (length2 < 10485760) {
                        File file3 = new File(getContext().getFilesDir().getPath() + File.separator + "pdf");
                        if (!file3.exists()) {
                            file3.mkdirs();
                        }
                        File file4 = new File(file3.getPath() + File.separator + ("offer_order_" + System.currentTimeMillis() + ".pdf"));
                        if (FileUtil.saveFileInInternalStorage(fileFrom2, file4)) {
                            this.offerOrderFile = file4;
                        } else {
                            this.offerOrderFile = fileFrom2;
                        }
                        this.offer_order_picture.setVisibility(8);
                        this.offer_order_pdf.setVisibility(0);
                        this.offer_order_pdf.setImageResource(R.drawable.pdf);
                    } else {
                        getMainActivity().runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.addemployee.-$$Lambda$AddEmployeeFragment$bcbAYnfpViu1LBZf8KgjlORZ8Po
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.lambda$onActivityResult$37$AddEmployeeFragment();
                            }
                        });
                    }
                    Log.d("Offer Order", "fileSize: " + length2);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                util.logException(e2);
            }
        }
    }

    public /* synthetic */ void lambda$onActivityResult$36$AddEmployeeFragment() {
        Toast.makeText(getContext(), "PDF file size should not be greater than 5MB", 0).show();
    }

    public /* synthetic */ void lambda$onActivityResult$37$AddEmployeeFragment() {
        Toast.makeText(getContext(), "PDF file size should not be greater than 5MB", 0).show();
    }
}
