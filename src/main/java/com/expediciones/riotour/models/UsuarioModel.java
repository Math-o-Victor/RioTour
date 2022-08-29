package com.expediciones.riotour.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.ankurpathak.password.bean.constraints.ContainDigit;
import com.github.ankurpathak.password.bean.constraints.ContainLowercase;
import com.github.ankurpathak.password.bean.constraints.ContainSpecial;
import com.github.ankurpathak.password.bean.constraints.ContainUppercase;
import com.github.ankurpathak.password.bean.constraints.NotContainWhitespace;

@Entity
@Table(name="tb_usuario")
public class UsuarioModel {
	
	public UsuarioModel(){}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
	
	@NotBlank
	private String nomeUsuario;
	
	@Email
	private String email;
	
	@Size(min = 8, max = 30, message="A senha não pode ter menos que 8 caracteres ou mais que 30!")
    @NotContainWhitespace
    @ContainSpecial
    @ContainLowercase
    @ContainDigit
    @ContainUppercase
	private String senha;
	
	@ManyToMany
	@JsonIgnoreProperties("usuario")
	private List<ViagemModel> viagem;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<PassageiroModel> passageiro;

	public List<ViagemModel> getViagem() {
		return viagem;
	}

	public void setViagem(List<ViagemModel> viagem) {
		this.viagem = viagem;
	}

	public List<PassageiroModel> getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(List<PassageiroModel> passageiro) {
		this.passageiro = passageiro;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
