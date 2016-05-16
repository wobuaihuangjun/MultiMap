package com.xtc.map.status;

import com.xtc.map.LatLng;

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
    public Float zoom;

    /**
     * 目标可视区域的倾斜度，以角度为单位。
     */
    public Float tilt;
    /**
     * 可视区域指向的方向，以角度为单位，从正北向顺时针方向计算，从0度到360度。
     */
    public Float bearing;

    public MapStatus() {
    }

    public MapStatus(LatLng target, Float zoom, Float tilt, Float bearing) {
        this.target = target;
        this.zoom = zoom;
        this.tilt = tilt;
        this.bearing = bearing;
    }

    public static MapStatus.Builder builder() {
        return new MapStatus.Builder();
    }

    public static MapStatus.Builder builder(MapStatus var0) {
        return new MapStatus.Builder(var0);
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

    public static class Builder {
        private LatLng target;
        private Float zoom;
        private Float tilt;
        private Float bearing;

        public Builder() {
        }

        public Builder(MapStatus var1) {
            this.target(var1.target).bearing(var1.bearing).tilt(var1.tilt).zoom(var1.zoom);
        }

        public MapStatus.Builder target(LatLng target) {
            this.target = target;
            return this;
        }

        public MapStatus.Builder zoom(float zoom) {
            this.zoom = zoom;
            return this;
        }

        public MapStatus.Builder tilt(float tilt) {
            this.tilt = tilt;
            return this;
        }

        public MapStatus.Builder bearing(float bearing) {
            this.bearing = bearing;
            return this;
        }

        public MapStatus build() { // 构建，返回一个新对象
            if (this.target == null) {
                throw new IllegalArgumentException("target can't null");
            }
            return new MapStatus(this.target, this.zoom, this.tilt, this.bearing);
        }
    }

}
