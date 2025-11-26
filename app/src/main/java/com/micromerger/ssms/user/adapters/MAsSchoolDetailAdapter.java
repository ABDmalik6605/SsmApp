package com.micromerger.ssms.user.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.user.beans.maSchoolDetail.Data;
import java.util.List;

/* loaded from: classes2.dex */
public class MAsSchoolDetailAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Data> mItems;
    private int position;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return 0;
    }

    public MAsSchoolDetailAdapter(List<Data> items) {
        this.mItems = items;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int view_type) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ma_school_detail, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        String strReplace;
        String str;
        Data data = this.mItems.get(position);
        strReplace = "-";
        if (data.getMonitoring().size() > 0) {
            String strReplace2 = data.getMonitoring().get(0).getMonitoringStartDate() != null ? data.getMonitoring().get(0).getMonitoringStartDate().replace(ExifInterface.GPS_DIRECTION_TRUE, " ") : "-";
            strReplace = data.getMonitoring().get(0).getMonitoringEndDate() != null ? data.getMonitoring().get(0).getMonitoringEndDate().replace(ExifInterface.GPS_DIRECTION_TRUE, " ") : "-";
            zBooleanValue = data.getMonitoring().get(0).getIsMonitoringComplete() != null ? data.getMonitoring().get(0).getIsMonitoringComplete().booleanValue() : false;
            String str2 = strReplace;
            strReplace = strReplace2;
            str = str2;
        } else {
            str = "-";
        }
        if (data.getSchoolName() != null) {
            viewHolder.tv_shool_name.setText(data.getSchoolName());
        }
        if (strReplace != null) {
            viewHolder.tv_m_start_time.setText(strReplace);
        }
        if (str != null) {
            viewHolder.tv_m_end_date.setText(str);
        }
        if (data.getSchoolSemisCode() != null) {
            viewHolder.tv_semisCodeScheoolDetail.setText(data.getSchoolSemisCode());
        }
        viewHolder.check.setChecked(zBooleanValue);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItems.size();
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox check;
        View rootView;
        TextView tv_m_end_date;
        TextView tv_m_start_time;
        TextView tv_semisCodeScheoolDetail;
        TextView tv_shool_name;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }

        ViewHolder(View v) {
            super(v);
            this.tv_shool_name = (TextView) v.findViewById(R.id.tv_shool_name);
            this.tv_m_start_time = (TextView) v.findViewById(R.id.tv_m_start_time);
            this.tv_m_end_date = (TextView) v.findViewById(R.id.tv_m_end_date);
            this.tv_semisCodeScheoolDetail = (TextView) v.findViewById(R.id.tv_semisCodeScheoolDetail);
            this.check = (CheckBox) v.findViewById(R.id.checked);
            v.setOnClickListener(this);
            this.rootView = v;
        }
    }

    public void updateList(List<Data> list) {
        this.mItems = list;
        notifyDataSetChanged();
    }

    public Data getItem(int position) {
        return this.mItems.get(position);
    }

    public void addData(Data newData, int position) {
        this.mItems.add(position, newData);
        notifyItemInserted(position);
    }

    public void addData(Data newData) {
        this.mItems.add(newData);
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        this.mItems.remove(position);
        notifyItemRemoved(position);
    }

    public void clearData() {
        this.mItems.clear();
        notifyDataSetChanged();
    }
}
