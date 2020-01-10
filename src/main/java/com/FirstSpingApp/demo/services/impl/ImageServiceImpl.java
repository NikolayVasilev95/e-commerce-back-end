package com.FirstSpingApp.demo.services.impl;

import com.FirstSpingApp.demo.domain.Image;
import com.FirstSpingApp.demo.exceptionhandling.exception.ResourceNotFoundException;
import com.FirstSpingApp.demo.repositories.ImageRepository;
import com.FirstSpingApp.demo.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

  @Autowired private ImageRepository imageRepository;

  @Override
  public List<Image> getAllImages() {
    return imageRepository.findAll();
  }

  @Override
  public Image getImage(long id) {
    return imageRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Image not found"));
  }

  @Override
  public Image create(Image image) {
    return imageRepository.save(image);
  }

  @Override
  public Image update(Image image) {
    return imageRepository.save(image);
  }

  @Override
  public void delete(Long id) {
    getImage(id);
    imageRepository.deleteById(id);
  }
}
