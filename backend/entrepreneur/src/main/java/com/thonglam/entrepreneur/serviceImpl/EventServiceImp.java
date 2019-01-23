package com.thonglam.entrepreneur.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thonglam.entrepreneur.dao.EventRepository;

import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Event;
import com.thonglam.entrepreneur.service.EventService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Date;
import java.util.List;

@Service("eventService")
public class EventServiceImp implements EventService {


    @Autowired
    private EventRepository eventRepository;

    @Autowired
    ServletContext context;

    @Override
    public ResponseEntity<Response> saveGuestProfile(MultipartFile file, String event) {
        Event eventId =null;
        try {
           eventId = new ObjectMapper().readValue(event, Event.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        eventId.setCreateDate(new Date());

        boolean exists =new File(context.getRealPath("/eventProfile")).exists();
        if(!exists){
            new File(context.getRealPath("/eventProfile")).mkdir();
        }

        String fileName =file.getOriginalFilename();
        String modifiedFileName =FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(fileName);
        File serverFile = new File(context.getRealPath("/eventProfile/" + modifiedFileName));
        try {
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }

        eventId.setFileName(modifiedFileName);
        Event dbFile =eventRepository.save(eventId);
        if(dbFile !=null){
            return new  ResponseEntity<>(new Response("File upload  Successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response("File can not upload successfully "), HttpStatus.BAD_REQUEST);
    }



    @Override
    public List<Event> getAllEvents() {
      return eventRepository.findAll();
    }

    @Override
    public ResponseEntity<Event> getSingleEvent(Long id) {
        Event event = eventRepository.getOne(id);
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Event> updateEvent(Long id, Event event) {
        Event event1 = eventRepository.getOne(id);
        if (event1 == null) {
            return ResponseEntity.notFound().build();
        }
        event1.setFileName(event.getFileName());
        event1.setChiefGuest(event.getChiefGuest());
        event1.setEventVanue(event.getEventVanue());
        event1.setEventDate(event.getEventDate());
        Event ent =eventRepository.save(event1);
        return ResponseEntity.ok().body(ent);

    }

    @Override
    public ResponseEntity<Response> deleteEvent(Long id) {
        Event deleteId = eventRepository.getOne(id);
        if(deleteId==null){
            throw  new NullPointerException("Event is not present ");
        }
        eventRepository.deleteById(id);
        return new ResponseEntity<Response>(new Response("Event is delete successfully"), HttpStatus.OK);
    }
}
