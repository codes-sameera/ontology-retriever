# ontology-retriever
The backend spring-boot micro service for ontology retrieval

### API documentation

The ontology-retriever exposes two APIs to retrieve and store ontologies

1. Retrieve Ontology by its ID: `GET /ontology/{id}`<br>
Returns:<br> 
```json
{
  "ontologyId": "aeo",
  "title": "Anatomical Entity Ontology",
  "description": "AEO is an ontology of anatomical structures that expands CARO, the Common Anatomy Reference Ontology",
  "synonymProperties": [
    "http://www.geneontology.org/formats/oboInOwl#hasExactSynonym"
  ],
  "definitionProperties": [
    "http://purl.obolibrary.org/obo/IAO_0000115"
  ]
}
```
2. Save an Ontology: `POST /ontology`<br>
Body:<br>
```json
{
  "ontologyId": "aeo",
  "title": "Anatomical Entity Ontology",
  "description": "AEO is an ontology of anatomical structures that expands CARO, the Common Anatomy Reference Ontology",
  "synonymProperties": [
    "http://www.geneontology.org/formats/oboInOwl#hasExactSynonym"
  ],
  "definitionProperties": [
    "http://purl.obolibrary.org/obo/IAO_0000115"
  ]
}
