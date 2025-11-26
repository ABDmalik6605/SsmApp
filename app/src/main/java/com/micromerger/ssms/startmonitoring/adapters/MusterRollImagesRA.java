package com.micromerger.ssms.startmonitoring.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.micromerger.ssms.R;
import java.util.List;

/* loaded from: classes2.dex */
public class MusterRollImagesRA extends RecyclerView.Adapter<ViewHolder> {
    private static MyClickListener myClickListener;
    private List<Bitmap> mItems;
    private int position;

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return 0;
    }

    public MusterRollImagesRA(List<Bitmap> mItems) {
        this.mItems = mItems;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int view_type) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_muster_roll, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (this.mItems.size() > 0) {
            List<Bitmap> list = this.mItems;
            if (list != null) {
                viewHolder.image.setImageBitmap(this.mItems.get(position));
                return;
            }
            try {
                byte[] bArrDecode = Base64.decode(list.get(position).toString(), 0);
                viewHolder.image.setImageBitmap(BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        ImageView deleteImage;
        ImageView image;

        ViewHolder(View v) {
            super(v);
            this.image = (ImageView) v.findViewById(R.id.image);
            this.deleteImage = (ImageView) v.findViewById(R.id.deleteImage);
            this.image.setOnClickListener(this);
            this.deleteImage.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            MusterRollImagesRA.myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener2) {
        myClickListener = myClickListener2;
    }

    public void updateBitmapList(List<Bitmap> bitmapList) {
        this.mItems = bitmapList;
        notifyDataSetChanged();
    }

    public void deleteImage(int pos) {
        this.mItems.remove(pos);
        notifyDataSetChanged();
    }
}
