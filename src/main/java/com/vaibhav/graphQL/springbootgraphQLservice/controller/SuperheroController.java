package com.vaibhav.graphQL.springbootgraphQLservice.controller;

import com.vaibhav.graphQL.springbootgraphQLservice.model.Superhero;
import com.vaibhav.graphQL.springbootgraphQLservice.service.GraphqlService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/superhero")
@RestController
public class SuperheroController {

    @Autowired
    GraphqlService graphqlService;

    @PostMapping(path = "/all")
    public ResponseEntity<ExecutionResult> getAllBooks(@RequestBody String query) {
        ExecutionResult result = graphqlService.getGraphQL().execute(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
