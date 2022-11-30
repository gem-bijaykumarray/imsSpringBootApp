package com.gemini.ims.model;
import java.time.LocalDate;

public class Country {

    private Integer Id;
    

    private String Name;
    

    public Integer getId() {
            return Id;
        }
    
    public String getName() {
        return Name;
    }
    

    public void setId(Integer Id) {
            this.Id = Id;
        }
    
    public void setName(String Name) {
        this.Name = Name;
    }
    

    @Override
    public String toString() {
        return "Country{" +
                "Id=" + Id +
                "Name=" + Name +
                '}';
    }
}