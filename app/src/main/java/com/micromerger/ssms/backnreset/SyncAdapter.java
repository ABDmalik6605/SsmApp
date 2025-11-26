package com.micromerger.ssms.backnreset;

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
import com.micromerger.ssms.user.beans.employeeData.Monitoring;
import java.util.List;

/* loaded from: classes2.dex */
public class SyncAdapter extends RecyclerView.Adapter<DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static List<EmployeeData> mDataset;
    private static MyClickListener myClickListener;

    public interface MyClickListener {
        void onItemClick(int position, View v, EmployeeData employeeData);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox check;
        TextView tv_date;
        TextView tv_end;
        TextView tv_name;
        TextView tv_semes;
        TextView tv_start;

        public DataObjectHolder(View itemView) {
            super(itemView);
            this.tv_semes = (TextView) itemView.findViewById(R.id.tv_semes);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            this.tv_start = (TextView) itemView.findViewById(R.id.tv_start);
            this.tv_end = (TextView) itemView.findViewById(R.id.tv_end);
            this.check = (CheckBox) itemView.findViewById(R.id.checked);
            Log.i(SyncAdapter.LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            SyncAdapter.myClickListener.onItemClick(getAdapterPosition(), v, (EmployeeData) SyncAdapter.mDataset.get(getAdapterPosition()));
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener2) {
        myClickListener = myClickListener2;
    }

    public SyncAdapter(List<EmployeeData> myDataset) {
        mDataset = myDataset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataObjectHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sync, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        Monitoring monitoring = mDataset.get(position).getMonitoring().get(0);
        holder.tv_semes.setText("SEMIS Code: " + mDataset.get(position).getSchoolSemisCode());
        holder.tv_name.setText("School Name: " + mDataset.get(position).getSchoolName());
        if (monitoring.getScheduledMoniteringDate() != null && monitoring.getScheduledMoniteringDate().length() > 0) {
            if (mDataset.get(position).isSync()) {
                holder.tv_date.setText("Date: " + monitoring.getScheduledMoniteringDate().replace(ExifInterface.GPS_DIRECTION_TRUE, " "));
            } else {
                holder.tv_date.setVisibility(8);
            }
        } else {
            holder.tv_date.setText("Date: - ");
        }
        if (monitoring.getMonitoringStartDate() != null && monitoring.getMonitoringStartDate().length() > 0) {
            holder.tv_start.setText("Activity Start Time: " + monitoring.getMonitoringStartDate().replace(ExifInterface.GPS_DIRECTION_TRUE, " "));
        } else {
            holder.tv_start.setText("Activity Start Time: - ");
        }
        if (monitoring.getMonitoringEndDate() != null && String.valueOf(monitoring.getMonitoringEndDate()).length() > 0) {
            holder.tv_end.setText("Activity End Time: " + String.valueOf(monitoring.getMonitoringEndDate()).replace(ExifInterface.GPS_DIRECTION_TRUE, " "));
        } else {
            holder.tv_end.setText("Activity End Time: - ");
        }
        holder.check.setChecked(mDataset.get(position).isSync());
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
        List<EmployeeData> list = mDataset;
        if (list != null) {
            return list.size();
        }
        return 0;
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
}
