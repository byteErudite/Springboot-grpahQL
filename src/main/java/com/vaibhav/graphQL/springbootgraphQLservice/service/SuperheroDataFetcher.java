package com.vaibhav.graphQL.springbootgraphQLservice.service;

import com.vaibhav.graphQL.springbootgraphQLservice.model.Superhero;
import com.vaibhav.graphQL.springbootgraphQLservice.repository.SuperheroRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SuperheroDataFetcher implements DataFetcher<Superhero> {

    @Autowired
    private SuperheroRepository superheroRepository;

    @Override
    public Superhero get(DataFetchingEnvironment environment) throws Exception {
        String id = environment.getArgument("id");
        return superheroRepository.findById(id).orElse(null);
    }
}
