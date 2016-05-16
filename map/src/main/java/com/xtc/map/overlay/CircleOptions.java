package com.xtc.map.overlay;

import com.xtc.map.LatLng;

/**
 * 圆形选项类
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class CircleOptions {

    LatLng center;
    Stroke stroke;
    Integer fillColor;
    Double radius;
    Boolean visible;
    Float zIndex;

    public CircleOptions center(LatLng center) {
        this.center = center;
        return this;
    }

    public CircleOptions stroke(Stroke stroke) {
        this.stroke = stroke;
        return this;
    }

    public CircleOptions fillColor(Integer fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public CircleOptions radius(Double radius) {
        this.radius = radius;
        return this;
    }

    public CircleOptions visible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public CircleOptions zIndex(Float zIndex) {
        this.zIndex = zIndex;
        return this;
    }
}
