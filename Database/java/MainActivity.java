package com.example.robertopinotti.database;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import static android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener{

    // variabili
    int n=0;
    String risultato ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // apro il database e se non esiste viene creato
        DBLayer db = new DBLayer(this);
        db.open();
        db.close();

    }

    @Override
    public void onClick(View v) {

        TextView textView = findViewById(R.id.textView);
        EditText editText = findViewById(R.id.editText);

        String editTextString = editText.getText().toString();
        n = Integer.parseInt(editTextString);

        DBLayer db = new DBLayer(this);
        db.open();
        String strQuery = "SELECT * FROM tabella1 WHERE _id="+n+";";
        Cursor c = db.Execute(strQuery);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            risultato = (c.getString(c.getColumnIndex("nome")));
            risultato +=" ";
            risultato += (c.getString(c.getColumnIndex("cognome")));
        }


        textView.setText(risultato);

    }

}