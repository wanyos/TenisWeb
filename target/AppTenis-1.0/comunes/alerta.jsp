
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
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
                    <%List<String> m = (List) request.getAttribute("mensaje");
                      for(String aux: m){ %>
                      <p> <%=aux%> <p>
                          </br>
                     <% } %>
                <form action="index.jsp">
                    <button class="estilo-boton" type="submit" name="btn_crear">Inicio</button>    
                </form>
            </div>
        </div>
    </body>

</html>




