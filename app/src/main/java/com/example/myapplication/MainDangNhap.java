package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.databasedoctruyen;

public class MainDangNhap extends AppCompatActivity {

    //taoj bien cho man dang nhap
    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap, btnDangKy;

    // de tao doi tuong cho dbdoctruyen
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);

        AnhXa();

        // đối tượng databasedoctruyen
        databasedoctruyen = new databasedoctruyen(this);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDangNhap.this, MainDangKy.class);
                startActivity(intent);

            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gán cho các biến là giá trị nhập vào từ editText
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();

                //sử dụng con trỏ để lấy dữ liệu, gọi tới getData() để lấy tất cả các tk ở database
                 Cursor cursor = databasedoctruyen.getData();

                //Thực hiện vòng lặp để lấy dữ liệu từ cursor với moveToNext di chuyển tiếp
                while ((cursor.moveToNext())){
                    //lấy dữ liệu và gắn vào biến, dữ liệu tài khoản ở ô 1
                    //và đặt mật khẩu ở ô 2, ô 3 là email, 4 là phân quyền
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

                    //nếu tk và mk nhập vào từ bàn phím
                    if(datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)){

                        //lấy dữ liệu phanquyen và id
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        //chuyển qua màn hình MainActivity
                        Intent intent = new Intent(MainDangNhap.this, MainActivity.class);

                        //gửi dữ liệu qua activity là MainActivity
                        intent.putExtra("phanq", phanquyen);
                        intent.putExtra("idd", idd);
                        intent.putExtra("email", email);
                        intent.putExtra("tentaikhoan", tentk);

                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(MainDangNhap.this, "Sai thông tin đăng nhập", Toast.LENGTH_LONG).show();
                    }
                }
                //thực hiện trả cursor về đầu
                cursor.moveToFirst();
                //đóng khi không dùng
                cursor.close();
            }
        });
    }

    private  void AnhXa(){
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.dangky);
        btnDangNhap = findViewById(R.id.dangnhap);
    }
}