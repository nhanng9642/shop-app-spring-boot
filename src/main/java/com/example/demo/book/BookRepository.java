package com.example.demo.book;

import com.example.demo.test.BookTestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    Page<Book> findBooksByCategoryId(Integer categoryId, Pageable pageable);

    Optional<Book> findByBookImage(String img);

    @Query("""
        SELECT new com.example.demo.test.BookTestDTO(b.id, b.title, b.author, b.category)
        FROM book b
    """)
    public List<BookTestDTO> findAllDTO();
}
