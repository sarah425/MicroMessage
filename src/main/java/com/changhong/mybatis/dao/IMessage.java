package com.changhong.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.changhong.mybatis.entity.Message;

public interface IMessage {

	//根据条件查询所有数据
	public List<Message> findList(Map<String, Object> parameter);
	
	//根据分页条件查询数据
	public List<Message> findPage(Map<String, Object> parameter);
	
	//根据统计查询结果的数量
	public int count(Message message);
	
	public List<String> query();
	
}
