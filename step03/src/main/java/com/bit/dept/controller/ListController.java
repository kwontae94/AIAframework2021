package com.bit.dept.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.bit.dept.model.DeptDao;
import com.bit.framework.Controller;

public class ListController implements Controller{

	@Override
	public String execute(HttpServletRequest req) {
		try {
			DeptDao dao=new DeptDao();
			req.setAttribute("list", dao.selectAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "list";
	}

}
