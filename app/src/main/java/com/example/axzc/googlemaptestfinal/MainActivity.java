package com.example.axzc.googlemaptestfinal;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements  OnMapReadyCallback, GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraMoveCanceledListener, GoogleMap.OnMapClickListener {
    private GoogleMap gmap;
    private MapView mapView;
    private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyA2gkFQJeRSU6AveQMtbYc6FbxAkUUz4lo";

    Button btn_search,btn_set;
    EditText edtxt_x,edtxt_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtxt_x=(EditText)findViewById(R.id.editText_xInput);
        edtxt_y=(EditText)findViewById(R.id.editText_yInput);

        btn_set=(Button)findViewById(R.id.button_xyInput);
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double x,y;

                x=Double.parseDouble(edtxt_x.getText().toString());
                y=Double.parseDouble(edtxt_y.getText().toString());
                Toast.makeText(MainActivity.this,"x="+x+",y="+y,Toast.LENGTH_SHORT).show();


                gmap.setMinZoomPreference(12);
                LatLng ny = new LatLng(x, y);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(ny);
                markerOptions.draggable(true);
                gmap.addMarker(markerOptions);
                gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));

            }
        });

        try{
            Bundle mapViewBundle = null;
            if (savedInstanceState != null) {
                mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
                Toast.makeText(MainActivity.this,"!=null",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"===null",Toast.LENGTH_SHORT).show();
            }
            mapView=(MapView)findViewById(R.id.mapView) ;
            mapView.onCreate(mapViewBundle);
            mapView.getMapAsync(this);
        }
        catch (Exception e){
            Toast.makeText(MainActivity.this,"Error :"+e.toString(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }
        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        gmap.setIndoorEnabled(true);

        UiSettings uiSettings = gmap.getUiSettings();
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);

        LatLng ny = new LatLng(40.7143528, -74.0059731);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ny);
        markerOptions.draggable(true);

        gmap.addMarker(markerOptions);

        gmap.setOnCameraIdleListener(this);
        gmap.setOnCameraMoveStartedListener(this);
        gmap.setOnCameraMoveListener(this);
        gmap.setOnCameraMoveCanceledListener(this);
        gmap.setOnMapClickListener(this);

        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));

    }

    @Override
    public void onCameraIdle() {
        //Toast.makeText(this, "The camera has stopped moving.",
                //Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraMoveStarted(int reason) {
        if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
            Toast.makeText(this, "The user gestured on the map.",
                    Toast.LENGTH_SHORT).show();
        } else if (reason == GoogleMap.OnCameraMoveStartedListener
                .REASON_API_ANIMATION) {
            Toast.makeText(this, "The user tapped something on the map.",
                    Toast.LENGTH_SHORT).show();
        } else if (reason == GoogleMap.OnCameraMoveStartedListener
                .REASON_DEVELOPER_ANIMATION) {
            Toast.makeText(this, "The app moved the camera.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCameraMove() {
        //Toast.makeText(this, "The camera is moving.",
                //Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraMoveCanceled() {
        Toast.makeText(this, "Camera movement canceled.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        //Toast.makeText(this, "User clicked.",
               // Toast.LENGTH_SHORT).show();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        gmap.addMarker(markerOptions);
    }
}
