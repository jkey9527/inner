package com.cattle.inner.mapper;

import com.cattle.inner.bean.RecordBean;
import com.cattle.inner.bean.RecordParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 销售映射类
 *
 * @author niujie
 * @date 2023/4/21 22:35
 */
@Mapper
public interface RecordMapper {

    /**
     * 销售
     * @param record record
     * @return void
     * @author niujie
     * @date 2023/8/6
     */
    void saveRecord(RecordBean record);

    /**
     * 查询销售信息
     * @param recordParam recordParam
     * @return java.util.List<com.cattle.inner.bean.RecordBean>
     * @author niujie
     * @date 2023/8/6
     */
    List<RecordBean> getRecord(RecordParam recordParam);

    /**
     * 通过货品id查询销售记录
     * @param pro_id pro_id
     * @return java.util.List<com.cattle.inner.bean.RecordBean>
     * @author niujie
     * @date 2023/8/10
     */
    List<RecordBean> getRecordByProId(String pro_id);
}
