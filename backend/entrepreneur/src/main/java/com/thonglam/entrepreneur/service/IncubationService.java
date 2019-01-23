package com.thonglam.entrepreneur.service;

import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Incubation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


public interface IncubationService {


    ResponseEntity<Response> saveIncubationParticipant( MultipartFile file, String incubationReciever);
    public List<Incubation> getAllIncubation();
    public ResponseEntity<Incubation> updateIncubator( Long id,Incubation incubation);
    public ResponseEntity<Incubation> deleteIncubator( Long id);

}
