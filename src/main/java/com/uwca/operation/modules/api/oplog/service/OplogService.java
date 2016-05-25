package com.uwca.operation.modules.api.oplog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.modules.api.oplog.dao.OplogDao;
import com.uwca.operation.modules.api.oplog.entity.po.Oplog;
import com.uwca.operation.modules.api.oplog.entity.vo.OplogResult;

@Service
@Transactional
public class OplogService extends CrudService<OplogDao, Oplog>{
	
	public void addOplong(Oplog oplog){
		oplog.preInsert();
		dao.addOplong(oplog);
	}

	public Page<OplogResult> getOplogs(int pageindex, int pagesize,
			String userid) {
		Page<OplogResult> page = new Page<OplogResult>(pageindex, pagesize);
		page.setList(dao.getOplogs(userid));
		return page;
	}
}
