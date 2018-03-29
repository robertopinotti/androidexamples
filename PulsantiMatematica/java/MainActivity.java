package com.example.robertopinotti.pulsantimatematica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void piuUno(View view){

        // debug
        Log.d("MainActivity", "piuUno premuto");

        // porto in Java il mio oggetto grafico
        TextView t = (TextView)findViewById(R.id.nomeText);

        // trasformo in stringa il contenuto dell'oggetto grafico
        String tstring = t.getText().toString();

        // debug
        Log.d("MainActivity", "String letta: " + tstring);

        // trasformo in intero
        int n = Integer.parseInt(tstring);

        // debug
        Log.d("MainActivity", "Trasformazione in intero: " + n);

        n++;

        // debug
        Log.d("MainActivity", "Dopo l'operazione: " + n);

        // aggiorno il valore della variabile (devo convertirla in String!!!)
        t.setText(Integer.toString(n));

    }

    public void menoUno(View view){

        // debug
        Log.d("MainActivity", "menoUno premuto");

        // porto in Java il mio oggetto grafico
        TextView t = (TextView)findViewById(R.id.nomeText);

        // trasformo in stringa il contenuto dell'oggetto grafico
        String tstring = t.getText().toString();

        // debug
        Log.d("MainActivity", "String letta: " + tstring);

        // trasformo in intero
        int n = Integer.parseInt(tstring);

        // debug
        Log.d("MainActivity", "Trasformazione in intero: " + n);

        n--;

        // debug
        Log.d("MainActivity", "Dopo l'operazione: " + n);

        // aggiorno il valore della variabile (devo convertirla in String!!!)
        t.setText(Integer.toString(n));

    }

    public void perDue(View view){

        // debug
        Log.d("MainActivity", "perDue premuto");

        // porto in Java il mio oggetto grafico
        TextView t = (TextView)findViewById(R.id.nomeText);

        // trasformo in stringa il contenuto dell'oggetto grafico
        String tstring = t.getText().toString();

        // debug
        Log.d("MainActivity", "String letta: " + tstring);

        // trasformo in intero
        int n = Integer.parseInt(tstring);

        // debug
        Log.d("MainActivity", "Trasformazione in intero: " + n);

        n=n*2;

        // debug
        Log.d("MainActivity", "Dopo l'operazione: " + n);

        // aggiorno il valore della variabile (devo convertirla in String!!!)
        t.setText(Integer.toString(n));

    }

    public void elevatoDue(View view){

        // debug
        Log.d("MainActivity", "elevatoDue premuto");

        // porto in Java il mio oggetto grafico
        TextView t = (TextView)findViewById(R.id.nomeText);

        // trasformo in stringa il contenuto dell'oggetto grafico
        String tstring = t.getText().toString();

        // debug
        Log.d("MainActivity", "String letta: " + tstring);

        // trasformo in intero
        int n = Integer.parseInt(tstring);

        // debug
        Log.d("MainActivity", "Trasformazione in intero: " + n);

        n=n*n;

        // debug
        Log.d("MainActivity", "Dopo l'operazione: " + n);

        // aggiorno il valore della variabile (devo convertirla in String!!!)
        t.setText(Integer.toString(n));

    }

    /* come farlo con una sola funzione

    public void eval(View view){

        // debug
        Log.d("MainActivity", "eval premuto");

        // porto in Java il mio oggetto grafico
        TextView t = (TextView)findViewById(R.id.nomeText);

        // trasformo in stringa il contenuto dell'oggetto grafico
        String tstring = t.getText().toString();

        // debug
        Log.d("MainActivity", "String letta: " + tstring);

        // trasformo in intero
        int n = Integer.parseInt(tstring);

        // debug
        Log.d("MainActivity", "Trasformazione in intero: " + n);

        switch (view.getId()){
            case R.id.buttonPiuUno:
                n++;
                Log.d("MainActivity", "buttonPiuUno");
            case R.id.buttonMenoUno:
                n--;
                Log.d("MainActivity", "buttonMenoUno");
            case R.id.buttonPerDue:
                n*=2;
                Log.d("MainActivity", "buttonPerDue");
            case R.id.buttonElevatoDue:
                n*=n;
                Log.d("MainActivity", "buttonElevatoDue");
        }

        // debug
        Log.d("MainActivity", "Dopo aver aggiunto 1: " + n);

        // aggiorno il valore della variabile (devo convertirla in String!!!)
        t.setText(Integer.toString(n));

    }

    */

}
