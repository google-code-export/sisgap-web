// utilpsg.js

// newFunction
function setVisiblePorId(idObjeto) 
{
	document.getElementById(idObjeto).style.display="";
}

function convertStringDDMMYYYYtoDate(cadena)
{
	var resultado=null;
	if (cadena.length==10)
	{
		resultado = new Date();  
		var arrayDato = cadena.split("/");
		resultado.setFullYear(Number(arrayDato[2]));
		resultado.setMonth(Number(arrayDato[1])-1);
		resultado.setDate(Number(arrayDato[0]));
	}
	return resultado;
}



function isVisiblePorId(idObjeto) 
{
	if (document.getElementById(idObjeto).style.display=="none")
	{
		return false;
	}
	else
	{
		return true;
	}
}

function setInvisiblePorId(idObjeto) 
{
	document.getElementById(idObjeto).style.display="none";
}
function alternaVisibilidadPorId(idObjeto) 
{
	if (document.getElementById(idObjeto).style.display=="none")
	{
		document.getElementById(idObjeto).style.display="";
	}
}
function getValue(form,table,campo,fila)
{	
	return document.getElementById(form+":"+table+":"+campo+":"+fila).value;
}

function setValueHiddenToText(hid,txt)
{	
	document.getElementById(txt).value=document.getElementById(hid).value;
}

function habilitarPorId(idObjeto,styleClass) 
{
	document.getElementById(idObjeto).disabled="";
	if (styleClass!='')
		document.getElementById(idObjeto).className=styleClass;
}
function deshabilitarPorId(idObjeto,styleClass) 
{
	document.getElementById(idObjeto).disabled="true";
	if (styleClass!='')
		document.getElementById(idObjeto).className=styleClass;
}

function setClassNamePorId(idObjeto,styleClass) 
{
	if (styleClass!='')
		document.getElementById(idObjeto).className=styleClass;
}


function habilitarPorName(name,styleClass) 
{
	document.getElementsByName(name)[0].disabled="";
	if (styleClass!='')
		document.getElementsByName(name)[0].className=styleClass;
}
function deshabilitarPorName(name,styleClass) 
{
	document.getElementsByName(name)[0].disabled="true";
	if (styleClass!='')
		document.getElementsByName(name)[0].className=styleClass;
}


