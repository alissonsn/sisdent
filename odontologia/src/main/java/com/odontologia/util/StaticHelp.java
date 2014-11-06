package com.odontologia.util;

import java.sql.Timestamp;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class StaticHelp {

	public static void errorMessage(String titulo, String mensaje){
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensaje));
	}
	
	public static void correctMessage(String titulo, String mensaje){
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje));
	}
	
	public static HttpSession getSession(){
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
	}
	
	public static String getLoggedUsername(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  session.getAttribute("username").toString();
	}
	
	public static Timestamp getFechaActual(){
		Timestamp fecha;
		java.util.Date date = new java.util.Date();
		long ms = date.getTime();
		fecha = new Timestamp(ms);
		return fecha;
	}

	
}
