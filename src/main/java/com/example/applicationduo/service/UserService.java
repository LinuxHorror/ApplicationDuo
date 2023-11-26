package com.example.applicationduo.service;

import com.example.applicationduo.dto.UserCreationDto;
import com.example.applicationduo.entity.UserEntity;
import com.example.applicationduo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor

@Repository
public class UserService {
    private final UserRepository repository;

    public void save(UserEntity user) {
        repository.save(user);
    }

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public Optional<UserEntity> getById(UUID id) {
        return Optional.of(repository.getReferenceById(id));
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public boolean isExistsInDb(UserCreationDto dto) {
        return repository
                .findByEmailAndPassword(dto.getEmail(), dto.getPassword())
                .isPresent();
    }
    public Optional<UserEntity> findByNameAndEmail(UserCreationDto dto){
        return repository.findByUsernameAndEmail(dto.getUsername(), dto.getEmail());
    }

}
