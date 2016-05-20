package com.xtc.map;

import android.graphics.Point;

import com.amap.api.maps.AMap;
import com.baidu.mapapi.map.BaiduMap;

/**
 * 这个类负责将屏幕位置和地理坐标（经纬度）进行转换。屏幕位置是相对地图的左上角的位置，所以并不一定是从整个屏幕开始计算的。
 * <p/>
 * Created by hzj on 2016/5/14.
 */
public class MapProjection {

    private int currentMapType;

    private AMap gdMap;

    private BaiduMap bdMap;

    MapProjection() {
    }

    void setGdMap(AMap gdMap) {
        this.currentMapType = MapManager.MAP_TYPE_AMAP;
        this.gdMap = gdMap;
    }

    void setBdMap(BaiduMap bdMap) {
        this.currentMapType = MapManager.MAP_TYPE_BD;
        this.bdMap = bdMap;
    }

    /**
     * 将屏幕坐标转换成地理坐标
     *
     * @param point 屏幕坐标 如果传入null 则返回null
     * @return 地理坐标
     */
    public MapLatLng fromScreenLocation(Point point) {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            return ConvertUtil.convertGdLatLng(gdMap.getProjection().fromScreenLocation(point));
        } else {
            return ConvertUtil.convertBdLatLng(bdMap.getProjection().fromScreenLocation(point));
        }
    }

    /**
     * 将地理坐标转换成屏幕坐标
     *
     * @param location location - 地理坐标 如果传入 null 则返回null
     * @return 屏幕坐标
     */
    public Point toScreenLocation(MapLatLng location) {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            return gdMap.getProjection().toScreenLocation(ConvertUtil.convertToGdLatLng(location));
        } else {
            return bdMap.getProjection().toScreenLocation(ConvertUtil.convertToBdLatLng(location));
        }
    }

}
