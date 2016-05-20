package com.xtc.map;

import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Poi;
import com.baidu.mapapi.map.MapStatus;
import com.xtc.map.status.MapCamera;

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
    public static MapLatLng convertBdLatLng(com.baidu.mapapi.model.LatLng bdLatLng) {
        if (bdLatLng == null) {
            return null;
        }
        MapLatLng mapLatLng = new MapLatLng();
        mapLatLng.latitude = bdLatLng.latitude;
        mapLatLng.longitude = bdLatLng.longitude;
        return mapLatLng;
    }

    /**
     * 转换高德坐标
     *
     * @param gdLatLng 百度坐标
     * @return
     */
    public static MapLatLng convertGdLatLng(com.amap.api.maps.model.LatLng gdLatLng) {
        if (gdLatLng == null) {
            return null;
        }
        MapLatLng mapLatLng = new MapLatLng();
        mapLatLng.latitude = gdLatLng.latitude;
        mapLatLng.longitude = gdLatLng.longitude;
        return mapLatLng;
    }

    /**
     * 转换为百度坐标
     *
     * @param mapLatLng 百度坐标
     * @return
     */
    public static com.baidu.mapapi.model.LatLng convertToBdLatLng(MapLatLng mapLatLng) {
        if (mapLatLng == null) {
            return null;
        }
        return new com.baidu.mapapi.model.LatLng(mapLatLng.latitude, mapLatLng.longitude);
    }

    /**
     * 转换为高德坐标
     *
     * @param mapLatLng 百度坐标
     * @return
     */
    public static com.amap.api.maps.model.LatLng convertToGdLatLng(MapLatLng mapLatLng) {
        if (mapLatLng == null) {
            return null;
        }
        return new com.amap.api.maps.model.LatLng(mapLatLng.latitude, mapLatLng.longitude);
    }

    /**
     * 转换高德地图状态
     *
     * @param cameraPosition 高德地图状态
     * @return MapCamera
     */
    public static MapCamera convertGdMapStatus(CameraPosition cameraPosition) {
        MapCamera mapCamera = new MapCamera();
        if (cameraPosition != null) {
            mapCamera.target = convertGdLatLng(cameraPosition.target);
            mapCamera.tilt = cameraPosition.tilt;
            mapCamera.zoom = cameraPosition.zoom;// TODO: 2016/5/13 可能需要转换
            mapCamera.bearing = cameraPosition.bearing;
        }
        return mapCamera;
    }

    /**
     * 转换百度地图状态
     *
     * @param bdMapStatus 百度地图状态
     * @return MapCamera
     */
    public static MapCamera convertBdMapStatus(com.baidu.mapapi.map.MapStatus bdMapStatus) {
        MapCamera mapCamera = new MapCamera();
        if (bdMapStatus != null) {
            mapCamera.target = convertBdLatLng(bdMapStatus.target);
            mapCamera.tilt = bdMapStatus.overlook;
            mapCamera.zoom = bdMapStatus.zoom;// TODO: 2016/5/13 可能需要转换
            mapCamera.bearing = bdMapStatus.rotate;
        }
        return mapCamera;
    }

    /**
     * 转换高德地图状态
     *
     * @param mapCamera 地图状态
     * @return CameraPosition
     */
    public static CameraPosition convertToGdMapStatus(MapCamera mapCamera) {
        if (mapCamera == null) {
            return null;
        }
        CameraPosition.Builder builder = CameraPosition.builder();
        if (mapCamera.target != null) {
            com.amap.api.maps.model.LatLng latLng = convertToGdLatLng(mapCamera.target);
            builder.target(latLng);
        }
        if (mapCamera.zoom != null) {
            builder.zoom(mapCamera.zoom);
        }
        if (mapCamera.tilt != null) {
            builder.tilt(mapCamera.tilt);
        }
        if (mapCamera.bearing != null) {
            builder.bearing(mapCamera.bearing);
        }
        return builder.build();
    }

    /**
     * 转换为百度地图状态
     *
     * @param mapCamera 地图状态
     * @return 百度MapStatus
     */
    public static MapStatus convertToBdMapStatus(MapCamera mapCamera) {
        if (mapCamera == null) {
            return null;
        }
        MapStatus.Builder builder = new com.baidu.mapapi.map.MapStatus.Builder();
        if (mapCamera.target != null) {
            com.baidu.mapapi.model.LatLng latLng = convertToBdLatLng(mapCamera.target);
            builder.target(latLng);
        }
        if (mapCamera.zoom != null) {
            builder.zoom(mapCamera.zoom);
        }
        if (mapCamera.tilt != null) {
            builder.overlook(mapCamera.tilt);
        }
        if (mapCamera.bearing != null) {
            builder.rotate(mapCamera.bearing);
        }
        return builder.build();
    }

    /**
     * 转换百度Poi
     *
     * @param mapMapPoi 百度Poi
     * @return MapPoi
     */
    public static MapPoi convertBdPoi(com.baidu.mapapi.map.MapPoi mapMapPoi) {
        MapPoi poi = new MapPoi();
        if (mapMapPoi != null) {
            poi.position = convertBdLatLng(mapMapPoi.getPosition());
            poi.name = mapMapPoi.getName();
        }
        return poi;
    }

    /**
     * 转换高德Poi
     *
     * @param gdPoi 高德Poi
     * @return MapPoi
     */
    public static MapPoi convertGdPoi(Poi gdPoi) {
        MapPoi mapPoi = new MapPoi();
        if (gdPoi != null) {
            mapPoi.position = convertGdLatLng(gdPoi.getCoordinate());
            mapPoi.name = gdPoi.getName();
        }
        return mapPoi;
    }

}
