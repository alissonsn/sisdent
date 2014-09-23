package com.odontologia.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.odontologia.util.StaticHelp;

@SuppressWarnings("serial")
public class InvalidateSession extends HttpServlet{
	
	public void init() throws ServletException{
		
		HttpSession session = StaticHelp.getSession();
		if(session!=null){
			session.invalidate();	
		}
				
		
	}

}
