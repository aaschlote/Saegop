package br.com.furb.model;

public enum NaturezaOcorrencia {
	
	ARROMBAMENTO(0),
	ASSALTO(1),
	TRAFICO(2),
	ESTUPRO(3),
	FURTO(4),
	HOMICIDIO(5),
	LESAO_CORPORAL_AGRESSAO(6),
	PERTUBACAO(7),
	PORTE_ARMA(8),
	PORTE_DROGAS(9),
	ROUBO(10),
	ENCONTRADO_VEICULO_ROUBADADO(11),
	DISPARO_ARMA_FOGO(12),
	EMBRIAGUEZ(13),
	ESTELIONATO(14),
	PRISAO_FORAGIDO(15),
	MANDATO_PRISAO(16),
	NOTA_FALSA(17),
	ENCONTRADO_CADAVER_HUMANO(18),
	NAO_IDENTIFICADO(19);
	
	private int idNatureza;
	
	private NaturezaOcorrencia(int idNatureza) {
		this.idNatureza = idNatureza;
	}

	public int getNatureza() {
		return idNatureza;
	}
	
	public String toString(){
		switch(this) {
	      case ARROMBAMENTO: return "Arrombamento";
	      case ASSALTO: return "Assalto";
	      case TRAFICO: return "Tráfico";
	      case ESTUPRO: return "Estupro";
	      case FURTO: return "Furto";
	      case HOMICIDIO: return "Homicído";
	      case LESAO_CORPORAL_AGRESSAO: return "Lesçao Corporal/Agressão";
	      case PERTUBACAO: return "Pertubação";
	      case PORTE_ARMA: return "Porte de Arma";
	      case PORTE_DROGAS: return "Porte de Drogas";
	      case ROUBO: return "Roubo";
	      case ENCONTRADO_VEICULO_ROUBADADO: return "Encontrado veículo roubado";
	      case DISPARO_ARMA_FOGO: return "Disparo de arma de fogo";
	      case EMBRIAGUEZ: return "Embriaguez";
	      case ESTELIONATO: return "Estelionato";
	      case PRISAO_FORAGIDO: return "Prisão de foragido";
	      case MANDATO_PRISAO: return "Mandato de prisão";
	      case NOTA_FALSA: return "Pagamento com moeda falsa";
	      case ENCONTRADO_CADAVER_HUMANO: return "Encontrado cadáver humano";
	      case NAO_IDENTIFICADO: return "Arrombamento";
	      
	      default: throw new IllegalArgumentException();
	    }
		
	}
	
	public static NaturezaOcorrencia getNatureza(int index) {
      for (NaturezaOcorrencia n : NaturezaOcorrencia.values()) {
          if (n.getNatureza() == index) return n;
      }
      throw new IllegalArgumentException("Leg not found. Amputated?");
   }

}
