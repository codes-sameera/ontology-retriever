package com.sameeracodes.ontologyretriever.model.mapper;

import com.sameeracodes.ontologyretriever.model.storage.Ontology;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OntologyOLSMapper {

  @Mapping(source = "title", target = "config.title")
  @Mapping(source = "description", target = "config.description")
  @Mapping(source = "synonymProperties", target = "config.synonymProperties")
  @Mapping(source = "definitionProperties", target = "config.definitionProperties")
  com.sameeracodes.ontologyretriever.model.ols.Ontology map(Ontology storageObj);


  @Mapping(source = "config.title", target = "title")
  @Mapping(source = "config.description", target = "description")
  @Mapping(source = "config.synonymProperties", target = "synonymProperties")
  @Mapping(source = "config.definitionProperties", target = "definitionProperties")
  Ontology map(com.sameeracodes.ontologyretriever.model.ols.Ontology olsObj);
}