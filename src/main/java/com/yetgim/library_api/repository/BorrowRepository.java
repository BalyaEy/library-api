package com.yetgim.library_api.repository;

import com.yetgim.library_api.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByUserId(Long userId);
}
