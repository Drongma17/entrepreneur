package com.thonglam.entrepreneur.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thonglam.entrepreneur.dao.EntrepreneursDao;
import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Entrepreneurs;
import com.thonglam.entrepreneur.service.EntrepreneurService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service("entrepreneurService")
public class EntrepreneurServiceImp implements EntrepreneurService {


    @Autowired
    EntrepreneursDao entrepreneursDao;

    @Autowired
    ServletContext context;


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Entrepreneurs> getEntrepreneurs() {
        return entrepreneursDao.findAll();
    }


    @Override
    public ResponseEntity<Response> saveEntrepreneur(MultipartFile file, String entre) {
        Entrepreneurs entrepreneurs = null;
        try {
            entrepreneurs = new ObjectMapper().readValue(entre, Entrepreneurs.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isExist = new File(context.getRealPath("/entrepreneurProfile")).exists();
        if (!isExist) {
            new File(context.getRealPath("/entrepreneurProfile")).mkdir();
        }
        String fileName = file.getOriginalFilename();
        String modifiedFile = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
        File serverFileName = new File(context.getRealPath("entrepreneurProfile/" + modifiedFile));
        try {
            FileUtils.writeByteArrayToFile(serverFileName, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        entrepreneurs.setFileName(modifiedFile);
        Entrepreneurs entrepreneurDb = entrepreneursDao.save(entrepreneurs);
        if (entrepreneurDb != null) {
            return new ResponseEntity<Response>(new Response("File upload is successfull"), HttpStatus.OK);
        }
        return new ResponseEntity<Response>(new Response("File Upload is false"), HttpStatus.BAD_REQUEST);
    }




    public ResponseEntity<Entrepreneurs> updateEntrepreneur(Long id, Entrepreneurs entrepreneur) {
        Entrepreneurs entrid = entrepreneursDao.getOne(id);
        if (entrid == null) {
            return ResponseEntity.notFound().build();
        }
        entrid.setFirstName(entrepreneur.getFirstName());
        entrid.setLastName(entrepreneur.getLastName());
        entrid.setEmail(entrepreneur.getEmail());
        entrid.setPhoneNumber(entrepreneur.getPhoneNumber());
        entrid.setGender(entrepreneur.getGender());
        entrid.setSeedfund(entrepreneur.getSeedfund());
        entrid.setFileName(entrepreneur.getFileName());
        entrid.setBusinessmodel(entrepreneur.getBusinessmodel());
        entrid.setAbout(entrepreneur.getAbout());
        entrid.setAddress(entrepreneur.getAddress());
        entrid.setUpdateDate(entrepreneur.getUpdateDate());
        entrid.setUpdateDate(entrepreneur.getUpdateDate());
        Entrepreneurs ent = entrepreneursDao.save(entrid);
        return ResponseEntity.ok().body(ent);

    }


    @Override
    public ResponseEntity<Entrepreneurs> getEntrepreneur(Long id) {
        Entrepreneurs singleEntrepreneur = entrepreneursDao.getOne(id);
        if (singleEntrepreneur != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(singleEntrepreneur);
    }



    @Override
    public ResponseEntity<Entrepreneurs> deleteEntrepreneur(Long id) {
        Entrepreneurs emp = entrepreneursDao.getOne(id);
        if (emp == null) {
            ResponseEntity.notFound().build();
        }
        entrepreneursDao.deleteById(id);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<List<String>> getImage(){
        List<String> images = new ArrayList<>();
        String filesPath=context.getRealPath("/entrepreneurProfile/");
        File filefolder = new File(filesPath);
        if(filefolder!=null){
            for(final File file : filefolder.listFiles()){
                if(!file.isDirectory()){
                    String encodeBase64=null;
                    try{
                        String extension = FilenameUtils.getExtension(file.getName());
                        FileInputStream fileInputStream =new FileInputStream(file);
                        byte[] bytes =new byte[(int)file.length()];
                        fileInputStream.read(bytes);
                        encodeBase64 =Base64.getEncoder().encodeToString(bytes);
                        images.add("data:image/"+extension+";base64,"+encodeBase64);
                        fileInputStream.close();
                    }catch (Exception e){
                    }
                }
            }
        }
        return new ResponseEntity<List<String>>(images, HttpStatus.OK);
    }
}