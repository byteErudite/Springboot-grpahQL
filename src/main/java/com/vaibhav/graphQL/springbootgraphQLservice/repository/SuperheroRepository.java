package com.vaibhav.graphQL.springbootgraphQLservice.repository;

import com.vaibhav.graphQL.springbootgraphQLservice.model.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperheroRepository extends JpaRepository<Superhero, String> {
}