function isHabilitadoPorId(idObjeto) 
{
	if (document.getElementById(idObjeto).disabled=='')
	{
		return true;
	}
	else
	{
		return false;
	}
}
function existeElementoPorId(id)
{
	if (document.getElementById(id)!=null && document.getElementById(id)!='none')
	{
		return true;
	}
	else
	{
		return false;
	}
}


	/**
	 * Verifica si existe la opcion "valor" en la listaDestino, y la selecciona, devuelve true si es exitoso,
	 * false en caso contrario
	 * @author mvargas
	 * @param listaDestino : control destino (select)
	 * @param valor : valor a buscar
	 * @return
	 */
	function seleccionaOpcion(listaDestino,valor) 
	{
		var longitud = listaDestino.options.length;
		var i = 0;
	
		while((i < longitud)) 
		{
			if(listaDestino.options[i].value == valor) 
			{	
				listaDestino.options[i].selected='true';
				return true;
			}
			i++;
		}
		return false;
	}
	
	function getValuePorId(id)
	{
		return document.getElementById(id).value;
	}
	function setValuePorId(id,valor)
	{
		document.getElementById(id).value=valor;
	}
	
	function setHtmlPorId(id,valor)
	{
		//alert(document.getElementById(id).innerHTML);
		document.getElementById(id).innerHTML=valor;
	}
	function getHtmlPorId(id)
	{
		return document.getElementById(id).innerHTML;
	}
	
	function getCheckedPorName(name)
	{
		if (document.getElementsByName(name)[0].checked!='')
			return true;
		else
			return false;
	}
	
	function setCheckedPorName(name,valor)
	{
		if (valor)
		{
			document.getElementsByName(name)[0].checked=true;
		}
		else
		{
			document.getElementsByName(name)[0].checked='';
		}
	}
	
	function setCheckedPorId(id,valor)
	{
		if (valor)
		{
			document.getElementById(id).checked=true;
		}
		else
		{
			document.getElementById(id).checked='';
		}
	}
	
	function getCheckedPorId(id)
	{
		if (document.getElementById(id).checked!='')
			return true;
		else
			return false;
	}

	function getValuePorName(name)
	{
		return document.getElementsByName(name)[0].value;
	}
	function setValuePorName(name,valor)
	{
		document.getElementsByName(name)[0].value=valor;
	}

	function setVisiblePorName(name) 
	{
		document.getElementsByName(name)[0].style.display="";
	}
	function setInvisiblePorName(name) 
	{
		document.getElementsByName(name)[0].style.display="none";
	}
	
	
	
	//validamos que la tecla presionada este dentro de la mascara especificada
	function validarKeyPorMascara(evento,mascara)
	{
		var keynum;
		var keychar;
		var exp;
		
		if(window.event) // IE
		{
      keynum = evento.keyCode;
		}
		else if(evento.which) // Netscape/Firefox/Opera
		{
      keynum = evento.which;
		}
		keychar = String.fromCharCode(keynum);
		
	  if (keynum==8 || keynum==undefined)
	     return true;
		
		var exp = new RegExp(mascara);
		return exp.test(keychar);
	}
	function validacionRequerido(valor)
	{
		var temp = trim(valor);
		if (temp=='')
		{
			return false;
		}
		return true;
	}
	function validacionMascara(valor,mascara)
	{
		var exp = new RegExp(mascara);
	    if(exp.test(valor))
	    {
	      var arrayTemp = exp.exec(valor);
	      if(arrayTemp[0].length==valor.length) 
	      {
	        return true;
	      }
	      else
	      {
	        return false;
	      }
	    }
	    else
	    {
	      return false;
	    }
	}
	
	
	function validarKeyPorCantidad(evento,valor,cantidad)
	{
		var keynum;
		var keychar;
		var exp;
		
		if(window.event) // IE
		{
      		keynum = evento.keyCode;
		}
		else if(evento.which) // Netscape/Firefox/Opera
		{
      		keynum = evento.which;
		}
		keychar = String.fromCharCode(keynum);
		
	  	if (keynum==8 || keynum==undefined)
	     	return true;
	    
	    if (valor.length<cantidad)
	    	return true;
	    else 
	    	return false; 	
	     	
	}
	
	
	function redondear(cantidad, decimales) 
	{
		var cantidad = parseFloat(cantidad);
		var decimales = parseFloat(decimales);
		decimales = (!decimales ? 2 : decimales);
		return Math.round(cantidad * Math.pow(10, decimales)) / Math.pow(10, decimales);
	} 
	
	
	function esNumero(cadena){
	  if ( isNaN( cadena ) )
	  {
	    return false;
	  } else {
	    return true;
	  }
	}
	
	
	 
  function trim(s) 
	{
        return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
  }
  

  function numerosUnPunto(ob,n,e) {

  var key;
  if(document.all) { key = e.keyCode;}
  else  {key = e.which; }
  var value = ob.value;
  
  // caso: ingreso de numeros
  if (key >= 48 && key <= 57) {
    // caso: valor vacio
    if (value.length == 0) {
      // caso: si es que se inicia con un cero se concatena el punto decimal automaticamente
      if (key == 48) {
        //ob.value = value + '0.';
        ob.value = value + '0';
        	if(document.all) {window.event.keyCode = 0;}
			else
			{
				e.preventDefault();
				e.stopPropagation();
			}
        return false;
      }
    }
    // caso: valor no es vacio
    else if (value.length > 0) {
      var puntoPos = value.indexOf('.');
      // caso: valor no posee punto decimal
      if (puntoPos == -1) {
        if (value.length >= n) {
          	if(document.all) {window.event.keyCode = 0;}
			else
				{
					e.preventDefault();
					e.stopPropagation();
				}
          return false;
        }
      }
      // caso: valor posee punto decimal
      else {
        var segmentos = value.split('.');
        // caso: si se ha completado el grupo de dos numeros no se permite el ingreso del numero
        if (segmentos[segmentos.length - 1].length == 2) {
          	if(document.all) {window.event.keyCode = 0;}
			else
			{
				e.preventDefault();
				e.stopPropagation();
			}
          return false;
        }
      }
    }
  }

  else if (key == 46) {
    // caso: valor vacio, se antepone el numero cero
    if (value.length == 0) {
      ob.value = value + '0';
    }
    else {
      // caso: valor no posee punto decimal
      if (value.indexOf('.') == -1) {
      }
      // caso: valor posee punto decimal, no se permite el ingreso de la coma
      else {
		if(document.all) {window.event.keyCode = 0;}
		else
		{
			e.preventDefault();
			e.stopPropagation();
		}
        return false;
      }
    }
  }
  
  else if (key == 8) {
  }
  
  else {
	if(document.all) {window.event.keyCode = 0;}
	else
	{
	e.preventDefault();
	e.stopPropagation();
	}
    return false;
  }
  
  return true;
  
}



