package com.cattle.house.service.impl;

import com.cattle.house.bean.PageBean;
import com.cattle.house.bean.RecordBean;
import com.cattle.house.mapper.RecordMapper;
import com.cattle.house.service.RecordService;
import com.cattle.house.util.PageUtil;
import com.cattle.house.util.UuIdUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 明细服务类
 *
 * @author niujie
 * @date 2023/4/21 22:40
 */
@Service
@AllArgsConstructor
public class RecordServiceImpl implements RecordService {
    private static final Logger LOGGER = Logger.getLogger(RecordServiceImpl.class);

    private RecordMapper recordMapper;


    @Override
    public RecordBean getRecord(RecordBean record) throws Exception {
        try {
            return recordMapper.getRecord(record);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRecord(RecordBean record) throws Exception {
        try {
            record.setR_id(UuIdUtil.getUUID());
            record.setR_create_time(new Date());
            recordMapper.saveRecord(record);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRecord(RecordBean record) throws Exception {
        try {
            recordMapper.updateRecord(record);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRecord(RecordBean record) throws Exception {
        try {
            recordMapper.deleteRecord(record);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PageInfo<RecordBean> getRecordList4Page(RecordBean record) throws Exception {
        try {
            PageBean pageBean = record.getPageBean();
            PageUtil.startPage(pageBean, "r_create_time desc");
            List<RecordBean> recordList = recordMapper.getRecordList(record);
            PageInfo<RecordBean> pageInfo = new PageInfo<>(recordList);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BigDecimal getRecordBalance() throws Exception {
        try {
            return recordMapper.getRecordBalance();
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }


}
