package com.cattle.inner.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cattle.inner.bean.OptLog;
import com.cattle.inner.bean.SystemBean;
import com.cattle.inner.interceptor.UserContext;
import com.cattle.inner.mapper.SystemMapper;
import com.cattle.inner.service.SystemService;
import com.cattle.inner.util.UuIdUtil;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *  TODO
 * @author niujie
 * @date 2023/6/1 16:55
 */
@Service
@AllArgsConstructor
public class SystemServiceImpl implements SystemService {
    private static final Logger LOGGER = Logger.getLogger(SystemServiceImpl.class);

    private SystemMapper systemMapper;


    @Override
    public List<SystemBean> getSystem() throws Exception {
        try {
            return systemMapper.getSystem();
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void saveSystem(List<SystemBean> systemList) throws Exception {
        try {
            for (SystemBean systemBean : systemList) {
                systemMapper.saveSystem(systemBean);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void saveOptLog(String model, String type, String info, String memo) {
        try {

            OptLog optLog = new OptLog();
            optLog.setLog_id(UuIdUtil.getUUID());
            optLog.setLog_model(model);
            optLog.setLog_type(type);
            optLog.setLog_info(info);
            optLog.setLog_memo(memo);
            optLog.setLog_date(new Date());
            String userId = UserContext.getUserId();
            userId = StrUtil.isBlank(userId)?"-1":userId;
            optLog.setLog_user_id(userId);
            systemMapper.saveOptLog(optLog);
        } catch (Exception e) {
            LOGGER.error("日志保存异常", e);
        }
    }

    @Override
    public void saveOptLog(String model, String type, String info) {
        saveOptLog(model, type, info, "");
    }

    @Override
    public void saveOptLog(String model, String type) {
        saveOptLog(model, type, "", "");
    }

    @Override
    public List<OptLog> getLogs(OptLog log) throws Exception {
        try {
            return systemMapper.getLogs(log);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }
}
