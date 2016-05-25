package com.uwca.operation.modules.api.oplog.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.utils.TokenTool;
import com.uwca.operation.modules.api.oplog.entity.vo.OplogResult;
import com.uwca.operation.modules.api.oplog.entity.vo.OplogVo;
import com.uwca.operation.modules.api.oplog.entity.vo.OplogVo.Result;
import com.uwca.operation.modules.api.oplog.service.OplogService;

@Controller
@RequestMapping(value = "/api/oplog/")
public class OplogController {

	@Autowired
	private OplogService oplogService;


	@RequestMapping(value = "getOplogs")
	@ResponseBody
	public OplogVo getOplogs(@RequestParam("token") String token,
			@RequestParam("pagesize") int pagesize,
			@RequestParam("pageindex") int pageindex) {
		
		OplogVo oplogVo = new OplogVo();
		Result result = oplogVo.new Result();
		try {
			if (StringUtils.isEmpty(token)) {
				oplogVo.setReturncode(1);
				oplogVo.setMessage("参数不完整");
			}
			Page<OplogResult> page = oplogService.getOplogs(pageindex, pagesize,TokenTool.getUserid(token));
			result.setPagecount(page.getTotalPage());
			result.setRowcount(page.getList().size());
			result.setList(page.getList());
			oplogVo.setResult(result);
			oplogVo.setReturncode(0);
			oplogVo.setMessage("ok");
			return oplogVo;
		} catch (Exception e) {
			oplogVo.setReturncode(1);
			oplogVo.setMessage("获取操作日志列表异常");
			e.printStackTrace();
			return oplogVo;
		}
	}
}
