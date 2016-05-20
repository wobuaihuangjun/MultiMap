package com.xtc.map.overlay;

import android.os.Bundle;

import com.xtc.map.ConvertUtil;
import com.xtc.map.MapLatLng;

/**
 * 定义在地图上画一个圆的类
 * <p/>
 * Created by hzj on 2016/5/16.
 */
public class MapCircle {

    private com.amap.api.maps.model.Circle gdCircle;

    private com.baidu.mapapi.map.Circle bdCircle;

    public MapCircle(com.amap.api.maps.model.Circle gdCircle) {
        this.gdCircle = gdCircle;
    }

    public MapCircle(com.baidu.mapapi.map.Circle bdCircle) {
        this.bdCircle = bdCircle;
    }


    /**
     * 返回圆形的圆心经纬度坐标。
     *
     * @return 圆形的圆心经纬度坐标。
     */
    public MapLatLng getCenter() {
        if (bdCircle != null) {
            return ConvertUtil.convertBdLatLng(bdCircle.getCenter());
        } else if (gdCircle != null) {
            return ConvertUtil.convertGdLatLng(gdCircle.getCenter());
        } else {
            return null;
        }
    }

    /**
     * 设置圆形的圆心经纬度坐标，参数不能为null，无默认值。
     *
     * @param center 圆形的圆心经纬度坐标。
     */
    public void setCenter(MapLatLng center) {
        if (bdCircle != null) {
            bdCircle.setCenter(ConvertUtil.convertToBdLatLng(center));
        } else if (gdCircle != null) {
            gdCircle.setCenter(ConvertUtil.convertToGdLatLng(center));
        }
    }

    /**
     * 返回圆形填充颜色
     *
     * @return 圆形填充颜色的ARGB格式
     */
    public int getFillColor() {
        if (bdCircle != null) {
            return bdCircle.getFillColor();
        } else if (gdCircle != null) {
            return gdCircle.getFillColor();
        } else {
            return 0;
        }
    }

    /**
     * 设置填充颜色。填充颜色是绘制边框以内部分的颜色，ARGB格式。
     *
     * @param color 填充颜色ARGB格式。
     */
    public void setFillColor(int color) {
        if (bdCircle != null) {
            bdCircle.setFillColor(color);
        } else if (gdCircle != null) {
            gdCircle.setFillColor(color);
        }
    }

    /**
     * 返回圆形半径，单位米。
     *
     * @return 圆形半径，单位米。
     */
    public double getRadius() {
        if (bdCircle != null) {
            return bdCircle.getRadius();
        } else if (gdCircle != null) {
            return gdCircle.getRadius();
        } else {
            return 0;
        }
    }

    /**
     * 圆形半径，单位米。半径必须大于等于0。
     *
     * @param radius 圆形半径，单位米。
     */
    public void setRadius(double radius) {
        if (bdCircle != null) {
            bdCircle.setRadius((int) radius);
        } else if (gdCircle != null) {
            gdCircle.setRadius(radius);
        }
    }

    /**
     * 设置圆的边框信息
     *
     * @param stroke 圆的边框信息
     */
    public void setStroke(Stroke stroke) {
        if (bdCircle != null) {
            bdCircle.setStroke(new com.baidu.mapapi.map.Stroke(stroke.strokeWidth, stroke.color));
        } else if (gdCircle != null) {
            gdCircle.setStrokeWidth(stroke.strokeWidth);
            gdCircle.setStrokeColor(stroke.color);
        }
    }

    /**
     * 返回圆形边框颜色
     *
     * @return 圆形边框颜色，ARGB格式。
     */
    public int getStrokeColor() {
        if (bdCircle != null) {
            return bdCircle.getStroke().color;
        } else if (gdCircle != null) {
            return gdCircle.getStrokeColor();
        } else {
            return 0;
        }
    }

    /**
     * 返回圆形边框宽度。
     *
     * @return 圆形边框宽度，单位像素。
     */
    public float getStrokeWidth() {
        if (bdCircle != null) {
            return bdCircle.getStroke().strokeWidth;
        } else if (gdCircle != null) {
            return gdCircle.getStrokeWidth();
        } else {
            return 0;
        }
    }

    /**
     * 返回圆形Z轴数值
     *
     * @return 圆形Z轴数值
     */
    public float getZIndex() {
        if (bdCircle != null) {
            return bdCircle.getZIndex();
        } else if (gdCircle != null) {
            return gdCircle.getZIndex();
        } else {
            return 0;
        }
    }

    /**
     * 设置Z轴数值。设置圆形的Z轴数值。数值更高的圆将绘制在数值较低的上面。
     *
     * @param zIndex Z轴数值。
     */
    public void setZIndex(float zIndex) {
        if (bdCircle != null) {
            bdCircle.setZIndex((int) zIndex);
        } else if (gdCircle != null) {
            gdCircle.setZIndex(zIndex);
        }
    }

    /**
     * 返回圆形是否可见
     *
     * @return true为圆形可见，false为不可见。
     */
    public boolean isVisible() {
        if (bdCircle != null) {
            return bdCircle.isVisible();
        } else if (gdCircle != null) {
            return gdCircle.isVisible();
        } else {
            return false;
        }
    }

    /**
     * 设置圆形的可见属性。如果圆形不可见属性，则不会被绘制在地图上。默认为true。
     *
     * @param visible true为可见；false不可见。
     */
    public void setVisible(boolean visible) {
        if (bdCircle != null) {
            bdCircle.setVisible(visible);
        } else if (gdCircle != null) {
            gdCircle.setVisible(visible);
        }
    }

    /**
     * 删除地图对象里圆形
     */
    public void remove() {
        if (bdCircle != null) {
            bdCircle.remove();
        } else if (gdCircle != null) {
            gdCircle.remove();
        }
    }

    /**
     * 返回圆形的id
     *
     * @return 返回圆形的id。此id是覆盖物在所属的地图对象里的唯一标识。
     */
    public String getId() {
        if (bdCircle != null) {
            Bundle bundle = bdCircle.getExtraInfo();
            if (bundle != null) {
                return bundle.getString("id");
            } else {
                return null;
            }
        } else if (gdCircle != null) {
            return gdCircle.getId();
        } else {
            return null;
        }
    }
}
