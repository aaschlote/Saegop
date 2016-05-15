package br.com.furb.sisp;

import java.util.Collection;

import ptstemmer.Stemmer;
import br.com.furb.model.NaturezaOcorrenciaSisp;
import br.com.furb.textMining.Word;
import br.com.furb.textMining.WordTokenizer;

public class ClassificarSisp {

	private WordTokenizer tokenizer = new WordTokenizer();
	private Stemmer stemmer;

	public ClassificarSisp(Stemmer stemmer) {
		this.stemmer = stemmer;
	}

	public NaturezaOcorrenciaSisp classificar(String dsFato) throws Exception {

		String acao = "nao-identifi";

		Collection<Word> words = tokenizer.getWords(dsFato, stemmer);

		for (Word word : words) {

			if (obterCrimePolicial(word.toString())) {
				acao = word.toString();
				break;
			}
			continue;

		}

		return getNaturezaSisp(acao);
	}

	private boolean obterCrimePolicial(String acao) {
		return acao.equalsIgnoreCase("atip") || acao.equalsIgnoreCase("ameac")
				|| acao.equalsIgnoreCase("furt")
				|| acao.equalsIgnoreCase("injur")
				|| acao.equalsIgnoreCase("dan") || acao.equalsIgnoreCase("via")
				|| acao.equalsIgnoreCase("estelionat")
				|| acao.equalsIgnoreCase("corpor")
				|| acao.equalsIgnoreCase("perturb")
				|| acao.equalsIgnoreCase("perturbaca")
				|| acao.equalsIgnoreCase("difamaca")
				|| acao.equalsIgnoreCase("calun")
				|| acao.equalsIgnoreCase("caluni")
				|| acao.equalsIgnoreCase("roub")
				|| acao.equalsIgnoreCase("fug")
				|| acao.equalsIgnoreCase("poss")
				|| acao.equalsIgnoreCase("traf")
				|| acao.equalsIgnoreCase("mand")
				|| acao.equalsIgnoreCase("reaparec")
				|| acao.equalsIgnoreCase("eletron")
				|| acao.equalsIgnoreCase("desaparec")
				|| acao.equalsIgnoreCase("incendi")
				|| acao.equalsIgnoreCase("estupr")
				|| acao.equalsIgnoreCase("mort")
				|| acao.equalsIgnoreCase("fraud")
				|| acao.equalsIgnoreCase("port")
				|| acao.equalsIgnoreCase("falsificaca")
				|| acao.equalsIgnoreCase("falsidad")
				|| acao.equalsIgnoreCase("abandon")
				|| acao.equalsIgnoreCase("dispar")
				|| acao.equalsIgnoreCase("suicidi")
				|| acao.equalsIgnoreCase("vand")
				|| acao.equalsIgnoreCase("latrocini")
				|| acao.equalsIgnoreCase("embriagu")
				|| acao.equalsIgnoreCase("ilicit")
				|| acao.equalsIgnoreCase("acid")
				|| acao.equalsIgnoreCase("violenc")
				|| acao.equalsIgnoreCase("dirig")
				|| acao.equalsIgnoreCase("rac")
				|| acao.equalsIgnoreCase("invasa")
				|| acao.equalsIgnoreCase("homofob")
				|| acao.equalsIgnoreCase("comunicaca")
				|| acao.equalsIgnoreCase("apropriaca")
				|| acao.equalsIgnoreCase("trat")
				|| acao.equalsIgnoreCase("desacat")
				|| acao.equalsIgnoreCase("desobedienc")
				|| acao.equalsIgnoreCase("libidin")
				|| acao.equalsIgnoreCase("obscen")
				|| acao.equalsIgnoreCase("sex")
				|| acao.equalsIgnoreCase("prostituica")
				|| acao.equalsIgnoreCase("constrang")
				|| acao.equalsIgnoreCase("homicidi");
	}

	public NaturezaOcorrenciaSisp getNaturezaSisp(String acao) {

		switch (acao) {
		case "atip":
			return NaturezaOcorrenciaSisp.FATO_ATIPICO;
		case "ameac":
		case "constrang":	
			return NaturezaOcorrenciaSisp.AMEACA;
		case "furt":
		case "apropriaca":
			return NaturezaOcorrenciaSisp.FURTO;
		case "injur":
		case "rac":
		case "homofob":
			return NaturezaOcorrenciaSisp.INJURIA;
		case "via":
		case "dan":
		case "ilicit":
		case "vand":
			return NaturezaOcorrenciaSisp.DANO;
		case "estelionat":
		case "falsificaca":
		case "falsidad":
		case "fraud":
			return NaturezaOcorrenciaSisp.ESTELIONATO;
		case "corpor":
		case "violenc":
		case "trat":
			return NaturezaOcorrenciaSisp.LESAO_CORPORAL_AGRESSAO;
		case "perturb":
		case "perturbaca":
			return NaturezaOcorrenciaSisp.PERTUBACAO;
		case "difamaca":
			return NaturezaOcorrenciaSisp.DIFAMCAO;
		case "calun":
		case "caluni":
			return NaturezaOcorrenciaSisp.CALUNIA;
		case "roub":
			return NaturezaOcorrenciaSisp.ROUBO;
		case "fug":
			return NaturezaOcorrenciaSisp.FUGA;
		case "poss":
		case "traf":
			return NaturezaOcorrenciaSisp.POSSE_TRAFICO_DROGAS;
		case "mand":
		case "comunicaca":
			return NaturezaOcorrenciaSisp.MANDATO_PRISAO;
		case "reaparec":
			return NaturezaOcorrenciaSisp.REAPARECIMENTO_PESSOA;
		case "desaparec":
			return NaturezaOcorrenciaSisp.DESAPARECIMENTO_PESSOA;
		case "mort":
		case "latrocini":
		case "suicidi":
		case "homicidi":
			return NaturezaOcorrenciaSisp.HOMICIDIO;
		case "estupr":
			return NaturezaOcorrenciaSisp.ESTUPRO;
		case "embriagu":
		case "dirig":
			return NaturezaOcorrenciaSisp.EMBRIAGUEZ;
		case "acid":
			return NaturezaOcorrenciaSisp.ACIDENTE_TRANSITO;
		case "abandon":
			return NaturezaOcorrenciaSisp.ABANDONO_INCAPAZ;
		case "eletron":
			return NaturezaOcorrenciaSisp.CRIME_ELETRONICO;
		case "incendi":
			return NaturezaOcorrenciaSisp.INCENDIO;
		case "dispar":
		case "port":
			return NaturezaOcorrenciaSisp.DISPARO_ARMA_FOGO;
		case "invasa":
			return NaturezaOcorrenciaSisp.ARROMBAMENTO;
		case "desacat":
		case "desobedienc":
			return NaturezaOcorrenciaSisp.DESACATO_DESOBEDIENCIA_AUTOR;
		case "libidin":
		case "obscen":
		case "sex":
		case "prostituica":
			return NaturezaOcorrenciaSisp.CRIME_SEXUAL;

		default:
			return NaturezaOcorrenciaSisp.NAO_IDENTIFICADO;
		}
	}

}
