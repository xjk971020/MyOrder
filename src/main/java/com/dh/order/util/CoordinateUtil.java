package com.dh.order.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author xjk
 * @date 2019/3/6 -  15:39
 * 根据商家地址获取经纬度
 **/
public class CoordinateUtil {
    /**
     * @param addr
     * 查询的地址
     * @return  Object[]
     * @throws IOException
     */
    public static Object[] getCoordinate(String addr){
        //经度
        String lng = null;
        //纬度
        String lat = null;
        String address = null;
        try {
            address = java.net.URLEncoder.encode(addr, "UTF-8");
        }catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String key = "G54wnWXxq1Xj2CcO0OwwaNSNsNdkidpy";
        String url = String .format("http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s", address, key);
        URL myURL = null;
        URLConnection httpsConn;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            // 不使用代理
            httpsConn = myURL.openConnection();
            if (httpsConn != null) {
                insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data;
                int count = 1;
                while((data= br.readLine())!=null){
                    if(count == 5){
                        //经度
                        lng = (String)data.subSequence(data.indexOf(":")+1, data.indexOf(","));
                        count++;
                    }else if(count == 6){
                        //纬度
                        lat = data.substring(data.indexOf(":")+1);
                        count++;
                    }else{
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NullPointerException e) {
            e.printStackTrace();
        } finally {
            try {
                if(insr!=null){
                    insr.close();
                }
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Object[]{lng,lat};
    }
}
