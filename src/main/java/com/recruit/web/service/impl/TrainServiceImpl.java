package com.recruit.web.service.impl;

import com.recruit.web.mapper.recruit.TrainingexperienceMapper;
import com.recruit.web.pojo.Trainingexperience;
import com.recruit.web.service.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：qiwj
 * 时间：2019/12/8
 */
@Service
public class TrainServiceImpl implements ITrainService {
    @Autowired
    private TrainingexperienceMapper trainService;
    @Override
    public List<Trainingexperience> selecTrainByResumeId(Integer id) {
        return trainService.selecTrainByResumeId(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return trainService.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Trainingexperience record) {
        return trainService.insert(record);
    }

    @Override
    public int insertSelective(Trainingexperience record) {
        return trainService.insertSelective(record);
    }

    @Override
    public Trainingexperience selectByPrimaryKey(Integer id) {
        return trainService.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Trainingexperience record) {
        return trainService.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Trainingexperience record) {
        return trainService.updateByPrimaryKey(record);
    }
}
