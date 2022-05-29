
<div class="div-form-crear">
    <form action="ServletCrear" method="post">
        <label for="txt_nombre">Nombre</label>
        <input type="text" name="txt_nombre" placeholder="nombre" required/>

        <label for="txt_apellido">Apellido</label>
        <input type="text" name="txt_apellido" placeholder="apellido" required/>

        <label for="txt_comentario">Comentario</label>
        <input type="text" name="txt_comentario"/>
        
        <input type="hidden" name="nombre_etiqueta" value="${param.nombre_etiqueta}"/>
        <button class="estilo-boton" type="submit" name="btn_crear">Crear</button>
    </form>
</div>
