package com.dh.order.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
/**
 * Created by YE
 * 2019-02-26 23:42
 */
public class QRCodeUtil {

    public static void createQRCode(String content){
        int width = 300;
        int height = 300;
        String format = "png";
        //定义二维码参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN,2);

        //生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            StringBuilder sb = new StringBuilder();
            sb.append("E:/").append(System.currentTimeMillis()).append(".png");
            Path file = new File(sb.toString()).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
