package com.FirstSpingApp.demo.services;

import com.FirstSpingApp.demo.domain.Image;

import java.util.List;

public interface ImageService {

  List<Image> getAllImages();

  Image getImage(long id);

  Image create(Image image);

  Image update(Image image);

  void delete(Long id);
}
