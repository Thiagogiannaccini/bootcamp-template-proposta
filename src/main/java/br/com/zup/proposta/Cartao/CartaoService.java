package br.com.zup.proposta.Cartao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.zup.proposta.Proposta.ExecutorTransacao;
import br.com.zup.proposta.Proposta.Proposta;
import br.com.zup.proposta.Proposta.EnumProspota.StatusAvaliacaoProposta;

@Service
public class CartaoService {
	private final IntegracaoCartao integracaoCartao;
	private final EntityManager manager;
	private final Logger logger;
	@Autowired
	ExecutorTransacao executorTransacao;

	public CartaoService(IntegracaoCartao integracaoCartao, EntityManager manager) {
		this.integracaoCartao = integracaoCartao;
		this.manager = manager;
		this.logger = LoggerFactory.getLogger(CartaoService.class);
}

	@Scheduled(fixedDelayString = "${tempo.verificadordecartao}")
	@Transactional
	public void associa() {
		logger.info("[SCHEDULED] Verificando se existem propostas cadastradas...");

		TypedQuery<Proposta> propostas = manager.createNamedQuery("findPropostaByStatus", Proposta.class)
				.setParameter("statusAvaliacao", StatusAvaliacaoProposta.ELEGIVEL);

		propostas.getResultList().forEach(proposta -> {
			CartaoResponse cartaoResponse = integracaoCartao.pesquisaIdProposta(proposta.getId().longValue());

			if (proposta.verificarSeNaoExisteCartao()) {
				Assert.notNull(cartaoResponse, "O cartão não pode ser nulo");
				
				Cartao cartao = cartaoResponse.toCartao();
				executorTransacao.salvaEComita(cartao);
				proposta.incluirCartaoNaProposta(cartao);
				executorTransacao.atualizaEComita(proposta);
				logger.info("CRIAÇÃO DE CARTÃO PARA PROPOSTA ELEGÍVEL- Cartão criado para proposta {}",
						proposta.getId());
			}
		});
	}
}