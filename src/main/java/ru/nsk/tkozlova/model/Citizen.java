package ru.nsk.tkozlova.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */
@Entity
@Table(name = "citizens")
public class Citizen {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "address")
    private String address;

    @OneToMany(cascade = CascadeType.ALL,
            fetch =  FetchType.EAGER,
            mappedBy = "holder")
    private Set<IdentityDocument> documents = new HashSet<>();


    public Integer getId() {
        return id;
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

    public Set<IdentityDocument> getDocuments() {
        return documents;
    }

    public String getFullName() {
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }
}
