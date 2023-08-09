package com.cattle.inner.service;

import com.cattle.inner.bean.ProductBean;
import com.cattle.inner.bean.ProductDetailBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 货品服务接口
 *
 * @author niujie
 * @date 2023/4/21 22:38
 */
public interface ProductService {

    /**
     * 增加货品
     * @param product product
     * @return void
     * @author niujie
     * @date 2023/8/5
     */
    void saveProduct(ProductBean product) throws Exception;

    /**
     * 更新货品
     * @param product product
     * @return void
     * @author niujie
     * @date 2023/8/5
     */
    void updateProduct(ProductBean product) throws Exception;

    /**
     * 减少库存
     * @param productDetails productDetails
     * @return void
     * @author niujie
     * @date 2023/8/5
     */
    void subProductDetail(List<ProductDetailBean> productDetails) throws Exception;

    /**
     * 查询货品信息
     * @param product product
     * @return java.util.List<com.cattle.inner.bean.ProductBean>
     * @author niujie
     * @date 2023/8/5
     */
    List<ProductBean> getProductList(ProductBean product) throws Exception;

    /**
     * 查询货品信息
     * @param product product
     * @return com.cattle.inner.bean.ProductBean
     * @author niujie
     * @date 2023/8/5
     */
    ProductBean getProduct(ProductBean product) throws Exception;

    /**
     * 库存
     * @param product product
     * @return com.github.pagehelper.PageInfo<com.cattle.inner.bean.ProductBean>
     * @author niujie
     * @date 2023/8/9
     */
    PageInfo<ProductBean> getProducts(ProductBean product) throws Exception;

    /**
     * 删除货品
     * @param product product
     * @return void
     * @author niujie
     * @date 2023/8/9
     */
    void deleteProduct(ProductBean product) throws Exception;
}
