package com.gemini.ims.service;
import com.gemini.ims.entity.items.tables.daos.LanguageDao;
import com.gemini.ims.entity.items.tables.pojos.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gemini.ims.Exception.ServiceException;

@Service
public class LanguageService {

    private static final Logger log = LoggerFactory.getLogger(LanguageService.class);

    @Autowired
    LanguageDao objectDao ;

    public Language getLanguage(Integer Id,String Cd){
         return objectDao.fetchRecord(Id,Cd);
    }

    public int updateLanguage(Language classObject){
         return objectDao.updateRecord(classObject);
    }

    public Language patchUpdateLanguage(Language classObject){
        objectDao.update(classObject);
        return objectDao.fetchRecord(classObject.getId(),classObject.getCd());
    }

    public Language addLanguage(Language classObject){
         return objectDao.insertRecord(classObject);
    }

    public int deleteLanguage(Integer Id,String Cd){
          return objectDao.deleteRecord(Id,Cd);
    }


}

