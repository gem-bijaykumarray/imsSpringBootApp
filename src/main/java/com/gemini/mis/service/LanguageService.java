package com.gemini.mis.service;
import com.gemini.mis.entity.items.tables.daos.LanguageDao;
import com.gemini.mis.entity.items.tables.pojos.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gemini.mis.Exception.ServiceException;

@Service
public class LanguageService {

    private static final Logger LOG = LoggerFactory.getLogger(LanguageService.class);

    @Autowired
    LanguageDao objectDao ;

    public Language getLanguage(Integer Id){
         return objectDao.fetchRecord(Id);
    }

    public int updateLanguage(Language classObject){
         return objectDao.updateRecord(classObject);
    }

    public Language patchUpdateLanguage(Language classObject){
        objectDao.update(classObject);
        return objectDao.fetchRecord(classObject.getId());
    }

    public Language addLanguage(Language classObject){
         return objectDao.insertRecord(classObject);
    }

    public int deleteLanguage(Integer Id){
          return objectDao.deleteRecord(Id);
    }


}

