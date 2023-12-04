package com.example.applicationduo.service;

import com.example.applicationduo.dto.UserCreationDto;
import com.example.applicationduo.entity.UserEntity;
import com.example.applicationduo.mappers.UserMapper;
import com.example.applicationduo.repositories.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Repository
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public void save(UserEntity user) {
        repository.save(user);
    }

    public List<UserCreationDto> findAll() {
        return mapper.toListDto(repository.findAll());
    }

    public Optional<UserEntity> getById(UUID id) {
        return Optional.of(repository.getReferenceById(id));
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public boolean isExistsInDb(UserCreationDto dto) {
        return repository
                .findByPasswordAndEmail(dto.getPassword(), dto.getEmail())
                .isEmpty();
    }
    public Optional<UserEntity> findByPasswordAndEmail(UserCreationDto dto){
        return repository.findByPasswordAndEmail(dto.getPassword(), dto.getEmail());
    }

}
