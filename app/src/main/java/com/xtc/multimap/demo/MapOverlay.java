package com.xtc.multimap.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xtc.map.MapManager;
import com.xtc.map.MapUISettings;
import com.xtc.map.overlay.BitmapDescriptorFactory;
import com.xtc.map.overlay.Circle;
import com.xtc.map.overlay.CircleOptions;
import com.xtc.map.overlay.Marker;
import com.xtc.map.overlay.MarkerOptions;
import com.xtc.map.overlay.Stroke;
import com.xtc.multimap.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapOverlay extends Activity {

    @Bind(R.id.map_view)
    RelativeLayout mapView;
    @Bind(R.id.change_map_mode)
    Button changeMapMode;

    private Bundle savedInstanceState;

    private MapManager mapManager;
    private MapUISettings mapUISettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.simple_map);
        ButterKnife.bind(this);

        changeMapMode.setText("Add Marker");

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
        mapUISettings = mapManager.getUISettings();
        mapUISettings.setAllGesturesEnabled(true);

        addMarker();
    }

    Marker marker;
    Circle circle;

    private void addMarker() {
        if (marker != null) {
            marker.remove();
        }
        marker = mapManager.addMarker(new MarkerOptions()
                .position(mapManager.getMapStatus().target)
                .title("好好学习")
                .icon(BitmapDescriptorFactory.fromResource(this, R.drawable.icon_marka))
                .flat(true)
                .draggable(false));
        marker.setAnchor(0.5F,1.0F);
        marker.showInfoWindow();// 设置默认显示一个infowinfow

        if (circle != null) {
            circle.remove();
        }
        circle = mapManager.addCircle(new CircleOptions()
                .center(mapManager.getMapStatus().target)
                .radius(4000.0)
                .fillColor(Color.argb(150, 1, 1, 1))
                .visible(true)
                .stroke(new Stroke(Color.argb(255, 125, 135, 135), 5)));
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
                addMarker();
                break;
        }
    }

}
