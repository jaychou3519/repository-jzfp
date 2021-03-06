package com.demo.jzfp.entity;

public class TdataFamily {
	private java.lang.String memberId;
	private String familyName;//成员名字
	private String yhzgx;//与户主关系
	private String sex;//成员性别
	private String birthday;//成员出生日期
	private String jkzk;//成员健康
	private String workDesc;//其它
	private String Card;//成员身份证号
	
	public java.lang.String getMemberId() {
		return memberId;
	}
	public void setMemberId(java.lang.String memberId) {
		this.memberId = memberId;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getYhzgx() {
		return yhzgx;
	}
	public void setYhzgx(String yhzgx) {
		this.yhzgx = yhzgx;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCard() {
		return Card;
	}
	public void setCard(String Card) {
		this.Card = Card;
	}	
	/*public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}*/
	public String getJkzk() {
		return jkzk;
	}
	public void setJkzk(String jkzk) {
		this.jkzk = jkzk;
	}
	public String getWorkDesc() {
		return workDesc;
	}
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}
	@Override
	public String toString() {
		return "TdataFamily [familyName=" + familyName + ", yhzgx=" + yhzgx + ", sex=" + sex + ", Card=" + Card
				+ ", jkzk=" + jkzk + ", workDesc=" + workDesc + "]";
	}
	
}
