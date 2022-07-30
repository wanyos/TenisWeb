
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <c:if test="${opcion == 'editar'}">
        <h3> Editar ${param.nombre_etiqueta} </h3>     
    </c:if>
    <c:if test="${opcion == 'eliminar'}">
        <h3> Eliminar ${param.nombre_etiqueta} </h3>     
    </c:if>
   
    <c:if test="${param.nombre_etiqueta == 'Jugador'}">
        <jsp:include page="variar/variar_objeto_jugador.jsp?${lista_id}&${param.nombre_etiqueta}&${objeto}"/>   
    </c:if>

    <c:if test="${param.nombre_etiqueta == 'Bono'}">
        <jsp:include page="variar/variar_objeto_bono.jsp?${lista_id}&${param.nombre_etiqueta}&${objeto}"/>
    </c:if>

    <c:if test="${param.nombre_etiqueta == 'Partido'}">
        <jsp:include page="variar/variar_objeto_partido.jsp?${param.nombre_etiqueta}&${objeto}"/>
    </c:if>


