
var articulos=[];
var paquetes=[];
var ciudades=[];
var signoMoneda="$";

function load(){
cargarPaquetes("");
cargarProductos("");

}

function verDetalleProducto(tipo,id){
	localStorage.setItem("tipoPP",tipo);
	localStorage.setItem("idPP",id);
	window.location="product.html";
}
function cargarSgino(){
	$.get("http://localhost:8080/UserService/"+sessionStorage.getItem('usuario'), function (res) {
		var moneda=res.moneda;
		if(moneda=="Dolar"){
			signoMoneda = "USD";	
		}else if(moneda=="Euro"){
			signoMoneda = "EUR";
		}else if(moneda=="Peso Colombiano"){
			signoMoneda = "COP";
		}
		
	});	
}
function formatoNumero(numero){
	
	
	return signoMoneda+" "+numero.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
}

function mostrarRedesSociales(){
	//Redes sociales
	$.get("http://localhost:8080/SocialNetwork", function (res) {
		if (res==""){
			$("#facebook").hide();
			$("#twitter").hide();
		}else if(res=="facebook"){
			$("#facebook").show();
			$("#twitter").hide();			
		}else if(res=="twitter"){
			$("#facebook").hide();
			$("#twitter").show();
		}
	});
}

function formatoFecha(fecha){
	var myDate = new Date(fecha);
	var month = myDate.getMonth() + 1;
	month=month+"";
	if(month.length==1){
		month="0"+month;
	}
	var day = myDate.getDate();
	day=day+"";
	if(day.length==1){
		day="0"+day;
	}
	var year = myDate.getFullYear();
	return day + "/" + month + "/" + year;
}

