package br.senai.sc.ti20131n.pw.gpe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Canal {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length=30,nullable=false)
	private String nome;
	@Column(unique=true,nullable=false)
	private Integer numero;
	private String genero;
	private Integer classificacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}
}
