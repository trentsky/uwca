package com.uwca.operation.modules.activity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.modules.activity.dao.ActiDao;
import com.uwca.operation.modules.activity.entity.Acti;
import com.uwca.operation.modules.activity.entity.PropagandaRecord;

/**
 * 活动Service
 */
@Service
@Transactional(readOnly = true)
public class ActiService extends CrudService<ActiDao, Acti> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<Acti> findAllList(){
		return dao.findAllList(new Acti());
	}

	@Transactional(readOnly = false)
	public void save(Acti acti) {
		super.save(acti);
	}

	@Transactional(readOnly = false)
	public void delete(Acti acti) {
		super.delete(acti);
	}
	
	@Transactional(readOnly = false)
	public void savePropRecord(PropagandaRecord record) {
		dao.savePropRecord(record);
	}
	
	
}
