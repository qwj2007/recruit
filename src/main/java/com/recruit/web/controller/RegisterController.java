package com.recruit.web.controller;

import com.recruit.web.pojo.Resumes;
import com.recruit.web.pojo.Userinfo;
import com.recruit.web.service.IResumesService;
import com.recruit.web.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：qiwj
 * 时间：2019/11/30
 */
@Controller
@RequestMapping("Register")
public class RegisterController {
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IResumesService resumesService;

    @RequestMapping("/RegisterUserInfo")
    @ResponseBody
    public String Register(HttpServletRequest request) {
        Userinfo user = new Userinfo();
        Date date = new Date();
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        String email = request.getParameter("email");
        user.setUsername(username);
        user.setPwd(pwd);
        user.setEmail(email);
        user.setIsactive(true);
        user.setCreatetime(date);
        user.setIslock(false);
        Integer userid = userinfoService.insertSelective(user);

        if (user.getId() > 0) {
            Resumes resumes = new Resumes();
            String truename = request.getParameter("truename");
            String sex = request.getParameter("sex");
            String birthday = request.getParameter("birthday");
            String age = request.getParameter("age");
            String nation = request.getParameter("nation");
            String nativeplace = request.getParameter("nativeplace");
            String politicaloutlook = request.getParameter("politicaloutlook");
            String registeredresidence = request.getParameter("registeredresidence");
            String maritalstatus = request.getParameter("maritalstatus");
            String workdate = request.getParameter("workdate");
            String locationpersonnelrelationship = request.getParameter("locationpersonnelrelationship");
            String card = request.getParameter("card");
            String education = request.getParameter("education");
            String graduateschool = request.getParameter("graduateschool");
            String major = request.getParameter("major");
            String academicdegree = request.getParameter("academicdegree");
            String phone = request.getParameter("phone");
            String comanyname = request.getParameter("comanyname");
            String postname = request.getParameter("postname");
            String nowaddress = request.getParameter("nowaddress");
            String isfreshstudent = request.getParameter("isfreshstudent");
            resumes.setTruename(truename);
            resumes.setUserid(user.getId());
            resumes.setAcademicdegree(academicdegree);
            resumes.setAge(Integer.parseInt(age));
            resumes.setBirthday(birthday);
            resumes.setCard(card);
            resumes.setComanyname(comanyname);
            resumes.setEducation(education);
            resumes.setGraduateschool(graduateschool);
            resumes.setIsfreshstudent(Boolean.parseBoolean(isfreshstudent));
            resumes.setLocationpersonnelrelationship(locationpersonnelrelationship);
            resumes.setMajor(major);
            resumes.setMaritalstatus(maritalstatus);
            resumes.setNation(nation);
            resumes.setNativeplace(nativeplace);
            resumes.setNowaddress(nowaddress);
            resumes.setPhone(phone);
            resumes.setPoliticaloutlook(politicaloutlook);
            resumes.setPostname(postname);
            resumes.setRegisteredresidence(registeredresidence);
            resumes.setSex(sex);
            resumes.setWorkdate(workdate);
          int result=  resumesService.insertSelective(resumes);
          if(result==0)
          {
              return "注册失败!";
          }
        }
        return "ok";
    }
}