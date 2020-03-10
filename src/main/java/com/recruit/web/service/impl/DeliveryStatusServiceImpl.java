package com.recruit.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.recruit.web.mapper.recruit.DeliverystatusMapper;
import com.recruit.web.mapper.recruit.HrnoticeMapper;
import com.recruit.web.pojo.Delivery;
import com.recruit.web.pojo.Deliverystatus;
import com.recruit.web.pojo.Hrnotice;
import com.recruit.web.pojo.ResultMsg;
import com.recruit.web.service.IDeliveryService;
import com.recruit.web.service.IDeliveryStatusService;
import com.recruit.web.util.CookieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作者：qiwj
 * 时间：2020/3/10
 */
@Service
public class DeliveryStatusServiceImpl implements IDeliveryStatusService {
    @Autowired
    private HrnoticeMapper hrnoticeMapper;
    @Autowired
    private DeliverystatusMapper deliverystatusMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Deliverystatus record) {
        return 0;
    }

    @Override
    public int insertSelective(Deliverystatus record) {
        return 0;
    }

    @Override
    public Deliverystatus selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Deliverystatus record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Deliverystatus record) {
        return 0;
    }

    @Override
    public String passornopass(String infos, String contents,Integer status, HttpServletRequest request) {
        ResultMsg msg = new ResultMsg();
        try {
            //data[i].id+"|"+data[i].recruitinfoid+"|"+data[i].delveryid
            String[] arrays = infos.split(",");
            List<Deliverystatus> listStatus = new ArrayList<>();
            List<Hrnotice> HrList = new ArrayList<>();
            for (String array : arrays) {
                String[] ass = array.split("-");
                System.out.println(ass);
                Deliverystatus dstatus = new Deliverystatus();
                dstatus.setCreatetime(new Date());
                dstatus.setResumeid(Integer.parseInt(ass[0]));
                dstatus.setRecruitinfoid(Integer.parseInt(ass[1]));
                dstatus.setDelveryid(Integer.parseInt(ass[2]));
                dstatus.setStatus(status);
                listStatus.add(dstatus);

                Hrnotice hrnotice = new Hrnotice();
                hrnotice.setAttach("");
                hrnotice.setContents(contents);
                hrnotice.setCreatetimes(new Date());
                String employeeid = CookieManager.getInstance().getCookie(request, "employeeid");
                hrnotice.setCreateuserid(employeeid);
                hrnotice.setIsactive(true);
                hrnotice.setPublishtime(new Date());
                hrnotice.setUpdatetime(new Date());
                hrnotice.setTitle("投递简历通知");
                hrnotice.setUpdateuserid(employeeid);
                Integer userid = Integer.parseInt(ass[3]);
                hrnotice.setUserid(userid);
                HrList.add(hrnotice);
            }

            for (Deliverystatus ds : listStatus) {
                deliverystatusMapper.insertSelective(ds);
            }
            for (Hrnotice hr : HrList) {
                hrnoticeMapper.insertSelective(hr);
            }
            msg.setMsg("操作成功");
            msg.setResultCode("1");
        } catch (Exception ex) {
            msg.setMsg("操作失败");
            msg.setResultCode("0");
            System.out.println(ex);
        }
        return JSON.toJSONString(msg);
    }
}
