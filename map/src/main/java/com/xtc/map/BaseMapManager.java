package com.xtc.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.xtc.map.location.MapInterface;
import com.xtc.map.overlay.MapCircle;
import com.xtc.map.overlay.MapCircleOptions;
import com.xtc.map.overlay.MapMarker;
import com.xtc.map.overlay.MapMarkerOptions;
import com.xtc.map.overlay.ConvertOverlay;
import com.xtc.map.status.MapCamera;
import com.xtc.map.status.MapCameraUpdate;

/**
 * 地图管理的基类
 * <p/>
 * Created by hzj on 2016/5/11.
 */
public abstract class BaseMapManager {

    protected MapView bdMapView;
    protected BaiduMap bdMap;

    protected com.amap.api.maps.MapView gdMapView;
    protected AMap gdMap;

    protected int currentMapType;

    protected MapUISettings uiSettings;
    protected MapProjection mapProjection;

    public BaseMapManager() {
        uiSettings = new MapUISettings();
        mapProjection = new MapProjection();
    }

    /**
     * 获取当前地图类型
     */
    public int getCurrentMapType() {
        return currentMapType;
    }

    /**
     * 地图切换了
     */
    protected abstract void mapChanged();

    /**
     * 如果是显示高德地图，在创建mapview后此方法必须调用
     */
    public void onCreate(Bundle bundle) {
        if (gdMapView != null) gdMapView.onCreate(bundle);
    }

    /**
     * activity暂停时同时暂停地图控件
     */
    public void onPause() {
        if (bdMapView != null) bdMapView.onPause();

        if (gdMapView != null) gdMapView.onPause();
    }

    /**
     * activity恢复时同时恢复地图控件
     */
    public void onResume() {
        if (bdMapView != null) bdMapView.onResume();

        if (gdMapView != null) gdMapView.onResume();
    }

    /**
     * activity销毁时同时销毁地图控件
     */
    public void onDestroy() {
        if (bdMapView != null) bdMapView.onDestroy();

        if (gdMapView != null) gdMapView.onDestroy();
    }

    protected void releaseBaiduMap() {
        if (bdMapView != null) {
            bdMapView.onDestroy();
            bdMapView = null;
            bdMap = null;
        }
    }

    protected void releaseAMap() {
        if (gdMapView != null) {
            gdMapView.onDestroy();
            gdMapView = null;
            gdMap = null;
        }
    }

