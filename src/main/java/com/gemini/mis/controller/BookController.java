package com.gemini.mis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.mis.entity.items.tables.pojos.Book;
import com.gemini.mis.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService objectService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping({"/Book/{Id}"})
        public ResponseEntity<com.gemini.mis.model.Book> getBook(@PathVariable Integer Id) {
            com.gemini.mis.model.Book result=objectMapper.convertValue(objectService.getBook(Id), com.gemini.mis.model.Book.class);
            com.gemini.mis.model.Book dummyBody = null;
                if(result!=null){
                    LOG.info("Data fetched successfully");
                    return new ResponseEntity<>(result, HttpStatus.OK);
                }
                else{
                     LOG.info("Data not found : {}",Id);
                     return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
                }
    }

    @PutMapping({"/Book/{Id}"})
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book body, @PathVariable Integer Id){
       Book dummyBody = null;
       body.setId(Id);
       int result=objectService.updateBook(body);
       if(result == 1){
                LOG.info("Updated successfully");
                return new ResponseEntity<>(body, HttpStatus.OK);
           }
           else{
                LOG.info("Data to be updated does not exist : {} ", body.getId());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }

    @PatchMapping({"/Book/{Id}"})
    public ResponseEntity<Book> patchUpdateBook(@Valid @RequestBody Book body, @PathVariable Integer Id){
       Book dummyBody = null;
       body.setId(Id);
       Book result=objectService.patchUpdateBook(body);
       if(result!=null){
                LOG.info("Updated successfully");
                return new ResponseEntity<>(result, HttpStatus.OK);
           }
           else{
                LOG.info("Data to be updated does not exist : {} ", body.getId());
                return new ResponseEntity<>(dummyBody, HttpStatus.NOT_FOUND);
           }
    }


    @PostMapping({"/Book"})
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book body) {
        LOG.info("Record inserted successfully");
        return new ResponseEntity<>(objectService.addBook(body), HttpStatus.CREATED);
    }

    @DeleteMapping({"/Book/{Id}"})
    public ResponseEntity deleteBook(@PathVariable Integer Id) {
        int result=objectService.deleteBook(Id);
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

