package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.RandomCity;
import com.example.demo.domain.User;
import com.example.demo.service.RandomCityService;
import com.example.demo.service.UserService;

@RestController
public class ResourceController {
	@Autowired
	UserService userService;
	
	@Autowired
	RandomCityService randomCityService;
	
	@RequestMapping(value = "/springjwt/cities")
	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	public List<RandomCity> getUser(){
		return randomCityService.findAllRandomCities();
	}
	
	@RequestMapping(value = "/springjwt/users", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public List<User> getUsers(){
		return userService.findAllUsers();
	}
	
	@RequestMapping(value = "/actuator/d", method = RequestMethod.GET)
	public List<User> getUsers2(){
		return userService.findAllUsers();
	}
}
