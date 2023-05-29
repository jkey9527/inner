package com.cattle.house.service;

import com.cattle.house.bean.RecordBean;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;

/**
 * 明细服务接口
 *
 * @author niujie
 * @date 2023/4/21 22:38
 */
public interface RecordService {

    /**
     * 查询明细详情
     *
     * @param record record
     * @return com.cattle.house.bean.RecordBean
     * @author niujie
     * @date 2023/5/27
     */
    RecordBean getRecord(RecordBean record) throws Exception;

    /**
     * 保存明细
     *
     * @param record record
     * @return void
     * @author niujie
     * @date 2023/5/27
     */
    void saveRecord(RecordBean record) throws Exception;

    /**
     * 修改明细
     *
     * @param record record
     * @return void
     * @author niujie
     * @date 2023/5/27
     */
    void updateRecord(RecordBean record) throws Exception;

    /**
     * 删除明细
     *
     * @param record record
     * @return void
     * @author niujie
     * @date 2023/5/27
     */
    void deleteRecord(RecordBean record) throws Exception;

    /**
     * 分页查询明细
     *
     * @param record record
     * @return com.github.pagehelper.PageInfo<com.cattle.house.bean.RecordBean>
     * @author niujie
     * @date 2023/5/27
     */
    PageInfo<RecordBean> getRecordList4Page(RecordBean record) throws Exception;

    /**
     * 查询余额
     * @return java.math.BigDecimal
     * @author niujie
     * @date 2023/5/29
     */
    BigDecimal getRecordBalance() throws Exception;
}
