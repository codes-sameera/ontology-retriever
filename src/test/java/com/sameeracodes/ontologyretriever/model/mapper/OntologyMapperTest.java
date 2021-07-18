package com.sameeracodes.ontologyretriever.model.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


import com.sameeracodes.ontologyretriever.model.communication.Ontology;
import java.net.URI;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OntologyMapperTest {
  private Ontology ontology;

  @BeforeAll
  void setup() {
    ontology = new Ontology();
    ontology.setOntologyId("onto");
    ontology.setDescription("onto description");
    ontology.setTitle("onto title");
    ontology.setDefinitionProperties(new ArrayList<>());
    ontology.getDefinitionProperties().add(URI.create("ontodef"));
    ontology.setSynonymProperties(new ArrayList<>());
    ontology.getSynonymProperties().add(URI.create("ontosyn"));
  }

  @Test
  void testCommunicationToStorageModelMapping() {
    com.sameeracodes.ontologyretriever.model.storage.Ontology sOntology = Mappers.getMapper(OntologyMapper.class).map(ontology);
    assertAll(
        () -> assertEquals("onto", sOntology.getOntologyId()),
        () -> assertEquals("onto description", sOntology.getDescription()),
        () -> assertEquals("onto title", sOntology.getTitle()),
        () -> assertEquals(1, sOntology.getDefinitionProperties().size()),
        () -> assertEquals(1, sOntology.getSynonymProperties().size())
    );

    Ontology cOntology = Mappers.getMapper(OntologyMapper.class).map(sOntology);
    assertAll(
        () -> assertEquals("onto", cOntology.getOntologyId()),
        () -> assertEquals("onto description", cOntology.getDescription()),
        () -> assertEquals("onto title", cOntology.getTitle()),
        () -> assertEquals(1, cOntology.getDefinitionProperties().size()),
        () -> assertEquals(1, cOntology.getSynonymProperties().size())
    );
  }
}