package com.uwca.operation.modules.activity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.modules.activity.dao.CityDao;
import com.uwca.operation.modules.activity.entity.City;

/**
 * 活动Service
 */
@Service
@Transactional(readOnly = true)
public class CityService extends CrudService<CityDao, City> {
	
	@Transactional(readOnly = false)
	public List<City> findAllProvice() {
		return dao.findByParentId("0");
	}
	
	@Transactional(readOnly = false)
	public List<City> findByParentId(String parentid) {
		return dao.findByParentId(parentid);
	}
	
}
