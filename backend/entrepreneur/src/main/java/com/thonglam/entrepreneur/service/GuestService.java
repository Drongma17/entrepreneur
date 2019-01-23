package com.thonglam.entrepreneur.service;


import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Guest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GuestService {

    public ResponseEntity<Guest> save(Guest guest);
    public ResponseEntity<List<Guest>> getAllPerson() ;
    public ResponseEntity<Guest> getPerson(Long id);
    public ResponseEntity<Guest> updateGuest(Long id,Guest person);
    public ResponseEntity<Response> deleteGuest(Long id) ;
    public ResponseEntity<Response> saveGuestProfile( MultipartFile file,  String user) ;

}
