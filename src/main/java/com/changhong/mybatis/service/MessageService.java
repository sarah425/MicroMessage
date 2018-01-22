package com.changhong.mybatis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.jar.Attributes.Name;

import com.changhong.mybatis.bean.Page;
import com.changhong.mybatis.dao.CommandDao;
import com.changhong.mybatis.dao.IMessage;
import com.changhong.mybatis.dao.MessageDao;
import com.changhong.mybatis.entity.Command;
import com.changhong.mybatis.entity.CommandContent;
import com.changhong.mybatis.entity.Message;

public class MessageService {

	public MessageDao messageDao = new MessageDao();
	public CommandDao commandDao = new CommandDao();

	public List<Message> findList(String command, String description, Page page) {

		Map<String, Object> parameter = new HashMap<String, Object>();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);

		parameter.put("message", message);
		parameter.put("page", page);

		List<Message> pageList = messageDao.findList(parameter);

		for (Message message2 : pageList) {

			System.out.println(message2.toString());
		}

		return pageList;

	}

	public CommandContent findCommand(String command, String description) {

		List<Command> commands = commandDao.findCommand(command, description);
		List<CommandContent> commandContents = commands.get(0).getContentList();
		CommandContent commandContent = null;
		if (commandContents != null && commandContents.size() > 0) {
			commandContent = commandContents.get(new Random().nextInt(commandContents.size()));
		}

		return commandContent;

	}

	public void InsertOne(CommandContent commandContent) {

		commandDao.insertOne(commandContent);
	}

	public MessageService() {
		// TODO Auto-generated constructor stub
	}

	public List<Message> findPage(String command, String description, Page page) {

		Map<String, Object> parameter = new HashMap<String, Object>();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);

		int result = messageDao.count(message);

		page.setTotalNumber(result);

		parameter.put("message", message);
		parameter.put("page", page);

		List<Message> pageList = messageDao.findPage(parameter);

		for (Message message2 : pageList) {

			System.out.println(message2.toString());
		}

		return pageList;
	}

}
