package com.dh.order.controller;

import com.dh.order.model.Food;
import com.dh.order.service.FoodService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by YE
 * 2019-02-27 21:27
 */
@RestController
@RequestMapping("/food")
@CrossOrigin
public class FoodController {
    @Resource
    private FoodService foodService;

    @GetMapping(value = "/selectByMerchantId")
    public List<Map<String, Object>> selectByMerchantId(@RequestParam Integer merchantId){
        return foodService.selectByMerchantId(merchantId);
    }
}
