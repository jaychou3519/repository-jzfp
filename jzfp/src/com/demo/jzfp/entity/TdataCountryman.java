package com.demo.jzfp.entity;

public class TdataCountryman {

	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "TdataCountryman";
	public static final String ALIAS_COUNTRYMAN_ID = "贫困户ID";
	public static final String ALIAS_INFO_YEAR = "信息年度";
	public static final String ALIAS_CARD = "户主身份证号码";
	public static final String ALIAS_COUNTRY_ID = "贫困村ID";
	public static final String ALIAS_ADDRESS_SZ = "家庭住址(市州)来源于机构ID";
	public static final String ALIAS_ADDRESS_X = "家庭住址(县)来源于机构ID";
	public static final String ALIAS_ADDRESS_XZ = "家庭住址(乡镇)来源于机构ID";
	public static final String ALIAS_ADDRESS_C = "家庭住址(村)来源于机构ID";
	public static final String ALIAS_COUNTRYZB = "组别";
	public static final String ALIAS_COUNTRYMAN = "户主姓名";
	public static final String ALIAS_POOR_STATE = "贫困状态:来源于字典定义";
	public static final String ALIAS_POOR_CARD = "贫困户属性:精准识别户、普通贫困户、低保户、五保户、农业大户";
	public static final String ALIAS_POOR_REASON = "主要致贫原因";
	public static final String ALIAS_JHTP_DATE = "计划脱贫时间";
	public static final String ALIAS_SEX = "户主性别";
	public static final String ALIAS_FAMILY_NO = "家庭户号";
	public static final String ALIAS_FAMILY_MEMERS = "家庭人数";
	public static final String ALIAS_TELPHONE = "联系电话";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_WHCD = "文化程度";
	public static final String ALIAS_AGE = "年龄";
	public static final String ALIAS_WATER_AREA = "水田面积";
	public static final String ALIAS_GD_AREA = "耕地面积";
	public static final String ALIAS_SL_AREA = "山林面积（亩）";
	public static final String ALIAS_ZFJG = "住房结构：来源于字典";
	public static final String ALIAS_ZZ_AREA = "住宅面积";
	public static final String ALIAS_RJSRQK = "家庭年人均可支配收入";
	
