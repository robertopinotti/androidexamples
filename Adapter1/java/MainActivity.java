package com.example.robertopinotti.adapter1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);

        ListOfContacts contacts = new ListOfContacts();

        ContactsAdapter myAdapter = new ContactsAdapter(
                this,
                android.R.layout.list_content,
                contacts
        );

        listView.setAdapter(myAdapter);

    }
}
