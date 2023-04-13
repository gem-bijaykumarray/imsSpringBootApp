package com.gemini.mis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.mis.entity.items.tables.pojos.Language;
import com.gemini.mis.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
public class LanguageController {

    private static final Logger LOG = LoggerFactory.getLogger(LanguageController.class);

    @Autowired
    LanguageService objectService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping({"/Language/{Id}"})
        public ResponseEntity<com.gemini.mis.model.Language> getLanguage(@PathVariable Integer Id) {
            com.gemini.mis.model.Language result=objectMapper.convertValue(objectService.getLanguage(Id), com.gemini.mis.model.Language.class);
            com.gemini.mis.model.Language dummyBody = null;
                if(result!=null){
                    LOG.info("Data fetched successfully");
                    return new ResponseEntity<>(result, HttpStatus.OK);
                }
                else{
                     LOG.info("Data not found : {}",Id);
                     return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
                }
    }

    @PutMapping({"/Language/{Id}"})
    public ResponseEntity<Language> updateLanguage(@Valid @RequestBody Language body, @PathVariable Integer Id){
       Language dummyBody = null;
       body.setId(Id);
       int result=objectService.updateLanguage(body);
       if(result == 1){
                LOG.info("Updated successfully");
                return new ResponseEntity<>(body, HttpStatus.OK);
           }
           else{
                LOG.info("Data to be updated does not exist : {} ", body.getId());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }

    @PatchMapping({"/Language/{Id}"})
    public ResponseEntity<Language> patchUpdateLanguage(@Valid @RequestBody Language body, @PathVariable Integer Id){
       Language dummyBody = null;
       body.setId(Id);
       Language result=objectService.patchUpdateLanguage(body);
       if(result!=null){
                LOG.info("Updated successfully");
                return new ResponseEntity<>(result, HttpStatus.OK);
           }
           else{
                LOG.info("Data to be updated does not exist : {} ", body.getId());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }


    @PostMapping({"/Language"})
    public ResponseEntity<Language> addLanguage(@Valid @RequestBody Language body) {
        LOG.info("Record inserted successfully");
        return new ResponseEntity<>(objectService.addLanguage(body), HttpStatus.CREATED);
    }

    @DeleteMapping({"/Language/{Id}"})
    public ResponseEntity deleteLanguage(@PathVariable Integer Id) {
        int result=objectService.deleteLanguage(Id);
        if(result==1){
            LOG.info("Deleted successfully : {} ", Id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        }
        else{
            LOG.info("Data to be deleted does not exist : {} ", Id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data to be deleted does not exist");
        }
    }

}

