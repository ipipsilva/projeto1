package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Component
public class DataInitialize implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		List<User> listaUsuarios = userRepository.findAll();

		if (listaUsuarios.isEmpty()) {
			createUser("Igor Silva", "igor.silva@gmail.com");
			createUser("Danielle Fernandes", "danielle.fag@gmail.com");
		}

		User user = userRepository.getOne(2L);
		System.out.println(user);
	}

	private void createUser(String name, String email) {

		User user = new User(name, email);
		userRepository.save(user);
	}
}