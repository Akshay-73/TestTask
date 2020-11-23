package com.elfstudio.testtask.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.elfstudio.testtask.R;
import com.elfstudio.testtask.databinding.ActivityMainBinding;
import com.elfstudio.testtask.model.Hit;
import com.elfstudio.testtask.model.SearchedData;
import com.elfstudio.testtask.utils.listeners.ClickListener;
import com.elfstudio.testtask.utils.listeners.PaginationListener;
import com.elfstudio.testtask.utils.service.TimerService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    private MainAdapter adapter;

    private boolean isLoading = false;
    private static final int PAGE_START = 1;
    private int OFFSET = PAGE_START;
    private static final int FINAL_PAGE = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        startService(new Intent(this, TimerService.class));
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
        init();
        setText();
        setAdapter();
        setObserver();
    }

    private void init() {

    }

    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvMain.setLayoutManager(layoutManager);
        adapter = new MainAdapter(this, (isChecked, position, hit, hits) -> {
            if (isChecked) {
                viewModel.getChecks().put(position, true);
            } else {
                viewModel.getChecks().remove(position);
            }
            setText();
        });
        binding.setAdapter(adapter);


        binding.rvMain.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                OFFSET += 1;
                adapter.addLoading();
                hitApi(OFFSET);
            }

            @Override
            public boolean isLastPage() {
                return OFFSET > FINAL_PAGE;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setText() {
        binding.includeLayoutToolbar.tvItemSelected.setText(getResources().getString(R.string.item_selected) + " " + viewModel.getChecks().values().size());
    }

    private void setObserver() {
        hitApi(OFFSET);

        viewModel.getLiveMessage().observe(this, msg -> {
            if (msg != null) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hitApi(int page) {
        viewModel.getData(page).observe(this, searchedData -> {
            /*for (int i = viewModel.getSearchedData().values().size(); i < searchedData.getHits().size(); i++) {
                viewModel.getSearchedData().put(i, searchedData.getHits().get(i));
            }*/
            adapter.addAllData(searchedData.getHits());
            isLoading = false;
            adapter.removeLoading();
            Log.i("TAG", "DATA SET SUCCESSFUL");
        });
    }
}