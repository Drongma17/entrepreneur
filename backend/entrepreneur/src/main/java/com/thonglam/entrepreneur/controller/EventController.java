package com.thonglam.entrepreneur.controller;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Event;
import com.thonglam.entrepreneur.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(name = "/entrepreneurProgram")
public class EventController {


    @Autowired
    ServletContext context;


    @Autowired
    private EventService eventService;


    @PostMapping("/saveEventProfile")
    public ResponseEntity<Response> saveGuestProfile(@RequestParam("file") MultipartFile file, @RequestParam("event") String event) throws JsonMappingException, JsonParseException, IOException {
       return eventService.saveGuestProfile(file, event);
    }



   @GetMapping("/getAllEvents")
    public List<Event> getAllEvents(){
      return eventService.getAllEvents();
   }


     @GetMapping("/getOneEvent/{id}")
    public ResponseEntity<Event> getSingleEvent(@PathVariable("id") Long id){
       return eventService.getSingleEvent(id);
     }


    @PutMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable(value = "id") Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

     @DeleteMapping("/deleteEvent/{id}")
     public ResponseEntity<Response> deleteEvent(@PathVariable("id") Long id){
       return eventService.deleteEvent(id);
     }



}
