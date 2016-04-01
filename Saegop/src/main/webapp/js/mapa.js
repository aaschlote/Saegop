var map;
var markers = [];
var geocoder;

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

$('#buscarDados').on('click', function (e) {
	
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
 
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(ponto.latitude, ponto.longitude),
                title: ponto.dsFato,
                map: map,
                icon: 'img/marcador.png'
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