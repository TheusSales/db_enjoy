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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_CONSUMIDOR")
public class Consumidor extends EntityDefault {
	
	public Consumidor(String nr_celular, String nm_consumidor, String nr_cpf, Calendar dt_nasc, String ds_email) {
		super();
		this.nr_celular = nr_celular;
		this.nm_consumidor = nm_consumidor;
		this.nr_cpf = nr_cpf;
		this.dt_nasc = dt_nasc;
		this.ds_email = ds_email;
	}
	
	public Consumidor() {
		super();
	}

	@Id
	@SequenceGenerator(name = "sq_consumidor", sequenceName = "SQ_T_CONSUMIDOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_consumidor")
	@Column(name = "id_consumidor")
	private int id = 0;
	
	@Column(name = "nr_celular", length = 60, nullable = false)
	private String nr_celular = "";

	@Column(name = "nm_consumidor", length = 50, nullable = false)
	private String nm_consumidor = "";

	@Column(name = "nr_cpf", length = 11, nullable = false)
	private String nr_cpf = "";

	@Column(name = "dt_nasc", length = 60, nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dt_nasc = null;

	@Column(name = "ds_email", length = 50, nullable = false)
	private String ds_email = "";
	
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="cd_endereco", nullable=false)
	private EnderecoConsumidor endereco_consumidor = null;
	
	@OneToMany(mappedBy = "consumidor", cascade=CascadeType.PERSIST)
	@JoinColumn(name="cd_consumacao", nullable=false)
	private Collection<Consumacao> consumacao = null;

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

	public String getNm_consumidor() {
		return nm_consumidor;
	}

	public void setNm_consumidor(String nm_consumidor) {
		this.nm_consumidor = nm_consumidor;
	}

	public String getNr_cpf() {
		return nr_cpf;
	}

	public void setNr_cpf(String nr_cpf) {
		this.nr_cpf = nr_cpf;
	}

	public Calendar getDt_nasc() {
		return dt_nasc;
	}

	public void setDt_nasc(Calendar dt_nasc) {
		this.dt_nasc = dt_nasc;
	}

	public String getDs_email() {
		return ds_email;
	}

	public void setDs_email(String ds_email) {
		this.ds_email = ds_email;
	}

	public EnderecoConsumidor getEndereco_consumidor() {
		return endereco_consumidor;
	}

	public void setEndereco_consumidor(EnderecoConsumidor endereco_consumidor) {
		this.endereco_consumidor = endereco_consumidor;
	}

	public Collection<Consumacao> getConsumacao() {
		return consumacao;
	}

	public void setConsumacao(Collection<Consumacao> consumacao) {
		this.consumacao = consumacao;
	}
	
	

}
