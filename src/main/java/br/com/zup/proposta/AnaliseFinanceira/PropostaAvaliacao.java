package br.com.zup.proposta.AnaliseFinanceira;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import br.com.zup.proposta.Proposta.Proposta;
import br.com.zup.proposta.Proposta.EnumProspota.StatusAvaliacaoProposta;

@Service
@Validated
public class PropostaAvaliacao {

    @Autowired
    private IntegracaoProposta integracaoProposta;

    private final Logger logger = LoggerFactory.getLogger(Proposta.class);

    public StatusAvaliacaoProposta executa(@NotNull @Validated Proposta proposta) {

        ResultadoAnaliseProposta resultadoAnalisePropostaJson =
                integracaoProposta.avalia(new SolicitacaoAnaliseProposta(proposta)).getBody();

        logger.info("[PROPOSTA={} STATUS={}] Status de avaliação de proposta retornado com sucesso!",

                resultadoAnalisePropostaJson.getNome(), resultadoAnalisePropostaJson.getResultadoSolicitacao());

        return resultadoAnalisePropostaJson.retornaStatus();
    }
}