/**
 * Created by Andres on 14/04/2015.
 */

var proveedorId = 2;
var valorProductos = 0;
var lista_productos = [];

$(document).ready(function () {
    cargarProductos("agregarProductoPaquete", "Agregar");
});

function agregarProductoPaquete(idProducto, element){
    lista_productos = addProductToPackage(idProducto, $(element), lista_productos);
}

