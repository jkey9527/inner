package com.cattle.house.service.impl;

import com.cattle.house.bean.SystemBean;
import com.cattle.house.mapper.SystemMapper;
import com.cattle.house.service.SystemService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

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
            System.out.println(e.getMessage());
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public SystemBean getSystemByCode(String code) throws Exception {
        try {
            return systemMapper.getSystemByCode(code);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }
}
