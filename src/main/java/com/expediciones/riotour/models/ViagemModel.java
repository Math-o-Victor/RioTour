package com.expediciones.riotour.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.ArrayList;

@Entity
@Table(name="tb_viagem")
public class ViagemModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private List<String> foto = new ArrayList<>();
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Digits(integer=9, fraction=2) 
	private BigDecimal preco;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private int tamMaxGrupo;
	
	@NotBlank
	private String duracaoExpedicao;
	
	@NotBlank
	@Future
	private Date dataChegada;
	
	@ManyToOne
	@JsonIgnoreProperties("viagem")
	private CategoriaModel categoria;
	
	@ManyToOne
	@JsonIgnoreProperties("viagem")
	private UsuarioModel usuario;
	
	public ViagemModel() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public List<String> getFoto() {
		return foto;
	}

	public void setFoto(List<String> foto) {
		this.foto = foto;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getTamMaxGrupo() {
		return tamMaxGrupo;
	}
	public void setTamMaxGrupo(int tamMaxGrupo) {
		this.tamMaxGrupo = tamMaxGrupo;
	}
	public String getDuracaoExpedicao() {
		return duracaoExpedicao;
	}
	public void setDuracaoExpedicao(String duracaoExpedicao) {
		this.duracaoExpedicao = duracaoExpedicao;
	}
	public Date getDataChegada() {
		return dataChegada;
	}
	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}
	
	
}
