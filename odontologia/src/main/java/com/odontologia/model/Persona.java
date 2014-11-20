package com.odontologia.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Persona {

	@Id @GeneratedValue @Column(name="idpersona", nullable = false)
	private Integer idPersona;
	
	@Column(name="nombre", length = 50, nullable = true)
	private String nombre;
	
	@Column(name="apellidopaterno", length = 50, nullable = true)
	private String apellidoPaterno;
	
	@Column(name="apellidomaterno", length = 50, nullable = true)
	private String apellidoMaterno;
	
	@Column(name="telefono", length = 50, nullable = true)
	private String telefono;
	
	@Column(name="celular", length = 50, nullable = true)
	private String celular;
	
	@Column(name="direccion", length = 200, nullable = true)
	private String direccion;
	
	@Column(name="correoelectronico", length = 200, nullable = true)
	private String correoElectronico;
	
	@Column(name="fechanacimiento", nullable = true)
	private Date fechaNacimiento;
	
	@Column(name="dni", nullable = true)
	private Integer dni;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idusuario", nullable = true)
	private Usuario personaUsuario;
	
	@OneToOne(mappedBy="odontologoPersona")
	private Odontologo personaOdontologo;
	
	@OneToOne(mappedBy="pacientePersona")
	private Paciente personaPaciente;
	
	@ManyToOne @JoinColumn(name="iddistrito", nullable=false)
	private Distrito personaDistrito;	
	
	@Column(name="urlimagen", nullable=true, length=200)
	private String urlImagen;
	
	public Odontologo getPersonaOdontologo() {
		return personaOdontologo;
	}

	public void setPersonaOdontologo(Odontologo personaOdontologo) {
		this.personaOdontologo = personaOdontologo;
	}

	public Paciente getPersonaPaciente() {
		return personaPaciente;
	}

	public void setPersonaPaciente(Paciente personaPaciente) {
		this.personaPaciente = personaPaciente;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Distrito getPersonaDistrito() {
		return personaDistrito;
	}

	public void setPersonaDistrito(Distrito personaDistrito) {
		this.personaDistrito = personaDistrito;
	}

	public Usuario getPersonaUsuario() {
		return personaUsuario;
	}

	public void setPersonaUsuario(Usuario personaUsuario) {
		this.personaUsuario = personaUsuario;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	
	
	
}
