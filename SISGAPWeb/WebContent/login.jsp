<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage="/error/errorGeneral.jsp"%>	
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="pe.com.mmh.sisgap.comun.constantes.Constantes"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
		<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">				
	
	<title>Mercado Modelo de Huaral - SISGAP</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" />
	<script src="<%=request.getContextPath()%>/js/utils.js" ></script>
	<script src="<%=request.getContextPath()%>/js/utilEquifax.js" ></script>	
	
<title>SISGAP</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/style01.css">
<link rel="stylesheet" type="text/css" media="print" href="css/print01.css">


<script>
function validar(){  
    	
	
    document.getElementById('msgValidarUsuario').innerHTML="";
    document.getElementById('msgValidarClave').innerHTML="";
    
    var error=false;  
	var form = document.loginActionForm;
    
    if(trim(form.txtUserName.value)==''){    
       document.getElementById('msgValidarUsuario').innerHTML='Requerido';          
       error=true;
    }
    if(trim(form.txtPassword.value)==''){            
       document.getElementById('msgValidarClave').innerHTML='Requerido';       
       error=true;
    }        
    form.txtUserName.value=trim(form.txtUserName.value);
    return error;
}
function login(){
    var frm=document.loginActionForm;
	frm.metodo.value='ingresarSistema';	
    if(!validar()){    
	  frm.submit();
	}       
    event.returnValue=false;
}
</script>

</HEAD>
<body topmargin="0" leftmargin="0" style="background-color: #D6E3EE">
<table border="0" cellpadding="0" cellspacing="0" width="775" bgcolor="#FFFFFF">
    <tr>
        <td valign="top">
<!-- ***************** INICIO CABECERA ***************** //-->
<table border="0" cellspacing="0" cellpadding="0" width="769" height="91" bgcolor="#FFFFFF" style="padding-top:5px" class=noprint>
  <tr align="left" valign="top">
    <td rowspan="3" width="229" height="44">
		<img src="imagenes/Logo_Reporte_MMH.png" width="91" height="50" border="0" alt="Home" hspace="10" vspace=3>
    </td>
    <td width="476" height="17" valign="bottom" align="right"></td>
    <td rowspan="2" width="43" height="20"></td>
  </tr>
  <tr align="left" valign="top">
    <td width="740" height="20">
   <table border="0" cellpadding="0" cellspacing="0" width="100%" height="19">
    <tr>
       <td style="font: bold 11px Arial; color:#FFFFFF; background-color:rgb(31,111,176); text-align:center;">Mercado Modelo de Huaral</td>
       <td style="background-color:#FFFFFF; width:20px;"></td>
    </tr>
   </table>
   </td>
  </tr>
  <tr align="left" valign="top">
    <td colspan="2" width="519" height="15" style="background-image: url('imagenes/bg_Menu_Cabecera.jpg')"></td>
  </tr>
  <tr align="left" valign="top"><td colspan="3" width="769" height="1" style="background-color:rgb(255,255,255);"></td></tr>
  <tr align="left" valign="top"><td colspan="3" width="769" height="7" style="background-color:rgb(204,0,0);"></td></tr>
  <tr align="left" valign="top">
     <td colspan="3" width="769" height="24" align="right" style="background-color:rgb(232,244,255);">
   		
     </td>
  </tr>
  <tr align="left" valign="top"><td colspan="3" width="769" height="1" style="background-color:rgb(255,255,255);"></td></tr>
  <tr align="left" valign="top"><td colspan="3" width="769" height="1" style="background-color:rgb(183,183,183);"></td></tr>
</table>


