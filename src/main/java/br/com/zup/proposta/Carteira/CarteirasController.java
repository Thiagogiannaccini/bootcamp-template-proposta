package br.com.zup.proposta.Carteira;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.Cartao.Cartao;

@RestController
public class CarteirasController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private CarteiraService carteiraService;

	private Logger logger = LoggerFactory.getLogger(CarteirasController.class);

	@PostMapping("/cartoes/{idCartao}/carteiras/samsungpay")
	@Transactional
	public ResponseEntity<?> CartaoMaisSamsungPay(@PathVariable Long idCartao, @RequestBody @Valid CarteiraDto dto,
			UriComponentsBuilder builder) {
		return solicitacaoCarteira(TipoCarteira.SAMSUNG_PAY, idCartao, dto, builder);

	}

	@PostMapping("/{idCartao}/carteiras/paypal")
	@Transactional
	public ResponseEntity<?> CartaoMaisPayPal(@PathVariable Long idCartao, @RequestBody @Valid CarteiraDto dto,
			UriComponentsBuilder builder) {
		return solicitacaoCarteira(TipoCarteira.PAYPAL, idCartao, dto, builder);
	}

	protected ResponseEntity<?> solicitacaoCarteira(TipoCarteira tipoCarteira, Long idCartao, CarteiraDto dto,
			UriComponentsBuilder builder) {

		Cartao cartao = manager.find(Cartao.class, idCartao);

		if (cartao == null) {
			logger.info("Cartão não encontrado no sistema!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		if (cartao.carteiraAssociadaCartao(tipoCarteira)) {
			logger.info("Carteira já cadastrada para o cartão. Cartão: {}", cartao.getId());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		return carteiraService.AssociarCarteiraComCartao(tipoCarteira, cartao, dto.getEmail(), builder);
	}
}
