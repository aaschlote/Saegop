package br.com.furb.textMining;

import java.text.SimpleDateFormat;
import java.util.Collection;


import java.util.Date;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class AnalisarInformacao {
	
	private String dsFato;
	private String dsBairro;
	private String dsLocal = "";
	private String dsDtOcorrencia;
	private double longitude;
	private double latitude;
	private Date dtOcorrencia;
	private GeoApiContext context;
	private WordTokenizer tokenizer = new WordTokenizer();
	
	public AnalisarInformacao(String dsFato, String dsBairro, String dsDtOcorrencia, GeoApiContext context) {
		this.dsFato		= dsFato;
		this.dsBairro	= dsBairro;
		this.dsDtOcorrencia = dsDtOcorrencia;
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
		
		if	(getDsLocal().equalsIgnoreCase("")){
			setDsLocal(getDsBairro());
		}
		
		try {
			if	((!getDsLocal().equalsIgnoreCase("")) &&
				(!getDsLocal().equalsIgnoreCase("null"))){
				buscarLongitudeLatitude(getDsLocal());
			}
		} catch (Exception e) {
			System.out.println(getDsLocal() + " - " + e.getMessage());
		}
		
		
		if	((!dsDtOcorrencia.equalsIgnoreCase("")) &&
				(dsDtOcorrencia.length() > 8)){
			montarDataOcorrencia(dsDtOcorrencia);
		}
		
	}
	
	public void buscarLongitudeLatitude(String dsLocal) throws Exception{
		/*GeocodingResult[] results = GeocodingApi.geocode(context,
				dsLocal + ", Blumenau").await();
		
		for (GeocodingResult geocodingResult : results) {
			setLatitude(geocodingResult.geometry.location.lat);
			setLongitude(geocodingResult.geometry.location.lng);
		}*/
	}
	
	public void montarDataOcorrencia(String dsDtOcorrencia) throws Exception{
		int qtPassagens = 0;
		Collection<Word> dataWords = tokenizer.getWords(dsDtOcorrencia);
		String nrDia = "";
		String nrMes = "";
		String nrAno = "";
		for (Word word : dataWords) {
			if	(qtPassagens == 0){
				nrDia = word.toString();
				nrDia = (nrDia.length() == 1 ? "0"+nrDia : nrDia);
			}else if (qtPassagens == 1){
				nrMes = getMesAno(word.toString());
			}else if (qtPassagens == 2){
				nrAno = word.toString();
			}
			qtPassagens++;
		}
		
		if	((!nrDia.equalsIgnoreCase("")) &&
				(!nrMes.equalsIgnoreCase(""))&&
				(!nrAno.equalsIgnoreCase(""))){
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			dtOcorrencia = formato.parse(nrDia+"/"+nrMes+"/"+nrAno);
		}
	}
	
	private final static String getMesAno(String mes){
		 if	(mes.equalsIgnoreCase("jan")){
			 return "01";
		 }else if(mes.equalsIgnoreCase("fever")){
			 return "02";
		 }else if(mes.equalsIgnoreCase("marc")){
			 return "03";
		 }else if(mes.equalsIgnoreCase("abril")){
			 return "04";
		 }else if(mes.equalsIgnoreCase("mai")){
			 return "05";
		 }else if(mes.equalsIgnoreCase("junh")){
			 return "06";
		 }else if(mes.equalsIgnoreCase("julh")){
			 return "07";
		 }else if(mes.equalsIgnoreCase("agost")){
			 return "08";
		 }else if(mes.equalsIgnoreCase("setembr")){
			 return "09";
		 }else if(mes.equalsIgnoreCase("outubr")){
			 return "10";
		 }else if(mes.equalsIgnoreCase("novembr")){
			 return "11";
		 }else if(mes.equalsIgnoreCase("dezembr")){
			 return "12";
		 }
	     return "";
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

	public Date getDtOcorrencia() {
		return dtOcorrencia;
	}

	public void setDtOcorrencia(Date dtOcorrencia) {
		this.dtOcorrencia = dtOcorrencia;
	}
	

}
