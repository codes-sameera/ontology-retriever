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
```
If there is a problem with the database, `INTERNAL_SERVER_ERROR` status is returned<br>
If there is no ontology for the given id, `BAD_REQUEST` status is returned<br>

### Developer Notes
- The CI
  - Builds on every commit to the master and on every pull request to the master
  - Executes maven build; compilation and unit tests
  - Builds a docker image and publishes to a repository in docker hub; this image is used in ontology-retriever-deployment
- Aspect **(spring aop)** is used for logging API calls made to the application
- Different model is used for storage to the database and for API communication
- For mapping and converting objects to different models, **MapStruct** library is used, to avoid writing mapping code
- Lombok is used to avoid boilerplate code

#### Please check [ontology-retreiver-deployment](https://github.com/codes-sameera/ontology-retriever-deployment/blob/main/README.md) for installing and using this application
