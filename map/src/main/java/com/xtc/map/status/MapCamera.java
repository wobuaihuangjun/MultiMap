package com.xtc.map.status;

import com.xtc.map.MapLatLng;

/**
 * 地图状态
 * <p/>
 * Created by hzj on 2016/5/13.
 */
public class MapCamera {
    /**
     * 目标位置的屏幕中心点经纬度坐标。
     */
    public MapLatLng target;
    /**
     * 目标可视区域的缩放级别
     */
    public Float zoom;

    /**
     * 目标可视区域的倾斜度，以角度为单位。
     */
    public Float tilt;
    /**
     * 可视区域指向的方向，以角度为单位，从正北向顺时针方向计算，从0度到360度。
     */
    public Float bearing;

    public MapCamera() {
    }

    public MapCamera(MapLatLng target, Float zoom, Float tilt, Float bearing) {
        this.target = target;
        this.zoom = zoom;
        this.tilt = tilt;
        this.bearing = bearing;
    }

    public static MapCamera.Builder builder() {
        return new MapCamera.Builder();
    }

    public static MapCamera.Builder builder(MapCamera var0) {
        return new MapCamera.Builder(var0);
    }

    @Override
    public String toString() {
        return "MapCamera{" +
                "target=" + target +
                ", zoom=" + zoom +
                ", tilt=" + tilt +
                ", bearing=" + bearing +
                '}';
    }

    public static class Builder {
        private MapLatLng target;
        private Float zoom;
        private Float tilt;
        private Float bearing;

        public Builder() {
        }

        public Builder(MapCamera var1) {
            this.target(var1.target).bearing(var1.bearing).tilt(var1.tilt).zoom(var1.zoom);
        }

        public MapCamera.Builder target(MapLatLng target) {
            this.target = target;
            return this;
        }

        public MapCamera.Builder zoom(float zoom) {
            this.zoom = zoom;
            return this;
        }

        public MapCamera.Builder tilt(float tilt) {
            this.tilt = tilt;
            return this;
        }

        public MapCamera.Builder bearing(float bearing) {
            this.bearing = bearing;
            return this;
        }

        public MapCamera build() { // 构建，返回一个新对象
            if (this.target == null) {
                throw new IllegalArgumentException("target can't null");
            }
            return new MapCamera(this.target, this.zoom, this.tilt, this.bearing);
        }
    }

}
