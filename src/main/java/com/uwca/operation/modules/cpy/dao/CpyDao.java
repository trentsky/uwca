package com.uwca.operation.modules.cpy.dao;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.cpy.entity.Cpy;


@MyBatisDao
public interface CpyDao extends CrudDao<Cpy> {
	
	Cpy getCompanyByid(String id);
}