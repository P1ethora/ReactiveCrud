package com.example.reactcrud.service;

import com.example.reactcrud.dto.UserDto;
import com.example.reactcrud.repo.UserRepository;
import com.example.reactcrud.util.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public Flux<UserDto> getUsers() {
        return userRepository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<UserDto> getUser(Long id) {
        return userRepository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<UserDto> saveUser(Mono<UserDto> productDtoMono) {
        return productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(userRepository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<UserDto> updateUser(Mono<UserDto> productDtoMono, Long id) {
        return userRepository.findById(id)
                .flatMap(p -> productDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(userRepository::save)
                .map(AppUtils::entityToDto);

    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }

}
