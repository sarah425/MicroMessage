package com.changhong.mybatis.dao;

import java.util.List;

import com.changhong.mybatis.entity.CommandContent;

public interface ICommandContent {
	
	//插入单条数据
	public void insertOne(CommandContent content);
	
	//批量插入数据
	public void insertBatch(List<CommandContent> list);
	
	
}
