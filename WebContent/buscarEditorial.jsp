<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width">
        <link href="css/formularios.css" type="text/css" rel="stylesheet" />
		<title>Ejercicio módulo 4</title>
		<script src="js/buscarEditorial.js" type="text/javascript" language="javascript">     </script>
	</head>
	<body onload="buscarListaEditoriales()">
	        <header>
            <h1>Editoriales</h1>
        </header>
        <section>
            <form>
                <div class="label">
                    <label>Elige una editorial:</label> 
                </div>
                <div class="select" id="select">
                </div>
                <div class="label">
                    <label>Editorial:</label> 
                </div>
                <div id = "info" class="textarea"></div>
            </form>
        </section>
        <footer>
            <p>Ejercicio obligatorio módulo 4</p>
        </footer>
	</body>
</html>