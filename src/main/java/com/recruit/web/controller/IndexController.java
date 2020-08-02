package com.recruit.web.controller;


import com.recruit.web.common.LoginUtil;
import com.recruit.web.pojo.*;
import com.recruit.web.service.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 作者：qiwj
 * 时间：2019/11/22
 */
@Controller
public class IndexController {
    @Autowired
    private IRecruitInfoService recruitInfoService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private INewsService newsService;
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IBannerService bannerService;

    @RequestMapping("/")
    public String GetInfos(Model model, HttpServletRequest request) {
        LoginUtil.isLogin(request, model);

        List<Banner> listbanner=bannerService.selectBannerQT();
        List<Recruitinfo> list = recruitInfoService.selectRecruitInfos();
        List<News> newslist = newsService.selectNews();
        //if(list!=null) {
        model.addAttribute("recruitinfos", list);
        model.addAttribute("news", newslist);
        model.addAttribute("bannerList",listbanner);
        //}
        return "index";
    }

    @RequestMapping("index/getu")
    @ResponseBody
    public String GetName() {
        Teacher teacher = teacherService.selectById();
        return teacher.toString();
    }

    @RequestMapping("index/login")
    @ResponseBody
    public String Login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        Userinfo userinfo = userinfoService.getUserInfoByNamePwd(username, pwd);
        String result = "0";
        if (userinfo != null) {
            Cookie cookie = new Cookie("userid", userinfo.getId().toString());
            cookie.setPath("/");
            cookie.setDomain(request.getServerName());
            cookie.setMaxAge(-1);//-1表示关闭浏览器，cookie就失效。
            response.addCookie(cookie);
            cookie = new Cookie("username", userinfo.getUsername());
            cookie.setDomain(request.getServerName());
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);

            // CookieManager.getInstance().saveCookie(response, "userid",userinfo.getId().toString());
            // CookieManager.getInstance().saveCookie(response, "username",userinfo.getUsername().toString());
            result = userinfo.getId().toString();
        } else {
            Employee employee = employeeService.getEmployeeByUserIdPwd(username.trim(), pwd.trim());
            if (employee != null) {
                Cookie cookie = new Cookie("userid", employee.getUserid());
                //Cookie cookie = new Cookie("employeeid", employee.getUserid());
                cookie.setPath("/");
                cookie.setDomain(request.getServerName());
                cookie.setMaxAge(-1);//-1表示关闭浏览器，cookie就失效。
                response.addCookie(cookie);
               // cookie = new Cookie("employeename", employee.getTruename());
                cookie = new Cookie("username", employee.getTruename());
                cookie.setDomain(request.getServerName());
                cookie.setPath("/");
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
                result = "manager";
            }
        }
        return result;
    }

    @RequestMapping("index/loginOut")
    @ResponseBody
    public String Loginout(HttpServletRequest request, HttpServletResponse response) {
        String str = LoginUtil.LoginOut(request, response);
        return str;
    }

    @RequestMapping("index/outlogin")
    public String OutLogin(HttpServletRequest request, HttpServletResponse response) {
        LoginUtil.LoginOut(request, response);
        return "redirect:/";
    }
}
