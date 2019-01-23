package com.thonglam.entrepreneur.dao;

import com.thonglam.entrepreneur.dto.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
