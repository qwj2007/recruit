package com.recruit.web.controller;

import com.recruit.web.pojo.*;
import com.recruit.web.service.*;
import com.recruit.web.util.CookieManager;
import com.recruit.web.util.FileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.IntPredicate;

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

    @RequestMapping("/addwork")
    @ResponseBody
    public String addWork(HttpServletRequest request, Model model) throws ParseException {
        String resumeid = request.getParameter("resumesid");
        String id = request.getParameter("id");
        String begintime = request.getParameter("kssj");
        String endtime = request.getParameter("jssj");
        String postname = request.getParameter("zw");
        String job = request.getParameter("gzdw");
        String workcontent = request.getParameter("gznr");
        String userid = CookieManager.getInstance().getCookie(request, "userid");
        Workexperience workexperience = new Workexperience();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        workexperience.setBegintime(begintime);
        workexperience.setCratetime(new Date());
        workexperience.setCreateuserid(Integer.parseInt(userid));
        workexperience.setEndtime(endtime);
        workexperience.setIsactive(true);
        workexperience.setJob(job);
        workexperience.setOrders(1);
        workexperience.setPostname(postname);
        workexperience.setResumesid(Integer.parseInt(resumeid));
        workexperience.setUpdatetime(new Date());
        workexperience.setUpdateuserid(Integer.parseInt(userid));
        workexperience.setUpdatetime(new Date());
        workexperience.setWorkcontent(workcontent);

        int result = 0;
        if (id != null && id != "" && Integer.parseInt(id) > 0) {
            workexperience.setId(Integer.parseInt(id));
            result = workExperienceService.updateByPrimaryKeySelective(workexperience);
        } else {
            result = workExperienceService.insertSelective(workexperience);
        }
        model.addAttribute("id", workexperience.getId());

        if (result > 0) {
            return workexperience.getId().toString();
        }
        return "0";
    }

    @RequestMapping("/deleteWork")
    public String deleteWork(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        Workexperience workexperience = workExperienceService.selectByPrimaryKey(Integer.parseInt(id));
        if (workexperience != null) {
            workexperience.setIsactive(false);
        }
        Integer result = workExperienceService.updateByPrimaryKeySelective(workexperience);
        String url = "redirect:/resume/editworkexperience?resumesid=" + workexperience.getResumesid();
        return url;
    }

    /*
    * 工作经历
    * */
    @RequestMapping("/editworkexperience")
    public String editworkexperience(HttpServletRequest request, Model model) {
        resumesService.PersoncenterCheck(request, model);
        String resumeid = request.getParameter("resumesid");
        model.addAttribute("resumesid", resumeid);
        String id = request.getParameter("id");
        model.addAttribute("id", id);
        if (resumeid != null && !"".equals(resumeid)) {
            List<Workexperience> workexperiences = workExperienceService.selectWorkExperienceById(Integer.parseInt(resumeid));
            model.addAttribute("workexperiences", workexperiences != null ? workexperiences : new Workexperience());
        }
        if (id != null && !"".equals(id) && Integer.parseInt(id) > 0) {
            Workexperience workexperience = workExperienceService.selectByPrimaryKey(Integer.parseInt(id));
            model.addAttribute("workModel", workexperience);

        } else {
            model.addAttribute("workModel", new Workexperience());
        }

        return "editworkexperience";
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
            Integer i = resumesService.updateByPrimaryKeySelective(resumes);
            Userinfo userinfo = userinfoService.selectByPrimaryKey(resumes.getUserid());
            resumes.setEmail(dzyx);
            userinfoService.updateByPrimaryKey(userinfo);
            return "ok";
        } catch (Exception e) {
            return "no";
        }

    }

    @RequestMapping("/addEducation")
    @ResponseBody
    public String addEducation(HttpServletRequest request, Model model) {

        Educationexperience educationexperience = new Educationexperience();
        String resumeid = request.getParameter("resumesid");
        String id = request.getParameter("id");
        String begintime = request.getParameter("kssj");
        String endtime = request.getParameter("jssj");
        String majorname = request.getParameter("zy");
        String schoolname = request.getParameter("xxmc");
        String education = request.getParameter("xlid");
        String academicdegree = request.getParameter("xwid");
        String learningform = request.getParameter("xxxs");
        String userid = CookieManager.getInstance().getCookie(request, "userid");


        educationexperience.setBegintime(begintime);
        educationexperience.setCratetime(new Date());
        educationexperience.setCreateuserid(Integer.parseInt(userid));
        educationexperience.setEndtime(endtime);
        educationexperience.setIsactive(true);
        educationexperience.setAcademicdegree(academicdegree);
        educationexperience.setEducation(education);
        educationexperience.setLearningform(learningform);
        educationexperience.setMajorname(majorname);
        educationexperience.setOrders(1);
        educationexperience.setResumesid(Integer.parseInt(resumeid));
        educationexperience.setUpdateuserid(Integer.parseInt(userid));
        educationexperience.setSchoolname(schoolname);
        educationexperience.setUpdatetime(new Date());


        int result = 0;
        if (id != null && id != "" && Integer.parseInt(id) > 0) {
            educationexperience.setId(Integer.parseInt(id));
            result = edutionExpericenceService.updateByPrimaryKeySelective(educationexperience);
        } else {
            result = edutionExpericenceService.insertSelective(educationexperience);
        }
        model.addAttribute("id", educationexperience.getId());

        if (result > 0) {
            return educationexperience.getId().toString();
        }
        return "0";
    }

    /*
    *教育经历
    * */
    @RequestMapping("/editeducation")
    public String editeducation(HttpServletRequest request, Model model) {
        resumesService.PersoncenterCheck(request, model);
        String resumeid = request.getParameter("resumesid");
        model.addAttribute("resumesid", resumeid);
        String id = request.getParameter("id");
        model.addAttribute("id", id);
        if (resumeid != null && !"".equals(resumeid)) {
            List<Educationexperience> educationexperiences = edutionExpericenceService.selectEducationByResumeid(Integer.parseInt(resumeid));
            model.addAttribute("educations", educationexperiences);
        }
        if (id != null && !"".equals(id) && Integer.parseInt(id) > 0) {
            Educationexperience educationexperience = edutionExpericenceService.selectByPrimaryKey(Integer.parseInt(id));
            model.addAttribute("educationModel", educationexperience);

        } else {
            model.addAttribute("educationModel", new Educationexperience());
        }
        return "editeducation";
    }

    @RequestMapping("/deleteEdu")

    public String deleteEdu(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        Educationexperience educationexperience = edutionExpericenceService.selectByPrimaryKey(Integer.parseInt(id));
        if (educationexperience != null) {
            educationexperience.setIsactive(false);
        }
        Integer result = edutionExpericenceService.updateByPrimaryKeySelective(educationexperience);
        String url = "redirect:/resume/editeducation?resumesid=" + educationexperience.getResumesid();
        return url;
    }

    /*
    * 培训经历
    * */
    @RequestMapping("/editetrain")
    public String editTrain(HttpServletRequest request, Model model) {
        resumesService.PersoncenterCheck(request, model);
        String resumeid = request.getParameter("resumesid");
        model.addAttribute("resumesid", resumeid);
        String id = request.getParameter("id");
        model.addAttribute("id", id);
        if (resumeid != null && !"".equals(resumeid)) {
            List<Trainingexperience> trainingexperiences = trainService.selecTrainByResumeId(Integer.parseInt(resumeid));
            model.addAttribute("trains", trainingexperiences);
        }
        if (id != null && !"".equals(id) && Integer.parseInt(id) > 0) {
            Trainingexperience trainingexperience = trainService.selectByPrimaryKey(Integer.parseInt(id));
            model.addAttribute("trainModel", trainingexperience);

        } else {
            model.addAttribute("trainModel", new Trainingexperience());
        }
        return "edittrain";
    }

    @RequestMapping("/deleteTrain")
    public String deleteTrain(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        Trainingexperience Trainingexperience = trainService.selectByPrimaryKey(Integer.parseInt(id));
        if (Trainingexperience != null) {
            Trainingexperience.setIsactive(false);
        }
        Integer result = trainService.updateByPrimaryKeySelective(Trainingexperience);
        String url = "redirect:/resume/editTrain?resumesid=" + Trainingexperience.getResumesid();
        return url;
    }

    @RequestMapping("/addTrain")
    @ResponseBody
    public String addTrain(HttpServletRequest request, Model model) {
        Trainingexperience trainingexperience = new Trainingexperience();
        String resumeid = request.getParameter("resumesid");
        String id = request.getParameter("id");
        String begintime = request.getParameter("pxkssj");
        String endtime = request.getParameter("pxjssj");
        String traincompany = request.getParameter("pxdw");
        String traincontent = request.getParameter("pxnr");
        String userid = CookieManager.getInstance().getCookie(request, "userid");
        trainingexperience.setBegintime(begintime);
        trainingexperience.setCratetime(new Date());
        trainingexperience.setCreateuserid(Integer.parseInt(userid));
        trainingexperience.setEndtime(endtime);
        trainingexperience.setIsactive(true);
        trainingexperience.setOrders(1);
        trainingexperience.setResumesid(Integer.parseInt(resumeid));
        trainingexperience.setUpdateuserid(Integer.parseInt(userid));
        trainingexperience.setTraincomany(traincompany);
        trainingexperience.setTraincontent(traincontent);

        trainingexperience.setUpdatetime(new Date());
        int result = 0;
        if (id != null && id != "" && Integer.parseInt(id) > 0) {
            trainingexperience.setId(Integer.parseInt(id));
            result = trainService.updateByPrimaryKeySelective(trainingexperience);
        } else {
            result = trainService.insertSelective(trainingexperience);
        }
        model.addAttribute("id", trainingexperience.getId());

        if (result > 0) {
            return trainingexperience.getId().toString();
        }
        return "0";
    }


    /*
    * 证书
    * */
    @RequestMapping("/editcetificate")
    public String editcetificate(HttpServletRequest request, Model model) {
        resumesService.PersoncenterCheck(request, model);
        String resumeid = request.getParameter("resumesid");
        model.addAttribute("resumesid", resumeid);
        String id = request.getParameter("id");
        model.addAttribute("id", id);
        if (resumeid != null && !"".equals(resumeid)) {
            List<Certificate> certificates = certificateService.selectCertificateByResumeId(Integer.parseInt(resumeid));
            model.addAttribute("certificates", certificates);
        }
        if (id != null && !"".equals(id) && Integer.parseInt(id) > 0) {
            Certificate certificate = certificateService.selectByPrimaryKey(Integer.parseInt(id));
            model.addAttribute("cetificateModel", certificate);

        } else {
            model.addAttribute("cetificateModel", new Certificate());
        }
        return "editcetificate";
    }

    @RequestMapping("/deleteceti")
    public String deleteCetificate(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        Certificate certificate = certificateService.selectByPrimaryKey(Integer.parseInt(id));
        if (certificate != null) {
            certificate.setIsactive(false);
        }
        Integer result = certificateService.updateByPrimaryKeySelective(certificate);
        String url = "redirect:/resume/editTrain?resumesid=" + certificate.getResumesid();
        return url;
    }

    @RequestMapping("/addcetificate")
    @ResponseBody
    public String addCetificate(HttpServletRequest request, Model model) {

        Certificate certificate = new Certificate();
        String resumeid = request.getParameter("resumesid");
        String id = request.getParameter("id");
        String ZSMC = request.getParameter("ZSMC");
        String rq = request.getParameter("rq");

        String userid = CookieManager.getInstance().getCookie(request, "userid");
        certificate.setCertificatedate(rq);
        certificate.setCratetime(new Date());
        certificate.setCreateuserid(Integer.parseInt(userid));
        certificate.setCertificatename(ZSMC);
        certificate.setIsactive(true);
        certificate.setOrders(1);
        certificate.setResumesid(Integer.parseInt(resumeid));
        certificate.setUpdateuserid(Integer.parseInt(userid));
        certificate.setUpdatetime(new Date());
        int result = 0;
        if (id != null && id != "" && Integer.parseInt(id) > 0) {
            certificate.setId(Integer.parseInt(id));
            result = certificateService.updateByPrimaryKeySelective(certificate);
        } else {
            result = certificateService.insertSelective(certificate);
        }
        model.addAttribute("id", certificate.getId());

        if (result > 0) {
            return certificate.getId().toString();
        }
        return "0";
    }


    /*
    * 家庭成员
    * */
    @RequestMapping("/editfamily")
    public String editFamily(HttpServletRequest request, Model model) {

        resumesService.PersoncenterCheck(request, model);
        String resumeid = request.getParameter("resumesid");
        model.addAttribute("resumesid", resumeid);
        String id = request.getParameter("id");
        model.addAttribute("id", id);
        if (resumeid != null && !"".equals(resumeid)) {
            List<Family> families = familyService.selectFamilyByResumeId(Integer.parseInt(resumeid));
            model.addAttribute("families", families);
        }
        if (id != null && !"".equals(id) && Integer.parseInt(id) > 0) {
            Family family = familyService.selectByPrimaryKey(Integer.parseInt(id));
            model.addAttribute("familyModel", family);

        } else {
            model.addAttribute("familyModel", new Family());
        }
        return "editfamily";
    }

    @RequestMapping("/delfamily")
    public String delfamily(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        Family family = familyService.selectByPrimaryKey(Integer.parseInt(id));
        if (family != null) {
            family.setIsactive(false);
        }
        Integer result = familyService.updateByPrimaryKeySelective(family);
        String url = "redirect:/resume/editfamily?resumesid=" + family.getResumesid();
        return url;
    }

    @RequestMapping("/addfamily")
    @ResponseBody
    public String addFamily(HttpServletRequest request, Model model) {

        Family family = new Family();
        String resumeid = request.getParameter("resumesid");
        String id = request.getParameter("id");

        String username = request.getParameter("XM");
        String ZZMM = request.getParameter("ZZMMID");
        String companyname = request.getParameter("GZDW");
        String jobname = request.getParameter("ZW");
        String cw = request.getParameter("CW");
        String birthday = request.getParameter("CSNY");
        String userid = CookieManager.getInstance().getCookie(request, "userid");
        family.setAppellation(cw);
        family.setCratetime(new Date());
        family.setCreateuserid(Integer.parseInt(userid));
        family.setUsername(username);
        family.setIsactive(true);
        family.setBirthday(birthday);
        family.setPoliticaloutlook(ZZMM);
        family.setCompanyname(companyname);
        family.setJob(jobname);
        family.setResumesid(Integer.parseInt(resumeid));
        family.setUpdateuserid(Integer.parseInt(userid));
        family.setUpdatetime(new Date());
        int result = 0;
        if (id != null && id != "" && Integer.parseInt(id) > 0) {
            family.setId(Integer.parseInt(id));
            result = familyService.updateByPrimaryKeySelective(family);
        } else {
            result = familyService.insertSelective(family);
        }
        model.addAttribute("id", family.getId());

        if (result > 0) {
            return family.getId().toString();
        }
        return "0";
    }

    /*
    * 其他内容
    * */
    @RequestMapping("/addothers")
    @ResponseBody
    public String addotherinfos(HttpServletRequest request, Model model) {
        Otherinfos otherinfos = new Otherinfos();
        String resumeid = request.getParameter("resumesid");
        String id = request.getParameter("id");
        String contents = request.getParameter("zwpj");
        String userid = CookieManager.getInstance().getCookie(request, "userid");
        otherinfos.setContents(contents);
        otherinfos.setCreatetime(new Date());
        otherinfos.setCreateuserid(Integer.parseInt(userid));
        otherinfos.setIsactive(true);
        otherinfos.setResumesid(Integer.parseInt(resumeid));
        otherinfos.setUpdateuserid(Integer.parseInt(userid));
        otherinfos.setUpdatetime(new Date());
        int result = 0;
        List<Otherinfos> otherinfosList = otherInfosService.selectOtherinfosById(Integer.parseInt(resumeid));
        if (otherinfosList != null && otherinfosList.size() > 0) {
            otherinfos.setId(otherinfosList.get(0).getId());
            result = otherInfosService.updateByPrimaryKeySelective(otherinfos);
        } else {
            result = otherInfosService.insertSelective(otherinfos);
        }
        model.addAttribute("id", otherinfos.getId());

        if (result > 0) {
            return otherinfos.getId().toString();
        }
        return "0";
    }

    @RequestMapping("/editothers")
    public String editotherifos(HttpServletRequest request, Model model) {
        resumesService.PersoncenterCheck(request, model);
        String resumeid = request.getParameter("resumesid");
        model.addAttribute("resumesid", resumeid);
        String id = request.getParameter("id");
        model.addAttribute("id", id);
        if (resumeid != null && !"".equals(resumeid)) {
            List<Otherinfos> otherinfos = otherInfosService.selectOtherinfosById(Integer.parseInt(resumeid));
            if (otherinfos != null && otherinfos.size() > 0) {
                model.addAttribute("otherinfoModel", otherinfos.get(0));
            } else {
                model.addAttribute("otherinfoModel", new Otherinfos());
            }

        }
        /*
        if (id != null && !"".equals(id) && Integer.parseInt(id) > 0) {
            Otherinfos otherinfos = otherInfosService.selectByPrimaryKey(Integer.parseInt(id));
            model.addAttribute("otherinfoModel", otherinfos);

        } else {
            model.addAttribute("otherinfoModel", new Otherinfos());
        }*/
        return "othercontents";
    }

    @RequestMapping("/uploadpicture")
    public String uploadpicture(HttpServletRequest request, Model model) {
        String defaultImageUrl = "../images/14600639898.jpg";
        resumesService.PersoncenterCheck(request, model);
        String resumeid = request.getParameter("resumesid");
        model.addAttribute("resumesid", resumeid);
        String id = request.getParameter("id");
        model.addAttribute("id", id);
        Resumes resumes = resumesService.selectByPrimaryKey(Integer.parseInt(resumeid));

        if (resumes != null) {
            if (resumes.getPhoto().trim().equals("") || resumes.getPhoto().equals(null)) {
                resumes.setPhoto(defaultImageUrl);
            }
            model.addAttribute("resumes", resumes);
        } else {
            resumes = new Resumes();
            resumes.setPhoto(defaultImageUrl);
            model.addAttribute("resumes", resumes);
        }
        return "uploadpicture";
    }


    /*
    * 上传照片
    * */
    @RequestMapping("/uploadimage")
    public String uploadimage(HttpServletRequest request, Model model,
                              @RequestParam("file") MultipartFile file) throws Exception {
        String id = request.getParameter("resumesid");
        Resumes resumes = resumesService.selectByPrimaryKey(Integer.parseInt(id));
        // 拿到文件名
        String filename = file.getOriginalFilename();
        // 存放上传图片的文件夹
        File fileDir = FileUpload.getImgDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        System.out.println(fileDir.getAbsolutePath());
        try {
            if (!file.isEmpty()) {
                // 构建真实的文件路径
                String realFilePath = fileDir.getAbsolutePath() + File.separator + filename;
                File newFile = new File(realFilePath);
                System.out.println(newFile.getAbsolutePath());
                // 上传图片到 -》 “绝对路径”
                file.transferTo(newFile);
                resumes.setPhoto("../images/upload/" + filename);
                resumesService.updateByPrimaryKeySelective(resumes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/resume/resumemanger?resumesid=" + id;
    }

    @RequestMapping("/resumemanger")
    public String resumeManager(HttpServletRequest request, Model model) {
        resumesService.PersoncenterCheck(request, model);
        String resumeid = request.getParameter("resumesid");
        model.addAttribute("resumesid", resumeid);
        String id = request.getParameter("id");
        model.addAttribute("id", id);

        Resumes resumes = resumesService.selectByPrimaryKey(Integer.parseInt(resumeid));
        model.addAttribute("resumes", resumes);
        List<Workexperience> workexperiences = workExperienceService.selectWorkExperienceById(Integer.parseInt(resumeid));
        model.addAttribute("workexperiences", workexperiences);
        List<Educationexperience> educationexperiences = edutionExpericenceService.selectEducationByResumeid(Integer.parseInt(resumeid));
        model.addAttribute("educationexperiences", educationexperiences);
        List<Trainingexperience> trainingexperiences = trainService.selecTrainByResumeId(Integer.parseInt(resumeid));
        model.addAttribute("trainingexperiences", trainingexperiences);
        List<Certificate> certificates = certificateService.selectCertificateByResumeId(Integer.parseInt(resumeid));
        model.addAttribute("certificates", certificates);
        List<Family> families = familyService.selectFamilyByResumeId(Integer.parseInt(resumeid));
        model.addAttribute("families", families);
        List<Otherinfos> otherinfos = otherInfosService.selectOtherinfosById(Integer.parseInt(resumeid));
        if(otherinfos!=null&&otherinfos.size()>0){
            model.addAttribute("otherinfos", otherinfos.get(0));
        }

        return "resumemanager";
    }
}
