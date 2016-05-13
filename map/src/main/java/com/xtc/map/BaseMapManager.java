package com.xtc.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.xtc.map.location.Map;

/**
 * 地图管理的基类
 * <p/>
 * Created by hzj on 2016/5/11.
 */
public abstract class BaseMapManager {

    protected MapView bdMapView;
    protected BaiduMap bdMap;

    protected com.amap.api.maps.MapView aMapView;
    protected AMap aMap;

    protected int currentMapType;

    protected MapUISettings uiSettings;

    public BaseMapManager() {
        uiSettings = new MapUISettings();
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

    protected int convertAMapMode(int type) {
        if (type == MapOptions.MAP_SATELLITE) {
            return AMap.MAP_TYPE_SATELLITE;
        } else {
            return AMap.MAP_TYPE_NORMAL;
        }
    }

    protected int convertBaiduMapMode(int type) {
        if (type == MapOptions.MAP_SATELLITE) {
            return BaiduMap.MAP_TYPE_SATELLITE;
        } else {
            return BaiduMap.MAP_TYPE_NORMAL;
        }
    }

    protected int getAMapMode() {
        if (AMap.MAP_TYPE_SATELLITE == aMap.getMapType()) {
            return MapOptions.MAP_SATELLITE;
        } else {
            return MapOptions.MAP_NORMAL;
        }
    }

    protected int getBaiduMode() {
        if (BaiduMap.MAP_TYPE_SATELLITE == bdMap.getMapType()) {
            return MapOptions.MAP_SATELLITE;
        } else {
            return MapOptions.MAP_NORMAL;
        }
    }

    /**
     * 如果是显示高德地图，在创建mapview后此方法必须调用
     */
    public void onCreate(Bundle bundle) {
        if (aMapView != null) aMapView.onCreate(bundle);
    }

    /**
     * activity暂停时同时暂停地图控件
     */
    public void onPause() {
        if (bdMapView != null) bdMapView.onPause();

        if (aMapView != null) aMapView.onPause();
    }

    /**
     * activity恢复时同时恢复地图控件
     */
    public void onResume() {
        if (bdMapView != null) bdMapView.onResume();

        if (aMapView != null) aMapView.onResume();
    }

    /**
     * activity销毁时同时销毁地图控件
     */
    public void onDestroy() {
        if (bdMapView != null) bdMapView.onDestroy();

        if (aMapView != null) aMapView.onDestroy();
    }

    protected void releaseBaiduMap() {
        if (bdMapView != null) {
            bdMapView.onDestroy();
            bdMapView = null;
            bdMap = null;
        }

    }

    protected void releaseAMap() {
        if (aMapView != null) {
            aMapView.onDestroy();
            aMapView = null;
            aMap = null;

        }
    }

//    public final void moveCamera(CameraUpdate var1) {
//    }
//
//    public final void animateCamera(CameraUpdate var1) {
//    }
//
//    public final void animateCamera(CameraUpdate var1, AMap.CancelableCallback var2) {
//    }
//
//    public final void animateCamera(CameraUpdate var1, long var2, AMap.CancelableCallback var4) {
//    }

    public final void setOnMapStatusChangeListener(@NonNull final Map.OnMapStatusChangeListener listener) {
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
        if (aMap != null) {
            aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
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

    public final void setOnMapClickListener(@NonNull final Map.OnMapClickListener listener) {
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
        if (aMap != null) {
            aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
                @Override
                public void onMapClick(com.amap.api.maps.model.LatLng latLng) {
                    listener.onMapClick(ConvertUtil.convertGdLatLng(latLng));
                }
            });
        }
    }

    public final void setOnMapTouchListener(@NonNull final Map.OnMapTouchListener listener) {
        if (bdMap != null) {
            bdMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {
                @Override
                public void onTouch(MotionEvent motionEvent) {
                    listener.onTouch(motionEvent);
                }
            });
        }
        if (aMap != null) {
            aMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
                @Override
                public void onTouch(MotionEvent motionEvent) {
                    listener.onTouch(motionEvent);
                }
            });
        }
    }

    public final void setOnPOIClickListener(@NonNull final Map.OnPOIClickListener listener) {
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
        if (aMap != null) {
            aMap.setOnPOIClickListener(new AMap.OnPOIClickListener() {
                @Override
                public void onPOIClick(Poi poi) {
                    listener.onPOIClick(ConvertUtil.convertGdPoi(poi));
                }
            });
        }
    }

    public final void setOnMyLocationChangeListener(Map.OnMyLocationChangeListener var1) {
    }

    public final void setOnMapLongClickListener(@NonNull final Map.OnMapLongClickListener listener) {
        if (bdMap != null) {
            bdMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    listener.onMapLongClick(ConvertUtil.convertBdLatLng(latLng));
                }
            });
        }
        if (aMap != null) {
            aMap.setOnMapLongClickListener(new AMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(com.amap.api.maps.model.LatLng latLng) {
                    listener.onMapLongClick(ConvertUtil.convertGdLatLng(latLng));
                }
            });
        }
    }

    public final void setOnMarkerClickListener(@NonNull final Map.OnMarkerClickListener listener) {
        if (bdMap != null) {
            bdMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(com.baidu.mapapi.map.Marker marker) {
                    listener.onMarkerClick(new Marker(marker));
                    return false;
                }
            });
        }
        if (aMap != null) {
            aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(com.amap.api.maps.model.Marker marker) {
                    listener.onMarkerClick(new Marker(marker));
                    return false;
                }
            });
        }
    }

    @Deprecated
    public final void setOnPolylineClickListener(Map.OnPolylineClickListener var1) {
    }

    public final void setOnMarkerDragListener(@NonNull final Map.OnMarkerDragListener listener) {
        if (bdMap != null) {
            bdMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDrag(com.baidu.mapapi.map.Marker marker) {
                    listener.onMarkerDrag(new Marker(marker));
                }

                @Override
                public void onMarkerDragEnd(com.baidu.mapapi.map.Marker marker) {
                    listener.onMarkerDragEnd(new Marker(marker));
                }

                @Override
                public void onMarkerDragStart(com.baidu.mapapi.map.Marker marker) {
                    listener.onMarkerDragStart(new Marker(marker));
                }
            });
        }
        if (aMap != null) {
            aMap.setOnMarkerDragListener(new AMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(com.amap.api.maps.model.Marker marker) {
                    listener.onMarkerDragStart(new Marker(marker));
                }

                @Override
                public void onMarkerDrag(com.amap.api.maps.model.Marker marker) {
                    listener.onMarkerDrag(new Marker(marker));
                }

                @Override
                public void onMarkerDragEnd(com.amap.api.maps.model.Marker marker) {
                    listener.onMarkerDragEnd(new Marker(marker));
                }
            });
        }
    }

    @Deprecated
    public final void setOnInfoWindowClickListener(@NonNull final Map.OnInfoWindowClickListener listener) {
    }

    public final void setOnMapLoadedListener(@NonNull final Map.OnMapLoadedListener listener) {
        if (bdMap != null) {
            bdMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    listener.onMapLoaded();
                }
            });
        }
        if (aMap != null) {
            aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
                @Override
                public void onMapLoaded() {
                    listener.onMapLoaded();
                }
            });
        }
    }

}
