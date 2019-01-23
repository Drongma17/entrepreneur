package com.thonglam.entrepreneur.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thonglam.entrepreneur.dao.InvestmentDao;
import com.thonglam.entrepreneur.domain.Response;
import com.thonglam.entrepreneur.dto.Investment;
import com.thonglam.entrepreneur.service.InvestmentService;
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


@Service("investmentService")
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    ServletContext context;

   @Autowired
    InvestmentDao investmentDao;



    @Override
    public ResponseEntity<Response> saveInvestment(MultipartFile file, String investmentreciever) {
        Investment investment =null;
        try {
            investment =  new ObjectMapper().readValue(investmentreciever, Investment.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean isExist = new File(context.getRealPath("/investmentProfile")).exists();
        if (!isExist) {
            new File(context.getRealPath("/investmentProfile")).mkdir();
        }
        String fileName = file.getOriginalFilename();
        String modifiedFile = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
        File serverFileName = new File(context.getRealPath("investmentProfile/" + modifiedFile));
        try {
            FileUtils.writeByteArrayToFile(serverFileName, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        investment.setFileName(modifiedFile);
        Investment incubationDb = investmentDao.save(investment);
        if (incubationDb != null) {
            return new ResponseEntity<Response>(new Response("File upload is successfull"), HttpStatus.OK);
        }
        return new ResponseEntity<Response>(new Response("File Upload is false"), HttpStatus.BAD_REQUEST);
    }




    @Override
    public List<Investment> getAllInvestment() {
        return investmentDao.findAll();
    }

    @Override
    public Investment getInvestment(Long id) {
        return investmentDao.getOne(id);
    }

    @Override
    public ResponseEntity<Investment> updateInvestment(Long id, Investment investment) {
        Investment investment1 = investmentDao.getOne(id);
        if(investment1 ==null){
            return ResponseEntity.notFound().build();
        }
        investment1.setName(investment.getName());
        investment1.setEmail(investment.getEmail());
        investment1.setSeedfund(investment.getSeedfund());
        investment1.setBusinessmodel(investment.getBusinessmodel());
        investment1.setFileName(investment.getFileName());
        investment1.setAbout(investment.getAbout());
        investment1.setAddress(investment.getAddress());
        investment1.setUpdateDate(investment.getUpdateDate());

        Investment updateDb = investmentDao.save(investment1);
        return ResponseEntity.ok().body(updateDb);
    }



    @Override
    public ResponseEntity<Investment> deleteInvestment(Long id) {
        Investment deleteId = investmentDao.getOne(id);
        if(deleteId == null){
            ResponseEntity.notFound().build();
        }
        investmentDao.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
