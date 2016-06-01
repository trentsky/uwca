package com.uwca.operation.modules.api.assure.dao;

import java.util.List;
import java.util.Map;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.api.assure.entity.po.Assure;
import com.uwca.operation.modules.api.assure.entity.vo.AssureResult;

@MyBatisDao
public interface AssureDao extends CrudDao<Assure> {

	List<AssureResult> getAssureCompanys(AssureResult assureResult);

	List<AssureResult> getAssuredCompanys(AssureResult assureResult);

	void delAssureCompany(String id);

	Assure getAssureCompanyByid(String id);

	void updateAssure(Map<String, Object> map);

}
