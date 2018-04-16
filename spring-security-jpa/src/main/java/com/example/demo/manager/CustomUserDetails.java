package com.example.demo.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.domain.Person;
import com.example.demo.domain.UserRole;



	
/**
 * User Details - Represents information about a logged in user.
 */
public class CustomUserDetails implements UserDetails {
   

	/**
     * The entity that represents the currently logged in user.
     */
    private final Person person;

    /**
     * List of Roles associated w/ the logged in user.
     */
    private final List<GrantedAuthority> authorities;

    /**
     * Constructor.
     */
    public CustomUserDetails(final Person person) {
        // set model
        this.person = person;

        // Generate authorities/roles
        List<GrantedAuthority> roles = new ArrayList<>();

        // Everyone gets user
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Add Admin
        if (UserRole.ROLE_ADMIN.equals(person.getRole())) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        // Save to immutable collection.
        authorities = Collections.unmodifiableList(roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }
    
    //Username set as userName
    @Override
    public String getUsername() {
        return person.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return person.getActive();
    }

    /**
     * @return The user entity representing the currently logged in user.
     */
    public Person getUserModel() {
        return person;
    }

    /**
     * @return UserId of the currently logged in user.
     */
    public long getUserId() {
        return getUserModel().getId();
    }
}
