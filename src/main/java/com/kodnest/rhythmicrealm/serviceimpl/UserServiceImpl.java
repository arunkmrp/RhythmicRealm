package com.kodnest.rhythmicrealm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.rhythmicrealm.model.User;
import com.kodnest.rhythmicrealm.repository.UserRepository;
import com.kodnest.rhythmicrealm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);

	}

	@Override
	public boolean usernameOrEmailExists(String usernameOrEmail) {
		return userRepository.existsByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
	}

	@Override
	public boolean authenticateUser(String usernameOrEmail, String password) {

		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public User findByUsernameOrEmail(String usernameOrEmail) {
		return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
