package br.com.furb.textMining;

import java.util.Collection;



import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class AnalisarInformacao {
	
	private String dsFato;
	private String dsBairro;
	private String dsLocal;
	private double longitude;
	private double latitude;
	private GeoApiContext context;
	
	public AnalisarInformacao(String dsFato, String dsBairro, GeoApiContext context) {
		this.dsFato		= dsFato;
		this.dsBairro	= dsBairro;
		this.context 	= context;
	}

	public void processar() throws Exception{
		String dsBairroAnt = getDsBairro();
		
		if	(!dsBairro.equalsIgnoreCase("")){
			String dsBairroAux = dsBairro.substring(dsBairro.indexOf("Bairro:")+7);
			dsBairroAux = dsBairroAux.replace(",", "");
			dsBairroAux = dsBairroAux.replace(".", "");
			if	(dsBairroAux.length() > 18){
				dsBairroAux = dsBairroAux.substring(0,18); 
			}
			
			setDsBairro(dsBairroAux);
		}
		
		if	((!dsBairroAnt.equalsIgnoreCase("")) &&
			(dsBairroAnt.length() > 7) &&
			(dsBairroAnt.indexOf("Bairro:") > 0)){
			String dsLocalAux = dsBairroAnt.substring(6,dsBairroAnt.indexOf("Bairro:"));
			dsLocalAux = dsLocalAux.replace(",", "");
			dsLocalAux = dsLocalAux.replace("R.", "Rua");
			dsLocalAux = dsLocalAux.replace("R:", "Rua");
			dsLocalAux = dsLocalAux.replace(":", "");
			dsLocalAux = dsLocalAux.replace(".", "");
			
			setDsLocal(dsLocalAux);
		}
		
		if	((!getDsLocal().equalsIgnoreCase("")) &&
			(!getDsLocal().equalsIgnoreCase("null"))){
			buscarLongitudeLatitude(getDsLocal());
		}
	}
	
	public void buscarLongitudeLatitude(String dsLocal) throws Exception{
		GeocodingResult[] results = GeocodingApi.geocode(context,
				dsLocal + ", Blumenau").await();
		
		for (GeocodingResult geocodingResult : results) {
			setLatitude(geocodingResult.geometry.location.lat);
			setLongitude(geocodingResult.geometry.location.lng);
		}
	}

	public String getDsFato() {
		return dsFato;
	}

	public void setDsFato(String dsFato) {
		this.dsFato = dsFato;
	}

	public String getDsBairro() {
		return dsBairro;
	}

	public void setDsBairro(String dsBairro) {
		this.dsBairro = dsBairro;
	}

	public String getDsLocal() {
		return dsLocal;
	}

	public void setDsLocal(String dsLocal) {
		this.dsLocal = dsLocal;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	

}
