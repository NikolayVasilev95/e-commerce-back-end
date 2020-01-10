package com.FirstSpingApp.demo.repositories;

import com.FirstSpingApp.demo.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {}
