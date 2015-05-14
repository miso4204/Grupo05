/**
 * Created by Andres on 14/04/2015.
 */

var proveedorId = 2;

$(document).ready(function () {
    cargarDepartamentos("departamento");
    cargarCategorias("categoria");
});

$("#departamento").change(function () {
    var idDepartamento = $(this).val();
    cargarCiudadesDepartamento(parseInt(idDepartamento), "ciudad");
});