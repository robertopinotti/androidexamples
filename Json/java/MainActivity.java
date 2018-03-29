package com.example.robertopinotti.json;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GestioneThread().execute();

    }

    private class GestioneThread extends AsyncTask<Object, Void, String>{

        @Override
        protected String doInBackground(Object[] params) {

            String result = "";
            String sreturn = "";

            try {
                URL url = new URL("https://ewserver.di.unimi.it/mobicomp/geopost/followed");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = read.readLine();
                while(line!=null) {
                    result += line;
                    line = read.readLine();
                }

                JSONObject obj = new JSONObject(result.toString());
                JSONArray arr = obj.getJSONArray("followed");

                Log.d("doInBackground - arr",arr.toString());

                for (int i = 0; i < arr.length(); i++){
                    JSONObject jsonObject = arr.getJSONObject(i);

                    String username = jsonObject.getString("username");
                    String msg = jsonObject.getString("msg");
                    String lat = jsonObject.getString("lat");
                    String lon = jsonObject.getString("lon");

                    sreturn += username+" "+msg+" "+lat+" "+lon + "\n";

                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return sreturn;
        }

        protected void onProgressUpdate(Void... params) {
        }

        protected void onPostExecute(String html){
            TextView textView = findViewById(R.id.textView);
            textView.setText(html);
        }

    }

}