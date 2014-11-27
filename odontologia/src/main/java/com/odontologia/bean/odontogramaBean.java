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
	
	public List<DienteOdontograma> dientesModificados;
	
	//Detalle de Diente
	private DienteOdontograma dienteSeleccionado;
	private List<SituacionDental> situaciones;
	private List<SuperficieDental> superficies;
	private SituacionDental situacionDental;
	private SuperficieDental superficieDental;
	private String detalle;
	
	private long date;	
	private StreamedContent image;	
	private Odontograma odonto;
	
	//Historial del diente
	private Diente dienteSeleccionadoHistorial;
	private List<Diente> allDientes;
	private List<DienteOdontograma> historialSeleccionado;
	
	public odontogramaBean(){		
		paciente = new Paciente();
		fichaOdontologica = new FichaOdontologica();
		odontologo = new Odontologo();
		odontogramaPaciente = new ArrayList<>();
		odontogramasPorPaciente = new ArrayList<>();
		pacientes = new ArrayList<>();
		dienteSeleccionadoHistorial = new Diente();
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
		
		if(odontogramaService.getOdontogramasPorPaciente(paciente).size()==0){
			odontogramaService.insertarOdontograma(odontograma);
			SituacionDental situacionDental = situacionDentalService.buscarPorId(1);
			SuperficieDental superficieDental = superficieDentalService.buscarPorId(1);
			for(int j=1; j<=32; j++){
				DienteOdontograma var = new DienteOdontograma();
				Odontograma last = odontogramaService.lastInsert();			
				Diente diente = dienteService.dientePorId(j);
				var.setUrlImagen(imagenOdontogramaService.getUrlImagen(j, 1, 1));
				var.setDienteOdontogramaDiente(diente);
				var.setEsModificado(false);
				var.setDienteOdontogramaSituacionDental(situacionDental);
				var.setDienteOdontogramaSuperficieDental(superficieDental);
				var.setDienteOdontogramaOdontograma(last);
				dienteOdontogramaService.registrarDienteOdontograma(var);				
			}
			
		}else{
			Odontograma lastOd = odontogramaService.getLastOdontograma(paciente);			
			List<DienteOdontograma> lastDientes = dienteOdontogramaService.getByOdontograma(lastOd);
			odontogramaService.insertarOdontograma(odontograma);
			for(int i=0; i<=31; i++){				
				DienteOdontograma temporal = new DienteOdontograma();
				Odontograma last = odontogramaService.lastInsert();
				temporal.setDetalle(lastDientes.get(i).getDetalle());
				temporal.setEsModificado(false);
				temporal.setDienteOdontogramaDiente(lastDientes.get(i).getDienteOdontogramaDiente());
				temporal.setDienteOdontogramaOdontograma(last);
				temporal.setUrlImagen(lastDientes.get(i).getUrlImagen());
				temporal.setDienteOdontogramaSituacionDental(lastDientes.get(i).getDienteOdontogramaSituacionDental());
				temporal.setDienteOdontogramaSuperficieDental(lastDientes.get(i).getDienteOdontogramaSuperficieDental());
				dienteOdontogramaService.registrarDienteOdontograma(temporal);
			}
			
		}
	}
	
	public void cargarOdontogramas(int id){		
		Paciente paciente = pacienteService.buscarPorId(id);
		odontogramasPorPaciente = odontogramaService.getOdontogramasPorPaciente(paciente);
	}
	
	public String cargarOdontogramaEspecifico(int id){
		Odontograma odontograma = odontogramaService.buscarPorId(id);
		odonto = odontogramaService.buscarPorId(id);
		odontogramaPaciente = dienteOdontogramaService.getByOdontograma(odontograma);
		return "toOdontograma";
	}
	
	public String cargarOdontogramaEspecifico2(int id){
		Odontograma odontograma = odontogramaService.buscarPorId(id);
		odonto = odontogramaService.buscarPorId(id);
		odontogramaPaciente = dienteOdontogramaService.getByOdontograma(odontograma);
		return "odontograma2?faces-redirect=true";
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
		SuperficieDental tempCompleto = superficieDentalService.buscarPorId(1);
		if(situacionDental!=situacionDentalService.buscarPorId(6)){
			dienteSeleccionado.setDienteOdontogramaSuperficieDental(tempCompleto);
			dienteSeleccionado.setUrlImagen(imagenOdontogramaService.getUrlImagen(dienteSeleccionado.getDienteOdontogramaDiente().getIdDiente(), 1, situacionDental.getIdSituacionDental()));
		}else{
			dienteSeleccionado.setDienteOdontogramaSuperficieDental(superficieDental);
			dienteSeleccionado.setUrlImagen(imagenOdontogramaService.getUrlImagen(dienteSeleccionado.getDienteOdontogramaDiente().getIdDiente(), superficieDental.getIdSuperficieDental(), situacionDental.getIdSituacionDental()));
		}		
		dienteSeleccionado.setEsModificado(true);
		dienteSeleccionado.setFechaModificacion(StaticHelp.getFechaActual());
		dienteOdontogramaService.merge(dienteSeleccionado);
		dienteSeleccionado = new DienteOdontograma();
		situacionDental = new SituacionDental();
		superficieDental = new SuperficieDental();
		return "Odontograma";
	}
	
	public String actualizarDiente2(){
		dienteSeleccionado.setDetalle(detalle);
		dienteSeleccionado.setDienteOdontogramaSituacionDental(situacionDental);
		SuperficieDental tempCompleto = superficieDentalService.buscarPorId(1);
		SituacionDental tempCaries = situacionDentalService.buscarPorId(6);
		if(situacionDental.getIdSituacionDental()!= tempCaries.getIdSituacionDental()){
			dienteSeleccionado.setDienteOdontogramaSuperficieDental(tempCompleto);
			dienteSeleccionado.setUrlImagen(imagenOdontogramaService.getUrlImagen(dienteSeleccionado.getDienteOdontogramaDiente().getIdDiente(), 1, situacionDental.getIdSituacionDental()));
		}else{
			dienteSeleccionado.setDienteOdontogramaSuperficieDental(superficieDental);
			dienteSeleccionado.setUrlImagen(imagenOdontogramaService.getUrlImagen(dienteSeleccionado.getDienteOdontogramaDiente().getIdDiente(), superficieDental.getIdSuperficieDental(), situacionDental.getIdSituacionDental()));
		}		
		dienteSeleccionado.setEsModificado(true);
		dienteSeleccionado.setFechaModificacion(StaticHelp.getFechaActual());
		dienteOdontogramaService.merge(dienteSeleccionado);
		dienteSeleccionado = new DienteOdontograma();
		situacionDental = new SituacionDental();
		superficieDental = new SuperficieDental();
		return "odontograma2?faces-redirect=true";
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
	
	public String cargarHistorialDiente(){
		historialSeleccionado = dienteOdontogramaService.getHistorialDiente(dienteSeleccionadoHistorial, paciente);
		return "HistorialDiente";
	}
	
	public List<DienteOdontograma> getDientesModificados() {		
		return dienteOdontogramaService.getModified(odonto);
	}

	public void setDientesModificados(List<DienteOdontograma> dientesModificados) {
		this.dientesModificados = dientesModificados;
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

	public Diente getDienteSeleccionadoHistorial() {
		return dienteSeleccionadoHistorial;
	}

	public void setDienteSeleccionadoHistorial(
			Diente dienteSeleccionadoHistorial) {
		this.dienteSeleccionadoHistorial = dienteSeleccionadoHistorial;
	}

	public List<Diente> getAllDientes() {
		allDientes = dienteOdontogramaService.getDientes();
		return allDientes;
	}

	public void setAllDientes(List<Diente> allDientes) {
		this.allDientes = allDientes;
	}

	public List<DienteOdontograma> getHistorialSeleccionado() {
		return historialSeleccionado;
	}

	public void setHistorialSeleccionado(
			List<DienteOdontograma> historialSeleccionado) {
		this.historialSeleccionado = historialSeleccionado;
	}					
	
	
		
}

