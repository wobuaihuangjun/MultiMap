package com.xtc.multimap.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.xtc.multimap.R;
import com.xtc.map.MapManager;
import com.xtc.map.location.MapLocationClient;
import com.xtc.map.location.MapLocationListener;
import com.xtc.map.location.MapLocationOption;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapLocation extends Activity {
    private static final String TAG = "MapLocation";

    @Bind(R.id.map_view)
    RelativeLayout mapView;

    private Bundle savedInstanceState;

    private MapManager mapManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.simple_map);
        ButterKnife.bind(this);
        mapManager = new MapManager(this);

        initMap();
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

    private void initMap() {
        if (mapManager.getCurrentMapType() == MapManager.MAP_TYPE_BD) {
            mapManager.setMapView(mapView, MapManager.MAP_TYPE_AMAP);
            mapManager.onCreate(savedInstanceState);
        } else {
            mapManager.setMapView(mapView, MapManager.MAP_TYPE_BD);
        }
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

    @OnClick({R.id.change_map})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_map:
                initMap();
                break;
        }
    }

    MapLocationListener locationListener = new MapLocationListener() {
        @Override
        public void onLocationChanged(com.xtc.map.location.MapLocation mapLocation) {
            Log.i(TAG, mapLocation.toString());
        }
    };
}
