package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myapplication.adapter.adapterTruyen;
import com.example.myapplication.database.databasedoctruyen;
import com.example.myapplication.model.Truyen;

import java.util.ArrayList;

public class MainTimKiem extends AppCompatActivity {
    ListView listView;
    EditText edt;
    Button buttonTrangchu;
    ArrayList<Truyen> TruyenArrayList;
    ArrayList<Truyen> arrayList;

    adapterTruyen adapterTruyen;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tim_kiem);
        listView = findViewById(R.id.listviewtimkiem);
        edt = findViewById(R.id.timkiem);
        buttonTrangchu = findViewById(R.id.buttonTrangChu);

        intiList();

        //bat sk click cho item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainTimKiem.this, MainNoiDung.class);
                String tent = arrayList.get(position).getTenTruyen();
                String noidungt = arrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen", tent);
                intent.putExtra("noidung", noidungt);
                startActivity(intent);

            }
        });

        //editText search
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        //chuyển về trang chủ
        buttonTrangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainTimKiem.this, MainActivity.class );
                startActivity(intent);
            }
        });
    }

    private void filter(String text){
        arrayList.clear();
        //Tạo một danh sách mới có tên là filteredList, để chứa các phần tử truyện được lọc.
        ArrayList<Truyen> filteredList = new ArrayList<>();
        for(Truyen item : TruyenArrayList){
            if(item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                // Nếu item phù hợp, nó sẽ được thêm vào filteredList.
                filteredList.add(item);

                //them vao mang
                arrayList.add(item);
            }
        }
        //cập nhật giao diện người dùng hiển thị danh sách truyện theo danh sách đã được lọc.
        adapterTruyen.filterList(filteredList);
    }

    //phuong thuc lay du lieu, gan vao listview
    private void intiList() {
        TruyenArrayList = new ArrayList<>();
        arrayList = new ArrayList<>();
        databasedoctruyen = new databasedoctruyen(this);
        Cursor cursor = databasedoctruyen.getData2();

        while(cursor.moveToNext())
        {
            int id  = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            TruyenArrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

            arrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(), TruyenArrayList);
            listView.setAdapter(adapterTruyen);
        }
        cursor.moveToFirst();
        cursor.close();
    }
}