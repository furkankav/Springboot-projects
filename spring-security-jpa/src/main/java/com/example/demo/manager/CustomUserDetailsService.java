package com.example.demo.manager;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.domain.Person;
import com.example.demo.repository.PersonRepository;

/**
 * Custom User Details Service.  Create Custom User Details implementation.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    
	private  PersonRepository personRepository;


    public CustomUserDetailsService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        final Person person = personRepository.findByUserName(userName);
        if (person == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new CustomUserDetails(person);
    }
}