package com.odontologia.bean;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.service.UsuarioService;
import com.odontologia.util.StaticHelp;


@Controller
public class loginBean {

	private String usuario;
	private String password;
	private Boolean esLogueado;
	
	@Autowired
	UsuarioService usuarioservice;
	
	public String usuarioLogin(){
		esLogueado = usuarioservice.login(usuario, password);						
		if(esLogueado){
			HttpSession session = StaticHelp.getSession();
			session.setAttribute("username", usuario);
			return "index";
		}
		return null;
	}	
	
	public String usuarioLoginMovil(){
		esLogueado = usuarioservice.login(usuario, password);						
		if(esLogueado){
			HttpSession session = StaticHelp.getSession();
			session.setAttribute("username", usuario);
			return "indexMovil";
		}
		return null;
	}
		
	public String cerrarSesion(){
		HttpSession session = StaticHelp.getSession();
		session.invalidate();		
		return "login";
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEsLogueado() {
		return esLogueado;
	}

	public void setEsLogueado(Boolean esLogueado) {
		this.esLogueado = esLogueado;
	}
	
	
	
}
