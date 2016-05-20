package com.xtc.map.overlay;

import android.os.Bundle;

import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.xtc.map.ConvertUtil;
import com.xtc.map.MapLatLng;

import java.util.ArrayList;

/**
 * 地图 MapMarker 覆盖物
 * <p/>
 * Created by hzj on 2016/5/13.
 */
public class MapMarker {

    private com.amap.api.maps.model.Marker gdMarker;

    private com.baidu.mapapi.map.Marker bdMarker;

    public MapMarker(com.amap.api.maps.model.Marker gdMarker) {
        this.gdMarker = gdMarker;
    }

    public MapMarker(com.baidu.mapapi.map.Marker bdMarker) {
        this.bdMarker = bdMarker;
    }

    /**
     * 设置多少帧刷新一次图片资源，Marker动画的间隔时间，值越小动画越快。默认为20，最小为1。
     *
     * @return 刷新周期，速度越小速度越快。默认为20，最小为1。
     */
    public int getPeriod() {
        if (bdMarker != null) {
            return bdMarker.getPeriod();
        } else if (gdMarker != null) {
            return gdMarker.getPeriod();
        } else {
            return 20;
        }
    }

    /**
     * 设置多少帧刷新一次图片资源，Marker动画的间隔时间，值越小动画越快。默认为20，最小为1。
     *
     * @param period 刷新周期，越小速度越快。默认为20，最小为1。
     */
    public void setPeriod(int period) {
        if (bdMarker != null) {
            bdMarker.setPeriod(period);
        } else if (gdMarker != null) {
            gdMarker.setPeriod(period);
        }
    }

    public void setIcon(MapBitmap descriptor) {
        if (bdMarker != null) {
            bdMarker.setIcon(BitmapDescriptorFactory.fromBitmap(descriptor.bitmap));
        } else if (gdMarker != null) {
            gdMarker.setIcon(com.amap.api.maps.model.BitmapDescriptorFactory
                    .fromBitmap(descriptor.bitmap));
        }
    }

    public void setIcons(ArrayList<MapBitmap> descriptors) {
        if (descriptors == null) {
            return;
        }
        if (bdMarker != null) {
            ArrayList<com.baidu.mapapi.map.BitmapDescriptor> bdList = new ArrayList<>();
            for (MapBitmap bitmap : descriptors) {
                bdList.add(BitmapDescriptorFactory.fromBitmap(bitmap.bitmap));
            }
            bdMarker.setIcons(bdList);
        } else if (gdMarker != null) {
            ArrayList<com.amap.api.maps.model.BitmapDescriptor> bdList = new ArrayList<>();
            for (MapBitmap bitmap : descriptors) {
                bdList.add(com.amap.api.maps.model.BitmapDescriptorFactory
                        .fromBitmap(bitmap.bitmap));
            }
            gdMarker.setIcons(bdList);
        }
    }

    public MapLatLng getPosition() {
        if (bdMarker != null) {
            return ConvertUtil.convertBdLatLng(bdMarker.getPosition());
        } else if (gdMarker != null) {
            return ConvertUtil.convertGdLatLng(gdMarker.getPosition());
        } else {
            return null;
        }
    }

    public void setPosition(MapLatLng position) {
        if (bdMarker != null) {
            bdMarker.setPosition(ConvertUtil.convertToBdLatLng(position));
        } else if (gdMarker != null) {
            gdMarker.setPosition(ConvertUtil.convertToGdLatLng(position));
        }
    }

    public String getTitle() {
        if (bdMarker != null) {
            return bdMarker.getTitle();
        } else if (gdMarker != null) {
            return gdMarker.getTitle();
        } else {
            return null;
        }
    }

    public void setTitle(String title) {
        if (bdMarker != null) {
            bdMarker.setTitle(title);
        } else if (gdMarker != null) {
            gdMarker.setTitle(title);
        }
    }

    public boolean isDraggable() {
        if (bdMarker != null) {
            return bdMarker.isDraggable();
        } else if (gdMarker != null) {
            return gdMarker.isDraggable();
        } else {
            return false;
        }
    }

    public void setDraggable(boolean draggable) {
        if (bdMarker != null) {
            bdMarker.setDraggable(draggable);
        } else if (gdMarker != null) {
            gdMarker.setDraggable(draggable);
        }
    }

    public boolean isInfoWindowShown() {
        if (gdMarker != null) {
            return gdMarker.isInfoWindowShown();
        } else {
            return false;
        }
    }

    public void showInfoWindow() {
        if (gdMarker != null) {
            gdMarker.showInfoWindow();
        }
    }

    public void hideInfoWindow() {
        if (gdMarker != null) {
            gdMarker.hideInfoWindow();
        }
    }

