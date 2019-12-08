package com.recruit.web.controller;

import com.recruit.web.pojo.Delivery;
import com.recruit.web.pojo.Hrnotice;
import com.recruit.web.pojo.Resumes;
import com.recruit.web.pojo.Userinfo;
import com.recruit.web.service.IDeliveryService;
import com.recruit.web.service.IHrnoticeService;
import com.recruit.web.service.IResumesService;
import com.recruit.web.service.IUserinfoService;
import com.recruit.web.util.CookieManager;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/11/1
 */
@Controller
@RequestMapping("resume")
public class ResumeController {
    @Autowired
    private IHrnoticeService hrnoticeService;
    @Autowired
    private IResumesService resumesService;
    @Autowired
    private IDeliveryService deliveryService;
    @Autowired
    private IUserinfoService userinfoService;


    @RequestMapping("/getcount")
    @ResponseBody
    public Integer GetNoticeCount(HttpServletRequest request) {

        String userid = CookieManager.getInstance().getCookie(request, "userid");
        List<Hrnotice> list = hrnoticeService.GetHrnoticeByUserId(userid);
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @RequestMapping("/applyjob")
    @ResponseBody
    public String ApplyJob(HttpServletRequest request) {
        String recruitid = request.getParameter("recruitid");
        String userid = CookieManager.getInstance().getCookie(request, "userid");
        if (userid == null || "".equals(userid)) {
            return "-1";
        }
        Resumes resumes = resumesService.selctResumeByUserId(userid);
        if (resumes == null) {
            return "-1";
        }
        Delivery delivery = new Delivery();
        delivery.setCreatetime(new Date());
        delivery.setIsactive(true);
        delivery.setRecruitinfoid(Integer.parseInt(recruitid));
        delivery.setResumeid(resumes.getId());
        delivery.setRecruitinfoid(Integer.parseInt(recruitid));
        Integer coun = deliveryService.insertSelective(delivery);
        if (coun <= 0) {
            return "-1";
        }
        return "ok";
    }

    @RequestMapping("/editresume")
    public String editResume(HttpServletRequest request, Model model) {
        resumesService.PersoncenterCheck(request, model);

        return "editresume";
    }

    @RequestMapping("/updateresume")
    @ResponseBody
    @Transactional
    public String updateResume(HttpServletRequest request, Model model) {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Resumes resumes = resumesService.selectByPrimaryKey(id);
            String truename = request.getParameter("XM");
            String isfreshstudent = request.getParameter("SFYJS");
            String card = request.getParameter("SFZH");
            String age = request.getParameter("NL");
            String birthday = request.getParameter("CSRQ");
            String jg = request.getParameter("JG");
            String zzmm = request.getParameter("ZZMMID");
            String hhzk = request.getParameter("HYZKID");
            String ssgxszd = request.getParameter("RSGXSZD");
            String hkszd = request.getParameter("HKSZD");
            String phone = request.getParameter("LXDH");
            String xjzd = request.getParameter("XJZD");
            String dzyx = request.getParameter("DZYX");
            resumes.setTruename(truename);
            resumes.setIsfreshstudent(Boolean.parseBoolean(isfreshstudent));
            resumes.setCard(card);
            resumes.setAge(Integer.parseInt(age));
            resumes.setBirthday(birthday);
            resumes.setNativeplace(jg);
            resumes.setPoliticaloutlook(zzmm);
            resumes.setMaritalstatus(hhzk);
            resumes.setLocationpersonnelrelationship(ssgxszd);
            resumes.setRegisteredresidence(hkszd);
            resumes.setPhone(phone);
            resumes.setNowaddress(xjzd);
            resumes.setId(id);
           Integer i= resumesService.updateByPrimaryKeySelective(resumes);
            Userinfo userinfo = userinfoService.selectByPrimaryKey(resumes.getUserid());
            resumes.setEmail(dzyx);
            userinfoService.updateByPrimaryKey(userinfo);
            return "ok";
        } catch (Exception e) {
            return "no";
        }

    }
}
