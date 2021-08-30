package com.portal.cabmanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.portal.cabmanagement.entity.Role;
import com.portal.cabmanagement.entity.User;
import com.portal.cabmanagement.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	public String registerNewUser(@RequestBody User user) {
		if(userRepo.findByEmail(user.getEmail()).isPresent()) {
			return "email already exist";
		}
		if(user.getPassword()!=null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		String nextUsername = findNextUsername(user.getEmail());
		user.setUsername(nextUsername);
		user.setActive(true);
		Role role = roleService.getDefaultRole();
		user.setRoles(new ArrayList<>(Arrays.asList(role)));
		userRepo.saveAndFlush(user);
		return "user created";
	}

	public void updateUser(String username, User user) {
		Optional<User> user1 = userRepo.findByUsername(username);
		if(user1.isPresent() && user1.get().isActive()) {
			 merge(user1.get(),user);
		}
	}

	public User getUserByUsername(String username) {
		User user = userRepo.findByUsername(username).orElseThrow(()-> new IllegalStateException("user does not exist"));
		return user;
	}
	
	private void merge(User user1,User user2) {
//		copyNonNullProperties(user1,user);
		if(user2.getEmail()!=null) 
			user1.setEmail(user2.getEmail());
		if(user2.getPassword()!=null) 
			user1.setPassword(passwordEncoder.encode(user2.getPassword()));
		userRepo.saveAndFlush(user1);
	}

//	@Override
//	public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
//		Optional<User> user = userRepo.findByEmail(userDetails.getUsername());
//		user.ifPresent(x->x.setPassword(passwordEncoder.encode(newPassword)));
//		userRepo.save(user.get());
//		return new UserDetailsImpl(user.get());
//	}
	public boolean checkUsername(String username) {
		return userRepo.findByUsername(username).isPresent();
	}
	private synchronized String findNextUsername(String email) {
		boolean flag = true;
		String user = email.substring(0, email.indexOf('@'));
		if(!userRepo.findByUsername(user).isPresent()) return user;
		int previd = 1;
		int nextid = 1;
		while(flag) {
			user = user+nextid;
			flag = userRepo.findByUsername(user).isPresent();
			previd = nextid;
			nextid = previd+nextid;
		}
		return user;
	}

	public void deleteuser(String username) {
		Optional<User> user = userRepo.findByUsername(username);
		if(user.isPresent()) {
			User user1 = user.get();
			user1.setActive(false);
			userRepo.saveAndFlush(user1);
		}
		
	}
	
}
