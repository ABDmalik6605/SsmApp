package com.micromerger.ssms.startmonitoring.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import java.util.List;

/* loaded from: classes2.dex */
public class UnsyncSchoolDataAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<String> schoolList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb_school_is_checked;
        TextView tv_last_monitored;
        TextView tv_school_id;
        public TextView tv_school_title;

        public MyViewHolder(View view) {
            super(view);
            this.tv_school_title = (TextView) this.itemView.findViewById(R.id.tv_school_title);
            this.tv_last_monitored = (TextView) this.itemView.findViewById(R.id.tv_last_monitored);
            this.tv_school_id = (TextView) this.itemView.findViewById(R.id.tv_school_id);
            this.cb_school_is_checked = (CheckBox) this.itemView.findViewById(R.id.cb_school_is_checked);
        }
    }

    public UnsyncSchoolDataAdapter(Context context, List<String> schoolList) {
        this.context = context;
        this.schoolList = schoolList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_start_monitoring, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_school_title.setText(this.schoolList.get(position));
        holder.tv_last_monitored.setVisibility(8);
        holder.tv_school_id.setVisibility(8);
        holder.cb_school_is_checked.setVisibility(8);
        holder.cb_school_is_checked.setVisibility(8);
        holder.cb_school_is_checked.setChecked(false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.schoolList.size();
    }
}
