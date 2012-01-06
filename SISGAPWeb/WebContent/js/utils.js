function pintarError(id, error){
		document.getElementById(id).innerHTML=error;
		document.getElementById(id).className="mensajeError";
		document.getElementById(id).style.display="";		
	}
function limpiarError(id){
		document.getElementById(id).innerHTML='';
		document.getElementById(id).style.display="none";
}
function pintarRequerido(id, error){
	document.getElementById(id).innerHTML=error;
	document.getElementById(id).className="mensajeRequerido";
	document.getElementById(id).style.display="";		
}
function pintarMensaje(tabla,id, error){
	document.getElementById(id).innerHTML=error;
	document.getElementById(id).className="mensajeError";
	document.getElementById(tabla).style.display="";		
}
function limpiarMensaje(tabla,id){
	document.getElementById(id).innerHTML='';
	document.getElementById(tabla).style.display="none";
}
function sonLetras(cadena){
	
	var letra,cont=0;
	for ( var i = 0; i < cadena.length; i++) {

			letra=cadena.charAt(i);
 		if( (letra.keyCode<65 || letra.keyCode>90) && (letra.keyCode<97 || letra.keyCode>122)	)
 		cont++;	
		
	}
	if(cont== cadena.length)
		return true;
	return false;
	
}

function esMinuscula(ch) 
{ 
 var minusuculas;

 minusuculas = "abcdefghijklmnopqrstuvwxyz";

 return (minusuculas.indexOf(ch) != -1);
}  

function esMayuscula(ch) 
{ 
 var mayusculas;
 mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 return (mayusculas.indexOf(ch) != -1);
}  

function esLetra(ch) 
{ 
 return (esMayuscula(ch) || esMinuscula(ch));
} 
function noRetroceso(){	 		
		if(event.keyCode==8)
				return false;
}
function noEscribir(e){
	return false;
}
//onKeyPress
function permitirEscribirLetrasYNumeros(){	 
	 		
	 		if( (event.keyCode<48 || event.keyCode>57)  && 
	 				(event.keyCode<65 || event.keyCode>90) && 
	 				(event.keyCode<97 || event.keyCode>122) &&
	 				event.keyCode!=32)
	 		return false;	
}
function permitirEscribirSoloNumeros(){
	if(event.keyCode<48 || event.keyCode>57)
 		return false;
}

function permitirEscribirLetrasYNumerosSinEspacio(){	 
		
		if( (event.keyCode<48 || event.keyCode>57)  && 
				(event.keyCode<65 || event.keyCode>90) && 
				(event.keyCode<97 || event.keyCode>122))
		return false;	
}

function escuchadorSeleccionar(table,check) {
	  var current =   "odd";
	  var trs = table.getElementsByTagName("tr");
	  checks=document.getElementsByName(check);	 
	  for (var i = 0; i   <trs.length; i++) {
		  if(checks[i].checked==true){
			 trs[i+1].className =  "seleccion";
			// trs[i].style.background="#F7EB6B";
		  }		
		  else			
		  	{ 
		 // if(i%2!=0) trs[i].style.background="#FFFFFF";  else trs[i].style.background="#EFEFEF";			  
		 trs[i+1].className =  current;			 
	  			}	
		  current =   current == "even" ? "odd" : "even";
	  	}
} 


function marcarTodos(lista){
    var tabla=document.getElementsByName(lista);
    if(tabla[0].checked==true)
    val=true;
    else
    	val=false;    	
    for(var i=0;i<tabla.length;i++){
    	tabla[i].checked=val;
    } 
}
function marcarChecks(lista,todos){
    var tabla=document.getElementsByName(lista);
    if(todos.checked==true)
    val=true;
    else
    	val=false;    	
    for(var i=0;i<tabla.length;i++){
    	tabla[i].checked=val;
    } 
}

var newwindow;
function nuevaVentana(url)
{
	newwindow=window.open(url,'name',"height=300,width=900,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1");
	//newwindow=window.open(url,'name','height=200,width=600');
	if (window.focus) {
		newwindow.focus();
		}
}

function nuevaVentanaMaximizada(url)
{
	newwindow=window.open(url,'name',"scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1");
	//newwindow=window.open(url,'name','height=200,width=600');
	if (window.focus) {
		newwindow.focus();
		}
}

