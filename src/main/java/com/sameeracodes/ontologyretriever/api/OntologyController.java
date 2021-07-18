package com.sameeracodes.ontologyretriever.api;

import com.sameeracodes.ontologyretriever.model.communication.Ontology;
import com.sameeracodes.ontologyretriever.service.OntologyService;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OntologyController {
  @Autowired
  private OntologyService ontologyService;

  @GetMapping("/ontologies/{id}")
  public Ontology fetchOntology(@PathVariable String id) {
    log.debug("Ontology- {} fetch request received.", id);
    return ontologyService.fetchOntology(id);
  }

  @PostMapping("/ontologies")
  @ResponseStatus(HttpStatus.CREATED)
  public void addTransaction(@RequestBody Ontology ontology) {
    ontologyService.saveOntology(ontology);
  }

  @ExceptionHandler({NoSuchElementException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Ontology does not exist")
  public void handleBadInputs(Exception ex) {
    log.error(ex.getMessage());
  }

  @ExceptionHandler({DataAccessException.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Database operation failed")
  public void handleDatabaseError(Exception ex) {
    log.error(ex.getMessage());
  }
}
