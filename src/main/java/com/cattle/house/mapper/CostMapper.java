package com.cattle.house.mapper;

import com.cattle.house.bean.CostBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 费用映射类
 *
 * @author niujie
 * @date 2023/4/21 22:35
 */
@Mapper
public interface CostMapper {

    /**
     * 查询费用列表
     * @param cost cost
     * @return java.util.List<com.cattle.house.bean.CostBean>
     * @author niujie
     * @date 2023/4/22
     */
    List<CostBean> getAllCostList(CostBean cost);

    /**
     * 保存费用
     * @param cost cost
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    void saveCost(CostBean cost);

    /**
     * 查询费用
     *
     * @param cost cost
     * @return java.util.List<com.cattle.house.bean.CostBean>
     * @author niujie
     * @date 2023/4/22
     */
    List<CostBean> getCostList(CostBean cost);

    /**
     * 通过合同编号查询费用列表信息
     *
     * @param contractNo contractNo
     * @return java.util.List<com.cattle.house.bean.CostBean>
     * @author niujie
     * @date 2023/4/24
     */
    List<CostBean> getCostListByContractNo(String contractNo);

    /**
     * 删除费用信息
     * @param contractNo contractNo
     * @return void
     * @author niujie
     * @date 2023/6/1
     */
    void deleteCostByContractNo(String contractNo);
}
