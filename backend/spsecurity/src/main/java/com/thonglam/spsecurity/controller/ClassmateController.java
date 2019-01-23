package com.thonglam.spsecurity.controller;


import com.thonglam.spsecurity.dto.Classmates;
import com.thonglam.spsecurity.service.ClassmateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/classmates")
public class ClassmateController {


    @Autowired
    ClassmateService classmateService;


    @PostMapping("/save")
    public ResponseEntity<Classmates> classmateSave(@RequestBody  Classmates classmates) throws Exception{
        Classmates classmates1 = classmateService.saveClassmates(classmates);
        return new ResponseEntity<Classmates>(classmates1,  HttpStatus.OK);
    }


    @GetMapping("/get/classmates")
    public List<Classmates> getClassmates(){
        List<Classmates> cl = classmateService.getClassmate();
        return cl;
    }

}
