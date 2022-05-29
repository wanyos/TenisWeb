
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main>
    <div id="div-principal" class="estilo-caja principal principal-img">
        <c:choose>
            <c:when test="${opcion == 'listar'}">
                <jsp:include page="listar.jsp?nombre_etiqueta=${param.nombre_etiqueta}&columnas=${columnas}&datos=${datos}"/>
            </c:when>
             <c:when test="${opcion == 'crear'}">
                <jsp:include page="crear.jsp?nombre_etiqueta=${param.nombre_etiqueta}"/>
            </c:when>
             <c:when test="${opcion == 'eliminar'}">
                 <jsp:include page="editar-eliminar.jsp?nombre_etiqueta=${param.nombre_etiqueta}&${objeto}"/>
                <%--<jsp:include page="eliminar.jsp?nombre=${param.nombre_etiqueta}&${objeto}"/>--%>
            </c:when>
             <c:when test="${opcion == 'editar'}">
                 <jsp:include page="editar-eliminar.jsp?nombre_etiqueta=${param.nombre_etiqueta}&${objeto}"/>
                <%--<jsp:include page="editar.jsp?nombre=${param.nombre_etiqueta}&${objeto}"/>--%>
            </c:when>
        </c:choose>
        
    </div>
</main>