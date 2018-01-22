package com.changhong.mybatis.entity;

import lombok.Data;

/**
 *指令内容实体
 */
@Data
public class CommandContent {

	//id
	public Integer id;
		
	//指令内容
	public String content;
		
	//指令id
	public String commandId;
}
