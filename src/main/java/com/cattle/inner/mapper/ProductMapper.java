package com.cattle.inner.mapper;

import com.cattle.inner.bean.ProductBean;
import com.cattle.inner.bean.ProductDetailBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 货品映射类
 *
 * @author niujie
 * @date 2023/4/21 22:35
 */
@Mapper
public interface ProductMapper {

    /**
     * 保存货品
     * @param product product
     * @return void
     * @author niujie
     * @date 2023/8/5
     */
    void saveProduct(ProductBean product);

    /**
     * 保存货品明细
     * @param productDetailBean productDetail
     * @return void
     * @author niujie
     * @date 2023/8/5
     */
    void saveProductDetail(ProductDetailBean productDetailBean);

    /**
     * 入库
     * @param productDetail productDetail
     * @return void
     * @author niujie
     * @date 2023/8/5
     */
    void addProductDetail(ProductDetailBean productDetail);

    /**
     * 出库
     * @param productDetail productDetail
     * @return void
     * @author niujie
     * @date 2023/8/5
     */
    void subProductDetail(ProductDetailBean productDetail);

    /**
     * 查询货品信息
     * @param product product
     * @return java.util.List<com.cattle.inner.bean.ProductBean>
     * @author niujie
     * @date 2023/8/5
     */
    List<ProductBean> getProductList(ProductBean product);

    /**
     * 查询货品信息
     * @param product product
     * @return com.cattle.inner.bean.ProductBean
     * @author niujie
     * @date 2023/8/5
     */
    ProductBean getProduct(ProductBean product);

    /**
     * 查询货品明细信息
     * @param proId proId
     * @return java.util.List<com.cattle.inner.bean.ProductDetailBean>
     * @author niujie
     * @date 2023/8/5
     */
    List<ProductDetailBean> getProductDetails(String proId);

    /**
     * 根据货品ID集合查询货品信息
     * @param proIds proIds
     * @return java.util.List<com.cattle.inner.bean.ProductBean>
     * @author niujie
     * @date 2023/8/6
     */
    List<ProductBean> getProductListByProIds(List<String> proIds);

    /**
     * 查询货品明细信息
     * @return java.util.List<com.cattle.inner.bean.ProductDetailBean>
     * @author niujie
     * @date 2023/8/6
     */
    List<ProductDetailBean> getAllProductDetail();

    /**
     * 根据货号查询货品信息
     * @param pro_no pro_no
     * @return com.cattle.inner.bean.ProductBean
     * @author niujie
     * @date 2023/8/9
     */
    ProductBean getProductByProNo(String pro_no);

    /**
     * 根据货品ID删除明细
     * @param proId proId
     * @return void
     * @author niujie
     * @date 2023/8/9
     */
    void deleteProductDetailByMainId(String proId);

    /**
     * 更新货品信息
     * @param product product
     * @return void
     * @author niujie
     * @date 2023/8/9
     */
    void updateProduct(ProductBean product);

    /**
     * 删除货品
     * @param product product
     * @return void
     * @author niujie
     * @date 2023/8/9
     */
    void deleteProduct(ProductBean product);
}
