package com.gemini.ims.service;
import com.gemini.ims.entity.items.tables.daos.CountryDao;
import com.gemini.ims.entity.items.tables.pojos.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gemini.ims.Exception.ServiceException;

@Service
public class CountryService {

    private static final Logger log = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    CountryDao objectDao ;

    public Country getCountry(Integer Id){
         return objectDao.fetchRecord(Id);
    }

    public int updateCountry(Country classObject){
         return objectDao.updateRecord(classObject);
    }

    public Country patchUpdateCountry(Country classObject){
        objectDao.update(classObject);
        return objectDao.fetchRecord(classObject.getId());
    }

    public Country addCountry(Country classObject){
         return objectDao.insertRecord(classObject);
    }

    public int deleteCountry(Integer Id){
          return objectDao.deleteRecord(Id);
    }


}

