package br.com.zup.proposta.Proposta;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import com.sun.istack.NotNull;

import br.com.zup.proposta.Cartao.Cartao;
import br.com.zup.proposta.Proposta.EnumProspota.StatusAvaliacaoProposta;
import br.com.zup.proposta.Validators.CpfCnpj;

@Entity
@NamedQuery(name = "findPropostaByStatus", query = "select p from Proposta p where p.statusAvaliacao = :statusAvaliacao")
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Email @NotBlank String email;
	private @NotBlank String nome;
	private @NotBlank String endereco;
	private @Positive BigDecimal salario;
	private @CpfCnpj @NotBlank String documento;
	@Enumerated(EnumType.STRING)
	private @NotNull StatusAvaliacaoProposta statusAvaliacao;
	@OneToOne
	private Cartao cartao;

	@Deprecated
	public Proposta() {
	}

	public Proposta(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String endereco,
			@Positive BigDecimal salario, @CpfCnpj String documento) {
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.documento = documento;
		this.statusAvaliacao = StatusAvaliacaoProposta.NAO_ELEGIVEL;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public StatusAvaliacaoProposta getStatusAvaliacao() {
		return statusAvaliacao;
	}

	public void atualizaStatus(StatusAvaliacaoProposta statusAvaliacao) {
		Assert.isTrue(this.statusAvaliacao.equals(StatusAvaliacaoProposta.NAO_ELEGIVEL),
				"Proposta elegível! Não é possível fazer a troca");
		this.statusAvaliacao = statusAvaliacao;
	}

	public boolean verificarSeNaoExisteCartao() {
		return Objects.isNull(cartao);
	}

	public void incluirCartaoNaProposta(Cartao cartao) {
		this.cartao = cartao;
	}
}