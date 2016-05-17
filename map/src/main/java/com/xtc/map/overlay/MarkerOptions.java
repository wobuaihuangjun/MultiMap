package com.xtc.map.overlay;


import com.xtc.map.LatLng;

/**
 * 定义如何渲染Marker 的属性
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class MarkerOptions extends OverlayOptions {

    LatLng position;

    BitmapDescriptor icon;

    Float anchorX;
    Float anchorY;

    Integer period;

    String title;

    Float zIndex;
    Boolean visible;
    Boolean draggable;
    Boolean flat;

    public MarkerOptions position(LatLng position) {
        this.position = position;
        return this;
    }

    public MarkerOptions icon(BitmapDescriptor icon) {
        this.icon = icon;
        return this;
    }

    public MarkerOptions anchor(Float anchorX, Float anchorY) {
        this.anchorX = anchorX;
        this.anchorY = anchorY;
        return this;
    }

    public MarkerOptions period(Integer period) {
        this.period = period;
        return this;
    }

    public MarkerOptions title(String title) {
        this.title = title;
        return this;
    }

    public MarkerOptions zIndex(Float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public MarkerOptions visible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public MarkerOptions draggable(Boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    public MarkerOptions flat(Boolean flat) {
        this.flat = flat;
        return this;
    }
}
