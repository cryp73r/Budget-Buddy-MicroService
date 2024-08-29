package com.cryp73r.authentication.security.service;

import com.cryp73r.authentication.model.User;
import com.cryp73r.authentication.repository.UserRepository;
import com.cryp73r.authentication.security.model.ClientUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " is not found in the DEPOT_USER table"));
        return new ClientUserDetails(user);
    }
}
