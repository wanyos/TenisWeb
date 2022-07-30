
<%@page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Expires" content="0">
        <meta http-equiv="Last-Modified" content="0">
        <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
        <meta http-equiv="Pragma" content="no-cache">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/estilo.css?<%= LocalDate.now()%>"/>   <!-- Se pone fecha para actualziar la cache y obtenga los cambios del css  -->
        <link href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@300&display=swap" rel="stylesheet"/> 
        <title>Salir</title>
    </head>

    <body>
        <div class="estilo-caja principal principal-img salir">
            <% request.getSession().invalidate();%>
            <h3>Hasta otra...</h3>
        </div> 
            
        <script>
            window.location.hash = "no-back-button";
            window.location.hash = "Again-No-back-button";//esta linea es necesaria para chrome

            window.onhashchange = function () {
                window.location.hash = "no-back-button";
            };
        </script>
    </body>
</html>
