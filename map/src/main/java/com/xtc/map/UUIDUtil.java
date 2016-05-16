package com.xtc.map;

import java.util.UUID;

/**
 * UUID工具
 * Created by hzj on 2016/5/16.
 */
public class UUIDUtil {

    /**
     * 获得一个UUID
     *
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}
