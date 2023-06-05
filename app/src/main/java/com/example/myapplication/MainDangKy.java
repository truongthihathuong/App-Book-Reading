package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.databasedoctruyen;
import com.example.myapplication.model.TaiKhoan;

public class MainDangKy extends AppCompatActivity {

    EditText edtDKTaiKhoan, edtDKMatKhau, edtDKEmail;
    Button btnDKDangKy, btnDKDangNhap;

    databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        databasedoctruyen = new databasedoctruyen(this);
        AnhXa();
        //click cho button dang ky
        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();
                String email = edtDKEmail.getText().toString();

                TaiKhoan taikhoan1 = CreateTaiKhoan();
                if(taikhoan.equals("")|| matkhau.equals("")|| email.equals("")){
                    Log.e("Thông báo: ", "Chưa nhập đầy đủ thông tin");
                }
                //nếu đầy đủ thông tin nhập vào thì add tk đó vào db
                else{
                    databasedoctruyen.AddTaiKhoan((taikhoan1));
                    Toast.makeText(MainDangKy.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();

                }

            }
        });
        //run

        //trở về trang đăng nhập
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatKhau.getText().toString();
        String email = edtDKEmail.getText().toString();

        int phanquyen =1;

        TaiKhoan tk = new TaiKhoan(taikhoan, matkhau, email,phanquyen);
        return tk;
    }

    private void AnhXa(){
        edtDKEmail= findViewById(R.id.dkemail);
        edtDKMatKhau= findViewById(R.id.dkmatkhau);
        edtDKTaiKhoan= findViewById(R.id.dktaikhoan);
        btnDKDangKy= findViewById(R.id.dkdangky);
        btnDKDangNhap= findViewById(R.id.dkdangnhap);
    }
}