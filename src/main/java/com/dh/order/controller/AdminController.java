package com.dh.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xjk
 * @date 2019/3/10 -  12:57
 * 控制页面跳转
 **/
@Controller
public class AdminController {

    /**
     * 菜品分类管理页面
     * @return
     */
    @RequestMapping("/page/groupmanager")
    public String groupManager() {
        return "page/groupManager";
    }
}
