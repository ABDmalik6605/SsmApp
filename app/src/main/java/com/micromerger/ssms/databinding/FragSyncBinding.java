package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragSyncBinding implements ViewBinding {
    public final ImageView ivRefresh;
    public final LinearLayout llSearchBar;
    public final RecyclerView recyclerview;
    private final LinearLayout rootView;
    public final ProgressBar syncProgress;
    public final TextView syncText;
    public final TextView tvTitle;

    private FragSyncBinding(LinearLayout rootView, ImageView ivRefresh, LinearLayout llSearchBar, RecyclerView recyclerview, ProgressBar syncProgress, TextView syncText, TextView tvTitle) {
        this.rootView = rootView;
        this.ivRefresh = ivRefresh;
        this.llSearchBar = llSearchBar;
        this.recyclerview = recyclerview;
        this.syncProgress = syncProgress;
        this.syncText = syncText;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragSyncBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragSyncBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_sync, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragSyncBinding bind(View rootView) {
        int i = R.id.iv_refresh;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_refresh);
        if (imageView != null) {
            i = R.id.ll_search_bar;
            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_search_bar);
            if (linearLayout != null) {
                i = R.id.recyclerview;
                RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
                if (recyclerView != null) {
                    i = R.id.sync_progress;
                    ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.sync_progress);
                    if (progressBar != null) {
                        i = R.id.sync_text;
                        TextView textView = (TextView) rootView.findViewById(R.id.sync_text);
                        if (textView != null) {
                            i = R.id.tv_title;
                            TextView textView2 = (TextView) rootView.findViewById(R.id.tv_title);
                            if (textView2 != null) {
                                return new FragSyncBinding((LinearLayout) rootView, imageView, linearLayout, recyclerView, progressBar, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
