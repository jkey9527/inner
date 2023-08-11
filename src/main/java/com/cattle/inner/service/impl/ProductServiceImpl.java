package com.cattle.inner.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.cattle.inner.bean.PageBean;
import com.cattle.inner.bean.ProductBean;
import com.cattle.inner.bean.ProductDetailBean;
import com.cattle.inner.bean.RecordBean;
import com.cattle.inner.enums.*;
import com.cattle.inner.mapper.ProductMapper;
import com.cattle.inner.mapper.RecordMapper;
import com.cattle.inner.service.ProductService;
import com.cattle.inner.service.SystemService;
import com.cattle.inner.util.PageUtil;
import com.cattle.inner.util.UuIdUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private SystemService systemService;
    private RecordMapper recordMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveProduct(ProductBean product) throws Exception {
        if(ObjectUtil.isNull(product)){
            throw new Exception("货品保存失败，未录入任何货品资料！");
        }
        try {
            List<ProductBean> productList = productMapper.getProductList(product);
            if(CollUtil.isNotEmpty(productList)){
                throw new Exception("货号已存在，请重新录入货号！");
            }
            String productId = UuIdUtil.getUUID();
            product.setPro_id(productId);
            List<ProductDetailBean> productDetailBeans = product.getProductDetailBeans();
            checkProductDetail(productDetailBeans);
            productMapper.saveProduct(product);
            if (CollUtil.isNotEmpty(productDetailBeans)) {
                for (ProductDetailBean productDetailBean : productDetailBeans) {
                    productDetailBean.setPro_main_id(productId);
                    productDetailBean.setPro_det_id(UuIdUtil.getUUID());
                    productMapper.saveProductDetail(productDetailBean);
                }
            }
            systemService.saveOptLog(LogModelEnum.product.getValue(), LogTypeEnum.save.getValue(), JSONUtil.toJsonStr(product));
        }catch (Exception e){
            LOGGER.error("操作异常",e);
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateProduct(ProductBean product) throws Exception {
        if(ObjectUtil.isNull(product)){
            throw new Exception("货品更新失败，未录入任何货品资料！");
        }
        if(StrUtil.isBlank(product.getPro_no())){
            throw new Exception("货号不能为空！");
        }
        try {
            checkProduct(product,"update");
            ProductBean existsProduct = productMapper.getProductByProNo(product.getPro_no());
            if (ObjectUtil.isNotNull(existsProduct)) {
                if (!ObjectUtil.equals(product.getPro_id(), existsProduct.getPro_id())) {
                    throw new Exception("货品更新失败，货号已存在！");
                }
            }
            String proId = product.getPro_id();
            List<ProductDetailBean> productDetails = product.getProductDetailBeans();
            checkProductDetail(productDetails);
            productMapper.deleteProductDetailByMainId(proId);
            for (ProductDetailBean productDetail : productDetails) {
                productDetail.setPro_main_id(proId);
                productDetail.setPro_det_id(UuIdUtil.getUUID());
                productMapper.saveProductDetail(productDetail);
            }
            productMapper.updateProduct(product);
            systemService.saveOptLog(LogModelEnum.product.getValue(), LogTypeEnum.update.getValue(), JSONUtil.toJsonStr(product));
        }catch (Exception e){
            LOGGER.error("更新货品异常", e);
            throw new Exception(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteProduct(ProductBean product) throws Exception {
        if(ObjectUtil.isNull(product)){
            throw new Exception("货品删除失败，未录入任何货品资料！");
        }
        try {
            checkProduct(product,"delete");
            ProductBean existsProduct = productMapper.getProduct(product);
            if (ObjectUtil.isNull(existsProduct)) {
                throw new Exception("货品删除失败，货品不存在！");
            }
            String proId = product.getPro_id();
            productMapper.deleteProductDetailByMainId(proId);
            productMapper.deleteProduct(product);
            systemService.saveOptLog(LogModelEnum.product.getValue(), LogTypeEnum.delete.getValue(), JSONUtil.toJsonStr(product));
        }catch (Exception e){
            LOGGER.error("删除货品异常", e);
            throw new Exception(e);
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
        systemService.saveOptLog(LogModelEnum.product.getValue(), LogTypeEnum.update.getValue(), JSONUtil.toJsonStr(productDetails));
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

    @Override
    public PageInfo<ProductBean> getProducts(ProductBean product) throws Exception {
        try {
            PageBean pageBean = product.getPageBean();
            PageUtil.startPage(pageBean, "");
            List<ProductBean> productList = productMapper.getProductList(product);
            List<ProductDetailBean> productDetailBeanList = productMapper.getAllProductDetail();
            Map<String, List<ProductDetailBean>> detailMap =
                    productDetailBeanList.stream().collect(Collectors.groupingBy(ProductDetailBean::getPro_main_id));
            for (ProductBean productBean : productList) {
                String proId = productBean.getPro_id();
                if (!detailMap.containsKey(proId)) {
                    continue;
                }
                List<ProductDetailBean> productDetailBeans = detailMap.get(proId);
                for (ProductDetailBean productDetailBean : productDetailBeans) {
                    productDetailBean.setPro_det_color_name(ColorTypeEnum.getNameByValue(productDetailBean.getPro_det_color()));
                    productDetailBean.setPro_det_size_name(SizeTypeEnum.getNameByValue(productDetailBean.getPro_det_size()));
                }
                productBean.setProductDetailBeans(productDetailBeans);
                productBean.setPro_type_name(ProductTypeEnum.getNameByValue(productBean.getPro_type()));
            }
            PageInfo<ProductBean> pageInfo = new PageInfo<>(productList);
            return pageInfo;
        }catch (Exception e){
            LOGGER.error(e);
            throw new Exception(e);
        }
    }

    /**
     * 检查明细数据是否重复
     * @param productDetails productDetails
     * @return void
     * @author niujie
     * @date 2023/8/9
     */
    private void checkProductDetail(List<ProductDetailBean> productDetails) throws Exception {

        if(CollUtil.isEmpty(productDetails)){
            return;
        }
        int count = productDetails.stream()
                .map(v -> Convert.toStr(v.getPro_det_size(), "")
                        + Convert.toStr(v.getPro_det_color(), ""))
                .distinct().collect(Collectors.toList()).size();
        if(productDetails.size() != count){
            throw new Exception("明细中存在重复的颜色和尺寸项！");
        }
    }

    /**
     * 校验货品能否删除或者修改类型
     * @param product product
     * @return void
     * @author niujie
     * @date 2023/8/10
     */
    private void checkProduct(ProductBean product,String type) throws Exception {
        List<RecordBean> recordBeans =
                recordMapper.getRecordByProId(product.getPro_id());
        if(CollUtil.isEmpty(recordBeans)){
            return;
        }

        if(ObjectUtil.equals("delete",type)){
            throw new Exception("该货品已发生交易，不允许删除！");
        }else{
            ProductBean exists = productMapper.getProductById(product.getPro_id());
            if(!ObjectUtil.equals(product.getPro_type(),exists.getPro_type())){
                throw new Exception("该货品已发生交易，不允许修改！");
            }
        }
    }
}
