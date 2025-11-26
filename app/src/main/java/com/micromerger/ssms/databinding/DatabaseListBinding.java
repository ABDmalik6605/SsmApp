package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class DatabaseListBinding implements ViewBinding {
    public final Button databaseListClear;
    public final Button databaseListDone;
    public final TextView databaseListSize;
    public final TextView databaseListSizeText;
    public final TextView databaseListTitle;
    public final ListView list;
    private final LinearLayout rootView;

    private DatabaseListBinding(LinearLayout rootView, Button databaseListClear, Button databaseListDone, TextView databaseListSize, TextView databaseListSizeText, TextView databaseListTitle, ListView list) {
        this.rootView = rootView;
        this.databaseListClear = databaseListClear;
        this.databaseListDone = databaseListDone;
        this.databaseListSize = databaseListSize;
        this.databaseListSizeText = databaseListSizeText;
        this.databaseListTitle = databaseListTitle;
        this.list = list;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DatabaseListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DatabaseListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.database_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DatabaseListBinding bind(View rootView) {
        int i = R.id.database_list_clear;
        Button button = (Button) rootView.findViewById(R.id.database_list_clear);
        if (button != null) {
            i = R.id.database_list_done;
            Button button2 = (Button) rootView.findViewById(R.id.database_list_done);
            if (button2 != null) {
                i = R.id.database_list_size;
                TextView textView = (TextView) rootView.findViewById(R.id.database_list_size);
                if (textView != null) {
                    i = R.id.database_list_size_text;
                    TextView textView2 = (TextView) rootView.findViewById(R.id.database_list_size_text);
                    if (textView2 != null) {
                        i = R.id.database_list_title;
                        TextView textView3 = (TextView) rootView.findViewById(R.id.database_list_title);
                        if (textView3 != null) {
                            i = android.R.id.list;
                            ListView listView = (ListView) rootView.findViewById(android.R.id.list);
                            if (listView != null) {
                                return new DatabaseListBinding((LinearLayout) rootView, button, button2, textView, textView2, textView3, listView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
