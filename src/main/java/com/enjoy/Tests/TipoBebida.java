package com.enjoy.Tests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.enjoy.Entities.EntityDefault;

@Entity(name="T_TIPOBEBIDA")
public class TipoBebida extends EntityDefault {

    @Id
    @SequenceGenerator(name="sq_tipobebida", sequenceName="SQ_T_TIPOBEBIDA", allocationSize=1)
    @GeneratedValue(generator="sq_tipobebida", strategy=GenerationType.SEQUENCE)
    @Column(name="id_tipobebida")
    private int id;

    @Column(name="nm_tipobebida", nullable=false)
    private String nome = "";

    public TipoBebida(String nome) {
        this.nome = nome;
    }

    public TipoBebida() {}

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

}
