package com.micromerger.ssms.startmonitoring.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.startmonitoring.bean.School;
import com.micromerger.ssms.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class MonitoringSchoolsAdapter extends RecyclerView.Adapter<DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static List<School> mDataset = new ArrayList();
    private static MyClickListener myClickListener;
    Activity act;

    public interface MyClickListener {
        void onItemClick(int position, View v, School school);
    }

    public MonitoringSchoolsAdapter() {
    }

    public MonitoringSchoolsAdapter(Activity act) {
        this.act = act;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView card_view;
        CheckBox cb_school_is_checked;
        TextView tv_last_monitored;
        TextView tv_school_id;
        TextView tv_school_title;

        public DataObjectHolder(View itemView) {
            super(itemView);
            this.tv_school_title = (TextView) itemView.findViewById(R.id.tv_school_title);
            this.tv_last_monitored = (TextView) itemView.findViewById(R.id.tv_last_monitored);
            this.tv_school_id = (TextView) itemView.findViewById(R.id.tv_school_id);
            this.cb_school_is_checked = (CheckBox) itemView.findViewById(R.id.cb_school_is_checked);
            this.card_view = (CardView) itemView.findViewById(R.id.card_view);
            itemView.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            MonitoringSchoolsAdapter.myClickListener.onItemClick(getAdapterPosition(), v, (School) MonitoringSchoolsAdapter.mDataset.get(getAdapterPosition()));
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener2) {
        myClickListener = myClickListener2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataObjectHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_start_monitoring, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        School school = mDataset.get(position);
        holder.tv_school_title.setText(school.getSchoolName());
        if (school.getSchoolPrefix() != null) {
            holder.tv_school_title.setText(school.getSchoolPrefix() + " - " + school.getSchoolName());
        }
        if (school.getIsMonitoringComplete().booleanValue()) {
            holder.tv_last_monitored.setVisibility(0);
            holder.tv_last_monitored.setText(school.getMonitoringEndDate().toString().replace(ExifInterface.GPS_DIRECTION_TRUE, " "));
        } else {
            holder.tv_last_monitored.setVisibility(8);
            holder.tv_last_monitored.setText("");
        }
        holder.tv_school_id.setText(school.getSchoolSemisCode());
        holder.cb_school_is_checked.setChecked(school.getIsMonitoringComplete().booleanValue());
        holder.cb_school_is_checked.setEnabled(false);
        if (school.isDownloaded() == null || !school.isDownloaded().booleanValue()) {
            holder.card_view.setBackgroundColor(this.act.getResources().getColor(R.color.light_gray));
        } else {
            holder.card_view.setBackgroundColor(this.act.getResources().getColor(R.color.white));
        }
    }

    public void addItem(School dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return mDataset.size();
    }

    public void addData(School newModelData, int position) {
        mDataset.add(position, newModelData);
        notifyItemInserted(position);
    }

    public void addData(School newModelData) {
        mDataset.add(newModelData);
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public void clearData() {
        mDataset.clear();
        notifyDataSetChanged();
    }

    public void updateList(List<School> list) {
        ArrayList arrayList = new ArrayList();
        for (School school : list) {
            if ((school.getMonitoringStartDate() != null && !school.getIsMonitoringComplete().booleanValue()) || Utils.isSameCurrentMonth(school.getScheduledDate())) {
                arrayList.add(school);
            }
        }
        mDataset = arrayList;
        notifyDataSetChanged();
    }

    public void sortList() {
        Collections.sort(mDataset, new Comparator<School>() { // from class: com.micromerger.ssms.startmonitoring.adapters.MonitoringSchoolsAdapter.1
            @Override // java.util.Comparator
            public int compare(School s1, School s2) {
                Boolean isMonitoringComplete = s1.getIsMonitoringComplete();
                Boolean isMonitoringComplete2 = s2.getIsMonitoringComplete();
                if (!isMonitoringComplete.booleanValue() || isMonitoringComplete2.booleanValue()) {
                    return (isMonitoringComplete.booleanValue() || !isMonitoringComplete2.booleanValue()) ? 0 : -1;
                }
                return 1;
            }
        });
        notifyDataSetChanged();
    }
}
