package com.changhong.mybatis.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.changhong.mybatis.bean.Page;
import com.changhong.mybatis.entity.Message;
import com.changhong.mybatis.service.MessageService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 生成内容列表页面
 *
 */
public class ListServlet extends HttpServlet {
	
	public MessageService messageService = new MessageService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获得页面参数
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		String currentPage = req.getParameter("currentPage");
		
		//生成分页的page对象
		Page page = new Page();
		
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if(currentPage == null ||  !pattern.matcher(currentPage).matches())  {
			page.setCurrentPage(1);
		}else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}

		//向页面写参数
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		//获得接口的查询返回值
//		req.setAttribute("commandList", messageService.findCommand(name, description));
//		req.setAttribute("messageList", messageService.findPage(name,description,page));
		req.setAttribute("messageList", messageService.findList(command,description,page));
		//进行页面跳转
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
