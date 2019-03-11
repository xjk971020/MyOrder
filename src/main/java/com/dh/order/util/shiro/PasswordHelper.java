package com.dh.order.util.shiro;

import com.dh.order.model.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author xjk
 * @date 2019/2/14 -  10:12
 **/
public class PasswordHelper {

    static Logger logger = LoggerFactory.getLogger(PasswordHelper.class);
    /**
     * 实例化RandomNumberGenerator对象，用于生成一个随机数
     */
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    /**
     * 散列算法名称
     */
    private static final  String algorithName = "MD5";

    /**
     * 散列次数
     */
    private static final int hashIterations = 1024;

    public static void encryptPassword(User user) {
        if (user.getPassword() != null) {
            user.setSalt(randomNumberGenerator.nextBytes().toHex());
        }
        String password = new SimpleHash(algorithName,user.getPassword(), ByteSourceUtils.bytes(user.getCrenditalSalt()),hashIterations).toHex();
        user.setPassword(password);
    }


}
