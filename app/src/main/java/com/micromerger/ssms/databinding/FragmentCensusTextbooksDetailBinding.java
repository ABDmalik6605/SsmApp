package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragmentCensusTextbooksDetailBinding implements ViewBinding {
    public final Button cancelButton;
    public final RelativeLayout commentsLayout;
    public final EditText editTextComments;
    public final EditText etTextbooksDistributed;
    public final EditText etTextbooksRecieved;
    public final EditText etTextbooksRequested;
    public final EditText etTextbooksShortfalls;
    public final EditText etTextbooksStudents;
    public final EditText etTextbooksSurplus;
    public final RelativeLayout parentView;
    public final RadioButton rbNo;
    public final RadioButton rbYes;
    public final RadioGroup rgTimelyDistributed;
    private final RelativeLayout rootView;
    public final Button saveButton;
    public final Button skipButton;
    public final TextView tvComments;
    public final TextView tvTextbooksDetail;
    public final TextView tvTextbooksDistributed;
    public final TextView tvTextbooksRecieved;
    public final TextView tvTextbooksRequested;
    public final TextView tvTextbooksShortfalls;
    public final TextView tvTextbooksStudents;
    public final TextView tvTextbooksSurplus;
    public final TextView tvTimelyDistributed;

    private FragmentCensusTextbooksDetailBinding(RelativeLayout rootView, Button cancelButton, RelativeLayout commentsLayout, EditText editTextComments, EditText etTextbooksDistributed, EditText etTextbooksRecieved, EditText etTextbooksRequested, EditText etTextbooksShortfalls, EditText etTextbooksStudents, EditText etTextbooksSurplus, RelativeLayout parentView, RadioButton rbNo, RadioButton rbYes, RadioGroup rgTimelyDistributed, Button saveButton, Button skipButton, TextView tvComments, TextView tvTextbooksDetail, TextView tvTextbooksDistributed, TextView tvTextbooksRecieved, TextView tvTextbooksRequested, TextView tvTextbooksShortfalls, TextView tvTextbooksStudents, TextView tvTextbooksSurplus, TextView tvTimelyDistributed) {
        this.rootView = rootView;
        this.cancelButton = cancelButton;
        this.commentsLayout = commentsLayout;
        this.editTextComments = editTextComments;
        this.etTextbooksDistributed = etTextbooksDistributed;
        this.etTextbooksRecieved = etTextbooksRecieved;
        this.etTextbooksRequested = etTextbooksRequested;
        this.etTextbooksShortfalls = etTextbooksShortfalls;
        this.etTextbooksStudents = etTextbooksStudents;
        this.etTextbooksSurplus = etTextbooksSurplus;
        this.parentView = parentView;
        this.rbNo = rbNo;
        this.rbYes = rbYes;
        this.rgTimelyDistributed = rgTimelyDistributed;
        this.saveButton = saveButton;
        this.skipButton = skipButton;
        this.tvComments = tvComments;
        this.tvTextbooksDetail = tvTextbooksDetail;
        this.tvTextbooksDistributed = tvTextbooksDistributed;
        this.tvTextbooksRecieved = tvTextbooksRecieved;
        this.tvTextbooksRequested = tvTextbooksRequested;
        this.tvTextbooksShortfalls = tvTextbooksShortfalls;
        this.tvTextbooksStudents = tvTextbooksStudents;
        this.tvTextbooksSurplus = tvTextbooksSurplus;
        this.tvTimelyDistributed = tvTimelyDistributed;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCensusTextbooksDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCensusTextbooksDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_census_textbooks_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCensusTextbooksDetailBinding bind(View rootView) {
        int i = R.id.cancel_button;
        Button button = (Button) rootView.findViewById(R.id.cancel_button);
        if (button != null) {
            i = R.id.commentsLayout;
            RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.commentsLayout);
            if (relativeLayout != null) {
                i = R.id.editText_comments;
                EditText editText = (EditText) rootView.findViewById(R.id.editText_comments);
                if (editText != null) {
                    i = R.id.et_textbooks_distributed;
                    EditText editText2 = (EditText) rootView.findViewById(R.id.et_textbooks_distributed);
                    if (editText2 != null) {
                        i = R.id.et_textbooks_recieved;
                        EditText editText3 = (EditText) rootView.findViewById(R.id.et_textbooks_recieved);
                        if (editText3 != null) {
                            i = R.id.et_textbooks_requested;
                            EditText editText4 = (EditText) rootView.findViewById(R.id.et_textbooks_requested);
                            if (editText4 != null) {
                                i = R.id.et_textbooks_shortfalls;
                                EditText editText5 = (EditText) rootView.findViewById(R.id.et_textbooks_shortfalls);
                                if (editText5 != null) {
                                    i = R.id.et_textbooks_students;
                                    EditText editText6 = (EditText) rootView.findViewById(R.id.et_textbooks_students);
                                    if (editText6 != null) {
                                        i = R.id.et_textbooks_surplus;
                                        EditText editText7 = (EditText) rootView.findViewById(R.id.et_textbooks_surplus);
                                        if (editText7 != null) {
                                            RelativeLayout relativeLayout2 = (RelativeLayout) rootView;
                                            i = R.id.rb_no;
                                            RadioButton radioButton = (RadioButton) rootView.findViewById(R.id.rb_no);
                                            if (radioButton != null) {
                                                i = R.id.rb_yes;
                                                RadioButton radioButton2 = (RadioButton) rootView.findViewById(R.id.rb_yes);
                                                if (radioButton2 != null) {
                                                    i = R.id.rg_timely_distributed;
                                                    RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_timely_distributed);
                                                    if (radioGroup != null) {
                                                        i = R.id.save_button;
                                                        Button button2 = (Button) rootView.findViewById(R.id.save_button);
                                                        if (button2 != null) {
                                                            i = R.id.skip_button;
                                                            Button button3 = (Button) rootView.findViewById(R.id.skip_button);
                                                            if (button3 != null) {
                                                                i = R.id.tv_comments;
                                                                TextView textView = (TextView) rootView.findViewById(R.id.tv_comments);
                                                                if (textView != null) {
                                                                    i = R.id.tv_textbooks_detail;
                                                                    TextView textView2 = (TextView) rootView.findViewById(R.id.tv_textbooks_detail);
                                                                    if (textView2 != null) {
                                                                        i = R.id.tv_textbooks_distributed;
                                                                        TextView textView3 = (TextView) rootView.findViewById(R.id.tv_textbooks_distributed);
                                                                        if (textView3 != null) {
                                                                            i = R.id.tv_textbooks_recieved;
                                                                            TextView textView4 = (TextView) rootView.findViewById(R.id.tv_textbooks_recieved);
                                                                            if (textView4 != null) {
                                                                                i = R.id.tv_textbooks_requested;
                                                                                TextView textView5 = (TextView) rootView.findViewById(R.id.tv_textbooks_requested);
                                                                                if (textView5 != null) {
                                                                                    i = R.id.tv_textbooks_shortfalls;
                                                                                    TextView textView6 = (TextView) rootView.findViewById(R.id.tv_textbooks_shortfalls);
                                                                                    if (textView6 != null) {
                                                                                        i = R.id.tv_textbooks_students;
                                                                                        TextView textView7 = (TextView) rootView.findViewById(R.id.tv_textbooks_students);
                                                                                        if (textView7 != null) {
                                                                                            i = R.id.tv_textbooks_surplus;
                                                                                            TextView textView8 = (TextView) rootView.findViewById(R.id.tv_textbooks_surplus);
                                                                                            if (textView8 != null) {
                                                                                                i = R.id.tv_timely_distributed;
                                                                                                TextView textView9 = (TextView) rootView.findViewById(R.id.tv_timely_distributed);
                                                                                                if (textView9 != null) {
                                                                                                    return new FragmentCensusTextbooksDetailBinding(relativeLayout2, button, relativeLayout, editText, editText2, editText3, editText4, editText5, editText6, editText7, relativeLayout2, radioButton, radioButton2, radioGroup, button2, button3, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9);
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
