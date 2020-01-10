package com.FirstSpingApp.demo.resources;

import com.FirstSpingApp.demo.domain.Image;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ImageDto {

  private Long id;
  private String publicPath;
  private List<ProductDto> products;

  public ImageDto() {}

  public ImageDto(Image image) {
    Objects.requireNonNull(image, "Image must be not null");
    this.id = image.getId();
    this.publicPath = image.getPublicPath();
    this.products = image.getProducts().stream().map(ProductDto::new).collect(Collectors.toList());
  }

  public static Image toEntity(ImageDto dto) {
    Image image = new Image();
    image.setId(dto.getId());
    image.setPublicPath(dto.getPublicPath());
    if (dto.getProducts() != null) {
      image.setProducts(
          dto.getProducts().stream().map(ProductDto::toEntity).collect(Collectors.toList()));
    }
    return image;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPublicPath() {
    return publicPath;
  }

  public void setPublicPath(String publicPath) {
    this.publicPath = publicPath;
  }

  public List<ProductDto> getProducts() {
    return products;
  }

  public void setProducts(List<ProductDto> products) {
    this.products = products;
  }
}
