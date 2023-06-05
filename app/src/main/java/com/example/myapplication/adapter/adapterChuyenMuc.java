package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Chuyenmuc;
import com.example.myapplication.model.Chuyenmuc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterChuyenMuc extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Chuyenmuc> chuyenMucList;

    public adapterChuyenMuc(Context context, int layout, List<Chuyenmuc> chuyenMucList) {
        this.context = context;
        this.layout = layout;
        this.chuyenMucList = chuyenMucList;
    }

    @Override
    public int getCount() {
        return chuyenMucList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        ImageView img = (ImageView) convertView.findViewById(R.id.imgChuyenMuc);

        TextView txt = (TextView) convertView.findViewById(R.id.textviewTenchuyenmuc);

        Chuyenmuc cm = chuyenMucList.get(position);

        txt.setText(cm.getTenchuyenmuc());

        Picasso.get().load(cm.getHinhanhchuyenmuc()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(img);
        return convertView;
    }
}
