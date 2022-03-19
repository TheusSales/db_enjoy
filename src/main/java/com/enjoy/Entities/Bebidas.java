package com.enjoy.Entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "T_BEBIDAS")
public class Bebidas extends EntityDefault {

	public Bebidas(String nr_celular, String tipo_bebida) {
		super();
		this.nr_celular = nr_celular;
		this.tipo_bebida = tipo_bebida;
	}

	
	public Bebidas() {
		super();
	}


	@Id
	@SequenceGenerator(name = "sq_bebidas", sequenceName = "SQ_T_BEBIDAS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_bebidas")
	@Column(name = "cd_bebida")
	private int id = 0;

	@Column(name = "nm_bebida", length = 50, nullable = false)
	private String nr_celular = "";

	@Column(name = "tipo_bebida", length = 50, nullable = false)
	private String tipo_bebida = "";
	
	@JoinColumn(name="cd_consumacao")
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Consumacao consumacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNr_celular() {
		return nr_celular;
	}

	public void setNr_celular(String nr_celular) {
		this.nr_celular = nr_celular;
	}

	public String getTipo_bebida() {
		return tipo_bebida;
	}

	public void setTipo_bebida(String tipo_bebida) {
		this.tipo_bebida = tipo_bebida;
	}


	public Consumacao getConsumacao() {
		return consumacao;
	}


	public void setConsumacao(Consumacao consumacao) {
		this.consumacao = consumacao;
	}

}
