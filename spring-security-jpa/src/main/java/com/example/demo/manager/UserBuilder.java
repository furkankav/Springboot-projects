package com.example.demo.manager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.Person;
import com.example.demo.domain.UserRole;

/**
 * Utility class for building new User entity instances.
 */
public final class UserBuilder {

    /**
     * Used to encode the passwords using one-way hash algorithm.
     */
    private final PasswordEncoder passwordEncoder;

    private String userName;
    private String password;
    private String email;
    private UserRole role = UserRole.ROLE_USER;
    private boolean active = true;

    /**
     * Default constructor, uses BCryptPassword encoder.
     */
    public UserBuilder() {
        this(new BCryptPasswordEncoder());
    }

    /**
     * For injecting alternative password encoders.
     */
    public UserBuilder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Set email value.
     */
    public UserBuilder withEmail(final String email) {
        this.email = email;
        return this;
    }

    /**
     * Set Display Name value.
     */
    public UserBuilder withUserName(final String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Set plain text password value.
     */
    public UserBuilder withPassword(final String password) {
        this.password = password;
        return this;
    }

    /**
     * Set User's Role.
    */ 
    public UserBuilder withRole(final UserRole role) {
        this.role = role;
        return this;
    }

    /**
     * Set if the user is active or not.
     */
    public UserBuilder withActive(final Boolean active) {
        this.active = active;
        return this;
    }

    /**
     * Build the entity.
     * @return Entity
     */
    public Person build() {
        // Ensure non-null/empty password
        final Person person = new Person();
        person.setUserName(userName);
        person.setPassword(passwordEncoder.encode(password));
        person.setEmail(email);
        person.setRole(role);
        person.setActive(active);
 
        return person;
    }

  
}