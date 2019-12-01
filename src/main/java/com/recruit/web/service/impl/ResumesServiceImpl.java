package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.ResumesMapper;
import com.recruit.web.pojo.Recruitinfo;
import com.recruit.web.pojo.Resumes;
import com.recruit.web.pojo.Userinfo;
import com.recruit.web.service.IRecruitInfoService;
import com.recruit.web.service.IResumesService;
import com.recruit.web.service.IUserinfoService;
import com.recruit.web.util.CookieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者：qiwj
 * 时间：2019/11/30
 */
@Service
public class ResumesServiceImpl implements IResumesService {
    @Autowired
    private ResumesMapper resumesMapper;
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IResumesService resumesService;

    @Autowired
    private IRecruitInfoService recruitInfoService;
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
       Integer result= resumesMapper.insertSelective(record);
        return result;
    }

    @Override
    public Resumes selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Resumes record) {
        return 0;
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
            return "redirect:/";
        }
        String recruitid = request.getParameter("recruitid");
        if(null!=recruitid&&!"".equals(recruitid))
        {
            Recruitinfo recruitinfo = recruitInfoService.selectById(Integer.parseInt(recruitid));
            model.addAttribute("recruitinfo",recruitinfo);
        }
        Resumes resumes = resumesService.selctResumeByUserId(userid);
        model.addAttribute("resumes", resumes);

        Userinfo userinfo = userinfoService.selectByPrimaryKey(Integer.parseInt(userid));
        if (userinfo != null) {
            model.addAttribute("userinfo", userinfo);
        }
        return "personindex";
    }
}
