package com.vaibhav.graphQL.springbootgraphQLservice.service;

import com.vaibhav.graphQL.springbootgraphQLservice.model.Superhero;
import com.vaibhav.graphQL.springbootgraphQLservice.repository.SuperheroRepository;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphqlService {

    @Value("classpath:superhero.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private SuperheroRepository superheroRepository;

    @Autowired
    private AllHeroDataFetcher allHeroDataFetcher;

    @Autowired
    private SuperheroDataFetcher superheroDataFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {
        loadDataInHSQL();
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {

        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allSuperheroes", allHeroDataFetcher)
                        .dataFetcher("singleSuperHero", superheroDataFetcher))
                .build();
    }

    private void loadDataInHSQL() {
        Stream.of(new Superhero("1", "Aqua man", "Arthur", new String[] {"live-in-water", "trident"},"1975"),
                new Superhero("2", "Super man", "Kent clark", new String[] {"flying", "laser-eye"},"1939"),
                new Superhero("3", "Bat man", "Bruce wayne", new String[] {"money"},"1939"),
                new Superhero("4", "Wonder women", "Diana Prince",new String[] {"flying", "lassoOfTruth"},"1943"),
                new Superhero("5", "Flash", "Barry Allen", new String[] {"super-speed", "time-travel"},"1949"))
                .forEach(superhero -> superheroRepository.save(superhero));
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
