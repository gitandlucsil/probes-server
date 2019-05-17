package br.com.andlucsil.probesserver.report;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class GenerateReport {

	private String fileName;
	private Map<String, Object> params;
	private Connection connection;
	
	public GenerateReport(String fileName, Map<String, Object> params, Connection connection) {
		this.fileName = fileName;
		this.params = params;
		this.connection = connection;
	}
	
	public void generatePdf() throws JRException {
		JasperPrint jasperPrint = JasperFillManager.fillReport(this.fileName+".jasper", this.params, this.connection);
		JasperExportManager.exportReportToPdfFile(jasperPrint,this.fileName + ".pdf");
		System.out.println("Finalizado o relatorio do arquivo: "+this.fileName);
	}
}
