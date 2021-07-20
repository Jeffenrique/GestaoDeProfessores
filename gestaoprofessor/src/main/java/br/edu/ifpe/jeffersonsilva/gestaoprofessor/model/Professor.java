package br.edu.ifpe.jeffersonsilva.gestaoprofessor.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Professor {

	private int cod_matricula;
	
	@NotBlank
	@NotNull
	private String nome;
	private String endereco;
	private String cidade;
	private String uf;

	public Professor(int cod_matricula, String nome, String endereco, String cidade, String uf) {
		this.cod_matricula = cod_matricula;	
		this.nome = nome;
		this.endereco = endereco;
		this.cidade = cidade;
		this.uf = uf;

	}
	
	public Professor() {
		
	}
	

	public int getCod_matricula() {
		return cod_matricula;
	}

	public void setCod_matricula(int cod_matricula) {
		this.cod_matricula = cod_matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
