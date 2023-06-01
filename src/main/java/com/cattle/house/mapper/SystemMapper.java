package com.cattle.house.mapper;

import com.cattle.house.bean.SystemBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  系统参数映射
 * @author niujie
 * @date 2023/6/1 16:56
 */
@Mapper
public interface SystemMapper {

    /**
     * 查询系统参数
     * @return java.util.List<com.cattle.house.bean.SystemBean>
     * @author niujie
     * @date 2023/6/1
     */
    List<SystemBean> getSystem();

    /**
     * 保存系统参数
     * @param systemBean systemBean
     * @return void
     * @author niujie
     * @date 2023/6/1
     */
    void saveSystem(SystemBean systemBean);

    /**
     * 根据系统参数查询
     * @param code code
     * @return com.cattle.house.bean.SystemBean
     * @author niujie
     * @date 2023/6/1
     */
    SystemBean getSystemByCode(String code);
}
