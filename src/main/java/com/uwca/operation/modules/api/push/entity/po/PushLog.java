package com.uwca.operation.modules.api.push.entity.po;

import com.uwca.operation.common.persistence.DataEntity;

public class PushLog extends DataEntity<PushLog>{

	private static final long serialVersionUID = 1L;
	
	private String pushtitle;
	private String pushcontent;
	private String fromuserid;
	private String touserid;
	private int type;
	private String msgid;
	private String parameter;
	
	public String getPushtitle() {
		return pushtitle;
	}
	public void setPushtitle(String pushtitle) {
		this.pushtitle = pushtitle;
	}
	public String getPushcontent() {
		return pushcontent;
	}
	public void setPushcontent(String pushcontent) {
		this.pushcontent = pushcontent;
	}
	public String getFromuserid() {
		return fromuserid;
	}
	public void setFromuserid(String fromuserid) {
		this.fromuserid = fromuserid;
	}
	public String getTouserid() {
		return touserid;
	}
	public void setTouserid(String touserid) {
		this.touserid = touserid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	

}
