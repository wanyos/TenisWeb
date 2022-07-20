
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="div-caja-form">

    <h3> Crear ${param.nombre_etiqueta} </h3>
    <c:if test="${param.nombre_etiqueta == 'Jugador'}">
        <jsp:include page="crear/form_crear_jugador.jsp"/>
    </c:if>
    
    <c:if test="${param.nombre_etiqueta == 'Bono'}">
        <jsp:include page="crear/form_crear_bono.jsp"/>
    </c:if>
    
    <c:if test="${param.nombre_etiqueta == 'Partido'}">
        <jsp:forward page="/ServletCrear">
          <jsp:param name="inicio" value="cargar_listas"/> 
        </jsp:forward>
    </c:if>
    
</div>

