package com.nuthabit.websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.nuthabit.model.Message;

public class MessageDecoder implements Decoder.Text<Message> {
	Log log = LogFactory.getLog(this.getClass().getName());
	
	@Override
	public void destroy() {
		log.debug("MessageDecoder destroyed");
	}

	@Override
	public void init(EndpointConfig arg0) {
		log.debug("MessageDecoder inited");
	}

	@Override
	public Message decode(String str) throws DecodeException {
		Message msg = JSON.parseObject(str, Message.class);
		return msg;
	}

	@Override
	public boolean willDecode(String str) {
		try {
			Message msg = JSON.parseObject(str, Message.class);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
