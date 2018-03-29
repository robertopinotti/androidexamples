package com.example.robertopinotti.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class FragmentActivity extends AppCompatActivity {

    // qui è dove metto il codice della MainActivity del progetto GeoPost

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("FragmentActivity","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        TextView textView = findViewById(R.id.textView);
        textView.setText("session_id: "+MySingleton.getInstance().getSession_id());
    }

    public void logout(View view) {

        Log.d("FragmentActivity","logout premuto");

        MySingleton.getInstance().setSession_id("");

        Log.d("FragmentActivity","session_id: " +MySingleton.getInstance().getSession_id());

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
