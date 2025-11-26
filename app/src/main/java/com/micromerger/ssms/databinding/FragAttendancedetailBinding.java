package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public final class FragAttendancedetailBinding implements ViewBinding {
    public final Button btnMarkAbsent;
    public final EditText editTextComments;
    public final EditText etTransferredSemis;
    public final RelativeLayout finger;
    public final TextView fromDate;
    public final LinearLayout llDutyType;
    public final LinearLayout llFromToDate;
    public final LinearLayout llLeaveApprovalType;
    public final LinearLayout llLeaveType;
    public final LinearLayout parentView;
    public final ImageView previewImage;
    public final RadioButton rbNo;
    public final RadioButton rbYes;
    public final RadioGroup rgMsgIlmi;
    public final RadioGroup rgVerifyAbsconder;
    public final LinearLayout rlIlmi;
    private final LinearLayout rootView;
    public final Spinner spDutyType;
    public final Spinner spLeaveApprovalType;
    public final Spinner spLeaveType;
    public final Spinner spReasons;
    public final TextView toDate;
    public final RelativeLayout transferredSemisLayout;
    public final TextView tvAvailabilityy;
    public final TextView tvComments;
    public final TextView tvDutyType;
    public final TextView tvFromDate;
    public final TextView tvLeaveType;
    public final TextView tvMsgIlmi;
    public final TextView tvTextbooksDetail;
    public final TextView tvToDate;
    public final TextView tvTransferredSemis;
    public final TextView tvVerifyAbsconder;
    public final ImageView uploadImage;
    public final LinearLayout verifyAbsconderLayout;

    private FragAttendancedetailBinding(LinearLayout rootView, Button btnMarkAbsent, EditText editTextComments, EditText etTransferredSemis, RelativeLayout finger, TextView fromDate, LinearLayout llDutyType, LinearLayout llFromToDate, LinearLayout llLeaveApprovalType, LinearLayout llLeaveType, LinearLayout parentView, ImageView previewImage, RadioButton rbNo, RadioButton rbYes, RadioGroup rgMsgIlmi, RadioGroup rgVerifyAbsconder, LinearLayout rlIlmi, Spinner spDutyType, Spinner spLeaveApprovalType, Spinner spLeaveType, Spinner spReasons, TextView toDate, RelativeLayout transferredSemisLayout, TextView tvAvailabilityy, TextView tvComments, TextView tvDutyType, TextView tvFromDate, TextView tvLeaveType, TextView tvMsgIlmi, TextView tvTextbooksDetail, TextView tvToDate, TextView tvTransferredSemis, TextView tvVerifyAbsconder, ImageView uploadImage, LinearLayout verifyAbsconderLayout) {
        this.rootView = rootView;
        this.btnMarkAbsent = btnMarkAbsent;
        this.editTextComments = editTextComments;
        this.etTransferredSemis = etTransferredSemis;
        this.finger = finger;
        this.fromDate = fromDate;
        this.llDutyType = llDutyType;
        this.llFromToDate = llFromToDate;
        this.llLeaveApprovalType = llLeaveApprovalType;
        this.llLeaveType = llLeaveType;
        this.parentView = parentView;
        this.previewImage = previewImage;
        this.rbNo = rbNo;
        this.rbYes = rbYes;
        this.rgMsgIlmi = rgMsgIlmi;
        this.rgVerifyAbsconder = rgVerifyAbsconder;
        this.rlIlmi = rlIlmi;
        this.spDutyType = spDutyType;
        this.spLeaveApprovalType = spLeaveApprovalType;
        this.spLeaveType = spLeaveType;
        this.spReasons = spReasons;
        this.toDate = toDate;
        this.transferredSemisLayout = transferredSemisLayout;
        this.tvAvailabilityy = tvAvailabilityy;
        this.tvComments = tvComments;
        this.tvDutyType = tvDutyType;
        this.tvFromDate = tvFromDate;
        this.tvLeaveType = tvLeaveType;
        this.tvMsgIlmi = tvMsgIlmi;
        this.tvTextbooksDetail = tvTextbooksDetail;
        this.tvToDate = tvToDate;
        this.tvTransferredSemis = tvTransferredSemis;
        this.tvVerifyAbsconder = tvVerifyAbsconder;
        this.uploadImage = uploadImage;
        this.verifyAbsconderLayout = verifyAbsconderLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragAttendancedetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragAttendancedetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_attendancedetail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragAttendancedetailBinding bind(View rootView) {
        int i = R.id.btn_mark_absent;
        Button button = (Button) rootView.findViewById(R.id.btn_mark_absent);
        if (button != null) {
            i = R.id.editText_comments;
            EditText editText = (EditText) rootView.findViewById(R.id.editText_comments);
            if (editText != null) {
                i = R.id.et_transferred_semis;
                EditText editText2 = (EditText) rootView.findViewById(R.id.et_transferred_semis);
                if (editText2 != null) {
                    i = R.id.finger;
                    RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.finger);
                    if (relativeLayout != null) {
                        i = R.id.from_date;
                        TextView textView = (TextView) rootView.findViewById(R.id.from_date);
                        if (textView != null) {
                            i = R.id.ll_duty_type;
                            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_duty_type);
                            if (linearLayout != null) {
                                i = R.id.ll_from_to_date;
                                LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.ll_from_to_date);
                                if (linearLayout2 != null) {
                                    i = R.id.ll_leave_approval_type;
                                    LinearLayout linearLayout3 = (LinearLayout) rootView.findViewById(R.id.ll_leave_approval_type);
                                    if (linearLayout3 != null) {
                                        i = R.id.ll_leave_type;
                                        LinearLayout linearLayout4 = (LinearLayout) rootView.findViewById(R.id.ll_leave_type);
                                        if (linearLayout4 != null) {
                                            LinearLayout linearLayout5 = (LinearLayout) rootView;
                                            i = R.id.previewImage;
                                            ImageView imageView = (ImageView) rootView.findViewById(R.id.previewImage);
                                            if (imageView != null) {
                                                i = R.id.rb_no;
                                                RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_no);
                                                if (radioButton != null) {
                                                    i = R.id.rb_yes;
                                                    RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_yes);
                                                    if (radioButton2 != null) {
                                                        i = R.id.rg_msg_ilmi;
                                                        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_msg_ilmi);
                                                        if (radioGroup != null) {
                                                            i = R.id.rg_verify_absconder;
                                                            RadioGroup radioGroup2 = (RadioGroup) rootView.findViewById(R.id.rg_verify_absconder);
                                                            if (radioGroup2 != null) {
                                                                i = R.id.rl_ilmi;
                                                                LinearLayout linearLayout6 = (LinearLayout) rootView.findViewById(R.id.rl_ilmi);
                                                                if (linearLayout6 != null) {
                                                                    i = R.id.sp_duty_type;
                                                                    Spinner spinner = (Spinner) rootView.findViewById(R.id.sp_duty_type);
                                                                    if (spinner != null) {
                                                                        i = R.id.sp_leave_approval_type;
                                                                        Spinner spinner2 = (Spinner) rootView.findViewById(R.id.sp_leave_approval_type);
                                                                        if (spinner2 != null) {
                                                                            i = R.id.sp_leave_type;
                                                                            Spinner spinner3 = (Spinner) rootView.findViewById(R.id.sp_leave_type);
                                                                            if (spinner3 != null) {
                                                                                i = R.id.sp_reasons;
                                                                                Spinner spinner4 = (Spinner) rootView.findViewById(R.id.sp_reasons);
                                                                                if (spinner4 != null) {
                                                                                    i = R.id.to_date;
                                                                                    TextView textView2 = (TextView) rootView.findViewById(R.id.to_date);
                                                                                    if (textView2 != null) {
                                                                                        i = R.id.transferred_semis_layout;
                                                                                        RelativeLayout relativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.transferred_semis_layout);
                                                                                        if (relativeLayout2 != null) {
                                                                                            i = R.id.tv_availabilityy;
                                                                                            TextView textView3 = (TextView) rootView.findViewById(R.id.tv_availabilityy);
                                                                                            if (textView3 != null) {
                                                                                                i = R.id.tv_comments;
                                                                                                TextView textView4 = (TextView) rootView.findViewById(R.id.tv_comments);
                                                                                                if (textView4 != null) {
                                                                                                    i = R.id.tv_duty_type;
                                                                                                    TextView textView5 = (TextView) rootView.findViewById(R.id.tv_duty_type);
                                                                                                    if (textView5 != null) {
                                                                                                        i = R.id.tv_from_date;
                                                                                                        TextView textView6 = (TextView) rootView.findViewById(R.id.tv_from_date);
                                                                                                        if (textView6 != null) {
                                                                                                            i = R.id.tv_leave_type;
                                                                                                            TextView textView7 = (TextView) rootView.findViewById(R.id.tv_leave_type);
                                                                                                            if (textView7 != null) {
                                                                                                                i = R.id.tv_msg_ilmi;
                                                                                                                TextView textView8 = (TextView) rootView.findViewById(R.id.tv_msg_ilmi);
                                                                                                                if (textView8 != null) {
                                                                                                                    i = R.id.tv_textbooks_detail;
                                                                                                                    TextView textView9 = (TextView) rootView.findViewById(R.id.tv_textbooks_detail);
                                                                                                                    if (textView9 != null) {
                                                                                                                        i = R.id.tv_to_date;
                                                                                                                        TextView textView10 = (TextView) rootView.findViewById(R.id.tv_to_date);
                                                                                                                        if (textView10 != null) {
                                                                                                                            i = R.id.tv_transferred_semis;
                                                                                                                            TextView textView11 = (TextView) rootView.findViewById(R.id.tv_transferred_semis);
                                                                                                                            if (textView11 != null) {
                                                                                                                                i = R.id.tv_verify_absconder;
                                                                                                                                TextView textView12 = (TextView) rootView.findViewById(R.id.tv_verify_absconder);
                                                                                                                                if (textView12 != null) {
                                                                                                                                    i = R.id.uploadImage;
                                                                                                                                    ImageView imageView2 = (ImageView) rootView.findViewById(R.id.uploadImage);
                                                                                                                                    if (imageView2 != null) {
                                                                                                                                        i = R.id.verify_absconder_layout;
                                                                                                                                        LinearLayout linearLayout7 = (LinearLayout) rootView.findViewById(R.id.verify_absconder_layout);
                                                                                                                                        if (linearLayout7 != null) {
                                                                                                                                            return new FragAttendancedetailBinding(linearLayout5, button, editText, editText2, relativeLayout, textView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, imageView, radioButton, radioButton2, radioGroup, radioGroup2, linearLayout6, spinner, spinner2, spinner3, spinner4, textView2, relativeLayout2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, imageView2, linearLayout7);
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
