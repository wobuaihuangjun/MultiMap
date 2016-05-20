package com.xtc.map.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.xtc.map.MapManager;

/**
 * 定位服务类
 * <p/>
 * Created by hzj on 2016/5/11.
 */
public class MapLocationClient {

    private Context context;
    private int currentMapType;

    private MapLocationListener mapLocationListener;

    private LocationClient bdLocationClient;
    private AMapLocationClient aMapLocationClient;

    /**
     * 创建定位服务的客户端
     * <p/>
     * 地图切换后，需要重新创建
     *
     * @param context
     * @param currentMapType 当前地图类型
     */
    public MapLocationClient(Context context, int currentMapType) {
        this.context = context;
        this.currentMapType = currentMapType;
        init();
    }

    private void init() {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdLocationClient = new LocationClient(context);
        } else {
            aMapLocationClient = new AMapLocationClient(context);
        }
    }

    /**
     * 设置定位监听
     * <p/>
     * 地图切换后，需要重新设置
     *
     * @param listener MapLocationListener
     */
    public void setLocationListener(MapLocationListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener参数不能为null");
        }
        mapLocationListener = listener;
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdLocationClient.registerLocationListener(bdLocationListener);
        } else {
            aMapLocationClient.setLocationListener(aMapLocationListener);
        }
    }

    /**
     * 设置定位参数
     * <p/>
     * 地图切换后，需要重新设置
     */
    public void setLocationOption(MapLocationOption mapLocationOption) {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdLocationClient.setLocOption(
                    ConvertLocation.convertToBaiduLocationOption(mapLocationOption));
        } else {
            aMapLocationClient.setLocationOption(
                    ConvertLocation.convertToAMapLocationOption(mapLocationOption));
        }
    }

    public void startLocation() {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdLocationClient.start();
        } else {
            aMapLocationClient.startLocation();
        }
    }

    public void stopLocation() {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdLocationClient.stop();
        } else {
            aMapLocationClient.stopLocation();
        }
    }

    public MapLocation getLastKnownLocation() {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            return ConvertLocation.convertBaiduLocation(bdLocationClient.getLastKnownLocation());
        } else {
            return ConvertLocation.convertAMapLocation(aMapLocationClient.getLastKnownLocation());
        }
    }

    /**
     * 百度定位SDK监听函数
     */
    BDLocationListener bdLocationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (mapLocationListener != null) {
                mapLocationListener.onLocationChanged(
                        ConvertLocation.convertBaiduLocation(location));
            }
        }
    };
    /**
     * 高德定位SDK监听函数
     */
    private AMapLocationListener aMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (mapLocationListener != null) {
                mapLocationListener.onLocationChanged(
                        ConvertLocation.convertAMapLocation(aMapLocation));
            }
        }
    };
}
