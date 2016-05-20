package com.xtc.multimap.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.xtc.map.status.MapCamera;
import com.xtc.map.status.MapCameraUpdateFactory;
import com.xtc.multimap.R;
import com.xtc.map.MapLatLng;
import com.xtc.map.MapManager;
import com.xtc.map.location.MapInterface;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapEventListener extends Activity {
    private static final String TAG = "MapEventListener";

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
        setMapEventListener();
    }

    private void setMapEventListener() {
        mapManager.setOnMapLoadedListener(new MapInterface.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
                Log.i(TAG, "setOnMapLoadedListener");
                mapManager.updateMapStatus(MapCameraUpdateFactory.zoomTo(16));
            }
        });

        mapManager.setOnMapClickListener(new MapInterface.OnMapClickListener() {
            @Override
            public void onMapClick(MapLatLng var1) {
                Log.i(TAG, "setOnMapClickListener");
                mapManager.animateMapStatus(MapCameraUpdateFactory.newLatLng(var1), 1000);
            }
        });

        mapManager.setOnMapLongClickListener(new MapInterface.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(MapLatLng var1) {
                Log.i(TAG, "setOnMapLongClickListener");
            }
        });

        mapManager.setOnMapTouchListener(new MapInterface.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent var1) {
                Log.i(TAG, "setOnMapTouchListener");
            }
        });

        mapManager.setOnMapStatusChangeListener(new MapInterface.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChange(MapCamera var1) {
                Log.i(TAG, "onMapStatusChange");
            }

            @Override
            public void onMapStatusChangeFinish(MapCamera var1) {
                Log.i(TAG, "onMapStatusChangeFinish");
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
        }
    }

}
