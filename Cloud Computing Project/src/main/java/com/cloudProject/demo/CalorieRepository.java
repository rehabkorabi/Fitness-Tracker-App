package com.cloudProject.demo;

import com.azure.spring.data.cosmos.repository.CosmosRepository;

public interface CalorieRepository extends CosmosRepository<CalorieIntake, String>{

}
