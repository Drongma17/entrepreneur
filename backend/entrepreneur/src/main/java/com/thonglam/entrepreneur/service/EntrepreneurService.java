package com.thonglam.entrepreneur.service;

import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Entrepreneurs;
import com.thonglam.entrepreneur.dto.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EntrepreneurService {



    public List<Entrepreneurs> getEntrepreneurs();


    public ResponseEntity<Response> saveEntrepreneur(MultipartFile file, String entre);

    public ResponseEntity<Entrepreneurs> updateEntrepreneur(Long id, Entrepreneurs entrepreneurs);

    public ResponseEntity<Entrepreneurs> getEntrepreneur(Long id);


    public ResponseEntity<Entrepreneurs> deleteEntrepreneur(Long id);


    public ResponseEntity<List<String>> getImage();
}
