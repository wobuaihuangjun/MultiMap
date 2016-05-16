package com.xtc.map;

import android.graphics.Point;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapView;

/**
 * 地图UI设置控制
 * <p/>
 * Created by hzj on 2016/5/11.
 */
public class MapUISettings {

    private int currentMapType;

    private AMap gdMap;

    private BaiduMap bdMap;
    private MapView bdMapView;

    MapUISettings() {
    }

    void setGdMap(AMap gdMap) {
        this.currentMapType = MapManager.MAP_TYPE_AMAP;
        this.gdMap = gdMap;
    }

    void setBdMap(BaiduMap bdMap, MapView bdMapView) {
        this.currentMapType = MapManager.MAP_TYPE_BD;
        this.bdMapView = bdMapView;
        this.bdMap = bdMap;
    }

    /**
     * 设置当前地图是否支持所有手势
     */
    public void setAllGesturesEnabled(boolean enabled) {
        this.setRotateGesturesEnabled(enabled);
        this.setScrollGesturesEnabled(enabled);
        this.setZoomGesturesEnabled(enabled);
        this.setTiltGesturesEnabled(enabled);
    }

    /**
     * 设置是否允许缩放手势
     */
    public void setZoomGesturesEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdMap.getUiSettings().setZoomGesturesEnabled(enabled);
        } else {
            gdMap.getUiSettings().setZoomGesturesEnabled(enabled);
        }
    }

    /**
     * 设置是否允许旋转手势
     */
    public void setRotateGesturesEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdMap.getUiSettings().setRotateGesturesEnabled(enabled);
        } else {
            gdMap.getUiSettings().setRotateGesturesEnabled(enabled);
        }
    }

    /**
     * 设置是否允许拖拽手势
     */
    public void setScrollGesturesEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdMap.getUiSettings().setScrollGesturesEnabled(enabled);
        } else {
            gdMap.getUiSettings().setScrollGesturesEnabled(enabled);
        }
    }

    /**
     * 设置是否允许通过手势倾斜地图
     */
    public void setTiltGesturesEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdMap.getUiSettings().setOverlookingGesturesEnabled(enabled);
        } else {
            gdMap.getUiSettings().setTiltGesturesEnabled(enabled);
        }
    }

    /**
     * 设置是否允许指南针
     */
    public void setCompassEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdMap.getUiSettings().setCompassEnabled(enabled);
        } else {
            gdMap.getUiSettings().setCompassEnabled(enabled);
        }
    }

    /**
     * 设置比例尺功能是否可用
     * <p/>
     *
     * @param enabled
     */
    public void setScaleControlsEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            gdMap.getUiSettings().setScaleControlsEnabled(enabled);
        } else {
            bdMapView.showScaleControl(enabled);
        }
    }

    /**
     * 这个方法设置了地图是否允许显示缩放按钮。如果允许，则在地图上显示。默认缩放按钮为显示。
     * <p/>
     *
     * @param enabled
     */
    public void setZoomControlsEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            gdMap.getUiSettings().setZoomControlsEnabled(enabled);
        } else {
            bdMapView.showZoomControls(enabled);
        }
    }

    /**
     * 设置缩放按钮的位置。
     * <p/>
     * 高德地图模式下可用,在 onMapLoadFinish 后生效
     *
     * @param position MapOptions
     */
    public void setZoomPosition(int position) {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            if (MapOptions.ZOOM_POSITION_RIGHT_BUTTOM == position) {
                gdMap.getUiSettings().setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_BUTTOM);
            } else if (MapOptions.ZOOM_POSITION_RIGHT_CENTER == position) {
                gdMap.getUiSettings().setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_CENTER);
            }
        }
    }

    /**
     * 设置缩放按钮的位置
     * <p/>
     * 百度地图可用
     *
     * @param point
     */
    public void setZoomPosition(Point point) {
        if (currentMapType == MapManager.MAP_TYPE_BD) {
            bdMapView.setZoomControlsPosition(point);
        }
    }

    /**
     * 设置地图Logo的位置。
     *
     * @param position MapOptions
     */
    public void setLogoPosition(int position) {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            if (MapOptions.LOGO_POSITION_BOTTOM_LEFT == position) {
                gdMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
            } else if (MapOptions.LOGO_POSITION_BOTTOM_CENTER == position) {
                gdMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_CENTER);
            } else if (MapOptions.LOGO_POSITION_BOTTOM_RIGHT == position) {
                gdMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_RIGHT);
            }
        } else {
            if (MapOptions.LOGO_POSITION_BOTTOM_LEFT == position) {
                bdMapView.setLogoPosition(LogoPosition.logoPostionleftBottom);
            } else if (MapOptions.LOGO_POSITION_BOTTOM_CENTER == position) {
                bdMapView.setLogoPosition(LogoPosition.logoPostionCenterBottom);
            } else if (MapOptions.LOGO_POSITION_BOTTOM_RIGHT == position) {
                bdMapView.setLogoPosition(LogoPosition.logoPostionRightBottom);
            }
        }

    }

}
