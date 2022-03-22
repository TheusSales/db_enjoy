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
@Table(name = "T_CONSUMIDOR")
public class Consumidor extends EntityDefault {

	@Id
	@SequenceGenerator(name = "sq_consumidor", sequenceName = "SQ_T_CONSUMIDOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_consumidor")
	@Column(name = "id_consumidor")
	private int id = 0;

	@OneToMany(mappedBy="consumidor", cascade=CascadeType.PERSIST, orphanRemoval=true)
	private Collection<Consumacao> consumos = null;

	@JoinColumn(nullable=false)
	@OneToOne(cascade=CascadeType.PERSIST)
	private Endereco endereco = null;

	@Column(name = "celular_consumidor", length = 60, nullable = false)
	private String celular = "";

	@Column(name = "nm_consumidor", length = 50, nullable = false)
	private String nome = "";

	@Column(name = "cpf_consumidor", length = 11, nullable = false)
	private String cpf = "";

	@Column(name = "nasc_consumidor", length = 60, nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dt_nasc = null;

	@Column(name = "email_consumidor", length = 50, nullable = false)
	private String email = "";
	
	public Consumidor(String celular, String nome, String cpf, Calendar dt_nasc, String email, Endereco endereco) {
		super();
		this.celular = celular;
		this.nome = nome;
		this.cpf = cpf;
		this.dt_nasc = dt_nasc;
		this.email = email;
		this.endereco = endereco;
	}
	
	public Consumidor(String celular, String nome, String cpf, Calendar dt_nasc, String email, Endereco endereco, Collection<Consumacao> consumos) {
		this(celular, nome, cpf, dt_nasc, email, endereco);
		this.consumos = consumos;
	}
	
	public Consumidor() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public Collection<Consumacao> getConsumos() {
        return consumos;
    }

    public void setConsumos(Collection<Consumacao> consumos) {
        this.consumos = consumos;
    }

	public void addConsumacao(Consumacao consumacao) {
		if (this.consumos == null)
			this.consumos = new ArrayList<Consumacao>();
		this.consumos.add(consumacao);
	}

	public void removeConsumacao(Consumacao consumacao) {
		this.consumos.remove(consumacao);
	}

	public void removeConsumacao(int index) {
		this.consumos.remove(this.consumos.toArray()[index]);
	}
	
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Calendar getDt_nasc() {
		return dt_nasc;
	}

	public void setDt_nasc(Calendar dt_nasc) {
		this.dt_nasc = dt_nasc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
