package com.xtc.multimap.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.xtc.map.MapManager;
import com.xtc.map.MapOptions;
import com.xtc.map.MapUISettings;
import com.xtc.multimap.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SimpleMap extends Activity {

    private static final String TAG = "SimpleMap";

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

    private void initMap() {
        if (mapManager.getCurrentMapType() == MapManager.MAP_TYPE_BD) {
            mapManager.setMapView(mapView, MapManager.MAP_TYPE_AMAP);
            mapManager.onCreate(savedInstanceState);
        } else {
            mapManager.setMapView(mapView, MapManager.MAP_TYPE_BD);
        }

        initUISettings();
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
        mapManager.onDestroy();
    }

    @OnClick({R.id.change_map, R.id.change_map_mode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_map:
                initMap();
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

}
