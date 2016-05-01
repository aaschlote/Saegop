package br.com.furb.model;

public enum NaturezaOcorrencia {
	
	ARROMBAMENTO(1),
	ASSALTO(2),
	TRAFICO(3),
	ESTUPRO(4),
	FURTO(5),
	HOMICIDIO(6),
	LESAO_CORPORAL_AGRESSAO(7),
	PERTUBACAO(8),
	PORTE_ARMA(9),
	PORTE_DROGAS(10),
	ROUBO(11),
	ENCONTRADO_VEICULO_ROUBADADO(12),
	DISPARO_ARMA_FOGO(13),
	EMBRIAGUEZ(14),
	ESTELIONATO(15),
	PRISAO_FORAGIDO(16),
	MANDATO_PRISAO(17),
	NOTA_FALSA(18),
	ENCONTRADO_CADAVER_HUMANO(19),
	NAO_IDENTIFICADO(20);
	
	private int idNatureza;
	
	private NaturezaOcorrencia(int idNatureza) {
		this.idNatureza = idNatureza;
	}

	public int getNivel() {
		return idNatureza;
	}

}
