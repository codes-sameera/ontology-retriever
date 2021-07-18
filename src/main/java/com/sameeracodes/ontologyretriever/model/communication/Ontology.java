package com.sameeracodes.ontologyretriever.model.communication;


import java.net.URI;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Ontology {
  private String ontologyId;
  private String title;
  private String description;
  private Collection<URI> synonymProperties;
  private Collection<URI> definitionProperties;
}
