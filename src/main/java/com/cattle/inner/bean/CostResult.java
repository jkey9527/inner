package com.cattle.inner.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 费用结果
 *
 * @author niujie
 * @date 2023/8/10 19:49
 */
@Data
public class CostResult {

    /**
     * 费用列表
     */
    private List<CostBean> costList;

    /**
     * 金额
     */
    private Map moneyMap;
}
