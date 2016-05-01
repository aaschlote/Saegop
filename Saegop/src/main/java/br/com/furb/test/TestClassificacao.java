package br.com.furb.test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.persistence.Query;

import ptstemmer.Stemmer;
import ptstemmer.implementations.OrengoStemmer;
import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicial;
import br.com.furb.textMining.AnalisarInformacao;

public class TestClassificacao {
	
	public static void main(String[] args) {
		
		try {
			ConnectionDB conection = new ConnectionDB();
			
			String sql = "FROM AtividadePolicial a where length(dsFato) > 10 ";
					
			Query query = conection.getManager().createQuery(sql);
			
			List<AtividadePolicial> atividadePoliciais = query.getResultList();
			
			Stemmer stemmer = new OrengoStemmer();
			
			File arquivo = new File("DADOS_OCORR.txt");
			
			if	(arquivo.exists()){
				arquivo.delete();
			}
			arquivo.createNewFile();
			
			FileWriter writter = new FileWriter(arquivo);
			
			for (AtividadePolicial atividadePolicial : atividadePoliciais) {
				AnalisarInformacao analisar = new AnalisarInformacao("", "", "", null, stemmer);
				
				String itemAnalisado = analisar.classificarOcorrencia(atividadePolicial.getDsFato());
				
				writter.write(itemAnalisado+";"+atividadePolicial.getDsFato()+"\n");
				
			}
			System.out.println("FIM");
			writter.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
		
		
		
	}

}


