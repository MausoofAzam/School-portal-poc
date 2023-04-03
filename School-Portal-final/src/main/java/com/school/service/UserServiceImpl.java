package com.school.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.school.dto.UserRegistrationDto;
import com.school.entity.Role;
import com.school.entity.User;
import com.school.repository.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolesRepository rolesRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public User save(UserRegistrationDto registrationDto) throws RuntimeException {
		Set<String> strRoles = null;
		
		Set<Role> roles = new HashSet<>();
		
		User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(),
				bCryptPasswordEncoder.encode(registrationDto.getPassword()));

		if (userRepository.existsByEmail(registrationDto.getEmail())) {
			throw new RuntimeException("Email already exists!");
		}
		if (strRoles == null) {
			Role userRole = rolesRepository.findByName("ROLE_STUDENT")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			log.info("singnup as ROLE_STUDENT : ", userRole);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role teacherRole = rolesRepository.findByName("ROLE_TEACHER")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					log.info("case singnup as teacherRole : ", teacherRole);
					roles.add(teacherRole);

					break;
				case "mod":
					Role principleRole = rolesRepository.findByName("ROLE_PRINCIPLE")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					log.info("case singnup as principle role : ", principleRole.toString());
					roles.add(principleRole);

					break;
				default:
					Role studentRole = rolesRepository.findByName("ROLE_USER")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					log.info("case singnup as user Role : ", studentRole);
					roles.add(studentRole);
				}
			});
		}
		user.setRoles(roles);
		return userRepository.save(user);
	}
}
