package com.uwca.operation.modules.api.assure.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.modules.api.assure.dao.AssureDao;
import com.uwca.operation.modules.api.assure.entity.po.Assure;
import com.uwca.operation.modules.api.assure.entity.vo.AssureResult;
import com.uwca.operation.modules.api.company.dao.CompanyDao;
import com.uwca.operation.modules.api.company.entity.po.Company;
import com.uwca.operation.modules.api.oplog.entity.po.Oplog;
import com.uwca.operation.modules.api.oplog.service.OplogService;

@Service
@Transactional
public class AssureService extends CrudService<AssureDao, Assure>{
	
	@Autowired
	AssureDao assureDao;
	
	@Autowired
	CompanyDao companyDao;
	
	@Autowired
	private OplogService oplogService;

	public Page<AssureResult> getAssureCompanys(int pageindex,int pagesize,String mobile) {
		Page<AssureResult> page = new Page<AssureResult>(pageindex, pagesize);
		page.setList(assureDao.getAssureCompanys(mobile));
		return page;
	}

	public Page<AssureResult> getAssuredCompanys(int pageindex,int pagesize,String mobile) {
		
		Page<AssureResult> page = new Page<AssureResult>(pageindex, pagesize);
		page.setList(assureDao.getAssuredCompanys(mobile));
		return page;
	}

	public void delAssureCompany(String id) {
		assureDao.delAssureCompany(id);
	}

	public void addAssureCompany(Assure assure) {
		assure.preInsert();
		assureDao.insert(assure);
		
		// 添加操作日志
		Oplog oplog = new Oplog();
		oplog.setUserid(assure.getAssuredid());
		oplog.setObjectid(assure.getId());
		oplog.setType(8);
		Company company = companyDao.getCompanyByUserid(assure.getAssureid());
		oplog.setContent("向"+company.getCompanyname()+"申请担保");
		oplogService.addOplong(oplog);
	}

	public String reviewedAssureCompany(String id,String type) {
		Assure assure = dao.getAssureCompanyByid(id);
		if (null == assure) {
			return "担保信息不存在";
		}
		Company company = companyDao.getCompanyByUserid(assure.getAssureid());
		if ("1".equals(type)) {
			if (!"0".equals(assure.getState())) {
				return "该担保信息已审核";
			}
			if ((company.getGuarantee().compareTo(assure.getMoney()))<0) {
				return "担保金额不足";
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", company.getId());
			int currentStarLevel = company.getStarlevel();
			map.put("starlevel", currentStarLevel==1?1:currentStarLevel+1);
			map.put("guarantee", company.getGuarantee().subtract(assure.getMoney()));
			companyDao.updateCompany(map);
			
			
			//发送担保成功短信
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		assureDao.updateAssure(map);
		
		// 添加操作日志
		Oplog oplog = new Oplog();
		oplog.setUserid(assure.getAssuredid());
		oplog.setObjectid(assure.getId());
		if ("1".equals(type)) {
			oplog.setType(9);
			oplog.setContent("同意"+company.getCompanyname()+"申请的担保");
		}else if ("3".equals(type)) {
			oplog.setType(10);
			oplog.setContent("拒绝"+company.getCompanyname()+"申请的担保");
		}
		oplogService.addOplong(oplog);
		
		
		//发送推送
		
		
		
		return "ok";
	}
}
