package com.xtc.multimap.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.xtc.map.MapLatLng;
import com.xtc.map.MapManager;
import com.xtc.map.search.CodeOption;
import com.xtc.map.search.CodeResult;
import com.xtc.map.search.CoderSearch;
import com.xtc.map.search.OnCoderResultListener;
import com.xtc.map.search.ReCodeOption;
import com.xtc.map.search.ReCodeResult;
import com.xtc.multimap.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 搜索服务demo
 * <p/>
 * Created by hzj on 2016/5/17.
 */
public class GeocoderActivity extends Activity {


    @Bind(R.id.city)
    EditText city;
    @Bind(R.id.geocodekey)
    EditText address;
    @Bind(R.id.lat)
    EditText lat;
    @Bind(R.id.lon)
    EditText lon;
    @Bind(R.id.map_view)
    RelativeLayout mapView;

    private Bundle savedInstanceState;

    private MapManager mapManager;

    private CoderSearch coderSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.map_geocode_search);
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

        initSearch();
    }

    private void initSearch() {
        coderSearch = new CoderSearch(this, mapManager.getCurrentMapType());
        coderSearch.setOnGetGeoCodeResultListener(new OnCoderResultListener() {
            @Override
            public void onGeocodeSearched(CodeResult codeResult) {
                Log.i("GeocoderActivity", codeResult.toString());
            }

            @Override
            public void onRegeocodeSearched(ReCodeResult reCodeResult) {
                Log.i("GeocoderActivity", reCodeResult.toString());
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
        if (coderSearch != null) {
            coderSearch.destroy();
        }
    }

    private void geoCodeSearch() {
        coderSearch.geocode(new CodeOption().address(address.getText().toString()).city(city.getText().toString()));
    }

    private void reGeoCodeSearch() {
        MapLatLng ptCenter = new MapLatLng((Float.valueOf(lat.getText()
                .toString())), (Float.valueOf(lon.getText().toString())));
        coderSearch.reGeoCode(new ReCodeOption().location(ptCenter));
    }

    @OnClick({R.id.change_map, R.id.geocode, R.id.reversegeocode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_map:
                initMap();
                break;
            case R.id.geocode:
                geoCodeSearch();
                break;
            case R.id.reversegeocode:
                reGeoCodeSearch();
                break;
        }
    }
}
