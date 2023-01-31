package by.bsuir.drugstore.controller;

import by.bsuir.drugstore.model.Image;
import by.bsuir.drugstore.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.Optional;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> findImageById(@PathVariable("id") Long id) {
        Optional<Image> optionalImage = imageService.findById(id);
        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
                    .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