function mostrarDetalleProductoPaquete(){
	cargarSgino();
	
	mostrarRedesSociales();
	
	var tipo=localStorage.getItem("tipoPP");
	var id=localStorage.getItem("idPP");
	if (tipo=="producto"){
		$.get("http://localhost:8080/ProductService/"+id, function (res) {
			var contenido=res.contenidos;
			var video="";
			var descripcion="";
			var imagen="";
			$("#ulFotos").empty();
        	for(var i=0;i<contenido.length;i++){
        		if (contenido[i].tipoContenido=="Imagen"){
        			imagen=contenido[i].contenido;
        			$("#ulFotos").append('<li><img src="'+contenido[i].contenido+'" height="500px" ></li>');
        		}else if (contenido[i].tipoContenido=="Texto"){
        			descripcion=contenido[i].contenido;
        		}else if (contenido[i].tipoContenido=="Video"){
        			video=contenido[i].contenido;        			
        		}
        	}
        	if (imagen==""){
        		$("#ulFotos").append('<li><img src="http://assets2.bigthink.com/system/idea_thumbnails/48791/headline/beach.?1356289372" height="500px" ></li>');
        	}
        	if(video==""){
        		$("#video").remove();
        	}
        	$("#incluye").remove();
        	$("#titulo").text(res.nombre);
        	$("#descripcion").html(res.descripcion+"</br></br>"+descripcion);
        	$("#precio").text(formatoNumero(res.valor));
        	$("#video").attr('src',video);
        	$("#boton1").append("<button onclick=\"addProduct('label1','" + res.id + "','" + imagen + "','" + res.nombre + "','" + res.descripcion + "','" + res.valor + "');\" type='button' class='btn btn-default btn-lg pull-right'><span class='glyphicon glyphicon-plus' aria-hidden='true'></span> Agregar al Carrito</button>");
        	$("#boton2").append("<button onclick=\"addProduct('label2','" + res.id + "','" + imagen + "','" + res.nombre + "','" + res.descripcion + "','" + res.valor + "');\" type='button' class='btn btn-default btn-lg pull-right'><span class='glyphicon glyphicon-plus' aria-hidden='true'></span> Agregar al Carrito</button>");
        	
        	
        	//Información adicional por categoria
        	$("#infAdicional").empty();
        	if(res.idCategoria==3 || res.idCategoria==4){        		
        		$("#infAdicional").append("<h4  class='consult-title'>Información del Tour</h4><br>");
            	$("#infAdicional").append("<label	style='color: green; font-size: 15px;'>Origen:&nbsp;&nbsp;</label>");
            	$("#infAdicional").append("<label	style=' font-size: 13px;'>"+res.ciudadOrigen+"</label><br>");
            	$("#infAdicional").append("<label	style='color: green; font-size: 15px;'>Destino:&nbsp;&nbsp;</label>");
            	$("#infAdicional").append("<label	style=' font-size: 13px;'>"+res.ciudadDestino+"</label><br>");
            	$("#infAdicional").append("<label	style='color: green; font-size: 15px;'>Fecha Entrada:&nbsp;&nbsp;</label>");
            	$("#infAdicional").append("<label	style=' font-size: 13px;'>"+formatoFecha(res.fechaEntrada)+"</label><br>");
            	$("#infAdicional").append("<label	style='color: green; font-size: 15px;'>Fecha Salida:&nbsp;&nbsp;</label>");
            	$("#infAdicional").append("<label	style=' font-size: 13px;'>"+formatoFecha(res.fechaSalida)+"</label><br>");	
        	}else if(res.idCategoria==2){
        		$("#infAdicional").append("<h4  class='consult-title'>Plan de Alimentación</h4><br>");            	
            	$("#infAdicional").append("<label	style=' font-size: 13px;'>"+res.planAlimentacion+"</label><br>");
        	}else if(res.idCategoria==1){
        		$("#infAdicional").append("<h4  class='consult-title'>Alojamiento</h4><br>");            
        		$("#infAdicional").append("<label	style='color: green; font-size: 15px;'>Fecha Entrada:&nbsp;&nbsp;</label>");
            	$("#infAdicional").append("<label	style=' font-size: 13px;'>"+formatoFecha(res.fechaEntrada)+"</label><br>");
            	$("#infAdicional").append("<label	style='color: green; font-size: 15px;'>Fecha Salida:&nbsp;&nbsp;</label>");
            	$("#infAdicional").append("<label	style=' font-size: 13px;'>"+formatoFecha(res.fechaSalida)+"</label><br>");
        	}
        	
        	
        	cargarCarrusel();
        	$("#loader").hide(); 
	    });
	}else if (tipo=="paquete"){
		$.get("http://localhost:8080/PackageServices/"+id, function (res) {
			var productos=res.productos;
			var imagen="http://assets2.bigthink.com/system/idea_thumbnails/48791/headline/beach.?1356289372";
			$("#ulFotos").empty();
			for(var j=0;j<productos.length;j++){
				imagen="http://assets2.bigthink.com/system/idea_thumbnails/48791/headline/beach.?1356289372";
				var contenido=productos[j].contenidos;
				var baseHtml="";
				for(var i=0;i<contenido.length;i++){
	        		if (contenido[i].tipoContenido=="Imagen"){
	        			imagen=contenido[i].contenido;
	        			$("#ulFotos").append('<li><img src="'+imagen+'" height="500px" ></li>');
	        			break;	        			
	        		}	
	        	}
				
        		baseHtml += '<div class="list-item">';
                baseHtml += '<div class="list-thumb">';
                baseHtml += '<div class="title">';
                baseHtml += '<h4>' + productos[j].nombre+'</h4></div>';
                baseHtml += '<img src="' + imagen + '" height="200px" width="270px" ></div>';
                baseHtml += '<div class="list-content">';            
                baseHtml += '<h5>' + formatoNumero(productos[j].valor) + '</h5>';
                baseHtml += '<span>' + productos[j].descripcion + '</span>';
                baseHtml += "<a class='price-btn' onclick=\"verDetalleProducto('producto','" + productos[j].id + "');\" href='#'>Ver producto</a>";
                baseHtml += '</div></div>';
                $("#top10Paquetes").append(baseHtml);
			}
        	
        	var descuento=0;
        	//var labelDescuento="";
        	//var valorDescuento='<h4 class="consult-title">$' + value.valor + '</h4>';
        	if (res.promocion!=null){
        		descuento=res.promocion.descuento;
        		if(descuento>0){
        			var valor=res.valor-((descuento/100)*res.valor);
        			//valorDescuento='<h4 class="consult-title" style="text-decoration:line-through">$' + value.valor + '    <label style="color:red">    $'+valor+'</label></h4>';
            		//labelDescuento='<label style="color:red">'+descuento+'%</label>';	
            		
            		$("#pDescuento").text(descuento+"%");
            		$("#descuento").text(formatoNumero(valor));
        		}
        		
        	}
        	
        	
        	$("#titulo").text(res.nombre);
        	$("#descripcion").html(res.descripcion);
        	$("#precio").text(formatoNumero(res.valor));
        	$("#video").remove();
        	$("#boton1").append("<button onclick=\"addPaquete('label1','" + res.id + "','" + imagen + "','" + res.nombre + "','" + res.descripcion + "','" + res.valor + "','" + descuento + "');\" type='button' class='btn btn-default btn-lg pull-right'><span class='glyphicon glyphicon-plus' aria-hidden='true'></span> Agregar al Carrito</button>");
        	$("#boton2").append("<button onclick=\"addPaquete('label2','" + res.id + "','" + imagen + "','" + res.nombre + "','" + res.descripcion + "','" + res.valor + "','" + descuento + "');\" type='button' class='btn btn-default btn-lg pull-right'><span class='glyphicon glyphicon-plus' aria-hidden='true'></span> Agregar al Carrito</button>");
        	cargarCarrusel();
        	$("#loader").hide(); 
	    });
	}
	
}
function cargarComboCiudades(){
	ciudades.sort(compare);
	$("#comboCiudad").empty();
    $("#comboCiudad").append('<option  value="">Seleccione >></option>');
    for(var i=0;i<ciudades.length;i++){
    	$("#comboCiudad").append('<option  value="'+ciudades[i].id+'">'+ciudades[i].nombre+'</option>');
    }
}
function buscarCiudad(idCiudad) {
	for(var i=0;i<ciudades.length;i++){
		if (ciudades[i].id==idCiudad){
			return true;
		}
	}
	return false;
}
function compare(a,b) {
	  if (a.nombre < b.nombre)
	     return -1;
	  if (a.nombre > b.nombre)
	    return 1;
	  return 0;
	}

