package com.example;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface CycleRepository extends CrudRepository<Cycle, Integer> {}
