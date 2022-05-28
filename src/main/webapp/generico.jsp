<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu general</title>
        <link rel="stylesheet" href="css/estilo-crear-jugador.css/">
        <link rel="stylesheet" href="css/estilo.css"/>
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

        <c:if test="${not empty mensaje}">
            <script type="text/javascript">
              alert("${mensaje}");
            </script>
        </c:if>
       
        
    </body>
</html>