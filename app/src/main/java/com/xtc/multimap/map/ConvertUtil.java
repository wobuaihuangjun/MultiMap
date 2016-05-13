package com.xtc.multimap.map;

import com.amap.api.maps.model.CameraPosition;
import com.baidu.mapapi.map.MapPoi;

/**
 * 地图类型转换
 * <p/>
 * Created by hzj on 2016/5/13.
 */
public class ConvertUtil {

    /**
     * 转换百度坐标
     *
     * @param bdLatLng 百度坐标
     * @return
     */
    public static LatLng convertBdLatLng(com.baidu.mapapi.model.LatLng bdLatLng) {
        if (bdLatLng == null) {
            return null;
        }
        LatLng latLng = new LatLng();
        latLng.latitude = bdLatLng.latitude;
        latLng.longitude = bdLatLng.longitude;
        return latLng;
    }

    /**
     * 转换高德坐标
     *
     * @param gdLatLng 百度坐标
     * @return
     */
    public static LatLng convertGdLatLng(com.amap.api.maps.model.LatLng gdLatLng) {
        if (gdLatLng == null) {
            return null;
        }
        LatLng latLng = new LatLng();
        latLng.latitude = gdLatLng.latitude;
        latLng.longitude = gdLatLng.longitude;
        return latLng;
    }

    /**
     * 转换为百度坐标
     *
     * @param latLng 百度坐标
     * @return
     */
    public static com.baidu.mapapi.model.LatLng convertToBdLatLng(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new com.baidu.mapapi.model.LatLng(latLng.latitude, latLng.longitude);
    }

    /**
     * 转换为高德坐标
     *
     * @param latLng 百度坐标
     * @return
     */
    public static com.amap.api.maps.model.LatLng convertToGdLatLng(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new com.amap.api.maps.model.LatLng(latLng.latitude, latLng.longitude);
    }

    /**
     * 转换高德地图状态
     *
     * @param cameraPosition 高德地图状态
     * @return MapStatus
     */
    public static MapStatus convertGdMapStatus(CameraPosition cameraPosition) {
        MapStatus mapStatus = new MapStatus();
        if (cameraPosition != null) {
            mapStatus.target = convertGdLatLng(cameraPosition.target);
            mapStatus.tilt = cameraPosition.tilt;
            mapStatus.zoom = cameraPosition.zoom;// TODO: 2016/5/13 可能需要转换
            mapStatus.bearing = cameraPosition.bearing;
        }
        return mapStatus;
    }

    /**
     * 转换高德地图状态
     *
     * @param bdMapStatus 百度地图状态
     * @return MapStatus
     */
    public static MapStatus convertBdMapStatus(com.baidu.mapapi.map.MapStatus bdMapStatus) {
        MapStatus mapStatus = new MapStatus();
        if (bdMapStatus != null) {
            mapStatus.target = convertBdLatLng(bdMapStatus.target);
            mapStatus.tilt = bdMapStatus.overlook;
            mapStatus.zoom = bdMapStatus.zoom;// TODO: 2016/5/13 可能需要转换
            mapStatus.bearing = bdMapStatus.rotate;
        }
        return mapStatus;
    }

    /**
     * 转换百度Poi
     *
     * @param mapPoi 百度Poi
     * @return Poi
     */
    public static Poi convertBdPoi(MapPoi mapPoi) {
        Poi poi = new Poi();
        if (mapPoi != null) {
            poi.position = convertBdLatLng(mapPoi.getPosition());
            poi.name = mapPoi.getName();
        }
        return poi;
    }

    /**
     * 转换高德Poi
     *
     * @param gdPoi 高德Poi
     * @return Poi
     */
    public static Poi convertGdPoi(com.amap.api.maps.model.Poi gdPoi) {
        Poi poi = new Poi();
        if (gdPoi != null) {
            poi.position = convertGdLatLng(gdPoi.getCoordinate());
            poi.name = gdPoi.getName();
        }
        return poi;
    }

}
