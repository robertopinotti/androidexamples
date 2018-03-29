package com.example.robertopinotti.thread;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* METODO CLASSICO

    public void calcola(View view){

        Log.d("MainActivity", "calcola premuto");

        EditText editText = (EditText) findViewById(R.id.editText);
        String editTextString = editText.getText().toString();
        int n = Integer.parseInt(editTextString);

        Log.d("MainActivity", "int: "+n);

        for (int i = 2; i <= n/2; i++)
            if (n%i==0)
                Log.d("MainActivity", n+" non è primo!");

    }

     */

    // METODO CON AsyncTask

    public void Calcola(View view){

        Log.d("MainActivity", "calcola premuto");

        new CalcolaPrimo().execute();

    }

    private class CalcolaPrimo extends AsyncTask<Object, Object, Object> {

        @Override
        protected Void doInBackground(Object... params) {
            Log.d("MainActivity", "doInBackground()");
            int n = 10;
            for (int i = 2; i <= n/2; i++)
                if (n%i==0)
                    Log.d("doInBackground()", "yolo");
            return null;
        }

    }

}