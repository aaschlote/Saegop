package br.com.furb.test;

import java.io.File;

import br.com.furb.sisp.ImportarFileSisp;

public class TestImpFileSisp {
	
	public static void main(String[] args) {
		
		try {
			File arquivo = new File("Relatorio_2016.csv");
			if	(!arquivo.exists()){
				System.out.println("Arquivo não existe");
				System.exit(0);
			}
			
			File[] arquivos = new File[1];
			
			arquivos[0] = arquivo;
			
			ImportarFileSisp importSisp = new ImportarFileSisp();
			importSisp.importarArquivosSisp(arquivos);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
