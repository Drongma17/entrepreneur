package com.thonglam.entrepreneur.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thonglam.entrepreneur.dao.IncubationRepository;
import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Incubation;
import com.thonglam.entrepreneur.service.IncubationService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;


@Service("incubationService")
public class IncubationServiceImpl implements IncubationService {

    @Autowired
    ServletContext context;

    @Autowired
    IncubationRepository incubationRepository;

    @Override
    public ResponseEntity<Response> saveIncubationParticipant(MultipartFile file, String incubationReciever) {
        Incubation incubation=null;
        try {
            incubation  = new ObjectMapper().readValue(incubationReciever, Incubation.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean isExist = new File(context.getRealPath("/incubationProfile")).exists();
        if (!isExist) {
            new File(context.getRealPath("/incubationProfile")).mkdir();
        }
        String fileName = file.getOriginalFilename();
        String modifiedFile = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
        File serverFileName = new File(context.getRealPath("incubationProfile/" + modifiedFile));
        try {
            FileUtils.writeByteArrayToFile(serverFileName, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        incubation.setFileName(modifiedFile);
        Incubation incubationDb = incubationRepository.save(incubation);
        if (incubationDb != null) {
            return new ResponseEntity<Response>(new Response("File upload is successfull"), HttpStatus.OK);
        }
        return new ResponseEntity<Response>(new Response("File Upload is false"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<Incubation> getAllIncubation() {
        return incubationRepository.findAll();
    }

    @Override
    public ResponseEntity<Incubation> updateIncubator(Long id, Incubation incubation) {
        Incubation incubationId= incubationRepository.getOne(id);
        if(incubationId ==null){
            return ResponseEntity.notFound().build();
        }
        incubationId.setName(incubation.getName());
        incubationId.setEmail(incubation.getEmail());
        incubationId.setSeedfund(incubation.getSeedfund());
        incubationId.setBusinessmodel(incubation.getBusinessmodel());
        incubationId.setFileName(incubation.getFileName());
        incubationId.setAbout(incubation.getAbout());
        incubationId.setAddress(incubation.getAddress());
        incubationId.setUpdateDate(incubation.getUpdateDate());

        Incubation updateDb =incubationRepository.save(incubationId);
        return ResponseEntity.ok().body(updateDb);
    }



    @Override
    public ResponseEntity<Incubation> deleteIncubator(Long id) {
        Incubation deleteId = incubationRepository.getOne(id);
        if(deleteId == null){
            ResponseEntity.notFound().build();
        }
        incubationRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