function ocultarMostrar(div){
	if(div.style.display== "none")
		mostrar(div);
		else
			ocultar(div);
}
function ocultar(div){	
	
	div.style.display = "none";}
function mostrar(div){  
	div.style.display ="block";	
}
function aviso(mensaje){return confirm(mensaje);}
function avisoEliminar(){ return confirm('¿Está seguro de eliminar?');}
function avisoInsertar(){return confirm('¿Está seguro de insertar?');}
function avisoAgregar(){return confirm('¿Está seguro de agregar?');}
function avisoModificar(){ return confirm('¿Está seguro de modificar?'); }
function avisoGuardar(){ return confirm('¿Está seguro de guardar?'); }
/*
function actualizarFondos(lista,tabla){
    var checks=document.getElementsByName(lista);   
    for(var i=0;i<checks.length;i++){
    	  if(checks[i].checked==true)
    		  tabla[i].tr.style.bgcolor"#FFFF66";
    }
     
    
}
*/ 


/**********************************************
* Obtiene la fila padre(<TR>) del objeto dado  
*************************************************/
function getParentRow(object){
	return getParentElement(object,"TR");
}			
/*********************************************************
** Obtiene el elemento padre mas proximo de tipo tag del objeto dado 
*************************************************/
function getParentElement(object, tag){
	var parentObj=object;
	do{
		parentObj=parentObj.parentElement;	
	}while(parentObj.tagName!=tag);	
	return parentObj;			
}

function inArray(start, el){   
   for (i = start; i < this.length; i++)
   {
   if (el == this[i])
		return i;//return the index of the element  
   }
   return -1;//not found
}

Array.prototype.search = inArray;

/*********************************************************
** TRIM
*************************************************/
function str_trim(){
	var x=this;
	var _start,_end;			
		
	for(i=0;i<x.length && x.charAt(i)==' ';i++);
	_start = i;
	
	if(_start==x.length)return '';
	
	for(i=x.length-1;i>=0 && x.charAt(i)==' ';i--);	
	_end=i+1;
	return x.substring(_start,_end);
}
function str_lPad(n,c){
	_t = c + this;
	while(t.length<n)
		_t = c + t;	
	return (this.substr(0,n));
	
}

/**********************************************
** Obtiene un objeto fecha de una cadena dd/mm/yyyy
*************************************************/
function _toDate(){	
	var dp = this.split("/");	
	if(dp.length==4)	
		return new Date(dp[2],dp[1]-1,dp[0],dp[3],0,0);
	if(dp.length==5)	
		return new Date(dp[2],dp[1]-1,dp[0],dp[3],dp[4],0);
	if(dp.length==6)	
		return new Date(dp[2],dp[1]-1,dp[0],dp[3],dp[4],dp[5]);
	if(dp.length==3)	
		return new Date(dp[2],dp[1]-1,dp[0]);
	return null;
}			

String.prototype.trim=str_trim;
String.prototype.leftPad=str_lPad;
String.prototype.toDate=_toDate;
String.prototype.toDateTime = function(){
	var RE_DATE_TIME=/^(\d{0,2})\/(\d{0,2})\/(\d{0,4})(\s|$)(\d{0,2})?(:|$)(\d{0,2})?$/; 
	var arr = RE_DATE_TIME.exec(this);
	//numero de elementos fuera de rango
	if(arr==null||arr.length<4 || arr.length>8)
		return null;
	//construir fecha:
 	var aDate = new Date(arr[3],arr[2]-1,arr[1]
 		,arr.length>5?arr[5]:0,arr.length>7?arr[7]:0,0,0);	
 	return 	aDate;
} ;
/********************************************************************
*Valida el ingreso de caracteres a un control de texto
*
*********************************************************************/
var DEC_PATTERN=/\d{0,}\.?\d{0,2}/;
var SDEC_PATTERN=/\-?\d{0,}\.?\d{0,2}/;
var DBL_PATTERN=/\d{0,}\.?\d{0,}/;
var INT_PATTERN=/\d{0,}/;
var SINT_PATTERN=/\-?\d{0,}/;
var FEC_PATTERN=/\d{0,2}\/?\d{0,2}\/?\d{0,4}/; 
var MES_ANIO=/\d{0,2}\/?\d{0,4}/; 
var ANIO_MES=/\d{0,4}\/?\d{0,2}/; 
var TEL_PATTERN=/(\d{0,}\-*)*/; 
var ALFA_PATTERN=/[\w\s\.\,\xB0\/\-\xD1\xF1]*/;
var LETTER_PATTERN=/[A-Za-z\s]*/; 
var TIME_PATTERN=/\d{0,2}:?\d{0,2}/; ///^([01]?[0-9]|[2][0-3])(:[0-5][0-9])?$/; 
var DATE_TIME_PATTERN=/^(\d{0,2})\/?(\d{0,2})\/?(\d{0,4})\s?(\d{0,2})?:?(\d{0,2})?$/; 
var MAIL_PATTERN=/^[_a-zA-Z0-9-]+(.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+.)+([a-zA-Z]{2,4})$/;
var IDVAR_PATTERN=/[A-Za-z][A-Za-z0-9_]*/; //identificador de variable
var WHITE_PATTERN = /\s+/;
 

