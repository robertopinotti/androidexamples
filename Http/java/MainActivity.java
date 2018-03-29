package com.example.robertopinotti.http;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Calcola(View view){
        Log.d("MainActivity", "Calcola()");
        new GestioneThread().execute();
    }

    private class GestioneThread extends AsyncTask<Object, Void, String>{

        @Override
        protected String doInBackground(Object[] params) {

            String html = "";

            try {
                URL url = new URL("http://mobidev2014.appspot.com/helloworld");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = read.readLine();
                while(line!=null) {
                    html += line;
                    line = read.readLine();
                }
                Log.d("GestioneThread", "Stringa letta: "+html);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return html;
        }

        protected void onProgressUpdate(Void... params) {
        }

        protected void onPostExecute(String html){
            TextView textView = (TextView)findViewById(R.id.textView);
            textView.setText(html);
        }

    }

}
