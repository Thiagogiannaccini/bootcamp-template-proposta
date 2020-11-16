package br.com.zup.proposta.Proposta;

import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.AnaliseFinanceira.PropostaAvaliacao;
import br.com.zup.proposta.Proposta.EnumProspota.StatusAvaliacaoProposta;
import br.com.zup.proposta.Validators.BloqueiaDocumentoIgualValidator;
import feign.FeignException;

@RestController
public class PropostasController {

	@Autowired
	private ExecutorTransacao executorTransacao;

	@Autowired
	private PropostaAvaliacao propostaAvaliacao;

	@Autowired
	BloqueiaDocumentoIgualValidator bloqueiaDocumentoIgualValidator;

	private Logger logger = LoggerFactory.getLogger(Proposta.class);

	@PostMapping("/propostas")
	@Transactional
	public ResponseEntity<?> cria(@RequestBody @Valid PropostaDto dto, UriComponentsBuilder builder) {
		if (!bloqueiaDocumentoIgualValidator.isValid(dto)) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		Proposta proposta = dto.toModel();

		try {
			executorTransacao.salvaEComita(proposta);
			StatusAvaliacaoProposta avaliacao = propostaAvaliacao.executa(proposta);
			proposta.atualizaStatus(avaliacao);
			executorTransacao.atualizaEComita(proposta);

			logger.info("A proposta de número={}, foi criada com sucesso", proposta.getId());

			URI enderecoConsulta = builder.path("/propostas/{id}").build(proposta.getId());
			return ResponseEntity.created(enderecoConsulta).build();

		} catch (FeignException.UnprocessableEntity e) {

			executorTransacao.salvaEComita(proposta);

			logger.info("A proposta de número={}, foi criada com sucesso", proposta.getId());

			URI enderecoConsulta = builder.path("/propostas/{id}").build(proposta.getId());
			return ResponseEntity.created(enderecoConsulta).build();
		}
	}
}