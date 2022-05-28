
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="div-tabla">
    <h3> Listado ${param.nombre_etiqueta} </h3>

    <table>
        <thead>
            <tr>
                <c:forEach var="col" items="${columnas}">
                    <td>${col}</td>
                </c:forEach>
            </tr>
        </thead>

        <tbody>
            <c:if test="${param.nombre_etiqueta == 'Jugador'}">
                <c:forEach var="jugador" items="${datos}">
                    <tr>
                        <td>${jugador.getId()}</td>
                        <td>${jugador.nombre}</td>
                        <td>${jugador.apellido}</td>
                        <td>${jugador.comentario}</td>
                    </tr>
                </c:forEach>
            </c:if>

            <c:if test="${param.nombre_etiqueta == 'Bono'}">
                <c:forEach var="bono" items="${datos}">
                    <tr>
                        <td>${bono.getId()}</td>
                        <td>${bono.fecha}</td>
                        <td>${bono.nombre}</td>
                        <td>${bono.getIdJugador()}</td>
                        <td>${bono.getHoras()}</td>
                        <td>${bono.estado}</td>
                    </tr>
                </c:forEach>
            </c:if>

            <c:if test="${param.nombre_etiqueta == 'Partido'}">
                <c:forEach var="partido" items="${datos}">
                    <tr>
                        <td>${partido.getId()}</td>
                        <td>${partido.fecha}</td>
                        <td>${partido.getIdPares()}</td>
                        <td>${partido.getPagaj1()}</td>
                        <td>${partido.getPagaj2()}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</div>

