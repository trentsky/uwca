package com.uwca.operation.modules.cpy.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.common.utils.DateUtils;
import com.uwca.operation.modules.cpy.dao.CpyDao;
import com.uwca.operation.modules.cpy.entity.Cpy;

@Service
@Transactional(readOnly = true)
public class CpyService extends CrudService<CpyDao, Cpy> {

	public Page<Cpy> findPage(Page<Cpy> page, Cpy company) {
		
		// 设置默认时间范围，默认当前月
		if (company.getStarttime() == null){
			company.setStarttime(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (company.getEndtime() == null){
			company.setEndtime(DateUtils.addMonths(company.getStarttime(), 1));
		}
		
		return super.findPage(page, company);
		
	}
	
}
