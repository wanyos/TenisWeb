
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section>
    <div class="estilo-caja">
        <div class="atras">
            <a href="index.jsp">Back</a>
        </div>
        <div class="menu-datos">
            <a href="${pageContext.request.contextPath}/ServletControlador?opcion=listar&nombre_etiqueta=${param.nombre_etiqueta}">Listar</a>
            <a href="${pageContext.request.contextPath}/ServletControlador?opcion=crear&nombre_etiqueta=${param.nombre_etiqueta}">Crear</a>
            <a href="${pageContext.request.contextPath}/ServletControlador?opcion=eliminar&nombre_etiqueta=${param.nombre_etiqueta}">Eliminar</a>
            <c:set var="p" value="${param.nombre_etiqueta}"/>  
            <c:if test="${p != 'Partido'}">
              <a href="${pageContext.request.contextPath}/ServletControlador?opcion=editar&nombre_etiqueta=${param.nombre_etiqueta}">Editar</a>     
            </c:if>
        </div>
        <h4> ${param.nombre_etiqueta} </h4>
    </div>
</section>