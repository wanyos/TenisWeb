

const txt_fecha = document.getElementById("input_fecha");
txt_fecha.addEventListener("change", validarFecha);

function cerrar() {
    window.close();
}

window.location.hash = "no-back-button";
window.location.hash = "Again-No-back-button";//esta linea es necesaria para chrome

window.onhashchange = function () {
    window.location.hash = "no-back-button";
};


function intervalo() {
    setTimeout(cerrar, 3000);
}

function validarFecha() {
    const fecha_aux = document.getElementById("input_fecha").value;
    const fecha_escogida = new Date(fecha_aux);

    const fecha_sistema = Date.now();
    const fecha_ahora = new Date(fecha_sistema);

    fecha_escogida.setHours(0, 0, 0, 0);
    fecha_ahora.setHours(0, 0, 0, 0);

    console.log("fecha escogida: " + fecha_escogida);
    console.log("fecha ahora: " + fecha_ahora);

    if (fecha_escogida < fecha_ahora) {
        alert("la fecha debe ser mayor a la actual...");
    }
}



