package com.cattle.inner.service;

import com.cattle.inner.bean.SystemBean;

import java.util.List;

/**
 * 系统参数服务接口
 *
 * @author niujie
 * @date 2023/4/21 22:38
 */
public interface SystemService {

    /**
     * 查询系统参数
     * @return java.util.List<com.cattle.inner.bean.SystemBean>
     * @author niujie
     * @date 2023/6/1
     */
    List<SystemBean> getSystem() throws Exception;

    /**
     * 保存系统参数
     * @param systemList systemList
     * @return void
     * @author niujie
     * @date 2023/6/1
     */
    void saveSystem(List<SystemBean> systemList) throws Exception;

    /**
     * 根据系统参数编码查询
     * @param code code
     * @return com.cattle.inner.bean.SystemBean
     * @author niujie
     * @date 2023/6/1
     */
    SystemBean getSystemByCode(String code) throws Exception;
}
