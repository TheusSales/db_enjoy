package com.enjoy.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_ESTABELECIMENTO")
public class Estabelecimento extends EntityDefault {

	@Id
	@SequenceGenerator(name = "sq_estabelecimento", sequenceName = "SQ_T_ESTABELECIMENTO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_estabelecimento")
	@Column(name = "id_estabelecimento")
	private int id = 0;

	@Column(name = "nm_estabelecimento", length = 50, nullable = false)
	private String nome = "";

	@Column(name="endereco_estabelecimento", nullable = false)
	private String endereco = null;
	
	public Estabelecimento(String nome, String endereco) {
		super();
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public Estabelecimento() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDs_endereco() {
		return endereco;
	}

	public void setDs_endereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
