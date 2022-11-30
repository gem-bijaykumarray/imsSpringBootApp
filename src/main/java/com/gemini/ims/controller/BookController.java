package com.gemini.ims.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.ims.entity.items.tables.pojos.Book;
import com.gemini.ims.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService objectService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping({"/Book/{Id}"})
        public ResponseEntity<com.gemini.ims.model.Book> getBook(@PathVariable Integer Id) {
            com.gemini.ims.model.Book result=objectMapper.convertValue(objectService.getBook(Id), com.gemini.ims.model.Book.class);
            com.gemini.ims.model.Book dummyBody = null;
                if(result!=null){
                    log.info("Data fetched successfully");
                    return new ResponseEntity<>(result, HttpStatus.OK);
                }
                else{
                     log.info("Data not found : {}",Id);
                     return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
                }
    }

    @PutMapping({"/Book/{Id}"})
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book body, @PathVariable Integer Id){
       Book dummyBody = null;
       body.setId(Id);
       int result=objectService.updateBook(body);
       if(result == 1){
                log.info("Updated successfully");
                return new ResponseEntity<>(body, HttpStatus.OK);
           }
           else{
                log.info("Data to be updated does not exist : {} ", body.getId());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }

    @PatchMapping({"/Book/{Id}"})
    public ResponseEntity<Book> patchUpdateBook(@Valid @RequestBody Book body, @PathVariable Integer Id){
       Book dummyBody = null;
       body.setId(Id);
       Book result=objectService.patchUpdateBook(body);
       if(result!=null){
                log.info("Updated successfully");
                return new ResponseEntity<>(body, HttpStatus.OK);
           }
           else{
                log.info("Data to be updated does not exist : {} ", body.getId());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }


    @PostMapping({"/Book"})
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book body) {
        log.info("Record inserted successfully");
        return new ResponseEntity<>(objectService.addBook(body), HttpStatus.CREATED);
    }

    @DeleteMapping({"/Book/{Id}"})
    public ResponseEntity deleteBook(@PathVariable Integer Id) {
        int result=objectService.deleteBook(Id);
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

