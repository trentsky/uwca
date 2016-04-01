package com.uwca.operation.modules.activity.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.utils.CacheUtils;
import com.uwca.operation.common.utils.StringUtils;
import com.uwca.operation.common.web.BaseController;
import com.uwca.operation.modules.activity.entity.Acti;
import com.uwca.operation.modules.activity.entity.City;
import com.uwca.operation.modules.activity.entity.Platform;
import com.uwca.operation.modules.activity.entity.PropagandaRecord;
import com.uwca.operation.modules.activity.entity.SimpleCityInfo;
import com.uwca.operation.modules.activity.service.ActiService;
import com.uwca.operation.modules.activity.service.CityService;
import com.uwca.operation.modules.activity.service.PlatformService;

/**
 * 运营推广Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/event/activity")
public class ActiController extends BaseController {

	@Autowired
	private ActiService actiService;
	
	@Autowired
	private CityService cityService;
	public static final String PLATFORM_LIST = "platformAllList";
	
	@Autowired
	private PlatformService platformService;
	
	@ModelAttribute
	public Acti get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return actiService.get(id);
		}else{
			return new Acti();
		}
	}    
	
	@RequestMapping(value = {"list", ""})
	public String list(Acti acti, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Acti> page = actiService.findPage(new Page<Acti>(request, response), acti); 
        model.addAttribute("page", page);
		return "modules/activity/actiList";
	}

	@RequiresPermissions("event:acti:view")
	@RequestMapping(value = "form")
	public String form(Acti acti, Model model, @RequestParam(required=false) String id ) {
		if (StringUtils.isNotBlank(id)){
			acti = actiService.get(id);
		}else{
			acti = new Acti();
			model.addAttribute("cities", cityService.findAllProvice());
			Object o = CacheUtils.get(PLATFORM_LIST);
			List<Platform> platList = new ArrayList<Platform>();
			if(null == o){
				platList = platformService.findAllPlatforms();
				CacheUtils.put(PLATFORM_LIST, platList);
			}else{
				platList = (List<Platform>) o;
			}
			List<Platform> iphoneList = new ArrayList<Platform>();
			List<Platform> androidList = new ArrayList<Platform>();
			for(Platform plat : platList){
				if(plat.getPlatformid() == 1){
					iphoneList.add(plat);
				}else if(plat.getPlatformid() == 2){
					androidList.add(plat);
				}
			}
			model.addAttribute("iphone", iphoneList);
			model.addAttribute("android", androidList);
		}
		model.addAttribute("acti", acti);
		return "modules/activity/actiForm";
	}
	
	@RequiresPermissions("event:acti:edit")
	@RequestMapping(value = "save")
	public String save(Acti acti, HttpServletRequest request, HttpServletResponse response, Model model) {
		actiService.save(acti);
		for(String s : acti.getPmlist()){
			for(String ci : acti.getCities()){
				PropagandaRecord p = new PropagandaRecord();
				p.setPropagandaid(acti.getId());
				p.setAreaid(ci);
				p.setAppconfigid(s);
				actiService.savePropRecord(p);
			}
		}
		return list(acti, request, response, model);
	}
	
	@RequiresPermissions("event:acti:view")
	@RequestMapping(value = "getCities")
	@ResponseBody
	public String getCities(String pi){
		List<City> listCity = cityService.findByParentId(pi);
		List<SimpleCityInfo> simpleList = new ArrayList<SimpleCityInfo>();
		for(City c : listCity){
			SimpleCityInfo s = new SimpleCityInfo();
			s.setAreaid(c.getAreaid());
			s.setName(c.getName());
			simpleList.add(s);
		}
		return JSONObject.toJSONString(simpleList);
	}
	
}
