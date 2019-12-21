package com.example.second;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class LocationMap extends Fragment implements OnMapReadyCallback {


    private MapView mapView = null;

    public LocationMap()
    {
        // required
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.locationmap, container, false);

        mapView = (MapView)layout.findViewById(R.id.map);
        mapView.getMapAsync(this);

        return layout;
    }


    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //액티비티가 처음 생성될 때 실행되는 함수

        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    public static Location findGeoPoint(Context context, String restaurantAddress) {
        Location loc = new Location("");
        Geocoder coder = new Geocoder(context);
        List<Address> addr = null;// 한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 설정

        try {
            addr = coder.getFromLocationName(restaurantAddress, 5);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 몇개 까지의 주소를 원하는지 지정 1~5개 정도가 적당
        if (addr != null) {
            for (int i = 0; i < addr.size(); i++) {
                Address lating = addr.get(i);
                double lat = lating.getLatitude(); // 위도가져오기
                double lon = lating.getLongitude(); // 경도가져오기
                loc.setLatitude(lat);
                loc.setLongitude(lon);
                System.out.println("안녕하세요" + lat);
                System.out.println(lon);
            }
        }
        return loc;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Bundle bundle = getArguments();
        String restaurantAddress;
        String restaurantName;


        restaurantName = bundle.getString("restaurantName");
        restaurantAddress = bundle.getString("restaurantAddress");



        double lon = findGeoPoint(getContext(), restaurantAddress).getLongitude();
        double lat = findGeoPoint(getContext(), restaurantAddress).getLatitude();

        Log.d("레스토랑주소는용 바로", "출력은 정상입니다.");
        Log.d("레스토랑주소는용 바로", restaurantAddress);

        LatLng store = new LatLng(lat, lon);

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(store);

        markerOptions.title(restaurantName);

        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(store));

        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));


    }
}
