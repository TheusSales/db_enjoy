package com.enjoy.Entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="T_BEBIDA")
public class Bebida extends EntityDefault {

	@Id
	@SequenceGenerator(name = "sq_bebida", sequenceName = "SQ_T_BEBIDA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_bebida")
	@Column(name="id_bebida")
	private int id = 0;

	@OneToMany(mappedBy="bebida", cascade=CascadeType.PERSIST, orphanRemoval=true)
	private Collection<ConsumacaoBebida> consumacaoBebida;

	@Column(name="nm_bebida", length=50, nullable=false)
	private String nome = "";

	@JoinColumn(name="tipo_bebida")
	@ManyToOne(cascade=CascadeType.PERSIST, optional=false)
	private TipoBebida tipo = null;

	public Bebida(String nome, TipoBebida tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}

	
	public Bebida() {
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

	public TipoBebida getTipo() {
		return tipo;
	}

	public void setTipo(TipoBebida tipo) {
		this.tipo = tipo;
	}

    public Collection<ConsumacaoBebida> getConsumacaoBebida() {
        return consumacaoBebida;
    }

    public void setConsumacaoBebida(Collection<ConsumacaoBebida> consumacaoBebida) {
        this.consumacaoBebida = consumacaoBebida;
    }

}
