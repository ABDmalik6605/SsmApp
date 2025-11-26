package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

/* loaded from: classes2.dex */
public final class FragmentAddEmployeeBinding implements ViewBinding {
    public final LinearLayout adminResponseLayout;
    public final AppointmentOrderPictureLayoutBinding appointmentOrderPictureLayout;
    public final LinearLayout attendanceLayout;
    public final LinearLayout cnicLayout;
    public final CnicPictureLayoutBinding cnicPictureLayout;
    public final LinearLayout contactNoLayout;
    public final TextView dateOfBirth;
    public final LinearLayout designationLayout;
    public final LinearLayout dobLayout;
    public final LinearLayout employeeIdLayout;
    public final EmployeePayslipLayoutBinding employeePayslipLayout;
    public final EmployeePictureLayoutBinding employeePictureLayout;
    public final EmployeeThumbLayoutBinding employeeThumbLayout;
    public final EditText etCnic;
    public final EditText etComments;
    public final EditText etContactNo;
    public final EditText etDesignation;
    public final EditText etName;
    public final EditText etOrderNo;
    public final EditText etPersonId;
    public final LinearLayout genderLayout;
    public final LinearLayout imagesLayout;
    public final LinearLayout imagesLinearLayout;
    public final InformationLetterLayoutBinding informationLetterLayout;
    public final TextView joiningDate;
    public final LinearLayout joiningDateLayout;
    public final JoiningReportLayoutBinding joiningReportLayout;
    public final LinearLayout letterOrderLayout;
    public final LinearLayout nameLayout;
    public final OfferOrderLayoutBinding offerOrderLayout;
    public final TextView orderDate;
    public final LinearLayout orderDateLayout;
    public final LinearLayout orderNoLayout;
    public final ImageView otherPictureCameraIcon;
    public final PictureAttendanceRegisterLayoutBinding pictureAttendanceRegisterLayout;
    public final PostingOrderPictureLayoutBinding postingOrderPictureLayout;
    public final LinearLayout reOpenLayout;
    public final LinearLayout reasonLayout;
    public final RelativeLayout reasonsLayout;
    public final RelativeLayout remarksLayout;
    public final LinearLayout responsePictureLayout;
    public final RelativeLayout responsePictureTap;
    public final TextView retiredExpiredDate;
    public final LinearLayout retiredExpiredDateLayout;
    public final RadioGroup rgAttendance;
    public final RadioGroup rgGender;
    public final RadioGroup rgReOpen;
    public final RadioGroup rgReason;
    private final ScrollView rootView;
    public final Button saveBtn;
    public final LinearLayout schoolLayout;
    public final SearchableSpinner schoolsSpinner;
    public final CardView statusCard;
    public final TextView tvAdminResponse;
    public final TextView tvAttendance;
    public final TextView tvDesignation;
    public final TextView tvOrderDate;
    public final TextView tvOrderNo;
    public final TextView tvPersonId;
    public final TextView tvRemarks;
    public final TextView tvRetiredExpiredDate;
    public final TextView tvTapImageView;
    public final TextView tvTicketStatus;

