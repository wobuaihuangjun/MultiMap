package com.xtc.multimap.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.xtc.map.MapLatLng;
import com.xtc.map.MapManager;
import com.xtc.map.location.MapLocationClient;
import com.xtc.map.location.MapLocationListener;
import com.xtc.map.location.MapLocationOption;
import com.xtc.map.overlay.PoiOverlay;
import com.xtc.map.search.MapPoiSearch;
import com.xtc.map.search.OnPoiSearchListener;
import com.xtc.map.search.PoiAroundSearchOption;
import com.xtc.map.search.PoiKeySearchOption;
import com.xtc.map.search.PoiSearchResult;
import com.xtc.map.status.MapCamera;
import com.xtc.map.status.MapCameraUpdateFactory;
import com.xtc.multimap.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hzj on 2016/5/23.
 */
public class PoiSearch extends Activity {


    @Bind(R.id.map_view)
    RelativeLayout mapView;
    @Bind(R.id.city)
    EditText city;
    @Bind(R.id.key)
    EditText key;

    private Bundle savedInstanceState;

    private MapManager mapManager;

    private MapPoiSearch poiSearch;

    private MapLatLng mapLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.map_poi_search);
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
        mapManager.getUISettings().setAllGesturesEnabled(true);
        startLocation();
        initSearch();
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
        option.setNeedAddress(true);
        mapLocationClient.setLocationOption(option);
        mapLocationClient.startLocation();
    }

    MapLocationListener locationListener = new MapLocationListener() {
        @Override
        public void onLocationChanged(com.xtc.map.location.MapLocation mapLocation) {
            if (mapLocation != null && mapLocation.getErrorCode() == com.xtc.map.location.MapLocation.SUCCESS) {
                if (mapLocationClient != null) {
                    mapLocationClient.stopLocation();
                }
                mapLatLng = new MapLatLng(mapLocation.getLatitude(), mapLocation.getLongitude());
                MapCamera status = new MapCamera.Builder().target(mapLatLng).zoom(18).build();
                mapManager.animateMapStatus(MapCameraUpdateFactory.newMapStatus(status), 1000);
            }

        }
    };

    private void initSearch() {
        poiSearch = new MapPoiSearch(this, mapManager.getCurrentMapType());
        poiSearch.setOnPoiSearchListener(new OnPoiSearchListener() {
            @Override
            public void onPoiItemSearched(PoiSearchResult result) {
                if (result.getErrorCode() == PoiSearchResult.NO_ERROR) {
                    mapManager.clear();
                    PoiOverlay overlay = new PoiOverlay(mapManager, result);
                    overlay.addToMap();
                    overlay.zoomToSpan();
                }
                Log.i("PoiSearchResult", result.toString());
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
        if (poiSearch != null) {
            poiSearch.destroy();
        }
    }

    private void keySearch() {
        poiSearch.searchKeyWord(new PoiKeySearchOption()
                .city(city.getText().toString())
                .keyWord(key.getText().toString())
                .pageNumber(0)
                .pageSize(10)
        );
    }

    private void nearbySearch() {
        poiSearch.searchNearby(new PoiAroundSearchOption()
                .location(mapLatLng)
                .radius(4000)
                .city(city.getText().toString())
                .keyWord(key.getText().toString())
                .pageNumber(0)
                .pageSize(10)
        );
    }

    @OnClick({R.id.change_map, R.id.key_search, R.id.nearby_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_map:
                initMap();
                break;
            case R.id.key_search:
                keySearch();
                break;
            case R.id.nearby_search:
                nearbySearch();
                break;
        }
    }
}
