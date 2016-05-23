package com.xtc.map.search;

import com.xtc.map.MapLatLng;

/**
 * 附近搜索参数
 * <p/>
 * Created by hzj on 2016/5/20.
 */
public class PoiAroundSearchOption {

    String city;

    MapLatLng location;

    String keyWord;

    String searchType;

    int radius;

    int pageNumber;

    int pageSize;

    /**
     * 检索位置
     *
     * @param location 检索位置
     * @return PoiAroundSearchOption
     */
    public PoiAroundSearchOption location(MapLatLng location) {
        this.location = location;
        return this;
    }

    /**
     * 设置检索的半径范围
     *
     * @param radius 设置检索的半径范围
     * @return PoiAroundSearchOption
     */
    public PoiAroundSearchOption radius(int radius) {
        this.radius = radius;
        return this;
    }

    /**
     * 检索城市,为空表示全国检索
     *
     * @param city 检索城市
     * @return PoiKeySearchOption
     */
    public PoiAroundSearchOption city(String city) {
        this.city = city;
        return this;
    }

    /**
     * 检索关键字
     *
     * @param keyWord 检索关键字
     * @return PoiKeySearchOption
     */
    public PoiAroundSearchOption keyWord(String keyWord) {
        this.keyWord = keyWord;
        return this;
    }

    /**
     * 搜索区域类型
     *
     * @param searchType 搜索区域类型
     * @return PoiKeySearchOption
     */
    public PoiAroundSearchOption searchType(String searchType) {
        this.searchType = searchType;
        return this;
    }

    /**
     * 分页编号
     *
     * @param pageNumber 分页编号
     * @return PoiKeySearchOption
     */
    public PoiAroundSearchOption pageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    /**
     * 设置每页容量，默认为每页10条
     *
     * @param pageSize 设置每页容量，默认为每页10条
     * @return PoiKeySearchOption
     */
    public PoiAroundSearchOption pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public String toString() {
        return "PoiAroundSearchOption{" +
                "city='" + city + '\'' +
                ", location=" + location +
                ", keyWord='" + keyWord + '\'' +
                ", searchType='" + searchType + '\'' +
                ", radius=" + radius +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
