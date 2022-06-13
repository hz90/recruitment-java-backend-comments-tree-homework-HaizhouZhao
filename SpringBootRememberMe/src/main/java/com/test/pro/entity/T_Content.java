package com.test.pro.entity;

import java.util.List;

/**
 * 留言记录
 * 
 * @author hai
 *
 */
public class T_Content {
	private Integer id;
	private Integer fid;
	private Integer userid;
	private String username;
	private String CreateDate;
	private String content;
	List<T_Content> lst;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<T_Content> getLst() {
		return lst;
	}

	public void setLst(List<T_Content> lst) {
		this.lst = lst;
	}

}
