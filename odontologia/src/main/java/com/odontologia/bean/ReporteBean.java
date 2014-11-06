package com.odontologia.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.odontologia.model.Persona;
import com.odontologia.util.StaticHelp;

@SuppressWarnings({ "deprecation" })
public class ReporteBean {
	
	private List<Persona> personas;

	public ReporteBean() {
		personas = new ArrayList<>();
		

	}

	JasperPrint jasperPrint;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init(Integer idPaciente) throws JRException {
		JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(personas);
		Map parametros = new HashMap<>();
		parametros.put("ID_PACIENTE", idPaciente);
		String reportPath = FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/reportes/report1.jasper");
		jasperPrint = JasperFillManager.fillReport(reportPath, parametros);
	}

	public void PDF(ActionEvent actionEvent, Integer idPaciente)
			throws JRException, IOException {
		init(idPaciente);
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=fichaClinica" + idPaciente + ".pdf");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				servletOutputStream);
		System.out.println("DONE");
		FacesContext.getCurrentInstance().responseComplete();

	}
	
	private SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }
	/*
	public void verPDF(ActionEvent actionEvent, Integer idPaciente)
			throws JRException, IOException {
		Session s = 
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION,	s);
		parametros.put("ID_PACIENTE", idPaciente);
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/report1.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros);
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment; filename=jsfReporte"+idPaciente+".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
	}*/

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public StreamedContent getArchivoPDF(Integer idPaciente) throws JRException {
		InputStream inputStream = null;
		try {

			Map parametros = new HashMap<>();
			parametros.put("ID_PACIENTE", idPaciente);
			ByteArrayOutputStream Teste = new ByteArrayOutputStream();

			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(getClass().getClassLoader()
							.getResourceAsStream(
									"/reportes/report1.jasper".trim()));

			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					parametros, new JREmptyDataSource());

			JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();

			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, Teste);

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

			exporter.exportReport();

			inputStream = new ByteArrayInputStream(Teste.toByteArray());
		}

		catch (JRException ex) {

			return new DefaultStreamedContent(inputStream, "application/pdf",
					"nombre_archivo");

		}
		return null;
	}

	public void DOCX(ActionEvent actionEvent, Integer idPaciente)
			throws JRException, IOException {
		init(idPaciente);
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=report.docx");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JRDocxExporter docxExporter = new JRDocxExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void XLSX(ActionEvent actionEvent, Integer idPaciente)
			throws JRException, IOException {
		init(idPaciente);
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=report.xlsx");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JRXlsxExporter docxExporter = new JRXlsxExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void ODT(ActionEvent actionEvent, Integer idPaciente)
			throws JRException, IOException {
		init(idPaciente);
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=report.odt");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JROdtExporter docxExporter = new JROdtExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void PPT(ActionEvent actionEvent, Integer idPaciente)
			throws JRException, IOException {
		init(idPaciente);
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=report.pptx");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JRPptxExporter docxExporter = new JRPptxExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
		FacesContext.getCurrentInstance().responseComplete();
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

}
