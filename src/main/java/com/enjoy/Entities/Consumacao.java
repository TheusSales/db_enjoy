package com.enjoy.Entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_CONSUMACAO")
public class Consumacao extends EntityDefault {

	@Id
	@SequenceGenerator(name="sq_consumacao", sequenceName="SQ_T_CONSUMACAO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_consumacao")
	@Column(name="id_consumacao")
	private int id = 0;

	@JoinColumn(name="id_consumidor", nullable=false)
	@OneToOne(cascade=CascadeType.PERSIST)
	private Consumidor consumidor = null;

	@OneToMany(mappedBy="consumacao", cascade=CascadeType.PERSIST, orphanRemoval=true)
	private Collection<ConsumacaoBebida> consumacaoBebidas = null;

	@JoinColumn(name="id_estabelecimento", nullable=false)
	@OneToOne(cascade=CascadeType.PERSIST)
	private Estabelecimento estabelecimento = null;
	
	@Column(name="dt_consumacao",length=50, nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar emissao = null;
	
	@Column(name="total_consumacao",length = 6, nullable=false)
	private float total = 0;
	
	public Consumacao(Calendar emissao, float total) {
		super();
		this.emissao = emissao;
		this.total = total;
	}
	
	public Consumacao(Calendar emissao, float vl_total, Consumidor consumidor, Estabelecimento estabelecimento) {
		this(emissao, vl_total);
		this.consumidor = consumidor;
		this.estabelecimento = estabelecimento;
	}
	
	public Consumacao(Calendar emissao, float vl_total, Consumidor consumidor, Collection<ConsumacaoBebida> consumacaoBebidas,
			Estabelecimento estabelecimento) {
		this(emissao, vl_total, consumidor, estabelecimento);
		this.consumacaoBebidas = consumacaoBebidas;
	}
	
	public Consumacao() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    public Collection<ConsumacaoBebida> getConsumacaoBebidas() {
        return consumacaoBebidas;
    }

	public void addBebida(Bebida bebida) {
		if (this.consumacaoBebidas == null)
			this.consumacaoBebidas = new ArrayList<ConsumacaoBebida>();
		var consumacaoBebida = new ConsumacaoBebida(this, bebida);
		this.consumacaoBebidas.add(consumacaoBebida);
	}

	public void removeBebida(ConsumacaoBebida bebida) {
		this.consumacaoBebidas.remove(bebida);
	}

	public void removeBebida(int index) {
		this.consumacaoBebidas.remove(this.consumacaoBebidas.toArray()[index]);
	}

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

	public Calendar getEmissao() {
		return emissao;
	}

	public void setEmissao(Calendar emissao) {
		this.emissao = emissao;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
