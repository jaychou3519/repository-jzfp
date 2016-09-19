package com.demo.jzfp.entity;

import java.util.List;

public class TdataCountryman {
	//////////基本信息
	private String countrymanId;
	private String countryman;//户主姓名
	private String countryId;//机构ID
	private String poorState;//贫困状态
	private String sex;//性别
	private String age;//年龄
	private String card;//身份证
	private String telphone;//联系电话
	private String whcd;//文化程度
	private String jkzk;//健康状态


	private java.lang.String poorCard;//贫困户属性
	
	///////////家庭经济
	private String zfjg;//住房结构
	private String zzArea;//住房面积
	private String gdArea;//耕地面积
	private String slArea;//山林面积
	private String rjsrqk;//年人均可支配金额
	
	//////////致贫原因
	private String poorReason;//致贫原因
	private String remark;//致贫说明
	
	
	//////////家庭成员
	private List<TdataFamily> tdataFamilys;//家庭成员
	private List<TdataAction> tdataActions;//帮扶措施
	
	private TdataHelper tdataHelper;
	private TdataResult tdataResult;
	private String pkhimg;
	

	public String getCountrymanId() {
		return countrymanId;
	}


	public void setCountrymanId(String countrymanId) {
		this.countrymanId = countrymanId;
	}


	public String getJkzk() {
		return jkzk;
	}


	public void setJkzk(String jkzk) {
		this.jkzk = jkzk;
	}


	public String getCountryman() {
		return countryman;
	}


	public void setCountryman(String countryman) {
		this.countryman = countryman;
	}


	public String getPoorState() {
		return poorState;
	}


	public void setPoorState(String poorState) {
		this.poorState = poorState;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getCard() {
		return card;
	}


	public void setCard(String card) {
		this.card = card;
	}


	public String getTelphone() {
		return telphone;
	}


	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}


	public String getWhcd() {
		return whcd;
	}


	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}


	/*public String getHealth() {
		return health;
	}


	public void setHealth(String health) {
		this.health = health;
	}*/


	public String getZfjg() {
		return zfjg;
	}


	public void setZfjg(String zfjg) {
		this.zfjg = zfjg;
	}


	public String getZzArea() {
		return zzArea;
	}


	public void setZzArea(String zzArea) {
		this.zzArea = zzArea;
	}


	public String getGdArea() {
		return gdArea;
	}


	public void setGdArea(String gdArea) {
		this.gdArea = gdArea;
	}


	public String getSlArea() {
		return slArea;
	}


	public void setSlArea(String slArea) {
		this.slArea = slArea;
	}


	public String getRjsrqk() {
		return rjsrqk;
	}


	public void setRjsrqk(String rjsrqk) {
		this.rjsrqk = rjsrqk;
	}


	public String getPoorReason() {
		return poorReason;
	}


	public void setPoorReason(String poorReason) {
		this.poorReason = poorReason;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public List<TdataFamily> getTdataFamilys() {
		return tdataFamilys;
	}


	public void setTdataFamilys(List<TdataFamily> tdataFamilys) {
		this.tdataFamilys = tdataFamilys;
	}

	public List<TdataAction> getTdataActions() {
		return tdataActions;
	}


	public void setTdataActions(List<TdataAction> tdataActions) {
		this.tdataActions = tdataActions;
	}


	public TdataHelper getTdataHelper() {
		return tdataHelper;
	}


	public void setTdataHelper(TdataHelper tdataHelper) {
		this.tdataHelper = tdataHelper;
	}


	public TdataResult getTdataResult() {
		return tdataResult;
	}


	public void setTdataResult(TdataResult tdataResult) {
		this.tdataResult = tdataResult;
	}


	public java.lang.String getPoorCard() {
		return poorCard;
	}


	public void setPoorCard(java.lang.String poorCard) {
		this.poorCard = poorCard;
	}


	public String getCountryId() {
		return countryId;
	}


	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}


	public String getPkhimg() {
		return pkhimg;
	}


	public void setPkhimg(String pkhimg) {
		this.pkhimg = pkhimg;
	}
	
}
