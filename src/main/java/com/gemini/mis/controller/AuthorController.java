package com.gemini.mis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.mis.entity.items.tables.pojos.Author;
import com.gemini.mis.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
public class AuthorController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    AuthorService objectService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping({"/Author/{Id}"})
        public ResponseEntity<com.gemini.mis.model.Author> getAuthor(@PathVariable Integer Id) {
            com.gemini.mis.model.Author result=objectMapper.convertValue(objectService.getAuthor(Id), com.gemini.mis.model.Author.class);
            com.gemini.mis.model.Author dummyBody = null;
                if(result!=null){
                    LOG.info("Data fetched successfully");
                    return new ResponseEntity<>(result, HttpStatus.OK);
                }
                else{
                     LOG.info("Data not found : {}",Id);
                     return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
                }
    }

    @PutMapping({"/Author/{Id}"})
    public ResponseEntity<Author> updateAuthor(@Valid @RequestBody Author body, @PathVariable Integer Id){
       Author dummyBody = null;
       body.setId(Id);
       int result=objectService.updateAuthor(body);
       if(result == 1){
                LOG.info("Updated successfully");
                return new ResponseEntity<>(body, HttpStatus.OK);
           }
           else{
                LOG.info("Data to be updated does not exist : {} ", body.getId());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }

    @PatchMapping({"/Author/{Id}"})
    public ResponseEntity<Author> patchUpdateAuthor(@Valid @RequestBody Author body, @PathVariable Integer Id){
       Author dummyBody = null;
       body.setId(Id);
       Author result=objectService.patchUpdateAuthor(body);
       if(result!=null){
                LOG.info("Updated successfully");
                return new ResponseEntity<>(result, HttpStatus.OK);
           }
           else{
                LOG.info("Data to be updated does not exist : {} ", body.getId());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }


    @PostMapping({"/Author"})
    public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author body) {
        LOG.info("Record inserted successfully");
        return new ResponseEntity<>(objectService.addAuthor(body), HttpStatus.CREATED);
    }

    @DeleteMapping({"/Author/{Id}"})
    public ResponseEntity deleteAuthor(@PathVariable Integer Id) {
        int result=objectService.deleteAuthor(Id);
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

