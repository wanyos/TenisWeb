
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="modelo.Jugador"%>
<%@page import="modelo.Pares"%>
<%@page import="datos.ParesDao"%>
<%@page import="modelo.Objetos"%>
<%@page import="java.util.List"%>
<%@page import="datos.JugadorDao"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Expires" content="0">
        <meta http-equiv="Last-Modified" content="0">
        <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
        <meta http-equiv="Pragma" content="no-cache">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Partido</title>
        <link rel="stylesheet" href="css/estilo.css?1.0" media="all"/>
        <link href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@300&display=swap" rel="stylesheet"/> 
    </head>
    <body>

        <jsp:include page="../titulo.jsp"/>
        <jsp:include page="../menu_datos.jsp"/>

        <%
            JugadorDao j = new JugadorDao();
            List<Objetos> lista_jugadores = j.select();

            //ParesDao p = new ParesDao();
           //List<Integer> lista_id_pares = p.getListaId();
        %>

        <%
            //String valor_id = (String) request.getAttribute("valor_id_pares");
           // int id_par = -1;
            //if (valor_id != null && !valor_id.equals(" --- ")) {
              //  id_par = Integer.parseInt(valor_id);
           // }
            //Objetos pares = p.getObjeto(id_par);
            Objetos obj_pares = (Objetos)request.getAttribute("objeto_pares");
        %>

        <%--   <c:if test="${not empty valor_id}">
               <jsp:include page="../titulo.jsp"/>
               <jsp:include page="../menu_datos.jsp"/>
           </c:if> 

        <% if (valor_id != null) {%>
        <jsp:include page="../titulo.jsp"/>
        <jsp:include page="../menu_datos.jsp"/>
        <% }%>
        --%>

        <div class="estilo-caja principal">

            <div class="div-form-crear-partido">
                <label for="txt_fecha">Fecha</label>
                <input type="date" name="txt_fecha" min="2000-01-01" max="2050-01-01" required/>

                <div class="div-buscarId-jugador">
                    <form action="ServletCrear" method="get">
                        <label for="cbo_id_pares">Seleccionar id_pares :</label>
                        <select name="cbo_id_pares">
                            <option value=" --- "> --- </option>
                            <c:forEach var="id" items="${lista_id_pares}">
                                <option value=${id}>${id}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="crear" value="buscar_objeto_pares"/>
                        <button class="estilo-boton" type="submit" name="btn_buscar">Buscar</button>
                    </form>
                </div>


                <form action="ServletCrear" method="post">

                    <div class="div-buscarId-jugador">
                        <label for="cbo_jugador1">Jugador1 :</label>
                        <select name="cbo_jugador1">
                            <%
                                if (obj_pares != null) {
                                    int idj1 = ((Pares) obj_pares).getIdJ1();
                                    String nj1 = ((Pares) obj_pares).getNombreJ1();
                            %>
                            <option value="<%=idj1%>"><%=idj1 + " - " + nj1%></option>           
                            <% } else { %>
                            <option value=" --- "> --- </option>
                               <% for (Objetos jug : lista_jugadores) {
                                    int idj = ((Jugador) jug).getId();
                                    String nombre = ((Jugador) jug).getNombre();%>
                            <option value="<%=idj%>"><%=idj + " - " + nombre%></option>  
                            <% }

                                }%>
                        </select>
                    </div>

                    <div class="div-buscarId-jugador">
                        <label for="cbo_jugador2">Jugador2 :</label>
                        <select name="cbo_jugador2">
                            <c:set var="objeto" value="${obj_pares}"/>
                            <c:if test="${objeto != null}">
                                <%-- <option value=${pares.id_j2}>${pares.id_j2 +" - "+ pares.nombre_j2}</option> --%>
                            </c:if>
                            <c:if test="${obj_pares == null}">
                                <option value=" --- "> --- </option>
                                <c:forEach var="ju" items="<%=lista_jugadores%>">
                             <%--    <option value=${ju.getId}>${ju.getId +" - "+ ju.getNombre}</option>  --%> 
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>

                    <div class="div-buscarId-jugador">
                        <label for="cbo_paga1">Paga j1 :</label>
                        <select name="cbo_paga1">
                            <c:forEach var="id" items="${lista_id}"> 
                                <option value=${id}>${id}</option>
                            </c:forEach> 
                        </select>
                    </div>

                    <div class="div-buscarId-jugador">
                        <label for="cbo_paga2">Paga j2:</label>
                        <select name="cbo_paga2">
                            <c:forEach var="id" items="${lista_id}"> 
                                <option value=${id}>${id}</option>
                            </c:forEach> 
                        </select>
                    </div>

                    <button class="estilo-boton" type="submit" name="btn_crear">Crear</button>
                </form>
            </div>
        </div> 

        <jsp:include page="../pie_pagina.jsp"/>
    </body>
</html>
