package com.yetgim.library_api.repository;

import com.yetgim.library_api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
