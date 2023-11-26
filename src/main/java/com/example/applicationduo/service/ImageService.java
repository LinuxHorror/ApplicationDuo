package com.example.applicationduo.service;

import com.example.applicationduo.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository repository;

    @Transactional
    public void uploadImage(MultipartFile file) throws IOException {

    }

    @Transactional
    public byte[] getImage(String name) {
        return new byte[0];
    }
}
