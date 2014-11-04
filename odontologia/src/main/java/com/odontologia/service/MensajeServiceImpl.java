package com.odontologia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Cita;
import com.odontologia.model.Mensaje;
import com.odontologia.model.Usuario;

@Service
public class MensajeServiceImpl implements MensajeService {
	
	Session mailSession;
	private String fromUser;
	private String fromUserEmailPassword;
	private String emailHost;
	private String cabecera;
	private String cuerpo;
	Properties emailProperties;
	
	@PersistenceContext
	EntityManager em;	
	
	@Transactional
	public boolean insertarMensaje(Mensaje mensaje) {
		boolean resultado=false;
		try{
			em.persist(mensaje);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;	
		
	}

	@Transactional
	public boolean eliminarMensaje(Mensaje mensaje) {
		boolean resultado=false;
		try{
			Mensaje toRemove = em.getReference(Mensaje.class, mensaje.getIdMensaje());
			em.remove(toRemove);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Mensaje> getMensajesEmisorReceptor(Integer idPersona) {
		List<Mensaje> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT m FROM Mensaje m JOIN m.mensajeUsuarioReceptor mu JOIN mu.usuarioPersona up WHERE up.idPersona=:idPersona");
			q.setParameter("idPersona", idPersona);			
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}
  /*
	@Transactional
	public boolean NotificarCitasUsuarioEstado(Integer idCita, Integer idUsuario, String estado) {

		Cita cit = em.find(Cita.class, idCita);
		Usuario user = em.find(Usuario.class, idUsuario);
		String asunto = "Notificacion de SPADENT se acerca la cita Odontologica: Fecha-"+cit.getHoraInicio()+ " a : "+cit.getCitaEstadoCita().getNombre()+" "
				+ user.getUsuarioPersona().getNombre();        
		String body = "Se le notifica que se su vehiculo esta siendo cambiado al estado: "+cit.getCitaEstadoCita().getNombre()+" <br/>" +
						"Sr. o Sra. : "+user.getUsuarioPersona().getNombre()+" "+user.getUsuarioPersona().getApellidoPaterno()+" "+user.getUsuarioPersona().getApellidoMaterno()+"<br />"+
						"Su Nro. Documento : "+user.getUsuarioPersona().getDni()+"<br />"+
						"Su Vehiculo de Nro. Placa : "+cit.getTitulo()+"<br />"+
						"La Fecha en la que se efectuo esta operacion es : "+cit.getHoraInicio()+"<br />";
		
		
		EnviarMensaje(user.getUsuarioPersona().getCorreoElectronico(), asunto, body);
		return false;
	}
*/	
	
	@Transactional
	public boolean NotificarCitasUsuario(Integer idCita, Integer idUsuario) {

		Cita cit = em.find(Cita.class, idCita);
		Usuario user = em.find(Usuario.class, idUsuario);
		String asunto = "Notificacion de SPADENT que se cambio la cita: Fecha-"+cit.getHoraInicio()+ " a : "+ user.getUsuarioPersona().getNombre();        
		String body = " : <br/>" +
						"Sr. o Sra. : "+user.getUsuarioPersona().getNombre()+" "+user.getUsuarioPersona().getApellidoPaterno()+" "+user.getUsuarioPersona().getApellidoMaterno()+"<br />"+
						"Su Nro. Documento : "+user.getUsuarioPersona().getDni()+"<br />"+
						"El Asunto : "+cit.getTitulo()+"<br />"+
						"La Fecha en la que se efectuo esta operacion es : "+cit.getHoraInicio()+"<br />";
		
		
		EnviarMensaje(user.getUsuarioPersona().getCorreoElectronico(), asunto, body);
		return false;
	}
	
	
	
	@Override
	public void propiedades() {
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", "587");
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		mailSession = Session.getDefaultInstance(emailProperties, null);
		setFromUser("atflorest@gmail.com");
		setFromUserEmailPassword("#eg1pt022");
		setEmailHost("smtp.gmail.com");
	}

	@Override
	public String mensajeEmail() {
		return "Mensaje";
	}

	@Override
	public String cabeceraEmail() {
		return "Asunto";
	}

	@Override
	public void preparaEnviar(String para, String cabecera, String cuerpo) {
		try {
			MimeMessage emailMessage = new MimeMessage(mailSession);
			emailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(para));
			emailMessage.setSubject(cabecera);
			emailMessage.setContent(cuerpo, "text/html");
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(getEmailHost(), getFromUser(),
					getFromUserEmailPassword());
			transport
					.sendMessage(emailMessage, emailMessage.getAllRecipients());
			transport.close();
			System.out.println("Email sent successfully.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public boolean EnviarMensaje(String to) {
		propiedades();
		preparaEnviar(to, cabeceraEmail(), mensajeEmail());
		return true;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getFromUserEmailPassword() {
		return fromUserEmailPassword;
	}

	public void setFromUserEmailPassword(String fromUserEmailPassword) {
		this.fromUserEmailPassword = fromUserEmailPassword;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	@Override
	public boolean EnviarMensaje(String to, String cabecera, String cuerpo) {
		propiedades();
		preparaEnviar(to, cabecera, cuerpo);
		return true;
	}

}