function validKey(format){	
	el = event.srcElement;	
	str1 = el.value;
	rango1 = document.selection.createRange().duplicate();
	while(rango1.expand("character"));

	var text = str1.substr(0,str1.length-rango1.text.length)
		+ String.fromCharCode(event.keyCode) + rango1.text ;
	var arr = format.exec(text);
	return (arr!=null && text==arr[0]);
}

function validKeyTA(format){	
	var text = event.srcElement.value + String.fromCharCode(event.keyCode);
	var arr = format.exec(text);
	return (arr!=null && text==arr[0]);
}
function validWhiteSpaces(obj,format) {
		val=obj.value;
		if(format.test(val)){
			alert("El campo no debe contener espacios en blanco");
			val = val.replace(format, "");
			obj.value=val;
			return false;
		}
		return true;
}	
/********************************************************************
*Valida el texto de un contro de texto como dato fecha
*
*********************************************************************/
function validDate(){
	var text = event.srcElement.value.trim();
	if(text=='')return true;
	var blnRet=isDate(text);
	
	if(!blnRet){
		event.returnValue=false;
		alert("Fecha No V?lida");
		event.srcElement.select();
		event.srcElement.focus();
	}
	return blnRet;
}
function validDateTime(){
	var text = event.srcElement.value.trim();
	if(text=='')return true;
	var blnRet=isDateTime(text);
	
	if(!blnRet){
		event.returnValue=false;
		alert("Fecha No V?lida");
		event.srcElement.select();
		event.srcElement.focus();
	}
	return blnRet;
}
function validTime(){
	var text = event.srcElement.value.trim();
	if(text=='')return true;
	var blnRet=isTime(text);	
	if(!blnRet){
		event.returnValue=false;
		alert("Hora/minuto no v?lido.");
		event.srcElement.select();
		event.srcElement.focus();
	}
	return blnRet;
}

function isDate(text){
	var arDate=text.split("/");	
	var blnRet;
	if(arDate.length==3){
		var aDate = new Date(arDate[2],arDate[1]-1,arDate[0]);
		blnRet = (aDate.getFullYear()==arDate[2]&&aDate.getMonth()==arDate[1]-1&&aDate.getDate()==arDate[0]);
	}
	return (arDate.length==3 && blnRet);
}

function isDateTime(text){
	var RE_DATE_TIME=/^(\d{0,2})\/(\d{0,2})\/(\d{0,4})(\s|$)(\d{0,2})?(:|$)(\d{0,2})?$/; 
	var arr = RE_DATE_TIME.exec(text);
	//numero de elementos fuera de rango
	if(arr==null||arr.length<4 || arr.length>8)
		return false;
	//construir fecha:
 	var aDate = new Date(arr[3],arr[2]-1,arr[1]
 		,arr.length>5?arr[5]:0,arr.length>7?arr[7]:0,0,0);
 	//log.value+=aDate;	
 	return (aDate.getFullYear()==arr[3]
 				&&aDate.getMonth()==arr[2]-1 && aDate.getDate()==arr[1]);		
}
function isTime(text){
	var arDate=text.split(":");	
	var blnRet;
	if(arDate.length!=2) return false;
	return (arDate[0]*1>0 && arDate[0]*1<24 && arDate[1]*1>=0 && arDate[1]*1<60);
}

