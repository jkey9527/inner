package com.cattle.inner.bean;

import lombok.Data;

/**
 * 分页实体
 *
 * @author niujie
 * @date 2023/4/30 20:36
 */
@Data
public class PageBean {

    /**
     * 当前页
     */
    private int pageNum;

    /**
     * 每页大小
     */
    private int pageSize;
}