function emailValido(str) {
               
		var at="@";
		var dot=".";
		var lat=str.indexOf(at);
		var lstr=str.length;
		var ldot=str.indexOf(dot);
		if (str.indexOf(at)==-1){		
		   return false;
		}
		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){		  
		   return false;
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){		   
		    return false;
		}

		if (str.indexOf(at,(lat+1))!=-1){		   
		    return false;
		}

		if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){		   
		    return false;
		}

		if (str.indexOf(dot,(lat+2))==-1){		    
		    return false;
		}
		
		if (str.indexOf(" ")!=-1){		    
		    return false;
		}

 		 return true;					
	}


/*
 *Funciones validaciones de fechas
 */
function esFechaValidaDDMMAAAA(fecha, mensaje){	
	var parteFecha = fecha.split('/');
	if( parteFecha.length != 3){  	  
	    setHtmlPorId(mensaje,"El formato de fecha debe ser dd/mm/aaaa");
		return false;
	}
	 dd = parteFecha[0];
	 mm = parteFecha[1];
	 yy = parteFecha[2];
	 return (esFecha(dd,mm,yy,mensaje) );
  }
	
  function esFecha(dia,mes,ano, mensaje) {  
	if (!esNumero(dia) || !esNumero(mes) ||!esNumero(ano)){		
		setHtmlPorId(mensaje,"Caracteres no validos");
		return false;
	}    
    if (ano <1850 || ano.length > 4){		
		setHtmlPorId(mensaje,"Año no valido");
		return false;
	}
	if ((mes<1)||(mes>12 )){		
		setHtmlPorId(mensaje,"Mes no valido");
		return false;
	}
	if ((dia<1)||(dia>31)){	
		setHtmlPorId(mensaje,"Dia no valido");
		return false;
	}
	if((mes==1)||(mes==3)||(mes==5)||
		(mes==7)||(mes==8)||(mes==10)||
		(mes==12)){
		if(dia > 31){
			setHtmlPorId(mensaje,"Dia no valido");
			return false;
		}
	}
	if((mes==4)||(mes==6)||(mes==9)||(mes==11)){
		if(dia > 30){
			setHtmlPorId(mensaje,"Dia no valido");
			return false;
		}
	}
	if(mes==2){
		if((ano  % 4 == 0)&&((!(ano  % 100 == 0))||(ano  % 400 == 0))){
			if(dia > 29){
				setHtmlPorId(mensaje,"Dia no valido");
				return false;
			}
		}
		else{
			if(dia > 28){ 
				setHtmlPorId(mensaje,"Dia no valido");
				return false;
			}
		}
	}
	return true;
  }
  
  function  formateaFechaDDMMAAAA(objeto){  
   var  keyAscii = event.keyCode;
    event.keyCode = 0;
   if((objeto.value.length == 1) || (objeto.value.length) == 4 ){
   	  if(keyAscii<48 || keyAscii>57 ){
			 event.keyCode = 0;
	  }else{
			event.keyCode = 47;
			objeto.value = objeto.value+String.fromCharCode(keyAscii);
	}

   }else{
  	  if(keyAscii<48 || keyAscii>57 ){
			 event.keyCode = 0;
	  }else{
			event.keyCode =   keyAscii;	 
	}
  }
}
  
function fecha1MayorQueFecha2(fecha1,fecha2){
	
		var parteFecha 	= fecha1.split('/');
		var parteFecha2 = fecha2.split('/');
		
		 dd = parteFecha[0];
		 mm = parteFecha[1];
		 yy = parteFecha[2];
		 
		 dd2 = parteFecha2[0];
		 mm2 = parteFecha2[1];
		 yy2 = parteFecha2[2];
		 
		// alert(yy+">"+yy2);
		 if(yy>yy2){ //año 
			 return true;
		 }
		 else{
			 if(yy == yy2){//alert(yy+"=="+yy2);
				 if(mm>mm2){ //alert(mm+">"+mm2);
					 //mes
					 	return true;
					 	}
				 else if(mm==mm2){
					 //alert(mm+"=="+mm2);				 
					 			if(dd>dd2)//alert(dd+"=="+dd2);
					 				//dia  
					 				return true;
				 }
			 }
		 }
		 
		 return false;
	  
}  