/********************************************************************
*Compara dos fechas
*********************************************************************/
function date_isBefore(d){	
if(	(ret=this.getFullYear()-d.getFullYear())==0 
			&& (ret=this.getMonth()-d.getMonth())==0
			&& (ret=this.getDate()-d.getDate())==0
			&& (ret=this.getHours()-d.getHours())==0
			&& (ret=this.getMinutes()-d.getMinutes())==0 )
	return (ret<0);
}
Date.prototype.isBefore=date_isBefore;
/********************************************************************
*Compara si dos fechas son iguales
*********************************************************************/
function date_isEquals(d){	
	if(		(ret=this.getFullYear()-d.getFullYear())==0 
			&& (ret=this.getMonth()-d.getMonth())==0
			&& (ret=this.getDate()-d.getDate())==0  )
	return (ret==0) ;
}
Date.prototype.isEquals=date_isEquals;
/**********************************************
** Habilita/Deshabilita un formulario(y todos sus elementos) 
*************************************************/
function enableForm(frm, b){	
	var elems = frm.elements;	
	for(i=0;i<elems.length;i++){		
		if(elems[i].tagName=='INPUT' && (elems[i].type=='image'||elems[i].type=='hidden'))
			continue;		
		elems[i].disabled=!b;		
	}
}			
/**********************************************
** Determina si existe al menos un elemento seleccionado 
*************************************************/
function isAnyChecked(frm, chkFld){	
	var chks = frm.elements(chkFld);
	if(chks==null)return false;
	if(typeof chks.length=='undefined') return (chks.checked);
	for(i=0;i<chks.length;i++){
		if(chks[i].checked)return true;
	}
	return false;
}
function isAnyUnchecked(frm, chkFld){	
	var chks = frm.elements(chkFld);
	if(chks==null)return false;
	if(typeof chks.length=='undefined') return (!chks.checked);
	for(i=0;i<chks.length;i++){
		if(!chks[i].checked)return true;
	}
	return false;
}
/**********************************************
** selecciona todos los elementos de un listbox
*************************************************/
function selectList(olist){
	for(i=0;i<olist.options.length;i++){
		olist.options[i].selected=true;
	}	
}
/**********************************************
** retorna una referencia a un elemento de un formulario html
*************************************************/
function element(elname){
	for(k=0;k<document.forms.length;k++){
		var obj = document.forms[k].elements[elname];
		if(obj!=null) return obj;				
	}	
}
/**********************************************
** retorna si un elemento existe en una lista
*************************************************/
function existsInList(cmb, val){
	var j=0;
	for(j=0;j<cmb.options.length;j++){
		if(val==cmb.options[j].value)
			return true;
	}
	return false;
}

/**********************************************
** Devuelve el primer elemento seleccionado
*************************************************/
function getSelected(frm, chkFld){
	var chks = frm.elements(chkFld);
	if(chks==null)return null;
	if(typeof chks.length=='undefined') return (chks.checked?chks:null);
	
	for(i=0;i<chks.length;i++){
		if(chks[i].checked)return chks[i];
	}
	return null;
}

/**********************************************
** Limpia el texto del usuario solicitante
*************************************************/
function deleteText(object, control){
	if(event.keyCode==46){
		object.value='';
		control.value='';
	}	
}
/**********************************************
** controla tama?o de un TEXTAREA
*************************************************/
var _EVT_PASTE=1;
var _EVT_KEYPRESS=2;

function checkSize(size){
	var _txa = event.srcElement;
	
 if(event.type=='keypress'){
	if(_txa.value.length>=size)event.keyCode=0;	
 }else if(event.type=='paste'){
	var ttp = window.clipboardData.getData("Text");
	if(_txa.value.length+ttp.length>size)
		event.returnValue=false;
	//else {
	//	_txa.value=_txa.value + ttp;
	//} 
 }

}

