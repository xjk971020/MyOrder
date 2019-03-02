package com.dh.order;

import com.dh.order.util.QRCodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class OrderApplicationTests {

    @Test
    public void contextLoads() {
        QRCodeUtil.createQRCode("走你");
    }

}
