package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class DatabaseListItemBinding implements ViewBinding {
    public final TextView databaseCreate;
    public final TextView databaseCreateText;
    public final TextView databaseDescription;
    public final TextView databaseDescriptionText;
    public final TextView databaseModify;
    public final TextView databaseModifyText;
    public final TextView databaseName;
    private final LinearLayout rootView;

    private DatabaseListItemBinding(LinearLayout rootView, TextView databaseCreate, TextView databaseCreateText, TextView databaseDescription, TextView databaseDescriptionText, TextView databaseModify, TextView databaseModifyText, TextView databaseName) {
        this.rootView = rootView;
        this.databaseCreate = databaseCreate;
        this.databaseCreateText = databaseCreateText;
        this.databaseDescription = databaseDescription;
        this.databaseDescriptionText = databaseDescriptionText;
        this.databaseModify = databaseModify;
        this.databaseModifyText = databaseModifyText;
        this.databaseName = databaseName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DatabaseListItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DatabaseListItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.database_list_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DatabaseListItemBinding bind(View rootView) {
        int i = R.id.database_create;
        TextView textView = (TextView) rootView.findViewById(R.id.database_create);
        if (textView != null) {
            i = R.id.database_create_text;
            TextView textView2 = (TextView) rootView.findViewById(R.id.database_create_text);
            if (textView2 != null) {
                i = R.id.database_description;
                TextView textView3 = (TextView) rootView.findViewById(R.id.database_description);
                if (textView3 != null) {
                    i = R.id.database_description_text;
                    TextView textView4 = (TextView) rootView.findViewById(R.id.database_description_text);
                    if (textView4 != null) {
                        i = R.id.database_modify;
                        TextView textView5 = (TextView) rootView.findViewById(R.id.database_modify);
                        if (textView5 != null) {
                            i = R.id.database_modify_text;
                            TextView textView6 = (TextView) rootView.findViewById(R.id.database_modify_text);
                            if (textView6 != null) {
                                i = R.id.database_name;
                                TextView textView7 = (TextView) rootView.findViewById(R.id.database_name);
                                if (textView7 != null) {
                                    return new DatabaseListItemBinding((LinearLayout) rootView, textView, textView2, textView3, textView4, textView5, textView6, textView7);
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
