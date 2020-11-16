package br.com.zup.proposta.AnaliseFinanceira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "integracoes", url = "${feign.analise-url}")
public interface IntegracaoProposta {
    @PostMapping("api/solicitacao")
    ResponseEntity<ResultadoAnaliseProposta> avalia(SolicitacaoAnaliseProposta solicitacaoAnaliseProposta);
}