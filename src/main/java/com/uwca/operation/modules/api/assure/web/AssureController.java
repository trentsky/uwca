package com.uwca.operation.modules.api.assure.web;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.utils.BaseEntity;
import com.uwca.operation.common.utils.TokenTool;
import com.uwca.operation.modules.api.assure.entity.po.Assure;
import com.uwca.operation.modules.api.assure.entity.vo.AssureResult;
import com.uwca.operation.modules.api.assure.entity.vo.AssureVo;
import com.uwca.operation.modules.api.assure.entity.vo.AssureVo.Result;
import com.uwca.operation.modules.api.assure.service.AssureService;

@Controller
@RequestMapping(value = "/api/assure/")
public class AssureController {

	@Autowired
	private AssureService assureService;

	@RequestMapping(value = "getAssureCompanys")
	@ResponseBody
	public AssureVo getAssureCompany(@RequestParam("token") String token,
			@RequestParam("pagesize") int pagesize,
			@RequestParam("pageindex") int pageindex,
			@RequestParam("type") String type) {
		AssureVo assureVo = new AssureVo();
		Result result = assureVo.new Result();

		try {
			if (StringUtils.isEmpty(token)||StringUtils.isEmpty(type)) {
				assureVo.setReturncode(1);
				assureVo.setMessage("参数不完整");
				assureVo.setResult(result);
				return assureVo;
			}

			String mobile = TokenTool.getMobile(token);
			Page<AssureResult> page = new Page<AssureResult>();
			// type=1 获取我担保的企业 2:获取担保我的企业
			if ("1".equals(type)) {
				page = assureService.getAssureCompanys(pageindex, pagesize,
						mobile);
			} else if ("2".equals(type)) {
				page = assureService.getAssuredCompanys(pageindex, pagesize,
						mobile);
			} else {
				assureVo.setReturncode(1);
				assureVo.setMessage("type 参数传递错误");
				assureVo.setResult(result);
				return assureVo;
			}
			result.setPagecount(page.getTotalPage());
			result.setRowcount(page.getList().size());
			result.setList(page.getList());
			assureVo.setResult(result);
			assureVo.setReturncode(0);
			assureVo.setMessage("ok");
			return assureVo;
		} catch (Exception e) {
			assureVo.setReturncode(1);
			assureVo.setMessage("获取担保企业信息异常");
			assureVo.setResult(result);
			e.printStackTrace();
			return assureVo;
		}
	}

	@RequestMapping(value = "delAssureCompany")
	@ResponseBody
	public BaseEntity delAssureCompany(@RequestParam("id") String id,
			@RequestParam("sign") String sign) {

		BaseEntity baseEntity = new BaseEntity();
		try {
			if (StringUtils.isEmpty(id) || StringUtils.isEmpty(sign)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}
			assureService.delAssureCompany(id);
			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
			return baseEntity;
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("删除担保企业信息异常");
			e.printStackTrace();
			return baseEntity;
		}
	}

	@RequestMapping(value = "reviewedAssureCompany")
	@ResponseBody
	public BaseEntity reviewedAssureCompany(@RequestParam("id") String id,
			@RequestParam("type") String type, @RequestParam("sign") String sign) {
		BaseEntity baseEntity = new BaseEntity();
		try {
			if (StringUtils.isEmpty(id) || StringUtils.isEmpty(sign)
					|| StringUtils.isEmpty(type)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}
			//1：同意 2：拒绝
			String message = assureService.reviewedAssureCompany(id,type);
			if (!"ok".equals(message)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage(message);
				return baseEntity;
			}
			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
			return baseEntity;
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("审核担保企业异常");
			e.printStackTrace();
			return baseEntity;
		}
	}

	@RequestMapping(value = "addAssureCompany")
	@ResponseBody
	public BaseEntity addAssureCompany(
			@RequestParam("assureid") String assureid,
			@RequestParam("token") String token,
			@RequestParam("money") String money,
			@RequestParam("sign") String sign) {

		BaseEntity baseEntity = new BaseEntity();
		try {
			if (StringUtils.isEmpty(assureid) || StringUtils.isEmpty(sign)
					|| StringUtils.isEmpty(token)
					|| StringUtils.isEmpty(assureid)
					|| StringUtils.isEmpty(money)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}
			String assuredid = TokenTool.getUserid(token);
			Assure assure = new Assure();
			assure.setAssuredid(assuredid);
			assure.setAssureid(assureid);
			assure.setMoney(new BigDecimal(money));
			assureService.addAssureCompany(assure);
			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
			return baseEntity;
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("申请担保异常");
			e.printStackTrace();
			return baseEntity;
		}
	}

}
