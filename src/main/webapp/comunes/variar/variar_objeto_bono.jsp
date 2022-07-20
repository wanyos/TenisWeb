
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form action="ServletModificar" method="get">
    <div class="div-caja-form">
        <label for="cbo">Seleccionar:</label>
        <select name="cbo_id" id="cbo_id">
            <c:forEach var="id" items="${lista_id}">
                <option value=${id}>${id}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="nombre_etiqueta" value="${param.nombre_etiqueta}"/>
        <input type="hidden" name="lista_id" value="${lista_id}"/> 
        <input type="hidden" name="opcion" value="${opcion}"/> 
        <button class="estilo-boton" name="btn_buscar">Buscar</button>     
    </div>
</form>   


<c:if test="${not empty objeto}">
    <div class="div-caja-form">
        <c:set var="opcion" value="${param.opcion}"/>


        <form action="ServletModificar" method="post">
            <div class="div-estilo-form">
                <c:choose>
                    <c:when test="${opcion == 'editar'}">
                        <label for="txt_fecha">Fecha</label>
                        <input class="txt txt_cadena" type="date" name="txt_fecha" min="2000-01-01" max="2050-01-01" value="${objeto.fecha}" required/>

                        <label for="txt_nombre">Nombre</label>
                        <input class="txt txt_cadena" type="text" name="txt_nombre" value="${objeto.nombre}" required/>

                        <label for="txt_id_jugador">ID Jugador</label>
                        <input class="txt txt_numero" type="text" name="txt_id_jugador" value="${objeto.idJugador}" required/>

                        <label for="txt_horas">Horas</label>
                        <input class="txt txt_numero" type="text" name="txt_horas" min="0" max="10" value="${objeto.horas}" required/>

                        <label for="txt_estado">Estado</label>
                        <input class="txt txt_cadena" type="text" name="txt_estado" value="${objeto.estado}"/>
                    </c:when>


                    <c:when test="${opcion == 'eliminar'}">
                        <label for="txt_fecha">Fecha</label>
                        <input class="txt txt_cadena" type="date" name="txt_fecha" min="2000-01-01" max="2050-01-01" value="${objeto.fecha}" readonly="true"/>

                        <label for="txt_nombre">Nombre</label>
                        <input class="txt txt_cadena" type="text" name="txt_nombre" value="${objeto.nombre}" readonly="true"/>

                        <label for="txt_id_jugador">ID Jugador</label>
                        <input class="txt txt_numero" type="text" name="txt_id_jugador" value="${objeto.idJugador}" readonly="true"/>

                        <label for="txt_horas">Horas</label>
                        <input class="txt txt_numero" type="text" name="txt_horas" min="0" max="10" value="${objeto.horas}" readonly="true"/>

                        <label for="txt_estado">Estado</label>
                        <input class="txt txt_cadena" type="text" name="txt_estado" value="${objeto.estado}" readonly="true"/>
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