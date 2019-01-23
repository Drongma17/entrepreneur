package com.thonglam.entrepreneur.dao;

import com.thonglam.entrepreneur.dto.Incubation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubationRepository extends JpaRepository<Incubation, Long> {
}
