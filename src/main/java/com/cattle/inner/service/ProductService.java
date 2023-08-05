package com.cattle.inner.service;

import com.cattle.inner.bean.ProductBean;
import com.cattle.inner.bean.ProductDetailBean;

import java.util.List;

/**
 * 货品服务接口
 *
 * @author niujie
 * @date 2023/4/21 22:38
 */
public interface ProductService {

    /**
     * 新建货品
     * @param product product
     * @return void
     * @author niujie
     * @date 2023/8/5
     */
    void saveProduct(ProductBean product) throws Exception;

    /**
     * 增加库存
     * @param productDetails productDetails
     * @return void
     * @author niujie
     * @date 2023/8/5
     */
    void addProductDetail(List<ProductDetailBean> productDetails) throws Exception;

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
}
