package com.enjoy.Entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_ESTABELECIMENTO")
public class Estabelecimento extends EntityDefault {
	
	public Estabelecimento(String nr_estabelecimento, String ds_endereco) {
		super();
		this.nr_estabelecimento = nr_estabelecimento;
		this.ds_endereco = ds_endereco;
	}
	
	public Estabelecimento() {
		super();
	}

	@Id
	@SequenceGenerator(name = "sq_estabelecimento", sequenceName = "SQ_T_ESTABELECIMENTO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_estabelecimento")
	@Column(name = "cd_estabelecimento")
	private int id = 0;

	@Column(name = "nm_estabelecimento", length = 50, nullable = false)
	private String nr_estabelecimento = "";

	@Column(name = "ds_endereco", length = 50, nullable = false)
	private String ds_endereco = "";
	
	@OneToOne(mappedBy="estabelecimento", cascade=CascadeType.PERSIST,orphanRemoval=true)
	private Consumacao consumacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNr_estabelecimento() {
		return nr_estabelecimento;
	}

	public void setNr_estabelecimento(String nr_estabelecimento) {
		this.nr_estabelecimento = nr_estabelecimento;
	}

	public String getDs_endereco() {
		return ds_endereco;
	}

	public void setDs_endereco(String ds_endereco) {
		this.ds_endereco = ds_endereco;
	}

	public Consumacao getConsumacao() {
		return consumacao;
	}

	public void setConsumacao(Consumacao consumacao) {
		this.consumacao = consumacao;
	}
	
	
}
