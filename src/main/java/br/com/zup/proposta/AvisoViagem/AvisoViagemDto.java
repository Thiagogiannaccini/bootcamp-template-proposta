package br.com.zup.proposta.AvisoViagem;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class AvisoViagemDto {

	private @NotBlank String destino;
	private @NotNull @Future LocalDate validoAte;

	public AvisoViagem toAviso(HttpServletRequest request) {
		return new AvisoViagem(destino, validoAte, request.getRemoteAddr(), request.getHeader("User-Agent"));
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	public void setValidoAte(LocalDate validoAte) {
		this.validoAte = validoAte;
	}
}