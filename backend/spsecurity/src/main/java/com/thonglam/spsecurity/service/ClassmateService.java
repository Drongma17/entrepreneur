package com.thonglam.spsecurity.service;

import com.thonglam.spsecurity.dto.Classmates;
import com.thonglam.spsecurity.repository.ClassMateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassmateService {


    @Autowired
    private ClassMateRepository classMateRepository;

    public Classmates saveClassmates(Classmates classmates){
        Classmates mates = classMateRepository.save(classmates);
        return mates;
    }

    public List<Classmates> getClassmate(){
        List<Classmates> classmates =(List<Classmates>)classMateRepository.findAll();
        return classmates;
    }

}
