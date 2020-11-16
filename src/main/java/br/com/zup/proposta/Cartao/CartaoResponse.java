package br.com.zup.proposta.Cartao;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sun.istack.NotNull;

public class CartaoResponse {
	
	private @NotNull UUID id;
	private @NotNull LocalDateTime emitidoEm;

	public Cartao toCartao() {
		return new Cartao(id, emitidoEm);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public void setEmitidoEm(LocalDateTime emitidoEm) {
		this.emitidoEm = emitidoEm;
	}

}
