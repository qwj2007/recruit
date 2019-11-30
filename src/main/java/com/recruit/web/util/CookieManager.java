package com.recruit.web.util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;

import java.io.UnsupportedEncodingException;

import com.recruit.web.constant.UserConstant;
import org.apache.commons.lang.StringUtils;


/**
 * cookie管理类
 *
 * @author zhaozhen
 */
public class CookieManager {
    private static CookieManager instance;

    public static CookieManager getInstance() {
        if (instance == null) {
            synchronized (CookieManager.class) {
                if (instance == null) {
                    instance = new CookieManager();
                }
            }
        }
        return instance;
    }

    public String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0){
            return null;}
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(key)) {
                return cookies[i].getValue();
            }
        }
        return null;
    }

    public void saveCookie(HttpServletResponse response, String key, String value) {
        this.saveCookie(response, key, value, -1, "/");
    }

    public void saveCookie(HttpServletResponse response, String key, String value, String path) {
        this.saveCookie(response, key, value, -1, path);
    }

    public void saveCookie(HttpServletResponse response, String key, String value, int second) {
        this.saveCookie(response, key, value, second, "/");
    }

    public void saveCookie(HttpServletResponse response, String key, String value, int second, String path) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath(path);
        cookie.setMaxAge(second);
        cookie.setDomain(UserConstant.COOKIE_DOMAIN);
        response.addCookie(cookie);
    }

    public void saveCookie(HttpServletResponse response, String key,
                           String value, int second, String path, String domain) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath(path);
        cookie.setMaxAge(second);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    /**
     * 根据Cookie名字从客户端清除Cookie
     * @param response
     * @param key
     */
    public  void removeCookie(HttpServletResponse response, String key){
        Cookie cookie = new Cookie(key, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setDomain(UserConstant.COOKIE_DOMAIN);
        response.addCookie(cookie);
    }

    public void clearCookie(HttpServletResponse response, String key,
                            int second, String path) {
        Cookie cookie = new Cookie(key, null);
        cookie.setPath(path);
        cookie.setMaxAge(second);
        cookie.setDomain(UserConstant.COOKIE_DOMAIN);
        response.addCookie(cookie);
    }

    public void clearCookie(HttpServletResponse response, String key,
                            int second, String path, String domain) {
        Cookie cookie = new Cookie(key, null);
        cookie.setPath(path);
        cookie.setMaxAge(second);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    /**
     * 数据加密 value+base64+AES
     * @param value
     * @return
     */
    public String encrypt(String value) throws UnsupportedEncodingException {

        if (StringUtils.isEmpty(value)){return null;}
        byte[] enbyte=AESUtil.encrypt(value,UserConstant.USER_SESSION_AES_PASSWORD);
        if (enbyte==null){return null;}
        return AESUtil.parseByte2HexStr(enbyte);
    }

    /**
     * 数据解密
     * @param value
     * @return
     */
    public String dencrypt(String value) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(value)){return null;}
        byte [] debyte=AESUtil.decrypt(AESUtil.parseHexStr2Byte(value),UserConstant.USER_SESSION_AES_PASSWORD);
        if (debyte==null){return null;}
        return new String(debyte);
    }

}
