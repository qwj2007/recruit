package com.recruit.web.service.impl;

import com.recruit.web.common.LoginUtil;
import com.recruit.web.mapper.recruit.ResumesMapper;
import com.recruit.web.pojo.*;
import com.recruit.web.service.*;
import com.recruit.web.util.CookieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 作者：qiwj
 * 时间：2019/11/30
 */
@Service
public class ResumesServiceImpl implements IResumesService {
    private String duihao = "duihao.png";
    private String cuohao = "Illus02.png";
    @Autowired
    private ResumesMapper resumesMapper;
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IResumesService resumesService;

    @Autowired
    private IRecruitInfoService recruitInfoService;
    @Autowired
    private IWorkExperienceService workExperienceService;
    @Autowired
    private IEdutionExpericenceService edutionExpericenceService;
    @Autowired
    private ITrainService trainService;
    @Autowired
    private ICertificateService certificateService;
    @Autowired
    private IFamilyService familyService;
    @Autowired
    private IOtherInfosService otherInfosService;
    @Autowired
    private IDeliveryService deliveryService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Resumes record) {
        Integer result = resumesMapper.insert(record);
        return result;
    }

    @Override
    public int insertSelective(Resumes record) {
        Integer result = resumesMapper.insertSelective(record);
        return result;
    }

    @Override
    public Resumes selectByPrimaryKey(Integer id) {
        return resumesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Resumes record) {
        return resumesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Resumes record) {
        return 0;
    }

    @Override
    public Resumes selctResumeByUserId(String userid) {
        return resumesMapper.selctResumeByUserId(userid);
    }

    @Override
    public String PersoncenterCheck(HttpServletRequest request, Model model) {
        String userid = CookieManager.getInstance().getCookie(request, "userid");
        if (userid == null) {
            return "0";
        }
        String recruitid = request.getParameter("recruitid");
        if (null != recruitid && !"".equals(recruitid)) {
            Recruitinfo recruitinfo = recruitInfoService.selectById(Integer.parseInt(recruitid));
            model.addAttribute("recruitinfo", recruitinfo);
        }
        Userinfo userinfo = userinfoService.selectByPrimaryKey(Integer.parseInt(userid));
        if (userinfo != null) {
            model.addAttribute("userinfo", userinfo);
        }
        LoginUtil.isLogin(request,model);
        Resumes resumes = resumesService.selctResumeByUserId(userid);
        ResumePicture resumePicture = new ResumePicture();
        resumePicture.setPic_train(cuohao);
        resumePicture.setPic_education(cuohao);
        resumePicture.setPic_work(cuohao);
        resumePicture.setPic_personalInfo(cuohao);
        resumePicture.setPic_certificate(cuohao);
        resumePicture.setPic_family(cuohao);
        resumePicture.setPic_ohter(cuohao);
        resumePicture.setPic_photo(cuohao);
        if (resumes != null) {
            model.addAttribute("resumes", resumes);
            resumePicture.setPic_personalInfo(duihao);

            Integer resumesid = resumes.getId();
            Delivery delivery = deliveryService.selectDeliveryByResumesId(resumesid);
            if (delivery != null) {
                Recruitinfo recruitinfo = recruitInfoService.selectById(delivery.getRecruitinfoid());
                model.addAttribute("recruitinfo", recruitinfo);
            }
            List<Workexperience> workexperiences = workExperienceService.selectWorkExperienceById(resumes.getId());
            if (workexperiences != null && workexperiences.size() > 0) {
                resumePicture.setPic_work(duihao);
            }
            List<Educationexperience> educationexperiences = edutionExpericenceService.selectEducationByResumeid(resumes.getId());
            if (educationexperiences != null && educationexperiences.size() > 0) {
                resumePicture.setPic_education(duihao);
            }

            List<Trainingexperience> trainingexperiences = trainService.selecTrainByResumeId(resumes.getId());
            if (trainingexperiences != null && trainingexperiences.size() > 0) {
                resumePicture.setPic_train(duihao);
            }
            List<Certificate> certificates = certificateService.selectCertificateByResumeId(resumes.getId());
            if (certificates != null && certificates.size() > 0) {
                resumePicture.setPic_certificate(duihao);
            }
            List<Family> families = familyService.selectFamilyByResumeId(resumes.getId());
            if (families != null && families.size() > 0) {
                resumePicture.setPic_family(duihao);
            }
            if (resumes.getPhoto() != null && !"".equals(resumes.getPhoto())) {
                resumePicture.setPic_photo(duihao);
            }
            List<Otherinfos> otherinfos = otherInfosService.selectOtherinfosById(resumes.getId());
            if (otherinfos != null && otherinfos.size() > 0) {
                resumePicture.setPic_ohter(duihao);
            }
        } else {
            resumes = new Resumes();
            model.addAttribute("resumes", resumes);
        }
        model.addAttribute("resumePicture", resumePicture);

        return "1";
    }

    @Override
    public List<Resumes> selectResumeAll(Map<String,Object> map) {
        return resumesMapper.selectResumeAll(map);
    }
}
