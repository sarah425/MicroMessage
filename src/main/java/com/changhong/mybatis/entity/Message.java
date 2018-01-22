package com.changhong.mybatis.entity;

import lombok.Data;

/**
 * 消息实体类
 *
 */
@Data
public class Message {

    /**
     * 主键
     */
	public Integer id;
	/**
     * 命令符
     */
	public String command;
	/**
     * 描述
     */
	public String description;
	/**
     * 内容
     */
	public String content;
	
}
