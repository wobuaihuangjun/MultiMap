package com.xtc.map.overlay;

import com.xtc.map.MapLatLng;

/**
 * 圆形选项类
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class MapCircleOptions {

    MapLatLng center;
    Stroke stroke;
    Integer fillColor;
    Double radius;
    Boolean visible;
    Float zIndex;

    public MapCircleOptions center(MapLatLng center) {
        this.center = center;
        return this;
    }

    public MapCircleOptions stroke(Stroke stroke) {
        this.stroke = stroke;
        return this;
    }

    public MapCircleOptions fillColor(Integer fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public MapCircleOptions radius(Double radius) {
        this.radius = radius;
        return this;
    }

    public MapCircleOptions visible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public MapCircleOptions zIndex(Float zIndex) {
        this.zIndex = zIndex;
        return this;
    }
}
