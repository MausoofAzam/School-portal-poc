package com.school.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.school.dto.UserRegistrationDto;
import com.school.entity.User;

public interface UserService  extends UserDetailsService{
	
	User save(UserRegistrationDto  userRegistrationDto);
}