function cargarProductos(urlFiltro) {	
	cargarSgino();
	var contador=1;
	var contaLabel=0;
	var baseHtml = '';
	var primero=false;
	var div='id="divb"';
	$("#divb").remove();
	$("#divb").remove();
	$("#tituloProductos").remove();
	$("#productos").empty();
        	
    $.get("http://localhost:8080/ProductService"+urlFiltro, function (res) {
        //$("#loader").remove();
        $("#row1").append('<h3 id="tituloProductos" style="color: green;" class="widget-title" align="right">Productos</h3>');
        $.each(res, function (index, value) {       
        	var imagen="http://assets2.bigthink.com/system/idea_thumbnails/48791/headline/beach.?1356289372";
        	var contenido=value.contenidos;
        	for(var i=0;i<contenido.length;i++){
        		if (contenido[i].tipoContenido=="Imagen"){
        			imagen=contenido[i].contenido;
        		}
        	}
        	if (buscarCiudad(value.idCiudad)==false && value.ciudad!=""){
        		var ciudad =new Object();
            	ciudad.id=value.idCiudad;
            	ciudad.nombre=value.ciudad;	
            	ciudades.push(ciudad);
        	}
        	
         
        	contaLabel=contaLabel+1;
            baseHtml += '<div ' + div + ' class="col-md-4"><!-- second column -->';
            baseHtml += "<a onclick=\"verDetalleProducto('producto','" + value.id + "');\" href='#'>";
            baseHtml += '<div class="widget-item">';
            baseHtml += '<h3 class="widget-title">' + value.nombre + '</h3>';
            baseHtml += '<div class="sample-thumb">';
            baseHtml += '<img src="' + imagen + '" alt="New Event" title="New Event">';
            baseHtml += '</div>';
            baseHtml += '<h4 class="consult-title">' + formatoNumero(value.valor) + '</h4>';
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
            		div="";
            	}else{
            		baseHtml+="</div>";
            		$("#productos").append(baseHtml);
                	baseHtml='<div  class="row">';	
            	}
            	
            }
            
        });
        if(primero==false){
        	if (baseHtml!=""){
        		$("#row1").append(baseHtml);	
            }	
        }else{
        	if (baseHtml!=""){
        		$("#productos").append(baseHtml);	
            }
        }
        
         
    });
}

