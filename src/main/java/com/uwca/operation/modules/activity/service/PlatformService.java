package com.uwca.operation.modules.activity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.modules.activity.dao.PlatformDao;
import com.uwca.operation.modules.activity.entity.Platform;

/**
 * 活动Service
 */
@Service
@Transactional(readOnly = true)
public class PlatformService extends CrudService<PlatformDao, Platform> {
	
	@Transactional(readOnly = false)
	public List<Platform> findAllPlatforms(){
		return dao.findAllPlatform();
	}
	
}
