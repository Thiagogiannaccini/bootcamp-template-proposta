package br.com.zup.proposta.Biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private	@NotNull byte[] digital;
	private	@NotNull LocalDateTime instanteCadastro = LocalDateTime.now();

	@Deprecated
	public Biometria() {
	}

	public Biometria(String digital) {
		this.digital = digital.getBytes();
	}

	public Long getId() {
		return id;
	}
}