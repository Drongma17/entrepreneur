package com.thonglam.entrepreneur.service;

import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Investment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



public interface InvestmentService {


    ResponseEntity<Response> saveInvestment(MultipartFile file, String investmentreciever);
    public List<Investment> getAllInvestment();
    public Investment getInvestment(Long id);
    public ResponseEntity<Investment> updateInvestment(Long id, Investment investment);
    public ResponseEntity<Investment> deleteInvestment(Long id);

}
