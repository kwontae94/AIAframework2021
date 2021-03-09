package com.bit.framework.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.dept.controller.AddController;
import com.bit.dept.controller.IndexController;
import com.bit.dept.controller.InsertController;
import com.bit.dept.controller.ListController;
import com.bit.framework.Controller;

public class DispatcherServlet extends HttpServlet{

	//프론트 컨트롤러 역할
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDo(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//post방식은 엔코딩 필요 get방식은 tomcat 서버가 엔코딩한다.
		doDo(req,resp);
	}
	
	
	protected void doDo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri=req.getRequestURI();
		String path=uri.substring(req.getContextPath().length()); //받아온 URI에서 contextpath는 빼줘야한다.
		System.out.println(path);
		
		//handleMapping
		Controller controller=null;
		if(path.equals("/index.bit")) {
			controller=new IndexController();
		}
		else if(path.equals("/list.bit")) {
			controller=new ListController();
		}
		else if(path.equals("/add.bit")) {
			controller=new AddController();
		}
		else if(path.equals("/insert.bit")) {
			controller=new InsertController();
		}
		
		String viewName=controller.execute(req);
		
		
		// viewResolver
		if(viewName.startsWith("redirect:")) {
			resp.sendRedirect(viewName.substring("redirect:".length())
					);
		}else {
			String prefix="/WEB-INF/views/";
			String suffix=".jsp";
			req.getRequestDispatcher(prefix+viewName+suffix)
			.forward(req, resp);
		}
		
	}
	
}
