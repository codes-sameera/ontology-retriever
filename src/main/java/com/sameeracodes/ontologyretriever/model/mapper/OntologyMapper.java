package com.sameeracodes.ontologyretriever.model.mapper;

import com.sameeracodes.ontologyretriever.model.storage.Ontology;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OntologyMapper {
  com.sameeracodes.ontologyretriever.model.communication.Ontology map(Ontology storageObj);

  Ontology map(com.sameeracodes.ontologyretriever.model.communication.Ontology communicationObj);
}