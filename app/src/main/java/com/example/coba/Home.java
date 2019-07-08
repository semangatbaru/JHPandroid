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

public class Home extends AppCompatActivity {
    //Mendefinisikan variabel
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    SessionManager sessionManager;
    public static String URL2 = "http://192.168.1.13:8080/herbal/ApiRegister?";
    private  void loadPlayer() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray barangArray = obj.getJSONArray("result");
                    for (int i = 0; i < barangArray.length(); i++) {
                        JSONObject barangObject = barangArray.getJSONObject(i);

                        String barangitem = (String) barangArray.getString("no"),
                        barangObject.getString("namabarang"),
                                barangObject.getString("harga"),
                                barangObject.getString("satuan"),
                                barangObject.getString("stok"),
                                barangObject.getString("kategori"),
                                barangObject.getString("gambar"),
                                barangObject.getString("deskripsi"),
                                barangitemList.add(barangitem);
                    }
                    ListViewAdapter adapter = new ListViewAdapter(barangitemList, getApplicationContext());

                    listview.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.LENGTH_SHORT.show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    //    private TextView viewnama;
//    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sessionManager = new SessionManager(getApplicationContext());

        findViewById(R.id.detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Detail.class);
                startActivity(intent);
                barangItemList = new ArrayList<>();
                loadPlayer();
            }
        });



//        HashMap<String, String> user = sessionManager.getUserDetail();
//        String Nama = user.get(SessionManager.NAMA_USER);
//        viewnama.setText(Html.fromHtml(Nama));
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Menginisiasi Toolbar dan mensetting sebagai actionbar
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        // Menginisiasi  NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Mengatur Navigasi View Item yang akan dipanggil untuk menangani item klik menu navigasi
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()){
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.navigation1:
                        Intent intent = new Intent(Home.this, MainActivity.class);
                        Home.this.startActivity(intent);
                        return true;
                    case R.id.navigation2:
                        Intent intent2 = new Intent(Home.this, MainActivity.class);
                        Home.this.startActivity(intent2);
                        return true;

                    case R.id.navigation3:
                        Intent intent4 = new Intent(Home.this, Login.class);
                        Home.this.startActivity(intent4);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Ini Menu Home ",Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        // Menginisasi Drawer Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string. openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Kode di sini akan merespons setelah drawer menutup disini kita biarkan kosong
                super.onDrawerClosed(drawerView);
           }
            @Override
            public void onDrawerOpened(View drawerView) {
                //  Kode di sini akan merespons setelah drawer terbuka disini kita biarkan kosong
                super.onDrawerOpened(drawerView);
            }
        };
        //Mensetting actionbarToggle untuk drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //memanggil synstate
        actionBarDrawerToggle.syncState();
//]



    }


}

