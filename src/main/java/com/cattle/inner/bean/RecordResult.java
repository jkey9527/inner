package com.cattle.inner.bean;

import lombok.Data;

import java.util.Map;

/**
 * 销售
 *
 * @author niujie
 * @date 2023/8/6 11:45
 */
@Data
public class RecordResult {

    /**
     * 金额对象
     */
    private Map moneyMap;

    /**
     * 数量对象
     */
    private Map countMap;
}
