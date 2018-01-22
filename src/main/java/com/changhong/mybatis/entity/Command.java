package com.changhong.mybatis.entity;

import java.util.List;

import lombok.Data;

/**
 *Command实体类型
 */

@Data
public class Command {
	
	//id
	public Integer id;
	
	//指令名称
	public String name;
	
	//指令描述
	public String description;
	
	//指令内容列表
	public List<CommandContent> contentList;

}
