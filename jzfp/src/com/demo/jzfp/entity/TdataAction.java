package com.demo.jzfp.entity;


public class TdataAction{
	
	/////////帮扶措施
	private java.lang.String actionType;//政策措施分类:1七个一批；2、其他
	private java.lang.String actionDl;//大类
	private java.lang.String actionXl;//小类
	private String actionMoney;//增收获益
	private String remark;//说明
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getActionMoney() {
		return actionMoney;
	}
	public void setActionMoney(String actionMoney) {
		this.actionMoney = actionMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public java.lang.String getActionDl() {
		return actionDl;
	}
	public void setActionDl(java.lang.String actionDl) {
		this.actionDl = actionDl;
	}
	public java.lang.String getActionXl() {
		return actionXl;
	}
	public void setActionXl(java.lang.String actionXl) {
		this.actionXl = actionXl;
	}
	@Override
	public String toString() {
		return "TdataAction [actionType=" + actionType + ", actionDl=" + actionDl + ", actionXl=" + actionXl
				+ ", actionMoney=" + actionMoney + ", remark=" + remark + "]";
	}
	
}

