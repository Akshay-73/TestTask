package com.elfstudio.testtask.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.elfstudio.testtask.R;
import com.elfstudio.testtask.databinding.ItemSearchDataBinding;
import com.elfstudio.testtask.databinding.LayoutItemLoadingBinding;
import com.elfstudio.testtask.model.Hit;
import com.elfstudio.testtask.utils.listeners.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private final Context context;
    private List<Hit> searchedData;
    private ClickListener<Boolean, Integer, Hit, List<Hit>> clickListener;
    private ItemSearchDataBinding binding;

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

    public MainAdapter(Context context, ClickListener<Boolean, Integer, Hit, List<Hit>> clickListener) {
        this.context = context;
        this.clickListener = clickListener;
        searchedData = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(context);
        }
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == VIEW_TYPE_NORMAL) {
            viewHolder = getViewHolder(parent, layoutInflater);
        } else if (viewType == VIEW_TYPE_LOADING) {
            LayoutItemLoadingBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_item_loading, parent, false);
            viewHolder = new ProgressHolder(binding);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_NORMAL:
                final MainViewHolder myViewHolder = (MainViewHolder) holder;
                myViewHolder.bind(searchedData);
                break;
            case VIEW_TYPE_LOADING:
                break;
        }
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        binding = DataBindingUtil.inflate(inflater, R.layout.item_search_data, parent, false);
        viewHolder = new MainViewHolder(binding);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == searchedData.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    private void add(Hit hit) {
        searchedData.add(hit);
        notifyItemInserted(searchedData.size() - 1);
    }

    public void addAllData(List<Hit> searchedData) {
        for (Hit hit : searchedData) {
            add(hit);
        }
    }

    public void addLoading() {
        isLoaderVisible = true;
        notifyDataSetChanged();
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = searchedData.size() - 1;
        Hit result = getItem(position);

        if (result != null) {
            searchedData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Hit getItem(int position) {
        return searchedData.get(position);
    }

    public void clearList() {
        searchedData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return searchedData == null ? 0 : searchedData.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private final ItemSearchDataBinding binding;

        public MainViewHolder(@NonNull final ItemSearchDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        private void bind(List<Hit> data) {
            if (data != null) {
                binding.tvTitle.setText(data.get(getAdapterPosition()).getTitle());
                binding.checkbox.setChecked(data.get(getAdapterPosition()).isChecked());
                binding.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        data.get(getAdapterPosition()).setChecked(isChecked);
                        clickListener.onClick(isChecked, getAdapterPosition(), data.get(getAdapterPosition()), data);
                    }
                });
            }
        }
    }

    public static class ProgressHolder extends RecyclerView.ViewHolder {
        private LayoutItemLoadingBinding loadingBinding;

        ProgressHolder(@NonNull LayoutItemLoadingBinding loadingBinding) {
            super(loadingBinding.getRoot());
            this.loadingBinding = loadingBinding;
        }


    }
}
