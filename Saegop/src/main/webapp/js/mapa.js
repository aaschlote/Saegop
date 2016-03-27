var map;
var marker;
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

function carregarPontos() {
	 
    $.getJSON('json/pontos.json', function(pontos) {
 
        $.each(pontos, function(index, ponto) {
 
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(ponto.latitude, ponto.longitude),
                title: "Meu ponto personalizado! :-D",
                map: map,
                icon: 'img/marcador.png'
            });
 
        });
 
    });
 
}
 