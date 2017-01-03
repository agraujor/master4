//SERVER WILL SEND RESPONSE IN ---HTML--- 

//we will store here object XMLHttpRequest
var xhr;
//shows AUTHORS
function buscarListaAutores() {
	obtenerObjeto("lista");
}
//searchs for an author 
function buscaAutor() {
	obtenerObjeto("autor");
}
    
//gets object
function obtenerObjeto(opcion) {
	// Para Explorer 6 y anteriores
	if (window.ActiveXObject)      
		xhr = new ActiveXObject("Microsoft.XMLHttp");  
	// Resto de navegadores          
	else if ((window.XMLHttpRequest) || (typeof XMLHttpRequest) != undefined)  
		xhr = new XMLHttpRequest();  
	else {                  
		alert("Su navegador no soporta AJAX");           
		return;              
	}
	// send asyn. petition             
	switch(opcion) {               
	case "lista":                                   	
		cargarAutores();                       
		break;                    
	case "autor":               
		enviarPeticion();                      
		break;              
	default:                 
		window.alert("Error");            
	}
	
}

function enviarPeticion() {      
	// getting reference     
	var lista = document.getElementById("nombre");              
	// Getting value of slected option              
	var valor = lista.options[lista.selectedIndex].value;
	
	if (valor=="nada")               
		document.getElementById("autor").innerHTML ="";               
	else{
		// preparing petition
		xhr.open("GET", "ServletAutor?indice=" + valor, true);       
		// Setting who will process result       
		xhr.onreadystatechange = procesarRespuesta2; // sin parentesis
		// send petition     
		xhr.send();
	}
}
            
function procesarRespuesta() {             
	//IT will obnly be executed if we get the complete response            
	if (xhr.readyState == 4) {
		document.getElementById("select").innerHTML =
			xhr.responseText;              
	}     
}
   
function procesarRespuesta2() {               
	//IT will obnly be executed if we get the complete response         
	if (xhr.readyState == 4) {      
		document.getElementById("autor").innerHTML =
                            xhr.responseText;
	}
}
            
function cargarAutores() {               
	// preparing petition              
	xhr.open("GET", "ServletAutor" , true);         
	// Setting who will process     
	xhr.onreadystatechange = procesarRespuesta;             
	// sending petition           
	xhr.send();      
}
