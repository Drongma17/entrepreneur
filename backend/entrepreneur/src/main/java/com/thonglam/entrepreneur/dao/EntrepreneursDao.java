package com.thonglam.entrepreneur.dao;

import com.thonglam.entrepreneur.dto.Entrepreneurs;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EntrepreneursDao extends JpaRepository<Entrepreneurs, Long> {
    
}
