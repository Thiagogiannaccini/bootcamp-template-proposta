package br.com.zup.proposta.Proposta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AcompanharPropostasController {

	   @PersistenceContext
	    EntityManager manager;

	    private Logger logger = LoggerFactory.getLogger(AcompanharPropostasController.class);

	    @GetMapping("/propostas/{id}")
	    public ResponseEntity<?> acompanha(@PathVariable("id") Long id) {
	        Proposta proposta = manager.find(Proposta.class, id);
	    
	        if(proposta == null){
	        logger.info("Esta proposta não existe");
	            return ResponseEntity.notFound().build();
	        }
	        logger.info("Proposta  de id={} encontrada", id);
	        PropostaResponse propostaResponse = new PropostaResponse(proposta);
	        return ResponseEntity.ok(propostaResponse);
	    }
	}


