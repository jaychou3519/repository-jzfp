package com.demo.jzfp.entity;

import java.util.List;

public class PoorMessage {
	//////////基本信息
	private String name;//户主姓名
	private String poorState;//贫困状态
	private String sex;//性别
	private String age;//年龄
	private String identity;//身份证
	private String tel;//联系电话
	private String education;//文化程度
	private String health;//健康状态
	
	///////////家庭经济
	private String construction;//住房结构
	private String houseArea;//住房面积
	private String landArea;//耕地面积
	private String hillArea;//山林面积
	private String dominate;//年人均可支配金额
	
	//////////致贫原因
	private String poorReason;//致贫原因
	private String reasonIllustrate;//致贫说明
	
	/////////帮扶措施
	private String measure;//帮扶措施
	private String measureDetail;//措施详情
	private String money;//增收获益
	private String realCase;//实际情况
	
	///////////帮扶成效
	private String date;//计划脱贫时间
	private String income;//人均收入
	private String allIncome;//总收入
	private String houseSafe;//住房安全
	private String medical;//医疗
	private String compulsoryEducation;//义务教育
	private String condition;//是否符合脱贫条件
	private String organization;//责任单位
	private String fzr;//负责人
	private String fzrTel;//负责人电话
	private String bffzr;//帮扶负责人
	
	//////////家庭成员
	private List<Member> members;
	
	
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getMeasureDetail() {
		return measureDetail;
	}
	public void setMeasureDetail(String measureDetail) {
		this.measureDetail = measureDetail;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getRealCase() {
		return realCase;
	}
	public void setRealCase(String realCase) {
		this.realCase = realCase;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getAllIncome() {
		return allIncome;
	}
	public void setAllIncome(String allIncome) {
		this.allIncome = allIncome;
	}
	public String getHouseSafe() {
		return houseSafe;
	}
	public void setHouseSafe(String houseSafe) {
		this.houseSafe = houseSafe;
	}
	public String getMedical() {
		return medical;
	}
	public void setMedical(String medical) {
		this.medical = medical;
	}
	public String getCompulsoryEducation() {
		return compulsoryEducation;
	}
	public void setCompulsoryEducation(String compulsoryEducation) {
		this.compulsoryEducation = compulsoryEducation;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getFzr() {
		return fzr;
	}
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	public String getFzrTel() {
		return fzrTel;
	}
	public void setFzrTel(String fzrTel) {
		this.fzrTel = fzrTel;
	}
	public String getBffzr() {
		return bffzr;
	}
	public void setBffzr(String bffzr) {
		this.bffzr = bffzr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getConstruction() {
		return construction;
	}
	public void setConstruction(String construction) {
		this.construction = construction;
	}
	public String getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}
	public String getLandArea() {
		return landArea;
	}
	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}
	public String getHillArea() {
		return hillArea;
	}
	public void setHillArea(String hillArea) {
		this.hillArea = hillArea;
	}
	public String getDominate() {
		return dominate;
	}
	public void setDominate(String dominate) {
		this.dominate = dominate;
	}
	public String getPoorReason() {
		return poorReason;
	}
	public void setPoorReason(String poorReason) {
		this.poorReason = poorReason;
	}
	public String getReasonIllustrate() {
		return reasonIllustrate;
	}
	public void setReasonIllustrate(String reasonIllustrate) {
		this.reasonIllustrate = reasonIllustrate;
	}
	
	
}
