FROM adoptopenjdk:11.0.11_9-jre-hotspot

WORKDIR  ontology-retriever

COPY target/ontology-retriever-0.0.1-SNAPSHOT.jar ontology-retriever.jar

ENTRYPOINT ["java","-jar","ontology-retriever.jar"]

EXPOSE 8080