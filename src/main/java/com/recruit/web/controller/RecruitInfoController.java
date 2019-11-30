package com.recruit.web.controller;

import com.recruit.web.common.LoginUtil;
import com.recruit.web.pojo.News;
import com.recruit.web.pojo.Recruitinfo;
import com.recruit.web.pojo.Userinfo;
import com.recruit.web.service.INewsService;
import com.recruit.web.service.IRecruitInfoService;
import com.recruit.web.service.IUserinfoService;
import com.recruit.web.util.CookieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/11/28
 */
@Controller
@RequestMapping("center")
public class RecruitInfoController {

    @Autowired
    private IRecruitInfoService recruitInfoService;
    @Autowired
    private INewsService newsService;
    @Autowired
    private IUserinfoService userinfoService;

    @RequestMapping("/recruitlist")
    public String getMoreRecruit(Model model, HttpServletRequest request) {
        Boolean result = LoginUtil.isLogin(request, model);
        List<Recruitinfo> list = recruitInfoService.selectRecruitInfos();
        model.addAttribute("recruitinfos", list);
        return "recruitlist";
    }

    @RequestMapping("/newslist")
    public String getNewsMore(Model model, HttpServletRequest request) {
        Boolean result = LoginUtil.isLogin(request, model);
        List<News> list = newsService.selectNews();
        model.addAttribute("newslist", list);
        return "newslist";
    }

    @RequestMapping("/recruitinfos")
    public String recruitinfos(Model model, HttpServletRequest request) {
        LoginUtil.isLogin(request, model);
        Integer id = Integer.parseInt(request.getParameter("id"));
        Recruitinfo recruitinfo = recruitInfoService.selectById(id);
        model.addAttribute("recruitinfos", recruitinfo);
        return "recruitinfos";
    }


    @RequestMapping("/newsinfo")
    public String getNewsInfo(Model model, HttpServletRequest request) {
        LoginUtil.isLogin(request, model);
        Integer id = Integer.parseInt(request.getParameter("id"));
        News newsinfo = newsService.selectByPrimaryKey(id);
        model.addAttribute("newsinfo", newsinfo);
        return "newsinfo";
    }

    @RequestMapping("/register")
    public String registerUserInfo() {
        return "regist";
    }

    @RequestMapping("/checklogin")
    @ResponseBody
    public String checkRegistUserInfo(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        Userinfo userinfo = userinfoService.getUserInfo(username, "");
        if (userinfo != null) {
            return "1";
        }
        userinfo = userinfoService.getUserInfo("", email);
        if (userinfo != null) {
            return "2";
        }
        return "ok";
    }
}
