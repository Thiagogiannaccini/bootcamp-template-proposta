package br.com.zup.proposta.AvisoViagem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.sun.istack.NotNull;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(generator = "uuid4")
	private UUID id;
	private @NotBlank String destino;
	private @NotNull @Future LocalDate validoAte;
	private @NotBlank String ip;
	private @NotBlank String userAgent;
	private @CreationTimestamp LocalDateTime instante;

	@Deprecated
	public AvisoViagem() {
	}

	public AvisoViagem(@NotBlank String destino, @NotNull LocalDate validoAte, @NotBlank String ip,
			@NotBlank String userAgent) {
		this.destino = destino;
		this.validoAte = validoAte;
		this.ip = ip;
		this.userAgent = userAgent;
	}

	public UUID getId() {
		return id;
	}

}