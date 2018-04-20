package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAllUsers(){
		return (List<User>)userRepository.findAll();
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
