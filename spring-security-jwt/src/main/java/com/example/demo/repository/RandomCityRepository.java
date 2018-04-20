package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.RandomCity;

@Repository
public interface RandomCityRepository extends JpaRepository<RandomCity,Long> {

	List<RandomCity> findAll();
	
}
