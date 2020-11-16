package br.com.zup.proposta.Bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotNull LocalDateTime bloqueadoEm = LocalDateTime.now();
	private @NotBlank String ip;
	private @NotBlank String userAgent;

	@Deprecated
	public Bloqueio() {
	}

	public Bloqueio(@NotBlank String ip, @NotBlank String userAgent) {
		this.ip = ip;
		this.userAgent = userAgent;
	}

	public Long getId() {
		return id;
	}
}