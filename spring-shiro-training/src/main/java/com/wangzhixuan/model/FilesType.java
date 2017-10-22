package com.wangzhixuan.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/22 0022.
 */
public class FilesType implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;  // 主键
    private String name;  //名称
    private String code; // 代码
    private Date creattime; // 创建时间
    private String ms;  // 描述
    private String dmlb;//  代码类别  --为以后扩展进行使用
    private String isdeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getDmlb() {
        return dmlb;
    }

    public void setDmlb(String dmlb) {
        this.dmlb = dmlb;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }
}
