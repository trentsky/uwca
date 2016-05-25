package com.uwca.operation.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.uwca.operation.modules.api.push.entity.po.PushLog;
import com.uwca.operation.modules.api.push.service.PushLogService;
import com.uwca.operation.modules.api.sys.dao.DeviceDao;
import com.uwca.operation.modules.api.sys.entity.po.Device;

@Service
public class MobileSendMessage {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	public static final String apiKey = "oc0yx4ST3dNQcGbXNQUNE2yn";
	public static final String secretKey = "KpRboB9HiE6wm7I9IHtcMLnNGZY0S27W";
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private PushLogService pushLogService;
	
	public void pushMsgToSingleDevice(String fromuserid,String touserid,PushMessage pushMessage){
		BaiduPushClient pushClient = genPushClient();
		Device device = deviceDao.getDeviceByuserid(touserid);
		if (null == device) {
			logger.error("设备信息不存在");
			return;
		}
        try {
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().
                addChannelId(device.getChannelid()).
                addMsgExpires(new Integer(3600)).
                addMessageType(1).
                addMessage(JSON.toJSONString(pushMessage)).
                addDeviceType(3);
            
            PushMsgToSingleDeviceResponse response = pushClient.
                pushMsgToSingleDevice(request);
            
            addPushLog(fromuserid, touserid, pushMessage,1,response.getMsgId());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void PushMsgToAll(PushMessage pushMessage){
		BaiduPushClient pushClient = genPushClient();
        try {
            PushMsgToAllRequest request = new PushMsgToAllRequest()
                    .addMsgExpires(new Integer(3600)).addMessageType(0)
                    .addMessage(JSON.toJSONString(pushMessage)) 
                    .addSendTime(System.currentTimeMillis() / 1000 + 70).
                    addDeviceType(3);
            PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
            addPushLog(null, null, pushMessage, 2, response.getMsgId());
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	private void addPushLog(String fromuserid, String touserid,
			PushMessage pushMessage,int type,String msgid) {
		PushLog pushLog = new PushLog();
		pushLog.setPushtitle(pushMessage.getTitle());
		pushLog.setPushcontent(pushMessage.getDescription());
		pushLog.setFromuserid(fromuserid);
		pushLog.setTouserid(touserid);
		pushLog.setType(1);
		pushLog.setMsgid(msgid);
	}
	

	private BaiduPushClient genPushClient() {
		PushKeyPair pair = new PushKeyPair(apiKey,secretKey);

        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        pushClient.setChannelLogHandler (new YunLogHandler () {
            public void onHandle (YunLogEvent event) {
            	logger.info(event.getMessage());
            }
        });
		return pushClient;
	}
}
