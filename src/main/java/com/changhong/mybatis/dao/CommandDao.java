package com.changhong.mybatis.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.context.request.NativeWebRequest;

import com.changhong.mybatis.db.Accessdb;
import com.changhong.mybatis.entity.Command;
import com.changhong.mybatis.entity.CommandContent;
import com.changhong.mybatis.entity.Message;

public class CommandDao {
	Accessdb access = new Accessdb();
	SqlSession session = null;
	
	/**
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Command> findCommand(String name, String description) {
		//创建Message对象，用于向Mybatis中传参
		Command com = new Command();
		com.setName(name);
		com.setDescription(description);
		
		//创建返回的List对象
		List<Command> commandList = new ArrayList<Command>();
		try {
			//获得数据库会话
			session = access.getSqlSession();
			//只能包含一个Object参数，因此传输多个参数时需要构造成为一个对象传入
			commandList = session.selectList("findCommand", com);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return commandList;
	}
	
	
	/**
	 * 单条删除
	 */
	public void deleteOne(int id) {
		
		try {
			//获得数据库会话
			session = access.getSqlSession();
			session.selectList("deleteOne", Integer.valueOf(id));
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
			//回滚事务
			session.rollback();
		} finally {
			//关闭session
			if (session != null) {
				session.close();
			}
		}
		
	}
	
	/**
	 * 单条插入数据
	 */
	public void insertOne(CommandContent content) {
		
		try {
			//获得数据库会话
			session = access.getSqlSession();
			ICommandContent commandContent = session.getMapper(ICommandContent.class);
			commandContent.insertOne(content);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
			//回滚事务
			session.rollback();
		} finally {
			//关闭session
			if (session != null) {
				session.close();
			}
		}
		
	}
	
	/**
	 * 批量插入数据
	 */
	public void insertBatch(List<CommandContent> contentList) {
		try {
			//获得数据库会话
			session = access.getSqlSession();
			ICommandContent commandContent = session.getMapper(ICommandContent.class);
			commandContent.insertBatch(contentList);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
			//回滚事务
			session.rollback();
		} finally {
			//关闭session
			if (session != null) {
				session.close();
			}
		}
		
	}
	
	/**
	 * 批量删除
	 */
	public void deleteBatch(List<Integer> list) {
		
		try {
			//获得数据库会话
			session = access.getSqlSession();
			session.selectList("deleteBatch", list);
			session.commit();
		} catch (IOException e) {
			//回滚事务
			session.rollback();
		} finally {
			//关闭session
			if (session != null) {
				session.close();
			}
		}
		
		
		
	}

}
