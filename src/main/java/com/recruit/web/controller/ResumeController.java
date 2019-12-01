package com.recruit.web.controller;

import com.recruit.web.pojo.Delivery;
import com.recruit.web.pojo.Hrnotice;
import com.recruit.web.pojo.Resumes;
import com.recruit.web.service.IDeliveryService;
import com.recruit.web.service.IHrnoticeService;
import com.recruit.web.service.IResumesService;
import com.recruit.web.util.CookieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        if(coun<=0)
        {
            return "-1";
        }
        return "ok";
    }
}
