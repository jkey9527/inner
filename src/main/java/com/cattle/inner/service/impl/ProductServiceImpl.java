package com.cattle.inner.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cattle.inner.bean.*;
import com.cattle.inner.mapper.ProductMapper;
import com.cattle.inner.service.*;
import com.cattle.inner.util.UuIdUtil;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 费用服务类
 *
 * @author niujie
 * @date 2023/4/21 22:40
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);

    private ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveProduct(ProductBean product) throws Exception {
        if(ObjectUtil.isNull(product)){
            throw new Exception("货品保存失败，未录入任何货品资料！");
        }
        try {
            String productId = UuIdUtil.getUUID();
            product.setPro_id(productId);
            productMapper.saveProduct(product);
            List<ProductDetailBean> productDetailBeans = product.getProductDetailBeans();
            if (CollUtil.isNotEmpty(productDetailBeans)) {
                for (ProductDetailBean productDetailBean : productDetailBeans) {
                    productDetailBean.setPro_main_id(productId);
                    productDetailBean.setPro_det_id(UuIdUtil.getUUID());
                    productMapper.saveProductDetail(productDetailBean);
                }
            }
        }catch (Exception e){
            LOGGER.error("操作异常",e);
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addProductDetail(List<ProductDetailBean> productDetails) throws Exception {
        if (CollUtil.isEmpty(productDetails)) {
            throw new Exception("入库失败，入库信息为空！");
        }
        for (ProductDetailBean productDetail : productDetails) {
            String proDetId = productDetail.getPro_det_id();
            if(StrUtil.isBlank(proDetId)){
                // 如果货品明细主键为空，则表示之前未入库，则新增入库
                productDetail.setPro_det_id(UuIdUtil.getUUID());
                productMapper.saveProductDetail(productDetail);
            }else {
                // 库存数量增加
                productMapper.addProductDetail(productDetail);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void subProductDetail(List<ProductDetailBean> productDetails) throws Exception {
        if (CollUtil.isEmpty(productDetails)) {
            throw new Exception("出库失败，出库信息为空！");
        }
        for (ProductDetailBean productDetail : productDetails) {
            productMapper.subProductDetail(productDetail);
        }
    }

    @Override
    public List<ProductBean> getProductList(ProductBean product) throws Exception {
        try {
            return productMapper.getProductList(product);
        }catch (Exception e){
            LOGGER.error(e);
            throw new Exception(e);
        }
    }

    @Override
    public ProductBean getProduct(ProductBean product) throws Exception {
        try {
            ProductBean productBean = productMapper.getProduct(product);
            if(ObjectUtil.isNull(product)){
                throw new Exception("未查询到该商品信息！");
            }
            String proId = productBean.getPro_id();
            List<ProductDetailBean> productDetailBeans = productMapper.getProductDetails(proId);
            if(CollUtil.isNotEmpty(productDetailBeans)){
                productBean.setProductDetailBeans(productDetailBeans);
            }
            return productBean;
        }catch (Exception e){
            LOGGER.error(e);
            throw new Exception(e);
        }
    }
}
