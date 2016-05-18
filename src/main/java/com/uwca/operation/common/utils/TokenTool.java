package com.uwca.operation.common.utils;

public class TokenTool {
	
	//用户id|手机号|密码|公司ID
	
	public static String getUserid(String token) throws Exception{
		return getTokenOrgValue(token)[0];
	}
	
	public static String getMobile(String token) throws Exception{
		return getTokenOrgValue(token)[1];
	}
	
	public static String getPasswd(String token) throws Exception{
		return getTokenOrgValue(token)[2];
	}
	
	public static String getCompanyid(String token) throws Exception{
		return getTokenOrgValue(token)[3];
	}
	
	public static String[] getTokenOrgValue(String token) throws Exception{
		return DES3Util.decode(token).split("\\|");
	}
}
