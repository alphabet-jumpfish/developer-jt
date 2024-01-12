package com.jumpfish.developer.porjects.monitors.utils.uid;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;


// single
public class SnowflakeFactory {

    static {
        IdGeneratorOptions options = new IdGeneratorOptions();
        options.WorkerId = 1;
        options.WorkerIdBitLength = 6; // WorkerIdBitLength 默认值6，支持的 WorkerId 最大值为2^6-1，若 WorkerId 超过64，可设置更大的 WorkerIdBitLength
        options.BaseTime = 1657123201000l; //设置2022年7月7日 一旦设置不可修改
        YitIdHelper.setIdGenerator(options);
    }

    public static Long getId() {
        return YitIdHelper.nextId();
    }

}
