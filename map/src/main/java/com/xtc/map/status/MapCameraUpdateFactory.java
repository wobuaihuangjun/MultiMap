package com.xtc.map.status;

import com.xtc.map.MapLatLng;

/**
 * 生成地图状态将要发生的变化
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class MapCameraUpdateFactory {

    MapCameraUpdateFactory() {
    }

    public static MapCameraUpdate newMapStatus(MapCamera var0) {
        if (var0 == null) {
            return null;
        } else {
            MapCameraUpdate var1 = new MapCameraUpdate(1);
            var1.mapCamera = var0;
            return var1;
        }
    }

    public static MapCameraUpdate newLatLng(MapLatLng var0) {
        if (var0 == null) {
            return null;
        } else {
            MapCameraUpdate var1 = new MapCameraUpdate(2);
            var1.target = var0;
            return var1;
        }
    }

    public static MapCameraUpdate newLatLngZoom(MapLatLng var0, float var1) {
        MapCameraUpdate var2 = new MapCameraUpdate(3);
        if (var0 == null) {
            return null;
        } else {
            var2.target = var0;
            var2.zoom = var1;
            return var2;
        }
    }

    public static MapCameraUpdate zoomIn() {
        MapCameraUpdate var0 = new MapCameraUpdate(4);
        var0.zoomIncrease = 1.0F;
        return var0;
    }

    public static MapCameraUpdate zoomOut() {
        MapCameraUpdate var0 = new MapCameraUpdate(4);
        var0.zoomIncrease = -1.0F;
        return var0;
    }

    public static MapCameraUpdate zoomTo(float var0) {
        MapCameraUpdate var1 = new MapCameraUpdate(5);
        var1.zoom = var0;
        return var1;
    }

}