/**********************************************
** crea una ventana popup
*************************************************/
function _openWindow(url,name, w,h,params){
	var lp=(screen.availWidth-w)/2;
	var tp=(screen.availHeight-h)/2;
	var win = window.open(url,name,"width=" + w + ",height="+ h+ ",status=yes,toolbar=no,menubar=no,"
				+ "left="+lp+",top="+tp + ((params!=null)?','+params:''),true);
	win.focus();	
	return win;	
}
function _openWindowWithScrolls(url,name, w,h,params){
	var lp=(screen.availWidth-w)/2;
	var tp=(screen.availHeight-h)/2;
	var win = window.open(url,name,"width=" + w + ",height="+ h+ ",status=yes,toolbar=no,scrollbars=yes, resizable=yes,menubar=no,"
				+ "left="+lp+",top="+tp + ((params!=null)?','+params:''),true);
	win.focus();	
	return win;	
}
function openWindow(url,w,h){
	var win = window.open(url,"newwin","width=" + w + ",height="+ h+ ",status=yes,toolbar=no,menubar=no",true);
	win.focus();
	return win;	
}
function openNewWindow(url,w,h,params){
	//aseguar que abre una nueva ventana
	var name = url.replace(/\/|\.|\-|/,"");
	return _openWindow(url,name.substr(name.length-10),w,h,params);
}
function openWindowWithScrolls(url,w,h,params){
	return _openWindowWithScrolls(url,"winpopup",w,h,params);
}
/**********************************************
** Valida formularios
*************************************************/
function _findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function validateForm() { //v4.0
  var i,p,q,nm,test,num,min,max,errors='',args=validateForm.arguments;
  for (i=0; i<(args.length-2); i+=3) { test=args[i+2]; val=_findObj(args[i]);
    if (val) { nm=args[i+1]!=''?args[i+1]:val.name; //etiqueta
    if ((val=val.value.trim())!="") {
      if(test.indexOf('isDate')!=-1){
      	if(!isDate(val)) errors+='-'+nm+' debe contener una fecha v?lida con formato (dd/mm/yyyy).\n';
      }else if (test.indexOf('isEmail')!=-1) { p=val.indexOf('@');
        if (p<1 || p==(val.length-1)) errors+='- '+nm+' debe contener una direcci?n de correo.\n';
      }else if (test.indexOf('inLength') != -1) { 
          	len=test.substring(9);
          	if (len!=val.length) errors+='- '+nm+' debe tener '+len+' caracteres.\n';
    	} else if (test!='R') { num = parseFloat(val);
        if (isNaN(val)) errors+='- '+nm+' debe contener un n?mero.\n';
        if (test.indexOf('inRange') != -1) { 
        	p=test.indexOf(':');
          	min=test.substring(8,p); max=test.substring(p+1);
          	if (num<min || max<num) errors+='- '+nm+' debe contener un n?mero entre '+min+' y '+max+'.\n';
    	}
    	
    
    } } else if (test.charAt(0) == 'R') errors += '- '+nm+' es obligatorio.\n'; }
  } if (errors) alert('Han ocurrido los siguientes errores:\n'+errors);
  return (errors == '');
}

/**********************************************
** crea una peticion al  servidor de aplicacion y retorna un string (text, html, o xml)
*************************************************/
function requestXML(funcion, params){
	var url = "xmlService?metodo=" + funcion + "&"+params; 
	var msxml = new ActiveXObject("msxml2.XMLHTTP");
    msxml.Open("GET", url, false);
    msxml.Send("");
    var ret = msxml.responseText;	    
    if(ret.indexOf("OK")!=0){
    	alert(ret);
    	return null;
    }
	return ret.substr(2);
}
function requestHTML(url){
	var msxml = new ActiveXObject("msxml2.XMLHTTP");
    msxml.Open("GET", url, false);
    msxml.Send("");
    var ret = msxml.responseText;	    
	return ret;
}
/**********************************************
** convierte a mayuculas los caracteres ingresados
*************************************************/
function upperCase(disableEnterKey){
 if(disableEnterKey!=null && disableEnterKey && event.keyCode==13){event.keyCode = 0; return 0; }	
 
 if((event.srcElement.tagName=='INPUT' && event.srcElement.type=="text")||event.srcElement.tagName=='TEXTAREA' ){
	 if(event.keyCode>=97 && event.keyCode<=122){
		event.keyCode = event.keyCode - 32;		
	 }else if(event.keyCode==13){
		event.keyCode = 0;
	}
	 return  event.keyCode;
 }
}

