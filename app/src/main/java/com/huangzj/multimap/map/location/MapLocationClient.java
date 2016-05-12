package com.huangzj.multimap.map.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.baidu.location.LocationClient;
import com.huangzj.multimap.map.MapManager;

/**
 * 定位服务类
 * <p/>
 * Created by hzj on 2016/5/11.
 */
public class MapLocationClient {

    private Context context;
    private int currentMapType;

    private MapLocationListener mapLocationListener;

    private LocationClient baiduLocationClient;
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
        aMapLocationClient = new AMapLocationClient(context);
    }

    private void init() {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            baiduLocationClient = new LocationClient(context);
            aMapLocationClient.stopLocation();
        } else {
            aMapLocationClient = new AMapLocationClient(context);
            baiduLocationClient.stop();
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
        aMapLocationClient.setLocationListener(aMapLocationListener);
    }

    /**
     * 设置定位参数
     * <p/>
     * 地图切换后，需要重新设置
     */
    public void setLocationOption(MapLocationOption mapLocationOption) {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            baiduLocationClient.setLocOption(
                    ConvertLocation.convertToBaiduLocationOption(mapLocationOption));
        } else {
            aMapLocationClient.setLocationOption(
                    ConvertLocation.convertToAMapLocationOption(mapLocationOption));
        }
    }

    public void startLocation() {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            baiduLocationClient.start();
        } else {
            aMapLocationClient.startLocation();
        }
    }

    public void stopLocation() {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            baiduLocationClient.stop();
        } else {
            aMapLocationClient.stopLocation();
        }
    }

    public MapLocation getLastKnownLocation() {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            return ConvertLocation.convertBaiduLocation(baiduLocationClient.getLastKnownLocation());
        } else {
            return ConvertLocation.convertAMapLocation(aMapLocationClient.getLastKnownLocation());
        }
    }

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
