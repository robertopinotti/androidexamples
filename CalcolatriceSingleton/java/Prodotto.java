package com.example.robertopinotti.calcolatricesingleton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Prodotto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodotto);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView textView = findViewById(R.id.textView);
        int n = MySingleton.getInstance().getValue();
        Log.d("MainActivity", "n: "+n);
        textView.setText(Integer.toString(n));
    }

    public void piuUno(View view){

        Log.d("MainActivity", "piuUno premuto");
        TextView textView = findViewById(R.id.textView);
        String textViewString = textView.getText().toString();
        int n = Integer.parseInt(textViewString);
        n++;
        textView.setText(Integer.toString(n));

    }

    public void piuDieci(View view){

        Log.d("MainActivity", "piuDieci premuto");
        TextView textView = findViewById(R.id.textView);
        String textViewString = textView.getText().toString();
        int n = Integer.parseInt(textViewString);
        n+=10;
        textView.setText(Integer.toString(n));

    }

    public void prodotto(View view){
        Log.d("MainActivity", "prodotto premuto");
        TextView textView = findViewById(R.id.textView);
        MySingleton.getInstance().setValue(Integer.parseInt(textView.getText().toString()));
        Log.d("MainActivity", "n: "+ Integer.toString(MySingleton.getInstance().getValue()));
        Intent intent = new Intent(this, Somma.class);
        startActivity(intent);
    }

}
