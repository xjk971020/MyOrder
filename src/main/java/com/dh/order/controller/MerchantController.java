package com.dh.order.controller;


import com.dh.order.model.Merchant;
import com.dh.order.result.MessageResult;
import com.dh.order.result.code.OperationStatusCode;
import com.dh.order.result.enums.ResultEnums;
import com.dh.order.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YE
 * 2019-02-26 21:27
 */
@RestController
@RequestMapping(value = "/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping("/create")
    public MessageResult createMerchant(@RequestParam(value = "f")MultipartFile file, HttpServletRequest request) {
        String merchantName = request.getParameter("name");
        String merchantProvince = request.getParameter("province");
        String merchantCity = request.getParameter("city");
        String merchantAddress = request.getParameter("address");
        String merchantDescribe = request.getParameter("description");
        String merchantPhone = request.getParameter("phone");
        String startTime = request.getParameter("start-time");
        String endTime = request.getParameter("end-time");
        String password = request.getParameter("form-password");
        String url = "";
        if (file != null) {
            url = savePic(file);
        }
        if (url == null) {
             url = "D:/Git/order/images/默认头像.jpg";
        }
        Map<String,String> data = new HashMap<>(16);


        data.put("merchantName",merchantName);
        data.put("merchantProvince",merchantProvince);
        data.put("merchantCity",merchantCity);
        data.put("merchantAddress",merchantAddress);
        data.put("merchantDescribe",merchantDescribe);
        data.put("merchantPhone",merchantPhone);
        data.put("startTime",startTime);
        data.put("endTime",endTime);
        data.put("password",password);
        data.put("url",url);
        try {
            return merchantService.createMerchant(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new MessageResult(OperationStatusCode.ERROR, ResultEnums.ERROR);
    }

    /**
     * 保存文件
     * @param file
     * @return
     */
    public String savePic(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = "D:/Git/order/images/"
                        + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(filePath));
                return filePath;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
