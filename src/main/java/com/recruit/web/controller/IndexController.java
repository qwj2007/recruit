package com.recruit.web.controller;


import com.recruit.web.common.LoginUtil;
import com.recruit.web.pojo.News;
import com.recruit.web.pojo.Recruitinfo;
import com.recruit.web.pojo.Teacher;
import com.recruit.web.pojo.Userinfo;
import com.recruit.web.service.INewsService;
import com.recruit.web.service.IRecruitInfoService;
import com.recruit.web.service.ITeacherService;
import com.recruit.web.service.IUserinfoService;
import com.recruit.web.util.CookieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
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

    @RequestMapping("/")
    public String GetInfos(Model model, HttpServletRequest request) {
        List<Recruitinfo> list = recruitInfoService.selectRecruitInfos();
        List<News> newslist = newsService.selectNews();
        LoginUtil.isLogin(request,model);
        //if(list!=null) {
        model.addAttribute("recruitinfos", list);
        model.addAttribute("news", newslist);
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
            response.addCookie(cookie);
            cookie = new Cookie("username", userinfo.getUsername());
            cookie.setDomain(request.getServerName());
            cookie.setPath("/");
            response.addCookie(cookie);

            // CookieManager.getInstance().saveCookie(response, "userid",userinfo.getId().toString());
            // CookieManager.getInstance().saveCookie(response, "username",userinfo.getUsername().toString());
            result = "1";
        }
        return result;
    }

    @RequestMapping("index/loginOut")
    @ResponseBody
    public String Loginout(HttpServletRequest request, HttpServletResponse response) {
        String servername = request.getServerName();
        Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        if (null == cookies){
    }
    else {
            for(Cookie cookie:cookies)
            {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        CookieManager.getInstance().removeCookie(response,"userid");
        CookieManager.getInstance().removeCookie(response,"username");
        return "1";
    }

}
