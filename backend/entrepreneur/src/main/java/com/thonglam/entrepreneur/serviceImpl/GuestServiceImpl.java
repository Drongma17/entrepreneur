package com.thonglam.entrepreneur.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thonglam.entrepreneur.dao.GuestRepository;
import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Guest;
import com.thonglam.entrepreneur.service.GuestService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Date;
import java.util.List;

@Service("guestService")
public class GuestServiceImpl implements GuestService {


    @Autowired
    ServletContext context;

    @Autowired
    GuestRepository guestRepository;

private static Logger LOGGER =LoggerFactory.getLogger(GuestServiceImpl.class);

    @Override
     public ResponseEntity<List<Guest>> getAllPerson() {
       return new ResponseEntity<List<Guest>>(guestRepository.findAll(), HttpStatus.OK);
    }

   @Override
    public ResponseEntity<Guest> save(Guest guest){
       if (guest == null) {
           throw new NullPointerException("person object can not be null");
       }
       guest.setCreateDate(new Date());
       Guest person1 = guestRepository.save(guest);
       return new ResponseEntity<Guest>(person1, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Guest> getPerson(Long id) {
        if (id == null) {
            throw new NullPointerException("person object can not save");
        }
        Guest persondb = guestRepository.getOne(id);
        return new ResponseEntity<Guest>(persondb, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Guest> updateGuest(Long id, Guest person) {
        Guest person1 = guestRepository.getOne(id);
        if (person1 == null) {
            return ResponseEntity.notFound().build();
        }
        person1.setUpdateDate(new Date());
        person1.setPhoneNumber(person.getPhoneNumber());
        person1.setFileName(person.getFirstName());
        Guest dbperson = guestRepository.save(person1);
        return new ResponseEntity<Guest>(dbperson, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> deleteGuest(Long id) {
        if (id == null) {
            throw new NullPointerException("person object can not save");
        }
        guestRepository.deleteById(id);
        return new ResponseEntity<Response>(new Response("person is deleted "), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> saveGuestProfile(MultipartFile file, String user) {
        Guest guest=null;
        try {
            guest= new ObjectMapper().readValue(user, Guest.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        guest.setCreateDate(new Date());

        boolean isExist = new File(context.getRealPath("/userProfile")).exists();
        if (!isExist) {
            new File(context.getRealPath("/userProfile")).mkdir();
        }
        String fileName = file.getOriginalFilename();
        String modifiedName = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
        File serverFile = new File(context.getRealPath("/userProfile/" + modifiedName));
        //+File.pathSeparator
        try {
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        guest.setFileName(modifiedName);
        Guest dbPerson = guestRepository.save(guest);

        if (dbPerson != null) {
            return new ResponseEntity<>(new Response("File upload is successfull"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response("File Upload is false"), HttpStatus.BAD_REQUEST);
    }



}
