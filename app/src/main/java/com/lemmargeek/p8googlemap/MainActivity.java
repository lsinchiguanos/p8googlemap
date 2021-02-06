package com.lemmargeek.p8googlemap;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private LatLng buenaFe = new LatLng(-0.8874108,-79.5071985);
    private CameraUpdate cameraUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;
    }

    public void changedViewMap(View view){
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public void moveCamMap(View view){
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(-0.8874108,-79.5071985), 5);
        googleMap.moveCamera(cameraUpdate);
    }

    public void animatedCamMap(View view){
        CameraPosition cameraPosition = CameraPosition.builder().target(buenaFe).zoom(19).bearing(45).tilt(70).build();
        cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        googleMap.animateCamera(cameraUpdate);
    }

}