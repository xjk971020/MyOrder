package com.dh.order.service;

import com.dh.order.dao.MerchantMapper;
import com.dh.order.dao.UserMapper;
import com.dh.order.model.Merchant;
import com.dh.order.model.User;
import com.dh.order.result.MessageResult;
import com.dh.order.result.code.OperationStatusCode;
import com.dh.order.result.enums.ResultEnums;
import com.dh.order.util.CoordinateUtil;
import com.dh.order.util.QRCodeUtil;
import com.dh.order.util.shiro.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by YE
 * 2019-02-26 22:24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MerchantService {

    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 创建商家
     * @param data
     * @return
     */
    public MessageResult createMerchant(Map<String,String> data) throws ParseException {

        String merchantName = String.valueOf(data.get("merchantName"));
        String merchantProvince = String.valueOf(data.get("merchantProvince"));
        String merchantCity = String.valueOf(data.get("merchantCity"));
        String merchantAddress = String.valueOf(data.get("merchantAddress"));
        String merchantDescribe = String.valueOf(data.get("merchantDescribe"));
        String merchantPhone = String.valueOf(data.get("merchantPhone"));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        //营业开始时间
        String startTime = String.valueOf(data.get("startTime"));
        Time sqlStartTime = new Time(sdf.parse(startTime).getTime());
        //营业结束时间
        String endTime = String.valueOf(data.get("endTime"));
        Time sqlEndTime = new Time(sdf.parse(endTime).getTime());
        //商家创建时间
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        //头像的地址
        String url =  data.get("url");

        Object[] coordinate = CoordinateUtil.getCoordinate(merchantProvince + merchantCity + merchantAddress);
        //获得经度
        Double longitude = Double.valueOf(String.valueOf(coordinate[0]));
        //获得纬度
        Double latitude = Double.valueOf(String.valueOf(coordinate[1]));

        Merchant merchant = new Merchant();
        merchant.setMerchantName(merchantName);
        merchant.setMerchantAddress(merchantAddress);
        merchant.setMerchantNation("中国");
        merchant.setMerchantProvince(merchantProvince);
        merchant.setMerchantCity(merchantCity);
        merchant.setMerchantGrade(null);
        merchant.setMerchantPrice(null);
        merchant.setMerchantCreateTime(createTime);
        merchant.setMerchantEndTime(sqlEndTime);
        merchant.setMerchantBeginTime(sqlStartTime);
        merchant.setMerchantLongitude(longitude);
        merchant.setMerchantLatitude(latitude);
        merchant.setMerchantPhone(merchantPhone);
        merchant.setMerchantDescribe(merchantDescribe);
        merchant.setMerchantUrl(url);

        merchantMapper.insert(merchant);
        //根据商家id生成二维码
        String content = merchant.getMerchantName();
        QRCodeUtil.createQRCode(content);

        //获得创建的商家的id
        Integer merchantId = merchant.getMerchantId();

        String password = String.valueOf(data.get("password"));
        User user = new User();
        user.setMerchantId(merchantId);
        user.setPassword(password);
        User insertUser = createUser(user);
        if (insertUser != null) {
            return new MessageResult(OperationStatusCode.SUCCESS, String.valueOf(insertUser.getUserId()));
        } else {
            return new MessageResult(OperationStatusCode.ERROR,ResultEnums.ERROR);
        }

    }

    /**
     * 创建商家的同时增加商家用户
     * @param user
     * @return
     */
    public User createUser(User user) {
        PasswordHelper.encryptPassword(user);
        if (userMapper.insertUser(user) != 0) {
            return user;
        }
        return null;
    }

    /**
     * 根据id获取merchant
     * @param merchantId
     * @return
     */
    public Merchant selectMerchantByMerchantId(Integer merchantId) {
        return merchantMapper.selectByPrimaryKey(merchantId);
    }

}
