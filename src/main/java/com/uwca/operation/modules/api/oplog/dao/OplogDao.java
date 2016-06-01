package com.uwca.operation.modules.api.oplog.dao;

import java.util.List;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.api.oplog.entity.po.Oplog;
import com.uwca.operation.modules.api.oplog.entity.vo.OplogResult;

@MyBatisDao
public interface OplogDao extends CrudDao<Oplog>{
	
	public void addOplong(Oplog oplog);

	public List<OplogResult> getOplogs(OplogResult oplogResult);
	
}
