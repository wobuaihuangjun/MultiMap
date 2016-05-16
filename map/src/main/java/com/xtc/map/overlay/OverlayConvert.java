package com.xtc.map.overlay;

import android.support.annotation.NonNull;

import com.xtc.map.ConvertUtil;

/**
 * OverlayOption转换
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class OverlayConvert {

    /**
     * 将MarkerOptions转换为高德类型
     *
     * @param options MarkerOptions
     * @return 高德类型MarkerOptions
     */
    public static com.amap.api.maps.model.MarkerOptions convertGdMarkerOptions(@NonNull MarkerOptions options) {
        com.amap.api.maps.model.MarkerOptions gdOption = new com.amap.api.maps.model.MarkerOptions();
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
     * @param options MarkerOptions
     * @return 百度类型MarkerOptions
     */
    public static com.baidu.mapapi.map.MarkerOptions convertBdMarkerOptions(@NonNull MarkerOptions options) {
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
}
