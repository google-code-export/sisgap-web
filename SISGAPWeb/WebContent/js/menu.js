//<!--
 var SMinitiallyOpenSub90023 = "SM900231Sub"; 
 var SMheightToOpen90023 = 200; 
 var SMspeed90023 = 10; 
 var SMstep90023 = 40; 
 var SMableToCloseSub90023 = false; 
 var SMobjOpen90023 = null; 
 var SMobj90023 = null; 
 var SMtimer90023 = null;  
 var SMopening90023 = false; 
 var SMisWorking90023 = false; 
 var SMtmpNeedOpen90023 = 0;
var SMtmpNeedClose90023 = 0;
var SMToOpen90023 = -1;
var SMCaller90023 = null;
var SMCallerOpen90023 = null;
var SMFS90023 = false;
var SMCallerName90023 = "SM900231";
var SMSl90023;
var SMSCurItem=null;
function SMpoc90023(SMsubName, SMCaller, SMSlide) { 		
	SMSl90023 = SMSlide;
	SMCaller90023 = SMCaller;	
	SMToOpen90023 = parseInt(SMheightToOpen90023);
	if(SMisWorking90023 == false) { 
		SMobj90023 = document.getElementById(SMsubName);
		if(SMinitiallyOpenSub90023 != "") { 
			SMobjOpen90023 = document.getElementById(SMinitiallyOpenSub90023);
			SMinitiallyOpenSub90023 = "";
			SMCallerOpen90023 = document.getElementById(SMCallerName90023);
			SMCallerName90023 = "";
        } 
		if(SMobjOpen90023 != null) { 
			SMtmpNeedClose90023 = parseInt(SMobjOpen90023.style.height);
		} 
	    SMtimer90023 = window.setInterval("SMda90023()", SMspeed90023);
    }     
} 
function SMpoc290023(SMsubName, toOpen, SMCaller, SMSlide) { 
	SMSl90023 = SMSlide;
    SMCaller90023 = SMCaller;
    SMToOpen90023 = parseInt(toOpen);
    if(SMisWorking90023 == false) { 
		SMobj90023 = document.getElementById(SMsubName);
		if(SMinitiallyOpenSub90023 != "") { 
			SMobjOpen90023 = document.getElementById(SMinitiallyOpenSub90023);
			SMinitiallyOpenSub90023 = "";
            SMCallerOpen90023 = document.getElementById(SMCallerName90023);
            SMCallerName90023 = "";
        } 
        if(SMobjOpen90023 != null) { 
			SMtmpNeedClose90023 = parseInt(SMobjOpen90023.style.height);
        } 
        SMtimer90023 = window.setInterval("SMda90023()", SMspeed90023);
        } 
 } 
 function SMda90023() { 
	if(SMobjOpen90023 == null) { 
		SMoo90023();
      } else
      if(SMobjOpen90023 == SMobj90023) { 
		if(SMableToCloseSub90023 == true) { 
			SMco90023();
         } else { 
			window.clearInterval(SMtimer90023);
           } 
           } else { SMoo290023();
                   } 
	}
 function SMoo90023() {  
	if(SMSl90023 == 0) { 
		SMcs90023(SMCaller90023, "SM_ps90023", "");
        SMCallerOpen90023 = SMCaller90023;
        window.clearInterval(SMtimer90023);
        SMsl90023 = 1;
        return;
    } SMisWorking90023 = true;
    if(SMToOpen90023 > 0) { 
		SMobj90023.style.display = "block";
    } 
    if(SMtmpNeedOpen90023 + SMstep90023 <= SMToOpen90023) { 
		SMobj90023.style.height = SMtmpNeedOpen90023 + SMstep90023;
        SMtmpNeedOpen90023 = SMtmpNeedOpen90023 + SMstep90023;
        } 
    else { 
		window.clearInterval(SMtimer90023);
		SMobj90023.style.height = SMToOpen90023; 
		SMcs90023(SMCaller90023, "SM_ps90023", "");
		if(SMCallerOpen90023 != null) { 
			SMFS90023 = true;
			SMcs90023(SMCallerOpen90023, "SM_p90023", "");
			SMFS90023 = false;
        } 
        SMCallerOpen90023 = SMCaller90023;
        SMobjOpen90023 = SMobj90023;
        SMtmpNeedOpen90023 = 0;
        SMisWorking90023 = false;
        SMToOpen90023 = -1; 
    } 
} 

