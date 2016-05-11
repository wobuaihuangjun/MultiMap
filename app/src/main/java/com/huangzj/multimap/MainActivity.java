package com.huangzj.multimap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends Activity {

    private MapView baiduMapView;

    private com.amap.api.maps.MapView aMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBaiMap();
//        setAMap(savedInstanceState);
    }

    private void setAMap(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        aMapView = (com.amap.api.maps.MapView) findViewById(R.id.map);
        aMapView.onCreate(savedInstanceState);// 此方法必须重写
    }

    private void setBaiMap() {
        Intent intent = getIntent();
        if (intent.hasExtra("x") && intent.hasExtra("y")) {
            // 当用intent参数时，设置中心点为指定点
            Bundle b = intent.getExtras();
            LatLng p = new LatLng(b.getDouble("y"), b.getDouble("x"));
            baiduMapView = new MapView(this,
                    new BaiduMapOptions().mapStatus(new MapStatus.Builder()
                            .target(p).build()));
        } else {
            baiduMapView = new MapView(this, new BaiduMapOptions());
        }

        setContentView(baiduMapView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        if (baiduMapView != null) baiduMapView.onPause();

        if (aMapView != null) aMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        if (baiduMapView != null) baiduMapView.onResume();

        if (aMapView != null) aMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // activity 销毁时同时销毁地图控件
        if (baiduMapView != null) baiduMapView.onDestroy();

        if (aMapView != null) aMapView.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
