package com.FirstSpingApp.demo.controllers;

import com.FirstSpingApp.demo.domain.Image;
import com.FirstSpingApp.demo.resources.ImageDto;
import com.FirstSpingApp.demo.services.ImageService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
public class ImageController {

  @Autowired ImageService imageService;

  /** USER API */
  @GetMapping("/api/user/images")
  public List<Image> showImagesForUser() {
    return imageService.getAllImages();
  }

  /** ADMIN API */
  @GetMapping("/api/admin/images")
  public List<Image> showImagesForAdmin() {
    return imageService.getAllImages();
  }

  @PostMapping(value = "/api/admin/image/add", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ImageDto> addImage(@RequestBody @Valid ImageDto imageDto) {
    Image result = imageService.create(ImageDto.toEntity(imageDto));
    return new ResponseEntity<ImageDto>(new ImageDto(result), HttpStatus.OK);
  }

  @DeleteMapping(value = "/api/admin/Image/delete")
  public ResponseEntity deleteImage(
      @RequestParam(value = "id", required = true) @Positive Long id) {
    imageService.delete(id);
    Gson gson = new Gson();
    return ResponseEntity.ok(gson.toJson("Image successfully delete!"));
  }
}
