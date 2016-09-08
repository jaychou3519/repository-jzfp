package com.demo.jzfp.entity;

import java.util.Hashtable;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class TsysUserinfo implements KvmSerializable {
	

	private java.lang.String userpost;

	/**
	 * 用户ID db_column: USER_ID
	 */
	private java.lang.String userId;

	/**
	 * 登录账号 db_column: LOGIN_NAME
	 */
	private java.lang.String loginName;

	/**
	 * 登录密码 db_column: PASSWORD
	 */
	private java.lang.String password;

	/**
	 * 姓名 db_column: USER_REALNAME
	 */
	private java.lang.String userRealname;

	/**
	 * userNo db_column: USER_NO
	 */
	private java.lang.String userNo;

	/**
	 * 所属机构 db_column: ORG_ID
	 */
	private java.lang.String orgId;

	/**
	 * deptId db_column: DEPT_ID
	 */
	private java.lang.String deptId;

	/**
	 * 性别 db_column: SEX
	 */
	private java.lang.String sex;

	/**
	 * 手机 db_column: MOBILETEL
	 */
	private java.lang.String mobiletel;

	/**
	 * 传真 db_column: FAX
	 */
	private java.lang.String fax;

	/**
	 * 电子邮箱 db_column: EMAIL
	 */
	private java.lang.String email;

	/**
	 * 邮编 db_column: ZIP
	 */
	private java.lang.String zip;

	/**
	 * 状态1:启用 0停用、2、调岗 db_column: STATE
	 */
	private java.lang.String state;

	/**
	 * 登陆次数 db_column: USER_LOGINCOUNT
	 */
	private java.lang.Long userLogincount;

	/**
	 * 用户类型 db_column: USER_TYPE
	 */
	private java.lang.String userType;

	/**
	 * 开通时间 db_column: DREDGE_TIME
	 */

	private java.lang.String dredgeTime;

	/**
	 * 排序号 db_column: SORT_NUM
	 */
	private java.lang.Long sortNum;

	/**
	 * 创建人 db_column: CREATE_MAN
	 */
	private java.lang.String createMan;

	/**
	 * 最后登录IP db_column: LOGIN_IP
	 */
	private java.lang.String loginIp;

	/**
	 * 最后登录时间 db_column: LOGIN_DATE
	 */
	private java.lang.String loginDate;
	// 用户头像
	private String userimg;

	public String getUserimg() {
		return userimg;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}

	/**
	 * 创建时间 db_column: CREATE_DATE
	 */
	private java.lang.String createDate;

	private String postName;
	private String postId;


	// columns END
	public TsysUserinfo() {
	}

	public TsysUserinfo(java.lang.String userId) {
		this.userId = userId;
	}

	/*
	 * 用户岗位
	 */
	public java.lang.String getUserpost() {
		return userpost;
	}

	public void setUserpost(java.lang.String userpost) {
		this.userpost = userpost;
	}

	public void setUserId(java.lang.String value) {
		this.userId = value;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setLoginName(java.lang.String value) {
		this.loginName = value;
	}

	public java.lang.String getLoginName() {
		return this.loginName;
	}

	public void setPassword(java.lang.String value) {
		this.password = value;
	}

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setUserRealname(java.lang.String value) {
		this.userRealname = value;
	}

	public java.lang.String getUserRealname() {
		return this.userRealname;
	}

	public void setUserNo(java.lang.String value) {
		this.userNo = value;
	}

	public java.lang.String getUserNo() {
		return this.userNo;
	}

	public void setOrgId(java.lang.String value) {
		this.orgId = value;
	}

	public java.lang.String getOrgId() {
		return this.orgId;
	}

	public void setDeptId(java.lang.String value) {
		this.deptId = value;
	}

	public java.lang.String getDeptId() {
		return this.deptId;
	}

	public void setSex(java.lang.String value) {
		this.sex = value;
	}

	public java.lang.String getSex() {
		return this.sex;
	}

	public void setMobiletel(java.lang.String value) {
		this.mobiletel = value;
	}

	public java.lang.String getMobiletel() {
		return this.mobiletel;
	}

	public void setFax(java.lang.String value) {
		this.fax = value;
	}

	public java.lang.String getFax() {
		return this.fax;
	}

	public void setEmail(java.lang.String value) {
		this.email = value;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setZip(java.lang.String value) {
		this.zip = value;
	}

	public java.lang.String getZip() {
		return this.zip;
	}

	public void setState(java.lang.String value) {
		this.state = value;
	}

	public java.lang.String getState() {
		return this.state;
	}

	public void setUserLogincount(java.lang.Long value) {
		this.userLogincount = value;
	}

	public java.lang.Long getUserLogincount() {
		return this.userLogincount;
	}

	public void setUserType(java.lang.String value) {
		this.userType = value;
	}

	public java.lang.String getUserType() {
		return this.userType;
	}

	public void setDredgeTime(java.lang.String value) {
		this.dredgeTime = value;
	}

	public java.lang.String getDredgeTime() {
		return this.dredgeTime;
	}

	public void setSortNum(java.lang.Long value) {
		this.sortNum = value;
	}

	public java.lang.Long getSortNum() {
		return this.sortNum;
	}

	public void setCreateMan(java.lang.String value) {
		this.createMan = value;
	}

	public java.lang.String getCreateMan() {
		return this.createMan;
	}

	public void setLoginIp(java.lang.String value) {
		this.loginIp = value;
	}

	public java.lang.String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginDate(java.lang.String value) {
		this.loginDate = value;
	}

	public java.lang.String getLoginDate() {
		return this.loginDate;
	}

	public void setCreateDate(java.lang.String value) {
		this.createDate = value;
	}

	public java.lang.String getCreateDate() {
		return this.createDate;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	@Override
	public Object getProperty(int arg0) {
		return null;
	}

	@Override
	public int getPropertyCount() {
		return 0;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		
	}
}
