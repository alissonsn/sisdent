package com.odontologia.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter{

	public AuthenticationFilter(){		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try{
			HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            res.setHeader("Pragma", "no-cache");
            res.setDateHeader("Expires", 0);
            HttpSession ses = req.getSession(false);            
            String reqURI = req.getRequestURI();
            
            if(reqURI.indexOf("/login.jsf")>=0 || reqURI.indexOf("/login.xhtml")>=0 || (ses!=null && ses.getAttribute("username")!=null)
            		|| reqURI.contains("javax.faces.resource") || reqURI.indexOf("/public/") >= 0){
            	chain.doFilter(request, response);            	
            }
            else{
            	res.sendRedirect(req.getContextPath()+"/login.jsf");              	
            }
            
		}
		catch(Throwable t){
			System.out.print("ERROR: "+t.getMessage());
		}
		
	}

	
	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException { 		
	}
	

}
