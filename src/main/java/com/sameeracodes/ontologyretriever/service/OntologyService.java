package com.sameeracodes.ontologyretriever.service;

import static com.sameeracodes.ontologyretriever.constants.OLSConstants.OLS_ENDPOINT;

import com.sameeracodes.ontologyretriever.model.communication.Ontology;
import com.sameeracodes.ontologyretriever.model.mapper.OntologyMapper;
import com.sameeracodes.ontologyretriever.model.mapper.OntologyOLSMapper;
import com.sameeracodes.ontologyretriever.repository.OntologyAccess;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OntologyService {
  @Autowired
  private OntologyAccess ontologyAccess;

  @Autowired
  private RestTemplate restTemplate;

  public Ontology fetchOntology(String id) {
    Optional<Ontology> ontology = Optional.ofNullable(
        Mappers.getMapper(OntologyMapper.class).map(
            ontologyAccess.findById(id).orElseGet(
                () -> {
                  // try to get the ontology from the OLS
                  com.sameeracodes.ontologyretriever.model.ols.Ontology olsOntology;
                  try {
                    log.debug("Ontology- {} not found in database, trying to fetch it from the OLS.", id);
                    olsOntology = restTemplate.getForObject(OLS_ENDPOINT + id,
                        com.sameeracodes.ontologyretriever.model.ols.Ontology.class);
                  }
                  catch (Exception e) {
                    // ontology cannot be found on the OLS
                    log.debug("Ontology- {} not found in OLS as well", id);
                    return null;
                  }
                  log.debug("Saving ontology- {} to database", id);
                  return ontologyAccess.save(Mappers.getMapper(OntologyOLSMapper.class).map(olsOntology));
                })
        )
    );
    return ontology.orElseThrow(() -> new NoSuchElementException("Ontology not found: " + id));
  }

  public void saveOntology(Ontology ontology) {
    log.debug("Saving ontology- {} to database", ontology);
    ontologyAccess.save(Mappers.getMapper(OntologyMapper.class).map(ontology));
  }
}
