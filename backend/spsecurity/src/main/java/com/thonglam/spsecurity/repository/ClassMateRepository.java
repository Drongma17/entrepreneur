package com.thonglam.spsecurity.repository;

import com.thonglam.spsecurity.dto.Classmates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ClassMateRepository extends CrudRepository<Classmates, UUID> {
}