	private String beginTime;
	private String endTime;
	private String address;
	private String countryName;
	//private List<TdataFamily> listFamily;//家庭成员
	
//	public List<TdataFamily> getListFamily() {
//		return listFamily;
//	}
//
//	public void setListFamily(List<TdataFamily> listFamily) {
//		this.listFamily = listFamily;
//	}
	// 权限控制
	private String orgType;

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 贫困户ID db_column: COUNTRYMAN_ID
	 */
	private java.lang.String countrymanId;
	/**
	 * 信息年度 db_column: INFO_YEAR
	 */
	//@Excel(name = "贫困年度", code = "infoYear", orderNum = "1")
	private java.lang.String infoYear;
	/**
	 * 户主身份证号码 db_column: CARD
	 */
	private java.lang.String card;
	/**
	 * 贫困村ID db_column: COUNTRY_ID
	 */
	//@Excel(name = "贫困村", code = "countryId", orderNum = "3")
	private java.lang.String countryId;
	/**
	 * 家庭住址(市州)来源于机构ID db_column: ADDRESS_SZ
	 */
	private java.lang.String addressSz;
	/**
	 * 家庭住址(县)来源于机构ID db_column: ADDRESS_X
	 */
	private java.lang.String addressX;
	/**
	 * 家庭住址(乡镇)来源于机构ID db_column: ADDRESS_XZ
	 */
	private java.lang.String addressXz;
	/**
	 * 家庭住址(村)来源于机构ID db_column: ADDRESS_C
	 */
	private java.lang.String addressC;
	/**
	 * 组别 db_column: COUNTRYZB
	 */
	private java.lang.String countryzb;
	/**
	 * 户主姓名 db_column: COUNTRYMAN
	 */
	//@Excel(name = "户主姓名", code = "countryman", orderNum = "2")
	private java.lang.String countryman;
	/**
	 * 贫困状态:来源于字典定义 db_column: POOR_STATE
	 */
	//@Excel(name = "贫困状态", code = "poorState", orderNum = "8")
	private java.lang.String poorState;
	/**
	 * 贫困户属性:精准识别户、普通贫困户、低保户、五保户、农业大户 db_column: POOR_CARD
	 */
	//@Excel(name = "贫困户属性", code = "poorCard", orderNum = "10")
	private java.lang.String poorCard;
	/**
	 * 主要致贫原因 db_column: POOR_REASON
	 */
	//@Excel(name = "主要致贫原因", code = "poorReason", orderNum = "11")
	private java.lang.String poorReason;
	/**
	 * 计划脱贫时间 db_column: JHTP_DATE
	 */
	//@Excel(name = "计划脱贫时间", code = "jhtpDate", orderNum = "12")
	private java.lang.String jhtpDate;
	/**
	 * 户主性别 db_column: SEX
	 */
	//@Excel(name = "性别", code = "sex", orderNum = "13")
	private java.lang.String sex;
	/**
	 * 家庭户号 db_column: FAMILY_NO
	 */
	private java.lang.String familyNo;
	/**
	 * 家庭人数 db_column: FAMILY_MEMERS
	 */
	//@Excel(name = "家庭人数", code = "familyMemers", orderNum = "6")
	private java.lang.Integer familyMemers;
	/**
	 * 联系电话 db_column: TELPHONE
	 */
	//@Excel(name = "联系电话", code = "telphone", orderNum = "7")
	private java.lang.String telphone;
	/**
	 * 创建人 db_column: CREATOR
	 */
	private java.lang.String creator;
	/**
	 * 创建时间 db_column: CREATE_TIME
	 */
	//@Excel(name = "创建时间", code = "createTime", orderNum = "14")
	private java.lang.String createTime;
	/**
	 * 备注 db_column: REMARK
	 */
	private java.lang.String remark;
	/**
	 * 文化程度 db_column: WHCD
	 */
	//@Excel(name = "文化程度", code = "whcd", orderNum = "4")
	private java.lang.String whcd;
	/**
	 * 年龄 db_column: AGE
	 */
	//@Excel(name = "年龄", code = "age", orderNum = "5")
	private java.lang.Integer age;
	/**
	 * 水田面积 db_column: WATER_AREA
	 */
	private java.lang.String waterArea;
	/**
	 * 耕地面积 db_column: GD_AREA
	 */
	private java.lang.String gdArea;
	/**
	 * 山林面积（亩） db_column: SL_AREA
	 */
	private java.lang.String slArea;
	/**
	 * 住房结构：来源于字典 db_column: ZFJG
	 */
	private java.lang.String zfjg;
	/**
	 * 住宅面积 db_column: ZZ_AREA
	 */
	private java.lang.String zzArea;
	/**
	 * 家庭年人均可支配收入 db_column: RJSRQK
	 */
	//@Excel(name = "家庭年人均可支配收入", code = "rjsrqk", orderNum = "9")
	private java.lang.String rjsrqk;

	// columns END

	public TdataCountryman() {
	}

	public TdataCountryman(java.lang.String countrymanId) {
		this.countrymanId = countrymanId;
	}

	public void setCountrymanId(java.lang.String value) {
		this.countrymanId = value;
	}

	public java.lang.String getCountrymanId() {
		return this.countrymanId;
	}

	public void setInfoYear(java.lang.String value) {
		this.infoYear = value;
	}

	public java.lang.String getInfoYear() {
		return this.infoYear;
	}

	public void setCard(java.lang.String value) {
		this.card = value;
	}

	public java.lang.String getCard() {
		return this.card;
	}

	public void setCountryId(java.lang.String value) {
		this.countryId = value;
	}

	public java.lang.String getCountryId() {
		return this.countryId;
	}

	public void setAddressSz(java.lang.String value) {
		this.addressSz = value;
	}

	public java.lang.String getAddressSz() {
		return this.addressSz;
	}

	public void setAddressX(java.lang.String value) {
		this.addressX = value;
	}

