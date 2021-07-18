package com.sameeracodes.ontologyretriever.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.sameeracodes.ontologyretriever.model.communication.Ontology;
import com.sameeracodes.ontologyretriever.service.OntologyService;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.MongoTransactionException;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OntologyController.class)
class OntologyControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private OntologyService ontologyService;

  @Test
  void testFetchOntologyWhenOntologyIsFound() throws Exception {
    Ontology ontology = new Ontology();
    ontology.setOntologyId("abcd");
    when(ontologyService.fetchOntology("abcd")).thenReturn(ontology);

    mockMvc.perform(get("/ontologies/abcd"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void testFetchOntologyWhenOntologyIsNotFound() throws Exception {
    Ontology ontology = new Ontology();
    ontology.setOntologyId("abcd");
    when(ontologyService.fetchOntology("abcd")).thenThrow(new NoSuchElementException());

    mockMvc.perform(get("/ontologies/abcd"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void testFetchOntologyWhenThereIsADatabaseError() throws Exception {
    Ontology ontology = new Ontology();
    ontology.setOntologyId("abcd");
    when(ontologyService.fetchOntology("abcd")).thenThrow(new MongoTransactionException("Fail"));

    mockMvc.perform(get("/ontologies/abcd"))
        .andDo(print())
        .andExpect(status().isInternalServerError());
  }
}