package com.cattle.house.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cattle.house.bean.ContractBean;
import com.cattle.house.bean.UserBean;
import com.cattle.house.mapper.ContractMapper;
import com.cattle.house.mapper.UserMapper;
import com.cattle.house.service.UserService;
import com.cattle.house.util.UuIdUtil;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务类
 *
 * @author niujie
 * @date 2023/4/21 22:40
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private UserMapper userMapper;

    private ContractMapper contractMapper;

    @Override
    public UserBean loginIn(UserBean userBean) throws Exception {
        String userNo = userBean.getUser_no();
        String userPhone = userBean.getUser_phone();
        String userPassword = userBean.getUser_password();
        if ((StrUtil.isBlank(userNo) && StrUtil.isBlank(userPhone)) || StrUtil.isBlank(userPassword)) {
            throw new Exception("用户名或密码不能为空！");
        }
        return userMapper.getUserBean(userBean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUser(UserBean userBean) throws Exception {
        try {
            List<UserBean> userList = getUserList(new UserBean());
            if (CollUtil.isNotEmpty(userList)) {
                List<UserBean> existsUserList = userList.stream().filter(v -> ObjectUtil.equal(v.getUser_no(), userBean.getUser_no())
                        || ObjectUtil.equal(v.getUser_phone(), userBean.getUser_phone())).toList();
                if (CollUtil.isNotEmpty(existsUserList)) {
                    throw new Exception("用户已存在！");
                }
            }
            checkUserInfo(userBean);
            ContractBean contractBean = new ContractBean();
            contractBean.setCon_no(userBean.getUser_contract_no());
            List<ContractBean> contractList = contractMapper.getContractList(contractBean);
            if(CollUtil.isEmpty(contractList)){
                throw new Exception("合同不存在！");
            }
            userBean.setUser_id(UuIdUtil.getUUID());
            userMapper.saveUser(userBean);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUser(UserBean userBean) throws Exception {
        try {
            String userId = userBean.getUser_id();
            if (StrUtil.isBlank(userId)) {
                throw new Exception("参数{userId}异常");
            }
            UserBean user = getUserByUserId(userId);
            if (ObjectUtil.isNull(user)) {
                throw new Exception("删除失败，未查询到用户信息");
            }
            userMapper.deleteUser(userBean);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UserBean getUserByUserId(String userId) throws Exception {
        try {
            if (StrUtil.isBlank(userId)) {
                throw new Exception("参数{userId}异常");
            }
            return userMapper.getUserByUserId(userId);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(UserBean userBean) throws Exception {
        try {
            String userId = userBean.getUser_id();
            if (StrUtil.isBlank(userId)) {
                throw new Exception("参数{userId}异常");
            }
            checkUserInfo(userBean);
            UserBean user = getUserByUserId(userId);
            if (ObjectUtil.isNull(user)) {
                throw new Exception("修改失败，未查询到用户信息");
            }
            List<UserBean> userList = getUserList(new UserBean());
            if (CollUtil.isNotEmpty(userList)) {
                List<UserBean> existsUserList = userList.stream().filter(v -> (ObjectUtil.equal(v.getUser_no(), userBean.getUser_no())
                        || ObjectUtil.equal(v.getUser_phone(), userBean.getUser_phone())) && !ObjectUtil.equal(v.getUser_id(), userBean.getUser_id())).toList();
                if (CollUtil.isNotEmpty(existsUserList)) {
                    throw new Exception("用户已存在！");
                }
            }
            ContractBean contractBean = new ContractBean();
            contractBean.setCon_no(userBean.getUser_contract_no());
            List<ContractBean> contractList = contractMapper.getContractList(contractBean);
            if(CollUtil.isEmpty(contractList)){
                throw new Exception("合同不存在！");
            }
            userMapper.updateUser(userBean);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UserBean> getUserList(UserBean user) throws Exception {
        try {
            return userMapper.getUserList(user);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 检查用户信息
     *
     * @param userBean userBean
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    private void checkUserInfo(UserBean userBean) throws Exception {
        String userNo = userBean.getUser_no();
        String userPhone = userBean.getUser_phone();
        String userPassword = userBean.getUser_password();
        if (StrUtil.isBlank(userNo) && StrUtil.isBlank(userPhone)) {
            throw new Exception("用户名/手机号不能同时不能为空！");
        }
        if (StrUtil.isBlank(userPassword)) {
            throw new Exception("密码不能为空！");
        }
    }
}
