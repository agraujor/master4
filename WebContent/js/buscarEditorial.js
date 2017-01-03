//SERVER WILL SEND RESPONSE IN ---XML---            

//we will store here object XMLHttpRequest
            
var xhr;

//shows editorials            
function buscarListaEditoriales() {             
	obtenerObjeto("lista");          
}

//searchs for an editorial
function buscaEditorial() {                       
	obtenerObjeto("editorial");          
}
   
//gets object
function obtenerObjeto(opcion) {
               
	// Para Explorer 6 y anteriores             
	if (window.ActiveXObject)            
		xhr = new ActiveXObject("Microsoft.XMLHttp");           
	// Resto de navegadores          
	else if ((window.XMLHttpRequest) ||(typeof XMLHttpRequest) != undefined)              
		xhr = new XMLHttpRequest();              
	else {            
		alert("Su navegador no soporta AJAX");             
		return;            
	}             
	// Sends  asyn. petition        		
	switch(opcion) {          
	case "lista":           
		cargarEditoriales();           
		break;          
	case "editorial":
		enviarPeticion();                 
		break;               
	default:                   
		window.alert("Error");             
	}        
}

            
function cargarEditoriales() {
	// preparing petition
	xhr.open("GET", "ServletEditorial", true);
	// Setting who will process result         
	xhr.onreadystatechange = procesarRespuesta;            
	// send petition
	xhr.send();
    
}
            
function enviarPeticion() {
                
	// getting reference             
	var lista = document.getElementById("editorial");                
	// Getting value of slected option               
	var valor = lista.options[lista.selectedIndex].value;               
	if (valor=="nada")               
		document.getElementById("info").innerHTML ="";               
	else{
		// preparing petition
		xhr.open("GET", "ServletEditorial?indice=" + valor, true);
		// Setting who will process result 
		xhr.onreadystatechange = procesarRespuesta2; 
		// send petition	                
		xhr.send();                
	}
            
}

function procesarRespuesta() {              
	//IT will obnly be executed if we get the complete response     
	if (xhr.readyState == 4) {                  
		// Getting in XML                 
		var resp = xhr.responseXML;                  
		// TAG <libro>                 
		var editoriales = resp.getElementsByTagName("editorial");
		// Getting children
        // creating selector
        var selector = '<select id="editorial" onchange="buscaEditorial();">';                  
        selector +="<option value='nada'>-</option>";                                   
        for(var i=0; i<editoriales.length; i++){
        	var nombreEditorial = editoriales[i].getElementsByTagName("nombre")[0]; //[0].innerHTML;     
        	selector += "<option value='" + 
                               nombreEditorial.childNodes[0].nodeValue + "'>"+nombreEditorial.childNodes[0].nodeValue+"</option>"; 
        }                                  
        selector += "</select>";                                                
        document.getElementById("select").innerHTML =                 
        	selector;               
	}       
}
            
function procesarRespuesta2() {          
	//IT will obnly be executed if we get the complete response                         
	if (xhr.readyState == 4) {               
		// Recibimos la respuesta en formato XML
		var resp = xhr.responseXML;
		// Accedo a la etiqueta <libro>
		var editorial = resp.getElementsByTagName("editorial")[0];
		var info = "<h5>Nombre</h5>"; 
		info = info+'<div style="margin: 10px; color: darkmagenta">';
		var nombreEditorial = editorial.getElementsByTagName("nombre")[0];
		info = info+nombreEditorial.childNodes[0].nodeValue+"</div>";
		info = info + "<h5>Direcci&oacute;n</h5> ";
		info = info+'<div style="margin: 10px; color: darkmagenta">';
		var direccionEditorial = editorial.getElementsByTagName("direccion")[0]; //[0].innerHTML;
		info = info+direccionEditorial.childNodes[0].nodeValue+"</div>";
		/*  var librosEditorial =editorial.getElementsByTagName("libro");
        
       		info = info+'<div style="margin: 10px; color: darkmagenta">';
            info = info + "<h5>Libros</h5> ";
            for(var i=0; i<librosEditorial.length; i++){
            	info += librosEditorial.childNodes[i].nodeValue ;
            }
            info ="</div>";*/     
		document.getElementById("info").innerHTML =
                    	info; 
	}            
}