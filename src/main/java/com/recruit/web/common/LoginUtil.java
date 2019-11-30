package com.recruit.web.common;

import com.recruit.web.util.CookieManager;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

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
}
