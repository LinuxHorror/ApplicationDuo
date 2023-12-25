package com.example.applicationduo.service;

import com.example.applicationduo.dto.UserCreationDto;
import com.example.applicationduo.entity.UserEntity;
import com.example.applicationduo.mappers.UserMapper;
import com.example.applicationduo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Repository
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElse(null);
    }

    @Transactional
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
                .findByUsername(dto.getUsername())
                .isEmpty();
    }
    public Optional<UserEntity> findByPasswordAndEmail(UserCreationDto dto){
        return repository.findByPasswordAndUsername(dto.getPassword(), dto.getUsername());
    }

}
