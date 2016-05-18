package com.uwca.operation.modules.api.sys.dao;

import java.util.Map;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.api.sys.entity.po.UwcaUser;
import com.uwca.operation.modules.api.sys.entity.po.UserInfo;

@MyBatisDao
public interface UwcaUserDao extends CrudDao<UwcaUser>{

	UserInfo getUserInfoByMobile(String mobile);

	UwcaUser isExistUser(String mobile);

	void modifyPasswd(Map<String, Object> map);

	String getLastLoginDevice(String mobile);

	void updateLoginDevice(Map<String, Object> map);

}
