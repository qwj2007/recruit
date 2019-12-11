package com.recruit.web.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * 作者：qiwj
 * 时间：2019/12/11
 */
public class EncodeBase64 {
    public static String encodeBase64(String value) throws Exception {
        byte[] b = null;
        String s = null;
        try {
            b = value.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    /*** 解码
     34
     * decode by Base64
     35
     */

    public static String decodeBase64(String value) throws Exception {
        byte[] b = null;
        String result = null;
        if (value != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(value);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
