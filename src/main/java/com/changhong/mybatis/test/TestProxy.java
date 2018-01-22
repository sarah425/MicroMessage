package com.changhong.mybatis.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.changhong.mybatis.entity.Message;

public class TestProxy implements InvocationHandler{

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//		System.out.println("==proxy==" + proxy + "====");
//		System.out.println("==methon=d==" + method + "====");
//		System.out.println("==args===" + args + "====");
		
		
		List<Object> result= new ArrayList<Object>();
		
		result.add("1");
		result.add("2");
		result.add("3");
		
		return result;
	}

}
