package com.example.demo.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    public Page<Book> findBooksByCategoryId(Integer categoryId, Pageable pageable);

    Optional<Book> findByBookImage(String img);
}
