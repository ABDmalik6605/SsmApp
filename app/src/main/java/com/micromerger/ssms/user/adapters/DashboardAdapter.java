package com.micromerger.ssms.user.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import com.micromerger.ssms.user.beans.dashboard.Data;
import java.util.List;

/* loaded from: classes2.dex */
public class DashboardAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static MyClickListener myClickListener;
    private List<Data> mItems;
    private int position;

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return 0;
    }

    public DashboardAdapter(List<Data> items) {
        this.mItems = items;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int view_type) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_attendance, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.tv_name.setText(this.mItems.get(position).getUsers().getUserName());
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
        TextView tv_name;

        ViewHolder(View v) {
            super(v);
            this.tv_name = (TextView) v.findViewById(R.id.tv_name);
            CheckBox checkBox = (CheckBox) v.findViewById(R.id.checked);
            this.check = checkBox;
            checkBox.setVisibility(4);
            v.setOnClickListener(this);
            this.rootView = v;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DashboardAdapter.myClickListener.onItemClick(getAdapterPosition(), view);
        }
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

    public void updateList(List<Data> list) {
        this.mItems = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(MyClickListener myClickListener2) {
        myClickListener = myClickListener2;
    }
}
