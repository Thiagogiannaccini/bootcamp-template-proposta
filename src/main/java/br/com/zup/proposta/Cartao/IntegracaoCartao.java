package br.com.zup.proposta.Cartao;

import java.util.Map;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zup.proposta.AvisoViagem.AvisoViagemDto;

@FeignClient(name = "cartao", url = "${host.cartao}")
public interface IntegracaoCartao {

	@GetMapping("/api/cartoes")
	CartaoResponse pesquisaIdProposta(@RequestParam Long idProposta);

	@PostMapping("/api/cartoes/{idCartao}/bloqueios")
	ResponseEntity<?> bloquearCartao(@PathVariable UUID idCartao, @RequestBody Map bloqueioRequest);

	@PostMapping("/api/cartoes/{idCartao}/avisos")
	ResponseEntity<?> avisoDeViagem(@PathVariable UUID idCartao, @RequestBody AvisoViagemDto avisoViagemDto);

	@PostMapping("/api/cartoes/{idCartao}/carteiras")
    ResponseEntity<?> associarCarteira(@RequestBody Map carteiraRequest, @PathVariable UUID idCartao);
}