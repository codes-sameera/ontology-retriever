FROM adoptopenjdk:11.0.11_9-jre-hotspot

WORKDIR  ontology

COPY target/ontology-retriever-0.0.1-SNAPSHOT.jar ontology-retriever.jar

ENTRYPOINT ["java","-jar","project-manager.jar"]

EXPOSE 8080