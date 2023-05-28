package com.cattle.house.mapper;

import com.cattle.house.bean.RecordBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 明细映射类
 *
 * @author niujie
 * @date 2023/4/21 22:35
 */
@Mapper
public interface RecordMapper {

    /**
     * 查询明细详情
     *
     * @param record record
     * @return com.cattle.house.bean.RecordBean
     * @author niujie
     * @date 2023/5/27
     */
    RecordBean getRecord(RecordBean record);

    /**
     * 保存明细
     *
     * @param record record
     * @return void
     * @author niujie
     * @date 2023/5/27
     */
    void saveRecord(RecordBean record);

    /**
     * 修改明细
     *
     * @param record record
     * @return void
     * @author niujie
     * @date 2023/5/27
     */
    void updateRecord(RecordBean record);

    /**
     * 删除明细
     *
     * @param record record
     * @return void
     * @author niujie
     * @date 2023/5/27
     */
    void deleteRecord(RecordBean record);

    /**
     * 查询明细列表
     *
     * @param record record
     * @return java.util.List<com.cattle.house.bean.RecordBean>
     * @author niujie
     * @date 2023/5/27
     */
    List<RecordBean> getRecordList(RecordBean record);
}
