package com.gemini.mis.model;
import java.time.LocalDate;

public class Author {

    private Integer Id;
    

    private String FirstName;
    private String LastName;
    private LocalDate DateOfBirth;
    private Integer YearOfBirth;
    private Byte Distinguished;
    

    public Integer getId() {
            return Id;
        }
    
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }
    public Integer getYearOfBirth() {
        return YearOfBirth;
    }
    public Byte getDistinguished() {
        return Distinguished;
    }
    

    public void setId(Integer Id) {
            this.Id = Id;
        }
    
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public void setDateOfBirth(LocalDate DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }
    public void setYearOfBirth(Integer YearOfBirth) {
        this.YearOfBirth = YearOfBirth;
    }
    public void setDistinguished(Byte Distinguished) {
        this.Distinguished = Distinguished;
    }
    

    @Override
    public String toString() {
        return "Author{" +
                "Id=" + Id +
                "FirstName=" + FirstName +"LastName=" + LastName +"DateOfBirth=" + DateOfBirth +"YearOfBirth=" + YearOfBirth +"Distinguished=" + Distinguished +
                '}';
    }
}