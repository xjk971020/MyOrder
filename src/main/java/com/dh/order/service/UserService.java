package com.dh.order.service;

import com.dh.order.dao.UserMapper;
import com.dh.order.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xjk
 * @date 2019/3/8 -  10:23
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 创建商家的同时创建用户
     */
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    /**
     * 根据userId查找user
     * @param userId
     * @return
     */
    public User selectUserByUserId(Integer userId) {
        return userMapper.selectUserByUserId(userId);
    }

    /**
     * 根据userId查找merchantId
     * @param userId
     * @return
     */
    public Integer selecyMerchantIdByUserId(Integer loginId) {
        return userMapper.selecyMerchantIdByUserId(loginId);
    }
}
