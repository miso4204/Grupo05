/**
 * Created by Andres on 12/05/2015.
 */
function crearGráfica(id, title, subtitle, categories, series, text){
    options = {
        chart: {
            renderTo: id,
            type: 'column'
        },
        title: {
            text: ""
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            min: 0,
            title: {
                text: text
            }
        },
        legend: {
            reversed: true
        },
        series: series
    };
    return options;
}

function getInfoFechas(fecha1, fecha2){
    $.get("http://localhost:8080/ReporteServices/salesfechas/"+fecha1+"/"+fecha2, function (res) {
        getInfo(res);
    });
}

function getInfoLocation(idCiudad){
    $.get("http://localhost:8080/ReporteServices/salesubication/"+idCiudad, function (res) {
        getInfo(res);
    });
}

function getInfoRatingLocation(idCiudad){
    $.get("http://localhost:8080/ReporteServices/salesubicationcalificada/"+idCiudad, function (res) {
        getInfoRating(res);
    });
}

function getInfoRatingPackage(idPaquete){
    $.get("http://localhost:8080/ReporteServices/salespaquetecalificada/"+idPaquete, function (res) {
        getInfoRatingPack(res);
    });
}

function getInfo(res){
    var numero_compras = res.length;
    var montoCompras = 0;
    var numItems = 0;
    $.each(res, function(index, value){
        var obj = res[index];
        $.each(obj.itemCompras, function(ind, val){
            montoCompras+=obj.itemCompras[ind].valor;
            numItems++;
        });
    });
    var grafica1 = new Highcharts.Chart(crearGráfica("container", "Numero de compras", "", ['Numero de compras'], [{
        name: "Numero de compras",
        data: [numero_compras]
    }], ""));
    var grafica2 = new Highcharts.Chart(crearGráfica("container2", "Monto total por compras", "", ['Monto total por compras'], [{
        name: "Monto total por compras",
        data: [montoCompras]
    }], ""));
    var grafica3 = new Highcharts.Chart(crearGráfica("container3", "Total de productos", "", ['Total de productos'], [{
        name: "Total de productos",
        data: [numItems]
    }], ""));
}

function getInfoRating(res){
    var calificaciones = [0,0,0,0,0,0];
    $.each(res, function(index, value){
        var obj = res[index];
        if(obj.calificacion>0 && obj.calificacion <=5){
            calificaciones[obj.calificacion] = calificaciones[obj.calificacion]+1;
        }else{
            calificaciones[0]++;
        }
    });
    var grafica1 = new Highcharts.Chart(crearGráfica("container", "Calificación de las compras", "", ['Sin Calificar', '1', '2', '3', '4', '5'], [{
        name: "Calificación de las compras",
        data: [numero_compras]
    }], ""));
}

function getInfoRatingPack(res){
    var promedio = 0;
    var num = 0;
    $.each(res, function(index, value){
        var obj = res[index];
        if(obj.calificacion>0 && obj.calificacion <=5){
            promedio+=obj.calificacion;
            num++;
        }
    });
    promedio = promedio/num;
    var grafica1 = new Highcharts.Chart(crearGráfica("container", "Calificación del paquete", "", ['Calificación del paquete'], [{
        name: "Calificación del paquete",
        data: [promedio]
    }], ""));
}