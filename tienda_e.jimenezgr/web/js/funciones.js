
function soloCaracterPrecioValido() {
    if ((event.keyCode < 46) || (event.keyCode > 57) || (event.keyCode === 47))
        event.returnValue = false;
}

function comprobarClave() {
    if (document.formReg.password.value !== document.formReg.password2.value) {
        alert("Las contraseñas no coinciden");
        document.formReg.password.focus();
        return false;
    }
    document.formReg.submit();
}

function validarProducto() {
    if (document.formAgregar.nombreProd.value === "") {
        alert("Por favor, introduzca el nombre");
        document.formAgregar.nombreProd.focus();
        return false;
    }
    if (document.formAgregar.precio.value === "") {
        alert("Por favor, introduzca el precio");
        document.formAgregar.precio.focus();
        return false;
    }
    if (document.formAgregar.categoria.value === "") {
        alert("Por favor, seleccione una categoría");
        return false;
    }
    if (document.formAgregar.imagen.value === "") {
        alert("Por favor, introduzca una imagen");
        document.formAgregar.imagen.focus();
        return false;
    }
    document.formAgregar.submit();

}

function confirmar(msg)
{
    if (confirm(msg))
        return true;
    else
        return false;
}

function formatoPrecio(precio) {
    precio = parseFloat(precio).toFixed(2);
    precio = precio + " €";
    return precio;
}

