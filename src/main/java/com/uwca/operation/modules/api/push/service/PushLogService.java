package com.uwca.operation.modules.api.push.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.modules.api.push.dao.PushLogDao;
import com.uwca.operation.modules.api.push.entity.po.PushLog;
import com.uwca.operation.modules.api.push.entity.vo.PushLogDetailResult;
import com.uwca.operation.modules.api.push.entity.vo.PushLogResult;

@Service
@Transactional
public class PushLogService  extends CrudService<PushLogDao, PushLog>{
	
	public void addPushLog(PushLog pushLog){
		pushLog.preInsert();
		dao.addPushLog(pushLog);
	}
	
	public Page<PushLogResult> getPushLogs(int pageindex, int pagesize,
			String userid,String type) {
		PushLogResult pushLogResult = new PushLogResult();
		Page<PushLogResult> page = new Page<PushLogResult>(pageindex, pagesize);
		pushLogResult.setPage(page);
		pushLogResult.setId(userid);
		pushLogResult.setType(type);
		page.setList(dao.getPushLogs(pushLogResult));
		return page;
	}
	
	public PushLogDetailResult getPushLogByid(String id) {
		return dao.getPushLogByid(id);
	}
}
