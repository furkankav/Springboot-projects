package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.RandomCity;
import com.example.demo.repository.RandomCityRepository;

@Service
public class RandomCityService {
	@Autowired 
	private RandomCityRepository randomCityRepos;
	
	public List<RandomCity> findAllRandomCities(){
		return (List<RandomCity>)randomCityRepos.findAll();
	}
}
