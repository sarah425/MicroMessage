package com.changhong.mybatis.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.changhong.mybatis.bean.Page;

/**
 * 分页拦截器
 *
 */
//@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor{

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,SystemMetaObject.
				DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		String id = mappedStatement.getId();
		if (id.matches(".+List$")) {
			//获取原始的sql语句
			BoundSql boundSql = statementHandler.getBoundSql();
			String sql = boundSql.getSql();
			//拼接计算查询数据总数的sql语句
			String countSql = "select count(*) from ("  + sql + ")a";
			System.out.println("====" + countSql + "=====");
			//获取数据库连接
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement preparedStatement = connection.prepareStatement(countSql);
			//获取parameterHandler，为sql语句的参数进行赋值
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			Map<String,Object> parameter = (Map<String, Object>) boundSql.getParameterObject();
			Page page = (Page) parameter.get("page");
			if (rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}
			//拼接分页的sql
			String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getSize();
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		//返回被拦截的方法处，继续执行
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
