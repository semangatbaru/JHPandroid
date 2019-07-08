package com.example.coba;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home extends AppCompatActivity {
    //Mendefinisikan variabel
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    SessionManager sessionManager;
    public static String URL2 = "http://192.168.1.16/herbal/ApiRegister/";

    ListView listView;
    private List<Barangitem> barangitemList;
    private  void loadPlayer() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray barangArray = obj.getJSONArray("result");
                    for (int i = 0; i < barangArray.length(); i++) {
                        JSONObject barangObject = barangArray.getJSONObject(i);

                        Barangitem barangItem = new Barangitem(barangObject.getString("id_barang"),
                        barangObject.getString("namabarang"),
                                barangObject.getString("harga"),
                                barangObject.getString("satuan"),
                                barangObject.getString("stok"),
                                barangObject.getString("kategori"),
                                barangObject.getString("gambar"),
                                barangObject.getString("deskripsi"));
                                barangitemList.add(barangItem);
                        Toast.makeText(Home.this, "v !", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(Home.this, "s !", Toast.LENGTH_LONG).show();
                    ListViewAdapter adapter = new ListViewAdapter(barangitemList, getApplicationContext());

                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Home.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, "DITAMBAHKAN !", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView =  findViewById(R.id.listView);
        barangitemList = new ArrayList<>();
        loadPlayer();



    }


}