    public boolean isVisible() {
        if (bdMarker != null) {
            return bdMarker.isVisible();
        } else if (gdMarker != null) {
            return gdMarker.isVisible();
        } else {
            return false;
        }
    }

    public void setVisible(boolean visible) {
        if (bdMarker != null) {
            bdMarker.setVisible(visible);
        } else if (gdMarker != null) {
            gdMarker.setVisible(visible);
        }
    }

    /**
     * 返回Marker的附加对象，即自定义的Marker的属性。
     *
     * @return Marker的附加对象。
     */
    public Bundle getExtraInfo() {
        if (bdMarker != null) {
            return bdMarker.getExtraInfo();
        } else if (gdMarker != null) {
            return (Bundle) gdMarker.getObject();
        } else {
            return null;
        }
    }

    /**
     * 设置Marker的附加对象。用户可以自定义Marker的属性。
     *
     * @param extraInfo Marker的附加对象。
     */
    public void setExtraInfo(Bundle extraInfo) {
        if (bdMarker != null) {
            bdMarker.setExtraInfo(extraInfo);
        } else if (gdMarker != null) {
            gdMarker.setObject(extraInfo);
        }
    }

    /**
     * 返回 Marker图片旋转的角度，从正北开始，逆时针计算。
     *
     * @return Marker图片旋转的角度。
     */
    public float getRotate() {
        if (bdMarker != null) {
            return bdMarker.getRotate();
        } else if (gdMarker != null) {
            return gdMarker.getRotateAngle();
        } else {
            return 0;
        }
    }

    /**
     * 设置Marker图片旋转的角度，从正北开始，逆时针计算。
     *
     * @param rotate Marker图片旋转的角度。
     */
    public void setRotate(float rotate) {
        if (bdMarker != null) {
            bdMarker.setRotate(rotate);
        } else if (gdMarker != null) {
            gdMarker.setRotateAngle(rotate);
        }
    }

    /**
     * 返回marker是否是平贴在地图上。
     *
     * @return 若marker平贴在地图上返回 true；若marker面对镜头返回 false。
     */
    public boolean isFlat() {
        if (bdMarker != null) {
            return bdMarker.isFlat();
        } else if (gdMarker != null) {
            return gdMarker.isFlat();
        } else {
            return false;
        }
    }

    /**
     * 设置当前marker是否平贴在地图上。
     *
     * @param flat marker平贴地图设置为 true，面对镜头设置为 false。
     */
    public void setFlat(boolean flat) {
        if (bdMarker != null) {
            bdMarker.setFlat(flat);
        } else if (gdMarker != null) {
            gdMarker.setFlat(flat);
        }
    }

    public float getZIndex() {
        if (bdMarker != null) {
            return bdMarker.getZIndex();
        } else if (gdMarker != null) {
            return gdMarker.getZIndex();
        } else {
            return 0;
        }
    }

    public void setZIndex(float zIndex) {
        if (bdMarker != null) {
            bdMarker.getZIndex();
        } else if (gdMarker != null) {
            gdMarker.getZIndex();
        }
    }

    /**
     * 设置marker在屏幕的像素坐标。若用此方式，marker固定显示在屏幕上；如果想让标记随地图移动，可以使用 setPosition(MapLatLng) 改变。
     *
     * @param x 横向像素点。
     * @param y 纵向像素点。
     */
    public void setPositionByPixels(int x, int y) {
        if (gdMarker != null) {
            gdMarker.setPositionByPixels(x, y);
        }
    }

    /**
     * 设置当前marker的锚点。 锚点是定位图标接触地图平面的点。图标的左上顶点为（0,0）点，右下点为（1,1）点。默认情况下，锚点为（0.5,1.0）。
     *
     * @param anchorU 锚点水平范围的比例
     * @param anchorV 锚点垂直范围的比例
     */
    public void setAnchor(float anchorU, float anchorV) {
        if (bdMarker != null) {
            bdMarker.setAnchor(anchorU, anchorV);
        } else if (gdMarker != null) {
            gdMarker.setAnchor(anchorU, anchorV);
        }
    }

    /**
     * 设置当前marker在最上面
     */
    public void setToTop() {
        if (bdMarker != null) {
            bdMarker.setToTop();
        } else if (gdMarker != null) {
            gdMarker.setToTop();
        }
    }

    /**
     * 删除当前marker。在删除当前marker之后，它的所有方法将不被支持。
     */
    public void remove() {
        if (bdMarker != null) {
            bdMarker.remove();
        } else if (gdMarker != null) {
            gdMarker.remove();
        }
    }

    /**
     * 删除当前marker并销毁Marker的图片等资源。一旦使用此方法，该对象将被彻底释放
     */
    public void destroy() {
        if (bdMarker != null) {
            bdMarker.remove();
        } else if (gdMarker != null) {
            gdMarker.destroy();
        }
    }
}
