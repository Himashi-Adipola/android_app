package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.apiOps.ApiClientMap;
import com.example.myapplication.apiOps.IGoogleApiInterface;
import com.example.myapplication.model.MyPlaces;
import com.example.myapplication.model.Results;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Console;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
        , LocationListener {

    private static final int MY_PERMISSION_CODE = 1000;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Double latitude, longitude;
    private Location mLastLocation;
    private Marker mMarker;
    private LocationRequest mLocationRequest;
    MyPlaces currentPlace;

    IGoogleApiInterface mService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // Initilize Service
        mService = Common.getGoogleApiInterface();
        // Request Run Time Permission
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        BottomNavigationView bottomNavigationView =(BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.find_garage:
                        nearByPlace("car_repair");
                        break;
                    case R.id.find_spare_parts:
                        nearByPlace("spare parts");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }

    private void nearByPlace(String placeType) {
        mMap.clear();
        String url = getUrl(latitude,longitude,placeType);
        mService.getNearByPlaces(url)
                .enqueue(new Callback<MyPlaces>() {
                    @Override
                    public void onResponse(Call<MyPlaces> call, Response<MyPlaces> response) {

                        currentPlace = response.body();
                        if(response.isSuccessful()){
                            for(int i =0; i <response.body().getResults().length; i++){
                                MarkerOptions markerOptions = new MarkerOptions();
                                Results googlePlace =response.body().getResults()[i];
                                double lat = Double.parseDouble(googlePlace.getGeometry().getLocation().getLat());
                                double lng = Double.parseDouble(googlePlace.getGeometry().getLocation().getLng());
                                String placeName  = googlePlace.getName();
                                String vicinity = googlePlace.getVicinity();
                                LatLng latLng = new LatLng(lat ,lng);
                                markerOptions.position(latLng);
                                markerOptions.title(placeName);
                                if(placeType.equals("car_repair"))
//                                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_baseline_add_location_24));
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                                else if(placeType.equals("spare Parts"))
//                                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_baseline_add_location_24));
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                                else

                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                                markerOptions.snippet(String.valueOf(i)); // assign index for marker




                                // Add Map
                                mMap.addMarker(markerOptions);


                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MyPlaces> call, Throwable t) {

                    }
                });

    }

    private String getUrl(Double latitude, Double longitude, String placeType) {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+10000);
        googlePlaceUrl.append("&type="+placeType);
//     googlePlaceUrl.append("&name"+ placeType);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+getResources().getString(R.string.browser_key));
        System.out.println( " getURl"+ googlePlaceUrl.toString());
//      System.out.println("getUrl",googlePlaceUrl.toString());
        return googlePlaceUrl.toString();
    }

    private boolean checkLocationPermission() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
                ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                },MY_PERMISSION_CODE);
            else
                ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                },MY_PERMISSION_CODE);
            return false;
        }
        else
            return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_CODE:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                    {
                        if(mGoogleApiClient == null)
                            buildGoogleApiClien();
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClien();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClien();
            mMap.setMyLocationEnabled(true);

        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Common.currentResults =currentPlace.getResults()[Integer.parseInt(marker.getSnippet())];
                startActivity( new Intent(MapsActivity.this ,ViewPlaceActivity.class));
                return true;
            }
        });


    }

    private synchronized void buildGoogleApiClien() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation  =location;
        if(mMarker != null)
            mMarker.remove();
        latitude = location.getLatitude();
        longitude =location.getLongitude();

        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title("Your Position")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMarker =mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        if(mGoogleApiClient != null)
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);


    }
}
