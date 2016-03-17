package br.com.furb.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Atividade_Policial")
public class AtividadePolicial {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "DS_FATO")
	private String dsFato;

	@Column(name = "DS_BAIRRO", length=4000)
	private String dsBairro;

	@Column(name = "DS_ENDERECO")
	private String dsEndereco;

	@Column(name = "DT_OCORRENCIA")
	@Temporal(TemporalType.DATE)
	private Calendar dtOcorrencia;

	@Column(name = "NR_LONGITUDE")
	private double longitude;

	@Column(name = "NR_LATITUDE")
	private double latitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsFato() {
		return dsFato;
	}

	public void setDsFato(String dsFato) {
		this.dsFato = dsFato;
	}

	public String getDsBairro() {
		return dsBairro;
	}

	public void setDsBairro(String dsBairro) {
		this.dsBairro = dsBairro;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public Calendar getDtOcorrencia() {
		return dtOcorrencia;
	}

	public void setDtOcorrencia(Calendar dtOcorrencia) {
		this.dtOcorrencia = dtOcorrencia;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
