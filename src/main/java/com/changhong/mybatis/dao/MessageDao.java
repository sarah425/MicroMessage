package com.changhong.mybatis.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.logging.jdbc.ConnectionLogger;
import org.apache.ibatis.logging.jdbc.StatementLogger;
import org.apache.ibatis.session.SqlSession;

import com.changhong.mybatis.bean.Page;
import com.changhong.mybatis.db.Accessdb;
import com.changhong.mybatis.entity.Message;

/**
 *Message的查询Dao层
 */


public class MessageDao {

	Accessdb access = new Accessdb();
	SqlSession session = null;
	
	/**
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> findList(Map<String,Object> parameter) {
		//创建Message对象，用于向Mybatis中传参
		Message message = new Message();
		
		//创建返回的List对象
		List<Message> messageList = new ArrayList<Message>();
		try {
			//获得数据库会话
			session = access.getSqlSession();
			//只能包含一个Object参数，因此传输多个参数时需要构造成为一个对象传入
//			messageList = session.selectList("findList", message);
			IMessage iMessage = session.getMapper(IMessage.class);
			System.out.println("===parameter===" + parameter.toString() + "=====");
			messageList = iMessage.findList(parameter);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return messageList;
	}
	
	
	public List<Message> findPage(Map<String, Object> paramter) {
		//创建Message对象，用于向Mybatis中传参
		Message message = new Message();
		
		//创建返回的List对象
		List<Message> messageList = new ArrayList<Message>();
		try {
			//获得数据库会话
			session = access.getSqlSession();
			//只能包含一个Object参数，因此传输多个参数时需要构造成为一个对象传入
//			messageList = session.selectList("findList", message);
			IMessage iMessage = session.getMapper(IMessage.class);
			messageList = iMessage.findPage(paramter);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return messageList;
	}
	
	//统计查询结果的数目
	public int count(Message message) {

		int result = 0;
		//创建返回的List对象
		List<Message> messageList = new ArrayList<Message>();
		try {
			//获得数据库会话
			session = access.getSqlSession();
			//只能包含一个Object参数，因此传输多个参数时需要构造成为一个对象传入
			IMessage iMessage = session.getMapper(IMessage.class);
			result = iMessage.count(message);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return result;
	}
	
	
//	public static void main(String[] args) {
//		MessageDao messageDao = new MessageDao();
//		List<Message> result = messageDao.findList("", "");
//		for (Message message : result) {
//			System.out.println(message.toString());
//		}
//		
//	}
	/**
	 *通过输入的命令或者描述查询列表
	 */
//	public List<Message> findList(String command, String description) {
//		
//		List<Message> messageList = new ArrayList<Message>();
//
//		try {
//			//动态加载mysql驱动,向DriverManager注册一个Driver
//			Class.forName("com.mysql.jdbc.Driver");
//			//通过DriverManager获取连接
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf8", "root",
//						"1991sarah");
//			//创建用于查询的sql语句
//			StringBuilder sql = new StringBuilder("SELECT id,command,description,content FROM message where 1=1");
//			//将判断过的参数进行暂存，在构造statement之后放入sql语句
//			List<String> paramList = new ArrayList<String>();
//			if (command != null && !"".equals(command.trim())) {
//				sql.append(" and command = ?");
//				paramList.add(command);
//
//			}
//			if (description != null && !"".equals(description.trim())) {
//				sql.append(" and description like '%' ? '%'");
//				paramList.add(description);
//
//			}
//
//			//构造预编译的sql语句
//			PreparedStatement pm = conn.prepareStatement(sql.toString());
//			for (int i = 0; i < paramList.size(); i++) {
//					pm.setString(i + 1, paramList.get(i));
//			}
//			//执行sql语句
//			ResultSet rs = pm.executeQuery();
//
//			//处理结果集
//			while (rs.next()) {
//
//				Message message = new Message();
//				message.setId(rs.getInt("id"));
//				message.setCommand(rs.getNString("command"));
//				message.setDescription(rs.getNString("description"));
//				message.setContent(rs.getNString("content"));
//				messageList.add(message);
//			}
//			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		
//		return messageList;
//
//	}
	
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
