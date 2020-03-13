package com.project.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.service.AddForm;
import com.project.entity.User;

@Service
public class UserService extends AppService {
	@Autowired
	PasswordEncoder passwordEncoder;

	public void createUser(AddForm userAddForm) throws NoSuchAlgorithmException {

		User user = new User();
		user.setName(userAddForm.getName());
		user.setEmail(userAddForm.getEmail());
		user.setPassword(passwordEncoder.hashMD5(userAddForm.getPassword()));

		userRepository.save(user);
	}

	public Page<User> findAllUser(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public User findOne(int userId) {
		return userRepository.findOne(userId);
	}

	public EditForm setUserEditForm(int userId) {
		User user = userRepository.findOne(userId);
		EditForm userEditForm = new EditForm();
		userEditForm.setUserId(user.getId());
		userEditForm.setName(user.getName());
		userEditForm.setEmail(user.getEmail());
		return userEditForm;
	}

	public void updateUser(EditForm EditForm) throws NoSuchAlgorithmException {

		User user = userRepository.findOne(EditForm.getUserId());
		user.setName(EditForm.getName());
		user.setPassword(passwordEncoder.hashMD5(EditForm.getPassword()));

		userRepository.save(user);
	}

	

	public void deleteUser(int userId) {
		userRepository.delete(userId);
	}

	
}
