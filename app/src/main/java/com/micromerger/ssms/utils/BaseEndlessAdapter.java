package com.micromerger.ssms.utils;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import com.rockerhieu.rvadapter.endless.EndlessRecyclerViewAdapter;

/* loaded from: classes2.dex */
public class BaseEndlessAdapter extends EndlessRecyclerViewAdapter {
    public BaseEndlessAdapter(Context context, RecyclerView.Adapter wrapped, EndlessRecyclerViewAdapter.RequestToLoadMoreListener requestToLoadMoreListener) {
        super(context, wrapped, requestToLoadMoreListener, R.layout.custom_item_loading, true);
    }
}
