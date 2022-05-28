
<div class="div-crear-jugador">
    <form action="ServletCrear?opcion=jugador" method="post">
        <label for="txt_nombre">Nombre</label>
        <input type="text" name="txt_nombre" placeholder="nombre" required/>

        <label for="txt_apellido">Apellido</label>
        <input type="text" name="txt_apellido" placeholder="apellido" required/>

        <label for="txt_comentario">Comentario</label>
        <input type="text" name="txt_comentario"/>

        <button type="submit" name="btn_crear">Crear</button>
    </form>
</div>
