package de.tappd.Tappd.service;

import de.tappd.Tappd.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}