	public java.lang.String getAddressX() {
		return this.addressX;
	}

	public void setAddressXz(java.lang.String value) {
		this.addressXz = value;
	}

	public java.lang.String getAddressXz() {
		return this.addressXz;
	}

	public void setAddressC(java.lang.String value) {
		this.addressC = value;
	}

	public java.lang.String getAddressC() {
		return this.addressC;
	}

	public void setCountryzb(java.lang.String value) {
		this.countryzb = value;
	}

	public java.lang.String getCountryzb() {
		return this.countryzb;
	}

	public void setCountryman(java.lang.String value) {
		this.countryman = value;
	}

	public java.lang.String getCountryman() {
		return this.countryman;
	}

	public void setPoorState(java.lang.String value) {
		this.poorState = value;
	}

	public java.lang.String getPoorState() {
		return this.poorState;
	}

	public void setPoorCard(java.lang.String value) {
		this.poorCard = value;
	}

	public java.lang.String getPoorCard() {
		return this.poorCard;
	}

	public void setPoorReason(java.lang.String value) {
		this.poorReason = value;
	}

	public java.lang.String getPoorReason() {
		return this.poorReason;
	}

	public void setJhtpDate(java.lang.String value) {
		this.jhtpDate = value;
	}

	public java.lang.String getJhtpDate() {
		return this.jhtpDate;
	}

	public void setSex(java.lang.String value) {
		this.sex = value;
	}

	public java.lang.String getSex() {
		return this.sex;
	}

	public void setFamilyNo(java.lang.String value) {
		this.familyNo = value;
	}

	public java.lang.String getFamilyNo() {
		return this.familyNo;
	}

	public void setFamilyMemers(java.lang.Integer value) {
		this.familyMemers = value;
	}

	public java.lang.Integer getFamilyMemers() {
		return this.familyMemers;
	}

	public void setTelphone(java.lang.String value) {
		this.telphone = value;
	}

	public java.lang.String getTelphone() {
		return this.telphone;
	}

	public void setCreator(java.lang.String value) {
		this.creator = value;
	}

	public java.lang.String getCreator() {
		return this.creator;
	}

	public void setCreateTime(java.lang.String value) {
		this.createTime = value;
	}

	public java.lang.String getCreateTime() {
		return this.createTime;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setWhcd(java.lang.String value) {
		this.whcd = value;
	}

	public java.lang.String getWhcd() {
		return this.whcd;
	}

	public void setAge(java.lang.Integer value) {
		this.age = value;
	}

	public java.lang.Integer getAge() {
		return this.age;
	}

	public void setWaterArea(java.lang.String value) {
		this.waterArea = value;
	}

	public java.lang.String getWaterArea() {
		return this.waterArea;
	}

	public void setGdArea(java.lang.String value) {
		this.gdArea = value;
	}

	public java.lang.String getGdArea() {
		return this.gdArea;
	}

	public void setSlArea(java.lang.String value) {
		this.slArea = value;
	}

	public java.lang.String getSlArea() {
		return this.slArea;
	}

	public void setZfjg(java.lang.String value) {
		this.zfjg = value;
	}

	public java.lang.String getZfjg() {
		return this.zfjg;
	}

	public void setZzArea(java.lang.String value) {
		this.zzArea = value;
	}

	public java.lang.String getZzArea() {
		return this.zzArea;
	}

	public void setRjsrqk(java.lang.String value) {
		this.rjsrqk = value;
	}

	public java.lang.String getRjsrqk() {
		return this.rjsrqk;
	}

//	private Set tdataResults = new HashSet(0);
//
//	public void setTdataResults(Set<TdataResult> tdataResult) {
//		this.tdataResults = tdataResult;
//	}
//
//	public Set<TdataResult> getTdataResults() {
//		return tdataResults;
//	}
//
//	private Set tdataHelpers = new HashSet(0);
//
//	public void setTdataHelpers(Set<TdataHelper> tdataHelper) {
//		this.tdataHelpers = tdataHelper;
//	}
//
//	public Set<TdataHelper> getTdataHelpers() {
//		return tdataHelpers;
//	}
}
