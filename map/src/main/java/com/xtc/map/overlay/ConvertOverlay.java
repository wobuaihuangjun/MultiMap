package com.xtc.map.overlay;

import android.support.annotation.NonNull;

import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.MarkerOptions;
import com.xtc.map.ConvertUtil;

/**
 * OverlayOption转换
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class ConvertOverlay {

    /**
     * 将MarkerOptions转换为高德类型
     *
     * @param options MapMarkerOptions
     * @return 高德类型MarkerOptions
     */
    public static MarkerOptions convertGdMarkerOptions(@NonNull MapMarkerOptions options) {
        MarkerOptions gdOption = new com.amap.api.maps.model.MarkerOptions();
        if (options.position != null) {
            gdOption.position(ConvertUtil.convertToGdLatLng(options.position));
        }
        if (options.icon != null) {
            gdOption.icon(com.amap.api.maps.model.BitmapDescriptorFactory
                    .fromBitmap(options.icon.bitmap));
        }
        if (options.anchorX != null && options.anchorY != null) {
            gdOption.anchor(options.anchorX, options.anchorY);
        }
        if (options.period != null) {
            gdOption.period(options.period);
        }
        if (options.title != null) {
            gdOption.title(options.title);
        }
        if (options.visible != null) {
            gdOption.visible(options.visible);
        }
        if (options.draggable != null) {
            gdOption.draggable(options.draggable);
        }
        if (options.flat != null) {
            gdOption.setFlat(options.flat);
        }
        return gdOption;
    }

    /**
     * 将MarkerOptions转换为百度类型
     *
     * @param options MapMarkerOptions
     * @return 百度类型MarkerOptions
     */
    public static com.baidu.mapapi.map.MarkerOptions convertBdMarkerOptions(@NonNull MapMarkerOptions options) {
        com.baidu.mapapi.map.MarkerOptions bdOption = new com.baidu.mapapi.map.MarkerOptions();
        if (options.position != null) {
            bdOption.position(ConvertUtil.convertToBdLatLng(options.position));
        }
        if (options.icon != null) {
            bdOption.icon(com.baidu.mapapi.map.BitmapDescriptorFactory
                    .fromBitmap(options.icon.bitmap));
        }
        if (options.anchorX != null && options.anchorY != null) {
            bdOption.anchor(options.anchorX, options.anchorY);
        }
        if (options.period != null) {
            bdOption.period(options.period);
        }
        if (options.title != null) {
            bdOption.title(options.title);
        }
        if (options.visible != null) {
            bdOption.visible(options.visible);
        }
        if (options.draggable != null) {
            bdOption.draggable(options.draggable);
        }
        if (options.flat != null) {
            bdOption.flat(options.flat);
        }
        return bdOption;
    }

    public static CircleOptions convertGdCircleOptions(@NonNull MapCircleOptions options) {
        CircleOptions gdOption = new com.amap.api.maps.model.CircleOptions();
        if (options.center != null) {
            gdOption.center(ConvertUtil.convertToGdLatLng(options.center));
        }
        if (options.stroke != null) {
            gdOption.strokeColor(options.stroke.color);
            gdOption.strokeWidth(options.stroke.strokeWidth);
        }
        if (options.fillColor != null) {
            gdOption.fillColor(options.fillColor);
        }
        if (options.radius != null) {
            gdOption.radius(options.radius.intValue());
        }
        if (options.visible != null) {
            gdOption.visible(options.visible);
        }
        if (options.zIndex != null) {
            gdOption.zIndex(options.zIndex.intValue());
        }
        return gdOption;
    }

    public static com.baidu.mapapi.map.CircleOptions convertBdCircleOptions(@NonNull MapCircleOptions options) {
        com.baidu.mapapi.map.CircleOptions bdOption = new com.baidu.mapapi.map.CircleOptions();
        if (options.center != null) {
            bdOption.center(ConvertUtil.convertToBdLatLng(options.center));
        }
        if (options.stroke != null) {
            bdOption.stroke(new com.baidu.mapapi.map.Stroke(
                    options.stroke.strokeWidth, options.stroke.color));
        }
        if (options.fillColor != null) {
            bdOption.fillColor(options.fillColor);
        }
        if (options.radius != null) {
            bdOption.radius(options.radius.intValue());
        }
        if (options.visible != null) {
            bdOption.visible(options.visible);
        }
        if (options.zIndex != null) {
            bdOption.zIndex(options.zIndex.intValue());
        }
        return bdOption;
    }
}
