
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--
/** Se busca el el id del objeto que queremos editar o eliminar
**  Tanto para editar como eliminar se usa el mismo buscador
**  Dependiendo del valor del parametro opcion sera editar o eliminar
**/
--%>

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
                        <label for="txt_nombre">Nombre</label>
                        <input class="txt txt_cadena" type="text" name="txt_nombre" value="${objeto.nombre}" required/>
                        <label for="txt_apellido">Apellido</label>
                        <input class="txt txt_cadena" type="text" name="txt_apellido" value="${objeto.apellido}" required/>
                        <label for="txt_comentario">Comentario</label>
                        <input class="txt txt_cadena" type="text" name="txt_comentario" value="${objeto.comentario}"/> 
                    </c:when>

                    <c:when test="${opcion == 'eliminar'}">
                        <label for="txt_nombre">Nombre</label>
                        <input class="txt txt_cadena" type="text" name="txt_nombre" value="${objeto.nombre}" readonly="true"/>
                        <label for="txt_apellido">Apellido</label>
                        <input class="txt txt_cadena" type="text" name="txt_apellido" value="${objeto.apellido}" readonly="true"/>
                        <label for="txt_comentario">Comentario</label>
                        <input class="txt txt_cadena" type="text" name="txt_comentario" value="${objeto.comentario}" readonly="true"/> 
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


