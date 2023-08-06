package com.cattle.inner.service;

import com.cattle.inner.bean.RecordBean;
import com.cattle.inner.bean.RecordParam;
import com.cattle.inner.bean.RecordResult;

/**
 * 销售服务接口
 *
 * @author niujie
 * @date 2023/4/21 22:38
 */
public interface RecordService {

    /**
     * 销售
     * @param record record
     * @return void
     * @author niujie
     * @date 2023/8/6
     */
    void saveRecord(RecordBean record) throws Exception;

    /**
     * 查询销售数据
     * @param recordParam recordParam
     * @return com.cattle.inner.bean.RecordResult
     * @author niujie
     * @date 2023/8/6
     */
    RecordResult getRecord(RecordParam recordParam) throws Exception;
}
