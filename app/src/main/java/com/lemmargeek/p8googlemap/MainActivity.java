package com.lemmargeek.p8googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap googleMap;
    private LatLng buenaFe = new LatLng(-0.8874108,-79.5071985);
    private CameraUpdate cameraUpdate;
    private Projection projection;
    private PolylineOptions polylineOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.btnFigura);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;
        googleMap.setOnMapClickListener(this);
        polylineOptions = new PolylineOptions();
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

    public void drawPolygon(View view){
        googleMap.clear();
        polylineOptions = null;
        polylineOptions.width(8);
        polylineOptions.color(Color.RED);
        googleMap.addPolyline(polylineOptions);
    }

    @Override
    public void onMapClick(LatLng latLng) {

        googleMap.addMarker(new MarkerOptions().position(latLng).title("Point on map"));
        projection = googleMap.getProjection();
        Point point = projection.toScreenLocation(latLng);

        polylineOptions.add(latLng);

        Toast.makeText(MainActivity.this,
                "Click\n" +
                "Lat: " + latLng.latitude + "\n" +
                "Lng: " + latLng.longitude + "\n" +
                "X: " + point.x + "- Y: " + point.y,
                Toast.LENGTH_SHORT).show();
    }
}