/**********************************************
** obtiene la pagina de referencia url.
*************************************************/

function openDoc(url){
	window.navigate(getPath(url));
}
/**********************************************
** obtiene la ruta absoluta del url relativo
*************************************************/
function getPath(url){	
    var baseTag = document.getElementsByTagName("base");
    if(baseTag.length<=0)return url;
    var baseRef = baseTag[0].href;
    var i = baseRef.lastIndexOf("/");
	return baseRef.substr(0,i+1)+url;
}

/**********************************************
** FUNCION QUE SELECCIONA TODOS LOS CHECKBOX
*************************************************/
function checkAll(checkChild,checkParent){
	if(checkParent!=null && checkChild!=null){
		if(checkParent.checked == true){
			if(checkChild.length){
		    	for(var i=0;i<checkChild.length;i++){
		    		if(!checkChild[i].disabled){
		        		checkChild[i].checked = true;
		        	}
		      	}
	      	}else{
	      		if(!checkChild.disabled){
	      			checkChild.checked = true;
	      		}
	      	}
	    }else{
	    	if(checkChild.length){
		      	for(var i=0;i<checkChild.length;i++){
		      		if(!checkChild[i].disabled){
		        		checkChild[i].checked = false;
		        	}
		      	}
	      	}else{
	      		if(!checkChild.disabled){
	      			checkChild.checked = false;
	      		}
	      	}
	    }
    }
}


/**********************************************
** FUNCION QUE SELECCIONA Y DESELECCIONA TODOS LOS CHECKBOX EXCEPTO UNO CON NOMBRE CHECKALL
*************************************************/
	function checkUncheckAll(theElement){
		var form=document.forms[0];
		for(var i=0;i<form.elements.length;i++){
		    if(form.elements[i].type=='checkbox' &&  form.elements[i].name!= 'checkall') {
		    	form.elements[i].checked = theElement.checked;
		    }
		}
	}


/**********************************************
** VALIDA EL NOMBRE DEL ARCHIVO
*************************************************/
function validanombrearchivo(nombre, ext){
  var pattern = "/\\b(^(((\\S)|(\\s))+)(\\."+ext+")$)\\b/gi";
	var rpt = nombre.match(eval(pattern));
	if (!rpt) {
 		return false;
	}	else {
 		return true;
	}
}

/**********************************************
** genera una cadena de parametros a partir de un form
*************************************************/
function getFormParams(frm){
	var _els = frm.elements;
	var _s = "";
	for(i=0;i<_els.length;i++){
		if(_els[i].tagName=="INPUT"&&(_els[i].type=="radio"||_els[i].type=="checkbox")){
			if(!_els[i].checked)continue;
		}
		if(_els[i].name==""||typeof _els[i].name=="undefined")continue;
		
		if(_els[i].tagName == 'SELECT'){
			var array=selectToArray(_els[i]);
			for (var j=0; j<array.length; j++) {
					_s+="&"+_els[i].name + "="+array[j];
			}
			continue;
		}
		
		
		_s+="&"+_els[i].name + "="+_els[i].value;		
	}
	return (_s!="")?_s.substr(1):"";
}

/**********************************************
** utilidades para SELECT
*************************************************/

function hasOptions(obj) {
		if (obj!=null && obj.options!=null) { return true; }
		return false;
	}
function selectToArray(from){
		var array = new Array();
		if (!hasOptions(from)) { return null; }
		for (var i=0; i<from.options.length; i++) {
			var o = from.options[i];
			if (o.selected) {
				array[array.length]=o.value;
			}
		}
		
		return array;
}
 
/*******************************************
*Eliminar de una lista las opciones seleccionadas
*con la tecla supr - onKeydown="eliminakey(this)"
********************************************/
function eliminakey(obj) {
  	if(window.event.keyCode == 46){
	 	for(i=0;i<obj.length;i++)
		{	
			if(obj.options[i].selected)
			 {
				obj.options[i] = null;
				obj.length-1;
				i--;
			 }	
		}
	}
  }
