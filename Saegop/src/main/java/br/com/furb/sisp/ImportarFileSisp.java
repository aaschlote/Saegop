package br.com.furb.sisp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;

import ptstemmer.Stemmer;
import ptstemmer.implementations.OrengoStemmer;
import br.com.furb.controller.CriarAtividadePolicialSisp;
import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicialSisp;
import br.com.furb.textMining.AnalisarInformacao;

import com.google.maps.GeoApiContext;

public class ImportarFileSisp {

	GeoApiContext context = new GeoApiContext()
			.setApiKey("AIzaSyClyg2c5hxqJotZHUhAPx8oufyvgzlaix4");
	ConnectionDB conection = new ConnectionDB();
	Stemmer stemmer;
	final static String separador = ";";

	public void importarArquivosSisp(File[] arquivos) throws Exception {

		stemmer = new OrengoStemmer();
		BufferedReader br = null;
		String line = "";
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		AnalisarInformacao analisaInfo = new AnalisarInformacao("", "", "",
				context, stemmer);
		
		removeAllInstances();

		for (int i = 0; i < arquivos.length; i++) {
			File file = arquivos[i];
			ArrayList<AtividadePolicialSisp> listaAtividade = new ArrayList<AtividadePolicialSisp>();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));

			while ((line = br.readLine()) != null) {

				String[] info = line.split(separador);
				AtividadePolicialSisp atividadeSisp = new AtividadePolicialSisp();
				atividadeSisp.setDsBairro(info[2]);
				atividadeSisp.setDsEndereco(info[3]);
				atividadeSisp.setHrOcorrencia(info[5]);
				atividadeSisp.setDsTipificacao(info[6]);

				if (!info[4].equalsIgnoreCase("")) {

					Calendar dtOcorrenciaCal = Calendar.getInstance();
					Date dtOcorrencia = formato.parse(info[4]);
					dtOcorrenciaCal.setTime(dtOcorrencia);
					atividadeSisp.setDtOcorrencia(dtOcorrenciaCal);
				}

				listaAtividade.add(atividadeSisp);
			}
			
			Map<String, double[]> localMap = new HashMap<String, double[]>();

			for (AtividadePolicialSisp atividadePolicialSisp : listaAtividade) {

				analisaInfo.setLatitude(0);
				analisaInfo.setLongitude(0);
				
				if	(!atividadePolicialSisp.getDsEndereco().equalsIgnoreCase("")){
					if	(localMap.containsKey(atividadePolicialSisp.getDsEndereco())){
						
						double[] localMatriz = localMap.get(atividadePolicialSisp.getDsEndereco());
						atividadePolicialSisp.setLatitude(localMatriz[0]);
						atividadePolicialSisp.setLongitude(localMatriz[1]);
						
					}else{
						analisaInfo.montarLocalOcorrencia(atividadePolicialSisp
								.getDsEndereco());
						
						atividadePolicialSisp.setLatitude(analisaInfo.getLatitude());
						atividadePolicialSisp.setLongitude(analisaInfo.getLongitude());
						
						double[] localMatriz = new double[2];
						localMatriz[0] = analisaInfo.getLatitude();
						localMatriz[1] = analisaInfo.getLongitude();
						
						localMap.put(atividadePolicialSisp.getDsEndereco(),localMatriz);
					}										
				}				
			}

			System.out.println("iniciou");
			CriarAtividadePolicialSisp criarAtiviSisp = new CriarAtividadePolicialSisp(listaAtividade, conection);
			criarAtiviSisp.inserir();
			System.out.println("terminou");
			
		}
	}

	private final void removeAllInstances() {
		conection.getManager().getTransaction().begin();
		Query query = conection.getManager().createQuery(
				"from AtividadePolicialSisp as a");
		for (Object obj : query.getResultList()) {
			conection.getManager().remove(obj);
		}
		conection.getManager().getTransaction().commit();
	}

}
