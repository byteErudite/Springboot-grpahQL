package com.vaibhav.graphQL.springbootgraphQLservice.service;

import com.vaibhav.graphQL.springbootgraphQLservice.model.Superhero;
import com.vaibhav.graphQL.springbootgraphQLservice.repository.SuperheroRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllHeroDataFetcher implements DataFetcher<List<Superhero>> {

    @Autowired
    private SuperheroRepository superheroRepository;

    @Override
    public List<Superhero> get(DataFetchingEnvironment environment) throws Exception {
        return superheroRepository.findAll();
    }
}
