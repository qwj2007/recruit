package com.recruit.web.common;

import com.recruit.web.pojo.Navigation;
import com.recruit.web.pojo.Resumes;
import com.recruit.web.service.INavigationService;
import com.recruit.web.service.IResumesService;
import com.recruit.web.util.CookieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/11/28
 */
@Component
public class LoginUtil {
    @Autowired
    private IResumesService resumesService;
    @Autowired
    private static IResumesService staticresume;
    @Autowired
    private INavigationService navigationService;
    @Autowired
    private static INavigationService staticnavigationService;

    @PostConstruct
    public void init() {
        staticresume = resumesService;
        staticnavigationService = navigationService;
    }

    public static Boolean isLogin(HttpServletRequest request, Model model) {
        //获取导航信息
        List<Navigation> listNavication = staticnavigationService.selectNavigation();
        if (listNavication != null && listNavication.size() > 0) {
            model.addAttribute("menus", listNavication);
        } else {
            model.addAttribute("menus", new ArrayList<Navigation>());
        }
        String userid = CookieManager.getInstance().getCookie(request, "userid");
        String username = CookieManager.getInstance().getCookie(request, "username");
        if (userid == null || userid == "") {
            model.addAttribute("showLogin", 0);
            model.addAttribute("photourl", "");

            return false;
        } else {
            //已经登录
            model.addAttribute("showLogin", 1);
            model.addAttribute("username", username);
            model.addAttribute("userid", userid);
            Resumes resumes = staticresume.selctResumeByUserId(userid);
            model.addAttribute("photourl", resumes != null ? resumes.getPhoto() : "");

        }

        return true;
    }

    public static String LoginOut(HttpServletRequest request, HttpServletResponse response) {
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
