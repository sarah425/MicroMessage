package com.changhong.mybatis.test;

import java.lang.reflect.Proxy;

public class TestSqlSession {

	
	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<T> type) {	
		
		System.out.println("通过接口的class类型从Map中拿到代理工厂类");
		System.out.println("通过代理工厂类实例化代理类");
		System.out.println("通过代理类返回代理实例");
		
	    return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[] {type},new TestProxy());
	  }

}
