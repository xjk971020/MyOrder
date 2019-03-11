package com.dh.order.controller;

import com.dh.order.result.MessageResult;
import com.dh.order.result.code.OperationStatusCode;
import com.dh.order.result.enums.ResultEnums;
import com.dh.order.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author xjk
 * @date 2019/3/6 -  17:36
 **/
@Controller
public class  LoginController {

    @Autowired
    UserService userService;
    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 用户登陆的入口
     * @param userName
     * @param password
     * @param rememberMe
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public MessageResult login(
            @RequestParam(value = "loginId", required = false) String loginId,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "remember", required = false) String rememberMe
    ) {
        String isRememberMe = "true";
        if (loginId != null && password != null) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(loginId, password);
            if (rememberMe != null) {
                if (rememberMe.equals(isRememberMe)) {
                    //说明选择了记住我
                    token.setRememberMe(true);
                } else {
                    token.setRememberMe(false);
                }
            } else {
                token.setRememberMe(false);
            }
            try {
                subject.login(token);
                System.out.println("是否登录：" + subject.isAuthenticated());
                return new MessageResult(OperationStatusCode.SUCCESS, ResultEnums.SUCCESS);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return new MessageResult(OperationStatusCode.ERROR, ResultEnums.LOGIN_UNKNOWN);
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return new MessageResult(OperationStatusCode.ERROR, ResultEnums.LOGIN_ERROR);
            } catch (Exception e) {
                e.printStackTrace();
                return new MessageResult(OperationStatusCode.ERROR, ResultEnums.INNER_ERROR);
            }
        } else {
            return new MessageResult(OperationStatusCode.ERROR, ResultEnums.INPUT_ERROR);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "page/index";
    }
}
