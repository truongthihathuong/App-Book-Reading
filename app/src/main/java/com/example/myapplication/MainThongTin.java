package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainThongTin extends AppCompatActivity {
    TextView txtThongtinapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin);
        txtThongtinapp = findViewById(R.id.textviewthongtin);

        String thongtin = "Ứng dụng được phát hành bởi nhóm 9-62HT";

        txtThongtinapp.setText(thongtin);


    }
}