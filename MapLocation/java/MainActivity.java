package com.example.robertopinotti.maplocation;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    static final Integer LOCATION = 0x1;
    static final Integer GPS_SETTINGS = 0x7;
    private double currentLatitude;
    private double currentLongitude;
    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;
    PendingResult<LocationSettingsResult> locationSettingsResultPendingResult;
    GoogleMap map;

    /* ================================================================================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ask for permission
        askForPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, LOCATION);

        // build the googleApiClient
        googleApiClient = new GoogleApiClient.Builder(this)
                // new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                // adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000) // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 seconds, in milliseconds

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Log.d("MainActivity", "SupportMapFragment");
    }

    /* ================================================================================================ */

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    /* ================================================================================================ */

    // To handle the results of a permission request, the onRequestPermissionsResult method is called
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            askForGPS();

            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

     /* ================================================================================================ */

    // This function prompts the user to enable GPS if it’s not enabled
    private void askForGPS() {

        // create a LocationSettingsRequest.Builder and add all of the LocationRequests that the app will be using:
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        builder.setAlwaysShow(true);

        locationSettingsResultPendingResult = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());

        // Prompt the User to Change Location Settings
        locationSettingsResultPendingResult.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(MainActivity.this, GPS_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {

                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
    }

      /* ================================================================================================ */

    @Override
    public void onMapReady(GoogleMap maps) {
        Log.d("MainActivity", "onMapReady");

        map=maps;

    }

    /* ================================================================================================ */

    public void caricaDati(View v){
        Log.d("MainActivity", "caricaDati");
        double latmin=0, lonmin=0, latmax=0, lonmax=0;
        int x=0;

        ListOfContacts contacts = new ListOfContacts();

        for (int i=0; i<contacts.size();i++){

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

    /* ================================================================================================ */

    public void miaPosizione(View view) {
        Log.d("MainActivity", "miaPosizione");

        map.addMarker(new MarkerOptions()
                .position(new LatLng(currentLatitude, currentLongitude))
                .title("Mia Posizione"));

        LatLng myposition = new LatLng(currentLatitude,currentLongitude);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myposition, 15));

    }

    /* ================================================================================================ */

    // client needs to connected when the app starts
    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    /* ================================================================================================ */

    // client needs to disconnected when the app stops
    @Override
    public void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    /* ================================================================================================ */

    public void onConnected(@Nullable Bundle bundle) {
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        Log.d("MainActivity", "startLocationUpdates()");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, locationRequest, this);
    }

    /* ================================================================================================ */

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    /* ================================================================================================ */

    @Override
    public void onLocationChanged(Location location) {

        Log.d("MainActivity", "onLocationChanged");

        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

        Log.d("onLocationChanged", "currentLatitude: "+currentLatitude);
        Log.d("onLocationChanged", "currentLongitude: "+currentLongitude);

    }

    /* ================================================================================================ */

}