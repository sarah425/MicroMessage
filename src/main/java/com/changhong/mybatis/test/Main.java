package com.changhong.mybatis.test;

import java.util.List;

import com.changhong.mybatis.dao.IMessage;

public class Main {

	public static void main(String[] args) {
		
		TestSqlSession testSqlSession = new TestSqlSession();

		IMessage iMessage = testSqlSession.getMapper(IMessage.class);
		
		List<String> result = iMessage.query();
		
		System.out.println(result.toString());
	}

}
