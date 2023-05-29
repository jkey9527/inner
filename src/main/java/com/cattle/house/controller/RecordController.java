package com.cattle.house.controller;

import com.cattle.house.bean.RecordBean;
import com.cattle.house.response.Result;
import com.cattle.house.service.RecordService;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 明细
 *
 * @author niujie
 * @date 2023/4/21 22:43
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "cattle/house/record", method = RequestMethod.POST)
@CrossOrigin(origins = "*")
public class RecordController {
    private static final Logger LOGGER = Logger.getLogger(RecordController.class);

    private RecordService recordService;

    /**
     * 查询明细详情
     *
     * @param record record
     * @return java.lang.String
     * @author niujie
     * @date 2023/5/27
     */
    @RequestMapping("/getRecord")
    public String getRecord(@RequestBody RecordBean record) {
        try {
            RecordBean recordBean = recordService.getRecord(record);
            return Result.success("操作成功！", recordBean);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 保存明细
     *
     * @param record record
     * @return java.lang.String
     * @author niujie
     * @date 2023/5/27
     */
    @RequestMapping("/saveRecord")
    public String saveRecord(@RequestBody RecordBean record) {
        try {
            recordService.saveRecord(record);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 修改明细
     *
     * @param record record
     * @return java.lang.String
     * @author niujie
     * @date 2023/5/27
     */
    @RequestMapping("/updateRecord")
    public String updateRecord(@RequestBody RecordBean record) {
        try {
            recordService.updateRecord(record);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 删除明细
     *
     * @param record record
     * @return java.lang.String
     * @author niujie
     * @date 2023/5/27
     */
    @RequestMapping("/deleteRecord")
    public String deleteRecord(@RequestBody RecordBean record) {
        try {
            recordService.deleteRecord(record);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 分页查询明细
     *
     * @param record record
     * @return java.lang.String
     * @author niujie
     * @date 2023/5/27
     */
    @RequestMapping("/getRecordList4Page")
    public String getRecordList4Page(@RequestBody RecordBean record) {
        try {
            PageInfo<RecordBean> pageInfo = recordService.getRecordList4Page(record);
            return Result.success("操作成功！", pageInfo);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 查询余额
     * @return java.lang.String
     * @author niujie
     * @date 2023/5/29
     */
    @RequestMapping("/getRecordBalance")
    public String getRecordBalance() {
        try {
            BigDecimal balance = recordService.getRecordBalance();
            return Result.success("操作成功！", balance);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

}