    /**
     * 加一个Marker（标记）到地图上
     *
     * @param options 一个MarkerOptions 对象，它定义了如何渲染Marker 的属性。
     * @return 返回一个 MapMarker 对象，此对象已经加到地图上。
     */
    public MapMarker addMarker(MapMarkerOptions options) {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            return new MapMarker(gdMap.addMarker(ConvertOverlay.convertGdMarkerOptions(options)));
        } else {
            return new MapMarker((com.baidu.mapapi.map.Marker) bdMap
                    .addOverlay(ConvertOverlay.convertBdMarkerOptions(options)));
        }
    }

    /**
     * 添加圆形（circle）覆盖物到地图上。
     *
     * @param options 设置圆形初始化属性的CircleOptions对象
     * @return 一个Circle对象。
     */
    public final MapCircle addCircle(MapCircleOptions options) {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            return new MapCircle(gdMap.addCircle(ConvertOverlay.convertGdCircleOptions(options)));
        } else {
            com.baidu.mapapi.map.Circle circle = (com.baidu.mapapi.map.Circle) bdMap
                    .addOverlay(ConvertOverlay.convertBdCircleOptions(options));
            Bundle bundle = new Bundle();
            bundle.putString("id", UUIDUtil.getUUID());
            circle.setExtraInfo(bundle);
            return new MapCircle(circle);
        }
    }

    /**
     * 获取地图的当前状态
     *
     * @return 地图的当前状态
     */
    public MapCamera getMapStatus() {
        if (currentMapType == MapManager.MAP_TYPE_AMAP) {
            return ConvertUtil.convertGdMapStatus(gdMap.getCameraPosition());
        } else {
            return ConvertUtil.convertBdMapStatus(bdMap.getMapStatus());
        }
    }

    /**
     * 改变地图状态
     *
     * @param var1 地图状态将要发生的变化
     */
    public void updateMapStatus(MapCameraUpdate var1) {
        MapCamera status = var1.getMapStatus(getMapStatus());
        if (bdMap != null) {
            com.baidu.mapapi.map.MapStatusUpdate update = MapStatusUpdateFactory.newMapStatus(
                    ConvertUtil.convertToBdMapStatus(status));
            bdMap.setMapStatus(update);
        }
        if (gdMap != null) {
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(
                    ConvertUtil.convertToGdMapStatus(status));
            gdMap.moveCamera(update);
        }
    }

    /**
     * 以动画方式更新地图状态，动画耗时 300 ms
     *
     * @param var1 定义转换的目的地位置
     */
    public void animateMapStatus(MapCameraUpdate var1) {
        MapCamera status = var1.getMapStatus(getMapStatus());
        if (bdMap != null) {
            com.baidu.mapapi.map.MapStatusUpdate update = MapStatusUpdateFactory.newMapStatus(
                    ConvertUtil.convertToBdMapStatus(status));
            bdMap.animateMapStatus(update);
        }
        if (gdMap != null) {
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(
                    ConvertUtil.convertToGdMapStatus(status));
            gdMap.animateCamera(update);
        }

    }

    /**
     * 以动画方式更新地图状态
     *
     * @param var1 地图状态将要发生的变化
     * @param var2 动画时间
     */
    public void animateMapStatus(MapCameraUpdate var1, int var2) {
        MapCamera status = var1.getMapStatus(getMapStatus());
        if (bdMap != null) {
            com.baidu.mapapi.map.MapStatusUpdate update = MapStatusUpdateFactory.newMapStatus(
                    ConvertUtil.convertToBdMapStatus(status));
            bdMap.animateMapStatus(update, var2);
        }
        if (gdMap != null) {
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(
                    ConvertUtil.convertToGdMapStatus(status));
            gdMap.animateCamera(update, var2, null);
        }

    }

    public final void setOnMapStatusChangeListener(@NonNull final MapInterface.OnMapStatusChangeListener listener) {
        if (bdMap != null) {
            bdMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
                @Override
                public void onMapStatusChangeStart(com.baidu.mapapi.map.MapStatus mapStatus) {

                }

                @Override
                public void onMapStatusChange(com.baidu.mapapi.map.MapStatus mapStatus) {
                    listener.onMapStatusChange(ConvertUtil.convertBdMapStatus(mapStatus));
                }

                @Override
                public void onMapStatusChangeFinish(com.baidu.mapapi.map.MapStatus mapStatus) {
                    listener.onMapStatusChangeFinish(ConvertUtil.convertBdMapStatus(mapStatus));
                }
            });
        }
        if (gdMap != null) {
            gdMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {
                    listener.onMapStatusChange(ConvertUtil.convertGdMapStatus(cameraPosition));
                }

                @Override
                public void onCameraChangeFinish(CameraPosition cameraPosition) {
                    listener.onMapStatusChangeFinish(ConvertUtil.convertGdMapStatus(cameraPosition));
                }
            });
        }
    }

    public final void setOnMapClickListener(@NonNull final MapInterface.OnMapClickListener listener) {
        if (bdMap != null) {
            bdMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    listener.onMapClick(ConvertUtil.convertBdLatLng(latLng));
                }

                @Override
                public boolean onMapPoiClick(MapPoi mapPoi) {
                    return false;
                }
            });
        }
        if (gdMap != null) {
            gdMap.setOnMapClickListener(new AMap.OnMapClickListener() {
                @Override
                public void onMapClick(com.amap.api.maps.model.LatLng latLng) {
                    listener.onMapClick(ConvertUtil.convertGdLatLng(latLng));
                }
            });
        }
    }

    public final void setOnMapTouchListener(@NonNull final MapInterface.OnMapTouchListener listener) {
        if (bdMap != null) {
            bdMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {
                @Override
                public void onTouch(MotionEvent motionEvent) {
                    listener.onTouch(motionEvent);
                }
            });
        }
        if (gdMap != null) {
            gdMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
                @Override
                public void onTouch(MotionEvent motionEvent) {
                    listener.onTouch(motionEvent);
                }
            });
        }
    }

    public final void setOnPOIClickListener(@NonNull final MapInterface.OnPOIClickListener listener) {
        if (bdMap != null) {
            bdMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {

                }

                @Override
                public boolean onMapPoiClick(MapPoi mapPoi) {
                    listener.onPOIClick(ConvertUtil.convertBdPoi(mapPoi));
                    return false;
                }
            });
        }
        if (gdMap != null) {
            gdMap.setOnPOIClickListener(new AMap.OnPOIClickListener() {
                @Override
                public void onPOIClick(Poi poi) {
                    listener.onPOIClick(ConvertUtil.convertGdPoi(poi));
                }
            });
        }
    }

    public final void setOnMapLongClickListener(@NonNull final MapInterface.OnMapLongClickListener listener) {
        if (bdMap != null) {
            bdMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    listener.onMapLongClick(ConvertUtil.convertBdLatLng(latLng));
                }
            });
        }
        if (gdMap != null) {
            gdMap.setOnMapLongClickListener(new AMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(com.amap.api.maps.model.LatLng latLng) {
                    listener.onMapLongClick(ConvertUtil.convertGdLatLng(latLng));
                }
            });
        }
    }

    public final void setOnMarkerClickListener(@NonNull final MapInterface.OnMarkerClickListener listener) {
        if (bdMap != null) {
            bdMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(com.baidu.mapapi.map.Marker marker) {
                    listener.onMarkerClick(new MapMarker(marker));
                    return false;
                }
            });
        }
        if (gdMap != null) {
            gdMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(com.amap.api.maps.model.Marker marker) {
                    listener.onMarkerClick(new MapMarker(marker));
                    return false;
                }
            });
        }
    }

    public final void setOnMarkerDragListener(@NonNull final MapInterface.OnMarkerDragListener listener) {
        if (bdMap != null) {
            bdMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDrag(com.baidu.mapapi.map.Marker marker) {
                    listener.onMarkerDrag(new MapMarker(marker));
                }

                @Override
                public void onMarkerDragEnd(com.baidu.mapapi.map.Marker marker) {
                    listener.onMarkerDragEnd(new MapMarker(marker));
                }

                @Override
                public void onMarkerDragStart(com.baidu.mapapi.map.Marker marker) {
                    listener.onMarkerDragStart(new MapMarker(marker));
                }
            });
        }
        if (gdMap != null) {
            gdMap.setOnMarkerDragListener(new AMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(com.amap.api.maps.model.Marker marker) {
                    listener.onMarkerDragStart(new MapMarker(marker));
                }

                @Override
                public void onMarkerDrag(com.amap.api.maps.model.Marker marker) {
                    listener.onMarkerDrag(new MapMarker(marker));
                }

                @Override
                public void onMarkerDragEnd(com.amap.api.maps.model.Marker marker) {
                    listener.onMarkerDragEnd(new MapMarker(marker));
                }
            });
        }
    }

    public final void setOnMapLoadedListener(@NonNull final MapInterface.OnMapLoadedListener listener) {
        if (bdMap != null) {
            bdMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    listener.onMapLoaded();
                }
            });
        }
        if (gdMap != null) {
            gdMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
                @Override
                public void onMapLoaded() {
                    listener.onMapLoaded();
                }
            });
        }
    }

    @Deprecated
    public final void setOnInfoWindowClickListener(@NonNull final MapInterface.OnInfoWindowClickListener listener) {
    }

    @Deprecated
    public final void setOnPolylineClickListener(MapInterface.OnPolylineClickListener var1) {
    }
}
