package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.database.databasedoctruyen;
import com.example.myapplication.model.Truyen;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class MainDangBai extends AppCompatActivity {

    ImageView imageView;
    Button add_img;
    EditText edtTenTruyen, edtNoiDung, edtAnh;
    Button btnDangBai;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_bai);

        imageView = findViewById(R.id.imageView);
        add_img = findViewById(R.id.add_img);

        edtAnh= findViewById(R.id.dbImg);
        edtTenTruyen = findViewById(R.id.dbTentruyen);
        edtNoiDung = findViewById(R.id.dbNoidung);
        btnDangBai = findViewById(R.id.dbDangbai);

        databasedoctruyen = new databasedoctruyen(this);
        add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(MainDangBai.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

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

//        Button gallery = findViewById(R.id.add_img);
//        gallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        if (data != null) {
//                            Uri selectedImageUri = data.getData();
//                            ImageView imageView = findViewById(R.id.imageView);
//                            imageView.setImageURI(selectedImageUri);
//                        }
//                    }
//                    else {
//                        Toast.makeText(MainDangBai.this, "Lỗi kết nối", Toast.LENGTH_LONG).show();
//                    }
//                });
//                launcher.launch(intent);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        imageView.setImageURI(uri);
    }

    //phương thức tạo truyện
    private Truyen CreateTruyen(){
        String tentruyen = edtTenTruyen.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id", 0);
        Truyen truyen = new Truyen(tentruyen, noidung, img, id);
        return truyen;
    }
}