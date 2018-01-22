package com.changhong.mybatis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.changhong.mybatis.bean.Page;
import com.changhong.mybatis.constant.CONSTANT;
import com.changhong.mybatis.dao.MessageDao;
import com.changhong.mybatis.entity.Message;

public class AutoReplyService {
	
	MessageDao messageDao = new MessageDao();
	List<Message> list = new ArrayList<Message>();
	
	public String findByCommand(Map<String, Object> parameter) {

		Message message = (Message) parameter.get("message");
		Page page = (Page) parameter.get("page");
		
		list  = messageDao.findList(parameter);
		
		if ((message.command).equals(CONSTANT.COMMAND_HELP)) {		
			list = messageDao.findList(null);
			StringBuilder str = new StringBuilder();
			
			for (int i = 0;i < list.size();i ++) {
				
				if (i == 0) {
					str.append(list.get(i).getCommand());
				}
				str.append(",").append(list.get(i).getCommand());
			}
			return str.toString();	
		}
		if (list != null && list.size() > 0) {
			
			return list.get(0).getContent();
			
		}
		return CONSTANT.COMMAND_NOT_FOUND;
	}

}
