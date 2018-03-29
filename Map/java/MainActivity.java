package com.example.robertopinotti.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap maps) {
        Log.d("MainActivity", "onMapReady");

        map=maps;
        // double lat,lon;
        // Marker marker;

        /*
        ListOfContacts contacts = new ListOfContacts();

        for (int i=0; i<contacts.size();i++){

            //LatLng city = new LatLng(contacts.get(i).getLat(), contacts.get(i).getLon());
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(contacts.get(i).getLat(),contacts.get(i).getLon()))
                    .title("Marker in "+contacts.get(i).getNome()));

            // contacts.get(i).getNome();
            // lat=contacts.get(i).getLat();
            // lon=contacts.get(i).getLon();

        } */

        // LatLng sydney = new LatLng(-33.852, 151.211);
        // LatLng perth = new LatLng(-31.952854, 115.857342);

        // map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // map.addMarker(new MarkerOptions().position(perth).title("Marker in Perth"));

        // Marker marker = map.addMarker(new MarkerOptions(new LatLng(lat,lon)));

        // map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void caricaDati(View v){
        Log.d("MainActivity", "caricaDati");
        double latmin=0, lonmin=0, latmax=0, lonmax=0;
        int x=0;

        ListOfContacts contacts = new ListOfContacts();

        for (int i=0; i<contacts.size();i++){

            //LatLng city = new LatLng(contacts.get(i).getLat(), contacts.get(i).getLon());
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(contacts.get(i).getLat(),contacts.get(i).getLon()))
                    .title("Marker in "+contacts.get(i).getNome()));

            if(x==0){
                latmin=latmax=contacts.get(i).getLat();
                lonmin=lonmax=contacts.get(i).getLon();
                x=1;
            }

            if (contacts.get(i).getLat()<latmin)
                latmin=contacts.get(i).getLat();

            if (contacts.get(i).getLon()<lonmin)
                lonmin=contacts.get(i).getLon();

            if (contacts.get(i).getLat()>latmax)
                latmax=contacts.get(i).getLat();

            if (contacts.get(i).getLon()>lonmax)
                lonmax=contacts.get(i).getLon();
        }

        LatLngBounds latLngBounds = new LatLngBounds(new LatLng(latmin,lonmin), new LatLng(latmax,lonmax));

        // map.setLatLngBoundsForCameraTarget(latLngBounds);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngBounds.getCenter(),3));

    }

}