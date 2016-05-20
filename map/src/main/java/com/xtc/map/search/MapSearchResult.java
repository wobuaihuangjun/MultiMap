package com.xtc.map.search;

/**
 * 地图搜索结果基类
 * Created by hzj on 2016/5/19.
 */
public class MapSearchResult {

    /**
     * 成功
     */
    public static final int NO_ERROR = 0;

    /**
     * 失败
     */
    public static final int ERROR = 1;

    /**
     * 未搜索到结果
     */
    public static final int RESULT_NOT_FOUND = 2;

    int errorCode;

    public int getErrorCode() {
        return errorCode;
    }
}
