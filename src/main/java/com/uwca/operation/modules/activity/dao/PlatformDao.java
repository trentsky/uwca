package com.uwca.operation.modules.activity.dao;

import java.util.List;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.activity.entity.Platform;

/**
 * 平台DAO接口
 */
@MyBatisDao
public interface PlatformDao extends CrudDao<Platform> {

	public List<Platform> findAllPlatform();

	public Platform findByPmAndVersion(String pm, String version);
	
}
