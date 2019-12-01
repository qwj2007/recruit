package com.recruit.web.common;

import com.recruit.web.util.CookieManager;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者：qiwj
 * 时间：2019/11/28
 */
public class LoginUtil {
    public static Boolean isLogin(HttpServletRequest request, Model model)
    {
        String cookies = CookieManager.getInstance().getCookie(request, "userid");
        String cookies_name = CookieManager.getInstance().getCookie(request, "username");
        if (cookies == null&&cookies!="") {
            model.addAttribute("showLogin", 0);
            return false;
        } else {
            model.addAttribute("showLogin", 1);
            model.addAttribute("username", cookies_name);
            model.addAttribute("userid", cookies);
        }
        return true;
    }

    public static String LoginOut(HttpServletRequest request, HttpServletResponse response)
    {
        String servername = request.getServerName();
        Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        if (null == cookies) {
        } else {
            for (Cookie cookie : cookies) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        CookieManager.getInstance().removeCookie(response, "userid");
        CookieManager.getInstance().removeCookie(response, "username");
        return "1";
    }
}
