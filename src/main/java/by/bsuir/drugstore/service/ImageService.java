package by.bsuir.drugstore.service;

import by.bsuir.drugstore.model.Image;
import by.bsuir.drugstore.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }
}
