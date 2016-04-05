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
		
		var dia    = ponto.dtOcorrencia.dayOfMonth;
		var mes    = ponto.dtOcorrencia.month;
		
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

				    /* Since this div is in a position prior to .gm-div style-iw.
				     * We use jQuery and create a iwBackground variable,
				     * and took advantage of the existing reference .gm-style-iw for the previous div with .prev().
				    */
				    var iwBackground = iwOuter.prev();

				    // Removes background shadow DIV
				    iwBackground.children(':nth-child(2)').css({'display' : 'none'});

				    // Removes white background DIV
				    iwBackground.children(':nth-child(4)').css({'display' : 'none'});

				    // Moves the infowindow 115px to the right.
				    iwOuter.parent().parent().css({left: '115px'});

				    // Moves the shadow of the arrow 76px to the left margin.
				    iwBackground.children(':nth-child(1)').attr('style', function(i,s){ return s + 'left: 76px !important;'});

				    // Moves the arrow 76px to the left margin.
				    iwBackground.children(':nth-child(3)').attr('style', function(i,s){ return s + 'left: 76px !important;'});

				    // Changes the desired tail shadow color.
				    iwBackground.children(':nth-child(3)').find('div').children().css({'box-shadow': 'rgba(72, 181, 233, 0.6) 0px 1px 6px', 'z-index' : '1'});

				    // Reference to the div that groups the close button elements.
				    var iwCloseBtn = iwOuter.next();

				    // Apply the desired effect to the close button
				    iwCloseBtn.css({opacity: '1', right: '38px', top: '3px', border: '7px solid #48b5e9', 'border-radius': '13px', 'box-shadow': '0 0 5px #3990B9'});

				    // If the content of infowindow not exceed the set maximum height, then the gradient is removed.
				    if($('.iw-content').height() < 140){
				      $('.iw-bottom-gradient').css({display: 'none'});
				    }

				    // The API automatically applies 0.7 opacity to the button after the mouseout event. This function reverses this event to the desired value.
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