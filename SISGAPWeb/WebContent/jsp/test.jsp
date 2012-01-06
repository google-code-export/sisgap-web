<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage="/error/errorGeneral.jsp"%>	
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="org.apache.commons.beanutils.PropertyUtils" %>
<%@ taglib uri="/WEB-INF/displaytag-el.tld"  prefix="display" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilos.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/style01.css">
<link rel="stylesheet" type="text/css" media="print" href="<%=request.getContextPath()%>/css/print01.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/calendar-system.css">

</head>
<body>
<table border="0" width="885" class="tahoma11" cellpadding="3" cellspacing="1">
   		<tr bgcolor="#EFF3F9">
	      <td width=885 align="left" class="titulo">TEST PAGINACION</td>      
	   </tr>
	</table> 


	<fieldset>
		<legend>Búsqueda de conceptos y atributos</legend>
		<table>		
			<tr>
				<td>Código de modelo</td>
				<td><input type="text" size="20" value="S03FLAG" /></td>
				<td>&nbsp; &nbsp;  </td>
				<td>Nombre de concepto</td>
				<td><input type="text" size="20" value="Score Personas Financiero" /></td>
				<td>&nbsp; &nbsp;  </td>
				<td>Nombre de atributo</td>
				<td><input type="text" size="20" /></td>
				<td >&nbsp;<input type="button"
					value="Buscar" /></td>
			</tr>
		</table>
		</fieldset>
<span id="sp1"  class="pagebanner"> 5 registros encontrados, mostrando del 1 al 5. </span> <span class="pagelinks" id="sp2" > [Inicio / Anterior] <strong>1</strong>, <a href="#" title="Go to page 2">2</a>, <a href="#" title="Go to page 3">3</a>, <a href="#" title="Go to page 4">4</a> [ <a href="#">Siguiente</a> / <a href="#">Ultimo</a>]</span>
	
<fieldset>
		<legend>Selección de atributos</legend>
		
		<table  cellpadding="2" class="consultanormal" style="width:80%" cellspacing="1" id="row">
					<thead>			
			<tr class="titulo">
				<th  class="sortable" width="5%" ><a>Sel.</a>	</th>
				<th width="30%"" class="sortable"><a>Nombre </a></th>
				<th   class="sortable"><a>Descripción </a></th>
			</tr>
			</thead>
				

			
			<tr class="odd" >
				<td><input type="checkbox" /></td>
				<td>S03SCORE</td>
				<td>Puntaje Score</td>
			</tr>
			<tr class="even">
				<td><input type="checkbox" /></td>
				<td>S03RGOINI</td>
				<td>Rango inicial</td>
			</tr>
			<tr class="odd" >
				<td><input type="checkbox" /></td>
				<td>S03RGOFIN</td>
				<td>Rango final</td>
			</tr>
			<tr class="even">
				<td><input type="checkbox" /></td>
				<td>S03PROMAL</td>
				<td>Probabilidad de malo</td>
			</tr>
			<tr class="odd" >
				<td><input type="checkbox" /></td>
				<td>S03PERCEN</td>
				<td>	Percentil</td>
			</tr>
						
			
			<tr>
				<td colspan="3" align="center"><input type="button"
					value="Guardar" /></td>
			</tr>
		</table>
	</fieldset>
	
<%-- 
  <display:table  style="width:auto" cellspacing="1" cellpadding="2" export="true" name="requestScope.listaOperaciones" id="row" class="consultanormal" excludedParams="method" requestURI="/consultaCarga.do?method=listar" pagesize="50" defaultsort="1">
	      <display:setProperty name="export.txt" value="false"/> 
	      <display:setProperty name="export.excel.filename" value="ReporteConsultasCarga.xls" /> 
	      <c:if test="${row.usu_crea!=null}">
	     	 <display:column property="prod"		title="Producto"		class="sortable" sortable="true"/>
	          <display:column property="usu_crea"		title="Usuario"		class="sortable" sortable="true"/>            	
		      <display:column property="nm_arch"		title="Nombre del archivo"		class="sortable" sortable="true"/>
		      <display:column property="tm_inicio"		title="Hora de inicio"		class="sortable" sortable="true"/>
		      <display:column property="tm_termino"		title="Hora de termino"		class="sortable" sortable="true"/>
		      <display:column property="duracion"		title="Duración"		class="sortable" sortable="true"/>
		      <display:column property="per"		title="Periodo del archivo"		class="sortable" sortable="true"/>
		      <display:column property="estado_proceso"		title="Estado actual de la Operación"		class="sortable" sortable="true"/>
		      <display:column property="qty_reg_correcto"		title="Registros correctos"		class="sortable" style="text-align:center" sortable="true"/>
		      <display:column property="qty_reg_warning"		title="Registros warning"		class="sortable" style="text-align:center" sortable="true"/>      
		      <display:column property="qty_reg_errado"		title="Registros con error"		class="sortable" style="text-align:center" sortable="true"/>
		      <display:column  media="html" title="Cancelar Carga" class="sortable" sortable="false" style="text-align:left">
		      	<c:if test="${row.id_estado==3}">
		      		<a href="javascript: eliminarCarga('<c:out value="${row.peri}"/>','<c:out value="${row.id_ope}"/>','<c:out value="${row.id_prod}"/>')">Eliminar</a>
		      	</c:if>
	      	</display:column>
		      <display:column sortProperty="error" media="html" title="Archivo de error" class="sortable" sortable="true" style="text-align:left">
		      	<c:if test="${row.qty_reg_errado>0}">
		      		<a href="<c:out value="${row.error}"/>"><c:out value="${row.error}"/>
		      		</a>
		      	</c:if>
		      </display:column>
	      	  <display:column sortProperty="filewarning" media="html" title="Archivo  Warning" class="sortable" sortable="true" style="text-align:left">
	      		<c:if test="${row.qty_reg_warning>0}">
	      			<a href="<c:out value="${row.filewarning}"/>"><c:out value="${row.filewarning}"/>
	      			</a>
	      		</c:if>
	      	 </display:column>
	      	 
      	
       		</c:if>
         </display:table>
         --%>

</body>
</html>