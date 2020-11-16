package br.com.zup.proposta.Biometria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.Cartao.Cartao;

public class BiometriasController {

	@PersistenceContext
	private EntityManager manager;

	private final Logger logger = LoggerFactory.getLogger(Biometria.class);

	@Transactional
	@PostMapping("/cartoes/{idCartao}/biometria")
	public ResponseEntity<?> cadastrarBiometria(@PathVariable Long idCartao, @RequestBody @Valid BiometriaDto dto,
			UriComponentsBuilder builder) {

		Cartao cartao = manager.find(Cartao.class, idCartao);

		if (cartao == null) {
			logger.error("Cartão não encontrado no sistema!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Biometria biometria = dto.toBiometria();
		manager.persist(biometria);
		logger.info("Biometria cadastrada!");
		cartao.addBiometria(biometria);
		manager.merge(cartao);
		logger.info("Biometria associada ao cartao={}", cartao.getNumeroCartao());

		return ResponseEntity.created(builder.path("/biometrias/{id}").buildAndExpand(idCartao).toUri()).build();
	}

}