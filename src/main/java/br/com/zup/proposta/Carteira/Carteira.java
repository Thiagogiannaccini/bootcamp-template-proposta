package br.com.zup.proposta.Carteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
public class Carteira {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank @Email String email;
	@Enumerated(EnumType.STRING)
	private @NotNull TipoCarteira tipoCarteira;

	@Deprecated
	public Carteira() {
	}

	public Carteira(@NotBlank @Email String email, @NotNull TipoCarteira tipoCarteira) {
		this.email = email;
		this.tipoCarteira = tipoCarteira;
	}

	public boolean CarteiraJaAdicionada(TipoCarteira tipoCarteira) {
		return this.tipoCarteira.equals(tipoCarteira);
	}

	public Long getId() {
		return id;
	}
}
