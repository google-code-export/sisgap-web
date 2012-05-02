package pe.com.mmh.sisgap.comun.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.jfree.chart.plot.CategoryPlot;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

//Funcion EJB
import pe.com.mmh.sisgap.administracion.action.FacturacionAction;
import pe.com.mmh.sisgap.administracion.ejb.SisasFacadeLocal;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.transforms.decorators.ResultsDecorator;
import pe.com.mmh.sisgap.transforms.decorators.ResultsDecoratorHTML;
import pe.com.mmh.sisgap.transforms.decorators.ResultsDecoratorPrinter;
import pe.com.mmh.sisgap.transforms.decorators.ResultsDecoratorPrinterImpl;


//Imports de prueba para impresion directamente a la impresora...
//----- Inicio -----
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
//----- Fin -----

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
		String provider = request.getParameter("provider");
		byte[] bytes = null;
		try {
				if(reporte!=null){
					
					if(reporte.equals("REPORTE_SOCIO")){
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Reporte de Socios.jrxml");
					}else if(reporte.equals("REPORTE_SISAS")){
						String periodo = request.getParameter("periodo");
						Integer codigo = new Integer(request.getParameter("codigo"));
						
						SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);
						ResultSet rs = facadeLocal.getTempSisa(periodo, codigo);
				        Document document = new Document(PageSize.A4.rotate());
				        ByteArrayOutputStream baos = new ByteArrayOutputStream();
				        PdfWriter.getInstance(document, baos);
				        document.open();
						ResultsDecoratorPrinter printer = new ResultsDecoratorPrinterImpl();
						ResultsDecorator decorator = new ResultsDecoratorHTML(printer);
						decorator.setTableDefination("<table border='1' style='font-size:13px;'>");
						decorator.write(rs,"3%","20%","8%","8%","8%");
						
				        String snippet = "<center><h3>ASOCIACION DE COMERCIANTES DEL MERCADO MODELO DE HUARAL</h3></center>";
				        	   snippet +="<h3>PADRON DE ASOCIADOS</h3>";
				        	   snippet +=printer.toString();
				        	   
			   	        HashMap<String,Object> map = new HashMap<String, Object>();
				        map.put(HTMLWorker.FONT_PROVIDER, new FontProvider() {
							
							@Override
							public boolean isRegistered(String arg0) {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public Font getFont(String fontname,
					                String encoding, boolean embedded, float size,
					                int style, BaseColor color) {
								// TODO Auto-generated method stub
								return new Font(FontFamily.UNDEFINED, 7, style, color);
							}
						});
				        	   
				        	   
						List<Element> objects = HTMLWorker.parseToList(new StringReader(snippet), null, map);
						for (Element element : objects){document.add(element);}
						
						document.close();
						bytes = baos.toByteArray();	
					}else if(reporte.equals("REPORTE_ITEMSCOB")){
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Reporte Item de Cobranza.jrxml");
					}else if(reporte.equals("REPORTE_DOCUMENTOS")){
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Reporte de Documentos.jrxml");
					}else if(reporte.equals("REPORTE_DOCUMENTO_DETALLE")){
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Documento por Detalle.jrxml");
						String nrodoc = request.getParameter("nrodoc");
						parametros.put("P_NRO_DOCUMENTO", nrodoc);
						String nroDocumento = request.getParameter("nroDoc");
						System.out.println("[REPORTE_DOCUMENTO_DETALLE]Parametro nroDoc : "+nrodoc);
						parametros.put("P_NRO_DOCUMENTO", nroDocumento);
						System.out.println("Ruta del reporte : "+ruta); 
						//Debe Actualizar campo impreso en la tabla factura
						//impresaFactura(nroDocumento, 0.0);
						
						/*if (nroDocumento!=null || nrodoc!=null){
							PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
							DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							//DocPrintJob docPrintJob = printService.createPrintJob();
						}*/
						
						/*PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
						DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
						DocPrintJob docPrintJob = printService.createPrintJob(); 
						String string="Texo que se imprime\n";
						string+="en la impresora predeterminada\n";
						string+="fin del ejemplo\n"; 
						Doc doc=new SimpleDoc(string.getBytes(),flavor,null); 
						try {
							docPrintJob.print(doc, null);
							System.out.println("imprimiendo...");
						}
						catch (PrintException e) {
							System.out.println("Error al imprimir: "+e.getMessage());
						} 
						System.out.println("FIN DE IMPRESION");*/
						
						
					}else if(reporte.equals("REPORTE_DOCUMENTO_X_GRUPO")){
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Reporte de Documentos Filtro x grupo.jrxml");
					}else if(reporte.equals("REPORTE_DOCUMENTOS_FILTRO")){
						String tipDoc = request.getParameter("tipDoc");
						String estDoc = request.getParameter("estDoc");
						System.out.println("[REPORTE_DOCUMENTOS_FILTRO]Parametro tipDoc : "+tipDoc);
						if(tipDoc.equals("T")){
							tipDoc = "%%";
						}
						
						if(estDoc.equals("T")){
							estDoc = "%%";
							
						}else if(estDoc.equals("P")){
							estDoc = "1";
						}else if(estDoc.equals("C")){
							estDoc = "2";
						}else if(estDoc.equals("A")){
							estDoc = "3";
						}
						parametros.put("P_TIPO_DOCUMENTOS", tipDoc);
						parametros.put("P_ESTADO", estDoc);
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Reporte de Documentos Filtro.jrxml");
					}else if(reporte.equals("LISTADO_RECIBO_LUZ")){
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Listado de Recibos de Luz.jrxml");
					}else if(reporte.equals("RECIBO_LUZ")){
						String codRec = request.getParameter("codRec");
						String codSoc = request.getParameter("codSoc");
						System.out.println("[RECIBO_LUZ]Parámetro Recibo : " + codRec + " Parámetro Socio : " + codSoc);
						parametros.put("P_CODIGO_RECIBO", codRec);
						parametros.put("P_CODIGO_SOCIOS", codSoc);
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Recibo de Luz.jrxml");
					}else if(reporte.equals("RECIBO_LUZ_DOBLE")){
						String codRec = request.getParameter("codRec");
						String codSoc = request.getParameter("codSoc");
						System.out.println("[RECIBO_LUZ_DOBLE]Parámetro Recibo : " + codRec + " Parámetro Socio : " + codSoc);
						parametros.put("P_CODIGO_RECIBO", codRec);
						parametros.put("P_CODIGO_SOCIOS", codSoc);
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Recibo de Luz Doble.jrxml");
					}else if(reporte.equals("RECIBO_LUZ_DOBLE_A4")){
						String codRec = request.getParameter("codRec");
						String codSoc = request.getParameter("codSoc");
						System.out.println("[RECIBO_LUZ_DOBLE_A4]Parámetro Recibo : " + codRec + " Parámetro Socio : " + codSoc);
						parametros.put("P_CODIGO_RECIBO", codRec);
						parametros.put("P_CODIGO_SOCIOS", codSoc);
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Recibo de Luz Doble A4.jrxml");
					}else if (reporte.equals("REPORTE_DOCUMENTOS_FILTRO_ITEM")){
						String itmCob = request.getParameter("itmCob");
						String tipDoc = request.getParameter("tipDoc");
						String estDoc = request.getParameter("estDoc");
						String estCan = request.getParameter("estCan");
						System.out.println("[REPORTE_DOCUMENTOS_FILTRO_ITEM]Parametro itmCob : "+itmCob);
						System.out.println("[REPORTE_DOCUMENTOS_FILTRO_ITEM]Parametro tipDoc : "+tipDoc);
						System.out.println("[REPORTE_DOCUMENTOS_FILTRO_ITEM]Parametro estDoc : "+estDoc);
						System.out.println("[REPORTE_DOCUMENTOS_FILTRO_ITEM]Parametro estDoc : "+estCan);
						if (estCan.equals("S")) {
							if(tipDoc.equals("T")){
								tipDoc = "%%";
							}
							
							if(estDoc.equals("T")){
								estDoc = "%%";
								
							}else if(estDoc.equals("P")){
								estDoc = "1";
							}else if(estDoc.equals("C")){
								estDoc = "2";
							}else if(estDoc.equals("A")){
								estDoc = "3";
							}
							parametros.put("P_TIPO_DOCUMENTOS", tipDoc);
							parametros.put("P_ESTADO", estDoc);
							parametros.put("P_CODIGO_COBRANZA", itmCob);
							
							ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Reporte de Documentos Filtro Item.jrxml");
						} else {
							ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Reporte de Documentos Filtro Item Impago.jrxml");	
						}
						
					}else if (reporte.equals("LISTADO_RECIBOS_SOCIOS")){				
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Reporte General de Recibos Luz.jrxml");
						
						String codRec = request.getParameter("codRec");
						parametros.put("P_CODIGO_RECIBO", codRec);
						
					}else if (reporte.equals("LISTADO_SISAS")){				
						ruta = getServletConfig().getServletContext().getRealPath("/WEB-INF/reportes/Listado de Sisas.jrxml");
					}
					
					if (provider == null) {
						generateReport(request, response, ruta, parametros);
					} else {
						generateReportOther(request, response, ruta, bytes);
					}
				}	
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
			/*if (ruta.equals("C:\\tools\\jboss7\\standalone\\deployments\\SISGAP.ear\\SISGAPWeb.war\\WEB-INF\\reportes\\Documento por Detalle.jrxml")){
				JasperPrint	jasperPrint=JasperFillManager.fillReport(masterReport,parametros,cnn); 
				JasperPrintManager.printReport(jasperPrint, true);  
			} else {*/
				masterReport =  JasperCompileManager.compileReport(ruta);//(JasperReport) JRLoader.loadObject(master);
				
				bytes = JasperRunManager.runReportToPdf(masterReport,parametros, cnn);
				
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);

			//}
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
	
	@SuppressWarnings("unused")
	private void generateReportOther(HttpServletRequest request,
			HttpServletResponse response,String ruta,byte[] bytes) throws IOException{
		
		JasperReport masterReport = null;
		ServletOutputStream servletOutputStream = response.getOutputStream();

		try {
//			Connection cnn = getConnection();
//			masterReport =  JasperCompileManager.compileReport(ruta);//(JasperReport) JRLoader.loadObject(master);
//			bytes = JasperRunManager.runReportToPdf(masterReport,parametros, cnn);

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);

			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
			
		} catch (Exception e) {
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
	
	public Object lookup(String name) throws NamingException{
		
		Context context = null;
		
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return context.lookup(name);
	}

}
