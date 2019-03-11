package com.dh.order.realm;

import com.dh.order.model.User;
import com.dh.order.service.UserService;
import com.dh.order.util.shiro.ByteSourceUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xjk
 * @date 2019/3/6 -  16:59
 **/
public class MyRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(AuthorizingRealm.class);

    @Autowired
    private UserService userService;
    /**
     * 权限校验相关
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 身份认证相关
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        Integer userId = Integer.parseInt((String)token.getPrincipal());
        User user = userService.selectUserByUserId(userId);
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (Boolean.TRUE.equals(user.isLocked())) {
            throw new LockedAccountException();
        }
        Object principal = user.getUserId();
        Object credentials = user.getPassword();
        ByteSource credentialsSalt = ByteSourceUtils.bytes(user.getCrenditalSalt());
        String realmName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
        return info;
    }
}
