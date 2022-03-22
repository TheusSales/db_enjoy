package com.enjoy.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.enjoy.Entities.Enums.EstadosEnum;

@Entity
@Table(name = "T_ENDERECO")
public class Endereco extends EntityDefault {

	@Id
	@SequenceGenerator(name = "sq_endereco", sequenceName = "SQ_T_ENDERECO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_endereco")
	@Column(name = "id_endereco")
	private int id = 0;

	@Column(name = "nr_cep", length = 8, nullable = false)
	private int cep = 0;

	@Column(name = "nm_estado", length = 2, nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadosEnum estado = EstadosEnum.SP;

	@Column(name = "ds_bairro", length = 50, nullable = false)
	private String bairro = "";

	@Column(name = "nm_rua", length = 50, nullable = false)
	private String rua = "";

	@Column(length = 6, nullable = false)
	private int numero = 0;
	
	public Endereco(int nr_cep, EstadosEnum nm_estado, String nm_bairro, String nm_rua,
			int numero) {
		super();
		this.cep = nr_cep;
		this.estado = nm_estado;
		this.bairro = nm_bairro;
		this.rua = nm_rua;
		this.numero = numero;
	}
	
	public Endereco() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCep() {
		return cep;
	}

	public void setcep(int cep) {
		this.cep = cep;
	}

	public EstadosEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadosEnum estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}