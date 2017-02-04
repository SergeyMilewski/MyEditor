package com.sergey.myeditor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sergey.myeditor.R;
import com.sergey.myeditor.data.model.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 26.01.17.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Field> fields = new ArrayList<>();
    private Context context;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private boolean isLoading;
    private int threshold = 5;
    private int lastVisibleItem, totalItemCount;

    private OnLoadMoreListener onLoadMoreListener;
    private OnItemClickListener listener;

    public MyRecycleViewAdapter(Context ctx, RecyclerView recyclerView, OnItemClickListener listener) {

        context = ctx;
        this.listener = listener;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + threshold)) {
                    if (true/*onLoadMoreListener != null*/) {
                        //   onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadListener(OnLoadMoreListener onLoadListener) {
        this.onLoadMoreListener = onLoadListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout, parent, false);
            return new ItemViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.loading_layout, parent, false);
            return new LoadingViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            Field field = fields.get(position);
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.title.setText(field.getTitle());
            itemViewHolder.idText.setText(String.valueOf(field.getId()));
            ((ItemViewHolder) holder).bind(listener, fields.get(position));
            itemViewHolder.checkBox.setChecked(field.isCompleted());
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return fields == null ? 0 : fields.size();
    }

    @Override
    public int getItemViewType(int position) {
        return fields.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView idText;
        public AppCompatCheckBox checkBox;
        public LinearLayout container;


        public ItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            idText = (TextView) itemView.findViewById(R.id.id);
            checkBox = (AppCompatCheckBox) itemView.findViewById(R.id.checkbox);
            container = (LinearLayout) itemView.findViewById(R.id.item_container);
        }

        public void bind(OnItemClickListener listener, Field field) {
            container.setOnClickListener((View v) -> listener.onItemClick(field));
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progress);
        }
    }
}
