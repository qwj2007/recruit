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
}
