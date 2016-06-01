package com.uwca.operation.modules.cpy.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uwca.operation.common.config.Global;
import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.utils.DateUtils;
import com.uwca.operation.common.web.BaseController;
import com.uwca.operation.modules.cpy.entity.Cpy;
import com.uwca.operation.modules.cpy.service.CpyService;

@Controller
@RequestMapping(value = "${adminPath}/company")
public class CpyController extends BaseController {

	@Autowired
	private CpyService companyService;

	@RequestMapping(value = { "list", "" })
	public String list(Cpy company, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		company.setStarttime(DateUtils.parseDate(request
				.getParameter("starttime")));
		company.setEndtime(DateUtils.parseDate(request.getParameter("endtime")));
		Page<Cpy> page = companyService.findPage(new Page<Cpy>(request,
				response), company);
		model.addAttribute("page", page);
		return "modules/company/companyList";
	}

	@RequestMapping(value = { "getCompanyByid", "" })
	public String getCompanyByid(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Cpy cpy = companyService.getCompanyByid(id);
		model.addAttribute("cpy", cpy);
		return "modules/company/companyInfo";
	}

	@RequestMapping(value = { "getBusinesslicense", "" })
	public void getBusinesslicense(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");
		FileInputStream fis = null;
		OutputStream os = null;
		
		try {
			fis = new FileInputStream(Global.getUserfilesBaseDir()+request.getParameter("businesslicense"));
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 1024];
			while ((count = fis.read(buffer)) != -1)
				os.write(buffer, 0, count);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
				if (fis != null)
					fis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
	}

}
