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

import com.elfstudio.testtask.R;
import com.elfstudio.testtask.databinding.ActivityMainBinding;
import com.elfstudio.testtask.model.Hit;
import com.elfstudio.testtask.model.SearchedData;
import com.elfstudio.testtask.utils.listeners.ClickListener;
import com.elfstudio.testtask.utils.service.TimerService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        startService(new Intent(this, TimerService.class));
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
        setText();
        setAdapter();
        setObserver();
    }

    private void setAdapter() {
        adapter = new MainAdapter(this, (isChecked, position, hit, hits) -> {
            if (isChecked) {
                viewModel.getChecks().put(position, isChecked);
            } else {
                viewModel.getChecks().remove(position);
            }
            setText();
        });
        binding.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    private void setText() {
        binding.includeLayoutToolbar.tvItemSelected.setText(getResources().getString(R.string.item_selected) + " " + viewModel.getChecks().values().size());
    }

    private void setObserver() {
        viewModel.getData().observe(this, searchedData -> {
            adapter.setSearchedData(searchedData.getHits());
            Log.i("TAG", "DATA SET SUCCESSFUL");
        });

        viewModel.getLiveMessage().observe(this, msg -> {
            if (msg != null) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}