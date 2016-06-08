package br.com.furb.test;


import br.com.furb.model.NaturezaOcorrencia;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class TestEndereco {
	
	public static void main(String[] args) {
		
		NaturezaOcorrencia natureza =  NaturezaOcorrencia.getNatureza(10);
		
		System.out.println(natureza.toString());
		
		
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyClyg2c5hxqJotZHUhAPx8oufyvgzlaix4");
		
		String dsLocalAux = "PRESIDENTE JOHN KENNEDY";
		
		dsLocalAux = dsLocalAux.replace("Dr", "Doutor");
		//dsLocalAux = dsLocalAux.replace("Prof", "Professor");
		dsLocalAux = dsLocalAux.replace("Exp", "Expressa");
		dsLocalAux = dsLocalAux.replace("02 de", "2 de");
		
		dsLocalAux = dsLocalAux.split("\\–", -1)[0];
		dsLocalAux = dsLocalAux.split(" – ")[0];
		
		if	(dsLocalAux.indexOf("(") > 0){
			dsLocalAux = dsLocalAux.substring(0,dsLocalAux.indexOf("(")-1); 
		}
		
		if	(dsLocalAux.indexOf("/") > 0){
			dsLocalAux = dsLocalAux.substring(0,dsLocalAux.indexOf("/")-1); 
		}
		
		if	(dsLocalAux.indexOf("n°") > 0){
			dsLocalAux = dsLocalAux.substring(0,dsLocalAux.indexOf("n°")-1); 
		}
		
		System.out.println(dsLocalAux);
		
		try {
			GeocodingResult[] results = GeocodingApi.geocode(context,
					dsLocalAux + ", Blumenau").await();
			
			for (GeocodingResult geocodingResult : results) {
				System.out.println(geocodingResult.geometry.location.lat);
				System.out.println(geocodingResult.geometry.location.lat);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	

}
