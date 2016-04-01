package com.uwca.operation.modules.activity.dao;

import java.util.List;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.activity.entity.Acti;
import com.uwca.operation.modules.activity.entity.PropagandaRecord;

/**
 * 字典DAO接口
 */
@MyBatisDao
public interface ActiDao extends CrudDao<Acti> {

	public List<String> findAreasList(String actiId);
	
	public List<String> findPlatformsList(String actiId);
	
	public void savePropRecord(PropagandaRecord record);
	
}
