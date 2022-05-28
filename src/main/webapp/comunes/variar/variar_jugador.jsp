
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <form action="ServletEditar?${lista_id}" method="get">
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
             <c:set var="opcion" value="${param.opcion}"/>
             <form action="ServletEditar?opcion=jugador" method="post">
                 <label for="txt_nombre">Nombre</label>
                 <input type="text" name="txt_nombre" value="${objeto.nombre}" required/>

                 <label for="txt_apellido">Apellido</label>
                 <input type="text" name="txt_apellido" value="${objeto.apellido}" required/>

                 <label for="txt_comentario">Comentario</label>
                 <input type="text" name="txt_comentario" value="${objeto.comentario}"/> 

                 <button type="submit" name="btn_crear">Editar</button>
             </form>
         </div>
     </c:if>
 

