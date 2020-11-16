package br.com.zup.proposta.AnaliseFinanceira;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import br.com.zup.proposta.Proposta.Proposta;
import br.com.zup.proposta.Validators.CpfCnpj;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class SolicitacaoAnaliseProposta {

	private @CpfCnpj String documento;
	private String nome;
	private String idProposta;

	@Deprecated
	public SolicitacaoAnaliseProposta() {
	}

	public SolicitacaoAnaliseProposta(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = String.valueOf(proposta.getId());
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public void setIdProposta(String idProposta) {
		this.idProposta = idProposta;
	}
}