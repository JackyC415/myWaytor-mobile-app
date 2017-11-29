package com.example.lap.mywaytor;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.app.Notification;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.drawable.RippleDrawable;
import android.support.v7.widget.AppCompatImageHelper.*;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;

import android.content.Context.*;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.internal.*;

import java.io.IOException;


public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    public Button bRetry, bYes;
    private GoogleMap googleMap;
    private Marker marker;
    private CameraPosition mCameraPosition;
    private LocationManager locationManager;
    private static final String TAG = LocationActivity.class.getSimpleName();
    private final LatLng mDefaultLocation = new LatLng(0, 0);
    private static final int DEFAULT_ZOOM = 1;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private Location mLastKnownLocation, location, locationCt;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private GoogleApiClient mGoogleApiClient;
    private static final int M_MAX_ENTRIES = 5;
    public LatLng spot;
    private Criteria criteria;
    public double lat,lng;
    String provider;
    float zoom = 20;
    boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        bRetry = findViewById(R.id.bRetry);
        bYes = findViewById(R.id.bYes);
        buttons();
    }

    public void buttons() {
        bRetry.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Location Moved Click Target!", Toast.LENGTH_SHORT).show();
                locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                provider = locationManager.getBestProvider(criteria, false);
                location = locationManager.getLastKnownLocation(provider);
                lat = location.getLatitude();
                lng = location.getLongitude();
                if(location == null || provider == null){
                    locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    provider = locationManager.getBestProvider(criteria, false);
                    location = locationManager.getLastKnownLocation(provider);
                }
                if(location != null || provider != null) {
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    spot = new LatLng(lat, lng);
                    marker.setPosition(spot);
                }
            }
        });
        bYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insert checks
                if((lat>37.31531) && (lat<37.31566)&&(lng<-120.4705) && (lng>-120.4708) ){
                    //at ThaiStar
                    Toast.makeText(getApplicationContext(), "Chose Thai Star!", Toast.LENGTH_SHORT).show();
                    check = true;
                    return;
                    //add toast and StartActivity variable
                }
                if((lat>37.3308) && (lat<37.3316)&&(lng>-120.4986) && (lng<-120.4662) ){
                    //at Em-tea
                    //add toast and StartActivity variable
                    Toast.makeText(getApplicationContext(), "Chose Em-tea!", Toast.LENGTH_SHORT).show();
                    check = true;
                    return;
                }
                if((lat>37.3185) && (lat<37.3189)&&(lng<-120.4982) && (lng>-120.4989) ){
                    //at Sno-Crave Tea House
                    //add toast and StartActivity variable
                    Toast.makeText(getApplicationContext(), "Chose Sno-Crave!", Toast.LENGTH_SHORT).show();
                    check = true;
                    return;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Not at a participating location!", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(LocationActivity.this, MenuActivity.class));
                }
             }
        });
    }

    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        googleMap.setMyLocationEnabled(true);
        criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        location = locationManager.getLastKnownLocation(provider);
        if(location == null){
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            provider = locationManager.getBestProvider(criteria, false);
            location = locationManager.getLastKnownLocation(provider);
        }
        if (location != null){
            lat = location.getLatitude();
            lng = location.getLongitude();
        }
        spot = new LatLng(lat, lng);
        marker = googleMap.addMarker(new MarkerOptions().position(spot).title("Are you here?"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spot,20));
        googleMap.setMaxZoomPreference(20);
    }
}