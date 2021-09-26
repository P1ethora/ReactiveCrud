package com.example.reactcrud.controller;

import com.example.reactcrud.dto.UserDto;
import com.example.reactcrud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public Flux<UserDto> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public Mono<UserDto> getUser(@PathVariable long id) {
        return service.getUser(id);
    }

    @PostMapping
    public Mono<UserDto> saveUser(@RequestBody Mono<UserDto> userDtoMono) {
        return service.saveUser(userDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<UserDto> updateUser(@RequestBody Mono<UserDto> userDtoMono, @PathVariable long id) {
        return service.updateUser(userDtoMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteUser(@PathVariable long id) {
        return service.deleteUser(id);
    }
}