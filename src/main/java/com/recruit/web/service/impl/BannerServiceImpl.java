package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.BannerMapper;
import com.recruit.web.pojo.Banner;
import com.recruit.web.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作者：qiwj
 * 时间：2020/3/16
 */
@Service
public class BannerServiceImpl implements IBannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> selectBanner() {
        List<Banner> list = bannerMapper.selectBanner();
        if (list != null) return list;
        else return new ArrayList<>();
    }

    @Override
    public int insertSelective(Banner record) {
        return bannerMapper.insertSelective(record);
    }


    @Override
    public int updateByPrimaryKeySelective(int id, String isShow) {
        Banner banner = bannerMapper.selectByPrimaryKey(id);
        if (banner != null) {
            if (isShow == "") {
                banner.setIsactive(false);
            } else if (isShow.equals("1")) {
                banner.setIsdisplay(true);
            }
            else if(isShow.equals("0")){
                banner.setIsdisplay(false);
            }
        }
        return bannerMapper.updateByPrimaryKeySelective(banner);
    }
}