<!-- ***************** FIN CABECERA ***************** //-->
        <table width="100%" border="0" cellpadding="0" cellspacing="2">
            <tr>
                <td colspan="5"><!-- ************************************************************************ //--><!-- *************************** CUERPO INICIO ****************************** //--><!-- ************************************************************************ //-->
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="50%" valign="top" align="center" style="BORDER-RIGHT: #999999 1px dashed"><br>
        <table width="90%"  border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td style="FONT-SIZE: 17px; COLOR: #555555; FONT-FAMILY: Arial, Helvetica, sans-serif; TEXT-ALIGN: right" 
                     >
                  <span class="Estilo2"> SISTEMA DE GESTION ADMINISTRATIVA</span></td>
            </tr>
            <tr >
                <td style="FONT-SIZE: 27px; COLOR: #ff0000; FONT-FAMILY: Arial, Helvetica, sans-serif; TEXT-ALIGN: right" 
                     ><span class="Estilo3">Inicio de sesión</span></td>
            </tr>
        </table>
        <br><center>
        <table width="90%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                <center>
             <html:form action="/login.do" method="post">
              <input type="hidden" name="metodo">    
              <!-- Mensaje Error login -->
                           <logic:notEmpty name="<%=Constantes.EXCEPTION%>">
                                <table border="0">
                                         <tr>			                            
					                            <td align="center" valign="middle" style="border-color: #9faed1;border-width: 1px;border-style: solid;font-family: Arial, Helvetica, sans-serif; font-size: 11px; font-style: normal; line-height: normal; font-weight: bold; font-variant: normal; color: #FF0000; background-color: #FFFFFF">										  
												      Su intento de ingreso no fue exitoso, intentar de nuevo.
												</td>
							              </tr>
						         </table>	
					      </logic:notEmpty>							   
						   <logic:notEmpty name="<%=Constantes.ALERT_MSG%>">		                                                        
			                                          <tr>			                            
								                            <td align="center" valign="middle" style="border-color: #9faed1;border-width: 1px;border-style: solid;font-family: Arial, Helvetica, sans-serif; font-size: 11px; font-style: normal; line-height: normal; font-weight: bold; font-variant: normal; color: #FF0000; background-color: #FFFFFF">										  
															      <bean:write name="<%=Constantes.ALERT_MSG%>"/>
															</td>
										              </tr>
				                             
						   </logic:notEmpty>            
                <table width="95%" border="0" cellpadding="5" cellspacing="4">
                    <tr>
                        <td class="labelLogin" align="left"><label for="txtUsuario"><b>Usuario:</b></label></td>
                        <td align="left">                            
                            <html:text property="txtUserName" styleId="width:110px; font: normal 11px Arial; background-color: #FFFFFF; color: #003366; border: 1px solid #003366; height: 14px; text-transform: uppercase" size="15"></html:text>&nbsp;<span id="msgValidarUsuario" style="color:blue"></span>                           
                        </td>
                    </tr>
                    <tr>
                        <td class="labelLogin" align="left"><label for="txtPassword"><b>Contraseña:</b></label></td>
                        <td align="left">                                                         
                              <html:password property="txtPassword" styleId="width:110px; font: normal 12px Arial; background-color: #FFFFFF; color: #003366; border: 1px solid #003366; height: 14px" size="15"></html:password>&nbsp;<span id="msgValidarClave" style="color:blue"></span>                             
                             &nbsp;&nbsp;&nbsp;&nbsp;
                    </tr>
                    <tr>
                    <td colspan="2" align="center">  
                          <input type="image" src="imagenes/botones/botonEntrar.gif" width="52" height="22" align="middle" style="cursor:pointer;" OnClick="javascript:login();">                                           
                   </td>
                    </tr>
                </table>
                </html:form>
                </center>
                </td>
            </tr>
        </table>
        <br/>
        <table width="90%"  border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td style="FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; TEXT-ALIGN: justify" 
                     >
                Ingrese su código de usuario asignado y la contraseña para iniciar sesión en el sistema.<br><br>
                </td>
            </tr>
            <tr>
                <td style="FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; TEXT-ALIGN: justify" 
                     >
                <br><br><br><br><br>
                </td>
            </tr>
        </table></center> 
        </td>
        <td width="50%" valign="top"><br>
          <br>
          <center>
        </center>
        </td>
    </tr>
</table>

<!-- ************************************************************************ //--><!-- ****************************** CUERPO FIN ****************************** //--><!-- ************************************************************************ //-->
              </td>
            </tr>
        </table>
        </td>
        <td background="imagenes/bg_Sombra_Cuerpo.jpg" width="4" rowspan="3"></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
		   <table width="99%"  border="0" cellpadding="0" cellspacing="0">
            <tr><td class="copyrightEquifax">Mercado Modelo de Huaral - © Derechos Reservados</td></tr>
        </table>
        </td>
    </tr>
    <tr><td colspan="2" background="imagenes/pagebg_bottom2.gif" height="12" ></td></tr>
</table>

<br/>
</body>
</html:html>
