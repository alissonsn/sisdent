package com.odontologia.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Diente;
import com.odontologia.model.DienteOdontograma;
import com.odontologia.model.FichaOdontologica;
import com.odontologia.model.Odontograma;
import com.odontologia.model.Odontologo;
import com.odontologia.model.Paciente;
import com.odontologia.model.Persona;
import com.odontologia.model.SituacionDental;
import com.odontologia.model.SuperficieDental;
import com.odontologia.service.DienteOdontogramaService;
import com.odontologia.service.DienteService;
import com.odontologia.service.FichaOdontologicaService;
import com.odontologia.service.ImagenOdontogramaService;
import com.odontologia.service.OdontogramaService;
import com.odontologia.service.OdontologoService;
import com.odontologia.service.PacienteService;
import com.odontologia.service.SituacionDentalService;
import com.odontologia.service.SuperficieDentalService;
import com.odontologia.util.StaticHelp;

@Controller
public class odontogramaBean {

	@Autowired
	OdontologoService odontologoService;
	
	@Autowired
	OdontogramaService odontogramaService;
	
	@Autowired
	FichaOdontologicaService fichaOdontologicaService;
	
	@Autowired
	DienteService dienteService;
	
	@Autowired
	PacienteService pacienteService;
	
	@Autowired
	SuperficieDentalService superficieDentalService;
	
	@Autowired
	SituacionDentalService situacionDentalService;
	
	@Autowired
	DienteOdontogramaService dienteOdontogramaService;
	
	@Autowired
	ImagenOdontogramaService imagenOdontogramaService;
	
	private Odontologo odontologo;
	private FichaOdontologica fichaOdontologica;
	private Paciente paciente;
	
	public List<DienteOdontograma> odontogramaPaciente;
	public List<Paciente> pacientes;
	public List <Odontograma> odontogramasPorPaciente;
	
	//Detalle de Diente
	private DienteOdontograma dienteSeleccionado;
	private List<SituacionDental> situaciones;
	private List<SuperficieDental> superficies;
	private SituacionDental situacionDental;
	private SuperficieDental superficieDental;
	private String detalle;
	
	private long date;
	
	private StreamedContent image;
	
	public odontogramaBean(){		
		paciente = new Paciente();
		fichaOdontologica = new FichaOdontologica();
		odontologo = new Odontologo();
		odontogramaPaciente = new ArrayList<>();
		odontogramasPorPaciente = new ArrayList<>();
		pacientes = new ArrayList<>();
		
		dienteSeleccionado = new DienteOdontograma();
		situacionDental = new SituacionDental();
		superficieDental = new SuperficieDental();
	}
	
	public void crearOdontograma(){
		HttpSession session = StaticHelp.getSession();
		Persona persona = (Persona) session.getAttribute("personaSesion");
		odontologo = odontologoService.buscarPorPersona(persona);		 		 		
		fichaOdontologica = fichaOdontologicaService.fichaPorPaciente(paciente);
		
		Timestamp fecha = StaticHelp.getFechaActual();
		Odontograma odontograma = new Odontograma();
		odontograma.setOdontogramaOdontologo(odontologo);
		odontograma.setOdontogramaFichaOdontologica(fichaOdontologica);		
		odontograma.setFechaActualizacion(fecha);
		odontogramaService.insertarOdontograma(odontograma);	
		
		SituacionDental situacionDental = situacionDentalService.buscarPorId(1);
		SuperficieDental superficieDental = superficieDentalService.buscarPorId(1);
		
		for(int i=1; i<=32; i++){
			DienteOdontograma var = new DienteOdontograma();
			Odontograma last = odontogramaService.lastInsert();			
			Diente diente = dienteService.dientePorId(i);
			var.setUrlImagen(imagenOdontogramaService.getUrlImagen(i, 1, 1));
			var.setDienteOdontogramaDiente(diente);
			var.setDienteOdontogramaSituacionDental(situacionDental);
			var.setDienteOdontogramaSuperficieDental(superficieDental);
			var.setDienteOdontogramaOdontograma(last);
			dienteOdontogramaService.registrarDienteOdontograma(var);
		}				
		
	}
	
	public void cargarOdontogramas(int id){		
		Paciente paciente = pacienteService.buscarPorId(id);
		odontogramasPorPaciente = odontogramaService.getOdontogramasPorPaciente(paciente);
	}
	
	public String cargarOdontogramaEspecifico(int id){
		Odontograma odontograma = odontogramaService.buscarPorId(id);
		odontogramaPaciente = dienteOdontogramaService.getByOdontograma(odontograma);
		return "toOdontograma";
	}

