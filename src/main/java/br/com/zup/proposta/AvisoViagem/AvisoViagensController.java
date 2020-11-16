package br.com.zup.proposta.AvisoViagem;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.Biometria.Biometria;
import br.com.zup.proposta.Cartao.Cartao;
import br.com.zup.proposta.Cartao.IntegracaoCartao;

@RestController
@Validated
public class AvisoViagensController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private IntegracaoCartao integracaoCartao;

	private Logger logger = LoggerFactory.getLogger(Biometria.class);

	@PostMapping("/cartoes/{idCartao}/aviso")
	@Transactional
	public ResponseEntity<?> cadastrarAvisoDeViagem(@PathVariable UUID idCartao,
			@RequestBody @Valid AvisoViagemDto avisoViagemDto, HttpServletRequest request, UriComponentsBuilder uri) {

		Cartao cartao = manager.find(Cartao.class, idCartao);

		if (cartao == null) {
			logger.error("Cartão não encontrado para aviso de viagem");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		logger.info("[CADASTRO DE AVISO] Aviso viagem enviado para o sistema! Cartão: {}", idCartao);
		integracaoCartao.avisoDeViagem(cartao.getNumeroCartao(), avisoViagemDto);

		AvisoViagem avisoViagem = avisoViagemDto.toAviso(request);
		manager.persist(avisoViagem);
		logger.info("Aviso viagem cadastrado! Aviso número={}", avisoViagem.getId());
		cartao.addAvisoViagem(avisoViagem);
		manager.merge(cartao);
		logger.info("Aviso associado ao cartão={}", cartao.getNumeroCartao());
		return ResponseEntity.created(uri.path("/avisos/{id}").buildAndExpand(avisoViagem.getId()).toUri()).build();
	}
}
