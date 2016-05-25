package com.uwca.operation.modules.api.sys.dao;

import java.util.List;
import java.util.Map;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.api.sys.entity.po.Device;

@MyBatisDao
public interface DeviceDao extends CrudDao<Device>{

	List<Device> getDevicesByUserid(Map<String, Object> map);
	
	Device getDeviceByuserid(String userid);

}
