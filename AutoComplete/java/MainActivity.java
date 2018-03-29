package com.example.robertopinotti.autocomplete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // codice per gestire l'AutoCompleteTextView
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                new String[] {"pippo","pluto","paperina"}
        );

        autoCompleteTextView.setAdapter(adapter);

        // listener per gestire la selezione dell'utente
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
                String selected = (String)adapter.getItemAtPosition(pos);
                Toast.makeText(
                        getApplicationContext(),
                        "hai selezionato "+selected,
                        Toast.LENGTH_LONG
                ).show();
            }
        });

    }
}
