package br.com.furb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Alex
 * 1 - Arrombamento
 * 2 - Assalto
 * 3 - Tráfico
 * 4 - Estupro
 * 5 - Furto
 * 6 - Homicidio
 * 7 - Lesão Corporal/Agressão
 * 8 - Perturbação
 * 9 - Porte de arma
 * 10 - Porte de drogas
 * 11 - Roubo
 * 12 - Encontrou veiculo roubado/furtado
 * 13 - Disparo de arma de fogo
 * 14 - Embriaguez
 * 15 - Estelionato
 * 16 - Prisão de foragido
 * 17 - Mandato de prisão
 * 18 - Nota falsa
 * 19 - Encontro de cadáver humano
 * 20 - Não identificado
 * 
 */

@Entity
@Table(name = "Natureza_Ocorrencia")
public class NaturezaOcorrencia {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "DS_NATUREZA")
	private String dsNatureza;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsNatureza() {
		return dsNatureza;
	}

	public void setDsNatureza(String dsNatureza) {
		this.dsNatureza = dsNatureza;
	}

}
