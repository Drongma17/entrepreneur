package com.thonglam.entrepreneur.dao;

import com.thonglam.entrepreneur.dto.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentDao extends JpaRepository<Investment, Long> {

}
