package com.sameeracodes.ontologyretriever.model.mapper;

import static org.junit.jupiter.api.Assertions.*;


import com.sameeracodes.ontologyretriever.model.storage.Ontology;
import java.net.URI;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OntologyOLSMapperTest {
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
  void testStorageToOLSModelMapping() {
    com.sameeracodes.ontologyretriever.model.ols.Ontology olsOntology = Mappers.getMapper(OntologyOLSMapper.class).map(ontology);
    assertAll(
        () -> assertEquals("onto", olsOntology.getOntologyId()),
        () -> assertEquals("onto description", olsOntology.getConfig().getDescription()),
        () -> assertEquals("onto title", olsOntology.getConfig().getTitle()),
        () -> assertEquals(1, olsOntology.getConfig().getDefinitionProperties().size()),
        () -> assertEquals(1, olsOntology.getConfig().getSynonymProperties().size())
    );

    Ontology sOntology = Mappers.getMapper(OntologyOLSMapper.class).map(olsOntology);
    assertAll(
        () -> assertEquals("onto", sOntology.getOntologyId()),
        () -> assertEquals("onto description", sOntology.getDescription()),
        () -> assertEquals("onto title", sOntology.getTitle()),
        () -> assertEquals(1, sOntology.getDefinitionProperties().size()),
        () -> assertEquals(1, sOntology.getSynonymProperties().size())
    );
  }
}