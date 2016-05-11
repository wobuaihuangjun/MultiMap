package com.huangzj.multimap.map;

import android.graphics.Point;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.baidu.mapapi.map.BaiduMap;

/**
 * 地图UI设置控制
 * <p/>
 * Created by hzj on 2016/5/11.
 */
public class MapUISettings {

    private int currentMapType;

    private AMap aMap;

    private BaiduMap baiduMap;

    void setAMap(AMap aMap) {
        this.currentMapType = MapManager.MAP_TYPE_AMAP;
        this.aMap = aMap;
    }

    void setBaiduMap(BaiduMap baiduMap) {
        this.currentMapType = MapManager.MAP_TYPE_BAIDU;
        this.baiduMap = baiduMap;
    }

    void init(int currentMapType, AMap aMap, BaiduMap baiduMap) {
        this.currentMapType = currentMapType;
        this.aMap = aMap;
        this.baiduMap = baiduMap;
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
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            baiduMap.getUiSettings().setZoomGesturesEnabled(enabled);
        } else {
            aMap.getUiSettings().setZoomGesturesEnabled(enabled);
        }
    }

    public boolean isZoomGesturesEnabled() {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            return baiduMap.getUiSettings().isZoomGesturesEnabled();
        } else {
            return aMap.getUiSettings().isZoomGesturesEnabled();
        }
    }

    /**
     * 设置是否允许旋转手势
     */
    public void setRotateGesturesEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            baiduMap.getUiSettings().setRotateGesturesEnabled(enabled);
        } else {
            aMap.getUiSettings().setRotateGesturesEnabled(enabled);
        }
    }

    public boolean isRotateGesturesEnabled() {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            return baiduMap.getUiSettings().isRotateGesturesEnabled();
        } else {
            return aMap.getUiSettings().isRotateGesturesEnabled();
        }
    }

    /**
     * 设置是否允许拖拽手势
     */
    public void setScrollGesturesEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            baiduMap.getUiSettings().setScrollGesturesEnabled(enabled);
        } else {
            aMap.getUiSettings().setScrollGesturesEnabled(enabled);
        }
    }

    public boolean isScrollGesturesEnabled() {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            return baiduMap.getUiSettings().isScrollGesturesEnabled();
        } else {
            return aMap.getUiSettings().isScrollGesturesEnabled();
        }
    }

    /**
     * 设置是否允许通过手势倾斜地图
     */
    public void setTiltGesturesEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            baiduMap.getUiSettings().setOverlookingGesturesEnabled(enabled);
        } else {
            aMap.getUiSettings().setTiltGesturesEnabled(enabled);
        }
    }

    public boolean isTiltGesturesEnabled() {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            return baiduMap.getUiSettings().isOverlookingGesturesEnabled();
        } else {
            return aMap.getUiSettings().isTiltGesturesEnabled();
        }
    }

    /**
     * 设置是否允许指南针
     */
    public void setCompassEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            baiduMap.getUiSettings().setCompassEnabled(enabled);
        } else {
            aMap.getUiSettings().setCompassEnabled(enabled);
        }
    }

    public boolean isCompassEnabled() {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            return baiduMap.getUiSettings().isCompassEnabled();
        } else {
            return aMap.getUiSettings().isCompassEnabled();
        }
    }

    /**
     * 设置定位按钮是否显示。默认的定位按钮为显示。
     * <p/>
     * 高德地图模式下可用
     * <p/>
     * 这个按钮可以让当前可视区域移动到以用户所在位置为中心的地图上。
     * 在这里定位按钮显示同时还要保证定位层处于显示状态， 定位按钮才会显示。
     *
     * @param enabled
     */
    public void setMyLocationButtonEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            aMap.getUiSettings().setMyLocationButtonEnabled(enabled);
        }
    }

    public boolean isMyLocationButtonEnabled() {
        if (currentMapType != MapManager.MAP_TYPE_AMAP) {
            return false;
        }
        return aMap.getUiSettings().isMyLocationButtonEnabled();
    }

    /**
     * 设置比例尺功能是否可用
     * <p/>
     * 高德地图模式下可用
     *
     * @param enabled
     */
    public void setScaleControlsEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            aMap.getUiSettings().setScaleControlsEnabled(enabled);
        }
    }

    public boolean isScaleControlsEnabled() {
        if (currentMapType != MapManager.MAP_TYPE_AMAP) {
            return false;
        }
        return aMap.getUiSettings().isScaleControlsEnabled();
    }

    /**
     * 这个方法设置了地图是否允许显示缩放按钮。如果允许，则在地图上显示。默认缩放按钮为显示。
     * <p/>
     * 高德地图模式下可用
     *
     * @param enabled
     */
    public void setZoomControlsEnabled(boolean enabled) {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            aMap.getUiSettings().setZoomControlsEnabled(enabled);
        }
    }

    public boolean isZoomControlsEnabled() {
        if (currentMapType != MapManager.MAP_TYPE_AMAP) {
            return false;
        }
        return aMap.getUiSettings().isZoomControlsEnabled();
    }

    /**
     * 设置缩放按钮的位置。
     * <p/>
     * 高德地图模式下可用
     *
     * @param position
     */
    public void setZoomPosition(int position) {
        if (currentMapType != MapManager.MAP_TYPE_AMAP) {
            return;
        }
        if (MapOptions.ZOOM_POSITION_RIGHT_BUTTOM == position) {
            aMap.getUiSettings().setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_BUTTOM);
        } else if (MapOptions.ZOOM_POSITION_RIGHT_CENTER == position) {
            aMap.getUiSettings().setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_CENTER);
        }
    }

    public int getZoomPosition() {
        if (currentMapType != MapManager.MAP_TYPE_AMAP) {
            return MapOptions.UNKNOWN;
        }
        int type = aMap.getUiSettings().getZoomPosition();
        if (AMapOptions.ZOOM_POSITION_RIGHT_BUTTOM == type) {
            return MapOptions.ZOOM_POSITION_RIGHT_BUTTOM;
        } else if (AMapOptions.ZOOM_POSITION_RIGHT_CENTER == type) {
            return MapOptions.ZOOM_POSITION_RIGHT_CENTER;
        } else {
            return MapOptions.UNKNOWN;
        }
    }

    /**
     * 设置地图Logo的位置。
     * 高德地图模式下可用
     *
     * @param position
     */
    public void setLogoPosition(int position) {
        if (currentMapType != MapManager.MAP_TYPE_AMAP) {
            return;
        }
        if (MapOptions.LOGO_POSITION_BOTTOM_LEFT == position) {
            aMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
        } else if (MapOptions.LOGO_POSITION_BOTTOM_CENTER == position) {
            aMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_CENTER);
        } else if (MapOptions.LOGO_POSITION_BOTTOM_RIGHT == position) {
            aMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_RIGHT);
        }
    }

    public int getLogoPosition() {
        if (currentMapType != MapManager.MAP_TYPE_AMAP) {
            return MapOptions.UNKNOWN;
        }
        int type = aMap.getUiSettings().getLogoPosition();
        if (AMapOptions.LOGO_POSITION_BOTTOM_LEFT == type) {
            return MapOptions.LOGO_POSITION_BOTTOM_LEFT;
        } else if (AMapOptions.LOGO_POSITION_BOTTOM_CENTER == type) {
            return MapOptions.LOGO_POSITION_BOTTOM_CENTER;
        } else if (AMapOptions.LOGO_POSITION_BOTTOM_RIGHT == type) {
            return MapOptions.LOGO_POSITION_BOTTOM_RIGHT;
        } else {
            return MapOptions.UNKNOWN;
        }
    }

    /**
     * 设置指南针的位置，百度地图模式下可用
     *
     * @param point
     */
    public void setCompassPosition(Point point) {
        if (currentMapType == MapManager.MAP_TYPE_BAIDU) {
            baiduMap.getUiSettings().setCompassPosition(point);
        }
    }
}
