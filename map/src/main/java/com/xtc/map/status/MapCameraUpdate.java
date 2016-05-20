package com.xtc.map.status;

import com.xtc.map.MapLatLng;

/**
 * 描述地图状态将要发生的变化
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class MapCameraUpdate {

    int type;
    MapCamera mapCamera;
    MapLatLng target;
    float zoom;
    float zoomIncrease;

    MapCameraUpdate(int var1) {
        this.type = var1;
    }

    public MapCamera getMapStatus(MapCamera currentStatus) {
        switch (this.type) {
            case 1:
                return this.mapCamera;
            case 2:
                return new MapCamera(this.target, currentStatus.zoom, currentStatus.tilt, currentStatus.bearing);
            case 3:
                return new MapCamera(this.target, this.zoom, currentStatus.tilt, currentStatus.bearing);
            case 4:
                return new MapCamera(this.target, currentStatus.zoom + zoomIncrease, currentStatus.tilt, currentStatus.bearing);
            case 5:
                return new MapCamera(currentStatus.target, this.zoom, currentStatus.tilt, currentStatus.bearing);
            default:
                return null;
        }
    }
}
