package br.com.zup.proposta.Biometria;

import com.sun.istack.NotNull;

import br.com.zup.proposta.Validators.Base64;

public class BiometriaDto {

	private @Base64	@NotNull String digital;

	public Biometria toBiometria() {
		return new Biometria(digital);
	}

	public void setDigital(String digital) {
		this.digital = digital;
	}

	public String getDigital() {
		return digital;
	}

}