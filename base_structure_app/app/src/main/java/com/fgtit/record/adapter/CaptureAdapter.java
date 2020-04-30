package com.fgtit.record.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgtit.record.R;
import com.fgtit.record.holder.ProgressViewHolder;
import com.fgtit.record.model.CaptureItem;
import com.fgtit.record.holder.CaptureHolder;

import java.util.List;


public class CaptureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<CaptureItem> captureItems;
    private Context context;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private int visibleThreshold = 2;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    public CaptureAdapter(Context context, RecyclerView recyclerView, List<CaptureItem> captureItems) {
        this.captureItems = captureItems;
        this.context = context;


        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });

        }
    }

    @Override
    public int getItemViewType(int position) {
        return captureItems.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_capture, parent, false);

            vh = new CaptureHolder(v,captureItems);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_loading_footer, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof CaptureHolder) {
            final CaptureItem item = captureItems.get(position);


        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return this.captureItems.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
