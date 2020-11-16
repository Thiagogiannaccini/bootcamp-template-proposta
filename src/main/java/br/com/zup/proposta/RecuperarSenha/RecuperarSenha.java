package br.com.zup.proposta.RecuperarSenha;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.zup.proposta.Cartao.Cartao;

@Entity
public class RecuperarSenha {

    @Id
    @GeneratedValue
    private UUID id;
    private  @NotNull LocalDateTime recuperadoEm = LocalDateTime.now();
    private  @NotBlank String ip;
    private  @NotBlank String userAgent;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public RecuperarSenha(){}

    public RecuperarSenha(String ip, String userAgent, Cartao cartao) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }
    public UUID getId() {
        return id;
    }
}