/**********************************************
** Abre una ventana de vista preliminar de un reporte
*************************************************/
function openReport(url,params,w,h){
	var prev = window.showModalDialog("report.preview",new Array('URL',url,params),"dialogWidth:"+screen.width+"px;dialogHeight:"+screen.height+ "px;status:no;center:yes;scroll:no;help:no;resizable:yes;");
}
function showReport(data,w,h){
	var prev = window.showModalDialog("report.preview",new Array('HTML',data),"dialogWidth:"+w+"px;dialogHeight:"+h+ "px;status:no;center:yes;scroll:no;help:no;resizable:yes;");
}
/*************************************
***Manejador de evento onload del window 
*********************************************/
var _onloadcalls = null;
function runOnload(fn){
	if(_onloadcalls==null)
		_onloadcalls = new Array();
	_onloadcalls[_onloadcalls.length] = fn;	
}
window.onload = function(){
	if(_onloadcalls==null) return;
	var i=0;
	for(i=0; i<_onloadcalls.length;i++){
		_onloadcalls[i].call();
	}
};
/*********************************************
** Resizing logic
**********************************************/
var _resizables = null;
function resize(maxwidth, maxbottom){
	for(i=0;i<_resizables.length;i++){
		var obj = document.getElementById(_resizables[i]);
		//alert("resizing.." + _resizables[i] + " is null " + (obj==null));
		if(obj!=null) {obj.style.width=maxwidth;
			//alert("dd" + obj.style.height  + "dd")
			if(obj.altofijo!="true"){
				if(!obj.h)obj.h = (!obj.height)?1:(obj.height.replace(/([^\d]*)(\d*)(\%?)/,"$2")*1.0/100.0);			
				var absTop = obj.offsetTop;
				var topObj = obj.offsetParent;
				while(topObj.tagName!='BODY'){absTop+=topObj.offsetTop; topObj = topObj.offsetParent;}
				obj.style.height = Math.max((maxbottom - absTop -40)*obj.h,50);						
			}
		}	
	}
}
function setResizableElements(){
	_resizables=arguments;
	var resizer = function(){
		frawidth = window.frameElement.offsetWidth-40;
		frabottom = window.frameElement.offsetHeight;				
		//alert(frawidth + ", doleft:"+window.screenLeft+","+window.rightMargin);
		resize(frawidth, frabottom);
	};			
	window.onresize= resizer;
	runOnload(resizer);
}
/*
*Expande o colapsa una fila(TR) y habilita o deshabilita los elementos dentro de dicha fila
*
*/
function expandeColapsa(src, bEnable){
	var trh=getParentRow(src);
	var tr = getParentRow(src).nextSibling;
	if(tr==null) return false;	
	tr.style.display=src.checked?'':'none';
	if(!bEnable)return true;
	//habilitar/deshabilitar campos
	var b = (tr.style.display=='none');
	var i=0;
	var nodos = tr.all;
	for(i=0;i<nodos.length;i++){
		if('INPUT|TEXTAREA|SELECT'.indexOf(nodos[i].tagName)>=0){
			nodos[i].disabled=b;			
		}
	}
	

	nodos=trh.all;
	i=0;
	for(i=0;i<nodos.length;i++){
		if('SELECT'.indexOf(nodos[i].tagName)>=0){
			nodos[i].disabled=b;			
		}
	}
		   
	return true;
} 	
function habilitaOperador(src,bEnable){
	var tr=getParentRow(src);
	var b = (tr.style.display=='none');
	nodos=tr.all;
	for(i=0;i<nodos.length;i++){
		if('SELECT'.indexOf(nodos[i].tagName)>=0){
			nodos[i].disabled=b;			
		}
	}
}


	function buscarSelect(obj,cadena){
		for (var opcombo=0;opcombo < obj.length;opcombo++){ 
			if(obj[opcombo].value.toLowerCase()==cadena.toLowerCase()){ 
				return true;
			} 
	    }
	    return false;
	}
  	
	function seleccionarSelect(obj,cadena){
		for (var opcombo=0;opcombo < obj.length;opcombo++){ 
		
			if(obj[opcombo].value.toLowerCase()==cadena.toLowerCase()){ 
				obj.selectedIndex=opcombo; 
			} 
	    }
	}
	
	
/**********************************************
** Deshabilita la tecla F5 , CTRL, ALT 
** document.onkeydown = detectKey;
*************************************************/

