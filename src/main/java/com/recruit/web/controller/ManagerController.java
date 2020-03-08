package com.recruit.web.controller;


import com.alibaba.fastjson.JSON;
import com.recruit.web.common.LoginUtil;
import com.recruit.web.pojo.*;
import com.recruit.web.service.IMenuService;
import com.recruit.web.service.IRecruitInfoService;
import com.recruit.web.util.ClientIp;
import com.recruit.web.util.CookieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    public String editRecruit()
    {
        return "manager/recruitedit";
    }
    @RequestMapping("loadRecruitData")
    @ResponseBody
    public String loadRecruitList()
    {
        List<Recruitinfo> list = recruitInfoService.selectAllRecruitInfos();
        TableDataModel tableDataModel=new TableDataModel();
        tableDataModel.setCount(list.size());
        tableDataModel.setMsg("操作成功");
        tableDataModel.setData(list);
        String jsons=JSON.toJSONString(tableDataModel);
        System.out.println(jsons);
        return  jsons;
    }

    @RequestMapping("newsinfo")
    public String getNewsInfo() {
        return "manager/newsinfo";
    }
}


