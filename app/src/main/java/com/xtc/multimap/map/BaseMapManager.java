package com.xtc.multimap.map;

import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.CameraPosition;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.xtc.multimap.map.location.Map;

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

//    public final void moveCamera(CameraUpdate var1) {
//    }
//
//    public final void animateCamera(CameraUpdate var1) {
//    }
//
//    public final void animateCamera(CameraUpdate var1, AMap.CancelableCallback var2) {
//    }
//
//    public final void animateCamera(CameraUpdate var1, long var2, AMap.CancelableCallback var4) {
//    }

    public final void setOnCameraChangeListener(Map.OnCameraChangeListener listener) {
        if(listener == null){
            return;
        }
//        if (bdMap != null) {
//            bdMap.setOnCa
//        }
//        if (aMap != null){
//            aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
//                @Override
//                public void onCameraChange(CameraPosition cameraPosition) {
//                    listener.onCameraChange();
//                }
//
//                @Override
//                public void onCameraChangeFinish(CameraPosition cameraPosition) {
//                    listener.onCameraChangeFinish();
//                }
//            });
//        }
    }

    public final void setOnMapClickListener(Map.OnMapClickListener var1) {
    }

    public final void setOnMapTouchListener(Map.OnMapTouchListener var1) {
    }

    public final void setOnPOIClickListener(Map.OnPOIClickListener var1) {
    }

    public final void setOnMyLocationChangeListener(Map.OnMyLocationChangeListener var1) {
    }

    public final void setOnMapLongClickListener(Map.OnMapLongClickListener var1) {
    }

    public final void setOnMarkerClickListener(Map.OnMarkerClickListener var1) {
    }

    public final void setOnPolylineClickListener(Map.OnPolylineClickListener var1) {
    }

    public final void setOnMarkerDragListener(Map.OnMarkerDragListener var1) {
    }

    public final void setOnInfoWindowClickListener(Map.OnInfoWindowClickListener var1) {
    }

    public final void setOnMapLoadedListener(Map.OnMapLoadedListener var1) {
    }

}
