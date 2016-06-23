package br.com.furb.model;

public enum NaturezaOcorrenciaSisp {

	FATO_ATIPICO(0),
	AMEACA(1),
	FURTO(2),
	INJURIA(3),
	DANO(4),
	ESTELIONATO(5),
	LESAO_CORPORAL_AGRESSAO(6),
	PERTUBACAO(7),
	DIFAMCAO(8),
	CALUNIA(9),
	ROUBO(10),
	FUGA(11),
	POSSE_TRAFICO_DROGAS(12),
	MANDATO_PRISAO(13),
	REAPARECIMENTO_PESSOA(14),
	DESAPARECIMENTO_PESSOA(15),
	HOMICIDIO(16),
	ESTUPRO(17),
	EMBRIAGUEZ(18),
	ACIDENTE_TRANSITO(19),
	ABANDONO_INCAPAZ(20),
	CRIME_ELETRONICO(21),
	INCENDIO(22),
	DISPARO_ARMA_FOGO(23),
	ARROMBAMENTO(24),
	DESACATO_DESOBEDIENCIA_AUTOR(25),
	CRIME_SEXUAL(26),
	NAO_IDENTIFICADO(27);
	
	private int idNatureza;
	
	private NaturezaOcorrenciaSisp(int idNatureza) {
		this.idNatureza = idNatureza;
	}

	public int getNatureza() {
		return idNatureza;
	}
	
	public String toString(){
		switch(this) {
	      case FATO_ATIPICO: return "Fato atípico";
	      case AMEACA: return "Ameaça";
	      case FURTO: return "Furto";
	      case INJURIA: return "Injúria";
	      case DANO: return "Dano";
	      case ESTELIONATO: return "Estelionato";
	      case LESAO_CORPORAL_AGRESSAO: return "Lesão Corporal/Agressão";
	      case PERTUBACAO: return "Perturbação";
	      case DIFAMCAO: return "Difamação";
	      case CALUNIA: return "Calúnia";
	      case ROUBO: return "Roubo";
	      case FUGA: return "Fuga";
	      case POSSE_TRAFICO_DROGAS: return "Posse/Tráfico de drogas";
	      case MANDATO_PRISAO: return "Mandato de prisão";
	      case REAPARECIMENTO_PESSOA: return "Reaparecimento de pessoa";
	      case DESAPARECIMENTO_PESSOA: return "Desaparecimento de pessoa";
	      case HOMICIDIO: return "Homicídio/Suicídios/Mortes adversas";
	      case ESTUPRO: return "Estupro";
	      case EMBRIAGUEZ: return "Embriaguez";
	      case ACIDENTE_TRANSITO: return "Acidente de trânsito";
	      case ABANDONO_INCAPAZ: return "Abandono de incapaz";
	      case CRIME_ELETRONICO: return "Crimes eletrônicos";
	      case INCENDIO: return "Incêndio";
	      case DISPARO_ARMA_FOGO: return "Disparo / Porte de arma de fogo";
	      case ARROMBAMENTO: return "Arrombamento";
	      case DESACATO_DESOBEDIENCIA_AUTOR: return "Desacato/Desobediência pela autoridade";
	      case CRIME_SEXUAL: return "Crime sexual";
	      case NAO_IDENTIFICADO: return "Não identificado";
	      
	      default: throw new IllegalArgumentException();
	    }
		
	}
	
	public static NaturezaOcorrenciaSisp getNatureza(int index) {
      for (NaturezaOcorrenciaSisp n : NaturezaOcorrenciaSisp.values()) {
          if (n.getNatureza() == index) return n;
      }
      throw new IllegalArgumentException("Leg not found. Amputated?");
   }
	
}
