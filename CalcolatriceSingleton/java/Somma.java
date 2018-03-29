package com.example.robertopinotti.calcolatricesingleton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Somma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_somma);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView textView = findViewById(R.id.textView);
        int n = MySingleton.getInstance().getValue();
        Log.d("MainActivity", "n: "+n);
        textView.setText(Integer.toString(n));
    }

    public void perDue(View view){

        Log.d("MainActivity", "perDue premuto");
        TextView textView = findViewById(R.id.textView);
        String textViewString = textView.getText().toString();
        int n = Integer.parseInt(textViewString);
        n=n*2;
        textView.setText(Integer.toString(n));

    }

    public void perDieci(View view){

        Log.d("MainActivity", "perDieci premuto");
        TextView textView = findViewById(R.id.textView);
        String textViewString = textView.getText().toString();
        int n = Integer.parseInt(textViewString);
        n=n*10;
        textView.setText(Integer.toString(n));

    }

    public void somma(View view){
        Log.d("MainActivity", "prodotto premuto");
        TextView textView = findViewById(R.id.textView);
        MySingleton.getInstance().setValue(Integer.parseInt(textView.getText().toString()));
        Log.d("MainActivity", "n: "+ Integer.toString(MySingleton.getInstance().getValue()));
        Intent intent = new Intent(this, Prodotto.class);
        startActivity(intent);
    }

}
