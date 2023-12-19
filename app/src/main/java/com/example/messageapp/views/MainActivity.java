package com.example.messageapp.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.messageapp.R;
import com.example.messageapp.databinding.ActivityMainBinding;
import com.example.messageapp.viewmodel.MyViewmodel;

public class MainActivity extends AppCompatActivity {

    MyViewmodel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MyViewmodel.class);


        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.setVModel(viewModel);
    }
}