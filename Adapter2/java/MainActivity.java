package com.example.robertopinotti.adapter2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, Main2Activity.class);

        ListView listView = (ListView)findViewById(R.id.listView);

        final ListOfContacts contacts = new ListOfContacts();

        ContactsAdapter myAdapter = new ContactsAdapter(
                this,
                android.R.layout.list_content,
                contacts);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Contact c = contacts.get(position);

                        Log.d("OnItemClickListener", c.getNome()+" "+c.getDistanza()+" "+c.getData());

                        intent.putExtra("nome", c.getNome());
                        intent.putExtra("distanza", c.getDistanza());
                        intent.putExtra("data", c.getData());

                        startActivity(intent);

                    } // onItemCLick
                } // OnItemClickListener
        );
    }
}
