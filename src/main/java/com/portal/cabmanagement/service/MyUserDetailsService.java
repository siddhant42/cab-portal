package com.portal.cabmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portal.cabmanagement.entity.User;
import com.portal.cabmanagement.repository.UserRepository;



@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	Optional<User> user = userRepository.findByUsername(username);

    	user.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));

//    	return user.map(UserDetailsImpl::new).get();
    	return new UserDetailsImpl(user.get());
    }
}