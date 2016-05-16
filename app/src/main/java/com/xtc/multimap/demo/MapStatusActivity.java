package com.xtc.multimap.demo;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.xtc.map.MapManager;
import com.xtc.map.MapOptions;
import com.xtc.map.MapUISettings;
import com.xtc.map.location.Map;
import com.xtc.multimap.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapStatusActivity extends Activity {

    @Bind(R.id.map_view)
    RelativeLayout mapView;

    private Bundle savedInstanceState;

    private MapManager mapManager;
    private MapUISettings mapUISettings;

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
        mapUISettings = mapManager.getUISettings();
        mapUISettings.setAllGesturesEnabled(true);
        if (mapManager.getCurrentMapType() == MapManager.MAP_TYPE_AMAP) {
            mapUISettings.setZoomPosition(MapOptions.ZOOM_POSITION_RIGHT_CENTER);
        }
        mapManager.setOnMapLoadedListener(new Map.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
                if (mapManager.getCurrentMapType() == MapManager.MAP_TYPE_BD) {
                    mapUISettings.setZoomPosition(new Point(mapView.getWidth() - 80, mapView.getHeight() / 2 - 80));
                }
            }
        });
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
                break;
        }
    }

}
