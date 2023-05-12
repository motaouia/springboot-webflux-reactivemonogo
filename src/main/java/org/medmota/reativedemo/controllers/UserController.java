package org.medmota.reativedemo.controllers;

import org.medmota.reativedemo.domain.User;
import org.medmota.reativedemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	private Mono<User> save(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@DeleteMapping("/users/{id}")
	private Mono<ResponseEntity<String>> delete(@PathVariable String id) {
		return this.userService.delete(id).flatMap(user -> Mono.just(ResponseEntity.ok("Deleted Successfully !!")))
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}

	@PutMapping("/users/{id}")
	private Mono<ResponseEntity<User>> updateUser(@PathVariable String id, @RequestBody User user) {
		return this.userService.update(id, user).flatMap(user1 -> Mono.just(ResponseEntity.ok(user1)))
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}

	@GetMapping("/users")
	private Flux<User> findAll() {
		return this.userService.findall();
	}

}
