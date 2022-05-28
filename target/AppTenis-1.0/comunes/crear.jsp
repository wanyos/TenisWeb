
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="div-crear">

    <h3> Crear ${param.nombre_etiqueta} </h3>
    <c:if test="${param.nombre == 'Jugador'}">
        <jsp:include page="crear/crear_jugador.jsp"/>
    </c:if>
    
    <c:if test="${param.nombre_etiqueta == 'Bono'}">
        <jsp:include page=""/>
    </c:if>
    
    <c:if test="${param.nombre_etiqueta == 'Partido'}">
        <jsp:include page=""/>
    </c:if>
    
</div>

