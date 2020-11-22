package com.elfstudio.testtask.ui;

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
import com.elfstudio.testtask.model.Hit;
import com.elfstudio.testtask.utils.listeners.ClickListener;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private final Context context;
    private List<Hit> searchedData;
    private ClickListener<Boolean, Integer, Hit, List<Hit>> clickListener;

    public MainAdapter(Context context, ClickListener<Boolean, Integer, Hit, List<Hit>> clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSearchDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_search_data, parent, false);
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.bind();
    }

    public void setSearchedData(List<Hit> searchedData) {
        this.searchedData = searchedData;
        notifyDataSetChanged();
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

        public MainViewHolder(@NonNull ItemSearchDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind() {
            if (searchedData != null) {
                binding.tvTitle.setText(searchedData.get(getAdapterPosition()).getTitle());

                binding.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        searchedData.get(getAdapterPosition()).setChecked(isChecked);
                        clickListener.onClick(isChecked, getAdapterPosition(), searchedData.get(getAdapterPosition()), searchedData);
                    }
                });
            }
        }
    }
}
