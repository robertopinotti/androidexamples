package com.example.robertopinotti.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    EditText usernameText=null, passwordText=null;
    String usernameString="", passwordString="";
    RequestQueue requesteQueue;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // if session_id != "" allora intent a FragmentActivity.class, else
        // session_id è dentro una classe singleton

        String session_id = MySingleton.getInstance().getSession_id();
        Log.d("onCreate", "session_id: "+session_id);
        if (session_id!=""){
            intent = new Intent(this, FragmentActivity.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creo la richiesta
        requesteQueue = Volley.newRequestQueue(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        String session_id = MySingleton.getInstance().getSession_id();
        Log.d("onStart", "session_id: "+session_id);
        if (session_id!=""){
            startActivity(intent);
        }

    }

    public void checkLogin(View view) {

        String url = "https://ewserver.di.unimi.it/mobicomp/geopost/login";

        // username
        usernameText = findViewById(R.id.username);
        if (usernameText.getText().toString().isEmpty()){
            Log.d("MainActivity", "if username");
            Toast.makeText(this, "Devi inserire un username", Toast.LENGTH_SHORT).show();
            return;
        }
        usernameString = usernameText.getText().toString().trim();

        // password
        passwordText = findViewById(R.id.password);
        if (passwordText.getText().toString().isEmpty()){
            Log.d("MainActivity", "if password");
            Toast.makeText(this, "Devi inserire una password", Toast.LENGTH_SHORT).show();
            return;
        }
        passwordString= passwordText.getText().toString().trim();

        // creazione url
        url = url + "?username=" + usernameString + "&password=" + passwordString;

        Log.d("MainActivity", url);

        // Creating the JsonObjectRequest class called objectRequest, passing required parameters:
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                response = "test"; // RIGA DA CANCELLARE QUANDO IL SERVER SARÀ OPERATIVO
                Log.d("onResponse", response);
                MySingleton.getInstance().setSession_id(response);
                openActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onErrorResponse", "That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        requesteQueue.add(stringRequest);

    }

    public void openActivity(){
        Log.d("MainActivity", "openActivity");
        intent = new Intent(this, FragmentActivity.class);
        startActivity(intent);
    }

}
