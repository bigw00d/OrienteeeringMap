package com.bigw00d.mapapp;

//import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampleapp.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private Marker topmarker;
    private PolylineOptions mPolyOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "USE YOUR ACCESS TOKEN");

        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(new Style.Builder().fromUri("USE YOUR STYLE"));
                LatLng point = new LatLng(34.894671, 135.806347);
                LatLng point2 = new LatLng(34.891934, 135.811271);
                LatLng point3 = new LatLng(34.890966, 135.815450);
                topmarker = mapboxMap.addMarker(new MarkerOptions()
                        .position(point)
                        .title("宇治駅")
                        .snippet("6/6 土 9:32"));
                mapboxMap.addMarker(new MarkerOptions()
                        .position(point2)
                        .title("シンボル２")
                        .snippet("6/6 土 9:33"));
                mapboxMap.addMarker(new MarkerOptions()
                        .position(point3)
                        .title("シンボル３")
                        .snippet("6/6 土 9:34"));
                mapboxMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
                topmarker.showInfoWindow(mapboxMap, mapView);
                mPolyOptions = new PolylineOptions();
                mPolyOptions.add(point);
                mPolyOptions.add(point2);
                mPolyOptions.add(point3);
                mPolyOptions.color(Color.RED);
                mPolyOptions.width(4);
                mapboxMap.addPolyline(mPolyOptions);

            }
        });
    }

    class CustomInfoWindowAdapter implements MapboxMap.InfoWindowAdapter  {
        private final View infoWindow;

        CustomInfoWindowAdapter() {
            infoWindow = getLayoutInflater().inflate(R.layout.info_window_main, null);
        }

        public View getInfoWindow(Marker marker) {
//                String title = marker.getTitle();
//                TextView textViewTitle = (TextView) infoWindow.findViewById(R.id.title);
//                textViewTitle.setText(title);

            String snippet = marker.getSnippet();
            TextView textViewSnippet = (TextView) infoWindow.findViewById(R.id.snippet);
            textViewSnippet.setText(snippet);

            ImageView badge = (ImageView) infoWindow.findViewById(R.id.badge);
            badge.setImageResource(R.drawable.player_icon);

            return infoWindow;
        }

        public View getInfoContents(Marker marker) {
            return null;
        }
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
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}
