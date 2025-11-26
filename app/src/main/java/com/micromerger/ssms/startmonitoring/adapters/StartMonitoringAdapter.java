package com.micromerger.ssms.startmonitoring.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.user.beans.employeeData.EmployeeData;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class StartMonitoringAdapter extends RecyclerView.Adapter<DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static List<EmployeeData> mDataset;
    private static MyClickListener myClickListener;

    public interface MyClickListener {
        void onItemClick(int position, View v, EmployeeData employeeData);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            Log.i(StartMonitoringAdapter.LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            StartMonitoringAdapter.myClickListener.onItemClick(getAdapterPosition(), v, (EmployeeData) StartMonitoringAdapter.mDataset.get(getAdapterPosition()));
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener2) {
        myClickListener = myClickListener2;
    }

    public StartMonitoringAdapter(List<EmployeeData> myDataset) {
        mDataset = myDataset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataObjectHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_start_monitoring, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        EmployeeData employeeData = mDataset.get(position);
        holder.tv_school_title.setText(employeeData.getSchoolName());
        if (employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue()) {
            holder.tv_last_monitored.setVisibility(0);
            holder.tv_last_monitored.setText(employeeData.getMonitoring().get(0).getMonitoringEndDate().toString().replace(ExifInterface.GPS_DIRECTION_TRUE, " "));
        } else {
            holder.tv_last_monitored.setVisibility(8);
            holder.tv_last_monitored.setText("");
        }
        holder.tv_school_id.setText(employeeData.getSchoolSemisCode());
        holder.cb_school_is_checked.setChecked(employeeData.getMonitoring().get(0).getIsMonitoringComplete().booleanValue());
        holder.cb_school_is_checked.setEnabled(false);
    }

    public void addItem(EmployeeData dataObj, int index) {
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

    public void addData(EmployeeData newModelData, int position) {
        mDataset.add(position, newModelData);
        notifyItemInserted(position);
    }

    public void addData(EmployeeData newModelData) {
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

    public void updateList(List<EmployeeData> list) {
        mDataset = list;
        notifyDataSetChanged();
    }

    public void sortList() {
        Collections.sort(mDataset, new Comparator<EmployeeData>() { // from class: com.micromerger.ssms.startmonitoring.adapters.StartMonitoringAdapter.1
            @Override // java.util.Comparator
            public int compare(EmployeeData ed1, EmployeeData ed2) {
                Boolean isMonitoringComplete = ed1.getMonitoring().get(0).getIsMonitoringComplete();
                Boolean isMonitoringComplete2 = ed2.getMonitoring().get(0).getIsMonitoringComplete();
                if (!isMonitoringComplete.booleanValue() || isMonitoringComplete2.booleanValue()) {
                    return (isMonitoringComplete.booleanValue() || !isMonitoringComplete2.booleanValue()) ? 0 : -1;
                }
                return 1;
            }
        });
        notifyDataSetChanged();
    }
}
