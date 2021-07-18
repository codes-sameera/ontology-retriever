package com.sameeracodes.ontologyretriever.model.ols;

import java.net.URI;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OntologyResourceConfig {
  private String title;
  private String description;
  private Collection<URI> definitionProperties;
  private Collection<URI> synonymProperties;
}
