package com.dh.order.dao;

import com.dh.order.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author xjk
 * @date 2019/3/7 -  15:16
 **/
@Component(value = "userMapper")
public interface UserMapper {
    /**
     * 创建商家用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据userId查找user
     * @param userId
     * @return
     */
    User selectUserByUserId(@Param(value = "userId")Integer userId);

    /**
     * 根据userId查找merchantId
     * @param userId
     * @return
     */
    Integer selecyMerchantIdByUserId(@Param(value = "userId")Integer userId);
}
