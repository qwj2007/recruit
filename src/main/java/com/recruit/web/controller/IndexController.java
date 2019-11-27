package com.recruit.web.controller;


import com.recruit.web.pojo.News;
import com.recruit.web.pojo.Recruitinfo;
import com.recruit.web.pojo.Teacher;
import com.recruit.web.service.INewsService;
import com.recruit.web.service.IRecruitInfoService;
import com.recruit.web.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/")

    public String GetInfos(Model model) {
        List<Recruitinfo> list = recruitInfoService.selectRecruitInfos();
        List<News> newslist=newsService.selectNews();

        //if(list!=null) {
            model.addAttribute("recruitinfos", list);
            model.addAttribute("news",newslist);
        //}
        return "index";
    }

    @RequestMapping("index/getu")
    @ResponseBody
    public String GetName() {
        Teacher teacher=teacherService.selectById();
        return teacher.toString();
    }


}
