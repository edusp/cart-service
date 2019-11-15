package com.bt.au.shoppingcart.repositories;

import com.bt.au.shoppingcart.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageUploadRepository extends JpaRepository<Image, Long> {
}
