package br.com.zup.proposta.Proposta.EnumProspota;

public enum StatusAvaliacaoResposta {
	

    COM_RESTRICAO(StatusAvaliacaoProposta.NAO_ELEGIVEL),
    SEM_RESTRICAO(StatusAvaliacaoProposta.ELEGIVEL);

    private StatusAvaliacaoProposta statusAvaliacao;

    private StatusAvaliacaoResposta(StatusAvaliacaoProposta statusAvaliacao) {
        this.statusAvaliacao = statusAvaliacao;
    }

    public StatusAvaliacaoProposta getStatusAvaliacao() {
        return statusAvaliacao;
    }
}