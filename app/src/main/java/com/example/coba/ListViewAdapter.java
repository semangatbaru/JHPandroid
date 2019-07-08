package com.example.coba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Barangitem> {
    private List<Barangitem> barangitemList;
    private Context context;
    public  ListViewAdapter (List<Barangitem> barangitemList, Context context){
        super(context, R.layout.list_item,barangitemList);
        this.barangitemList=barangitemList;
        this.context=context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View listViewItem = inflater.inflate(R.layout.list_item,null,true);
        TextView textViewno = listViewItem.findViewById(R.id.textViewno);
        TextView textViewnamabarang = listViewItem.findViewById(R.id.textViewNamabarang);
        TextView textViewharga = listViewItem.findViewById(R.id.textViewHarga);
        TextView textViewsatuan = listViewItem.findViewById(R.id.textViewSatuan);
        TextView textViewstok = listViewItem.findViewById(R.id.textViewStok);
        TextView textViewkategori = listViewItem.findViewById(R.id.textViewKategori);
        TextView textViewgambar = listViewItem.findViewById(R.id.textViewGambar);
        TextView textViewdeskripsi = listViewItem.findViewById(R.id.textViewDeskripsi);
        Barangitem barangitem = barangitemList.get(position);
        textViewno.setText(barangitem.getNo());
        textViewno.setText(barangitem.getNamabarang());
        textViewno.setText(barangitem.getHarga());
        textViewno.setText(barangitem.getSatuan());
        textViewno.setText(barangitem.getStok());
        textViewno.setText(barangitem.getKategori());
        textViewno.setText(barangitem.getGambar());
        textViewno.setText(barangitem.getDeskripsi());
        Glide.with(context).load(barangitem.getGambar()).into(imgView);
        return listViewItem;
    }
}