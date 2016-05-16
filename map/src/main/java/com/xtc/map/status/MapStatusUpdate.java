package com.xtc.map.status;

import com.xtc.map.LatLng;

/**
 * 描述地图状态将要发生的变化
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class MapStatusUpdate {

    int type;
    MapStatus mapStatus;
    LatLng target;
    float zoom;
    float zoomIncrease;

    MapStatusUpdate(int var1) {
        this.type = var1;
    }

    public MapStatus getMapStatus(MapStatus currentStatus) {
        switch (this.type) {
            case 1:
                return this.mapStatus;
            case 2:
                return new MapStatus(this.target, currentStatus.zoom, currentStatus.tilt, currentStatus.bearing);
            case 3:
                return new MapStatus(this.target, this.zoom, currentStatus.tilt, currentStatus.bearing);
            case 4:
                return new MapStatus(this.target, currentStatus.zoom + zoomIncrease, currentStatus.tilt, currentStatus.bearing);
            case 5:
                return new MapStatus(currentStatus.target, this.zoom, currentStatus.tilt, currentStatus.bearing);
            default:
                return null;
        }
    }
}
