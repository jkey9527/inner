package com.cattle.inner.service;

import com.cattle.inner.bean.OptLog;
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
     * 保存日志
     * @param model 模块
     * @param type 类型
     * @param info 详情
     * @param memo 备注
     * @return void
     * @author niujie
     * @date 2023/8/6
     */
    void saveOptLog(String model, String type, String info, String memo);

    /**
     * 保存日志
     * @param model model
     * @param type type
     * @param info info
     * @return void
     * @author niujie
     * @date 2023/8/6
     */
    void saveOptLog(String model, String type, String info);

    /**
     * 保存日志
     * @param model model
     * @param type type
     * @return void
     * @author niujie
     * @date 2023/8/6
     */
    void saveOptLog(String model, String type);

    /**
     * 查询日志
     * @param log log
     * @return java.util.List<com.cattle.inner.bean.OptLog>
     * @author niujie
     * @date 2023/8/6
     */
    List<OptLog> getLogs(OptLog log) throws Exception;
}
