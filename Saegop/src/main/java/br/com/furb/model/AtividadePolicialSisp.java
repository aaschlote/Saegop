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
@Table(name = "Atividade_Policial_Sisp")
public class AtividadePolicialSisp {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "DS_BAIRRO", length=4000)
	private String dsBairro;

	@Column(name = "DS_ENDERECO")
	private String dsEndereco;
	
	@Column(name = "DS_TIPIFICACAO")
	private String dsTipificacao;
	
	@Column(name = "DT_OCORRENCIA")
	@Temporal(TemporalType.DATE)
	private Calendar dtOcorrencia;
	
	@Column(name = "HR_OCORRENCIA")
	private String hrOcorrencia;
	
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

	public String getHrOcorrencia() {
		return hrOcorrencia;
	}

	public void setHrOcorrencia(String hrOcorrencia) {
		this.hrOcorrencia = hrOcorrencia;
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

	public String getDsTipificacao() {
		return dsTipificacao;
	}

	public void setDsTipificacao(String dsTipificacao) {
		this.dsTipificacao = dsTipificacao;
	}
	
	
}
