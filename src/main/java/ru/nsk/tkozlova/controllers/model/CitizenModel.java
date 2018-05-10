package ru.nsk.tkozlova.controllers.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @project CitizenApplication
 * @autor Toma on 5/4/2018.
 */
public class CitizenModel {
    private Integer id;

    private String firstName;

    private String middleName;

    private String lastName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDay;

    private String address;

    private String fullName;

    public CitizenModel() {
    }

    public CitizenModel(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    private List<DocumentModel> documents = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<DocumentModel> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentModel> documents) {
        this.documents = documents;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
