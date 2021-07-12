package com.microservice.cep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.cep.model.FaixaCep;

@Repository
public interface FaixaCepRepository extends  JpaRepository<FaixaCep, Long> {

}
