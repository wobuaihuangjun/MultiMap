package com.xtc.map;

/**
 * 地图状态
 * <p/>
 * Created by hzj on 2016/5/13.
 */
public class MapStatus {
    /**
     * 目标位置的屏幕中心点经纬度坐标。
     */
    public LatLng target;
    /**
     * 目标可视区域的缩放级别
     */
    public float zoom;

    /**
     * 目标可视区域的倾斜度，以角度为单位。
     */
    public float tilt;
    /**
     * 可视区域指向的方向，以角度为单位，从正北向顺时针方向计算，从0度到360度。
     */
    public float bearing;

    public MapStatus() {
    }

    public MapStatus(LatLng target, float zoom, float tilt, float bearing) {
        this.target = target;
        this.zoom = zoom;
        this.tilt = tilt;
        this.bearing = bearing;
    }

    @Override
    public String toString() {
        return "MapStatus{" +
                "target=" + target +
                ", zoom=" + zoom +
                ", tilt=" + tilt +
                ", bearing=" + bearing +
                '}';
    }
}
