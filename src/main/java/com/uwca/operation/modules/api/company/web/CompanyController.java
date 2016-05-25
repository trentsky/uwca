package com.uwca.operation.modules.api.company.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.uwca.operation.common.config.Global;
import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.utils.BaseEntity;
import com.uwca.operation.common.utils.Encodes;
import com.uwca.operation.common.utils.TokenTool;
import com.uwca.operation.modules.api.company.entity.po.Company;
import com.uwca.operation.modules.api.company.entity.po.CompanyDescribe;
import com.uwca.operation.modules.api.company.entity.vo.CompanyDescribeVo;
import com.uwca.operation.modules.api.company.entity.vo.CompanyDscResult;
import com.uwca.operation.modules.api.company.entity.vo.CompanyInfo;
import com.uwca.operation.modules.api.company.entity.vo.CompanyResult;
import com.uwca.operation.modules.api.company.entity.vo.CompanyVo;
import com.uwca.operation.modules.api.company.entity.vo.CompanyVo.Result;
import com.uwca.operation.modules.api.company.entity.vo.CompanysVo;
import com.uwca.operation.modules.api.company.service.CompanyService;

@Controller
@RequestMapping(value = "/api/company/")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "getCompanyInfo")
	@ResponseBody
	public CompanyVo getCompanyInfo(@RequestParam("token") String token) {
		CompanyVo companyVo = new CompanyVo();
		Result result = companyVo.new Result();

		try {
			if (StringUtils.isEmpty(token)) {
				companyVo.setReturncode(1);
				companyVo.setMessage("参数不完整");
				companyVo.setResult(result);
				return companyVo;
			}

			Company company = companyService.getCompanyInfo(TokenTool
					.getMobile(token));
			String businesslicense = company.getBusinesslicense();
			if (StringUtils.isNotEmpty(businesslicense)) {
				@SuppressWarnings("resource")
				InputStream in = new FileInputStream("/data/userfiles/"
						+ businesslicense);
				if (null != in) {
					byte[] bytes = new byte[in.available()];
					company.setBusinesslicense(Encodes.encodeBase64(bytes));
				}
			}
			CompanyResult companyResult = new CompanyResult();
			BeanUtils.copyProperties(company, companyResult);
			result.setCompanyResult(companyResult);
			companyVo.setResult(result);
			companyVo.setReturncode(0);
			companyVo.setMessage("ok");
			return companyVo;
		} catch (Exception e) {
			companyVo.setReturncode(1);
			companyVo.setMessage("获取公司信息异常");
			companyVo.setResult(result);
			e.printStackTrace();
			return companyVo;
		}
	}

	@RequestMapping(value = "modifyCompanyInfo")
	@ResponseBody
	public CompanyVo modifyCompanyInfo(@RequestParam("token") String token,
			@RequestParam("companyname") String companyname,
			@RequestParam("legalperson") String legalperson,
			@RequestParam("organizationcode") String organizationcode,
			@RequestParam("fax") String fax, @RequestParam("mail") String mail,
			@RequestParam("website") String website,
			@RequestParam("address") String address,
			@RequestParam("businesslicense") MultipartFile businesslicense,
			@RequestParam("sign") String sign) {
		CompanyVo companyVo = new CompanyVo();
		Result result = companyVo.new Result();

		try {
			if (StringUtils.isEmpty(token) || StringUtils.isEmpty(sign)) {
				companyVo.setReturncode(1);
				companyVo.setMessage("参数不完整");
				companyVo.setResult(result);
				return companyVo;
			}

			String newFileName = "";
			if (businesslicense != null && !businesslicense.isEmpty()) {
				String fileName = businesslicense.getOriginalFilename();
				String extensionName = fileName.substring(fileName
						.lastIndexOf(".") + 1);
				newFileName = String.valueOf(System.currentTimeMillis()) + "."
						+ extensionName;
				try {
					saveFile(newFileName, businesslicense);
				} catch (Exception e) {
					companyVo.setReturncode(1);
					companyVo.setMessage("营业执照保存失败");
					companyVo.setResult(result);
					return companyVo;
				}
			}

			Map<String, Object> map = new HashMap<String, Object>();
			String companyid = TokenTool.getCompanyid(token);
			map.put("id", companyid);
			map.put("userid", TokenTool.getUserid(token));
			map.put("companyname", companyname);
			map.put("legalperson", legalperson);
			map.put("organizationcode", organizationcode);
			map.put("businesslicense", newFileName);
			map.put("fax", fax);
			map.put("mail", mail);
			map.put("address", address);
			map.put("website", website);
			map.put("state", 1);
			companyService.updateCompany(map);
			Company company = companyService.getCompanyInfoById(companyid);
			CompanyResult companyResult = new CompanyResult();
			if (null != company) {
				BeanUtils.copyProperties(company, companyResult);
			}
			result.setCompanyResult(companyResult);
			companyVo.setReturncode(0);
			companyVo.setMessage("ok");
			return companyVo;
		} catch (Exception e) {
			companyVo.setReturncode(1);
			companyVo.setMessage("修改公司信息异常");
			companyVo.setResult(result);
			e.printStackTrace();
			return companyVo;
		}
	}

	@RequestMapping(value = "getCompanyDescs")
	@ResponseBody
	public CompanyDescribeVo getCompanyDescs(
			@RequestParam("token") String token,
			@RequestParam("pagesize") int pagesize,
			@RequestParam("pageindex") int pageindex) {
		CompanyDescribeVo companyDescribeVo = new CompanyDescribeVo();
		com.uwca.operation.modules.api.company.entity.vo.CompanyDescribeVo.Result result = companyDescribeVo.new Result();
		try {
			if (StringUtils.isEmpty(token)) {
				companyDescribeVo.setReturncode(1);
				companyDescribeVo.setMessage("参数不完整");
				companyDescribeVo.setResult(result);
				return companyDescribeVo;
			}
			Page<CompanyDscResult> page = companyService.getCompanyDescs(
					TokenTool.getCompanyid(token), pageindex, pagesize);

			result.setPagecount(page.getTotalPage());
			result.setRowcount(page.getList().size());
			result.setList(page.getList());
			companyDescribeVo.setResult(result);
			companyDescribeVo.setReturncode(0);
			companyDescribeVo.setMessage("ok");
			return companyDescribeVo;
		} catch (Exception e) {
			companyDescribeVo.setReturncode(1);
			companyDescribeVo.setMessage("获取相关词汇信息列表异常");
			companyDescribeVo.setResult(result);
			e.printStackTrace();
			return companyDescribeVo;
		}
	}

	@RequestMapping(value = "addCompanyDesc")
	@ResponseBody
	public BaseEntity addCompanyDesc(@RequestParam("token") String token,
			@RequestParam("content") String content,
			@RequestParam("sign") String sign) {
		BaseEntity baseEntity = new BaseEntity();
		try {
			if (StringUtils.isEmpty(token) || StringUtils.isEmpty(content)
					|| StringUtils.isEmpty(sign)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}

			String companyid = TokenTool.getCompanyid(token);

			if (companyService.isExistCompanyDesc(companyid, content)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("该相关词汇已添加");
				return baseEntity;
			}

			CompanyDescribe companyDescribe = new CompanyDescribe();
			companyDescribe.setCompanyid(companyid);
			companyDescribe.setContent(content);
			companyService.addCompanyDesc(companyDescribe);
			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
			return baseEntity;
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("添加相关词汇信息异常");
			e.printStackTrace();
			return baseEntity;
		}
	}

	@RequestMapping(value = "modifyCompanyDesc")
	@ResponseBody
	public BaseEntity modifyCompanyDesc(@RequestParam("id") String id,
			@RequestParam("content") String content,
			@RequestParam("sign") String sign) {
		BaseEntity baseEntity = new BaseEntity();
		try {
			if (StringUtils.isEmpty(id) || StringUtils.isEmpty(sign)
					|| StringUtils.isEmpty(content)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("content", content);
			companyService.updateCompanyDesc(map);
			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
			return baseEntity;
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("修改相关词汇信息异常");
			e.printStackTrace();
			return baseEntity;
		}
	}

	@RequestMapping(value = "delCompanyDesc")
	@ResponseBody
	public BaseEntity delCompanyDesc(@RequestParam("descid") String descid,
			@RequestParam("sign") String sign) {
		BaseEntity baseEntity = new BaseEntity();
		try {
			if (StringUtils.isEmpty(descid) || StringUtils.isEmpty(sign)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}
			companyService.delCompanyDesc(descid);
			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
			return baseEntity;
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("删除相关词汇信息列表异常");
			e.printStackTrace();
			return baseEntity;
		}
	}

	private void saveFile(String newFileName, MultipartFile filedata) {
		String saveFilePath = Global.getUserfilesBaseDir();
		File fileDir = new File(saveFilePath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		try {
			FileOutputStream out = new FileOutputStream(saveFilePath
					+ File.separator + newFileName);
			out.write(filedata.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "searchCompany")
	@ResponseBody
	public CompanysVo searchCompany(@RequestParam("text") String text,
			@RequestParam("token") String token,
			@RequestParam("pagesize") int pagesize,
			@RequestParam("pageindex") int pageindex) {
		
		CompanysVo companysVo = new CompanysVo();
		com.uwca.operation.modules.api.company.entity.vo.CompanysVo.Result result = companysVo.new Result();

		try {
			if (StringUtils.isEmpty(text)||StringUtils.isEmpty(token)) {
				companysVo.setReturncode(1);
				companysVo.setMessage("参数不完整");
				companysVo.setResult(result);
				return companysVo;
			}

			Page<CompanyInfo> page = companyService.searchCompany(pageindex,pagesize,text,TokenTool.getUserid(token));

			result.setPagecount(page.getTotalPage());
			result.setRowcount(page.getList().size());
			result.setList(page.getList());
			companysVo.setResult(result);
			companysVo.setReturncode(0);
			companysVo.setMessage("ok");
			return companysVo;
		} catch (Exception e) {
			companysVo.setReturncode(1);
			companysVo.setMessage("搜寻公司信息异常");
			companysVo.setResult(result);
			e.printStackTrace();
			return companysVo;
		}
	}
}
