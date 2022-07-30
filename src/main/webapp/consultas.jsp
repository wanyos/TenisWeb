
<%@page import="java.time.LocalDate"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Expires" content="0">
        <meta http-equiv="Last-Modified" content="0">
        <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
        <meta http-equiv="Pragma" content="no-cache">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/estilo.css?<%=LocalDate.now()%>"/>   <!-- Se pone fecha para actualziar la cache y obtenga los cambios del css  -->
        <link href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@300&display=swap" rel="stylesheet"/> 
        <title>Consultas</title>
    </head>
    <body>
        <jsp:include page="comunes/titulo.jsp"/>

        <div class="estilo-caja">
            <div class="atras">
                <a href="index.jsp">Back</a>
            </div>
        </div>

        <div class="estilo-caja principal">
            <h3> Consultas jugador/fecha </h3>

                <div class="div-caja-form">
                    <form action="ServletConsultas" method="post">
                        <label>Partido Jugador</label>
                        <select name="cbo_jugador">
                            <option value=" --- "> --- </option>
                            <c:forEach var="ju" items="${lista_jugadores}">
                                <option value=${ju.id}>${ju.nombre}</option>
                            </c:forEach>
                        </select>

                        <label> ---- Fecha Partido</label>
                        <input class="txt txt_cadena" type="date" name="txt_fecha" min="2000-01-01" max="2050-01-01"/>    
                        <button class="estilo-boton" type="submit" name="btn_buscar_fecha">Buscar</button>    
                    </form>
                </div>

                <div class="div-caja-form">
                    <div class="div-tabla">
                        <c:if test="${not empty columnas}">
                            <table>
                                <thead>
                                    <tr>
                                        <c:forEach var="col" items="${columnas}">
                                            <td>${col}</td>
                                        </c:forEach>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach var="partido" items="${lista_partidos}">
                                        <tr>
                                            <td>${partido.getId()}</td>
                                            <td>${partido.fecha}</td>
                                            <td>${partido.getIdPares()}</td>
                                            <td>${partido.getJugador1()}</td>
                                            <td>${partido.getJugador2()}</td>
                                            <td>${partido.getPagaj1()}</td>
                                            <td>${partido.getPagaj2()}</td>
                                            <td>${partido.getIdBono1()}</td>
                                            <td>${partido.getIdBono2()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
        </div>


        <jsp:include page="comunes/pie_pagina.jsp"/>
    </body>
</html>
