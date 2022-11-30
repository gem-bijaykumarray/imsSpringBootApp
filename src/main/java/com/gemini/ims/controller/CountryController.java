package com.gemini.ims.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.ims.entity.items.tables.pojos.Country;
import com.gemini.ims.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
public class CountryController {

    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    CountryService objectService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping({"/Country/{Id}"})
        public ResponseEntity<com.gemini.ims.model.Country> getCountry(@PathVariable Integer Id) {
            com.gemini.ims.model.Country result=objectMapper.convertValue(objectService.getCountry(Id), com.gemini.ims.model.Country.class);
            com.gemini.ims.model.Country dummyBody = null;
                if(result!=null){
                    log.info("Data fetched successfully");
                    return new ResponseEntity<>(result, HttpStatus.OK);
                }
                else{
                     log.info("Data not found : {}",Id);
                     return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
                }
    }

    @PutMapping({"/Country/{Id}"})
    public ResponseEntity<Country> updateCountry(@Valid @RequestBody Country body, @PathVariable Integer Id){
       Country dummyBody = null;
       body.setId(Id);
       int result=objectService.updateCountry(body);
       if(result == 1){
                log.info("Updated successfully");
                return new ResponseEntity<>(body, HttpStatus.OK);
           }
           else{
                log.info("Data to be updated does not exist : {} ", body.getId());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }

    @PatchMapping({"/Country/{Id}"})
    public ResponseEntity<Country> patchUpdateCountry(@Valid @RequestBody Country body, @PathVariable Integer Id){
       Country dummyBody = null;
       body.setId(Id);
       Country result=objectService.patchUpdateCountry(body);
       if(result!=null){
                log.info("Updated successfully");
                return new ResponseEntity<>(body, HttpStatus.OK);
           }
           else{
                log.info("Data to be updated does not exist : {} ", body.getId());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }


    @PostMapping({"/Country"})
    public ResponseEntity<Country> addCountry(@Valid @RequestBody Country body) {
        log.info("Record inserted successfully");
        return new ResponseEntity<>(objectService.addCountry(body), HttpStatus.CREATED);
    }

    @DeleteMapping({"/Country/{Id}"})
    public ResponseEntity deleteCountry(@PathVariable Integer Id) {
        int result=objectService.deleteCountry(Id);
        if(result==1){
            log.info("Deleted successfully : {} ", Id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        }
        else{
            log.info("Data to be deleted does not exist : {} ", Id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data to be deleted does not exist");
        }
    }

}

