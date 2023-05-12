package org.medmota.reativedemo.services;

import org.medmota.reativedemo.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

	Mono<User> saveUser(User user);

	Mono<User> delete(String id);

	Mono<User> update(String id, User user);

	Flux<User> findall();

	Mono<User> findById(String id);

}
