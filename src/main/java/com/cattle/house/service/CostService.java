package com.cattle.house.service;

import com.cattle.house.bean.CostBean;
import com.cattle.house.bean.UserBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 费用服务接口
 *
 * @author niujie
 * @date 2023/4/21 22:38
 */
public interface CostService {

    /**
     * 查询所有费用信息
     * @param cost cost
     * @return java.util.List<com.cattle.house.bean.CostBean>
     * @author niujie
     * @date 2023/4/22
     */
    List<CostBean> getAllCostList(CostBean cost) throws Exception;

    /**
     * 保存费用
     * @param cost cost
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    void saveCost(CostBean cost) throws Exception;

    /**
     * 计算费用
     * @param cost cost
     * @return com.cattle.house.bean.CostBean
     * @author niujie
     * @date 2023/4/22
     */
    CostBean calculateCost(CostBean cost) throws Exception;

    /**
     * 查询费用
     *
     * @param cost cost
     * @return java.util.List<com.cattle.house.bean.CostBean>
     * @author niujie
     * @date 2023/4/22
     */
    List<CostBean> getCostList(CostBean cost) throws Exception;

    /**
     * 初始化费用
     *
     * @param user user
     * @return com.cattle.house.bean.CostBean
     * @author niujie
     * @date 2023/4/24
     */
    CostBean initCost(UserBean user) throws Exception;

    /**
     * 分页查询费用
     *
     * @param cost cost
     * @return com.github.pagehelper.PageInfo<com.cattle.house.bean.CostBean>
     * @author niujie
     * @date 2023/4/30
     */
    PageInfo<CostBean> getAllCostList4Page(CostBean cost) throws Exception;

    /**
     * 通过合同编号查询费用信息
     *
     * @param cost cost
     * @return com.github.pagehelper.PageInfo<com.cattle.house.bean.CostBean>
     * @author niujie
     * @date 2023/5/1
     */
    PageInfo<CostBean> getCostListByContractNo4Page(CostBean cost) throws Exception;

    /**
     * 保存期初费用信息
     * @param cost cost
     * @return void
     * @author niujie
     * @date 2023/6/1
     */
    void saveStartCost(CostBean cost) throws Exception;

    /**
     * 删除费用信息
     * @param contractNo contractNo
     * @return void
     * @author niujie
     * @date 2023/6/1
     */
    void deleteCostByContractNo(String contractNo) throws Exception;
}
