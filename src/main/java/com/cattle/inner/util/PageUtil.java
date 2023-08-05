package com.cattle.inner.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cattle.inner.bean.PageBean;
import com.cattle.inner.constant.PageConstant;
import com.github.pagehelper.PageHelper;

/**
 * 分页工具
 *
 * @author niujie
 * @date 2023/4/30 20:43
 */
public class PageUtil {

    public static void startPage(PageBean pageBean, String order) {
        if (StrUtil.isBlank(order)) {
            order = "";
        }
        if (ObjectUtil.isNull(pageBean)) {
            PageHelper.startPage(PageConstant.PAGE_NUM, PageConstant.PAGE_SIZE, order);
            return;
        }
        Integer pageNum = Convert.toInt(pageBean.getPageNum(), PageConstant.PAGE_NUM);
        Integer pageSize = Convert.toInt(pageBean.getPageSize(), PageConstant.PAGE_SIZE);
        PageHelper.startPage(pageNum, pageSize, order);
    }
}
