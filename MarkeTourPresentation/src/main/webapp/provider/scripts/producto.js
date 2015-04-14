/**
 * Created by Andres on 14/04/2015.
 */
var hash;

$(document).ready(function () {
    if(window.location.hash){
        hash = window.location.hash;
    }
    hash = hash.substring(1,hash.length);
    getProducto(hash);
    getContents(hash);
});

function llenarContents(lista){
    $.each(lista, function (index, value) {
        if(value.tipo == 3){
            var text = '<iframe width="100%" height="500" src="'+value.contenido+'" frameborder="0" allowfullscreen></iframe>';
            $("#video-container").append(text);
        }else if(value.tipo == 4){
            var text = '<img src="'+value.contenido+'">';
            $("#images-container").append(text);
        }else if(value.tipo == 5){
            $("#text-container").append(value.contenido);
        }
    });
}