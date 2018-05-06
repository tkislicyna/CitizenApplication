package ru.nsk.tkozlova.controllers.model;

import org.springframework.format.annotation.DateTimeFormat;
import ru.nsk.tkozlova.model.Citizen;
import ru.nsk.tkozlova.model.DocumentType;

import javax.persistence.*;
import java.util.Date;

/**
 * @project CitizenApplication
 * @autor Toma on 5/4/2018.
 */
public class DocumentModel {

    private Integer id;

    private CitizenModel holder = new CitizenModel();

    private DocumentType type;

    private  String authority;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date issueDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date expiryDate;

    public DocumentModel() {}

    public DocumentModel (CitizenModel citizenModel) {
        holder = citizenModel;
    }

    public DocumentModel (Integer id, DocumentType type){
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CitizenModel getHolder() {
        return holder;
    }

    public void setHolder(CitizenModel holder) {
        this.holder = holder;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
