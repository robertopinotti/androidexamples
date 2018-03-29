package com.example.robertopinotti.volley;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    public String html = "prova";
    public RequestQueue requestQueue; /* VOLLEY */
    public String url = "http://mobidev2014.appspot.com/helloworld";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this); /* VOLLEY */
    }

    public void Calcola(View view){
        Log.d("MainActivity", "Calcola()");
        new GestioneThread().execute();
    }

    private class GestioneThread extends AsyncTask<Object, Void, String>{

        @Override
        protected String doInBackground(Object... objects) {

            Log.d("MainActivity", "doInBackground()");

            /* VOLLEY */

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

                @Override
                public void onResponse(String response) {
                    Log.d("MainActivity", "onResponse()");
                    html = response;
                    Log.d("onResponse()", "html: "+html);
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onErrorResponse()", error.toString());
                }
            });

            requestQueue.add(stringRequest);

            /* VOLLEY */

            return html;
        }

        protected void onPostExecute(String html){
            Log.d("onPostExecute","html: "+html);
            TextView textView = (TextView)findViewById(R.id.textView);
            textView.setText(html);
        }
    }

}