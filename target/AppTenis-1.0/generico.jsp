<%@page import="java.time.LocalDate"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Expires" content="0">
        <meta http-equiv="Last-Modified" content="0">
        <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
        <meta http-equiv="Pragma" content="no-cache">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu general</title>
        <%--<link rel="stylesheet" href="css/estilo-crear-jugador.css/crear.css">--%>
        <link rel="stylesheet" href="css/estilo.css?<%= LocalDate.now() %>"/>           <!-- Se pone fecha para obtener los cambios del css -->
        <link href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@300&display=swap" rel="stylesheet"/> 
    </head>
    <body>
        <jsp:include page="comunes/titulo.jsp"/>
        <jsp:include page="comunes/menu_datos.jsp"/>
        <jsp:include page="comunes/principal.jsp"/>
        <jsp:include page="comunes/pie_pagina.jsp"/>
        <script type="text/javascript">
            var el = document.getElementById("div-principal");
            el.className = "estilo-caja principal";
        </script>
    </body>
</html>