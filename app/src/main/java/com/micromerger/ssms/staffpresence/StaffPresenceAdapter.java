package com.micromerger.ssms.staffpresence;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.staffpresence.StaffPresenceAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class StaffPresenceAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private static DeleteClickListener deleteClickListener;
    private static ItemClickListener itemClickListener;
    private static List<StaffPresenceModel> staffPresenceModelList = new ArrayList();
    Activity activity;

    public interface DeleteClickListener {
        void onDeleteClick(int position, View v, StaffPresenceModel model);
    }

    public interface ItemClickListener {
        void onItemClick(int position, View v, StaffPresenceModel model);
    }

    StaffPresenceAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff_presence, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        StaffPresenceModel staffPresenceModel = staffPresenceModelList.get(position);
        if (staffPresenceModel.getName() != null && !staffPresenceModel.getName().isEmpty()) {
            holder.name_layout.setVisibility(0);
            holder.name.setText(staffPresenceModel.getName());
        } else {
            holder.name_layout.setVisibility(8);
        }
        if (staffPresenceModel.getCnic() != null && !staffPresenceModel.getCnic().isEmpty() && !staffPresenceModel.getCnic().equals("null")) {
            holder.cnic_layout.setVisibility(0);
            holder.cnic.setText(staffPresenceModel.getCnic());
        } else {
            holder.cnic_layout.setVisibility(8);
        }
        if (staffPresenceModel.getPersonalNo() != null && !staffPresenceModel.getPersonalNo().isEmpty() && !staffPresenceModel.getPersonalNo().equals("null")) {
            holder.personal_no_layout.setVisibility(0);
            holder.personal_no.setText(staffPresenceModel.getPersonalNo());
        } else {
            holder.personal_no_layout.setVisibility(8);
        }
        if (staffPresenceModel.getReason() != null && !staffPresenceModel.getReason().isEmpty() && !staffPresenceModel.getReason().equals("null")) {
            holder.status_layout.setVisibility(0);
            holder.status.setText(staffPresenceModel.getReason());
        } else {
            holder.status_layout.setVisibility(8);
        }
        holder.cb_is_synced.setChecked(staffPresenceModel.isSynced());
        if (staffPresenceModelList.get(holder.getAdapterPosition()).isSynced()) {
            holder.delete_record.setVisibility(8);
        } else {
            holder.delete_record.setVisibility(0);
            holder.delete_record.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceAdapter$eYp14qHFcYBYoMd2Bd3DbJ_0uCg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StaffPresenceAdapter.MyViewHolder myViewHolder = holder;
                    StaffPresenceAdapter.deleteClickListener.onDeleteClick(myViewHolder.getAdapterPosition(), view, StaffPresenceAdapter.staffPresenceModelList.get(myViewHolder.getAdapterPosition()));
                }
            });
        }
    }

    public void sortList() {
        Collections.sort(staffPresenceModelList, new Comparator<StaffPresenceModel>() { // from class: com.micromerger.ssms.staffpresence.StaffPresenceAdapter.1
            @Override // java.util.Comparator
            public int compare(StaffPresenceModel o1, StaffPresenceModel o2) {
                return String.valueOf(o2.getTimeInMillis()).compareTo(String.valueOf(o1.getTimeInMillis()));
            }
        });
        this.activity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceAdapter$GsGfFgjcmjMUXMdEqiscXqcnUBk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$sortList$1$StaffPresenceAdapter();
            }
        });
    }

    public /* synthetic */ void lambda$sortList$1$StaffPresenceAdapter() {
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<StaffPresenceModel> list = staffPresenceModelList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void updateList(List<StaffPresenceModel> list) {
        staffPresenceModelList = list;
        this.activity.runOnUiThread(new Runnable() { // from class: com.micromerger.ssms.staffpresence.-$$Lambda$StaffPresenceAdapter$b6r-6cv9v07q14ySEc4kbRwqq8U
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateList$2$StaffPresenceAdapter();
            }
        });
    }

    public /* synthetic */ void lambda$updateList$2$StaffPresenceAdapter() {
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox cb_is_synced;
        TextView cnic;
        LinearLayout cnic_layout;
        ImageView delete_record;
        TextView name;
        LinearLayout name_layout;
        TextView personal_no;
        LinearLayout personal_no_layout;
        TextView status;
        LinearLayout status_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.name_layout = (LinearLayout) itemView.findViewById(R.id.name_layout);
            this.cnic_layout = (LinearLayout) itemView.findViewById(R.id.cnic_layout);
            this.personal_no_layout = (LinearLayout) itemView.findViewById(R.id.personal_no_layout);
            this.status_layout = (LinearLayout) itemView.findViewById(R.id.status_layout);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.cnic = (TextView) itemView.findViewById(R.id.cnic);
            this.personal_no = (TextView) itemView.findViewById(R.id.personal_no);
            this.status = (TextView) itemView.findViewById(R.id.status);
            this.cb_is_synced = (CheckBox) itemView.findViewById(R.id.cb_is_synced);
            this.delete_record = (ImageView) itemView.findViewById(R.id.delete_record);
            itemView.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            StaffPresenceAdapter.itemClickListener.onItemClick(getAdapterPosition(), v, (StaffPresenceModel) StaffPresenceAdapter.staffPresenceModelList.get(getAdapterPosition()));
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener2) {
        itemClickListener = itemClickListener2;
    }

    public void setOnDeleteClickListener(DeleteClickListener deleteClickListener2) {
        deleteClickListener = deleteClickListener2;
    }
}
