package com.changhong.mybatis.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/**
 *读取mybatis的配置文件
 */
public class Accessdb {

	public SqlSession getSqlSession() throws IOException {
		//通过配置文件获取配置信息
		Reader reader = Resources.getResourceAsReader("com/changhong/mybatis/config/Configuration.xml");
		//通过配置信息获取SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//打开数据库回话session
		SqlSession sqlSession = sessionFactory.openSession();
		
		return sqlSession;
		
	}
}
