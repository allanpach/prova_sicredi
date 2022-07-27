package br.com.sicredi.pojo;

public class SimulacaoRequestBody {
	
	private String cpf;
	private String nome;
	private String email;
	private int valor;
	private int parcelas;
	private boolean seguro;
	private long id;
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public Integer getParcelas() {
		return parcelas;
	}
	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}
	public boolean getSeguro() {
		return seguro;
	}
	public void setSeguro(boolean seguro) {
		this.seguro = seguro;
	}
	public long getId() {
		return id;
	}
	public void setId(long l) {
		this.id = l;
	}
	
	
	
}
