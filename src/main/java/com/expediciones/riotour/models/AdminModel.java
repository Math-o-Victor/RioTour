package com.expediciones.riotour.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.github.ankurpathak.password.bean.constraints.ContainDigit;
import com.github.ankurpathak.password.bean.constraints.ContainLowercase;
import com.github.ankurpathak.password.bean.constraints.ContainSpecial;
import com.github.ankurpathak.password.bean.constraints.ContainUppercase;
import com.github.ankurpathak.password.bean.constraints.NotContainWhitespace;

@Entity
@Table(name="tb_admin")
public class AdminModel {
	
	public AdminModel(){}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Email
	private String email;
	
	@Size(min = 8, message="A senha não pode ter menos que 8 caracteres ou mais que 30!")
    @NotContainWhitespace
    @ContainSpecial
    @ContainLowercase
    @ContainDigit
    @ContainUppercase
    private String senha;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
