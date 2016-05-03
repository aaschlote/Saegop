package br.com.furb.textMining;

import br.com.furb.model.NaturezaOcorrencia;

public class ClassificacaoNatureza {

	public NaturezaOcorrencia getNaturezaOcorrencia(String[] acao, String dsFato) {

		String acaoPolicial = acao[1];
		String objetoPolicial = acao[2];

		switch (acaoPolicial) {
		case "arromb":
			return NaturezaOcorrencia.ARROMBAMENTO;

		case "assalt":
			return NaturezaOcorrencia.ASSALTO;

		case "traf":
			return NaturezaOcorrencia.TRAFICO;

		case "estupr":
			return NaturezaOcorrencia.ESTUPRO;

		case "furt":
		case "furto":
			return NaturezaOcorrencia.FURTO;

		case "homicidi":
			return NaturezaOcorrencia.HOMICIDIO;

		case "agred":
		case "agressa":
		case "desfer":
		case "fer":
		case "corpor":
		case "esfaque":
			return NaturezaOcorrencia.LESAO_CORPORAL_AGRESSAO;

		case "perturbaca":
		case "perturb":
			return NaturezaOcorrencia.PERTUBACAO;

		case "roub":
			return NaturezaOcorrencia.ROUBO;

		case "dispar":
			return NaturezaOcorrencia.DISPARO_ARMA_FOGO;
			
		case "embriagu":
			return NaturezaOcorrencia.EMBRIAGUEZ;
			
		case "estelionat":
			return NaturezaOcorrencia.ESTELIONATO;

		case "forag":
			return NaturezaOcorrencia.PRISAO_FORAGIDO;

		case "port":

			switch (objetoPolicial) {
			case "arm":
			case "revolv":
			case "pistol":
				return NaturezaOcorrencia.PORTE_ARMA;

			case "maconh":
			case "drog":
			case "crack":
			case "cocain":
			case "pedr":
			case "apreensa":
			case "entorpec":
				return NaturezaOcorrencia.PORTE_DROGAS;

			default:
				return NaturezaOcorrencia.NAO_IDENTIFICADO;
			}

		case "poss":

			switch (objetoPolicial) {
			
			case "arm":
			case "revolv":
			case "pistol":
				return NaturezaOcorrencia.PORTE_ARMA;

			case "maconh":
			case "drog":
			case "crack":
			case "cocain":
			case "pedr":
			case "apreensa":
			case "entorpec":
				return NaturezaOcorrencia.PORTE_DROGAS;
				
			case "veicul":
			case "mot":
			case "motociclet":
			case "carr":
				return NaturezaOcorrencia.ENCONTRADO_VEICULO_ROUBADADO;

			default:
				return NaturezaOcorrencia.NAO_IDENTIFICADO;
			}

		case "substanc":
			switch (objetoPolicial) {
			case "maconh":
			case "drog":
			case "crack":
			case "cocain":
			case "pedr":
			case "apreensa":
			case "entorpec":
				return NaturezaOcorrencia.PORTE_DROGAS;

			default:
				return NaturezaOcorrencia.NAO_IDENTIFICADO;
			}

		case "possu":
			switch (objetoPolicial) {
			case "maconh":
			case "drog":
			case "crack":
			case "cocain":
			case "pedr":
			case "apreensa":
			case "entorpec":
				return NaturezaOcorrencia.PORTE_DROGAS;
				
			case "pris":
			case "prisa":
				return NaturezaOcorrencia.MANDATO_PRISAO;
				
			case "arm":
			case "revolv":
			case "pistol":
				return NaturezaOcorrencia.PORTE_ARMA;
				
			case "veicul":
			case "mot":
			case "motociclet":
			case "carr":
				return NaturezaOcorrencia.ENCONTRADO_VEICULO_ROUBADADO;

			default:
				return NaturezaOcorrencia.NAO_IDENTIFICADO;
			}

		case "encontr":
			switch (objetoPolicial) {
			case "veicul":
			case "mot":
			case "motociclet":
			case "carr":
				return NaturezaOcorrencia.ENCONTRADO_VEICULO_ROUBADADO;

			case "embriag":
				return NaturezaOcorrencia.EMBRIAGUEZ;
				
			case "cadav": case "obit":
				return NaturezaOcorrencia.ENCONTRADO_CADAVER_HUMANO;
				
			case "arm":
			case "revolv":
			case "pistol":
				return NaturezaOcorrencia.PORTE_ARMA;
				
			case "maconh":
			case "drog":
			case "crack":
			case "cocain":
			case "apreensa":
			case "pedr":
			case "entorpec":
				return NaturezaOcorrencia.PORTE_DROGAS;	
				
			case "pris":
			case "prisa":
				return NaturezaOcorrencia.MANDATO_PRISAO;

			default:
				return NaturezaOcorrencia.NAO_IDENTIFICADO;
			}
			
		case "mand":
			switch (objetoPolicial) {
			case "pris":
			case "prisa":
			case "apreensa":	
				return NaturezaOcorrencia.MANDATO_PRISAO;

			default:
				return NaturezaOcorrencia.NAO_IDENTIFICADO;
			}
			
		case "not":
			switch (objetoPolicial) {
			case "fals":
				return NaturezaOcorrencia.NOTA_FALSA;

			default:
				return NaturezaOcorrencia.NAO_IDENTIFICADO;
			}

		default:
			return NaturezaOcorrencia.NAO_IDENTIFICADO;
		}

	}

}
