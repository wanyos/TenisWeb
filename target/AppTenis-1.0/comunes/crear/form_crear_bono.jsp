
<div class="div-caja-form">
    <form action="ServletCrear" method="post">
        <div class="div-estilo-form">
            <label for="txt_id">ID Bono</label>
            <input class="txt txt_numero" type="text" name="txt_id" required/>

            <label for="txt_fecha">Fecha</label>
            <input class="txt txt_cadena" type="date" name="txt_fecha" min="2000-01-01" max="2050-01-01" required/>

            <label for="txt_nombre">Nombre</label>
            <input class="txt txt_cadena" type="text" name="txt_nombre" required/>

            <label for="txt_id_jugador">ID Jugador</label>
            <input class="txt txt_numero" type="text" name="txt_id_jugador" required/>

            <label for="txt_horas">Horas</label>
            <input class="txt txt_numero" type="text" name="txt_horas" min="0" max="10" required/>

            <label for="txt_estado">Estado</label>
            <input class="txt txt_cadena" type="text" name="txt_estado" value="activo"/>

            <input type="hidden" name="nombre_etiqueta" value="${param.nombre_etiqueta}"/>
            <button class="estilo-boton" type="submit" name="btn_crear">Crear</button>
        </div>
    </form>
</div>