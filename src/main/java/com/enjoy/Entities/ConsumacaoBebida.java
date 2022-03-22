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

@Entity(name="T_CONSUMACAOBEBIDA")
public class ConsumacaoBebida extends EntityDefault {
    
    @Id
    @SequenceGenerator(name="sq_consumacaobebidas", sequenceName="SQ_T_CONSUMACAOBEBIDAS", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_consumacaobebidas")
    @Column(name="id_consbebida")
    private int id = 0;

	@JoinColumn(name="id_consumacao")
	@ManyToOne(cascade=CascadeType.PERSIST, optional=false)
	private Consumacao consumacao = null;

    @JoinColumn(name="id_bebida")
    @ManyToOne(cascade=CascadeType.PERSIST, optional=false)
    private Bebida bebida = null;
    
    public ConsumacaoBebida(Consumacao consumacao, Bebida bebida) {
        this.consumacao = consumacao;
        this.bebida = bebida;
    }
    
    public ConsumacaoBebida() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Consumacao getConsumacao() {
        return consumacao;
    }

    public void setConsumacao(Consumacao consumacao) {
        this.consumacao = consumacao;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

}
