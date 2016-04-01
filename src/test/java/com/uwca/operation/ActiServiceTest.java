package com.uwca.operation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uwca.operation.modules.activity.entity.Acti;
import com.uwca.operation.modules.activity.service.ActiService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-context*.xml" })
public class ActiServiceTest {

	@Autowired
	ActiService actiService;
	
	@Test
	public void saveActi() {
		Acti a = new Acti();
  /*
  `title` varchar(500) DEFAULT NULL,
  `showtype` int(11) DEFAULT '1' COMMENT '展示类型：1:1次 2:每天一次 3:每次启动app',
  `showcount` int(11) DEFAULT '1' COMMENT '展示次数',
  `location` int(11) DEFAULT NULL COMMENT '展示位置 0推荐1论坛2找车3发现4我',
  `picurl` varchar(100) DEFAULT NULL COMMENT '宣传图片url',
  `targeturl` varchar(100) DEFAULT NULL COMMENT '跳转地址url',
  `startpushtime` datetime NOT NULL,
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `endpushtime` datetime NOT NULL,
  `state` int(11) DEFAULT '1' COMMENT '1:正常 1：无效',
  `edittime` datetime DEFAULT NULL,
  */
		a.setTitle("title");
		a.setShowtype("1");
		a.setLocation("location");
		a.setPicurl("http://");
		a.setStartpushtime("2015");
		a.setState(1);
		a.setEndpushtime("2016");
		
		actiService.save(a);
		
		System.out.println("4@#$%^&*()");
	} 
	
}

