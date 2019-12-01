package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.HrnoticeMapper;
import com.recruit.web.pojo.Hrnotice;
import com.recruit.web.service.IHrnoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/11/1
 */
@Service
public class HrnoticeServiceImpl implements IHrnoticeService {
    @Autowired
    private HrnoticeMapper hrnoticeMapper;
    @Override
    public List<Hrnotice> GetHrnoticeByUserId(String userid) {
        return hrnoticeMapper.selectNoticeCountByUserId(userid);
    }
}
