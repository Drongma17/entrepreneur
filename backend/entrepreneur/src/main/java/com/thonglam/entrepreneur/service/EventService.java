package com.thonglam.entrepreneur.service;

import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    public ResponseEntity<Response> saveGuestProfile( MultipartFile file,  String event);
    public List<Event> getAllEvents();
    public ResponseEntity<Event> getSingleEvent(Long id);
    public ResponseEntity<Event> updateEvent( Long id, @RequestBody Event event);
    public ResponseEntity<Response> deleteEvent(Long id);
}
