package com.demo.jzfp.entity;

public class TdataResult{
	
	///////////帮扶成效
	private java.lang.String resultId;
	private String tpDate;//计划脱贫时间
	private String jtsrRjsr;//人均收入
	private String jtsrZsr;//总收入
	private String addressSafe;//住房安全
	private String jbshbzYl;//医疗
	private String jbshbzYwjy;//义务教育
	private String jffhtptj;//是否符合脱贫条件
	private String zcgjddz;//驻村工作队队长
	private String cfzr;//村负责人
	
	public java.lang.String getResultId() {
		return resultId;
	}
	public void setResultId(java.lang.String resultId) {
		this.resultId = resultId;
	}
	public String getTpDate() {
		return tpDate;
	}
	public void setTpDate(String tpDate) {
		this.tpDate = tpDate;
	}
	public String getJtsrRjsr() {
		return jtsrRjsr;
	}
	public void setJtsrRjsr(String jtsrRjsr) {
		this.jtsrRjsr = jtsrRjsr;
	}
	public String getJtsrZsr() {
		return jtsrZsr;
	}
	public void setJtsrZsr(String jtsrZsr) {
		this.jtsrZsr = jtsrZsr;
	}
	public String getAddressSafe() {
		return addressSafe;
	}
	public void setAddressSafe(String addressSafe) {
		this.addressSafe = addressSafe;
	}
	public String getJbshbzYl() {
		return jbshbzYl;
	}
	public void setJbshbzYl(String jbshbzYl) {
		this.jbshbzYl = jbshbzYl;
	}
	public String getJbshbzYwjy() {
		return jbshbzYwjy;
	}
	public void setJbshbzYwjy(String jbshbzYwjy) {
		this.jbshbzYwjy = jbshbzYwjy;
	}
	public String getzcgjddz() {
		return zcgjddz;
	}
	public void setzcgjddz(String zcgjddz) {
		this.zcgjddz = zcgjddz;
	}
	public String getcfzr() {
		return cfzr;
	}
	public void setcfzr(String cfzr) {
		this.cfzr = cfzr;
	}
	public String getJffhtptj() {
		return jffhtptj;
	}
	public void setJffhtptj(String jffhtptj) {
		this.jffhtptj = jffhtptj;
	}
	@Override
	public String toString() {
		return "TdataResult [tpDate=" + tpDate + ", jtsrRjsr=" + jtsrRjsr + ", jtsrZsr=" + jtsrZsr + ", addressSafe="
				+ addressSafe + ", jbshbzYl=" + jbshbzYl + ", jbshbzYwjy=" + jbshbzYwjy + ", zcgjddz=" + zcgjddz + ", cfzr=" + cfzr + ", jffhtptj=" + jffhtptj
				+ "]";
	}
	
}

