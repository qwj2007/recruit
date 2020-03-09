package com.recruit.web.controller;


import com.alibaba.fastjson.JSON;
import com.recruit.web.common.LoginUtil;
import com.recruit.web.pojo.*;
import com.recruit.web.service.IMenuService;
import com.recruit.web.service.INewsService;
import com.recruit.web.service.IRecruitInfoService;
import com.recruit.web.util.ClientIp;
import com.recruit.web.util.CookieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 作者：qiwj
 * 时间：2020/3/7
 */
@Controller
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRecruitInfoService recruitInfoService;
    @Autowired
    private INewsService newsService;

    @RequestMapping("/index")
    public String Index(HttpServletRequest request, Model model) {
        String userid = CookieManager.getInstance().getCookie(request, "employeeid");
        String username = CookieManager.getInstance().getCookie(request, "employeename");
        model.addAttribute("employeeid", userid);
        model.addAttribute("employeename", username);
        return "/manager/index";
    }

    @RequestMapping("/main")
    public String main(HttpServletRequest request, Model model) {

        loginParam param = new loginParam();
        param.setLoginIp(ClientIp.getIp2(request));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        param.setLoginTime(df.format(new Date()));
        param.setLoginCount(1);
        model.addAttribute("loginParam", param);
        return "/manager/main";
    }

    @RequestMapping("getMenu")
    @ResponseBody
    public String getMenu() {
        String jsons = menuService.getMenuJson();
        System.out.println(jsons);
        return jsons;
    }

    @RequestMapping("recruitlist")
    public String recruitlist(Model model) {

        return "manager/recruitlist";
    }

    @RequestMapping("edit")
    public String editRecruitPage(HttpServletRequest request, Model model) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Recruitinfo recruitinfo = recruitInfoService.selectById(id);
        if (recruitinfo == null) {
            recruitinfo = new Recruitinfo();
        }
        model.addAttribute("recruitinfo", recruitinfo);
        return "manager/recruitedit";
    }

    @RequestMapping("editrecruit")
    @ResponseBody
    public String editRecruitInfo(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        String begintime = request.getParameter("begintime");
        String endtime = request.getParameter("endtime");
        Recruitinfo recruitinfo = new Recruitinfo();
        recruitinfo.setBegintime(begintime);
        recruitinfo.setContents(contents);
        recruitinfo.setEndtime(endtime);
        recruitinfo.setIsactive(true);
        recruitinfo.setTitle(title);
        recruitinfo.setId(id);
        Integer count = 0;
        ResultMsg msg = new ResultMsg();
        if (id > 0) {
            count = recruitInfoService.updateByPrimaryKeySelective(recruitinfo);
        } else {
            count = recruitInfoService.insertSelective(recruitinfo);
        }
        if (count > 0) {
            msg.setMsg("操作成功");
            msg.setResultCode("1");

        } else {
            msg.setMsg("操作失败");
            msg.setResultCode("0");
        }
        String jsons = JSON.toJSONString(msg);
        return jsons;
    }

    @ResponseBody
    @RequestMapping("deleteRecurit")
    public String deleteRecurit(HttpServletRequest request) {

        String ids = request.getParameter("id");
        List<Integer> idss = new ArrayList<>();
        for (String idd : ids.split(",")) {
            idss.add(Integer.parseInt(idd));
        }
        /*
        Recruitinfo model = recruitInfoService.selectById(id);
        model.setIsactive(false);
        Integer i = recruitInfoService.updateByPrimaryKeySelective(model);*/
        Integer i = recruitInfoService.updateBatch(idss);
        ResultMsg msg = new ResultMsg();
        if (i > 0) {
            msg.setMsg("操作成功");
            msg.setResultCode("1");
        } else {
            msg.setMsg("操作失败");
            msg.setResultCode("0");
        }
        return JSON.toJSONString(msg);
    }

    @ResponseBody
    @RequestMapping("deleteNews")
    public String deleteNews(HttpServletRequest request){
        String ids = request.getParameter("id");
        List<Integer> idss = new ArrayList<>();
        for (String idd : ids.split(",")) {
            idss.add(Integer.parseInt(idd));
        }
        Integer i = newsService.updateBatch(idss);
        ResultMsg msg = new ResultMsg();
        if (i > 0) {
            msg.setMsg("操作成功");
            msg.setResultCode("1");
        } else {
            msg.setMsg("操作失败");
            msg.setResultCode("0");
        }
        return JSON.toJSONString(msg);
    }

    @RequestMapping("loadRecruitData")
    @ResponseBody
    public String loadRecruitList() {
        List<Recruitinfo> list = recruitInfoService.selectAllRecruitInfos();
        TableDataModel tableDataModel = new TableDataModel();
        tableDataModel.setCount(list.size());
        tableDataModel.setMsg("操作成功");
        tableDataModel.setData(list);
        String jsons = JSON.toJSONString(tableDataModel);
        System.out.println(jsons);
        return jsons;
    }

    @RequestMapping("newsinfo")
    public String getNewsInfo() {
        return "manager/newsinfo";
    }

    @ResponseBody
    @RequestMapping("loadNewsInfos")
    public String loadNewsInfos() {
        List<News> list = newsService.selectNewInfos();
        TableDataModel tableDataModel = new TableDataModel();
        tableDataModel.setCount(list.size());
        tableDataModel.setMsg("操作成功");
        tableDataModel.setData(list);
        String jsons = JSON.toJSONString(tableDataModel);
        System.out.println(jsons);
        return jsons;
    }

    @RequestMapping("editnews")
    @ResponseBody
    public String editNewInfo(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        String author = CookieManager.getInstance().getCookie(request, "employeename");
        News news = new News();

        news.setAuthor(author);
        news.setContents(contents);
        news.setIsactive(true);
        news.setTitle(title);
        news.setId(id);
        Integer count = 0;
        ResultMsg msg = new ResultMsg();
        if (id > 0) {
            count = newsService.updateByPrimaryKeySelective(news);
        } else {
            news.setCreatetime(new Date());
            count = newsService.insertSelective(news);
        }
        if (count > 0) {
            msg.setMsg("操作成功");
            msg.setResultCode("1");

        } else {
            msg.setMsg("操作失败");
            msg.setResultCode("0");
        }
        String jsons = JSON.toJSONString(msg);
        return jsons;
    }

    @RequestMapping("eidtnewspage")
    public String editNewPage(HttpServletRequest request, Model model) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        News news = newsService.selectByPrimaryKey(id);
        if (news == null) {
            news = new News();
        }
        model.addAttribute("news", news);
        return "manager/newsedit";
    }
}


