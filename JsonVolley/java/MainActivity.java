package com.example.robertopinotti.jsonvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String url = "https://ewserver.di.unimi.it/mobicomp/geopost/followed";
    String result = "";
    RequestQueue requesteQueue;
    JSONObject obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creo la richiesta
        requesteQueue = Volley.newRequestQueue(this);

        textView = findViewById(R.id.textView);

        // Creating the JsonObjectRequest class called objectRequest, passing required parameters:
        // 1. GET is used to fetch data from the server
        // 2. JsonURL is the URL to be fetched from
        // 3. Listener overrides the method onResponse() and passes JSONObject as a parameter
        JsonObjectRequest objectRequest = new JsonObjectRequest(url, obj,
                new Response.Listener<JSONObject>(){

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray arr = response.getJSONArray("followed");

                            Log.d("arr ", arr.toString());

                            for (int i = 0; i < arr.length(); i++){
                                JSONObject jsonObject = arr.getJSONObject(i);

                                String username = jsonObject.getString("username");
                                String msg = jsonObject.getString("msg");
                                String lat = jsonObject.getString("lat");
                                String lon = jsonObject.getString("lon");

                                Log.d("stampa ", username+msg+lat+lon);

                                result += username+" "+msg+" "+lat+" "+lon + "\n";

                                Log.d("result ", result);

                                textView.setText(result);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } // onReponse()

                }, // Response.Listener<JSONObject>

                // The final parameter overrides the method onErrorResponse()
                //and passes VolleyError as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );

        // Adds the JSON object request "objectRequest" to the request queue
        requesteQueue.add(objectRequest);

    }
}