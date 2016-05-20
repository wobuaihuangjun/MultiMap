package com.xtc.map.overlay;


import com.xtc.map.MapLatLng;

/**
 * 定义如何渲染Marker 的属性
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class MapMarkerOptions {

    MapLatLng position;

    MapBitmap icon;

    Float anchorX;
    Float anchorY;

    Integer period;

    String title;

    Float zIndex;
    Boolean visible;
    Boolean draggable;
    Boolean flat;

    public MapMarkerOptions position(MapLatLng position) {
        this.position = position;
        return this;
    }

    public MapMarkerOptions icon(MapBitmap icon) {
        this.icon = icon;
        return this;
    }

    public MapMarkerOptions anchor(Float anchorX, Float anchorY) {
        this.anchorX = anchorX;
        this.anchorY = anchorY;
        return this;
    }

    public MapMarkerOptions period(Integer period) {
        this.period = period;
        return this;
    }

    public MapMarkerOptions title(String title) {
        this.title = title;
        return this;
    }

    public MapMarkerOptions zIndex(Float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public MapMarkerOptions visible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public MapMarkerOptions draggable(Boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    public MapMarkerOptions flat(Boolean flat) {
        this.flat = flat;
        return this;
    }
}
