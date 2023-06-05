package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.myapplication.adapter.adapterChuyenMuc;
import com.example.myapplication.adapter.adapterThongTin;
import com.example.myapplication.adapter.adapterTruyen;
import com.example.myapplication.database.databasedoctruyen;
import com.example.myapplication.model.Chuyenmuc;
import com.example.myapplication.model.TaiKhoan;
import com.example.myapplication.model.Truyen;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView, listViewNew, listViewThongTin;
    DrawerLayout drawerLayout;
    String email;
    String tentaikhoan;

    ArrayList<Truyen> TruyenArraylist;

    adapterTruyen adapterTruyen;
    ArrayList<Chuyenmuc> chuyenMucArrayList;

    ArrayList<TaiKhoan> taiKhoanArrayList;
    adapterChuyenMuc adapterChuyenMuc;
    adapterThongTin adapterThongTin;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databasedoctruyen = new databasedoctruyen(this);

        // nhận dữ liệu từ màn đăng nhập gửi
        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra("phanq",0);
        int idd = intentpq.getIntExtra("idd",0);
        email = intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");

        AnhXa();
        ActionBar();
        ActionViewFlipper();

        //Bắt sk click item
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent (MainActivity.this, MainNoiDung.class);

                String tent = TruyenArraylist.get(position).getTenTruyen();
                String noidungt = TruyenArraylist.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });

        //bắt click item cho listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //đăng bài
                if (position == 0){
                    if(i == 2){
                        Intent intent = new Intent(MainActivity.this, MainAdmin.class);
                        //gửi id tài khoản qua màn hình admin
                        intent.putExtra("id",idd);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài: ","Bạn không có quyền");
                    }
                }
                //nếu ấn vòa thông tin màn hình chuyển qua màn hình thông tin app
                else if (position == 1){
                    Intent intent = new Intent(MainActivity.this, MainThongTin.class );
                    startActivity(intent);
                }
                //đăng xuất
                else if (position == 2) {
                    finish();
                }
            }
        });
    }
    @SuppressLint("RestrictedApi")
    private void ActionBar()
    {
        //hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);
        //cài đặt nút cho actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //tạo icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        //bật sự kiện click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    //phương thức cho chạy quảng cáo với ViewFlipper
    private void ActionViewFlipper() {
        //mảng chứa tấm ảnh cho quảng cáo
        ArrayList<String> mangquangcao = new ArrayList<>();
        //add ảnh vào mảng
        mangquangcao.add("https://toplist.vn/images/800px/rua-va-tho-230179.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/deo-chuong-cho-meo-230180.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/cu-cai-trang-230181.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg");

        //thực hiện vòng lặp for gán ảnh vào ImagerView, ròi từ imgview lên app
        for (int i=0; i<mangquangcao.size(); i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            //sử dụng hàm thư viện pis
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            //phương thức chỉnh ảnh vùa khung
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            //thêm ảnh từ imgview vào viewfipper
            viewFlipper.addView(imageView);

        }
        //thiết lập tự động chạy cho viewfipper chạy trong 4 giây
        viewFlipper.setFlipInterval(4000);
        //run auto viewflipper
        viewFlipper.setAutoStart(true);

        //gọi animation cho vào  và ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_night);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

        //gọi Animation vào viewFlipper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    private void AnhXa(){
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listViewNew= findViewById(R.id.listviewNew);
        listView= findViewById(R.id.listviewmanhinhchinh);
        listViewThongTin = findViewById(R.id.listviewthongtin);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);

        TruyenArraylist = new ArrayList<>();
        Cursor cursor1 = databasedoctruyen.getData1();
        while(cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArraylist.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArraylist);
            listViewNew.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

        //Thông tin
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));

        adapterThongTin = new adapterThongTin(this,R.layout.navigation_thongtin,taiKhoanArrayList);
        listViewThongTin.setAdapter(adapterThongTin);

        //Chuyên mục
        chuyenMucArrayList = new ArrayList<>();
        chuyenMucArrayList.add(new Chuyenmuc("Đăng bài",R.drawable.ic_add));
        chuyenMucArrayList.add(new Chuyenmuc("Thông tin",R.drawable.ic_face));
        chuyenMucArrayList.add(new Chuyenmuc("Đăng xuất",R.drawable.ic_logout));

        adapterChuyenMuc = new adapterChuyenMuc(this, R.layout.chuyenmuc,chuyenMucArrayList);
        listView.setAdapter(adapterChuyenMuc);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int MENU_1_ID = R.id.menu1;
        if (item.getItemId() == MENU_1_ID) {
            Intent intent = new Intent(MainActivity.this, MainTimKiem.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}