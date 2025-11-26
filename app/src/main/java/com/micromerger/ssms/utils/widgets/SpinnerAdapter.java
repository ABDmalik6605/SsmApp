package com.micromerger.ssms.utils.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.main.beans.ReferenceDataResponse;
import java.util.List;

/* loaded from: classes2.dex */
public class SpinnerAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflter;
    List<ReferenceDataResponse.ReferenceData> mData;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public SpinnerAdapter(Context applicationContext, List<ReferenceDataResponse.ReferenceData> mData) {
        this.context = applicationContext;
        this.mData = mData;
        this.inflter = LayoutInflater.from(applicationContext);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mData.size();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewInflate = this.inflter.inflate(R.layout.custom_spinner_items, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.textView)).setText(this.mData.get(i).getReferencevalue());
        return viewInflate;
    }
}
