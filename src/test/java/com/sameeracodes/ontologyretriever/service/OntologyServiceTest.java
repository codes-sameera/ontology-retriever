package com.sameeracodes.ontologyretriever.service;

import static com.sameeracodes.ontologyretriever.constants.OLSConstants.OLS_ENDPOINT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sameeracodes.ontologyretriever.model.mapper.OntologyOLSMapper;
import com.sameeracodes.ontologyretriever.model.storage.Ontology;
import com.sameeracodes.ontologyretriever.repository.OntologyAccess;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class OntologyServiceTest {
  @Mock
  OntologyAccess ontologyAccess;
  @Mock
  RestTemplate restTemplate;
  @InjectMocks
  OntologyService ontologyService;

  @Test
  void testFetchOntologyForOntologyStoredInDatabase() {
    Ontology ontology = new Ontology();
    ontology.setOntologyId("abcd");
    when(ontologyAccess.findById("abcd")).thenReturn(Optional.of(ontology));

    assertEquals("abcd",ontologyService.fetchOntology("abcd").getOntologyId());

    verify(ontologyAccess, times(1)).findById("abcd");
    verify(restTemplate, never()).getForObject(OLS_ENDPOINT + "abcd", com.sameeracodes.ontologyretriever.model.ols.Ontology.class);
  }

  @Test
  void testFetchOntologyForOntologyNotStoredInDatabase() {
    com.sameeracodes.ontologyretriever.model.ols.Ontology ontology = new com.sameeracodes.ontologyretriever.model.ols.Ontology();
    ontology.setOntologyId("abcd");
    when(ontologyAccess.findById("abcd")).thenReturn(Optional.empty());
    when(restTemplate.getForObject(OLS_ENDPOINT + "abcd", com.sameeracodes.ontologyretriever.model.ols.Ontology.class)).thenReturn(ontology);
    when(ontologyAccess.save(any(Ontology.class))).thenReturn(Mappers.getMapper(OntologyOLSMapper.class).map(ontology));

    assertEquals("abcd", ontologyService.fetchOntology("abcd").getOntologyId());

    verify(ontologyAccess, times(1)).findById("abcd");
    verify(restTemplate, times(1)).getForObject(OLS_ENDPOINT + "abcd", com.sameeracodes.ontologyretriever.model.ols.Ontology.class);
    verify(ontologyAccess, times(1)).save(any(Ontology.class));
  }

  @Test
  void testFetchOntologyForMissingOntology() {
    com.sameeracodes.ontologyretriever.model.ols.Ontology ontology = new com.sameeracodes.ontologyretriever.model.ols.Ontology();
    ontology.setOntologyId("abcd");
    when(ontologyAccess.findById("abcd")).thenReturn(Optional.empty());
    when(restTemplate.getForObject(OLS_ENDPOINT + "abcd", com.sameeracodes.ontologyretriever.model.ols.Ontology.class)).thenThrow(new NoSuchElementException());

    assertThrows(NoSuchElementException.class, () -> ontologyService.fetchOntology("abcd"));

    verify(ontologyAccess, times(1)).findById("abcd");
    verify(restTemplate, times(1)).getForObject(OLS_ENDPOINT + "abcd", com.sameeracodes.ontologyretriever.model.ols.Ontology.class);
  }
}