package com.xtc.map.status;

import com.xtc.map.LatLng;

/**
 * 生成地图状态将要发生的变化
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class MapStatusUpdateFactory {

    MapStatusUpdateFactory() {
    }

    public static MapStatusUpdate newMapStatus(MapStatus var0) {
        if (var0 == null) {
            return null;
        } else {
            MapStatusUpdate var1 = new MapStatusUpdate(1);
            var1.mapStatus = var0;
            return var1;
        }
    }

    public static MapStatusUpdate newLatLng(LatLng var0) {
        if (var0 == null) {
            return null;
        } else {
            MapStatusUpdate var1 = new MapStatusUpdate(2);
            var1.target = var0;
            return var1;
        }
    }

    public static MapStatusUpdate newLatLngZoom(LatLng var0, float var1) {
        MapStatusUpdate var2 = new MapStatusUpdate(3);
        if (var0 == null) {
            return null;
        } else {
            var2.target = var0;
            var2.zoom = var1;
            return var2;
        }
    }

    public static MapStatusUpdate zoomIn() {
        MapStatusUpdate var0 = new MapStatusUpdate(4);
        var0.zoomIncrease = 1.0F;
        return var0;
    }

    public static MapStatusUpdate zoomOut() {
        MapStatusUpdate var0 = new MapStatusUpdate(4);
        var0.zoomIncrease = -1.0F;
        return var0;
    }

    public static MapStatusUpdate zoomTo(float var0) {
        MapStatusUpdate var1 = new MapStatusUpdate(5);
        var1.zoom = var0;
        return var1;
    }

}
