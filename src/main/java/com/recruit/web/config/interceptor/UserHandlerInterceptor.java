package com.recruit.web.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.recruit.web.pojo.Baseinfo;
import com.recruit.web.pojo.Navigation;
import com.recruit.web.pojo.Resumes;
import com.recruit.web.service.IBaseInfoService;
import com.recruit.web.service.IHrnoticeService;
import com.recruit.web.service.INavigationService;
import com.recruit.web.service.IResumesService;
import com.recruit.web.util.CookieManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：qiwj
 * 时间：2020/8/1
 */
@Component
public class UserHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private IResumesService resumesService;

    @Autowired
    private INavigationService navigationService;


    @Autowired
    private IBaseInfoService baseInfoService;


    @Autowired
    private IHrnoticeService hrnoticeService;


    private final Logger logger = LoggerFactory.getLogger(UserHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("======== 拦截 ========");
        String userid = CookieManager.getInstance().getCookie(request, "userid");

        if (userid == null || userid == "") {
            userid="";
            //没有登录
            //CookieManager.getInstance().saveCookie(response, "showLogin", "");
            // CookieManager.getInstance().saveCookie(response, "photourl", "");
            //request.setAttribute("showLogin", 0);
            // request.setAttribute("photourl", "");
            System.out.println(request.getRequestURI());
            //response.sendRedirect("/");
            response.sendRedirect(request.getRequestURI());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        logger.info("调用postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        logger.info("调用afterCompletion");
    }
}
