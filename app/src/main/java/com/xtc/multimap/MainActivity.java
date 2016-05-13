package com.xtc.multimap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.xtc.multimap.map.MapManager;
import com.xtc.multimap.map.MapOptions;
import com.xtc.multimap.map.MapUISettings;
import com.xtc.multimap.map.location.MapLocation;
import com.xtc.multimap.map.location.MapLocationClient;
import com.xtc.multimap.map.location.MapLocationListener;
import com.xtc.multimap.map.location.MapLocationOption;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Bind(R.id.map_view)
    RelativeLayout mapView;

    private Bundle savedInstanceState;

    private MapManager mapManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mapManager = new MapManager(this);

        initMap();
    }

    private void initMap() {
        if (mapManager.getCurrentMapType() == MapManager.MAP_TYPE_BD) {
            mapManager.setMapView(mapView, MapManager.MAP_TYPE_AMAP);
            mapManager.onCreate(savedInstanceState);
        } else {
            mapManager.setMapView(mapView, MapManager.MAP_TYPE_BD);
        }

        initUISettings();

        startLocation();
    }

    private MapLocationClient mapLocationClient;

    private void startLocation() {
        mapManager.setMyLocationEnabled(true);
        if (mapLocationClient != null) {
            mapLocationClient.stopLocation();
        }
        mapLocationClient = new MapLocationClient(this, mapManager.getCurrentMapType());
        mapLocationClient.setLocationListener(locationListener);
        MapLocationOption option = new MapLocationOption();
        option.setLocationMode(MapLocationOption.LocationMode.Hight_Accuracy);
        option.setGpsFirst(true);
        option.setOpenGps(true);
        option.setNeedAddress(true);
        option.setScanSpan(5000);
        mapLocationClient.setLocationOption(option);
        mapLocationClient.startLocation();
    }

    private void initUISettings() {
        MapUISettings mapUISettings = mapManager.getUISettings();
        mapUISettings.setAllGesturesEnabled(true);
        mapUISettings.setMyLocationButtonEnabled(true);
        mapUISettings.setScaleControlsEnabled(true);
        mapUISettings.setZoomControlsEnabled(false);
        mapUISettings.setCompassEnabled(true);
        mapUISettings.setLogoPosition(MapOptions.LOGO_POSITION_BOTTOM_CENTER);
        mapUISettings.setZoomPosition(MapOptions.ZOOM_POSITION_RIGHT_CENTER);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapLocationClient.stopLocation();
        mapManager.onDestroy();
    }

    @OnClick({R.id.change_map, R.id.add_mark, R.id.change_map_mode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_map:
                initMap();
                break;
            case R.id.add_mark:
                break;
            case R.id.change_map_mode:
                if (mapManager.getMapMode() == MapOptions.MAP_SATELLITE) {
                    mapManager.setMapMode(MapOptions.MAP_NORMAL);
                } else {
                    mapManager.setMapMode(MapOptions.MAP_SATELLITE);
                }
                break;
        }
    }

    MapLocationListener locationListener = new MapLocationListener() {
        @Override
        public void onLocationChanged(MapLocation mapLocation) {
            Log.i(TAG, mapLocation.toString());
        }
    };

}
