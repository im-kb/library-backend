package com.demo.springboot.repository;

import com.demo.springboot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByBookId(Long bookId);

    @Query(
            value = "select availability from library.book where book_id=:bookId", nativeQuery = true
    )
    Boolean checkAvailabilityByBookid(
            @Param("bookId") Long bookId
    );

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE library.book set availability=false where book_id= :bookId", nativeQuery = true
    )
    void changeAvailability(
            @Param("bookId") Long bookId
    );

    List<Book> deleteBookByBookId(@Param("bookId") Long bookId);
}
