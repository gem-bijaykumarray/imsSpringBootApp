package com.gemini.mis.service;
import com.gemini.mis.entity.items.tables.daos.AuthorDao;
import com.gemini.mis.entity.items.tables.pojos.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gemini.mis.Exception.ServiceException;

@Service
public class AuthorService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    AuthorDao objectDao ;

    public Author getAuthor(Integer Id){
         return objectDao.fetchRecord(Id);
    }

    public int updateAuthor(Author classObject){
         return objectDao.updateRecord(classObject);
    }

    public Author patchUpdateAuthor(Author classObject){
        objectDao.update(classObject);
        return objectDao.fetchRecord(classObject.getId());
    }

    public Author addAuthor(Author classObject){
         return objectDao.insertRecord(classObject);
    }

    public int deleteAuthor(Integer Id){
          return objectDao.deleteRecord(Id);
    }


}

