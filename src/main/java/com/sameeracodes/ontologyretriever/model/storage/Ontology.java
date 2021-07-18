package com.sameeracodes.ontologyretriever.model.storage;

import java.net.URI;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "Ontology")
public class Ontology {
  @Id
  private String ontologyId;
  private String title;
  private String description;
  private Collection<URI> synonymProperties;
  private Collection<URI> definitionProperties;
}
