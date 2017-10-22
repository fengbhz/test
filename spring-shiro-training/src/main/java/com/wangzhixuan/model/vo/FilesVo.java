package com.wangzhixuan.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangzhixuan.commons.utils.JsonUtils;
import com.wangzhixuan.model.Role;
import com.wangzhixuan.model.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description：UserVo
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class FilesVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String name;
	private String filesize;
	private Date creattime;
	private String url;
	private String visitorNum;
	private String path;
	private String account;
	private String uid;
	private String type;
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

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public Date getCreattime() {
		return creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVisitorNum() {
		return visitorNum;
	}

	public void setVisitorNum(String visitorNum) {
		this.visitorNum = visitorNum;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}