function detectKeyF5() {
	/*if (event.ctrlKey) {
		event.ctrlkey=0;
		return false; 
	} 
	if (event.altKey) {
		event.altKey=0;
		return false;
	}*/
	if(window.event && window.event.keyCode == 116){
		window.event.keyCode = 0;
		return false;
	}
}

/**********************************************
** deshabilita el boton derecho del mouse 
**
** document.oncontextmenu = nocontextmenu;
** document.onmousedown = norightclick;
** document.onmouseup = norightclick;
*************************************************/

function nocontextmenu() {
  event.cancelBubble = true, event.returnValue = false;

  return false;
} 

function norightclick(e) {
  if (window.Event) {
    if (e.which == 2 || e.which == 3) return false;
  }
  else if (event.button == 2 || event.button == 3) {
    event.cancelBubble = true, event.returnValue = false;
    return false;
  }
}
/**********************************************/
function validaFiltros_2(src){
		var tr = getParentRow(src).nextSibling;
		var x=0;
		var y=0;
		var nodos = tr.all;
		var array = new Array();
		var _nivel=1;//nivel de errores,campos vacios a encontrar
		for(x=0;x<nodos.length;x++){
			//'INPUT|TEXTAREA|SELECT'
			if(src.valida.indexOf(nodos[x].tagName)>=0)
			{
				if(nodos[x].value==""){
					if(_nivel==src.nivel){						
						alert("Advertencia! \n Debe seleccionar/ingresar un valor para el filtro");
						if(tblFiltro.style.display=='block'){ 
							 nodos[x].focus();
						}
						return false;
						break;
					}
					_nivel=_nivel + 1; 
					
				}else{
					//si es 1 tons todos los campos deben tener valores
					//si es 2 al menos uno de los campos debe tener valor
					if(!src.err==1)
						break;
					
					//validar fechas
					if(isDateTime(nodos[x].value)){
						array[y]=x;
						y =y + 1;
					}
				}
			}
		}

		if(y>0){
			if(compTwoDates(nodos[array[0]].name,nodos[array[1]].name)){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	
	
	
	function compTwoDates(d,dd){	
		
		var form=document.forms[0];
		//nueva fecha de inicio, fecha hasta
		var flag1=false;
		var flag2=false;
		var fechaBase=form.elements[d].value;
		if(fechaBase!=""){
			if(isDateTime(fechaBase)){
				diaMes = fechaBase.substring(0,2);
				Mes = fechaBase.substring(3,5);
				Year = fechaBase.substring(6,10);
				var fechaIni = new Date(Year, Mes, diaMes);
				fechaIni.setMonth(fechaIni.getMonth()-1);
				flag1=true;

			}

		}else{return false;}
		
		
		fechaBase=form.elements[dd].value;
		if(fechaBase!=""){
			if(isDateTime(fechaBase)){
				diaMes = fechaBase.substring(0,2);
				Mes = fechaBase.substring(3,5);
				Year = fechaBase.substring(6,10);
				var fechaFin = new Date(Year, Mes, diaMes);
				fechaFin.setMonth(fechaFin.getMonth()-1);
				flag2=true;
			}else{alert('Fecha no valida');form.elements[dd].select();form.elements[dd].focus();return false;}
		}else{return false;}
	
		
	  if(flag1 && flag2){
			d1=fechaIni.getFullYear()*10000 + (fechaIni.getMonth()+1)*100 + fechaIni.getDate();		
			d2=fechaFin.getFullYear()*10000 + (fechaFin.getMonth()+1)*100 + fechaFin.getDate();
			if(d2<d1){
				alert("Advertencia! \n La fecha final debe ser mayor a la fecha de inicio");
				form.elements[dd].select();form.elements[dd].focus();
				return false;
			}else{
				return true;
			}
		}
		//return (d1<d2);

		
	}
	   
function isNumber(obj){
   var s = obj.value;
   if (isEmpty(s))
   if (isNumber.arguments.length == 1)
      return false;
   else
      return (isNumber.arguments[1] == true);
   for (i = 0; i < s.length; i++){
      var c = s.charAt(i);
      if (!isDigit(c)) {
         return false;
      }
   }
   return true;
}

function isDigit(c){
  return ((c >= "0") && (c <= "9"));
}

function isEmpty(s){
  return ((s == null) || (s.length == 0));
}
