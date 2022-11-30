package com.gemini.ims.service;
import com.gemini.ims.entity.items.tables.daos.BookDao;
import com.gemini.ims.entity.items.tables.pojos.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gemini.ims.Exception.ServiceException;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    @Autowired
    BookDao objectDao ;

    public Book getBook(Integer Id){
         return objectDao.fetchRecord(Id);
    }

    public int updateBook(Book classObject){
         return objectDao.updateRecord(classObject);
    }

    public Book patchUpdateBook(Book classObject){
        objectDao.update(classObject);
        return objectDao.fetchRecord(classObject.getId());
    }

    public Book addBook(Book classObject){
         return objectDao.insertRecord(classObject);
    }

    public int deleteBook(Integer Id){
          return objectDao.deleteRecord(Id);
    }


}

