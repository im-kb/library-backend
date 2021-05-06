package com.demo.springboot.repository;

import com.demo.springboot.model.Book;
import com.demo.springboot.model.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {
  //  Optional <PublishingHouse> addPublishingHouse(PublishingHouse publishingHouse);
}
