package com.xtc.map.search;

/**
 * 地理编码请求参数
 * Created by hzj on 2016/5/17.
 */
public class CodeOption {

    String address;
    String city;

    /**
     * 设置地址
     *
     * @param address 地址
     * @return 该地理编码请求参数对象
     */
    public CodeOption address(String address) {
        this.address = address;
        return this;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     * @return 该地理编码请求参数对象
     */
    public CodeOption city(String city) {
        this.city = city;
        return this;
    }
}
