package com.movieTicket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieTicket.model.RegisterUser;
import com.movieTicket.repository.UserRepository;

@Service
public class RegisterUserService {

	@Autowired
	private UserRepository userRepo;
	
	public void saveUser(RegisterUser user) {
		
		userRepo.save(user);
	}
	
	public List<RegisterUser> getAllUser(){
		
		return userRepo.findAll();
	}
}
