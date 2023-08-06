package com.cattle.inner.controller;

import com.cattle.inner.bean.RecordBean;
import com.cattle.inner.bean.RecordParam;
import com.cattle.inner.bean.RecordResult;
import com.cattle.inner.response.Result;
import com.cattle.inner.service.RecordService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * 销售
 *
 * @author niujie
 * @date 2023/4/21 22:43
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "cattle/inner/record", method = RequestMethod.POST)
@CrossOrigin(origins = "*")
public class RecordController {
    private static final Logger LOGGER = Logger.getLogger(RecordController.class);

    private RecordService recordService;

    /**
     * 销售
     * @param record record
     * @return java.lang.String
     * @author niujie
     * @date 2023/8/6
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
     * 查询销售数据
     * @param recordParam recordParam
     * @return java.lang.String
     * @author niujie
     * @date 2023/8/6
     */
    @RequestMapping("/getRecord")
    public String getRecord(@RequestBody RecordParam recordParam) {
        try {
            RecordResult recordResult = recordService.getRecord(recordParam);
            return Result.success("操作成功！", recordResult);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }


}
