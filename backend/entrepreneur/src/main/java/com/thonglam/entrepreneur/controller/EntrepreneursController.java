package com.thonglam.entrepreneur.controller;



import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Entrepreneurs;
import com.thonglam.entrepreneur.service.EntrepreneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/entrepreneurship")
public class EntrepreneursController {


    @Autowired
    private EntrepreneurService entrepreneurService;


    @PostMapping("/saveEntrepreneurProfile")
    ResponseEntity<Response> saveEntrepreneurProfile(@RequestParam("file") MultipartFile file, @RequestParam("entrepre") String entrepre) {
       return entrepreneurService.saveEntrepreneur(file, entrepre);
    }


    @GetMapping("/entrepreneurs")
    public List<Entrepreneurs> findAll() {
        return entrepreneurService.getEntrepreneurs();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Entrepreneurs> updateEntrepreneur(@PathVariable(value = "id") Long id, @RequestBody Entrepreneurs entrepreneur) {
     return entrepreneurService.updateEntrepreneur(id, entrepreneur);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Entrepreneurs> deleteEntrepreneur(@PathVariable(value = "id") Long Id) {
        return entrepreneurService.deleteEntrepreneur(Id);
    }


    @GetMapping("/getEntrepreneur/{id}")
    public ResponseEntity<Entrepreneurs> getEntrepreneur(@PathVariable Long id) {
       return entrepreneurService.getEntrepreneur(id);
    }



    @GetMapping("/getImage")
    public ResponseEntity<List<String>> getImage() {
        return entrepreneurService.getImage();
    }
}



