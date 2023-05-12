package org.medmota.reativedemo.services;

import org.medmota.reativedemo.domain.User;
import org.medmota.reativedemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Mono<User> saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Mono<User> delete(String id) {
		return userRepository.findById(id).flatMap(user -> userRepository.deleteById(user.getId()).thenReturn(user));
	}

	@Override
	public Mono<User> update(String id, User user) {

		return userRepository.findById(id).flatMap(u -> {
			u.setId(id);
			u.setName(user.getName());
			u.setEmailId(user.getEmailId());
			return saveUser(u);
		}).switchIfEmpty(Mono.empty());

	}

	@Override
	public Flux<User> findall() {
		return userRepository.findAll();
	}

	@Override
	public Mono<User> findById(String id) {
		return userRepository.findById(id);
	}

}
