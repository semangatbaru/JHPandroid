package com.example.coba;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray; import org.json.JSONException; import org.json.JSONObject;

import java.util.HashMap; import java.util.Map;


public class Login extends AppCompatActivity {

    // deklarasi objek

    EditText editTextUsername, editTextPassword;
    Button buttonLogin;

    // deklarasi variabel
    String username, password;

    // deklarasi variabel alamat host
    public static String URL = "http://192.168.1.13:8080/herbal/App_login";

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        // inisialisasi variabel objek
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // jika tombol login diklik
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = editTextUsername.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                {
                    login(username, password);
                }
            }
        });
    }

    // method login
    private void login(final String username, final String password){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String id_user = jsonObject1.getString("id_user").trim();
                            String username = jsonObject1.getString("username").trim();
                            String password = jsonObject1.getString("password").trim();
                            String level = jsonObject1.getString("level").trim();
                            sessionManager.createSession(username , password, level, id_user);
                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(Login.this, "Username dan Password tidak ditemukan! ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Login.this, "Error login : " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "Error login : " + error.toString(), Toast.LENGTH_SHORT) .show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { Map<String, String> params = new HashMap<>();
                params.put("username", username);
                // sesuaikan dengan $_POST pada PHP
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}