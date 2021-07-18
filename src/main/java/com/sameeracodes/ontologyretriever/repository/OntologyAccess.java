package com.sameeracodes.ontologyretriever.repository;

import com.sameeracodes.ontologyretriever.model.storage.Ontology;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OntologyAccess extends MongoRepository<Ontology, String> {

}
