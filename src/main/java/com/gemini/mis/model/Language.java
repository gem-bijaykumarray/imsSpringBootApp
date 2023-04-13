package com.gemini.mis.model;
import java.time.LocalDate;

public class Language {

    private Integer Id;
    

    private String Cd;
    private String Description;
    

    public Integer getId() {
            return Id;
        }
    
    public String getCd() {
        return Cd;
    }
    public String getDescription() {
        return Description;
    }
    

    public void setId(Integer Id) {
            this.Id = Id;
        }
    
    public void setCd(String Cd) {
        this.Cd = Cd;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }
    

    @Override
    public String toString() {
        return "Language{" +
                "Id=" + Id +
                "Cd=" + Cd +"Description=" + Description +
                '}';
    }
}