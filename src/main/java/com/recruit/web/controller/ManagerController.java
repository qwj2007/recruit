package com.recruit.web.controller;


import com.alibaba.fastjson.JSON;

import com.recruit.web.pojo.*;
import com.recruit.web.service.*;

import com.recruit.web.util.ClientIp;
import com.recruit.web.util.CookieManager;
import com.recruit.web.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 作者：qiwj
 * 时间：2020/3/7
 */
@Controller
@RequestMapping(value = "manage")
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
    @Autowired
    private INavigationService navigationService;
    @Autowired
    private IBaseInfoService baseInfoService;
    @Autowired
    private IEmailsetService emailsetService;
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IBannerService bannerService;

    @RequestMapping(value = "userinfoList", produces = {"text/html;charset=UTF-8;", "application/json"})
    public String userinfoList() {
        return "manage/userinfolist";
    }

    @RequestMapping(value = "loadUserinfo", method = {RequestMethod.POST, RequestMethod.GET},
            produces = {"text/html;charset=UTF-8;", "application/json"})
    @ResponseBody
    public String loadUserInfo(HttpServletRequest request) {
        String page=request.getParameter("page");
        String pagesize=request.getParameter("limit");
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> mapcount=new HashMap<>();
        int start=(Integer.parseInt(page)-1)*Integer.parseInt(pagesize);
        map.put("start",start);
        map.put("pagesize",Integer.parseInt(pagesize));

        List<Userinfo> list = userinfoService.getUserAll(map);
        List<Userinfo> listcount = userinfoService.getUserAll(mapcount);
        TableDataModel tableDataModel = new TableDataModel();
        tableDataModel.setCount(listcount.size());
        tableDataModel.setMsg("操作成功");
        tableDataModel.setData(list);
        String jsons = JSON.toJSONString(tableDataModel);
        System.out.println(jsons);
        return jsons;
    }

    @ResponseBody
    @RequestMapping("deleteUserInfo")
    public String deleteUserInfo(HttpServletRequest request) {

        String ids = request.getParameter("id");
        List<Integer> idss = new ArrayList<>();
        for (String idd : ids.split(",")) {
            idss.add(Integer.parseInt(idd));
        }

        Integer i = userinfoService.deleteUser(idss);
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
    @RequestMapping("updateUserPwd")
    public String updateUserPwd(HttpServletRequest request) {

        String ids = request.getParameter("id");
        List<Integer> idss = new ArrayList<>();
        for (String idd : ids.split(",")) {
            idss.add(Integer.parseInt(idd));
        }
        Integer i = userinfoService.updatePwd(idss);
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

    @RequestMapping(value = "employeeList", method = {RequestMethod.GET})
    public String employeeList() {
        return "/manage/employeelist";
    }

    @RequestMapping(value = "loadEmployee", produces = {"text/html;charset=UTF-8;", "application/json"})
    @ResponseBody
    public String loadEmployee(HttpServletRequest request) {
        String page=request.getParameter("page");
        String pagesize=request.getParameter("limit");
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> mapcount=new HashMap<>();
        int start=(Integer.parseInt(page)-1)*Integer.parseInt(pagesize);
        map.put("start",start);
        map.put("pagesize",Integer.parseInt(pagesize));

        List<Employee> list = employeeService.getEmployeeAll(map);
        List<Employee> listcount = employeeService.getEmployeeAll(map);
        TableDataModel tableDataModel = new TableDataModel();
        tableDataModel.setCount(listcount.size());
        tableDataModel.setMsg("操作成功");
        tableDataModel.setData(list);
        String jsons = JSON.toJSONString(tableDataModel);
        System.out.println(jsons);
        return jsons;
    }

    @RequestMapping(value = "editEmployee")
    @ResponseBody
    public String editEmployee(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        String userid = request.getParameter("userid");
        String truename = request.getParameter("truename");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        String createuserid = CookieManager.getInstance().getCookie(request, "employeeid");
        Employee employee = new Employee();
        employee.setCreateuserid(createuserid);
        employee.setEmail(email);
        employee.setId(id);
        employee.setIsactive(true);
        employee.setSex(sex);
        employee.setTruename(truename);
        employee.setPassword("111111");
        employee.setUserid(userid);
        Integer count = 0;
        ResultMsg msg = new ResultMsg();
        if (id > 0) {
            employee.setUpdatetime(new Date());
            count = employeeService.updateByPrimaryKeySelective(employee);
        } else {
            employee.setCreatetime(new Date());
            count = employeeService.insertSelective(employee);
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

    @RequestMapping("eidtEmployeepage")
    public String eidtEmployeepage(HttpServletRequest request, Model model) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.selectByPrimaryKey(id);
        if (employee == null) {
            employee = new Employee();
        }
        model.addAttribute("employee", employee);
        return "manage/employeeedit";
    }

    @ResponseBody
    @RequestMapping("deleteEmployee")
    public String deleteEmployee(HttpServletRequest request) {

        String ids = request.getParameter("id");
        List<Integer> idss = new ArrayList<>();
        for (String idd : ids.split(",")) {
            idss.add(Integer.parseInt(idd));
        }

        Integer i = employeeService.updateBatch(idss);
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
    @RequestMapping("updatePwd")
    public String updatePwd(HttpServletRequest request) {

        String ids = request.getParameter("id");
        List<Integer> idss = new ArrayList<>();
        for (String idd : ids.split(",")) {
            idss.add(Integer.parseInt(idd));
        }

        Integer i = employeeService.updatePwd(idss);
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

    @RequestMapping(value = "/index", produces = {"text/html;charset=UTF-8;", "application/json"})
    public String Index(HttpServletRequest request, Model model) {
       // String userid = CookieManager.getInstance().getCookie(request, "employeeid");
       // String username = CookieManager.getInstance().getCookie(request, "employeename");
        String userid = CookieManager.getInstance().getCookie(request, "userid");
        String username = CookieManager.getInstance().getCookie(request, "username");
        model.addAttribute("employeeid", userid);
        model.addAttribute("employeename", username);
        return "/manage/index";
    }

    @RequestMapping(value = "/main", produces = {"text/html;charset=UTF-8;", "application/json"})
    public String main(HttpServletRequest request, Model model) {

        loginParam param = new loginParam();
        param.setLoginIp(ClientIp.getIp2(request));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        param.setLoginTime(df.format(new Date()));
        param.setLoginCount(1);
        model.addAttribute("loginParam", param);
        return "manage/main";
    }

    @RequestMapping(value = "getMenu", method = {RequestMethod.GET}, produces = {"text/html;charset=UTF-8;", "application/json"})
    @ResponseBody
    public String getMenu() {
        String jsons = menuService.getMenuJson();
        System.out.println(jsons);
        return jsons;
    }

    @RequestMapping(value = "recruitlist", produces = {"text/html;charset=UTF-8;", "application/json"})
    public String recruitlist(Model model) {

        return "manage/recruitlist";
    }

    @RequestMapping("edit")
    public String editRecruitPage(HttpServletRequest request, Model model) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Recruitinfo recruitinfo = recruitInfoService.selectById(id);
        if (recruitinfo == null) {
            recruitinfo = new Recruitinfo();
        }
        model.addAttribute("recruitinfo", recruitinfo);
        return "manage/recruitedit";
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

    @RequestMapping(value = "loadRecruitData", produces = {"text/html;charset=UTF-8;", "application/json"})
    @ResponseBody
    public String loadRecruitList(HttpServletRequest request) {
        String page=request.getParameter("page");
        String pagesize=request.getParameter("limit");
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> mapcount=new HashMap<>();
        int start=(Integer.parseInt(page)-1)*Integer.parseInt(pagesize);
        map.put("start",start);
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Recruitinfo> listcount = recruitInfoService.selectAllRecruitInfos(mapcount);
        List<Recruitinfo> list = recruitInfoService.selectAllRecruitInfos(map);
        TableDataModel tableDataModel = new TableDataModel();
        tableDataModel.setCount(listcount.size());
        tableDataModel.setMsg("操作成功");
        tableDataModel.setData(list);
        String jsons = JSON.toJSONString(tableDataModel);
        System.out.println(jsons);
        return jsons;
    }

    @RequestMapping(value = "newsinfo", produces = {"text/html;charset=UTF-8;", "application/json"})
    public String getNewsInfo() {
        return "manage/newsinfo";
    }

    @ResponseBody
    @RequestMapping(value = "loadNewsInfos", produces = {"text/html;charset=UTF-8;", "application/json"})
    public String loadNewsInfos(HttpServletRequest request) {
        String page=request.getParameter("page");
        String pagesize=request.getParameter("limit");
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> mapcount=new HashMap<>();
        int start=(Integer.parseInt(page)-1)*Integer.parseInt(pagesize);
        map.put("start",start);
        map.put("pagesize",Integer.parseInt(pagesize));

        List<News> list = newsService.selectNewInfos(map);
        List<News> listcount = newsService.selectNewInfos(mapcount);
        TableDataModel tableDataModel = new TableDataModel();
        tableDataModel.setCount(listcount.size());
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
        return "manage/newsedit";
    }


    @RequestMapping(value = "loadresumeList",method = {RequestMethod.POST}, produces = {"text/html;charset=UTF-8;", "application/json"})
    @ResponseBody
    public String getResumeList(HttpServletRequest request) {
        String page=request.getParameter("page");
        String pagesize=request.getParameter("limit");
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> mapcount=new HashMap<>();
        int start=(Integer.parseInt(page)-1)*Integer.parseInt(pagesize);
        map.put("start",start);
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Resumes> listAll=resumesService.selectResumeAll(mapcount);
        List<Resumes> list = resumesService.selectResumeAll(map);
        TableDataModel tableDataModel = new TableDataModel();
        tableDataModel.setCount(listAll.size());
        tableDataModel.setMsg("操作成功");
        tableDataModel.setCode("0");
        tableDataModel.setData(list);
        String jsons = JSON.toJSONString(tableDataModel);
        System.out.println(jsons);
        // String str = "{\"code\":\"0\",\"count\":11,\"data\":[{\"academicdegree\":\"名誉博士\",\"age\":19,\"applyworkname\":\"软件开发\",\"education\":\"研究生或以上\",\"id\":7,\"locationpersonnelrelationship\":\"山东淄博\",\"nation\":\"初婚\",\"registeredresidence\":\"dsfds\",\"sex\":\"男\",\"truename\":\"dfdsf\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":7,\"locationpersonnelrelationship\":\"山东淄博\",\"nation\":\"初婚\",\"registeredresidence\":\"dsfds\",\"sex\":\"男\",\"truename\":\"dfdsf\"},{\"academicdegree\":\"管理学博士学位\",\"age\":19,\"applyworkname\":\"软件开发\",\"education\":\"大学本科\",\"id\":9,\"locationpersonnelrelationship\":\"打发erere\",\"nation\":\"汉族\",\"registeredresidence\":\"是的发顺丰\",\"sex\":\"男\",\"truename\":\"张三\"},{\"academicdegree\":\"管理学博士学位\",\"age\":19,\"applyworkname\":\"销售顾问\",\"education\":\"大学本科\",\"id\":9,\"locationpersonnelrelationship\":\"打发erere\",\"nation\":\"汉族\",\"registeredresidence\":\"是的发顺丰\",\"sex\":\"男\",\"truename\":\"张三\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":1,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":2,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":3,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":4,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":5,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道1\"},{\"academicdegree\":\"名誉博士\",\"age\":19,\"education\":\"研究生或以上\",\"id\":6,\"locationpersonnelrelationship\":\"s'da'f'd'sa'fa's'd'f\",\"nation\":\"汉族\",\"registeredresidence\":\"打发斯蒂芬\",\"sex\":\"男\",\"truename\":\"大声道22\"},{\"academicdegree\":\"名誉博士\",\"age\":31,\"education\":\"研究生或以上\",\"id\":10,\"locationpersonnelrelationship\":\"第三方第三方\",\"nation\":\"汉族\",\"registeredresidence\":\"第三方第三方水电费第三方\",\"sex\":\"男\",\"truename\":\"小王\"}],\"msg\":\"操作成功\"}\n";
        return jsons;
    }

    @RequestMapping(value = "resumeList", method = {RequestMethod.GET}, produces = {"text/html;charset=UTF-8;", "application/json"})
    public String resumeList() {
        return "manage/resumelist";
    }

    @RequestMapping("passornopass")
    @ResponseBody
    public String passornopass(HttpServletRequest request) {
        String infos = request.getParameter("infos");
        String type = request.getParameter("status");
        String jsons = "";
        Integer status = 0;
        switch (type) {
            case "0":
                status = 0;
                jsons = "你的简历没有通过初选";
                break;
            case "1":
                status = 1;
                jsons = "祝贺你，你的简历通过初选";
                break;
        }
        jsons = deliveryStatusService.passornopass(infos, jsons, status, request);
        return jsons;
    }

    @RequestMapping(value = "navicationList", produces = {"text/html;charset=UTF-8;", "application/json"})
    public String navicationList() {
        String url = "/manage/navicationList";
        return url;
    }

    @RequestMapping(value = "loadNavication", produces = {"text/html;charset=UTF-8;", "application/json"})
    @ResponseBody
    public String loadNavication(HttpServletRequest request) {
        String page=request.getParameter("page");
        String pagesize=request.getParameter("limit");
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> mapcount=new HashMap<>();
        int start=(Integer.parseInt(page)-1)*Integer.parseInt(pagesize);
        map.put("start",start);
        map.put("pagesize",Integer.parseInt(pagesize));

        List<Navigation> list = navigationService.selectNavigationAll(map);
        List<Navigation> listcount = navigationService.selectNavigationAll(mapcount);
        TableDataModel tableDataModel = new TableDataModel();
        tableDataModel.setCount(listcount.size());
        tableDataModel.setMsg("操作成功");
        tableDataModel.setCode("0");
        tableDataModel.setData(list);
        String jsons = JSON.toJSONString(tableDataModel);
        System.out.println(jsons);
        return jsons;
    }

    @RequestMapping("navicationEdit")
    public String navicationEdit() {
        return "/manage/navicationedit";
    }

    @RequestMapping("editnnavication")
    @ResponseBody
    public String editnnavication(HttpServletRequest request) {
        Navigation navigation = new Navigation();
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        String dis = request.getParameter("isdisplay");
        Boolean isdisplay = false;
        if (dis.equals("1")) {
            isdisplay = true;
        }
        Integer orders = Integer.parseInt(request.getParameter("orders"));
        navigation.setName(name);
        navigation.setCreatetime(new Date());
        navigation.setIsactive(true);
        navigation.setIsdisplay(isdisplay);
        navigation.setOrders(orders);
        int i = navigationService.insertSelective(navigation);
        ResultMsg msg = new ResultMsg();
        if (i > 0) {
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
    @RequestMapping("deleteNavication")
    public String deleteNavication(HttpServletRequest request) {
        String ids = request.getParameter("id");
        Navigation navigation = navigationService.selectByPrimaryKey(Integer.parseInt(ids));
        ResultMsg msg = new ResultMsg();
        if (navigation != null) {
            navigation.setIsdisplay(false);
            navigation.setIsactive(false);
            Integer i = navigationService.updateByPrimaryKeySelective(navigation);
            if (i > 0) {
                msg.setMsg("操作成功");
                msg.setResultCode("1");
            } else {
                msg.setMsg("操作失败");
                msg.setResultCode("0");
            }
        }

        return JSON.toJSONString(msg);
    }

    @RequestMapping(value = "baseinfo", produces = {"text/html;charset=UTF-8;", "application/json"})
    public String getBaseInfo(HttpServletRequest request, Model model) {
        Baseinfo baseinfo = baseInfoService.selectBaseInfo();
        if (baseinfo == null) {
            baseinfo = new Baseinfo();
        }
        model.addAttribute("baseinfo", baseinfo);
        return "manage/baseinfo";
    }

    /**
     * 个人信息上传
     *
     * @return {Result}
     */
    @RequestMapping(value = "/uploadimage")
    @ResponseBody
    public String headImg(@RequestParam(value = "file", required = false) MultipartFile file,
                          HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result= FileUpload.headImg(file, request, response);
        return result;
    }

    @RequestMapping(value = "editbaseinfo", method = {RequestMethod.POST})
    @ResponseBody
    public String editbaseinfo(HttpServletRequest request) {
        Baseinfo baseinfo = new Baseinfo();
        Integer id = Integer.parseInt(request.getParameter("id"));
        String logourl = request.getParameter("logourl");
        String systemtitle = request.getParameter("systemtitle");
        String footinfo = request.getParameter("footinfo");
        baseinfo.setIsactive(true);
        baseinfo.setFootinfo(footinfo);
        baseinfo.setId(id);
        baseinfo.setLogourl(logourl);
        baseinfo.setSystemtitle(systemtitle);
        int count = 0;
        if (id > 0) {
            count = baseInfoService.updateByPrimaryKeySelective(baseinfo);
        } else {
            count = baseInfoService.insertSelective(baseinfo);
        }
        ResultMsg msg = new ResultMsg();
        if (count > 0) {
            msg.setMsg("操作成功");
            msg.setResultCode("1");
        } else {
            msg.setMsg("操作失败");
            msg.setResultCode("0");
        }
        return JSON.toJSONString(msg);
    }

    @RequestMapping("emailinfo")
    public String getEmail(HttpServletRequest request, Model model) {
        Emailset emailset = emailsetService.selectOneEmail();
        if (emailset == null) {
            emailset = new Emailset();
        }
        model.addAttribute("emailinfo", emailset);
        return "manage/email";
    }


    @RequestMapping(value = "editemail", method = {RequestMethod.POST})
    @ResponseBody
    public String editemail(HttpServletRequest request) {
        Emailset emailset = new Emailset();
        Integer id = Integer.parseInt(request.getParameter("id"));
        String emailaddress = request.getParameter("emailaddress");
        String emailpwd = request.getParameter("emailpwd");
        String sendservice = request.getParameter("sendservice");
        String contents = request.getParameter("contents");
        emailset.setContents(contents);
        emailset.setEmailaddress(emailaddress);
        emailset.setId(id);
        emailset.setSendservice(sendservice);
        emailset.setEmailpwd(emailpwd);
        int count = 0;
        if (id > 0) {
            count = emailsetService.updateByPrimaryKeySelective(emailset);
        } else {
            count = emailsetService.insertSelective(emailset);
        }
        ResultMsg msg = new ResultMsg();
        if (count > 0) {
            msg.setMsg("操作成功");
            msg.setResultCode("1");
        } else {
            msg.setMsg("操作失败");
            msg.setResultCode("0");
        }
        return JSON.toJSONString(msg);
    }

    @RequestMapping(value = "bannerlist", produces = {"text/html;charset=UTF-8;", "application/json"})
    public String bannerList() {
        return "manage/bannerlist";
    }

    @RequestMapping(value = "loadBanner", produces = {"text/html;charset=UTF-8;", "application/json"})
    @ResponseBody
    public String loadBanner(HttpServletRequest request) {

        String page=request.getParameter("page");
        String pagesize=request.getParameter("limit");
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> mapcount=new HashMap<>();
        int start=(Integer.parseInt(page)-1)*Integer.parseInt(pagesize);
        map.put("start",start);
        map.put("pagesize",Integer.parseInt(pagesize));

        List<Banner> list = bannerService.selectBanner(map);
        List<Banner> listCount = bannerService.selectBanner(mapcount);
        TableDataModel tableDataModel = new TableDataModel();
        tableDataModel.setCount(listCount.size());
        tableDataModel.setMsg("操作成功");
        tableDataModel.setCode("0");
        tableDataModel.setData(list);
        String jsons = JSON.toJSONString(tableDataModel);
        System.out.println(jsons);
        return jsons;
    }

    @RequestMapping("uploadbanner")
    public String uploadbanner() {
        return "manage/uploadbanner";
    }

    @RequestMapping(value = "saveBanner", method = {RequestMethod.POST}, produces = {"text/html;charset=UTF-8;", "application/json"})
    @Transactional
    @ResponseBody
    public String saveBanner(HttpServletRequest request) {
        /**
         * 你应该在这里做service层的调用
         * 你应该结果返回数据格式如定义返回格式：1代表成功，0代表失败
         * 总之你应该规范
         * 我这里直接返回1，代表成功。
         */
        ResultMsg msg = new ResultMsg();
        try {
            String urls = request.getParameter("imageurls");
            String[] urlss = urls.split(",");
            for (String linkurl : urlss) {
                Banner banner = new Banner();
                banner.setImageurl(linkurl);
                bannerService.insertSelective(banner);
                msg.setMsg("操作成功");
                msg.setResultCode("1");
            }
        } catch (Exception e) {
            msg.setMsg("操作异常");
            msg.setResultCode("11");
        }
        return JSON.toJSONString(msg);
    }

    @ResponseBody
    @RequestMapping("updateBanner")
    public String updateBanner(HttpServletRequest request) {
        ResultMsg msg = new ResultMsg();
        int id = Integer.parseInt(request.getParameter("id"));
        String flag = request.getParameter("flag");
        int result = bannerService.updateByPrimaryKeySelective(id, flag);
        if (result > 0) {
            msg.setMsg("操作成功");
            msg.setResultCode("1");
        } else {
            msg.setMsg("操作失败");
            msg.setResultCode("0");
        }
        return JSON.toJSONString(msg);
    }
}


