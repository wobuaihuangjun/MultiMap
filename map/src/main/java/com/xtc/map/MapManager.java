package com.xtc.map;

import android.content.Context;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapView;
import com.xtc.map.status.MapStatus;


/**
 * 地图管理
 * <p/>
 * Created by hzj on 2016/5/11.
 */
public class MapManager extends BaseMapManager {

    public static final String TAG = "MapManager";

    /**
     * 高德地图
     */
    public static final int MAP_TYPE_AMAP = 1;
    /**
     * 百度地图
     */
    public static final int MAP_TYPE_BD = 2;


    private Context context;

    public MapManager(Context context) {
        super();
        this.context = context;
    }

    /**
     * 创建地图
     *
     * @param mapLayout 显现地图的父容器
     * @param type      地图类型
     */
    public void setMapView(ViewGroup mapLayout, int type) {
        if (currentMapType == type) {
            return;
        }
        currentMapType = type;
        if (MAP_TYPE_AMAP == type) {
            gdMapView = new com.amap.api.maps.MapView(context);
            gdMap = gdMapView.getMap();
            mapLayout.removeAllViews();
            mapLayout.addView(gdMapView);
            releaseBaiduMap();
        } else if (MAP_TYPE_BD == type) {
            bdMapView = new MapView(context, new BaiduMapOptions());
            bdMap = bdMapView.getMap();
            mapLayout.removeAllViews();
            mapLayout.addView(bdMapView);
            releaseAMap();
        } else {
            throw new IllegalArgumentException("unknown map type!");
        }
        mapChanged();
    }

    @Override
    protected void mapChanged() {
        if (MAP_TYPE_AMAP == currentMapType) {
            uiSettings.setGdMap(gdMap);
            mapProjection.setGdMap(gdMap);
        } else {
            uiSettings.setBdMap(bdMap, bdMapView);
            mapProjection.setBdMap(bdMap);
        }
    }

    /**
     * 获取地图ui控制器
     *
     * @return MapUISettings
     */
    public MapUISettings getUISettings() {
        return this.uiSettings;
    }

    /**
     * 获取地图投影坐标转换器.
     * <p/>
     * 如果是百度地图，当地图初始化完成之前返回null，在OnMapLoadedCallback.onMapLoaded()之后才能正常
     *
     * @return 地图投影坐标转换器
     */
    public MapProjection getMapProjection() {
        return this.mapProjection;
    }

    /**
     * 设置是否允许定位图层
     */
    public void setMyLocationEnabled(boolean enabled) {
        if (currentMapType == MAP_TYPE_AMAP) {
            gdMap.setMyLocationEnabled(enabled);
        } else {
            bdMap.setMyLocationEnabled(enabled);
        }
    }

    /**
     * 设置地图显示模式
     */
    public void setMapMode(int mapMode) {
        if (currentMapType == MAP_TYPE_AMAP) {
            if (mapMode == MapOptions.MAP_SATELLITE) {
                gdMap.setMapType(AMap.MAP_TYPE_SATELLITE);
            } else {
                gdMap.setMapType(AMap.MAP_TYPE_NORMAL);
            }
        } else if (currentMapType == MAP_TYPE_BD) {
            if (mapMode == MapOptions.MAP_SATELLITE) {
                bdMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
            } else {
                bdMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            }
        } else {
            throw new IllegalStateException("map view not init!");
        }
    }

    /**
     * 返回地图显示模式
     *
     * @return
     */
    public int getMapMode() {
        if (currentMapType == MAP_TYPE_AMAP) {
            if (AMap.MAP_TYPE_SATELLITE == gdMap.getMapType()) {
                return MapOptions.MAP_SATELLITE;
            } else {
                return MapOptions.MAP_NORMAL;
            }
        } else if (currentMapType == MAP_TYPE_BD) {
            if (BaiduMap.MAP_TYPE_SATELLITE == bdMap.getMapType()) {
                return MapOptions.MAP_SATELLITE;
            } else {
                return MapOptions.MAP_NORMAL;
            }
        } else {
            throw new IllegalStateException("map view not init!");
        }
    }

    /**
     * 获取地图最大缩放级别
     *
     * @return 地图最大缩放级别
     */
    public final float getMaxZoomLevel() {
        if (currentMapType == MAP_TYPE_AMAP) {
            return gdMap.getMaxZoomLevel();
        } else {
            return bdMap.getMaxZoomLevel();
        }
    }

    /**
     * 获取地图最小缩放级别
     *
     * @return 地图最小缩放级别
     */
    public final float getMinZoomLevel() {
        if (currentMapType == MAP_TYPE_AMAP) {
            return bdMap.getMinZoomLevel();
        } else {
            return bdMap.getMinZoomLevel();
        }
    }

    /**
     * 设置屏幕上的某个点为地图中心点。
     * <p/>
     * 高德地图可用
     * <p/>
     * 使用该方法设置后，地图将以所设置的屏幕坐标点为中心进行旋转、倾斜，而不是以当前屏幕正中心点。
     * 同时， moveCamera(CameraUpdate) 方法也将会以此坐标点为中心进行设置。
     *
     * @param x 屏幕上设置为地图中心点的 x 像素坐标，x 的范围为 0<= x <= 手机屏幕的像素宽度。
     * @param y 屏幕上设置为地图中心点的 y 像素坐标，y 的范围为 0<= y <= 手机屏幕的像素高度。
     */
    public void setPointToCenter(int x, int y) {
        if (currentMapType == MAP_TYPE_AMAP) {
            gdMap.setPointToCenter(x, y);
        }
    }

    /**
     * 清空地图所有的 Overlay 覆盖物以及 InfoWindow
     */
    public void clear() {
        if (bdMap != null) {
            bdMap.clear();
        }
        if (gdMap != null) {
            gdMap.clear();
        }
    }

}
