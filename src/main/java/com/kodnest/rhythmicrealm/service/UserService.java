package com.kodnest.rhythmicrealm.service;

import java.util.List;

import com.kodnest.rhythmicrealm.model.User;

public interface UserService {
	User findByUsername(String username);

	User findByEmail(String email);

	void saveUser(User user);

	boolean usernameOrEmailExists(String usernameOrEmail);

	boolean authenticateUser(String usernameOrEmail, String password);

	User findByUsernameOrEmail(String usernameOrEmail);

	List<User> getAllUsers();

}
