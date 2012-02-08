package pe.com.mmh.sisgap.comun.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Servlet implementation class ReportsServlet
 */
@WebServlet("/ReportsServlet")
public class ReportsServlet extends HttpServlet {
	
	@Resource(mappedName="java:/jdbc/sisgapDS")
	private DataSource dataSource;
	
	private static final long serialVersionUID = 1L;
	
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ruta = "";
		HashMap<String, String> parametros = new HashMap<String, String>();		
		String reporte = request.getParameter("reporte");
		
		if(reporte!=null){
			
			if(reporte.equals("REPORTE_SOCIO")){
				ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Reporte de Socios.jrxml");
			}else if(reporte.equals("REPORTE_ITEMSCOB")){
				ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Reporte Item de Cobranza.jrxml");
			}else if(reporte.equals("REPORTE_DOCUMENTOS")){
				ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Reporte de Documentos.jrxml");
			}else if(reporte.equals("REPORTE_DOCUMENTO_DETALLE")){
				ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Documento por Detalle.jrxml");
				String nroDocumento = request.getParameter("nroDoc");
				System.out.println("nroDoc : "+nroDocumento);
				parametros.put("P_NRO_DOCUMENTO", nroDocumento);
			}
			generateReport(request, response, ruta, parametros);
		}		
	}
	
	@SuppressWarnings("unused")
	private void generateReport(HttpServletRequest request,
			HttpServletResponse response,String ruta,HashMap<String, String> parametros) throws IOException{
		
		JasperReport masterReport = null;
		ServletOutputStream servletOutputStream = response.getOutputStream();

		byte[] bytes = null;

		try {
			Connection cnn = getConnection();
			masterReport =  JasperCompileManager.compileReport(ruta);//(JasperReport) JRLoader.loadObject(master);
			bytes = JasperRunManager.runReportToPdf(masterReport,parametros, cnn);

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);

			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
			
		} catch (JRException e) {
			e.printStackTrace();
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			response.setContentType("text/plain");
			response.getOutputStream().print(stringWriter.toString());
		}

	}
	
	private Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
