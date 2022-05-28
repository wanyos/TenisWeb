
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <form action="ServletEliminar?${lista_id}" method="get">
    <div class="div-editar-jugador">
        <label for="cbo">Seleccionar:</label>
        <select name="cbo_id" id="cbo_id">
            <c:forEach var="id" items="${lista_id}">
                <option value=${id}>${id}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="nombre_etiqueta" value="${param.nombre_etiqueta}"/>
        <input type="hidden" name="lista_id" value="${lista_id}"/> 
        <button name="btn_buscar">Buscar</button>     
    </div>
</form>   
        
     <c:if test="${not empty objeto}">
         <div class="div-crear-jugador">
             <form action="ServletEditar?opcion=jugador" method="post">
                 <label for="txt_nombre">Nombre</label>
                 <input type="text" name="txt_nombre" value="${objeto.nombre}" readonly="true"/>

                 <label for="txt_apellido">Apellido</label>
                 <input type="text" name="txt_apellido" value="${objeto.apellido}" readonly="true"/>

                 <label for="txt_comentario">Comentario</label>
                 <input type="text" name="txt_comentario" value="${objeto.comentario}" readonly="true"/> 

                 <button type="submit" name="btn_crear">Eliminar</button>
             </form>
         </div>
     </c:if>