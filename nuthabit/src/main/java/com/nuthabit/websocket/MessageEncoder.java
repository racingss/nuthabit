package com.nuthabit.websocket;

import javax.websocket.EncodeException;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.nuthabit.model.Message;

public class MessageEncoder implements Encoder.Text<Message> {
	Log log = LogFactory.getLog(this.getClass().getName());
	
	@Override
	public void destroy() {
		log.debug("MessageEncoder destoryed");
	}

	@Override
	public void init(EndpointConfig arg0) {
		log.debug("MessageEncoder inited");
	}

	@Override
	public String encode(Message message) throws EncodeException {
		String jsonStr = JSON.toJSONString(message);
		log.debug(jsonStr);
		return jsonStr;
	}

}
