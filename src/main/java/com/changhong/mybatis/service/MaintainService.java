package com.changhong.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import com.changhong.mybatis.dao.MessageDao;

public class MaintainService {
	
	MessageDao messageDao = new MessageDao();

	/**
	 * 删除单条信息
	 */
	public void deteleOne(int id) {
		
		if (!"".equals(id)) {
			messageDao.deleteOne(Integer.valueOf(id));
		}
		
		
	}
	
	/**
	 * 批量删除
	 */
	public void deteleBatch(String[] ids) {
		List<Integer> list = new ArrayList<Integer>();
		for (String s : ids) {
			list.add(Integer.valueOf(s));
		}	
		messageDao.deleteBatch(list);	
	}
	
}
