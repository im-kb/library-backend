package com.demo.springboot.repository;

import com.demo.springboot.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query(
            value = "select * from library.rental natural join library.book where" +
                    " client_id=(SELECT client_id from library.client where login = :login and password=:password)",nativeQuery= true

    )
    List<Rental> getRentalByClientLoginAndPassword(
            @Param("login") String login,
            @Param("password") String password
    );

}
