
<%@page import="java.time.LocalDate"%>
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
        <link rel="stylesheet" href="css/estilo.css?<%= LocalDate.now()%>"/>
        <link href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@300&display=swap" rel="stylesheet"/> 
    </head>
    <body>

        <jsp:include page="../titulo.jsp"/>
        <jsp:include page="../menu_datos.jsp"/>

        <div class="estilo-caja principal">
            <h3>Crear Partido</h3>
            <div class="div-caja-form">

                <div class="div-caja-form">
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
                    <div class="div-estilo-form">

                        <div class="div-caja-form">
                            <label for="txt_fecha">Fecha</label>
                            <input class="txt txt_cadena" id="input_fecha" type="date" name="txt_fecha" min="2000-01-01" max="2050-01-01" required/>
                        </div>

                     <div class="div-agrupar-form">
                        
                        <div class="div_caja_form">
                        
                        <div class="div-caja-form">
                            <label for="cbo_jugador1">Jugador1</label>
                            <select class="ancho-select" id="select_j1" name="cbo_jugador1" onchange="ir(this.value, 'select_j1')">
                                <c:set var="pares" value="${obj_pares}"/>
                                <c:if test="${pares != null}">
                                    <option value=${pares.idJ1}>${pares.idJ1} - ${pares.nombreJ1}</option>
                                </c:if>
                                <c:if test="${pares == null && jugador1 == null}">
                                    <option value=" --- "> --- </option>
                                    <c:forEach var="ju" items="${lista_jugadores}">
                                        <option value="${ju.id}">${ju.id} - ${ju.nombre}</option>  
                                    </c:forEach>
                                </c:if>
                                <c:if test="${jugador1 != null}">
                                    <option value=${jugador1.id}>${jugador1.id} - ${jugador1.nombre}</option>
                                </c:if>
                            </select>
                        </div>

                        <div class="div-caja-form">
                            <label for="cbo_jugador2">Jugador2</label>
                            <select class="ancho-select" id="select_j2" name="cbo_jugador2" onchange="ir(this.value, 'select_j2')" >
                                <c:set var="pares" value="${obj_pares}"/>
                                <c:if test="${pares != null}">
                                    <option value=${pares.idJ2}>${pares.idJ2} - ${pares.nombreJ2}</option>
                                </c:if>
                                <c:if test="${pares == null && jugador2 == null}">
                                    <option value=" --- "> --- </option>
                                    <c:forEach var="ju" items="${lista_jugadores}">
                                        <option value=${ju.id}>${ju.id} - ${ju.nombre}</option>  
                                    </c:forEach>
                                </c:if>
                                <c:if test="${jugador2 != null}">
                                    <option value=${jugador2.id}>${jugador2.id} - ${jugador2.nombre}</option>
                                </c:if>
                            </select>
                        </div>

                            <div class="div-caja-form">
                                <label>Paga- j1</label>
                                <select class="ancho-select" name="cbo_paga1"> 
                                    <c:set var="bonosj1" value="${bonosj1}"/>
                                    <c:if test="${bonosj1 != null}">
                                        <c:forEach var="bono" items="${bonosj1}"> 
                                            <option value=${bono.id}>id:${bono.id} - Horas:${bono.horas}</option>
                                        </c:forEach> 
                                    </c:if> 
                                    <c:if test="${bonosj1 == null}">
                                        <option value="no">No</option>
                                        <option value="si">Si</option>
                                    </c:if>
                                </select>

                                <label>Horas</label>
                                <select name="cbo_horas_p1">
                                    <option value="0">0</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                </select>
                            </div>

                            <div class="div-caja-form">
                                <label>Paga- j2</label>
                                <select class="ancho-select" name="cbo_paga2">  
                                    <c:set var="bonosj2" value="${bonosj2}"/>
                                    <c:if test="${bonosj2 != null}">
                                        <c:forEach var="bono" items="${bonosj2}"> 
                                            <option value=${bono.id}>id:${bono.id} - Horas:${bono.horas}</option>
                                        </c:forEach> 

                                    </c:if>
                                    <c:if test="${bonosj2 == null}">
                                        <option value="no">No</option>
                                        <option value="si">Si</option> 
                                    </c:if>
                                </select>

                                <label>Horas</label>
                                <select name="cbo_horas_p2">
                                    <option value="0">0</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                </select>
                            </div>

                        </div>
                                    
                            <div class="div-caja-form div-estilo-form">
                                <label>Comentario</label>
                                <textarea name="txt_comentario" rows="10" cols="50"></textarea>
                            </div>        

                     </div>

                        <input type="hidden" name="nombre_etiqueta" value="partido"/>
                        <button class="estilo-boton" type="submit" name="btn_crear">Crear</button>
                    </div>
                </form>
            </div>
        </div>

        <script>
            function ir(id_jugador, nombre_select) {
                let selection;
                if (nombre_select === 'select_j1') {
                    selection = document.getElementById("select_j2");
                } else {
                    selection = document.getElementById("select_j1");
                }
                let id = selection.options[selection.selectedIndex].value;
                let destino = "ServletCrear?id_jugador=" + id_jugador + "&nombre_select=" + nombre_select + "&id_otro_jugador=" + id;
                window.location.href = destino;
            }
        </script>

        <script>
            const txt_fecha = document.getElementById("input_fecha");
            txt_fecha.addEventListener("change", validaFecha);

            function validaFecha() {
                txt_fecha.style.color = "white";
                const valor_fecha = txt_fecha.value;
                const fecha_escogida = new Date(valor_fecha);

                const fecha_sistema = Date.now();
                const fecha_ahora = new Date(fecha_sistema);

                fecha_escogida.setHours(0, 0, 0, 0);
                fecha_ahora.setHours(0, 0, 0, 0);

                console.log("fecha escogida: " + fecha_escogida);
                console.log("fecha ahora: " + fecha_ahora);

                if (fecha_escogida < fecha_ahora) {
                    alert("la fecha debe ser mayor a la actual...");
                    txt_fecha.style.color = "red";
                    txt_fecha.value = "00/00/0000";
                }
            }
        </script>

        <jsp:include page="../pie_pagina.jsp"/>
    </body>
</html>
