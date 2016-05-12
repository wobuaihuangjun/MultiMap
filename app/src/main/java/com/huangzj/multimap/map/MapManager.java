package com.huangzj.multimap.map;

import android.content.Context;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapView;


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
    public static final int MAP_TYPE_BAIDU = 2;


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
            aMapView = new com.amap.api.maps.MapView(context);
            aMap = aMapView.getMap();
            mapLayout.removeAllViews();
            mapLayout.addView(aMapView);
            releaseBaiduMap();
        } else if (MAP_TYPE_BAIDU == type) {
            baiduMapView = new MapView(context, new BaiduMapOptions());
            baiduMap = baiduMapView.getMap();
            mapLayout.removeAllViews();
            mapLayout.addView(baiduMapView);
            releaseAMap();
        } else {
            throw new IllegalArgumentException("unknown map type!");
        }
    }

    @Override
    protected void mapChanged() {
        uiSettings.init(currentMapType, aMap, baiduMap);

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
     * 设置是否允许定位图层
     */
    public void setMyLocationEnabled(boolean enabled) {
        if (currentMapType == MAP_TYPE_AMAP) {
            aMap.setMyLocationEnabled(enabled);
        } else if (currentMapType == MAP_TYPE_BAIDU) {
            baiduMap.setMyLocationEnabled(enabled);
        } else {
            throw new IllegalStateException("map view not init!");
        }
    }

    /**
     * 获取是否允许定位图层
     *
     * @return 是否允许定位图层
     */
    public boolean isMyLocationEnabled() {
        if (currentMapType == MAP_TYPE_AMAP) {
            return aMap.isMyLocationEnabled();
        } else if (currentMapType == MAP_TYPE_BAIDU) {
            return baiduMap.isMyLocationEnabled();
        } else {
            return false;
        }
    }

    /**
     * 设置地图显示模式
     */
    public void setMapMode(int mapMode) {
        if (currentMapType == MAP_TYPE_AMAP) {
            aMap.setMapType(convertAMapMode(mapMode));
        } else if (currentMapType == MAP_TYPE_BAIDU) {
            baiduMap.setMapType(convertBaiduMapMode(mapMode));
        } else {
            throw new IllegalStateException("map view not init!");
        }
    }

    /**
     * 读取地图显示模式
     *
     * @return
     */
    public int getMapMode() {
        if (currentMapType == MAP_TYPE_AMAP) {
            return getAMapMode();
        } else if (currentMapType == MAP_TYPE_BAIDU) {
            return getBaiduMode();
        } else {
            throw new IllegalStateException("map view not init!");
        }
    }


}
