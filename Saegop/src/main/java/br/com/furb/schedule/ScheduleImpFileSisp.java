package br.com.furb.schedule;

import java.io.File;

import br.com.furb.sisp.ImportarFileSisp;

public class ScheduleImpFileSisp {
	
	public static void main(String[] args) {
		
		try {
			
			
			File[] arquivos = new File[7];
			
			arquivos[0] = new File("Relatorio_2016.csv");
			arquivos[1] = new File("Relatorio_2015.csv");
			arquivos[2] = new File("Relatorio_2014.csv");
			arquivos[3] = new File("Relatorio_2013.csv");
			arquivos[4] = new File("Relatorio_2012.csv");
			arquivos[5] = new File("Relatorio_2011.csv");
			
			ImportarFileSisp importSisp = new ImportarFileSisp();
			importSisp.importarArquivosSisp(arquivos);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
