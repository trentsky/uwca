package com.uwca.operation.modules.api.sys.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uwca.operation.common.utils.BaseEntity;
import com.uwca.operation.common.utils.TokenTool;
import com.uwca.operation.modules.api.sys.entity.po.UwcaUser;
import com.uwca.operation.modules.api.sys.entity.vo.RegVo;
import com.uwca.operation.modules.api.sys.entity.vo.RegVo.Result;
import com.uwca.operation.modules.api.sys.service.SysService;

@Controller
@RequestMapping(value = "/api/sys")
public class RegController {

	@Autowired
	private SysService sysService;

	@RequestMapping(value = "reg")
	@ResponseBody
	public RegVo reg(@RequestParam("mobile") String mobile,
			@RequestParam("verifyCode") String verifyCode,
			@RequestParam("passwd") String passwd,
			@RequestParam("companyName") String companyName,
			@RequestParam("name") String name,
			@RequestParam("position") String position,
			@RequestParam("deviceid") String deviceid,
			@RequestParam("sign") String sign) {

		RegVo regVo = new RegVo();
		Result result = regVo.new Result();

		try {
			if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(verifyCode)
					|| StringUtils.isEmpty(passwd)
					|| StringUtils.isEmpty(companyName)
					|| StringUtils.isEmpty(name)
					|| StringUtils.isEmpty(deviceid)) {
				regVo.setReturncode(1);
				regVo.setMessage("参数不完整");
				return regVo;
			}

			// if (!sysService.isExistVerifyCode(mobile, verifyCode)) {
			// regVo.setReturncode(1);
			// regVo.setMessage("验证码错误");
			// return regVo;
			// }

			UwcaUser user = new UwcaUser();
			user.setMobile(mobile);
			if (sysService.isExistUser(mobile)) {
				regVo.setReturncode(1);
				regVo.setMessage("该手机号已注册");
				return regVo;
			}

			if (sysService.isExistCompany(companyName)) {
				regVo.setReturncode(1);
				regVo.setMessage("该公司名称已注册");
				return regVo;
			}

			user.setPasswd(passwd);
			user.setName(name);
			user.setPosition(position);
			user.setDeviceid(deviceid);
			user.setCompanyName(companyName);
			UwcaUser u = sysService.reg(user);

			regVo.setReturncode(0);
			regVo.setMessage("ok");
			result.setUserid(u.getId());
			// 设置返回的token值

		} catch (Exception e) {
			regVo.setReturncode(1);
			regVo.setMessage("注册失败");
			e.printStackTrace();
		}
		regVo.setResult(result);
		return regVo;
	}

	@RequestMapping(value = "getVerifyCode")
	@ResponseBody
	public BaseEntity getVerifyCode(@RequestParam("mobile") String mobile) {
		BaseEntity baseEntity = new BaseEntity();
		try {
			sysService.getVerifyCode(mobile);
			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("获取验证码失败");
		}
		return baseEntity;
	}

	@RequestMapping(value = "modifyPaasswd")
	@ResponseBody
	public BaseEntity modifyPaasswd(@RequestParam("token") String token,
			@RequestParam("code") String code,
			@RequestParam("passwd") String passwd,
			@RequestParam("sign") String sign) {
		BaseEntity baseEntity = new BaseEntity();
		try {

			if (StringUtils.isEmpty(token) || StringUtils.isEmpty(code)
					|| StringUtils.isEmpty(passwd) || StringUtils.isEmpty(sign)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}
			
			String mobile = TokenTool.getMobile(token);
			
			if (!sysService.isExistVerifyCode(mobile, code)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("验证码错误");
				return baseEntity;
			}

			if (!sysService.isExistUser(mobile)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("用户不存在");
				return baseEntity;
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mobile", mobile);
			map.put("passwd", passwd);
			sysService.modifyPasswd(map);
			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("获取验证码失败");
		}
		return baseEntity;
	}

	@RequestMapping(value = "regDevice")
	@ResponseBody
	public BaseEntity regDevice(@RequestParam("token") String token,
			@RequestParam("deviceid") String deviceid,
			@RequestParam("channelid") String channelid,
			@RequestParam("sign") String sign) {
		BaseEntity baseEntity = new BaseEntity();
		try {

			if (StringUtils.isEmpty(token) || StringUtils.isEmpty(deviceid)
					|| StringUtils.isEmpty(channelid)
					|| StringUtils.isEmpty(sign)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}
			String mobile = TokenTool.getMobile(token);
			if (!sysService.isExistUser(mobile)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("用户不存在");
				return baseEntity;
			}
			sysService.regDevice(mobile, deviceid, channelid);
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("设备注册失败");
			e.printStackTrace();
		}
		return baseEntity;
	}

}
