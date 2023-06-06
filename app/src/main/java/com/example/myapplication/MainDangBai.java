package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.databasedoctruyen;
import com.example.myapplication.model.Truyen;

public class MainDangBai extends AppCompatActivity {
    EditText edtTenTruyen, edtNoiDung, edtAnh;
    Button btnDangBai;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_bai);

        edtAnh= findViewById(R.id.dbImg);
        edtTenTruyen = findViewById(R.id.dbTentruyen);
        edtNoiDung = findViewById(R.id.dbNoidung);
        btnDangBai = findViewById(R.id.dbDangbai);

        databasedoctruyen = new databasedoctruyen(this);

        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentruyen = edtTenTruyen.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                Truyen truyen = CreateTruyen();

                if(tentruyen.equals("")|| noidung.equals("")||img.equals("")){
                    Toast.makeText(MainDangBai.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("Err: ", "Đã nhập đầy đủ thông tin");
                }

                else {
                    databasedoctruyen.AddTruyen(truyen);

                    //chuyển màn hình qua admin và cập nhật dữ liệu
                    Intent intent = new Intent(MainDangBai.this, MainAdmin.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

    //phương thức tạo truyện
    private Truyen CreateTruyen(){
        String tentruyen = edtTenTruyen.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id", 0);
        Truyen truyen = new Truyen(tentruyen, noidung, img,id);
        return truyen;
    }
}