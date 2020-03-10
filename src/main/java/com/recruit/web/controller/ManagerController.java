package com.recruit.web.controller;


import com.alibaba.fastjson.JSON;
import com.recruit.web.common.LoginUtil;
import com.recruit.web.pojo.*;
import com.recruit.web.service.*;
import com.recruit.web.service.impl.DeliveryStatusServiceImpl;
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
    @Autowired
    private IResumesService resumesService;
    @Autowired
    private IDeliveryStatusService deliveryStatusService;

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
    public String deleteNews(HttpServletRequest request) {
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


    @RequestMapping("loadresumeList")
    @ResponseBody
    public String getResumeList() {
        List<Resumes> list = resumesService.selectResumeAll();
        TableDataModel tableDataModel = new TableDataModel();
        tableDataModel.setCount(list.size());
        tableDataModel.setMsg("操作成功");
        tableDataModel.setCode("0");
        tableDataModel.setData(list);
        String jsons = JSON.toJSONString(tableDataModel);
        System.out.println(jsons);
        // String str = "{\"code\":\"0\",\"count\":11,\"data\":[{\"academicdegree\":\"名誉博士\",\"age\":19,\"applyworkname\":\"软件开发\",\"education\":\"研究生或以上\",\"id\":7,\"locationpersonnelrelationship\":\"山东淄博\",\"nation\":\"初婚\",\"registeredresidence\":\"dsfds\",\"sex\":\"男\",\"truename\":\"dfdsf\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":7,\"locationpersonnelrelationship\":\"山东淄博\",\"nation\":\"初婚\",\"registeredresidence\":\"dsfds\",\"sex\":\"男\",\"truename\":\"dfdsf\"},{\"academicdegree\":\"管理学博士学位\",\"age\":19,\"applyworkname\":\"软件开发\",\"education\":\"大学本科\",\"id\":9,\"locationpersonnelrelationship\":\"打发erere\",\"nation\":\"汉族\",\"registeredresidence\":\"是的发顺丰\",\"sex\":\"男\",\"truename\":\"张三\"},{\"academicdegree\":\"管理学博士学位\",\"age\":19,\"applyworkname\":\"销售顾问\",\"education\":\"大学本科\",\"id\":9,\"locationpersonnelrelationship\":\"打发erere\",\"nation\":\"汉族\",\"registeredresidence\":\"是的发顺丰\",\"sex\":\"男\",\"truename\":\"张三\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":1,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":2,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":3,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":4,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":5,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道1\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":6,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道22\"},{\"academicdegree\":\"名誉博士\",\"age\":31,\"education\":\"研究生或以上\",\"id\":10,\"locationpersonnelrelationship\":\"第三方第三方\",\"nation\":\"汉族\",\"registeredresidence\":\"第三方第三方水电费第三方\",\"sex\":\"男\",\"truename\":\"小王\"}],\"msg\":\"操作成功\"}\n";
        return jsons;
    }

    @RequestMapping("resumeList")
    public String resumeList() {
        return "manager/resumeList";
    }

    @RequestMapping("passornopass")
    @ResponseBody
    public String passornopass(HttpServletRequest request) {
        String infos = request.getParameter("infos");
        String type = request.getParameter("status");
        String jsons = "";
        Integer status = 0;
        switch (type) {
            case "1":
                status=0;
                jsons = "你的简历没有通过初选";
                break;
            case "0":
                status=1;
                jsons = "祝贺你，你的简历通过初选";
                break;
        }
        jsons = deliveryStatusService.passornopass(infos, jsons, status,request);
        return jsons;
    }
}


