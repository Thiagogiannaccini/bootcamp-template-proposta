package br.com.zup.proposta.Bloqueio;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.zup.proposta.Cartao.Cartao;
import br.com.zup.proposta.Cartao.IntegracaoCartao;

@Service
public class BloqueioService {

	@Autowired
	private IntegracaoCartao integracaoCartao;

	private Logger logger = LoggerFactory.getLogger(BloqueioService.class);

	@Value("${spring.application.name}")
	private String nomeDoSistema;

	public void bloqueandoCartao(Cartao cartao) {
		if (cartao.verificarCartaoBloqueado()) {
			logger.info("Este cartão já esta bloqueado! Cartão = {}", cartao.getId());
			return;
		}
		Map bloqueioRequest = new HashMap<>();
		bloqueioRequest.put("sistemaResponsavel", nomeDoSistema);
		ResponseEntity<?> responseEntity = integracaoCartao.bloquearCartao(cartao.getNumeroCartao(), bloqueioRequest);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			cartao.bloquearCartao();
			logger.info("Cartão bloqueado: {} ", cartao.getId());
		}
	}
}