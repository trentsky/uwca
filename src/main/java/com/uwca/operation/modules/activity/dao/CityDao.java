package com.uwca.operation.modules.activity.dao;

import java.util.List;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.activity.entity.City;

/**
 * 城市DAO接口
 */
@MyBatisDao
public interface CityDao extends CrudDao<City> {

	public List<City> findByParentId(String parentid);

}
