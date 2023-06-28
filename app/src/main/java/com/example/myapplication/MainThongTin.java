package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.net.URI;

public class MainThongTin extends AppCompatActivity {

//    TextView txtThongtinapp;
    TextView ifimg1;
    TextView ifimg2;
    TextView ifimg3;
    Button ifPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin);
//        txtThongtinapp = findViewById(R.id.textviewthongtin);



//        String thongtin = "Liên hệ với chúng tôi:";
//
//        txtThongtinapp.setText(thongtin);

        ifimg1 = findViewById(R.id.if_img1);
        ifimg2 = findViewById(R.id.if_img2);
        ifimg3 = findViewById(R.id.if_img3);
        ifPhone = findViewById(R.id.if_Phone);

        ifimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoUrl("https://www.facebook.com/hathuong037");
            }
        });

        ifimg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.facebook.com/bqhuy2211");
            }
        });

        ifimg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.facebook.com/profile.php?id=100027890130775");
            }
        });

        ifPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                callIntent.setData(Uri.parse("tel: 0825888834"));
                startActivity(callIntent);
            }
        });

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
//            }
//        });
//
//        AdView mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);



    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}