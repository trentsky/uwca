package com.uwca.operation.modules.api.push.dao;

import java.util.List;
import java.util.Map;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.api.push.entity.po.PushLog;
import com.uwca.operation.modules.api.push.entity.vo.PushLogDetailResult;
import com.uwca.operation.modules.api.push.entity.vo.PushLogResult;

@MyBatisDao
public interface PushLogDao extends CrudDao<PushLog>{

	void addPushLog(PushLog pushLog);

	List<PushLogResult> getPushLogs(Map<String, Object> map);

	PushLogDetailResult getPushLogByid(String id);

}
