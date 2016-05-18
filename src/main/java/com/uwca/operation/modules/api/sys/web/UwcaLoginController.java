package com.uwca.operation.modules.api.sys.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uwca.operation.common.utils.DES3Util;
import com.uwca.operation.modules.api.sys.entity.po.UserInfo;
import com.uwca.operation.modules.api.sys.entity.vo.LoginVo;
import com.uwca.operation.modules.api.sys.entity.vo.LoginVo.Result;
import com.uwca.operation.modules.api.sys.service.SysService;

@Controller
@RequestMapping(value = "/api/")
public class UwcaLoginController {

	@Autowired
	private SysService sysService;

	@RequestMapping(value = "login")
	@ResponseBody
	public LoginVo login(@RequestParam("mobile") String mobile,
			@RequestParam("code") String code,
			@RequestParam("deviceid") String deviceid,
			@RequestParam("sign") String sign) {
		LoginVo loginVo = new LoginVo();
		Result result = loginVo.new Result();

		try {
			if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(code)
					|| StringUtils.isEmpty(deviceid)
					|| StringUtils.isEmpty(sign)) {
				loginVo.setReturncode(0);
				loginVo.setMessage("参数不完整");
				loginVo.setResult(result);
				return loginVo;
			}

			// if (!sysService.isExistVerifyCode(mobile, code)) {
			// loginVo.setReturncode(1);
			// loginVo.setMessage("验证码错误");
			// loginVo.setResult(result);
			// return loginVo;
			// }

			UserInfo user = sysService.getUserByMobile(mobile);
			if (null == user) {
				loginVo.setReturncode(0);
				loginVo.setMessage("请注册后登陆");
				loginVo.setResult(result);
				return loginVo;
			}

			sysService.isLastLoginDevice(mobile, deviceid);

			loginVo.setReturncode(0);
			loginVo.setMessage("ok");
			result.setCompanyid(user.getCompanyid());
			result.setUserid(user.getUserid());
			result.setItemcheckState(user.getItemcheckstate());

			// 设置返回的token
			result.setToken(DES3Util.encode(String.format("%s|%s|%s|%s",
					user.getUserid(), user.getMobile(), user.getPasswd(),
					user.getCompanyid())));
			loginVo.setResult(result);
			return loginVo;
		} catch (Exception e) {
			loginVo.setReturncode(1);
			loginVo.setMessage("登陆异常");
			loginVo.setResult(result);
			e.printStackTrace();
			return loginVo;
		}
	}

}
