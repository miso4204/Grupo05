
var articulos=[];

function cargarProductos() {
	var contador=1;
	var contaLabel=0;
	var baseHtml = '';
	var primero=false; 
        	
    $.get("http://localhost:8080/ProductService", function (res) {
        $("#loader").remove();
        $.each(res, function (index, value) {       
        	var imagen="http://assets2.bigthink.com/system/idea_thumbnails/48791/headline/beach.?1356289372";
        	var contenido=value.contenidos;
        	for(var i=0;i<contenido.length;i++){
        		if (contenido[i].tipoContenido=="Imagen"){
        			imagen=contenido[i].contenido;
        		}
        	}
        
        	contaLabel=contaLabel+1;
            baseHtml += '<div class="col-md-4"><!-- second column -->';
            baseHtml += '<a href="product.html">';
            baseHtml += '<div class="widget-item">';
            baseHtml += '<h3 class="widget-title">' + value.nombre + '</h3>';
            baseHtml += '<div class="sample-thumb">';
            baseHtml += '<img src="' + imagen + '" alt="New Event" title="New Event">';
            baseHtml += '</div>';
            baseHtml += '<h4 class="consult-title">$' + value.valor + '</h4>';
            baseHtml += '<p>' + value.descripcion + '</p></a>';
            baseHtml += '<label id="'+contaLabel+'"	style="color: green; font-size: 15px;"></label>';
            baseHtml +="<button onclick=\"addProduct('" + contaLabel + "','" + value.id + "','" + imagen + "','" + value.nombre + "','" + value.descripcion + "','" + value.valor + "');\" class='btn btn-large btn-success' type='button'>Agregar a carrito <i class='fa fa-shopping-cart'></i></button>";
            baseHtml += '</div></div>';
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

function addProduct(idLabel,id,imagen,nombre,descripcion,valor) {
	$("#"+idLabel).text("El producto se agrego al carrito de compras.");
	articulos=[];
	var data= new Object();
	data.id=parseInt(id);
	data.imagen=imagen;
	data.nombre=nombre;
	data.descripcion=descripcion;
	data.valor=parseInt(valor);
	data.cantidad=1;
	 if (localStorage.getItem("articulos")!=null ){		
			articulos=JSON.parse(localStorage.getItem("articulos"));			 
			 
		}
	var pos=buscarArticulo(data.id);
	if (pos>=0){
		articulos[pos].cantidad=articulos[pos].cantidad+1;
		articulos[pos].valor=articulos[pos].valor+data.valor;
	}else{
		articulos.push(data);	
	}
	
	localStorage.setItem("articulos",JSON.stringify(articulos));
	
}

function buscarArticulo(idArticulo){
    for(i=0;i<articulos.length;i++){
    	if (idArticulo==articulos[i].id){
    		return i;
    	}
    }	
    return -1;
    }
