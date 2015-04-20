/*function cargarProductos() {
	var contador=0;
	var baseHtml = '';
	var primero=false;
    $.get("http://localhost:8080/ProductServices/provider/", function (res) {
        
        $.each(res, function (index, value) {        	
            baseHtml += '<a href="product.html">';
            baseHtml += '<td>' + value.id + '</td>';
            baseHtml += '<td>' + value.estado + '</td>';
            baseHtml += '<td><a href="producto.html#'+value.id+'">' + value.nombre + '</a></td>';
            baseHtml += '<td>' + value.descripcion + '</td>';
            baseHtml += '<td>' + value.valor + '</td>';
            baseHtml += '<td>' + value.visitas + '</td>';
            baseHtml += '<td>' + value.categoria + '</td>';
            baseHtml += '<td><button type="button" class="btn btn-default" onclick="'+myFunction+'(' + value.id + ', this)"> <span class="glyphicon glyphicon-star" aria-hidden="true"></span> '+buttonName+' </button></td>';
            baseHtml += '</tr>';
            contatador=contador+1;
            if (contador==3){
            	$("#container").append(baseHtml);
            	baseHtml="";
            }
            
        });
        if (baseHtml!=""){
        	$("#container").append(baseHtml);	
        }
        
    });
}
*/

function cargarProductos() {
	var contador=1;
	var baseHtml = '';
	var primero=false;
    $.get("http://localhost:8080/ProductServices", function (res) {
        
        $.each(res, function (index, value) {        	
            baseHtml += '<a href="product.html">';
            baseHtml += '<div class="col-md-4"><!-- second column -->';
            baseHtml += '<div class="widget-item">';
            baseHtml += '<h3 class="widget-title">' + value.nombre + '</h3>';
            baseHtml += '<div class="sample-thumb">';
            baseHtml += '<img src="images/event_1.jpg" alt="New Event" title="New Event">';
            baseHtml += '</div>';
            baseHtml += '<h4 class="consult-title">' + value.valor + '</h4>';
            baseHtml += '<p>' + value.descripcion + '</p>';
            baseHtml += '</div></div></a>';
            contador=contador+1;
            if (contador==3){
            	contador=0;
            	if (primero==false){
            		primero=true;
            		$("#row1").append(baseHtml);
            		baseHtml='<div  class="row">';
            	}else{
            		baseHtml+="</div>";
            		$("#divProductos").append(baseHtml);
                	baseHtml='<div  class="row">';	
            	}
            	
            }
            
        });
        if (baseHtml!=""){
        	$("#container").append(baseHtml);	
        }
        
    });
}