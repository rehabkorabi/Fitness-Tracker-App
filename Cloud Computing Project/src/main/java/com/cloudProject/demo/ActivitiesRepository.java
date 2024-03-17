package com.cloudProject.demo;


import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivitiesRepository extends CosmosRepository<Activity, String>{
}