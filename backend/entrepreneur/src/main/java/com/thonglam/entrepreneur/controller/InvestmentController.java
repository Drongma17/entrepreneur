package com.thonglam.entrepreneur.controller;


import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Investment;
import com.thonglam.entrepreneur.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.util.List;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    @Autowired
    ServletContext context;


    @Autowired
   private  InvestmentService investmentService;


    @PostMapping("/saveInvestmentProfile")
    ResponseEntity<Response> saveInvestmentProfile(@RequestParam("file") MultipartFile file, @RequestParam("investm") String investmentreciever) {
       return investmentService.saveInvestment(file, investmentreciever);
    }



    @GetMapping("/getAllInvestment")
    public List<Investment> getAllInvestment(){
        return investmentService.getAllInvestment();
    }


    @GetMapping("/getInvestment/{id}")
    public Investment getInvestment(@PathVariable("id") Long id){
        return investmentService.getInvestment(id);
    }


    @PutMapping("/updateInvestment/{id}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable("id") Long id, @RequestBody Investment investment){
       return investmentService.updateInvestment(id, investment);
    }



    @DeleteMapping("/deleteInvestment/{id}")
    public ResponseEntity<Investment> deleteInvestment(@PathVariable(value = "id") Long id){
       return investmentService.deleteInvestment(id);
    }


}

