package com.cattle.house.bean;

import lombok.Data;

/**
 * token对象
 *
 * @author niujie
 * @date 2023/4/29 11:39
 */
@Data
public class TokenBean {

    /**
     * 加密后秘钥
     */
    private String privateKey;

    /**
     * 加密偏移量
     */
    private String iv;

}
