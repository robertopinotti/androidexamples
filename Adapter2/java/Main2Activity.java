package com.example.robertopinotti.adapter2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private String nome;
    private String distanza;
    private String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            nome = (String) bundle.get("nome");
            distanza = (String) bundle.get("distanza");
            data = (String) bundle.get("data");
            Log.d("Main2Activity", nome + " " + distanza + " " + data);
        }

        TextView textView1 = (TextView) findViewById(R.id.nome);
        textView1.setText(nome);

        TextView textView2 = (TextView) findViewById(R.id.distanza);
        textView2.setText(distanza);

        TextView textView3 = (TextView) findViewById(R.id.data);
        textView3.setText(data);

    }
}
