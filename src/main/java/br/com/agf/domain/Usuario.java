package br.com.agf.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "usuario")
public class Usuario implements Serializable{
	
	private Long id;
	private String name;
	private String email;
	private String senha;
	private boolean deletado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public boolean isDeletado() {
		return deletado;
	}
	public void setDeletado(boolean deletado) {
		this.deletado = deletado;
	}	
}
