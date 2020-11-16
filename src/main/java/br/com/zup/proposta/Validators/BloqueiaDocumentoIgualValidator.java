package br.com.zup.proposta.Validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import br.com.zup.proposta.Proposta.PropostaDto;

@Component
public class BloqueiaDocumentoIgualValidator {

	@PersistenceContext
	private EntityManager manager;

	public boolean isValid(PropostaDto dto ) {
		return manager.createQuery(
				"select p.documento from Proposta p where p.documento = :documento")
				.setParameter("documento", dto.getDocumento())
				.getResultList().isEmpty();
	}

}