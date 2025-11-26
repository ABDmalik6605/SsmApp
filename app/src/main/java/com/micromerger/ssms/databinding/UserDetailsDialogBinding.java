package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class UserDetailsDialogBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView userDetailsCreateDate;
    public final TextView userDetailsCreateDateText;
    public final TextView userDetailsDescription;
    public final TextView userDetailsDescriptionText;
    public final TextView userDetailsMatchScore;
    public final TextView userDetailsMatchScoreText;
    public final TextView userDetailsModifyDate;
    public final TextView userDetailsModifyDateText;
    public final TextView userDetailsName;
    public final TextView userDetailsNameText;

    private UserDetailsDialogBinding(LinearLayout rootView, TextView userDetailsCreateDate, TextView userDetailsCreateDateText, TextView userDetailsDescription, TextView userDetailsDescriptionText, TextView userDetailsMatchScore, TextView userDetailsMatchScoreText, TextView userDetailsModifyDate, TextView userDetailsModifyDateText, TextView userDetailsName, TextView userDetailsNameText) {
        this.rootView = rootView;
        this.userDetailsCreateDate = userDetailsCreateDate;
        this.userDetailsCreateDateText = userDetailsCreateDateText;
        this.userDetailsDescription = userDetailsDescription;
        this.userDetailsDescriptionText = userDetailsDescriptionText;
        this.userDetailsMatchScore = userDetailsMatchScore;
        this.userDetailsMatchScoreText = userDetailsMatchScoreText;
        this.userDetailsModifyDate = userDetailsModifyDate;
        this.userDetailsModifyDateText = userDetailsModifyDateText;
        this.userDetailsName = userDetailsName;
        this.userDetailsNameText = userDetailsNameText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static UserDetailsDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static UserDetailsDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.user_details_dialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static UserDetailsDialogBinding bind(View rootView) {
        int i = R.id.user_details_create_date;
        TextView textView = (TextView) rootView.findViewById(R.id.user_details_create_date);
        if (textView != null) {
            i = R.id.user_details_create_date_text;
            TextView textView2 = (TextView) rootView.findViewById(R.id.user_details_create_date_text);
            if (textView2 != null) {
                i = R.id.user_details_description;
                TextView textView3 = (TextView) rootView.findViewById(R.id.user_details_description);
                if (textView3 != null) {
                    i = R.id.user_details_description_text;
                    TextView textView4 = (TextView) rootView.findViewById(R.id.user_details_description_text);
                    if (textView4 != null) {
                        i = R.id.user_details_match_score;
                        TextView textView5 = (TextView) rootView.findViewById(R.id.user_details_match_score);
                        if (textView5 != null) {
                            i = R.id.user_details_match_score_text;
                            TextView textView6 = (TextView) rootView.findViewById(R.id.user_details_match_score_text);
                            if (textView6 != null) {
                                i = R.id.user_details_modify_date;
                                TextView textView7 = (TextView) rootView.findViewById(R.id.user_details_modify_date);
                                if (textView7 != null) {
                                    i = R.id.user_details_modify_date_text;
                                    TextView textView8 = (TextView) rootView.findViewById(R.id.user_details_modify_date_text);
                                    if (textView8 != null) {
                                        i = R.id.user_details_name;
                                        TextView textView9 = (TextView) rootView.findViewById(R.id.user_details_name);
                                        if (textView9 != null) {
                                            i = R.id.user_details_name_text;
                                            TextView textView10 = (TextView) rootView.findViewById(R.id.user_details_name_text);
                                            if (textView10 != null) {
                                                return new UserDetailsDialogBinding((LinearLayout) rootView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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
