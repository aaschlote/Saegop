var map;
var markers = [];
var geocoder;
var heatmaps = [];

var gradient = [
                'rgba(0, 255, 255, 0)',
                'rgba(0, 255, 255, 1)',
                'rgba(0, 191, 255, 1)',
                'rgba(0, 127, 255, 1)',
                'rgba(0, 63, 255, 1)',
                'rgba(0, 0, 255, 1)',
                'rgba(0, 0, 223, 1)',
                'rgba(0, 0, 191, 1)',
                'rgba(0, 0, 159, 1)',
                'rgba(0, 0, 127, 1)',
                'rgba(63, 0, 91, 1)',
                'rgba(127, 0, 63, 1)',
                'rgba(191, 0, 31, 1)',
                'rgba(255, 0, 0, 1)'];

geocoder = new google.maps.Geocoder();
 
function initialize() {
    var latlng = new google.maps.LatLng(-26.9257936,-49.2235072);
 
    var options = {
        zoom: 10,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
 
    map = new google.maps.Map(document.getElementById("mapa"), options);
}

initialize();

$('#buscarDadosHeats').on('click', function (e) {
	
	dtInicio = document.getElementById("dtInicio");
	dtFim = document.getElementById("dtFim");
	var patternData = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;

	if (dtInicio.value == ""){
		alert ('Obrigatório informar a data de inicio');    
		document.getElementById("dtInicio").focus();
		return false;
	}

	if(!patternData.test(dtInicio.value)){
	    alert("Digite a data no formato Dia/Mês/Ano");
	    document.getElementById("dtInicio").focus();
	    return false;
	}
	
	if (dtFim.value == ""){
		alert ('Obrigatório informar a data de inicio');    
		document.getElementById("dtFim").focus();
		return false;
	}
	
	if(!patternData.test(dtFim.value)){
	    alert("Digite a data no formato Dia/Mês/Ano");
	    document.getElementById("dtFim").focus();
	    return false;
	}
	
	$.ajax({
        url: "saegopBuscarPontos",
        type: 'POST',
        data: {'dt-inicio' : dtInicio.value, 'dt-fim' : dtFim.value},
        dataType: "json",
        success: function (data) { 
        	carregarHeats(data);
        	return false;
        },
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });

})

$('#buscarDadosHeatsSisp').on('click', function (e) {
	
	dtInicio = document.getElementById("dtInicio");
	dtFim = document.getElementById("dtFim");
	var patternData = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;

	if (dtInicio.value == ""){
		alert ('Obrigatório informar a data de inicio');    
		document.getElementById("dtInicio").focus();
		return false;
	}

	if(!patternData.test(dtInicio.value)){
	    alert("Digite a data no formato Dia/Mês/Ano");
	    document.getElementById("dtInicio").focus();
	    return false;
	}
	
	if (dtFim.value == ""){
		alert ('Obrigatório informar a data de inicio');    
		document.getElementById("dtFim").focus();
		return false;
	}
	
	if(!patternData.test(dtFim.value)){
	    alert("Digite a data no formato Dia/Mês/Ano");
	    document.getElementById("dtFim").focus();
	    return false;
	}
	
	$.ajax({
        url: "saegopBuscarPontosSisp",
        type: 'POST',
        data: {'dt-inicio' : dtInicio.value, 'dt-fim' : dtFim.value},
        dataType: "json",
        success: function (data) { 
        	carregarHeats(data);
        	return false;
        },
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });

})

function carregarHeats(pontosJson) {
	
	var pointsPosition = [];
	
	deleteHeatmap();
	
	$.each(pontosJson, function(index, ponto) {
		position = new google.maps.LatLng(ponto.latitude, ponto.longitude);
		pointsPosition.push(position);
	});
	
	var heatmap = new google.maps.visualization.HeatmapLayer({
		maxIntensity: 100,
		data: pointsPosition
	});
	
	heatmap.set('radius', heatmap.get('radius') ? null : 50);
	heatmap.set('gradient', heatmap.get('gradient') ? null : gradient);
	
	heatmaps.push(heatmap);
	heatmap.setMap(map);
	
	
}

function deleteHeatmap(){
    for (var k = 0; k < heatmaps.length; k++) {
    	heatmaps[k].setMap(null);
    }
    heatmaps=[];
}
	    

$('#buscarDadosPontos').on('click', function (e) {
	
	dtInicio = document.getElementById("dtInicio");
	dtFim = document.getElementById("dtFim");
	var patternData = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;

	if (dtInicio.value == ""){
		alert ('Obrigatório informar a data de inicio');    
		document.getElementById("dtInicio").focus();
		return false;
	}

	if(!patternData.test(dtInicio.value)){
	    alert("Digite a data no formato Dia/Mês/Ano");
	    document.getElementById("dtInicio").focus();
	    return false;
	}
	
	if (dtFim.value == ""){
		alert ('Obrigatório informar a data de inicio');    
		document.getElementById("dtFim").focus();
		return false;
	}
	
	if(!patternData.test(dtFim.value)){
	    alert("Digite a data no formato Dia/Mês/Ano");
	    document.getElementById("dtFim").focus();
	    return false;
	}
	
	$.ajax({
        url: "saegopBuscarPontos",
        type: 'POST',
        data: {'dt-inicio' : dtInicio.value, 'dt-fim' : dtFim.value},
        dataType: "json",
        success: function (data) { 
        	carregarPontos(data);
        	return false;
        },
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });

})
 

function carregarPontos(pontosJson) {
	 
	deleteMarkers();
	$.each(pontosJson, function(index, ponto) {
		
		var dia    = ponto.dtOcorrencia.dayOfMonth;
		var mes    = ponto.dtOcorrencia.month+1;
		
		if(dia<10){
			dia='0'+dia;
	    } 
		
	    if(mes<10){
	    	mes='0'+mes;
	    }
		
		var dtOcorrenciaFormat = dia + '/' + mes + '/' + ponto.dtOcorrencia.year;
		
		var content = 	'<div id="iw-container">' +
							'<div class="iw-content">' +
								'<div class="iw-subTitle">' + dtOcorrenciaFormat + '</div>' +
								'<p>' + ponto.dsFato + ' </p>' +
							'</div>' +
						'</div>';
		
			var infowindow = new google.maps.InfoWindow({
			    content: content,
			    maxWidth: 350
			  });
			
			var marker = new google.maps.Marker({
                position: new google.maps.LatLng(ponto.latitude, ponto.longitude),
                map: map,
                icon: 'img/marcador.png'
            });
			
			google.maps.event.addListener(marker, 'click', function() {
			    infowindow.open(map,marker);
			});
			
			google.maps.event.addListener(map, 'click', function() {
			    infowindow.close();
			});
			
			 google.maps.event.addListener(infowindow, 'domready', function() {
				    // Reference to the DIV that wraps the bottom of infowindow
				    var iwOuter = $('.gm-style-iw');

				    var iwBackground = iwOuter.prev();

				    iwBackground.children(':nth-child(2)').css({'display' : 'none'});

				    iwBackground.children(':nth-child(4)').css({'display' : 'none'});

				    iwOuter.parent().parent().css({left: '115px'});

				    iwBackground.children(':nth-child(1)').attr('style', function(i,s){ return s + 'left: 76px !important;'});

				    iwBackground.children(':nth-child(3)').attr('style', function(i,s){ return s + 'left: 76px !important;'});

				    iwBackground.children(':nth-child(3)').find('div').children().css({'box-shadow': 'rgba(72, 181, 233, 0.6) 0px 1px 6px', 'z-index' : '1'});

				    var iwCloseBtn = iwOuter.next();

				    iwCloseBtn.css({opacity: '1', right: '38px', top: '3px', border: '7px solid #48b5e9', 'border-radius': '13px', 'box-shadow': '0 0 5px #3990B9'});

				    if($('.iw-content').height() < 140){
				      $('.iw-bottom-gradient').css({display: 'none'});
				    }

				    iwCloseBtn.mouseout(function(){
				      $(this).css({opacity: '1'});
				    });
				  });
			
            markers.push(marker);
 
        });

}

function setMapOnAll(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}

function clearMarkers() {
  setMapOnAll(null);
}

function deleteMarkers() {
  clearMarkers();
  markers = [];
}