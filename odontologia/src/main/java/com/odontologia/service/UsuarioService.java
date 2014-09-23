package com.odontologia.service;

import com.odontologia.model.Usuario;

public interface UsuarioService {
		
	Usuario getUsuario(String usuario);
	
	boolean login(String usuario, String password);
	
	boolean registrarUsuario(Usuario usuario);

}
