
<div class="div-caja-form">
    <form action="ServletCrear" method="post">
        <div class="div-estilo-form">
            <label for="txt_nombre">Nombre</label>
            <input class="txt txt_cadena" type="text" name="txt_nombre" placeholder="nombre" required/>

            <label for="txt_apellido">Apellido</label>
            <input class="txt txt_cadena" type="text" name="txt_apellido" placeholder="apellido" required/>

            <label for="txt_comentario">Comentario</label>
            <input class="txt txt_cadena" type="text" name="txt_comentario"/>

            <input type="hidden" name="nombre_etiqueta" value="${param.nombre_etiqueta}"/>
            <button class="estilo-boton" type="submit" name="btn_crear">Crear</button>
        </div>
    </form>
</div>