	public String cargaDiente(int id){
		dienteSeleccionado = dienteOdontogramaService.buscarPorId(id);		
		situacionDental = dienteSeleccionado.getDienteOdontogramaSituacionDental();
		superficieDental = dienteSeleccionado.getDienteOdontogramaSuperficieDental();
		detalle = dienteSeleccionado.getDetalle();
		return null;
	}

	public String actualizarDiente(){
		dienteSeleccionado.setDetalle(detalle);
		dienteSeleccionado.setDienteOdontogramaSituacionDental(situacionDental);
		dienteSeleccionado.setDienteOdontogramaSuperficieDental(superficieDental);
		dienteSeleccionado.setUrlImagen(imagenOdontogramaService.getUrlImagen(dienteSeleccionado.getDienteOdontogramaDiente().getIdDiente(), superficieDental.getIdSuperficieDental(), situacionDental.getIdSituacionDental()));
		//+"?cache="+System.currentTimeMillis()
		dienteOdontogramaService.merge(dienteSeleccionado);
		dienteSeleccionado = new DienteOdontograma();
		situacionDental = new SituacionDental();
		superficieDental = new SuperficieDental();
		return "Odontograma";
	}
	
	public void cancelar(){
		dienteSeleccionado = new DienteOdontograma();
		situacionDental = new SituacionDental();
		superficieDental = new SuperficieDental();
	}

	public StreamedContent getImage() throws FileNotFoundException, MalformedURLException {
		FacesContext context = FacesContext.getCurrentInstance();		
		 if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) { 
		        return new DefaultStreamedContent();
		    }
		    else {
		    	String id = context.getExternalContext().getRequestParameterMap().get("dienteImagen");
		    	DienteOdontograma aux = dienteOdontogramaService.buscarPorId(Integer.parseInt(id));		    	
		    	InputStream stream = context.getExternalContext().getResourceAsStream("images/"+aux.getUrlImagen());		    			    			    
				return new DefaultStreamedContent(stream, "image/jpeg");
		    }						
	}
	
	public void setImage(StreamedContent image) {
		this.image = image;
	}
	
	public Odontologo getOdontologo() {
		return odontologo;
	}

	public void setOdontologo(Odontologo odontologo) {
		this.odontologo = odontologo;
	}

	public FichaOdontologica getFichaOdontologica() {
		return fichaOdontologica;
	}

	public void setFichaOdontologica(FichaOdontologica fichaOdontologica) {
		this.fichaOdontologica = fichaOdontologica;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<DienteOdontograma> getOdontogramaPaciente() {
		return odontogramaPaciente;
	}

	public void setOdontogramaPaciente(List<DienteOdontograma> odontogramaPaciente) {
		this.odontogramaPaciente = odontogramaPaciente;
	}

	public List<Paciente> getPacientes() {
		pacientes = pacienteService.getPacientes();
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<Odontograma> getOdontogramasPorPaciente() {		
		return odontogramasPorPaciente;
	}

	public void setOdontogramasPorPaciente(List<Odontograma> odontogramasPorPaciente) {
		this.odontogramasPorPaciente = odontogramasPorPaciente;
	}

	public List<SituacionDental> getSituaciones() {
		situaciones = situacionDentalService.getAll();
		return situaciones;
	}

	public void setSituaciones(List<SituacionDental> situaciones) {
		this.situaciones = situaciones;
	}

	public List<SuperficieDental> getSuperficies() {
		superficies = superficieDentalService.getAll();
		return superficies;
	}

	public void setSuperficies(List<SuperficieDental> superficies) {
		this.superficies = superficies;
	}

	public SituacionDental getSituacionDental() {
		return situacionDental;
	}

	public void setSituacionDental(SituacionDental situacionDental) {
		this.situacionDental = situacionDental;
	}

	public SuperficieDental getSuperficieDental() {
		return superficieDental;
	}

	public void setSuperficieDental(SuperficieDental superficieDental) {
		this.superficieDental = superficieDental;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public DienteOdontograma getDienteSeleccionado() {
		return dienteSeleccionado;
	}

	public void setDienteSeleccionado(DienteOdontograma dienteSeleccionado) {
		this.dienteSeleccionado = dienteSeleccionado;
	}

	public long getDate() {
		return System.currentTimeMillis();
	}

	public void setDate(long date) {
		this.date = date;
	}			
	
	
	
}

