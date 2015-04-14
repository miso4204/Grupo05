/**
 * Created by Andres on 14/04/2015.
 */

var hash;
$(document).ready(function () {
    if(window.location.hash){
        hash = window.location.hash;
    }
    hash = hash.substring(1,hash.length);
    getTipoContenidos();
});

$("#categoria").change(function () {
    if($("#categoria").val()==5){
        $("#contenido").fadeOut();
        $("#contenidoHTMLWrapper").fadeIn();
    }else{
        $("#contenido").fadeIn();
        $("#contenidoHTMLWrapper").fadeOut();
    }
});