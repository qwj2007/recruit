package com.recruit.web.pojo;



/**
 * 作者：qiwj
 * 时间：2020/3/8
 */
public class TableDataModel {
    private  String code="0";//layui默认必须是0才能显示
    private String msg="操作成功";
    private  Integer count;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
