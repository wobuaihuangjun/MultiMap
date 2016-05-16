package com.xtc.multimap.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.xtc.map.status.MapStatus;
import com.xtc.map.status.MapStatusUpdateFactory;
import com.xtc.multimap.R;
import com.xtc.map.LatLng;
import com.xtc.map.MapManager;
import com.xtc.map.location.Map;

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
        mapManager.setOnMapLoadedListener(new Map.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
                Log.i(TAG, "setOnMapLoadedListener");
                mapManager.updateMapStatus(MapStatusUpdateFactory.zoomTo(16));
            }
        });

        mapManager.setOnMapClickListener(new Map.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng var1) {
                Log.i(TAG, "setOnMapClickListener");
                mapManager.animateMapStatus(MapStatusUpdateFactory.newLatLng(var1), 1000);
            }
        });

        mapManager.setOnMapLongClickListener(new Map.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng var1) {
                Log.i(TAG, "setOnMapLongClickListener");
            }
        });

        mapManager.setOnMapTouchListener(new Map.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent var1) {
                Log.i(TAG, "setOnMapTouchListener");
            }
        });

        mapManager.setOnMapStatusChangeListener(new Map.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChange(MapStatus var1) {
                Log.i(TAG, "onMapStatusChange");
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus var1) {
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
