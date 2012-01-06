<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage="/error/errorGeneral.jsp"%>	
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="pe.com.mmh.sisgap.comun.constantes.Constantes"%>

<html:html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">

<%response.setHeader("Cache-control", "no-cache");
  response.setHeader("Pragma", "no-cache");
  response.setDateHeader("Expires", 0);
%>

<TITLE>Cambio de Contraseña</TITLE>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/style01.css">
<link rel="stylesheet" type="text/css" media="print" href="<%=request.getContextPath()%>/css/print01.css">
<style type="text/css">
<!--
.Estilo2 {
	font-size: 18px;
	font-weight: bold;
}

.Estilo3 {
	font-size: 18px
}

.menuCabeceraActivo {
	FONT-WEIGHT: bold;
	FONT-SIZE: 11px;
	WIDTH: 157px;
	CURSOR: pointer;
	COLOR: #ffffff;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
	BACKGROUND-COLOR: rgb(31, 111, 176);
	TEXT-ALIGN: center
}

.copyrightEquifax {
	FONT-SIZE: 10px;
	COLOR: #ffffff;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
	HEIGHT: 22px;
	BACKGROUND-COLOR: #666666;
	TEXT-ALIGN: center
}
-->
</style>

<script src="<%=request.getContextPath()%>/js/utils.js"></script>
<script src="<%=request.getContextPath()%>/js/utilEquifax.js" ></script>	
<SCRIPT LANGUAGE=javascript>
<!--
function validar(){  
    
    document.getElementById('msgValidarClaveActual').innerHTML="";
    document.getElementById('msgValidarClaveNueva').innerHTML="";
    document.getElementById('msgValidarClaveNueva2').innerHTML="";
    document.getElementById('msgAdv').innerHTML="";
    
  
    var error=false;  
	var form = document.loginActionForm;
	  	    
    if(trim(form.txtPassword.value)==''){    
       document.getElementById('msgValidarClaveActual').innerHTML='Requerido';       
       error=true;
    }
    if(trim(form.txtNuevoPassword.value)==''){            
       document.getElementById('msgValidarClaveNueva').innerHTML='Requerido';                
       error=true;
    }
    
    if(trim(form.txtNuevoPassword2.value)==''){            
        document.getElementById('msgValidarClaveNueva2').innerHTML='Requerido';                
        error=true;
     } 
    
    
    if(trim(form.txtNuevoPassword.value)!='' && trim(form.txtNuevoPassword2.value)!=''){
     if(trim(form.txtNuevoPassword.value)!=trim(form.txtNuevoPassword2.value)){    	
    	document.getElementById('msgAdv').innerHTML='Las contraseñas nuevas deben ser iguales.';     
    	error=true;
      }
   }
    
     if(trim(form.txtPassword.value)!='' && trim(form.txtNuevoPassword.value)!=''){
	    if(trim(form.txtPassword.value)==trim(form.txtNuevoPassword.value)){    	
	    	document.getElementById('msgAdv').innerHTML='La contraseña nueva debe ser diferente de la contraseña actual.';     
	    	error=true;
	    }
    }    
    
    return error;
}

function cambiarPassword(){
    var frm=document.loginActionForm;
	frm.metodo.value='cambiarPassword';		
    if(!validar()){   
	  frm.submit();
	}   
}

function regresar(){
    var frm=document.loginActionForm;
	frm.metodo.value='regresarInicio';		    
	 frm.submit();	   
}

