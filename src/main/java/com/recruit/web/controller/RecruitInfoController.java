package com.recruit.web.controller;

import com.recruit.web.common.LoginUtil;
import com.recruit.web.pojo.Hrnotice;
import com.recruit.web.pojo.News;
import com.recruit.web.pojo.Recruitinfo;
import com.recruit.web.pojo.Userinfo;
import com.recruit.web.service.IHrnoticeService;
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
import java.util.ArrayList;
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
    @Autowired
    private IHrnoticeService hrnoticeService;

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
    public String registerUserInfo(Model model, HttpServletRequest request) {
        LoginUtil.isLogin(request, model);
        return "regist";
    }

    @RequestMapping("/checklogin")
    @ResponseBody
    public String checkRegistUserInfo(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        Userinfo userinfo = userinfoService.getUserInfo(username, null);
        if (userinfo != null) {
            return "1";
        }
        userinfo = userinfoService.getUserInfo(null, email);
        if (userinfo != null) {
            return "2";
        }
        return "ok";
    }

    @RequestMapping("/noticeinfo")
    public String noticeinfo(Model model, HttpServletRequest request) {
        Boolean isok = LoginUtil.isLogin(request, model);
        if (!isok) {
            return "redirect:/";
        }
        Integer userid = Integer.parseInt(request.getParameter("id"));
        List<Hrnotice> list = hrnoticeService.GetHrnoticeByUserId(userid.toString());
        model.addAttribute("noticelist", list == null ? new ArrayList<Hrnotice>() : list);
        return "noticeList";
    }

    @RequestMapping("/noticeDetail")
    public String noticeDetail(Model model, HttpServletRequest request) {
        Boolean isok = LoginUtil.isLogin(request, model);
        if (!isok) {
            return "redict:/";
        }
        Integer id = Integer.parseInt(request.getParameter("id"));
        Hrnotice hrnotice = hrnoticeService.selectByPrimaryKey(id);
        model.addAttribute("noticeinfo", hrnotice);
        return "noticedetail";
    }

}
