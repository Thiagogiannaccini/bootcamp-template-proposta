package br.com.zup.proposta.Carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraDto {

	private @NotBlank @Email String email;

	public Carteira toCarteira(TipoCarteira carteira) {
		return new Carteira(email, carteira);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}