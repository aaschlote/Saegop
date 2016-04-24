package br.com.furb.test;

import java.io.File;

import br.com.furb.sisp.ImportarFileSisp;

public class TestImpFileSisp {
	
	public static void main(String[] args) {
		
		try {
			
			
			File[] arquivos = new File[4];
			
			arquivos[0] = new File("Relatorio_2016.csv");
			arquivos[1] = new File("Relatorio_2015.csv");
			arquivos[2] = new File("Relatorio_2014.csv");
			arquivos[2] = new File("Relatorio_2013.csv");
			
			ImportarFileSisp importSisp = new ImportarFileSisp();
			importSisp.importarArquivosSisp(arquivos);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
