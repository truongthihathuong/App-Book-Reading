package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.adapter.adapterTruyen;
import com.example.myapplication.database.databasedoctruyen;
import com.example.myapplication.model.Truyen;

import java.util.ArrayList;
import java.util.List;

public class MainAdmin extends AppCompatActivity {
    ListView listView;
    Button buttonThem;
    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        listView = findViewById(R.id.listviewAdmin);
        buttonThem = findViewById(R.id.buttonThemtruyen);

        initList();

        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //;lấy id tài khoản để biết tk admin nào đã vào chỉnh sửa
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra("id", 0);

                //tiếp tục gửi id qua màn hình thêm truyện
                Intent intent = new Intent(MainAdmin.this, MainDangBai.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int idtruyen = TruyenArrayList.get(position).getID();

                //xóa dữ liệu
                databasedoctruyen.Delete(idtruyen);

                //cập nhật lại activity
                Intent intent = new Intent(MainAdmin.this, MainAdmin.class);
                finish();
                startActivity(intent);
                Toast.makeText(MainAdmin.this, "Xóa truyện thành công", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    //gán dữ liệu cho listview
    private void initList() {
        TruyenArrayList = new ArrayList<>();
        databasedoctruyen = new databasedoctruyen(this);
        Cursor cursor1 = databasedoctruyen.getData2();

        while(cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(), TruyenArrayList);
            listView.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();
    }
}