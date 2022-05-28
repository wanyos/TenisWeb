
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="div-crear">

    <h3> Editar ${param.nombre_etiqueta} </h3>

    <c:if test="${param.nombre_etiqueta == 'Jugador'}">
        <jsp:include page="variar/variar_jugador.jsp?${lista_id}&${param.nombre_etiqueta}&${objeto}"/>   
    </c:if>

    <c:if test="${param.nombre_etiqueta == 'Bono'}">
        <jsp:include page=""/>
    </c:if>

    <c:if test="${param.nombre_etiqueta == 'Partido'}">
        <jsp:include page=""/>
    </c:if>

</div>

