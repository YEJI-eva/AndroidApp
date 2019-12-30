package com.example.googlemapexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;

        LatLng BUSAN = new LatLng(35.15, 129.05);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(BUSAN);
        markerOptions.title("부산");
        markerOptions.snippet("부산 IT교육센터");

        mMap.moveCamera(CameraUpdateFactory.newLatLng(BUSAN));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5));
    }

}
