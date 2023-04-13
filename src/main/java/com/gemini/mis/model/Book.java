package com.gemini.mis.model;
import java.time.LocalDate;

public class Book {

    private Integer Id;
    

    private Integer AuthorId;
    private String Title;
    private Integer PublishedIn;
    private Integer LanguageId;
    

    public Integer getId() {
            return Id;
        }
    
    public Integer getAuthorId() {
        return AuthorId;
    }
    public String getTitle() {
        return Title;
    }
    public Integer getPublishedIn() {
        return PublishedIn;
    }
    public Integer getLanguageId() {
        return LanguageId;
    }
    

    public void setId(Integer Id) {
            this.Id = Id;
        }
    
    public void setAuthorId(Integer AuthorId) {
        this.AuthorId = AuthorId;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }
    public void setPublishedIn(Integer PublishedIn) {
        this.PublishedIn = PublishedIn;
    }
    public void setLanguageId(Integer LanguageId) {
        this.LanguageId = LanguageId;
    }
    

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + Id +
                "AuthorId=" + AuthorId +"Title=" + Title +"PublishedIn=" + PublishedIn +"LanguageId=" + LanguageId +
                '}';
    }
}