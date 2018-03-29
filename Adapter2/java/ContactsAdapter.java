package com.example.robertopinotti.adapter2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter<Contact>{

    // costruttori

    public ContactsAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ContactsAdapter(Context context, int resource, List<Contact> items) {
        super(context, resource, items);
    }

    // metodo getView

    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;

        // parte di codice che crea una nuova voce nella lista
        // LayoutInflater serve per aggiungere della grafica via Java

        if (v==null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_element, null);
        }

        Contact p = getItem(position);

        if(p!=null){

            TextView textView1 = (TextView) v.findViewById(R.id.nome);
            textView1.setText(p.getNome());
            /*
            TextView textView2 = (TextView) v.findViewById(R.id.distanza);
            textView2.setText(p.getDistanza());

            TextView textView3 = (TextView) v.findViewById(R.id.data);
            textView3.setText(p.getData()); */

        }

        return v;

    }
}
