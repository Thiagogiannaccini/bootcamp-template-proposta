package br.com.zup.proposta.Proposta;

import java.math.BigDecimal;

import br.com.zup.proposta.Proposta.EnumProspota.StatusAvaliacaoProposta;

public class PropostaResponse {
	   private String documento;
	    private String email;
	    private String nome;
	    private String endereco;
	    private BigDecimal salario;
	    private StatusAvaliacaoProposta statusAvaliacaoProposta;

	    public PropostaResponse(Proposta proposta) {
	        this.documento = proposta.getDocumento();
	        this.email = proposta.getEmail();
	        this.nome = proposta.getNome();
	        this.endereco = proposta.getEndereco();
	        this.salario = proposta.getSalario();
	        this.statusAvaliacaoProposta = proposta.getStatusAvaliacao();
	    }

	    public String getDocumento() {
	        return documento;
	    }

	    public void setDocumento(String documento) {
	        this.documento = documento;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
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

	    public BigDecimal getSalario() {
	        return salario;
	    }

	    public void setSalario(BigDecimal salario) {
	        this.salario = salario;
	    }

	    public StatusAvaliacaoProposta getStatusAvaliacaoProposta() {
	        return statusAvaliacaoProposta;
	    }

	    public void setStatusAvaliacaoProposta(StatusAvaliacaoProposta statusAvaliacaoProposta) {
	        this.statusAvaliacaoProposta = statusAvaliacaoProposta;
	    }
	}