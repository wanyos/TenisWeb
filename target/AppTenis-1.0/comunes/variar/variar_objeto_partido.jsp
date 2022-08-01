
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form action="ServletModificar" method="get">
    <div class="div-caja-form">
        <label for="cbo">Seleccionar id partido :</label>
        <input class="txt txt_numero" type="text" name="txt_id_partido"/>
        <input type="hidden" name="nombre_etiqueta" value="${param.nombre_etiqueta}"/>
        <input type="hidden" name="lista_id" value="${lista_id}"/> 
        <input type="hidden" name="opcion" value="${opcion}"/> 
        <button class="estilo-boton" name="btn_buscar">Buscar</button>     
    </div>
</form>   

<c:if test="${not empty mensaje}">
    <script>
        let ms = "${mensaje}";
        alert(ms);
    </script>
</c:if>

<c:if test="${not empty objeto}">
    <div class="div-caja-form">
        <c:set var="opcion" value="${param.opcion}"/>

        <form action="ServletModificar" method="post">
            <div class="div-estilo-form">
                <c:choose>

                    <c:when test="${opcion == 'editar'}">
                        <div class="div-agrupar-form">         
                            <div class="div-estilo-form">
                                <label for="txt_fecha">Fecha</label>
                                <input class="txt txt_cadena" type="date" name="txt_fecha" min="2000-01-01" max="2050-01-01" value="${objeto.fecha}" required/>

                                <label for="txt_id_pares">Id Pares</label>
                                <input class="txt txt_numero" type="text" name="txt_id_pares" value="${objeto.idPares}" readonly="true"/>

                                <label for="txt_jugador1">Jugador1</label>
                                <input class="txt txt_cadena" type="text" name="txt_jugador1" value="${objeto.jugador1}" readonly="true"/>

                                <label for="txt_jugador2">Jugador2</label>
                                <input class="txt txt_cadena" type="text" name="txt_jugador2" value="${objeto.jugador2}" readonly="true"/>
                            </div>
                            <div class="div-estilo-form div-alinear-abajo">
                                <label for="txt_paga1">Paga j1</label>
                                <input class="txt txt_numero" type="text" name="txt_pagaj1" value="${objeto.pagaj1}"/>

                                <label for="txt_paga2">Paga j2</label>
                                <input class="txt txt_numero" type="text" name="txt_pagaj2" value="${objeto.pagaj2}"/>
                            </div>
                            <div class="div-estilo-form div-alinear-abajo">
                                 <label for="txt_id_bono1">Id Bono1</label>
                                <input class="txt txt_numero" type="text" name="txt_id_bono1" value="${objeto.idBono}"/>
                                
                                <label for="txt_id_bono2">Id Bono2</label>
                                <input class="txt txt_numero" type="text" name="txt_id_bono2" value="${objeto.idBono}"/>
                            </div>
                        </div>
                    </c:when>

                    <c:when test="${opcion == 'eliminar'}">
                        <div class="div-agrupar-form">         
                            <div class="div-estilo-form">
                                <label for="txt_fecha">Fecha</label>
                                <input class="txt txt_cadena" type="date" name="txt_fecha" min="2000-01-01" max="2050-01-01" value="${objeto.fecha}" readonly="true"/>

                                <label for="txt_id_pares">Id Pares</label>
                                <input class="txt txt_numero" type="text" name="txt_id_pares" value="${objeto.idPares}" readonly="true"/>

                                <label for="txt_jugador1">Jugador1</label>
                                <input class="txt txt_cadena" type="text" name="txt_jugador1" value="${objeto.jugador1}" readonly="true"/>

                                <label for="txt_jugador2">Jugador2</label>
                                <input class="txt txt_cadena" type="text" name="txt_jugador2" value="${objeto.jugador2}" readonly="true"/>
                            </div>
                            <div class="div-estilo-form div-alinear-abajo">
                                <label for="txt_paga1">Paga j1</label>
                                <input class="txt txt_numero" type="text" name="txt_pagaj1" value="${objeto.pagaj1}" readonly="true"/>

                                <label for="txt_paga2">Paga j2</label>
                                <input class="txt txt_numero" type="text" name="txt_pagaj2" value="${objeto.pagaj2}" readonly="true"/>
                            </div>
                            <div class="div-estilo-form div-alinear-abajo">
                                <label for="txt_id_bono">Id Bono1</label>
                                <input class="txt txt_numero" type="text" name="txt_id_bono1" value="${objeto.idBono1}" readonly="true"/>
                                
                                <label for="txt_id_bono2">Id Bono2</label>
                                <input class="txt txt_numero" type="text" name="txt_id_bono2" value="${objeto.idBono2}" readonly="true"/>
                            </div>
                            <div class="div-estilo-form div-alinear-abajo">
                                <label>Comentario</label>
                                <textarea name="txt_comentario" rows="10" cols="50" readonly="true">${objeto.comentario}</textarea>
                            </div>    
                        </div>
                    </c:when>

                </c:choose>

                <input type="hidden" name="nombre_etiqueta" value="${param.nombre_etiqueta}"/>
                <input type="hidden" name="opcion" value="${opcion}"/> 

                <c:if test="${opcion == 'editar'}">
                    <button class="estilo-boton" type="submit" name="btn_editar">Editar</button>
                </c:if>

                <c:if test="${opcion == 'eliminar'}">
                    <button class="estilo-boton" type="submit" name="btn_eliminar">Eliminar</button>
                </c:if>
            </div>
        </form>
    </div>
</c:if>