function SMco90023() { 
	if(SMSl90023 == 0) { 
		window.clearInterval(SMtimer90023);
        SMsl90023 = 1;
        return;
    } 
    SMisWorking90023 = true;
    if(SMtmpNeedClose90023 - SMstep90023 < SMstep90023) { 
		window.clearInterval(SMtimer90023);
		SMobjOpen90023.style.display = "none";
		SMobjOpen90023.style.height = 1;
		SMobjOpen90023 = null;
		SMisWorking90023 = false;
		SMtmpNeedClose90023 = 0;
		SMFS90023 = true;
		SMcs90023(SMCallerOpen90023, "SM_p90023", "");
		SMCallerOpen90023 = null;
		SMFS90023 = false;
    } else { 
		SMobjOpen90023.style.height = SMtmpNeedClose90023 - SMstep90023;
    } 
    SMtmpNeedClose90023 = SMtmpNeedClose90023 - SMstep90023;
} 

function SMoo290023() { 
	if(SMSl90023 == 0) { 
		SMcs90023(SMCaller90023, "SM_ps90023", "");
		SMFS90023 = true;
		if(SMCallerOpen90023 != null && SMCallerOpen90023 != SMCaller90023) { 
			SMcs90023(SMCallerOpen90023, "SM_p90023", "");
			} 
		SMCallerOpen90023 = SMCaller90023;
		SMFS90023 = false; window.clearInterval(SMtimer90023); SMsl90023 = 1; return; 
	} 
	SMisWorking90023 = true; 
	if(SMToOpen90023 > 0) { 
		SMobj90023.style.display = "block"; } 
	if(SMtmpNeedOpen90023 + SMstep90023 <= SMToOpen90023) { 
		SMobj90023.style.height = SMtmpNeedOpen90023 + SMstep90023; 
		SMtmpNeedOpen90023 = SMtmpNeedOpen90023 + SMstep90023; } 
	if(SMtmpNeedClose90023 - SMstep90023 >= 1) { 
		SMobjOpen90023.style.height = SMtmpNeedClose90023 - SMstep90023; 
		SMtmpNeedClose90023 = SMtmpNeedClose90023 - SMstep90023; } 
		else { 
			SMobjOpen90023.style.display = "none"; 
		if(SMtmpNeedOpen90023 + SMstep90023 > SMToOpen90023 && SMtmpNeedClose90023 - SMstep90023 < 1) { 
			window.clearInterval(SMtimer90023); 
			SMobj90023.style.height = SMToOpen90023; 
			SMobjOpen90023.style.display = "none"; 
			SMobjOpen90023.style.height = 1; 
			SMobjOpen90023 = null; 
			SMobjOpen90023 = SMobj90023; 
			SMtmpNeedOpen90023 = 0; 
			SMtmpNeedClose90023 = 0; 
			SMisWorking90023 = false; 
			SMToClose90023 = -1; 
			SMcs90023(SMCaller90023, "SM_ps90023", ""); 
			SMFS90023 = true; 
			SMcs90023(SMCallerOpen90023, "SM_p90023", ""); 
			SMCallerOpen90023 = SMCaller90023; SMFS90023 = false; 
		} 
	} 
} 
function SMcs90023(SMobj, SMstyle, SMimage) { 
	if(SMCallerName90023.length > 0)  
		if(SMobj == document.getElementById(SMCallerName90023)) 
			return; 
	if((SMobj == SMCallerOpen90023 && SMFS90023 == false)) 
	return; 
	if(SMstyle != "") { 
		SMobj.className = SMstyle; } 
	if(SMimage != "") { 
		(document.getElementById(SMobj.id + "I")).src = SMimage;  
	} 
}
//Added by J.C.R
function openPage(urlDoc, targetFrame){
	try{
		if(targetFrame==null)
			targetFrame = "_self";	
		document.open(urlDoc,targetFrame,"",true);		
	}catch(e){
		//alert(e);
	}
}
function loadPage(urlDoc){
	try{
		if(SMSCurItem!=null){		
			SMSCurItem.style.backgroundColor='';	
		}
		SMSCurItem = event.srcElement;
		if(SMSCurItem!=null)SMSCurItem.style.backgroundColor='#CED7E1';//'#DCE1E9';////'#BDC9D7';	

		document.open(getPath(urlDoc),"FraMain","",true);		
	}catch(e){//alert(e.message);
	}
}
//-->