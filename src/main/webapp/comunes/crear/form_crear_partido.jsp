
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
                        <input type="hidden" name="id_pares" value="buscar_objeto_pares"/>
                        <button class="estilo-boton" type="submit" name="btn_buscar">Buscar</button>
                    </form>
                </div>


                <form action="ServletCrear" method="post">

                    <div class="div-buscarId-jugador">
                        <label for="cbo_jugador1">Jugador1 :</label>
                        
                        <form action="ServletCrear" method="get">
                        
                            <select id="select_j1" onchange="actualizar('select_j1')" name="cbo_jugador1">
                            <c:set var="pares" value="${obj_pares}"/>
                            <c:if test="${pares != null}">
                                <option value=${pares.idJ1}>${pares.idJ1} - ${pares.nombreJ1}</option>
                            </c:if>
                            <c:if test="${pares == null}">
                                <option value=" --- "> --- </option>
                                <c:forEach var="ju" items="${lista_jugadores}">
                                    <option value=${ju.id}>${ju.id} - ${ju.nombre}</option>  
                                </c:forEach>
                            </c:if>
                        </select>
                            
                        </form>
                    </div>

                    <div class="div-buscarId-jugador">
                        <label for="cbo_jugador2">Jugador2 :</label>
                        <select id="select_j2" name="cbo_jugador2">
                            <c:set var="pares" value="${obj_pares}"/>
                            <c:if test="${pares != null}">
                                <option value=${pares.idJ2}>${pares.idJ2} - ${pares.nombreJ2}</option>
                            </c:if>
                            <c:if test="${pares == null}">
                                <option value=" --- "> --- </option>
                                <c:forEach var="ju" items="${lista_jugadores}">
                                    <option value=${ju.id}>${ju.id} - ${ju.nombre}</option>  
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>

                    <div class="div-buscarId-jugador">
                        <label>Paga j1 :</label>
                        <select name="cbo_paga1"> 
                            <c:set var="bonosj1" value="${bonosj1}"/>
                            <%--  <c:set var="nombre_select" value="${nombre_select}"/> --%>
                            <%-- <c:if test="${bonosj1 != null && nombre_select == 'select_j1'}"> --%>
                           <c:if test="${bonosj1 != null}">
                                <c:forEach var="bono" items="${bonosj1}"> 
                                    <option value=${bono.id}>id:${bono.id} - Horas:${bono.horas}</option>
                                </c:forEach> 
                                <div>
                                    <label>Horas</label>
                                    <select name="cbo_horas_p1">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                    </select>
                                </div>
                            </c:if> 
                            <%--  <c:if test="${bonosj1 == null || nombre_select != 'select_j1'}"> --%>
                            <c:if test="${bonosj1 == null}">
                                <option value="0">No</option>
                                <option value="1">Si</option>
                            </c:if>
                        </select>
                    </div>

                    <div class="div-buscarId-jugador">
                        <label>Paga j2 :</label>
                        <select name="cbo_paga2">  
                            <c:set var="bonosj2" value="${bonosj2}"/>
                            <%--  <c:set var="nombre_select" value="${nombre_select}"/> --%>
                            <%--  <c:if test="${bonosj2 != null && nombre_select == 'select_j2'}"> --%>
                            <c:if test="${bonosj2 != null}">
                                <c:forEach var="bono" items="${bonosj2}"> 
                                    <option value=${bono.id}>id: ${bono.id} - Horas: ${bono.horas}</option>
                                </c:forEach> 
                                <div>
                                    <label>Horas</label>
                                    <select name="cbo_horas_p1">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                    </select>
                                </div>
                            </c:if>
                            <%--  <c:if test="${bonosj2 == null || nombre_select != 'select_j2'}"> --%>
                            <c:if test="${bonosj2 == null}">
                                <option value="0">No</option>
                                <option value="1">Si</option> 
                            </c:if>
                        </select>
                    </div>

                    <button class="estilo-boton" type="submit" name="btn_crear">Crear</button>
                </form>
            </div>
        </div> 


        <script>
            function actualizar(nombre_select) {
              let selection;
                if (nombre_select === 'select_j1') {
                    selection = document.getElementById("select_j1");
                } else {
                    selection = document.getElementById("select_j2");
                }
             let id_jugador = selection.options[selection.selectedIndex].value;
             var xhr = new XMLHttpRequest();
             xhr.open('GET', 'ServletCrear', false);
             xhr.setRequestHeader('id_jugador', id_jugador);
             xhr.setRequestHeader("nombre_select", nombre_select);
             xhr.send();
            }
        </script> 
       
     
        

        <jsp:include page="../pie_pagina.jsp"/>
    </body>
</html>
