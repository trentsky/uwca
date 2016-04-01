package com.uwca.operation.modules.activity.entity;

import java.util.List;

import com.uwca.operation.common.persistence.DataEntity;
import com.uwca.operation.common.utils.SpringContextHolder;
import com.uwca.operation.modules.activity.dao.ActiDao;

public class Acti extends DataEntity<Acti> {
	
	private static final long serialVersionUID = 1L;
	private int platformid;
	private String version;
	private String location;
	private String title;
	private String cityname;
	private String showtype;
	private String picurl;
	private String targeturl;
	private String startpushtime;
	private String endpushtime;
	private String createtime;
	private String edittime;
	private int state;
	private String username;
	private String pversion; //平台与版本
	private String areas; //投放城市
	private String pushtime; //投放时间
	private Integer sort;	// 排序
	
	private List<String> pmlist;
	private List<String> cities;
	private List<String> provinces;
	
	@Override
	public void preInsert(){}
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	private ActiDao actiDao = SpringContextHolder.getBean(ActiDao.class);
	
	public String getPversion() {
		List<String> list = actiDao.findPlatformsList(id);
		StringBuffer strBuffer = new StringBuffer();
		int count = list.size();
		for (int i = 0; i <count; i++) {
			String s = list.get(i);
			strBuffer.append(i<(count-1)?(s+"<br/>"):s);
		}
		pversion = strBuffer.toString();
		return pversion;
	}
	public void setPversion(String pversion) {
		this.pversion = pversion;
	}
	public String getAreas() {
		List<String> list = actiDao.findAreasList(id);
		StringBuffer strBuffer = new StringBuffer();
		int count = list.size();
		for (int i = 0; i <count; i++) {
			String s = list.get(i);
			strBuffer.append(i<(count-1)?(s+"<br/>"):s);
		}
		areas = strBuffer.toString();
		return areas;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAreas(String areas) {
		this.areas = areas;
	}
	public String getPushtime() {
		pushtime = startpushtime + " 至 " + endpushtime;
		return pushtime;
	}
	public void setPushtime(String pushtime) {
		this.pushtime = pushtime;
	}
	
	public int getPlatformid() {
		return platformid;
	}
	public void setPlatformid(int platformid) {
		this.platformid = platformid;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	public String getShowtype() {
		return showtype;
	}
	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	public String getStartpushtime() {
		return startpushtime;
	}
	public void setStartpushtime(String startpushtime) {
		this.startpushtime = startpushtime;
	}
	public String getEndpushtime() {
		return endpushtime;
	}
	public void setEndpushtime(String endpushtime) {
		this.endpushtime = endpushtime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getEdittime() {
		return edittime;
	}
	public void setEdittime(String edittime) {
		this.edittime = edittime;
	}
	public ActiDao getActiDao() {
		return actiDao;
	}
	public void setActiDao(ActiDao actiDao) {
		this.actiDao = actiDao;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public String getTargeturl() {
		return targeturl;
	}

	public void setTargeturl(String targeturl) {
		this.targeturl = targeturl;
	}

	public List<String> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<String> provinces) {
		this.provinces = provinces;
	}

	public List<String> getPmlist() {
		return pmlist;
	}

	public void setPmlist(List<String> pmlist) {
		this.pmlist = pmlist;
	}
	
}
