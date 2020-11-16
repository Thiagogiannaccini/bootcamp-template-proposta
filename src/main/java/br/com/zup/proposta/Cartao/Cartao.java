package br.com.zup.proposta.Cartao;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

import br.com.zup.proposta.AvisoViagem.AvisoViagem;
import br.com.zup.proposta.Biometria.Biometria;
import br.com.zup.proposta.Bloqueio.Bloqueio;
import br.com.zup.proposta.Bloqueio.StatusCartao;
import br.com.zup.proposta.Carteira.Carteira;
import br.com.zup.proposta.Carteira.TipoCarteira;

@Entity
public class Cartao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotNull UUID numeroCartao;
	private @NotNull LocalDateTime emitidoEm;
	@OneToMany
	private Set<Biometria> biometrias = new HashSet<>();
	@Enumerated(EnumType.STRING)
	private StatusCartao statusCartao;
	@OneToMany
	private Set<Bloqueio> bloqueios = new HashSet<>();
	@OneToMany
	private Set<AvisoViagem> avisos = new HashSet<>();
	@OneToMany
	private Set<Carteira> carteiras = new HashSet<>();

	@Deprecated
	public Cartao() {
	}

	public Long getId() {
		return id;
	}

	public UUID getNumeroCartao() {
		return numeroCartao;
	}

	public Cartao(UUID numeroCartao, LocalDateTime emitidoEm) {
		this.numeroCartao = numeroCartao;
		this.emitidoEm = emitidoEm;
		this.statusCartao = statusCartao.DESBLOQUEADO;
	}

	public void addBiometria(Biometria biometria) {
		biometrias.add(biometria);
	}

	public void addBloqueioDoCartao(Bloqueio bloqueio) {
		bloqueios.add(bloqueio);
	}

	public void bloquearCartao() {
		this.statusCartao = statusCartao.BLOQUEADO;
	}

	public boolean verificarCartaoBloqueado() {
		return statusCartao.equals(statusCartao.BLOQUEADO);
	}

	public void addAvisoViagem(AvisoViagem avisoViagem) {
		avisos.add(avisoViagem);
	}

	public void addCarteira(Carteira carteira) {
		carteiras.add(carteira);
	}

	public boolean carteiraAssociadaCartao(TipoCarteira tipoCarteira) {
		return carteiras.stream().anyMatch(carteira -> carteira.CarteiraJaAdicionada(tipoCarteira));
	}
}