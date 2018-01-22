package com.changhong.mybatis.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.changhong.mybatis.service.AutoReplyService;
import com.changhong.mybatis.service.MessageService;

public class AutoReplyServlet extends HttpServlet{

//	AutoReplyService autoReplyService = new AutoReplyService();
	public MessageService messageService = new MessageService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("content");
		PrintWriter out = resp.getWriter();
		out.write(messageService.findCommand(name, null).getContent());
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	

}
