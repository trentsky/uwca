package com.uwca.operation.modules.api.push.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.utils.TokenTool;
import com.uwca.operation.modules.api.push.entity.vo.PushLogDetailResult;
import com.uwca.operation.modules.api.push.entity.vo.PushLogDetailVo;
import com.uwca.operation.modules.api.push.entity.vo.PushLogResult;
import com.uwca.operation.modules.api.push.entity.vo.PushLogVo;
import com.uwca.operation.modules.api.push.entity.vo.PushLogVo.Result;
import com.uwca.operation.modules.api.push.service.PushLogService;

@Controller
@RequestMapping(value = "/api/pushlog/")
public class PushLogController {

	@Autowired
	private PushLogService pushLogService;


	@RequestMapping(value = "getPushLogs")
	@ResponseBody
	public PushLogVo getPushLogs(@RequestParam("token") String token,
			@RequestParam("type") String type,
			@RequestParam("pagesize") int pagesize,
			@RequestParam("pageindex") int pageindex) {
		
		PushLogVo pushLogVo = new PushLogVo();
		Result result = pushLogVo.new Result();
		try {
			if (StringUtils.isEmpty(token)) {
				pushLogVo.setReturncode(1);
				pushLogVo.setMessage("参数不完整");
			}
			Page<PushLogResult> page = pushLogService.getPushLogs(pageindex, pagesize,TokenTool.getUserid(token),type);
			result.setPagecount(page.getTotalPage());
			result.setRowcount(page.getList().size());
			result.setList(page.getList());
			pushLogVo.setResult(result);
			pushLogVo.setReturncode(0);
			pushLogVo.setMessage("ok");
			return pushLogVo;
		} catch (Exception e) {
			pushLogVo.setReturncode(1);
			pushLogVo.setMessage("获取推送列表异常");
			e.printStackTrace();
			return pushLogVo;
		}
	}
	
	@RequestMapping(value = "getPushLogByid")
	@ResponseBody
	public PushLogDetailVo getPushLogByid(@RequestParam("id") String id) {
		
		PushLogDetailVo pushLogDetailVo = new PushLogDetailVo();
		com.uwca.operation.modules.api.push.entity.vo.PushLogDetailVo.Result result = pushLogDetailVo.new Result();
		try {
			if (StringUtils.isEmpty(id)) {
				pushLogDetailVo.setReturncode(1);
				pushLogDetailVo.setMessage("参数不完整");
			}
			PushLogDetailResult pushLogDetailResult = pushLogService.getPushLogByid(id);
			result.setPushLogDetailResult(pushLogDetailResult);
			pushLogDetailVo.setResult(result);
			pushLogDetailVo.setReturncode(0);
			pushLogDetailVo.setMessage("ok");
			return pushLogDetailVo;
		} catch (Exception e) {
			pushLogDetailVo.setReturncode(1);
			pushLogDetailVo.setMessage("获取推送信息异常");
			e.printStackTrace();
			return pushLogDetailVo;
		}
	}
}