function cargarPaquetes(urlFiltro) {	
	cargarSgino();
	var contador=0;
	var contaLabel=0;
	var baseHtml = '';
	var primero=false; 
	$("#paquetes").empty();    	
    $.get("http://localhost:8080/PackageServices"+urlFiltro, function (res) {
        $("#loader").hide();
        $("#productos").append('<h3 style="color: green;" class="widget-title" align="right">Paquetes</h3>');
        baseHtml='<div  class="row">';
        $.each(res, function (index, value) {       
        	var imagen="http://assets2.bigthink.com/system/idea_thumbnails/48791/headline/beach.?1356289372";
        	
        	if (value.productos.length>0){
        		var contenido=value.productos[0].contenidos;
        		for(var i=0;i<contenido.length;i++){
            		if (contenido[i].tipoContenido=="Imagen"){
            			imagen=contenido[i].contenido;
            		}
            	}
        	}
        	var productos=value.productos;
        	for(var i=0;i<productos.length;i++){
        		if (buscarCiudad(productos[i].idCiudad)==false && productos[i].ciudad!=""){
            		var ciudad =new Object();
                	ciudad.id=productos[i].idCiudad;
                	ciudad.nombre=productos[i].ciudad;	
                	ciudades.push(ciudad);
            	}
        	}
        	
        	
        	var descuento=0;
        	var labelDescuento="";
        	var valorDescuento='<h4 class="consult-title">' + formatoNumero(value.valor) + '</h4>';
        	if (value.promocion!=null){
        		descuento=value.promocion.descuento;
        		if(descuento>0){
        			var valor=value.valor-((descuento/100)*value.valor);
        			valorDescuento='<h4 class="consult-title" style="text-decoration:line-through">' + formatoNumero(value.valor) + '    <label style="color:red">    '+formatoNumero(valor)+'</label></h4>';
            		labelDescuento='<label style="color:red">'+descuento+'%</label>';	
        		}
        		
        	}
        	
        
        	contaLabel=contaLabel+1;
            baseHtml += '<div class="col-md-4"><!-- second column -->';
            baseHtml += "<a onclick=\"verDetalleProducto('paquete','" + value.id + "');\" href='#'>";
            baseHtml += '<div class="widget-item">';
            baseHtml += '<h3 class="widget-title">' + value.nombre+'  '+labelDescuento+ '</h3>';
            baseHtml += '<div class="sample-thumb">';
            baseHtml += '<img src="' + imagen + '" alt="New Event" title="New Event">';
            baseHtml += '</div>';
            baseHtml += valorDescuento;
            baseHtml += '<p>' + value.descripcion + '</p></a>';
            baseHtml += '<label id="p'+contaLabel+'"	style="color: green; font-size: 15px;"></label>';
            baseHtml +="<button onclick=\"addPaquete('p" + contaLabel + "','" + value.id + "','" + imagen + "','" + value.nombre + "','" + value.descripcion + "','" + value.valor + "','" + descuento + "');\" class='btn btn-large btn-success' type='button'>Agregar a carrito <i class='fa fa-shopping-cart'></i></button>";
            baseHtml += '</div></div>';
            contador=contador+1;
            if (contador==3){
            	contador=0;
            		baseHtml+="</div>";
            		$("#paquetes").append(baseHtml);
                	baseHtml='<div  class="row">';	
            	
            	
            }
            
            
        });
        cargarComboCiudades();
        if (baseHtml!=""){
        	$("#paquetes").append(baseHtml);	
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

function addPaquete(idLabel,id,imagen,nombre,descripcion,valor,descuento) {
	$("#"+idLabel).text("El paquete se agrego al carrito de compras.");
	paquetes=[];
	var data= new Object();
	data.id=parseInt(id);
	data.imagen=imagen;
	data.nombre=nombre;
	data.descripcion=descripcion;
	data.valor=parseFloat(valor);
	data.cantidad=1;
	data.descuento=parseFloat(descuento);
	 if (localStorage.getItem("paquetes")!=null ){		
		 paquetes=JSON.parse(localStorage.getItem("paquetes"));			 
			 
		}
	var pos=buscarPaquete(data.id);
	if (pos>=0){
		paquetes[pos].cantidad=paquetes[pos].cantidad+1;
		paquetes[pos].valor=paquetes[pos].valor+data.valor;
	}else{
		paquetes.push(data);	
	}
	
	localStorage.setItem("paquetes",JSON.stringify(paquetes));
	
}

function buscarPaquete(idPaquete){
    for(i=0;i<paquetes.length;i++){
    	if (idPaquete==paquetes[i].id){
    		return i;
    	}
    }	
    return -1;
    }

function filtrar(){
	$("#loader").show();
	var fecha1=$("#fecha1").val();
	var fecha2=$("#fecha2").val();
	var precio1=$("#precio1").val();
	var precio2=$("#precio2").val();
	var select_id = document.getElementById("comboCiudad");
	var idCiudad=select_id.options[select_id.selectedIndex].value;
	var div=$("#filtros");
	div.empty();
	div.append("<label>Filtrado por:</label><br>");
	var url="";
	var label="";
	if (fecha1!="" && fecha2!=""){
		url="/"+fecha1+"/"+fecha2;		
		div.append("<label style='background-color: gray;width: 35%;border-radius:3px;color:white'>Fecha <i  onclick=\"borrarFiltro('fecha');\" Style='cursor: pointer; margin-left:50%' class='fa fa-times'></i></label>");
	}else{
		url="/0/0";		
	}
	if (precio1!="" && precio2!=""){
		url=url+"/"+precio1+"/"+precio2;
		div.append("<label style='background-color: gray;width: 35%;border-radius:3px;color:white'>Precio <i  onclick=\"borrarFiltro('precio');\" Style='cursor: pointer; margin-left:50%' class='fa fa-times'></i></label>");
	}else{
		url=url+"/0/0";
	}
	if(idCiudad>0){
		url=url+"/"+idCiudad;
		div.append("<label style='background-color: gray;width: 35%;border-radius:3px;color:white'>Ciudad <i  onclick=\"borrarFiltro('ciudad');\" Style='cursor: pointer; margin-left:50%' class='fa fa-times'></i></label>");
	}else{
		url=url+"/0";
	}	
	
	if(url=="/0/0/0/0/0"){
		div.empty();
	}
	
	cargarPaquetes("/FiltrarPaquetes"+url);
	cargarProductos("/FiltrarProductos"+url);
}

function borrarFiltro(tipo){
	switch (tipo) {
	case "fecha":
		$("#fecha1").val("");
		$("#fecha2").val("");			
		break;
    case "precio":
    	$("#precio1").val("");
		$("#precio2").val("");
		break;
    case "ciudad":
    	cargarComboCiudades();
	break;	

	default:
		break;
	}
	filtrar();
	
}


function cargarTop10Paquetes() {	
	cargarSgino();
	mostrarRedesSociales();
	var contador=0;
	var contaLabel=0;
	var baseHtml = '';
	var primero=false; 
	$("#top10Paquetes").empty();    	
    $.get("http://localhost:8080/PackageServices", function (res) {
        $.each(res, function (index, value) {       
        	var imagen="http://assets2.bigthink.com/system/idea_thumbnails/48791/headline/beach.?1356289372";
        	
        	if (value.productos.length>0){
        		var productos=value.productos;
        		for(var j=0;j<productos.length;j++){
        			var contenido=productos[j].contenidos;
            		for(var i=0;i<contenido.length;i++){
                		if (contenido[i].tipoContenido=="Imagen"){
                			imagen=contenido[i].contenido;
                		}
                	}
        		}
        		
        	}
        
        /*	
        	
        	var descuento=0;
        	var labelDescuento="";
        	var valorDescuento='<h4 class="consult-title">$' + value.valor + '</h4>';
        	if (value.promocion!=null){
        		descuento=value.promocion.descuento;
        		if(descuento>0){
        			var valor=value.valor-((descuento/100)*value.valor);
        			valorDescuento='<h4 class="consult-title" style="text-decoration:line-through">$' + value.valor + '    <label style="color:red">    $'+valor+'</label></h4>';
            		labelDescuento='<label style="color:red">'+descuento+'%</label>';	
        		}
        		
        	}*/
        	
        
        	contaLabel=contaLabel+1;
        	if(contaLabel<=10){
        		baseHtml += '<div class="list-item">';
                baseHtml += '<div class="list-thumb">';
                baseHtml += '<div class="title">';
                baseHtml += '<h4>' + value.nombre+'</h4></div>';
                baseHtml += '<img src="' + imagen + '" height="200px" width="270px" ></div>';
                baseHtml += '<div class="list-content">';            
                baseHtml += '<h5>' + formatoNumero(value.valor) + '</h5>';
                baseHtml += '<span>' + value.descripcion + '</span>';
                baseHtml += "<a onclick=\"verDetalleProducto('paquete','" + value.id + "');\" href='#' class='price-btn'>Ver paquete</a>";
                baseHtml += '</div></div>';
                
        		$("#top10Paquetes").append(baseHtml);
        		baseHtml="";
        	}
            
            	
            	
            
            
            
        });
        cargarCarrusel();
        
    });
}


