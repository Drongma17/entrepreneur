package com.thonglam.entrepreneur.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Guest;
import com.thonglam.entrepreneur.service.FileStorage;
import com.thonglam.entrepreneur.service.GuestService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import java.util.List;

@RestController
public class GuestController {

    @Autowired
    ServletContext context;

    @Autowired
    FileStorage fileStorage;


    @Autowired
    private GuestService guestService;

    @GetMapping(value = "/test/{personName}")
    public ResponseEntity<Response> test(@PathVariable("personName") String personName) {
        return new ResponseEntity<Response>(new Response("Hi " + personName + ",   welcome to spring boot application "), HttpStatus.OK);
    }


    @GetMapping(value = "/persons")
    public ResponseEntity<List<Guest>> getAllPerson() {
        return guestService.getAllPerson();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Guest> save(@RequestBody Guest person) {
       return guestService.save(person);
    }


    @GetMapping(value = "/getPerson/{id}")
    public ResponseEntity<Guest> getPerson(@PathVariable("id") Long id) {
       return guestService.getPerson(id);
    }


    @PostMapping(value = "/update/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable(value = "id")Long id, @RequestBody Guest guest) {
    return guestService.updateGuest(id, guest);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        return guestService.deleteGuest(id);
    }





@PostMapping(value = "/saveUserProfileInServer")
    public ResponseEntity<Response> saveUserProfileInServer(@RequestParam("file") MultipartFile file, @RequestParam("user") String user)  {
       return guestService.saveGuestProfile(file, user);
    }

}