//-->
</SCRIPT>
</HEAD>
<body topmargin="0" leftmargin="0" style="background-color: #D6E3EE">
	<!-- ***  FIN SUB MENU SERVICIO AL CLIENTE  *** //-->
	<table border="0" cellpadding="0" cellspacing="0" width="775"
		height="75%" bgcolor="#FFFFFF">
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="2">
					<tr>
						<td colspan="5">
							<!-- ************************************************************************ //-->
							<!-- *************************** CUERPO INICIO ****************************** //-->
							<!-- ************************************************************************ //-->
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="50%" valign="top" align="center"
										style="border-right-style: dashed; border-right-width: 1px; border-right-color: #999999;"><br>
										<table width="90%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td
													style="font-family: Arial, Helvetica, sans-serif; font-size: 17px; color: #555555; text-align: right">
													<span class="Estilo2">SISGAP</span>
												</td>
											</tr>
											<tr>
												<td
													style="font-family: Arial, Helvetica, sans-serif; font-size: 27px; color: #FF0000; text-align: right"><span
													class="Estilo3">Cambio de contraseña</span>
												</td>
											</tr>
										</table> <br>
									
											<table width="90%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<center>
																  <html:form action="/login.do" method="post">
																  <input type="hidden" name="metodo">
																  
                                                              <logic:notEmpty name="<%=Constantes.ALERT_MSG%>">
		                                                              <table border="0">
									                                          <tr>			                            
														                            <td align="center" valign="middle" style="border-color: #9faed1;border-width: 1px;border-style: solid;font-family: Arial, Helvetica, sans-serif; font-size: 11px; font-style: normal; line-height: normal; font-weight: bold; font-variant: normal; color: #FF0000; background-color: #FFFFFF">										  
																					      <bean:write name="<%=Constantes.ALERT_MSG%>"/>
																					</td>
																              </tr>
										                              </table>
								                              </logic:notEmpty>
								                              
								                              <logic:notEmpty name="<%=Constantes.EXCEPTION%>">
		                                                              <table border="0">
									                                          <tr>			                            
														                            <td align="center" valign="middle" style="border-color: #9faed1;border-width: 1px;border-style: solid;font-family: Arial, Helvetica, sans-serif; font-size: 11px; font-style: normal; line-height: normal; font-weight: bold; font-variant: normal; color: #FF0000; background-color: #FFFFFF">										  
																					      <bean:write name="<%=Constantes.EXCEPTION%>"/>
																					</td>
																              </tr>
										                              </table>
								                              </logic:notEmpty>
								                              
								                              
								                              	
																<TABLE cellSpacing=1 cellPadding=1 border=0 CLASS="Borde">
																	<TR>
																		<TD CLASS="etiqueta">Código de Usuario&nbsp;</TD>
																		<TD CLASS="texto">&nbsp; <INPUT id="txtUsuario" name="txtUsuario" class="Campo" maxlength="20" size="20" value="${sessionScope.User.login}" readonly></TD>
																	</TR>
																	<TR>
																		<TD CLASS="etiqueta">Contraseña Actual</TD>
																		<td>&nbsp;
																		   <html:password  property="txtPassword" styleClass="texto" size="20"></html:password>&nbsp;<span id="msgValidarClaveActual" style="color:blue"></span>
																		</td>
																    </TR>																				
																	<TR>
																		<TD CLASS="etiqueta">Contraseña Nueva</TD>
																		<TD CLASS="texto">&nbsp; <html:password property="txtNuevoPassword" styleClass="texto" size="20"></html:password>&nbsp;<span id="msgValidarClaveNueva" style="color:blue"></span></TD>
																	</TR>
																	<TR>
																		<TD CLASS="etiqueta">Repetir Contraseña Nueva</TD>
																		<TD CLASS="texto">&nbsp; <html:password property="txtNuevoPassword2" styleClass="texto" size="20"></html:password>&nbsp;<span id="msgValidarClaveNueva2" style="color:blue"></span></TD>
																	</TR>
																	<TR>
																		<TD></TD>
																		<TD align=left>
																		   <input type="button"  value="Aceptar" OnClick="javascript:cambiarPassword();"/> 
																		   <input type="button" value="Cancelar" OnClick="javascript:regresar();"/>
																        </TD>
																	</TR>
																	<TR>
																		<TD></TD>
																		<TD align=center>
																		      <span id="msgAdv" style="color:blue"></span>
																        </TD>
																	</TR>
																</TABLE>
															</html:form>

														</center></td>
												</tr>
											</table>
											<table width="90%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td
														style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #000000; text-align: justify;">
														Ingrese su contraseña actual, y luego la nueva contraseña
														y la confirmación de ésta.<br>
													<br></td>
												</tr>
												<tr>
													<td
														style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #000000; text-align: justify">
														<br>
													<br>
													<br>
													<br></td>
												</tr>
											</table>
									</td>
									<td width="50%" valign="top"><br> <br></td>
								</tr>
							</table> <!-- ************************************************************************ //-->
							<!-- ****************************** CUERPO FIN ****************************** //-->
							<!-- ************************************************************************ //-->
						</td>
					</tr>
				</table></td>
			<td background="../images/bg_Sombra_Cuerpo.jpg" width="4" rowspan="3">
		</tr>
		<tr>
			<td colspan="2" align="center">
				<table width="99%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="copyrightEquifax">Infocorp/Equifax  -
							&copy; Derechos Reservados</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td colspan="2" background="../images/pagebg_bottom2.gif" height="12"></td>
		</tr>
	</table>
	<br>
</body>
</html:html>

