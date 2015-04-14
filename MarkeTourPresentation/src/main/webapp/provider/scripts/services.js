/**
 * Created by Andres on 14/04/2015.
 */

$.put = function(url, data, callback, type){

    if ( $.isFunction(data) ){
        type = type || callback,
            callback = data,
            data = {}
    }

    return $.ajax({
        url: url,
        type: 'PUT',
        success: callback,
        data: data,
        contentType: type
    });
};

$.dele = function(url, data, callback, type){

    if ( $.isFunction(data) ){
        type = type || callback,
            callback = data,
            data = {}
    }

    return $.ajax({
        url: url,
        type: 'DELETE',
        success: callback,
        data: data,
        contentType: type
    });
};

function cargarPaquetes() {
    $.get("http://localhost:8080/PackageServices/provider/" + proveedorId, function (res) {
        var baseHtml = '';
        $.each(res, function (index, value) {
            baseHtml += '<tr>';
            baseHtml += '<td>' + value.id + '</td>';
            baseHtml += '<td>' + value.estado + '</td>';
            baseHtml += '<td>' + value.nombre + '</td>';
            baseHtml += '<td>' + value.descripcion + '</td>';
            baseHtml += '<td>' + value.valor + '</td>';
            baseHtml += '<td>' + value.visitas + '</td>';
            baseHtml += '<td><button type="button" class="btn btn-primary" onclick="eliminarPaquete(' + value.id + ')"> <span class="glyphicon glyphicon-star" aria-hidden="true"></span> Eliminar </button></td>';
            baseHtml += '</tr>';
        });
        $("#paquete-container").append(baseHtml);
    });
}

function eliminarPaquete(id) {
    console.log(id);
}

function eliminarProducto(id) {
    console.log(id);
}

function cargarProductos(myFunction, buttonName) {
    $.get("http://localhost:8080/ProductServices/provider/" + proveedorId, function (res) {
        var baseHtml = '';
        $.each(res, function (index, value) {
            baseHtml += '<tr>';
            baseHtml += '<td>' + value.id + '</td>';
            baseHtml += '<td>' + value.estado + '</td>';
            baseHtml += '<td><a href="producto.html#'+value.id+'">' + value.nombre + '</a></td>';
            baseHtml += '<td>' + value.descripcion + '</td>';
            baseHtml += '<td>' + value.valor + '</td>';
            baseHtml += '<td>' + value.visitas + '</td>';
            baseHtml += '<td>' + value.categoria + '</td>';
            baseHtml += '<td><button type="button" class="btn btn-default" onclick="'+myFunction+'(' + value.id + ', this)"> <span class="glyphicon glyphicon-star" aria-hidden="true"></span> '+buttonName+' </button></td>';
            baseHtml += '</tr>';
        });
        $("#producto-container").append(baseHtml);
    });
}

function agregarProducto() {
    var nombre = $("#nombre").val();
    var descripcion = $("#descripcion").val();
    var valor = $("#valor").val();
    var capacidad = $("#capacidad").val();
    var categoria = $("#categoria").val();
    var ubicacion = $("#ciudad").val();
    var estado = $("#estado").is(":checked");
    if(estado){
        estado=1;
    }else{
        estado=0;
    }
    var object = {
        id: null,
        valor: valor,
        nombre: nombre,
        proveedor: proveedorId,
        descripcion: descripcion,
        visitas: 0,
        capacidad: capacidad,
        coordenadas: "",
        categoria: categoria,
        ubicacion: ubicacion,
        estado: estado
    };
    $.put("http://localhost:8080/ProductServices",JSON.stringify(object), function (res) {
        $("input, textarea, select").val("");
        alert("Producto agregado correctamente");
    },
        'application/json'
    );
}

function agregarPaquete(){
    var nombre = $("#nombre").val();
    var descripcion = $("#descripcion").val();
    var estado = $("#estado").is(":checked");
    if(estado){
        estado=1;
    }else{
        estado=0;
    }
    var object = {
        id: null,
        proveedor: proveedorId,
        nombre: nombre,
        descripcion: descripcion,
        estado: estado,
        visitas: 0,
        valor: valorProductos
    };
    $.put("http://localhost:8080/PackageServices",JSON.stringify(object), function (res) {
            $("input, textarea, select").val("");
            $.each(lista_productos, function (index, value) {
                $.get("http://localhost:8080/PackageServices/"+res.id+"/product/"+value, function (res2) {

                    },
                    'application/json'
                );
            });
            alert("Paquete agregado correctamente");
        },
        'application/json'
    );
}

function cargarDepartamentos(idSelect) {
    $.get("http://localhost:8080/DepartmentServices", function (res) {
        var baseHtml = '';
        $.each(res, function (index, value) {
            baseHtml+='<option value="'+value.id+'">';
            baseHtml+=value.descripcion;
            baseHtml+='</option>';
        });
        $("#"+idSelect).append(baseHtml);
        console.log("Departamentos cargados");
    });
}

function cargarCiudadesDepartamento(idDepartamento, idSelect){
    $.get("http://localhost:8080/CityServices/departament/"+idDepartamento, function (res) {
        var baseHtml = '';
        $.each(res, function (index, value) {
            baseHtml+='<option value="'+value.id+'">';
            baseHtml+=value.descripcion;
            baseHtml+='</option>';
        });
        $("#"+idSelect).html("");
        $("#"+idSelect).append(baseHtml);
    })
}

function cargarCategorias(idSelect){
    $.get("http://localhost:8080/CategoryServices", function (res) {
        var baseHtml = '';
        $.each(res, function (index, value) {
            baseHtml+='<option value="'+value.id+'">';
            baseHtml+=value.descripcion;
            baseHtml+='</option>';
        });
        $("#"+idSelect).append(baseHtml);
        console.log("Categorias cargadas");
    })
}

function addProductToPackage(id, element, list){
    if ($.inArray(id, list) == -1) {
        list.push(id);
        element.removeClass('btn-default');
        element.addClass('btn-primary');
        $.get("http://localhost:8080/ProductServices/"+id, function (res) {
            valorProductos+=res.valor;
        });
    } else {
        list = $.grep(list, function (value) {
            return value != id;
        });
        element.removeClass('btn-primary');
        element.addClass('btn-default');
        $.get("http://localhost:8080/ProductServices/"+id, function (res) {
            valorProductos-=res.valor;
        });
    }
    return list;
}

function getProducto(id){
    $.get("http://localhost:8080/ProductServices/"+id, function (res) {
        $("#productTitle").text(res.nombre);
    })
}

function getContents(id){
    $.get("http://localhost:8080/ContentServices/product/"+id, function (res) {
        llenarContents(res);
    });
}

function getTipoContenidos(){
    $.get("http://localhost:8080/ContentTypeServices", function (res) {
        var baseHtml = '';
        $.each(res, function (index, value) {
            baseHtml+='<option value="'+value.id+'">';
            baseHtml+=value.descripcion;
            baseHtml+='</option>';
        });
        $("#categoria").append(baseHtml);
    });
}

function agregarContenido(){
    var content;
    if($("#categoria").val() == 5){
        content = tinyMCE.activeEditor.getContent();
    }else{
        content = $("#contenido").val();
    }
    var object = {
        "id": null,
        "producto": hash,
        "contenido": content,
        "estado": 1,
        "tipo": $("#categoria").val()
    };
    $.put("http://localhost:8080/ContentServices/",JSON.stringify(object), function () {
        alert("Contenido Agregado");
    },
    'application/json'
    );
}