    private FragmentAddEmployeeBinding(ScrollView rootView, LinearLayout adminResponseLayout, AppointmentOrderPictureLayoutBinding appointmentOrderPictureLayout, LinearLayout attendanceLayout, LinearLayout cnicLayout, CnicPictureLayoutBinding cnicPictureLayout, LinearLayout contactNoLayout, TextView dateOfBirth, LinearLayout designationLayout, LinearLayout dobLayout, LinearLayout employeeIdLayout, EmployeePayslipLayoutBinding employeePayslipLayout, EmployeePictureLayoutBinding employeePictureLayout, EmployeeThumbLayoutBinding employeeThumbLayout, EditText etCnic, EditText etComments, EditText etContactNo, EditText etDesignation, EditText etName, EditText etOrderNo, EditText etPersonId, LinearLayout genderLayout, LinearLayout imagesLayout, LinearLayout imagesLinearLayout, InformationLetterLayoutBinding informationLetterLayout, TextView joiningDate, LinearLayout joiningDateLayout, JoiningReportLayoutBinding joiningReportLayout, LinearLayout letterOrderLayout, LinearLayout nameLayout, OfferOrderLayoutBinding offerOrderLayout, TextView orderDate, LinearLayout orderDateLayout, LinearLayout orderNoLayout, ImageView otherPictureCameraIcon, PictureAttendanceRegisterLayoutBinding pictureAttendanceRegisterLayout, PostingOrderPictureLayoutBinding postingOrderPictureLayout, LinearLayout reOpenLayout, LinearLayout reasonLayout, RelativeLayout reasonsLayout, RelativeLayout remarksLayout, LinearLayout responsePictureLayout, RelativeLayout responsePictureTap, TextView retiredExpiredDate, LinearLayout retiredExpiredDateLayout, RadioGroup rgAttendance, RadioGroup rgGender, RadioGroup rgReOpen, RadioGroup rgReason, Button saveBtn, LinearLayout schoolLayout, SearchableSpinner schoolsSpinner, CardView statusCard, TextView tvAdminResponse, TextView tvAttendance, TextView tvDesignation, TextView tvOrderDate, TextView tvOrderNo, TextView tvPersonId, TextView tvRemarks, TextView tvRetiredExpiredDate, TextView tvTapImageView, TextView tvTicketStatus) {
        this.rootView = rootView;
        this.adminResponseLayout = adminResponseLayout;
        this.appointmentOrderPictureLayout = appointmentOrderPictureLayout;
        this.attendanceLayout = attendanceLayout;
        this.cnicLayout = cnicLayout;
        this.cnicPictureLayout = cnicPictureLayout;
        this.contactNoLayout = contactNoLayout;
        this.dateOfBirth = dateOfBirth;
        this.designationLayout = designationLayout;
        this.dobLayout = dobLayout;
        this.employeeIdLayout = employeeIdLayout;
        this.employeePayslipLayout = employeePayslipLayout;
        this.employeePictureLayout = employeePictureLayout;
        this.employeeThumbLayout = employeeThumbLayout;
        this.etCnic = etCnic;
        this.etComments = etComments;
        this.etContactNo = etContactNo;
        this.etDesignation = etDesignation;
        this.etName = etName;
        this.etOrderNo = etOrderNo;
        this.etPersonId = etPersonId;
        this.genderLayout = genderLayout;
        this.imagesLayout = imagesLayout;
        this.imagesLinearLayout = imagesLinearLayout;
        this.informationLetterLayout = informationLetterLayout;
        this.joiningDate = joiningDate;
        this.joiningDateLayout = joiningDateLayout;
        this.joiningReportLayout = joiningReportLayout;
        this.letterOrderLayout = letterOrderLayout;
        this.nameLayout = nameLayout;
        this.offerOrderLayout = offerOrderLayout;
        this.orderDate = orderDate;
        this.orderDateLayout = orderDateLayout;
        this.orderNoLayout = orderNoLayout;
        this.otherPictureCameraIcon = otherPictureCameraIcon;
        this.pictureAttendanceRegisterLayout = pictureAttendanceRegisterLayout;
        this.postingOrderPictureLayout = postingOrderPictureLayout;
        this.reOpenLayout = reOpenLayout;
        this.reasonLayout = reasonLayout;
        this.reasonsLayout = reasonsLayout;
        this.remarksLayout = remarksLayout;
        this.responsePictureLayout = responsePictureLayout;
        this.responsePictureTap = responsePictureTap;
        this.retiredExpiredDate = retiredExpiredDate;
        this.retiredExpiredDateLayout = retiredExpiredDateLayout;
        this.rgAttendance = rgAttendance;
        this.rgGender = rgGender;
        this.rgReOpen = rgReOpen;
        this.rgReason = rgReason;
        this.saveBtn = saveBtn;
        this.schoolLayout = schoolLayout;
        this.schoolsSpinner = schoolsSpinner;
        this.statusCard = statusCard;
        this.tvAdminResponse = tvAdminResponse;
        this.tvAttendance = tvAttendance;
        this.tvDesignation = tvDesignation;
        this.tvOrderDate = tvOrderDate;
        this.tvOrderNo = tvOrderNo;
        this.tvPersonId = tvPersonId;
        this.tvRemarks = tvRemarks;
        this.tvRetiredExpiredDate = tvRetiredExpiredDate;
        this.tvTapImageView = tvTapImageView;
        this.tvTicketStatus = tvTicketStatus;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentAddEmployeeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentAddEmployeeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_add_employee, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentAddEmployeeBinding bind(View rootView) {
        int i = R.id.admin_response_layout;
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.admin_response_layout);
        if (linearLayout != null) {
            i = R.id.appointment_order_picture_layout;
            View viewFindViewById = rootView.findViewById(R.id.appointment_order_picture_layout);
            if (viewFindViewById != null) {
                AppointmentOrderPictureLayoutBinding appointmentOrderPictureLayoutBindingBind = AppointmentOrderPictureLayoutBinding.bind(viewFindViewById);
                i = R.id.attendance_layout;
                LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.attendance_layout);
                if (linearLayout2 != null) {
                    i = R.id.cnic_layout;
                    LinearLayout linearLayout3 = (LinearLayout) rootView.findViewById(R.id.cnic_layout);
                    if (linearLayout3 != null) {
                        i = R.id.cnic_picture_layout;
                        View viewFindViewById2 = rootView.findViewById(R.id.cnic_picture_layout);
                        if (viewFindViewById2 != null) {
                            CnicPictureLayoutBinding cnicPictureLayoutBindingBind = CnicPictureLayoutBinding.bind(viewFindViewById2);
                            i = R.id.contact_no_layout;
                            LinearLayout linearLayout4 = (LinearLayout) rootView.findViewById(R.id.contact_no_layout);
                            if (linearLayout4 != null) {
                                i = R.id.date_of_birth;
                                TextView textView = (TextView) rootView.findViewById(R.id.date_of_birth);
                                if (textView != null) {
                                    i = R.id.designation_layout;
                                    LinearLayout linearLayout5 = (LinearLayout) rootView.findViewById(R.id.designation_layout);
                                    if (linearLayout5 != null) {
                                        i = R.id.dob_layout;
                                        LinearLayout linearLayout6 = (LinearLayout) rootView.findViewById(R.id.dob_layout);
                                        if (linearLayout6 != null) {
                                            i = R.id.employee_id_layout;
                                            LinearLayout linearLayout7 = (LinearLayout) rootView.findViewById(R.id.employee_id_layout);
                                            if (linearLayout7 != null) {
                                                i = R.id.employee_payslip_layout;
                                                View viewFindViewById3 = rootView.findViewById(R.id.employee_payslip_layout);
                                                if (viewFindViewById3 != null) {
                                                    EmployeePayslipLayoutBinding employeePayslipLayoutBindingBind = EmployeePayslipLayoutBinding.bind(viewFindViewById3);
                                                    i = R.id.employee_picture_layout;
                                                    View viewFindViewById4 = rootView.findViewById(R.id.employee_picture_layout);
                                                    if (viewFindViewById4 != null) {
                                                        EmployeePictureLayoutBinding employeePictureLayoutBindingBind = EmployeePictureLayoutBinding.bind(viewFindViewById4);
                                                        i = R.id.employee_thumb_layout;
                                                        View viewFindViewById5 = rootView.findViewById(R.id.employee_thumb_layout);
                                                        if (viewFindViewById5 != null) {
                                                            EmployeeThumbLayoutBinding employeeThumbLayoutBindingBind = EmployeeThumbLayoutBinding.bind(viewFindViewById5);
                                                            i = R.id.et_cnic;
                                                            EditText editText = (EditText) rootView.findViewById(R.id.et_cnic);
                                                            if (editText != null) {
                                                                i = R.id.et_comments;
                                                                EditText editText2 = (EditText) rootView.findViewById(R.id.et_comments);
                                                                if (editText2 != null) {
                                                                    i = R.id.et_contact_no;
                                                                    EditText editText3 = (EditText) rootView.findViewById(R.id.et_contact_no);
                                                                    if (editText3 != null) {
                                                                        i = R.id.et_designation;
                                                                        EditText editText4 = (EditText) rootView.findViewById(R.id.et_designation);
                                                                        if (editText4 != null) {
                                                                            i = R.id.et_name;
                                                                            EditText editText5 = (EditText) rootView.findViewById(R.id.et_name);
                                                                            if (editText5 != null) {
                                                                                i = R.id.et_order_no;
                                                                                EditText editText6 = (EditText) rootView.findViewById(R.id.et_order_no);
                                                                                if (editText6 != null) {
                                                                                    i = R.id.et_person_id;
                                                                                    EditText editText7 = (EditText) rootView.findViewById(R.id.et_person_id);
                                                                                    if (editText7 != null) {
                                                                                        i = R.id.gender_layout;
                                                                                        LinearLayout linearLayout8 = (LinearLayout) rootView.findViewById(R.id.gender_layout);
                                                                                        if (linearLayout8 != null) {
                                                                                            i = R.id.images_layout;
                                                                                            LinearLayout linearLayout9 = (LinearLayout) rootView.findViewById(R.id.images_layout);
                                                                                            if (linearLayout9 != null) {
                                                                                                i = R.id.images_linear_layout;
                                                                                                LinearLayout linearLayout10 = (LinearLayout) rootView.findViewById(R.id.images_linear_layout);
                                                                                                if (linearLayout10 != null) {
                                                                                                    i = R.id.information_letter_layout;
                                                                                                    View viewFindViewById6 = rootView.findViewById(R.id.information_letter_layout);
                                                                                                    if (viewFindViewById6 != null) {
                                                                                                        InformationLetterLayoutBinding informationLetterLayoutBindingBind = InformationLetterLayoutBinding.bind(viewFindViewById6);
                                                                                                        i = R.id.joining_date;
                                                                                                        TextView textView2 = (TextView) rootView.findViewById(R.id.joining_date);
                                                                                                        if (textView2 != null) {
                                                                                                            i = R.id.joining_date_layout;
                                                                                                            LinearLayout linearLayout11 = (LinearLayout) rootView.findViewById(R.id.joining_date_layout);
                                                                                                            if (linearLayout11 != null) {
                                                                                                                i = R.id.joining_report_layout;
                                                                                                                View viewFindViewById7 = rootView.findViewById(R.id.joining_report_layout);
                                                                                                                if (viewFindViewById7 != null) {
                                                                                                                    JoiningReportLayoutBinding joiningReportLayoutBindingBind = JoiningReportLayoutBinding.bind(viewFindViewById7);
                                                                                                                    i = R.id.letter_order_layout;
                                                                                                                    LinearLayout linearLayout12 = (LinearLayout) rootView.findViewById(R.id.letter_order_layout);
                                                                                                                    if (linearLayout12 != null) {
                                                                                                                        i = R.id.name_layout;
                                                                                                                        LinearLayout linearLayout13 = (LinearLayout) rootView.findViewById(R.id.name_layout);
                                                                                                                        if (linearLayout13 != null) {
                                                                                                                            i = R.id.offer_order_layout;
                                                                                                                            View viewFindViewById8 = rootView.findViewById(R.id.offer_order_layout);
                                                                                                                            if (viewFindViewById8 != null) {
                                                                                                                                OfferOrderLayoutBinding offerOrderLayoutBindingBind = OfferOrderLayoutBinding.bind(viewFindViewById8);
                                                                                                                                i = R.id.order_date;
                                                                                                                                TextView textView3 = (TextView) rootView.findViewById(R.id.order_date);
                                                                                                                                if (textView3 != null) {
                                                                                                                                    i = R.id.order_date_layout;
                                                                                                                                    LinearLayout linearLayout14 = (LinearLayout) rootView.findViewById(R.id.order_date_layout);
                                                                                                                                    if (linearLayout14 != null) {
                                                                                                                                        i = R.id.order_no_layout;
                                                                                                                                        LinearLayout linearLayout15 = (LinearLayout) rootView.findViewById(R.id.order_no_layout);
                                                                                                                                        if (linearLayout15 != null) {
                                                                                                                                            i = R.id.other_picture_camera_icon;
                                                                                                                                            ImageView imageView = (ImageView) rootView.findViewById(R.id.other_picture_camera_icon);
                                                                                                                                            if (imageView != null) {
                                                                                                                                                i = R.id.picture_attendance_register_layout;
                                                                                                                                                View viewFindViewById9 = rootView.findViewById(R.id.picture_attendance_register_layout);
                                                                                                                                                if (viewFindViewById9 != null) {
                                                                                                                                                    PictureAttendanceRegisterLayoutBinding pictureAttendanceRegisterLayoutBindingBind = PictureAttendanceRegisterLayoutBinding.bind(viewFindViewById9);
                                                                                                                                                    i = R.id.posting_order_picture_layout;
                                                                                                                                                    View viewFindViewById10 = rootView.findViewById(R.id.posting_order_picture_layout);
                                                                                                                                                    if (viewFindViewById10 != null) {
                                                                                                                                                        PostingOrderPictureLayoutBinding postingOrderPictureLayoutBindingBind = PostingOrderPictureLayoutBinding.bind(viewFindViewById10);
                                                                                                                                                        i = R.id.re_open_layout;
                                                                                                                                                        LinearLayout linearLayout16 = (LinearLayout) rootView.findViewById(R.id.re_open_layout);
                                                                                                                                                        if (linearLayout16 != null) {
                                                                                                                                                            i = R.id.reason_layout;
                                                                                                                                                            LinearLayout linearLayout17 = (LinearLayout) rootView.findViewById(R.id.reason_layout);
                                                                                                                                                            if (linearLayout17 != null) {
                                                                                                                                                                i = R.id.reasons_layout;
                                                                                                                                                                RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.reasons_layout);
                                                                                                                                                                if (relativeLayout != null) {
                                                                                                                                                                    i = R.id.remarks_layout;
                                                                                                                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.remarks_layout);
                                                                                                                                                                    if (relativeLayout2 != null) {
                                                                                                                                                                        i = R.id.response_picture_layout;
                                                                                                                                                                        LinearLayout linearLayout18 = (LinearLayout) rootView.findViewById(R.id.response_picture_layout);
                                                                                                                                                                        if (linearLayout18 != null) {
                                                                                                                                                                            i = R.id.response_picture_tap;
                                                                                                                                                                            RelativeLayout relativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.response_picture_tap);
                                                                                                                                                                            if (relativeLayout3 != null) {
                                                                                                                                                                                i = R.id.retired_expired_date;
                                                                                                                                                                                TextView textView4 = (TextView) rootView.findViewById(R.id.retired_expired_date);
                                                                                                                                                                                if (textView4 != null) {
                                                                                                                                                                                    i = R.id.retired_expired_date_layout;
                                                                                                                                                                                    LinearLayout linearLayout19 = (LinearLayout) rootView.findViewById(R.id.retired_expired_date_layout);
                                                                                                                                                                                    if (linearLayout19 != null) {
                                                                                                                                                                                        i = R.id.rg_attendance;
                                                                                                                                                                                        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_attendance);
                                                                                                                                                                                        if (radioGroup != null) {
                                                                                                                                                                                            i = R.id.rg_gender;
                                                                                                                                                                                            RadioGroup radioGroup2 = (RadioGroup) rootView.findViewById(R.id.rg_gender);
                                                                                                                                                                                            if (radioGroup2 != null) {
                                                                                                                                                                                                i = R.id.rg_re_open;
                                                                                                                                                                                                RadioGroup radioGroup3 = (RadioGroup) rootView.findViewById(R.id.rg_re_open);
                                                                                                                                                                                                if (radioGroup3 != null) {
                                                                                                                                                                                                    i = R.id.rg_reason;
                                                                                                                                                                                                    RadioGroup radioGroup4 = (RadioGroup) rootView.findViewById(R.id.rg_reason);
                                                                                                                                                                                                    if (radioGroup4 != null) {
                                                                                                                                                                                                        i = R.id.save_btn;
                                                                                                                                                                                                        Button button = (Button) rootView.findViewById(R.id.save_btn);
                                                                                                                                                                                                        if (button != null) {
                                                                                                                                                                                                            i = R.id.school_layout;
                                                                                                                                                                                                            LinearLayout linearLayout20 = (LinearLayout) rootView.findViewById(R.id.school_layout);
                                                                                                                                                                                                            if (linearLayout20 != null) {
                                                                                                                                                                                                                i = R.id.schools_spinner;
                                                                                                                                                                                                                SearchableSpinner searchableSpinner = (SearchableSpinner) rootView.findViewById(R.id.schools_spinner);
                                                                                                                                                                                                                if (searchableSpinner != null) {
                                                                                                                                                                                                                    i = R.id.status_card;
                                                                                                                                                                                                                    CardView cardView = (CardView) rootView.findViewById(R.id.status_card);
                                                                                                                                                                                                                    if (cardView != null) {
                                                                                                                                                                                                                        i = R.id.tv_admin_response;
                                                                                                                                                                                                                        TextView textView5 = (TextView) rootView.findViewById(R.id.tv_admin_response);
                                                                                                                                                                                                                        if (textView5 != null) {
                                                                                                                                                                                                                            i = R.id.tv_attendance;
                                                                                                                                                                                                                            TextView textView6 = (TextView) rootView.findViewById(R.id.tv_attendance);
                                                                                                                                                                                                                            if (textView6 != null) {
                                                                                                                                                                                                                                i = R.id.tv_designation;
                                                                                                                                                                                                                                TextView textView7 = (TextView) rootView.findViewById(R.id.tv_designation);
                                                                                                                                                                                                                                if (textView7 != null) {
                                                                                                                                                                                                                                    i = R.id.tv_order_date;
                                                                                                                                                                                                                                    TextView textView8 = (TextView) rootView.findViewById(R.id.tv_order_date);
                                                                                                                                                                                                                                    if (textView8 != null) {
                                                                                                                                                                                                                                        i = R.id.tv_order_no;
                                                                                                                                                                                                                                        TextView textView9 = (TextView) rootView.findViewById(R.id.tv_order_no);
                                                                                                                                                                                                                                        if (textView9 != null) {
                                                                                                                                                                                                                                            i = R.id.tv_person_id;
                                                                                                                                                                                                                                            TextView textView10 = (TextView) rootView.findViewById(R.id.tv_person_id);
                                                                                                                                                                                                                                            if (textView10 != null) {
                                                                                                                                                                                                                                                i = R.id.tv_remarks;
                                                                                                                                                                                                                                                TextView textView11 = (TextView) rootView.findViewById(R.id.tv_remarks);
                                                                                                                                                                                                                                                if (textView11 != null) {
                                                                                                                                                                                                                                                    i = R.id.tv_retired_expired_date;
                                                                                                                                                                                                                                                    TextView textView12 = (TextView) rootView.findViewById(R.id.tv_retired_expired_date);
                                                                                                                                                                                                                                                    if (textView12 != null) {
                                                                                                                                                                                                                                                        i = R.id.tv_tap_image_view;
                                                                                                                                                                                                                                                        TextView textView13 = (TextView) rootView.findViewById(R.id.tv_tap_image_view);
                                                                                                                                                                                                                                                        if (textView13 != null) {
                                                                                                                                                                                                                                                            i = R.id.tv_ticket_status;
                                                                                                                                                                                                                                                            TextView textView14 = (TextView) rootView.findViewById(R.id.tv_ticket_status);
                                                                                                                                                                                                                                                            if (textView14 != null) {
                                                                                                                                                                                                                                                                return new FragmentAddEmployeeBinding((ScrollView) rootView, linearLayout, appointmentOrderPictureLayoutBindingBind, linearLayout2, linearLayout3, cnicPictureLayoutBindingBind, linearLayout4, textView, linearLayout5, linearLayout6, linearLayout7, employeePayslipLayoutBindingBind, employeePictureLayoutBindingBind, employeeThumbLayoutBindingBind, editText, editText2, editText3, editText4, editText5, editText6, editText7, linearLayout8, linearLayout9, linearLayout10, informationLetterLayoutBindingBind, textView2, linearLayout11, joiningReportLayoutBindingBind, linearLayout12, linearLayout13, offerOrderLayoutBindingBind, textView3, linearLayout14, linearLayout15, imageView, pictureAttendanceRegisterLayoutBindingBind, postingOrderPictureLayoutBindingBind, linearLayout16, linearLayout17, relativeLayout, relativeLayout2, linearLayout18, relativeLayout3, textView4, linearLayout19, radioGroup, radioGroup2, radioGroup3, radioGroup4, button, linearLayout20, searchableSpinner, cardView, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14);
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
