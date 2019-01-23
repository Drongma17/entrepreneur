package com.thonglam.entrepreneur.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Incubation;
import com.thonglam.entrepreneur.service.IncubationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/incubation")
public class IncubationController {


    @Autowired
    ServletContext context;

    @Autowired
    IncubationService incubationService;



    @PostMapping("/saveIncubation")
    ResponseEntity<Response> saveIncubationParticipant(@RequestParam("file") MultipartFile file, @RequestParam("incubation") String incubationReciever) throws JsonParseException, JsonMappingException, IOException {
       return incubationService.saveIncubationParticipant(file, incubationReciever);
    }


    @GetMapping("/getAllIncubation")
    public List<Incubation> getAllIncubation(){
         return incubationService.getAllIncubation();
    }


    @PutMapping("/updateIncubation/{id}")
    public ResponseEntity<Incubation> updateIncubator(@PathVariable(value ="id") Long id, @RequestBody Incubation incubation){
        return incubationService.updateIncubator(id, incubation);
    }



   @DeleteMapping("/deleteIncubator/{id}")
    public ResponseEntity<Incubation> deleteIncubator(@PathVariable(value = "id") Long id){
      return incubationService.deleteIncubator(id);
   }

}
