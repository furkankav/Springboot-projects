package com.example.demo.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Person;
import com.example.demo.domain.UserRole;
import com.example.demo.repository.PersonRepository;

@Component
public class UserManager {
	
	@Autowired
	private  PersonRepository personRepository;
	
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	/**
     * Create a new user as registered from website.
     * @param userName User name of new user.
     * @param password Password of new user.
     * @param email of new user.
     * @param role of new user.
     * @return The new user.
     */
    public Person createNewUser( final String userName, final String password, final String email, final UserRole userRole) {
        final UserBuilder userBuilder = new UserBuilder();
        userBuilder
            .withUserName(userName)
            .withPassword(password)
            .withEmail(email)
            .withRole(userRole)
            .withActive(true);

        // Create them!
        return persistNewUser(userBuilder);
    }

    public String encodePassword(final String plaintext) {
        return passwordEncoder.encode(plaintext);
    }

    private Person persistNewUser(final UserBuilder userBuilder) {
        // Build user & save
        final Person person = userBuilder.build();
        personRepository.save(person);
        // return the user
        return person;
    }
	
}
