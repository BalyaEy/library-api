package com.yetgim.library_api.controller;

import com.yetgim.library_api.entity.Borrow;
import com.yetgim.library_api.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping("/{bookId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Borrow> borrowBook(@PathVariable Long bookId, Principal principal) {
        Long userId = Long.valueOf(principal.getName());
        return ResponseEntity.ok(borrowService.borrowBook(userId, bookId));
    }

    @PutMapping("/return/{borrowId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Borrow> returnBook(@PathVariable Long borrowId) {
        return ResponseEntity.ok(borrowService.returnBook(borrowId));
    }

    @GetMapping("/my-borrows")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Borrow>> getUserBorrows(Principal principal) {
        Long userId = Long.valueOf(principal.getName());
        return ResponseEntity.ok(borrowService.getBorrowsByUserId(userId));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Borrow>> getAllBorrows() {
        return ResponseEntity.ok(borrowService.getAllBorrows());
    }
}
