package com.techsu.repository;

import com.techsu.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select b.* from book b,author a where b.author_id=a.id and a.name= ?1", nativeQuery = true)
    List<Book> searchBookByAuthor(String authorName);

    @Query(value = "select b.* from book b,category c where b.category_id=c.id and c.name= ?1", nativeQuery = true)
    List<Book> searchBookByCategory(String categoryName);
}
