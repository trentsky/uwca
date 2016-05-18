package com.uwca.operation.modules.api.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.common.utils.GuavaCacheUtil;
import com.uwca.operation.modules.api.company.dao.CompanyDao;
import com.uwca.operation.modules.api.company.entity.po.Company;
import com.uwca.operation.modules.api.sys.dao.DeviceDao;
import com.uwca.operation.modules.api.sys.dao.UwcaUserDao;
import com.uwca.operation.modules.api.sys.entity.po.Device;
import com.uwca.operation.modules.api.sys.entity.po.UwcaUser;
import com.uwca.operation.modules.api.sys.entity.po.UserInfo;
import com.uwca.operation.modules.api.sys.entity.vo.VerifyCodeVo;
import com.uwca.operation.modules.api.sys.entity.vo.VerifyCodeVo.Result;

@Service
@Transactional
public class SysService extends CrudService<UwcaUserDao, UwcaUser> {
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private UwcaUserDao uwcaUserDao;
	
	@Autowired
	private DeviceDao deviceDao;

	public boolean isVerifyCodeExpire(String mobile) {
		return StringUtils.isEmpty(GuavaCacheUtil.get(mobile)) ? true : false;
	}

	public void getVerifyCode(String mobile) {
		VerifyCodeVo verifyCode = new VerifyCodeVo();
		Result result = verifyCode.new Result();
		String code = getRandNum(6);
		result.setCode(code);
		GuavaCacheUtil.put(mobile, code);
		
		//调用短信接口发送验证码
		
		
		
	}

	public boolean isExistVerifyCode(String mobile, String verifyCode) {
		String code = GuavaCacheUtil.get(mobile);
		boolean flag = false;
		if (StringUtils.isEmpty(code)) {
			return flag;
		}
		if (code.equals(verifyCode)) {
			flag = true;
		}
		return flag;
	}
	
	public UwcaUser reg(UwcaUser user) {
		Company company = new Company();
		company.setCompanyname(user.getCompanyName());
		company.preInsert();
		companyDao.insert(company);
		user.setCompanyid(company.getId());
		user.preInsert();
		uwcaUserDao.insert(user);
		return user;
	}
	
	public Company getCompanyByName(String companyName) {
		return companyDao.getCompanyByName(companyName);
	}

	public UserInfo getUserByMobile(String mobile) {
		return uwcaUserDao.getUserInfoByMobile(mobile);
	}
	
	public String getRandNum(int charCount) {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;

	}

	public int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	public boolean isExistUser(String mobile) {
		return null == uwcaUserDao.isExistUser(mobile)?false:true;
	}

	public boolean isExistCompany(String companyName) {
		return null == companyDao.isExistCompany(companyName)?false:true;
		
	}

	public void modifyPasswd(Map<String, Object> map) {
		uwcaUserDao.modifyPasswd(map);
	}

	public void isLastLoginDevice(String mobile, String deviceid) {
		String lastLoginDevice = uwcaUserDao.getLastLoginDevice(mobile);
		if (!deviceid.equals(lastLoginDevice)) {
			
			//发送非上次登陆设备
			
			//更新用户表登陆设备标示
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mobile", mobile);
			map.put("deviceid", deviceid);
			uwcaUserDao.updateLoginDevice(map);
			
		}
	}

	public void regDevice(String mobile, String deviceid, String channelid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", mobile);
		map.put("deviceid", deviceid);
		map.put("channelid", channelid);
		List<Device> list = deviceDao.getDeviceByMobile(map);
		if (null == list || list.size() < 1) {
			Device device = new Device();
			device.preInsert();
			device.setChannelid(channelid);
			device.setDeviceid(deviceid);
			device.setMobile(mobile);
			deviceDao.insert(device);
		}
	}
}
