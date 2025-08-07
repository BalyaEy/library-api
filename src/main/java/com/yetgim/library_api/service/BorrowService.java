package com.yetgim.library_api.service;

import com.yetgim.library_api.entity.Book;
import com.yetgim.library_api.entity.Borrow;
import com.yetgim.library_api.entity.User;
import com.yetgim.library_api.repository.BookRepository;
import com.yetgim.library_api.repository.BorrowRepository;
import com.yetgim.library_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public Borrow borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getStock() <= 0) {
            throw new RuntimeException("Book is out of stock");
        }

        book.setStock(book.getStock() - 1);
        bookRepository.save(book);

        Borrow borrow = Borrow.builder()
                .user(user)
                .book(book)
                .borrowedAt(LocalDateTime.now())
                .build();

        return borrowRepository.save(borrow);
    }

    public Borrow returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (borrow.getReturnedAt() != null) {
            throw new RuntimeException("Book already returned");
        }

        borrow.setReturnedAt(LocalDateTime.now());

        Book book = borrow.getBook();
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);

        return borrowRepository.save(borrow);
    }

    public List<Borrow> getBorrowsByUserId(Long userId) {
        return borrowRepository.findByUserId(userId);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }
}
