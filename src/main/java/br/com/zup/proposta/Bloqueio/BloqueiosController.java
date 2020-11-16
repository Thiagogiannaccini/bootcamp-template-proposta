package br.com.zup.proposta.Bloqueio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.Cartao.Cartao;

@RestController
public class BloqueiosController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private BloqueioService bloqueioService;

	private final Logger logger = LoggerFactory.getLogger(BloqueiosController.class);

	@PostMapping("/cartoes/{idCartao}/bloquear")
	@Transactional
	public ResponseEntity<?> bloquearCartao(@PathVariable Long idCartao, HttpServletRequest request,
			UriComponentsBuilder builder) {

		Cartao cartao = manager.find(Cartao.class, idCartao);

		if (cartao == null) {
			logger.info("Cartão não encontrado no sistema!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Bloqueio bloqueio = new Bloqueio(request.getRemoteAddr(), request.getHeader("User-Agent"));
		manager.persist(bloqueio);
		bloqueioService.bloqueandoCartao(cartao);
		cartao.addBloqueioDoCartao(bloqueio);
		manager.merge(cartao);
		logger.info("Bloqueio cartão={} cadastrado", cartao.getNumeroCartao());
		return ResponseEntity.created(builder.path("/bloqueios/{id}").buildAndExpand(bloqueio.getId()).toUri()).build();
	}
}