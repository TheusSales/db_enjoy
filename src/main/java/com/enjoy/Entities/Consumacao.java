package com.enjoy.Entities;

import java.util.Calendar;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_CONSUMACAO")
public class Consumacao {
	
	public Consumacao(Calendar dt_consumacao, float vl_total) {
		super();
		this.dt_consumacao = dt_consumacao;
		this.vl_total = vl_total;
	}
	
	public Consumacao() {
		super();
	}

	@Id
	@SequenceGenerator(name="sq_consumacao", sequenceName="SQ_T_CONSUMACAO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_consumidor")
	@Column(name="cd_consumacao")
	private int id = 0;
	
	@Column(name="dt_consumacao",length=50, nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar dt_consumacao = null;
	
	@Column(name="vl_total",length = 6, nullable=false)
	private float vl_total = 0;
	
	@JoinColumn(name="id_consumidor")
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Consumidor consumidor = null;
	
	@OneToMany(mappedBy="consumacao", cascade=CascadeType.PERSIST, orphanRemoval = true)
	private Collection<Bebidas> bebidas = null;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="T_ESTABELECIMENTO_cd_estabelecimento", nullable=false)
	private Estabelecimento estabelecimento = null;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDt_consumacao() {
		return dt_consumacao;
	}

	public void setDt_consumacao(Calendar dt_consumacao) {
		this.dt_consumacao = dt_consumacao;
	}

	public float getVl_total() {
		return vl_total;
	}

	public void setVl_total(float vl_total) {
		this.vl_total = vl_total;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

	public Collection<Bebidas> getBebidas() {
		return bebidas;
	}

	public void setBebidas(Collection<Bebidas> bebidas) {
		this.bebidas = bebidas;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
}
