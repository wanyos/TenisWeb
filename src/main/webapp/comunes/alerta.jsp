
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Alerta</title>
        <link rel="stylesheet" href="css/estilo.css?<%= LocalDate.now() %>" media="all"/>
        <link href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@300&display=swap" rel="stylesheet"/> 
    </head>

    <body>
        <div class="estilo-caja principal">
            <div class="div-caja-form">
                <h3>!!! Resultado de la operación...</h3>
                </br></br>
                <c:forEach var="m" items="${mensaje}">
                    <p>${m}</p>
                </c:forEach>
                <form action="index.jsp">
                    <button class="estilo-boton" type="submit" name="btn_crear">Inicio</button>    
                </form>
            </div>
        </div>
    </body>

</html>




