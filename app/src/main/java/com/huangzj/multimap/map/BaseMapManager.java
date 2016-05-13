package com.huangzj.multimap.map;

import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

/**
 * 地图管理的基类
 * <p/>
 * Created by hzj on 2016/5/11.
 */
public abstract class BaseMapManager {

    protected MapView bdMapView;
    protected BaiduMap bdMap;

    protected com.amap.api.maps.MapView aMapView;
    protected AMap aMap;

    protected int currentMapType;

    protected MapUISettings uiSettings;

    public BaseMapManager() {
        uiSettings = new MapUISettings();
    }

    /**
     * 获取当前地图类型
     */
    public int getCurrentMapType() {
        return currentMapType;
    }

    /**
     * 地图切换了
     */
    protected abstract void mapChanged();

    protected int convertAMapMode(int type) {
        if (type == MapOptions.MAP_SATELLITE) {
            return AMap.MAP_TYPE_SATELLITE;
        } else {
            return AMap.MAP_TYPE_NORMAL;
        }
    }

    protected int convertBaiduMapMode(int type) {
        if (type == MapOptions.MAP_SATELLITE) {
            return BaiduMap.MAP_TYPE_SATELLITE;
        } else {
            return BaiduMap.MAP_TYPE_NORMAL;
        }
    }

    protected int getAMapMode() {
        if (AMap.MAP_TYPE_SATELLITE == aMap.getMapType()) {
            return MapOptions.MAP_SATELLITE;
        } else {
            return MapOptions.MAP_NORMAL;
        }
    }

    protected int getBaiduMode() {
        if (BaiduMap.MAP_TYPE_SATELLITE == bdMap.getMapType()) {
            return MapOptions.MAP_SATELLITE;
        } else {
            return MapOptions.MAP_NORMAL;
        }
    }

    /**
     * 如果是显示高德地图，在创建mapview后此方法必须调用
     */
    public void onCreate(Bundle bundle) {
        if (aMapView != null) aMapView.onCreate(bundle);
    }

    /**
     * activity暂停时同时暂停地图控件
     */
    public void onPause() {
        if (bdMapView != null) bdMapView.onPause();

        if (aMapView != null) aMapView.onPause();
    }

    /**
     * activity恢复时同时恢复地图控件
     */
    public void onResume() {
        if (bdMapView != null) bdMapView.onResume();

        if (aMapView != null) aMapView.onResume();
    }

    /**
     * activity销毁时同时销毁地图控件
     */
    public void onDestroy() {
        if (bdMapView != null) bdMapView.onDestroy();

        if (aMapView != null) aMapView.onDestroy();
    }

    protected void releaseBaiduMap() {
        if (bdMapView != null) {
            bdMapView.onDestroy();
            bdMapView = null;
            bdMap = null;
        }

    }

    protected void releaseAMap() {
        if (aMapView != null) {
            aMapView.onDestroy();
            aMapView = null;
            aMap = null;

        }
    }
}
