package ru.sergey_white.reactiveexample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sergey_white.reactiveexample.exception.EmailAlreadyExistsException;
import ru.sergey_white.reactiveexample.exception.UserNotFoundException;
import ru.sergey_white.reactiveexample.mapper.UserMapper;
import ru.sergey_white.reactiveexample.model.dto.UserDto;
import ru.sergey_white.reactiveexample.model.entity.User;
import ru.sergey_white.reactiveexample.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Flux<UserDto> getAllUsers() {
        return userRepository.findAll()
                .map(userMapper::toDto);
    }

    public Mono<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .switchIfEmpty(Mono.error(new UserNotFoundException(id)));
    }

    public Mono<UserDto> createUser(UserDto dto) {
        return userRepository.findByEmail(dto.email())
                .flatMap(existing -> Mono.<User>error(new EmailAlreadyExistsException(dto.email())))
                .switchIfEmpty(Mono.just(userMapper.toEntity(dto)))
                .flatMap(userRepository::save)
                .map(userMapper::toDto);
    }

    public Mono<UserDto> updateUser(Long id, UserDto dto) {
        return userRepository.findById(id)
                .flatMap(existing -> {
                    userMapper.updateEntity(existing, dto);
                    return userRepository.save(existing);
                })
                .map(userMapper::toDto)
                .switchIfEmpty(Mono.error(new UserNotFoundException(id)));
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new UserNotFoundException(id));
                    }
                    return userRepository.deleteById(id);
                });
    }

}