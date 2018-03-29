package com.example.robertopinotti.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String myPreferences;
    TextView textView;
    EditText editText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);

        String datoSalvato = sharedPreferences.getString("datoSalvato", null);

        if (datoSalvato == null)
            Log.d("onCreate", "datoSalvato = null");
        else
            Log.d("onCreate", "datoSalvato = " + datoSalvato);

        textView.setText(datoSalvato);

    }

    public void salva(View view) {

        Log.d("MainActivity", "button salva premuto");

        // WRITE

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Log.d("MainActivity", "textView: " + textView.getText().toString());

        editor.putString("datoSalvato",editText.getText().toString());

        editor.commit();

        // READ

        String datoSalvato = sharedPreferences.getString("datoSalvato", null);

        Log.d("MainActivity", "datoSalvato: " + datoSalvato);

        textView.setText(datoSalvato);

    }
}
