package com.xtc.map.search;

/**
 * POI检索参数
 * <p/>
 * Created by hzj on 2016/5/20.
 */
public class PoiKeySearchOption {

    String city;

    String keyWord;
    String searchType;

    int pageNumber;

    int pageSize;

    /**
     * 检索城市,为空表示全国检索
     *
     * @param city 检索城市
     * @return PoiKeySearchOption
     */
    public PoiKeySearchOption city(String city) {
        this.city = city;
        return this;
    }

    /**
     * 检索关键字
     *
     * @param keyWord 检索关键字
     * @return PoiKeySearchOption
     */
    public PoiKeySearchOption keyWord(String keyWord) {
        this.keyWord = keyWord;
        return this;
    }

    /**
     * 搜索区域类型
     *
     * @param searchType 搜索区域类型
     * @return PoiKeySearchOption
     */
    public PoiKeySearchOption searchType(String searchType) {
        this.searchType = searchType;
        return this;
    }

    /**
     * 分页编号
     *
     * @param pageNumber 分页编号
     * @return PoiKeySearchOption
     */
    public PoiKeySearchOption pageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    /**
     * 设置每页容量，默认为每页10条
     *
     * @param pageSize 设置每页容量，默认为每页10条
     * @return PoiKeySearchOption
     */
    public PoiKeySearchOption pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
