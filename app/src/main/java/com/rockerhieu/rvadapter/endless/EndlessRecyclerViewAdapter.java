package com.rockerhieu.rvadapter.endless;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.rockerhieu.rvadapter.RecyclerViewAdapterWrapper;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class EndlessRecyclerViewAdapter extends RecyclerViewAdapterWrapper {
    public static final int TYPE_PENDING = 999;
    private final Context context;
    private AtomicBoolean dataPending;
    private AtomicBoolean keepOnAppending;
    private final int pendingViewResId;
    private RequestToLoadMoreListener requestToLoadMoreListener;

    public interface RequestToLoadMoreListener {
        void onLoadMoreRequested();
    }

    public EndlessRecyclerViewAdapter(Context context, RecyclerView.Adapter adapter, RequestToLoadMoreListener requestToLoadMoreListener, int i, boolean z) {
        super(adapter);
        this.context = context;
        this.requestToLoadMoreListener = requestToLoadMoreListener;
        this.pendingViewResId = i;
        this.keepOnAppending = new AtomicBoolean(z);
        this.dataPending = new AtomicBoolean(false);
    }

    public EndlessRecyclerViewAdapter(Context context, RecyclerView.Adapter adapter, RequestToLoadMoreListener requestToLoadMoreListener) {
        this(context, adapter, requestToLoadMoreListener, R.layout.item_loading, true);
    }

    private void stopAppending() {
        setKeepOnAppending(false);
    }

    public void onDataReady(boolean z) {
        this.dataPending.set(false);
        setKeepOnAppending(z);
    }

    private void setKeepOnAppending(boolean z) {
        this.keepOnAppending.set(z);
        getWrappedAdapter().notifyDataSetChanged();
    }

    public void restartAppending() {
        this.dataPending.set(false);
        setKeepOnAppending(true);
    }

    private View getPendingView(ViewGroup viewGroup) {
        return LayoutInflater.from(this.context).inflate(this.pendingViewResId, viewGroup, false);
    }

    @Override // com.rockerhieu.rvadapter.RecyclerViewAdapterWrapper, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return super.getItemCount() + (this.keepOnAppending.get() ? 1 : 0);
    }

    @Override // com.rockerhieu.rvadapter.RecyclerViewAdapterWrapper, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i == getWrappedAdapter().getItemCount() ? TYPE_PENDING : super.getItemViewType(i);
    }

    @Override // com.rockerhieu.rvadapter.RecyclerViewAdapterWrapper, androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 999) {
            return new PendingViewHolder(getPendingView(viewGroup));
        }
        return super.onCreateViewHolder(viewGroup, i);
    }

    @Override // com.rockerhieu.rvadapter.RecyclerViewAdapterWrapper, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == 999) {
            if (this.dataPending.get()) {
                return;
            }
            this.dataPending.set(true);
            this.requestToLoadMoreListener.onLoadMoreRequested();
            return;
        }
        super.onBindViewHolder(viewHolder, i);
    }

    static class PendingViewHolder extends RecyclerView.ViewHolder {
        public PendingViewHolder(View view) {
            super(view);
        }
    }
}
