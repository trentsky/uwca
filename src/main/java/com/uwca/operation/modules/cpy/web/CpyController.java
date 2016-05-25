package com.uwca.operation.modules.cpy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping(value = {"list", ""})
	public String list(Cpy company, HttpServletRequest request, HttpServletResponse response, Model model) {
		company.setStarttime(DateUtils.parseDate(request.getParameter("starttime")));
		company.setEndtime(DateUtils.parseDate(request.getParameter("endtime")));
        Page<Cpy> page = companyService.findPage(new Page<Cpy>(request, response), company); 
        model.addAttribute("page", page);
		return "modules/company/companyList";
	}

}
