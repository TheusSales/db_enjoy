package com.enjoy.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_ENDERECO_CONSUMIDOR")
public class EnderecoConsumidor extends EntityDefault {
	
	public EnderecoConsumidor(int cd_endereco, int nr_cep, String nm_estado, String nm_bairro, String nm_rua,
			int nr_residencia) {
		super();
		this.cd_endereco = cd_endereco;
		this.nr_cep = nr_cep;
		this.nm_estado = nm_estado;
		this.nm_bairro = nm_bairro;
		this.nm_rua = nm_rua;
		this.nr_residencia = nr_residencia;
	}
	
	public EnderecoConsumidor() {
		super();
	}

	@Id
	@SequenceGenerator(name = "sq_endereco_consumidor", sequenceName = "SQ_T_ENDERECO_CONSUMIDOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_consumidor")
	@Column(name = "endereco_consumidor")
	private int cd_endereco = 0;

	@Column(name = "nr_cep", length = 8, nullable = false)
	private int nr_cep = 0;

	@Column(name = "nm_estado", length = 2, nullable = false)
	private String nm_estado = "";

	@Column(name = "ds_bairro", length = 50, nullable = false)
	private String nm_bairro = "";

	@Column(name = "nm_rua", length = 50, nullable = false)
	private String nm_rua = "";

	@Column(name = "nr_residencia", length = 6, nullable = false)
	private int nr_residencia = 0;

	public int getCd_endereco() {
		return cd_endereco;
	}

	public void setCd_endereco(int cd_endereco) {
		this.cd_endereco = cd_endereco;
	}

	public int getNr_cep() {
		return nr_cep;
	}

	public void setNr_cep(int nr_cep) {
		this.nr_cep = nr_cep;
	}

	public String getNm_estado() {
		return nm_estado;
	}

	public void setNm_estado(String nm_estado) {
		this.nm_estado = nm_estado;
	}

	public String getNm_bairro() {
		return nm_bairro;
	}

	public void setNm_bairro(String nm_bairro) {
		this.nm_bairro = nm_bairro;
	}

	public String getNm_rua() {
		return nm_rua;
	}

	public void setNm_rua(String nm_rua) {
		this.nm_rua = nm_rua;
	}

	public int getNr_residencia() {
		return nr_residencia;
	}

	public void setNr_residencia(int nr_residencia) {
		this.nr_residencia = nr_residencia;
	}

}