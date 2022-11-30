package com.gemini.ims.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.ims.entity.items.tables.pojos.Language;
import com.gemini.ims.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
public class LanguageController {

    private static final Logger log = LoggerFactory.getLogger(LanguageController.class);

    @Autowired
    LanguageService objectService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping({"/Language/{Id}/{Cd}"})
        public ResponseEntity<com.gemini.ims.model.Language> getLanguage(@PathVariable Integer Id,@PathVariable String Cd) {
            com.gemini.ims.model.Language result=objectMapper.convertValue(objectService.getLanguage(Id,Cd), com.gemini.ims.model.Language.class);
            com.gemini.ims.model.Language dummyBody = null;
                if(result!=null){
                    log.info("Data fetched successfully");
                    return new ResponseEntity<>(result, HttpStatus.OK);
                }
                else{
                     log.info("Data not found : {}",Id,Cd);
                     return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
                }
    }

    @PutMapping({"/Language/{Id}/{Cd}"})
    public ResponseEntity<Language> updateLanguage(@Valid @RequestBody Language body, @PathVariable Integer Id,@PathVariable String Cd){
       Language dummyBody = null;
       body.setId(Id);body.setCd(Cd);
       int result=objectService.updateLanguage(body);
       if(result == 1){
                log.info("Updated successfully");
                return new ResponseEntity<>(body, HttpStatus.OK);
           }
           else{
                log.info("Data to be updated does not exist : {}, {} ", body.getId(),body.getCd());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }

    @PatchMapping({"/Language/{Id}/{Cd}"})
    public ResponseEntity<Language> patchUpdateLanguage(@Valid @RequestBody Language body, @PathVariable Integer Id,@PathVariable String Cd){
       Language dummyBody = null;
       body.setId(Id);body.setCd(Cd);
       Language result=objectService.patchUpdateLanguage(body);
       if(result!=null){
                log.info("Updated successfully");
                return new ResponseEntity<>(body, HttpStatus.OK);
           }
           else{
                log.info("Data to be updated does not exist : {}, {} ", body.getId(),body.getCd());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }


    @PostMapping({"/Language"})
    public ResponseEntity<Language> addLanguage(@Valid @RequestBody Language body) {
        log.info("Record inserted successfully");
        return new ResponseEntity<>(objectService.addLanguage(body), HttpStatus.CREATED);
    }

    @DeleteMapping({"/Language/{Id}/{Cd}"})
    public ResponseEntity deleteLanguage(@PathVariable Integer Id,@PathVariable String Cd) {
        int result=objectService.deleteLanguage(Id,Cd);
        if(result==1){
            log.info("Deleted successfully : {}, {} ", Id,Cd);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        }
        else{
            log.info("Data to be deleted does not exist : {}, {} ", Id,Cd);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data to be deleted does not exist");
        }
    }

}

